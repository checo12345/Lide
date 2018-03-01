package com.lidebeta.spi.dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.lidebeta.spi.DeliverApi;
import com.lidebeta.spi.bean.Admin;
import com.lidebeta.spi.bean.BooleanWrapper;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Customer;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.FcmMessage;
import com.lidebeta.spi.bean.Notification;
import com.lidebeta.spi.bean.NotificationToken;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.OrderResponse;
import com.lidebeta.spi.bean.Total;

import static com.lidebeta.spi.service.OfyService.ofy;
public class DeliveryDAOImpl implements DeliveryDAO{
	
	private static final Logger log = Logger.getLogger(DeliveryDAOImpl.class.getName());
	private static final int MAX_NUMBER_OF_ORDERS = 3;
	
	@Override
	public List<Order> getOrdersByCoverageArea(final DeliveryMan deliveryMan) {
		
		List<Order> asingOpenOrders= ofy().load().
				type(Order.class).
				filter("open", true).
				filter("deliveryManId", deliveryMan.getId()).
				list();
		
		if(MAX_NUMBER_OF_ORDERS<=asingOpenOrders.size()){
			return putCoverageAreaId(asingOpenOrders);
		}
		
		final List<Order> avaibleOrders = ofy().
				load().
				type(Order.class).
				ancestor(Key.create(CoverageArea.class, deliveryMan.getCoverageAreaId())).
				filter("open", true).
				filter("deliveryManId", null).
				order("date").
				limit(MAX_NUMBER_OF_ORDERS-asingOpenOrders.size()).list();
		
		
		
		if(avaibleOrders.size()==0){
			return putCoverageAreaId(asingOpenOrders);
		}
		
		for(Order order: avaibleOrders){
			order.setDeliveryManId(deliveryMan.getId());
        	order.setStatus(Order.ORDER_STATUS_ASSIGN);
        	order.setStatusCode(Order.ORDER_STATUS_ASSIGN_CODE);
		}
		
		ofy().save().entities(avaibleOrders).now();
		
		asingOpenOrders= ofy().load().
				type(Order.class).
				filter("open", true).
				filter("deliveryManId", deliveryMan.getId()).
				list();
			
		deliveryMan.setAvaibleNumberOfOrders(MAX_NUMBER_OF_ORDERS-asingOpenOrders.size());
		ofy().save().entity(deliveryMan).now();
		
		return putCoverageAreaId(asingOpenOrders);
		
	}
	
	private List<Order> putCoverageAreaId(List<Order> orders){
		for(Order order: orders){
			order.setCoverageAreaId(order.getCoverageAreaKey().getId());
		}
		return orders;
	}
	
	@Override
	public OrderResponse updateOrder(Order order, DeliveryMan deliveryMan) {		
		
		Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, order.getCoverageAreaId());
		Key<Order> orderKey = Key.create(coverageAreaKey, Order.class, order.getId());
		
		final Order _order = ofy().load().key(orderKey).now();
		
		Total total = new Total();
		total.setProducts(order.getProducts());
		CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
		total = customerDAOImpl.getTotal(total, order.getCoverageAreaId());
		
		if(total!=null){
			_order.setTotal(total.getTotal());
		}
		
		_order.setProducts(order.getProducts());
		_order.setCoverageAreaId(order.getCoverageAreaId());
		
		if(!order.isOpen()){//it means that its going to close the order
			_order.setOpen(order.isOpen());
			_order.setObservation(order.getObservation());
			_order.setSuccess(order.isSuccess());
			_order.setStatus(order.isSuccess()?Order.ORDER_STATUS_SUCCESS:Order.ORDER_STATUS_CANCEL);
			_order.setStatusCode(order.isSuccess()?Order.ORDER_STATUS_SUCCESS_CODE:Order.ORDER_STATUS_CANCEL_CODE);
			_order.setDeliveryManId(deliveryMan.getId());
		}
		
//		Key<DeliveryMan> deliveryManKey = Key.create(DeliveryMan.class, _order.getDeliveryManId());
//		final DeliveryMan deliveryMan = ofy().load().key(deliveryManKey).now();
		
		BooleanWrapper result = ofy().transact(new Work<BooleanWrapper>() {
		    public BooleanWrapper run() {
		    	Key<Order> orderKey = ofy().save().entity(_order).now();
//		    	Key<DeliveryMan> deliveryManKey = ofy().save().entity(deliveryMan).now();
		    	
		        return new BooleanWrapper(orderKey!=null/* && deliveryManKey!= null*/);
		    }
		});
		
		if(result.isSuccess() && !_order.isOpen()){
			sendCloseNotification(_order);
			notifyAdminOrderClose(_order);
			deliveryMan.setAvaibleNumberOfOrders(deliveryMan.getAvaibleNumberOfOrders()+1);
			ofy().save().entity(deliveryMan).now();
		}else if(result.isSuccess() && _order.isOpen()){
			sendUpdateNotification(_order);
		}
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setSuccess(result.isSuccess());
		return orderResponse;
	}
	
	private void sendCloseNotification(Order order){
		Key<Customer> customerKey = Key.create(Customer.class, order.getUserID());
		Customer customer = ofy().load().key(customerKey).now();
		if(customer!=null){
			FcmMessage fcmMessage = new FcmMessage();
			fcmMessage.setTo(customer.getToken());
			fcmMessage.setTitle(order.isSuccess()?FcmMessage.TITLE_SUCCESS_ORDER:FcmMessage.TITLE_FAIL_ORDER);
			fcmMessage.setBody("");
			fcmMessage.setIcon(FcmMessage.TITLE_ICON_NOTIFICATION);
			fcmMessage.addDataEntry(FcmMessage.DATA_ORDER_CLOSED, order.getId().toString());
			fcmMessage.addDataEntry(FcmMessage.DATA_ORDER_STATUS, order.isSuccess()?
					Order.ORDER_STATUS_SUCCESS:Order.ORDER_STATUS_CANCEL);
			fcmMessage.send();
		}
	}
	
	private void notifyAdminOrderClose(Order order){
		
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
			fcmMessage.setTitle(order.isSuccess()?FcmMessage.TITLE_SUCCESS_ORDER:FcmMessage.TITLE_FAIL_ORDER);
			fcmMessage.setBody("");
			fcmMessage.setIcon(FcmMessage.TITLE_ICON_NOTIFICATION);
			fcmMessage.addDataEntry(FcmMessage.DATA_ORDER_CLOSED, order.getId().toString());
			fcmMessage.addDataEntry(FcmMessage.DATA_ORDER_ID_KEY, order.isSuccess()?
					Order.ORDER_STATUS_SUCCESS:Order.ORDER_STATUS_CANCEL);
			fcmMessage.send();
		}
		
	}
	
	private void sendUpdateNotification(Order order){
		Key<Customer> customerKey = Key.create(Customer.class, order.getUserID());
		Customer customer = ofy().load().key(customerKey).now();
		if(customer!=null){
			FcmMessage fcmMessage = new FcmMessage();
			fcmMessage.setTo(customer.getToken());
			fcmMessage.setTitle(FcmMessage.TITLE_UPDATE_ORDER);
			fcmMessage.setBody("");
			fcmMessage.setIcon(FcmMessage.TITLE_ICON_NOTIFICATION);
			fcmMessage.addDataEntry(FcmMessage.DATA_ORDER_UPDATED, order.getId().toString());
			fcmMessage.send();
		}
	}
	
	@Override
	public DeliveryMan updateMessengerToken(DeliveryMan deliveryMan, NotificationToken messengerToken) {
		deliveryMan.setMessengerToken(messengerToken.getToken());
		Key<DeliveryMan> result = ofy().save().entity(deliveryMan).now();
		
		return result!=null?deliveryMan:null;
	}

	
	
	
	@Override
	public BooleanWrapper sendNotification(DeliveryMan deliveryMan, Notification notification) {
		Key<Customer> customerKey = Key.create(Customer.class, notification.getCustomerId());
		Customer customer = ofy().load().key(customerKey).now();
		
		if(customer!=null && customer.getToken()!=null){
			FcmMessage fcmMessage = new FcmMessage();
			fcmMessage.setTo(customer.getToken());
			fcmMessage.setTitle(FcmMessage.TITLE_MESSENGER_NOTIFICATION);
			fcmMessage.setBody(notification.getMessage());
			fcmMessage.setIcon(FcmMessage.TITLE_ICON_NOTIFICATION);
			fcmMessage.addDataEntry(FcmMessage.DATA_MESSAGE_KEY, notification.getMessage());
			fcmMessage.addDataEntry(FcmMessage.DATA_ORDER_ID_KEY, notification.getOrderId().toString());
			return fcmMessage.send();
		}else{
			return new BooleanWrapper(true, "missing customer");
		}
		
	}

	
}
