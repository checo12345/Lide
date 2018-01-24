package com.lidebeta.spi.dao;

import static com.lidebeta.spi.service.OfyService.ofy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.googlecode.objectify.Key;
import com.lidebeta.spi.ProductContract;
import com.lidebeta.spi.RootApi;
import com.lidebeta.spi.bean.Admin;
import com.lidebeta.spi.bean.BooleanWrapper;
import com.lidebeta.spi.bean.Category;
import com.lidebeta.spi.bean.CompactProduct;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Customer;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.FcmMessage;
import com.lidebeta.spi.bean.Keyword;
import com.lidebeta.spi.bean.LatLng;
import com.lidebeta.spi.bean.Notification;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.bean.Response;
import com.lidebeta.spi.bean.Store;
import com.lidebeta.spi.bean.Total;
import com.lidebeta.spi.bean.Response.Reason;

public class CustomerDAOImpl implements CustomerDAO{
	
	private static final Logger log = Logger.getLogger(RootApi.class.getName());
	
	private com.google.appengine.api.search.Index getIndex(String name) {
		IndexSpec indexSpec = IndexSpec.newBuilder().setName(name).build();
	    com.google.appengine.api.search.Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
	    return index;
	}
	
	private List<Product> parseResultProducts(Results<ScoredDocument> results){
		List<Product> products = new ArrayList<Product>();
		for (ScoredDocument document : results) {
			Product product = new Product();
      	  	product.setCoverageAreaId	(Long.parseLong(document.getOnlyField(ProductContract.COLUMN_COVERAGE_AREA_ID).getAtom()));
      	  	product.setStoreId			(Long.parseLong(document.getOnlyField(ProductContract.COLUMN_STORE_ID).getAtom()));
	  		product.setId				(document.getId()); 
	  		product.setName				(document.getOnlyField(ProductContract.COLUMN_NAME).getText()); 
	  		product.setDescription		(document.getOnlyField(ProductContract.COLUMN_DESCRIPTION).getText()); 
	  		product.setAvaible			(document.getOnlyField(ProductContract.COLUMN_AVAIBLE).getNumber()==1);
	  		product.setPrice			(document.getOnlyField(ProductContract.COLUMN_PRICE).getNumber()); 
	  		product.setImage			(document.getOnlyField(ProductContract.COLUMN_IMAGE).getAtom());
	      	products.add(product);
		}
		return products;
	}
	
	@Override
	public List<Product> getProductsByCategoryCustomer(Category category) {
//		String queryString = ProductContract.COLUMN_STORE_ID+" = " + category.getStore_id() + 
//				" AND " + ProductContract.COLUMN_CATEGORIES + " = " + category.getName();
		String queryString = ProductContract.COLUMN_CATEGORIES + " = " + category.getName() + " AND " + 
				ProductContract.COLUMN_AVAIBLE + " = " + String.valueOf(1);
		
		Results<ScoredDocument> results = getIndex(category.getCoverage_area_id().toString()).search(queryString);
		return parseResultProducts(results);
	}

	@Override
	public List<Product> getProductsByKeywordCustomer(Keyword keyword) {
		
		StringBuilder query = new StringBuilder();
		if(keyword.getStore_id()!=null){
			query.append(ProductContract.COLUMN_STORE_ID);
			query.append(" = ");
			query.append(keyword.getStore_id());
			query.append(" AND ");
		}
		
		
		//TODO Traer la lista de tiendas por area de covertura y incluir en el query NOT 123456789
		//para cada tienda cerrada
		
		query.append(ProductContract.COLUMN_AVAIBLE);
		query.append(" = ");
		query.append(String.valueOf(1));
		query.append(" AND ");
		
		query.append(ProductContract.COLUMN_KEYWORDS);
		query.append(" = ");
		query.append(keyword.getName());
		
		Results<ScoredDocument> results = getIndex(keyword.getCoverage_area_id().toString()).search(query.toString());
		return parseResultProducts(results);
	}

	@Override
	public List<CoverageArea> fetchCoveragesAreasCustomer() {
		return ofy().load().type(CoverageArea.class).list();
	}

	@Override
	public List<Store> fetchStoresByCoverageAreaCustomer(CoverageArea coverageArea) {
		List<Store> stores = ofy().load().type(Store.class).
				ancestor( Key.create(CoverageArea.class, coverageArea.getId()) )/*.filter("open", true)*/.list();
		return stores;
	}

	@Override
	public List<Category> fetchCategoriesByStoreCustomer(Store store) {
		return ofy().load().type(Category.class).ancestor( Key.create(Store.class, store.getId()) ).list();
	}
	
	@Override
	public Total getTotal(Total total, Long coverageAreaId) {
		
		if(total==null || total.getProducts()==null){
			return null;
		}

		BigDecimal _total = new BigDecimal(0);
		com.google.appengine.api.search.Index index = getIndex(coverageAreaId.toString());
		for(CompactProduct compactProduct: total.getProducts()){
			Document document = index.get(compactProduct.getId());
			BigDecimal productQuantity = new BigDecimal(compactProduct.getQuantity());
			BigDecimal productPrice = new BigDecimal(document.getOnlyField(ProductContract.COLUMN_PRICE).getNumber());
			BigDecimal productTotal = productPrice.multiply(productQuantity);
			_total = _total.add(productTotal);
		}
		total.setTotal(_total.doubleValue());
		return total;
		
	}

	@Override
	public Response putOrder(Order order) {
		
		List<Store> stores = ofy().load().type(Store.class).
				ancestor( Key.create(CoverageArea.class, order.getCoverageAreaId())).filter("open", true).list();
		
		if(stores.isEmpty()){
			return new Response(false, Reason.CLOSE_STORE);
		}
		
		if(hasOrderOpen(order.getUserID())){
			return new Response(false, Reason.HAS_ORDER_OPEN);
		}
		
		LatLng destination = order.getDestination();
		List<CompactProduct> products = order.getProducts();
		
		if(order==null || destination == null || products == null){
			return new Response(false, Reason.NULL_PARAMETER_NEDED);
		}else if(order.getCoverageAreaId()==null){
			return new Response(false, Reason.MISSING_COVERAGE_AREA_ID);
		}else if(products.size()==0){
			return new Response(false, Reason.ZERO_PRODUCTS);
		}
		
		Total total = new Total();
		total.setProducts(order.getProducts());
		total = getTotal(total, order.getCoverageAreaId());
		
		
		if(total==null){
			return new Response(false, Reason.NULL_TOTAL);
		}else if(total.getTotal()==0){
			return new Response(false, Reason.EMPTY_TOTAL);
		}
		
		Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, order.getCoverageAreaId());
		CoverageArea coverageArea = ofy().load().key(coverageAreaKey).now();
		
		if(coverageArea!=null){
		
			order.setCoverageAreaKey( coverageAreaKey);
			order.setDate( Calendar.getInstance().getTime());
			order.setOpen(true);
			order.setSuccess(false);
			order.setObservation(null);
			order.setTotal(total.getTotal());
			order.setStatus(Order.ORDER_STATUS_PENDING);
			order.setStatusCode(Order.ORDER_STATUS_PENDING_CODE);
			Key<Order> orderKey = ofy().save().entity(order).now();
			
			if(orderKey != null){
				notifyDeliveryMan(order);
				notifyAdmin(order);
				Response response = new Response(true, Reason.SUCCESS);
				response.setOrderId(orderKey.getId());
				response.setOrderDate(order.getDate());
				response.setTotal(total.getTotal());
				return response;
			}else{
				return new Response(false, Reason.ORDER_RETURN_NULL_FROM_DATASTORE);
			}
			
		}else{
			return new Response(false, Reason.COVERAGE_AREA_ID_NOT_EXIST);
		}
	}

	private boolean hasOrderOpen(String customerId){
		List<Order> orders = 
				ofy().
				load().
				type(Order.class).
				filter("userID", customerId).
				filter("open", true).
				list();
		return orders.size()>0;
	}
	
	
	@Override
	public BooleanWrapper updateNotificationToken(Customer customer) {
		Key<Customer> customerKey = ofy().save().entity(customer).now();			
		return new BooleanWrapper(customerKey!=null, customerKey!=null?"":"problems davind entity");
	}

	@Override
	public BooleanWrapper sendNotification(Notification notification) {
		
		Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, notification.getCoverageAreaId());
		Key<Order> orderKey = Key.create(coverageAreaKey, Order.class, notification.getOrderId());
		
		Order order = ofy().load().key(orderKey).now();
				
		if(order!=null && order.getDeliveryManId()!=null){
			
			Key<DeliveryMan> deliveryManKey = Key.create(DeliveryMan.class, order.getDeliveryManId());
			DeliveryMan deliveryMan = ofy().load().key(deliveryManKey).now();
			
			if(deliveryMan!=null){
				FcmMessage fcmMessage = new FcmMessage();
				fcmMessage.setTo(deliveryMan.getMessengerToken());
				fcmMessage.setTitle(FcmMessage.TITLE_MESSENGER_NOTIFICATION);
				fcmMessage.setBody(notification.getMessage());
				fcmMessage.setIcon(FcmMessage.TITLE_ICON_NOTIFICATION);
				fcmMessage.addDataEntry(FcmMessage.DATA_MESSAGE_KEY, notification.getMessage());
				fcmMessage.addDataEntry(FcmMessage.DATA_ORDER_ID_KEY, notification.getOrderId().toString());
				fcmMessage.send();
				return new BooleanWrapper(true, "OK");
			}else{
				return new BooleanWrapper(false, "No delivery man has taken this order!");
			}
			
		}else{
			return new BooleanWrapper(false, "No souch order!");
		}
		
	}

	private void notifyDeliveryMan(Order order){
		List<DeliveryMan> deliveryManList = ofy().
				load().
				type(DeliveryMan.class).
				filter("coverageAreaId", order.getCoverageAreaId()).
				filter("avaibleNumberOfOrders >", 0).
				filter("active", true).
				order("-avaibleNumberOfOrders").
				limit(1).
				list(); 
		for(DeliveryMan deliveryMan:deliveryManList){
			FcmMessage fcmMessage = new FcmMessage();
			fcmMessage.setTo(deliveryMan.getMessengerToken());
			fcmMessage.setTitle(FcmMessage.TITLE_NEW_ORDER);
			fcmMessage.setBody(order.getId().toString());
			fcmMessage.setIcon(FcmMessage.TITLE_ICON_NOTIFICATION);
			fcmMessage.addDataEntry(FcmMessage.DATA_NEW_ORDER, order.getId().toString());
			fcmMessage.send();
		}
		
	}
	
	private void notifyAdmin(Order order){
		
		
		List<Admin> adminList = ofy().
				load().
				type(Admin.class).
				filter("active", true).
				list(); 
		
		
		Iterator<Admin> iter = adminList.iterator();

		while (iter.hasNext()) {
			Admin admin = iter.next();
			if(!admin.getCoverageAreaIdList().contains(order.getCoverageAreaId())){
				iter.remove();
			}
		}
		
		
		for(Admin admin: adminList){
			FcmMessage fcmMessage = new FcmMessage();
			fcmMessage.setTo(admin.getMessengerToken());
			fcmMessage.setTitle(FcmMessage.TITLE_NEW_ORDER);
			fcmMessage.setBody(order.getId().toString());
			fcmMessage.setIcon(FcmMessage.TITLE_ICON_NOTIFICATION);
			fcmMessage.addDataEntry(FcmMessage.DATA_NEW_ORDER, order.getId().toString());
			fcmMessage.send();
		}
		
	}
	
}
