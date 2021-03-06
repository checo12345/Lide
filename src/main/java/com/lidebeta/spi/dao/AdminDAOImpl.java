package com.lidebeta.spi.dao;

import static com.lidebeta.spi.service.OfyService.ofy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.lidebeta.spi.AdminApi;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.PutResponse;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.lidebeta.spi.ProductContract;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Keyword;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.bean.Store;
import com.lidebeta.spi.bean.Sync;

public class AdminDAOImpl implements AdminDAO {

	private static final Logger log = Logger.getLogger(AdminApi.class.getName());

	private com.google.appengine.api.search.Index getIndex(String name) {
		IndexSpec indexSpec = IndexSpec.newBuilder().setName(name).build();
	    com.google.appengine.api.search.Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
	    return index;
	}

	private List<Product> productsFromScoredDocuments(Results<ScoredDocument> results){
		List<Product> products = new ArrayList<Product>();
		for (ScoredDocument document : results) {
	      	products.add(productFromDocument(document));
		}
		return products;
	}
	
	private Product productFromDocument(Document document) {
		Product product = new Product();
  	  	product.setCoverageAreaId	(Long.parseLong(document.getOnlyField(ProductContract.COLUMN_COVERAGE_AREA_ID).getAtom()));
  	  	product.setStoreId			(Long.parseLong(document.getOnlyField(ProductContract.COLUMN_STORE_ID).getAtom()));
  		product.setId				(document.getId()); 
  		product.setName				(document.getOnlyField(ProductContract.COLUMN_NAME).getText()); 
  		product.setDescription		(document.getOnlyField(ProductContract.COLUMN_DESCRIPTION).getText()); 
  		product.setCodigoBarras		(document.getOnlyField(ProductContract.COLUMN_CODIGO_BARRAS).getText()); 
  		product.setAvaible			(document.getOnlyField(ProductContract.COLUMN_AVAIBLE).getNumber()==1);
  		product.setPrice			(document.getOnlyField(ProductContract.COLUMN_PRICE).getNumber()); 
  		product.setImage			(document.getOnlyField(ProductContract.COLUMN_IMAGE).getAtom());
  		product.setKeywords			(document.getOnlyField(ProductContract.COLUMN_KEYWORDS).getText());
  		product.setCategories		(document.getOnlyField(ProductContract.COLUMN_CATEGORIES).getText());
  		product.setQuantity  		(document.getOnlyField(ProductContract.COLUMN_QUANTITY).getNumber().intValue());
		return product;
	}
	
	private Document documentFromProduct(Product product){
		Document.Builder docBuilder = Document.newBuilder()
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_COVERAGE_AREA_ID)	.setAtom(product.getCoverageAreaId().toString()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_STORE_ID)			.setAtom(product.getStoreId().toString()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_NAME)				.setText(product.getName()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_DESCRIPTION)		.setText(product.getDescription()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_CODIGO_BARRAS)		.setText(product.getCodigoBarras()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_KEYWORDS)			.setText(product.getKeywords()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_CATEGORIES)			.setText(product.getCategories()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_AVAIBLE)			.setNumber(product.isAvaible()?1:0))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_PRICE)				.setNumber(product.getPrice()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_LAST_UPDATE)		.setDate(Calendar.getInstance().getTime()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_IMAGE)				.setAtom(product.getImage()))
			    .addField(Field.newBuilder().setName(ProductContract.COLUMN_QUANTITY)			.setNumber(product.getQuantity()));
		
		if(product.getId()!=null){
			docBuilder.setId(product.getId());
		}
			    
		return docBuilder.build();
	}
	
	@Override
	public void cleanIndex(String coverageAreaId) {
		com.google.appengine.api.search.Index index = getIndex(coverageAreaId);
		StringBuilder query = new StringBuilder();
		Results<ScoredDocument> results = index.search(query.toString());
		System.out.println("------------------>"+results.getNumberFound());
		while(results.getNumberFound()>0) {
			List<String> ids = new ArrayList<>();
			for (ScoredDocument document : results) {
				ids.add(document.getId());
			}
			index.delete(ids);
			results = index.search(query.toString());
			System.out.println("------------------>"+results.getNumberFound());
		}
	}
	
	@Override
	public Product updateProduct(Product product) {
		System.out.println("product = "+product.toString());
		PutResponse putResponse = 
				getIndex(product.getCoverageAreaId().toString()).put(documentFromProduct(product));
		for(String id: putResponse.getIds()){
			product.setId(id);
		}
		return product;
	}
	
	@Override
	public List<Product> fetchProductsByKeyword(Keyword keyword) {
		
		StringBuilder query = new StringBuilder();
		query.append(ProductContract.COLUMN_STORE_ID);
		query.append(" = ");
		query.append(keyword.getStore_id());
		query.append(" AND ");
		query.append(ProductContract.COLUMN_KEYWORDS);
		query.append(" = ");
		query.append(keyword.getName());
		
		Results<ScoredDocument> results = getIndex(keyword.getCoverage_area_id().toString()).search(query.toString());
		return productsFromScoredDocuments(results);
	}

	@Override
	public Product fetchProductByCb(Product product) {
		StringBuilder query = new StringBuilder();
		query.append(ProductContract.COLUMN_STORE_ID);
		query.append(" = ");
		query.append(product.getStoreId());
		query.append(" AND ");
		query.append(ProductContract.COLUMN_CODIGO_BARRAS);
		query.append(" = ");
		query.append(product.getCodigoBarras());
		
		System.out.println();
		System.out.println("----------------------"+query);
		System.out.println();
		
		Results<ScoredDocument> results = getIndex(product.getCoverageAreaId().toString()).search(query.toString());
		
		System.out.println();
		System.out.println("----------------------"+results);
		System.out.println();
		
		List<Product> productList = productsFromScoredDocuments(results);
		return productList.isEmpty()?null:productList.get(0);
	}
	
	@Override
	public Product fetchProductById(Product product) {
		Document doc = getIndex(product.getCoverageAreaId().toString()).get(product.getId());
		return doc!=null?productFromDocument(doc):null;
	}
	
	@Override
	public Store updateStore(Store store) {
		Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, store.getCoverageAreaId());
		CoverageArea coverageArea = ofy().load().key(coverageAreaKey).now();
		
		if(coverageArea==null){
			throw new IllegalArgumentException("Coverage Area with id="+store.getCoverageAreaId()+" does not exist");
		}
		
		Key<Store> storeKey = Key.create(coverageAreaKey, Store.class, store.getId());
		Store _store = ofy().load().key(storeKey).now();
		
		
		store.setCoverageAreaKey(coverageAreaKey);
		_store.setImageName(store.getImageName());
		_store.setLocation(store.getLocation());
		_store.setName(store.getName());
		_store.setOpen(store.isOpen());
		
		
		storeKey = ofy().save().entity(_store).now();
		return storeKey!=null?store:null;
	}

	@Override
	public List<Order> getOrders(Sync sync) {
		
		Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, sync.getCoverageAreaId());
		List<Order> orders = new ArrayList<>(); 
		if(sync.getFromDate()!=null){
			
			Date fromDate = sync.getFromDate();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			Date yesterday = calendar.getTime();
			fromDate = yesterday.before(fromDate)?fromDate:yesterday;
			
			Query<Order> query = ofy().load().
					type(Order.class).
					ancestor(coverageAreaKey).
					filter("date >", fromDate);
			if(sync.getOpen()!=null){
				query  = query.filter("open", sync.getOpen().booleanValue());
			}
			
			List<Order> ordersFromDate = query.list();
			
			orders.addAll(ordersFromDate);
		}
		
		if(sync.getOrders()!=null && !sync.getOrders().isEmpty()){
			
			for(Order order : sync.getOrders()){
				order.setCoverageAreaKey(coverageAreaKey);
			}
			
			Collection<Order> ordersFromList = ofy().load().entities(sync.getOrders()).values();
			
			orders.addAll(ordersFromList);
			
		}
		
		for(Order order: orders){
			order.setCoverageAreaId(order.getCoverageAreaKey().getId());
		}
		
		return orders;
	}

	public void actualizaInventario(Product product) {
		//Prodcto debe tener codigo de barras, store y coverageArea
		Product indexProd = fetchProductByCb( product);
		indexProd.setQuantity(indexProd.getQuantity()+product.getPedido());
		updateProduct(indexProd);
	}
	
}
