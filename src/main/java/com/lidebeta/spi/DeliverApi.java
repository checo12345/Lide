package com.lidebeta.spi;

import static com.lidebeta.spi.service.OfyService.ofy;

import java.util.List;
import java.util.logging.Logger;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;
import com.lidebeta.spi.bean.BooleanWrapper;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.Notification;
import com.lidebeta.spi.bean.NotificationToken;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.OrderResponse;
import com.lidebeta.spi.business.DeliveryBusinessImpl;

@Api(
		name = "deliverApi", 
		version = "v1", 
		scopes = { 
				Constants.EMAIL_SCOPE 
				}, 
		clientIds = {
				Constants.WEB_CLIENT_ID, 
				Constants.API_EXPLORER_CLIENT_ID,
				Constants.ANDROID_CLIENT_ID,
				"299646937934-r1et88074vhp0qpksuon32ec2qibs52q.apps.googleusercontent.com"
//				,				"299646937934-p45gchbmkd6ck1fne34vu4tjk99560i1.apps.googleusercontent.com"//debug
		}, 
		audiences = {Constants.ANDROID_AUDIENCE},
		description = "API for delivery team"
	)
public class DeliverApi {
	
	private static final Logger log = Logger.getLogger(DeliverApi.class.getName());
	private static final DeliveryBusinessImpl DELIVERY_BUSINESS = new DeliveryBusinessImpl();
	
	@ApiMethod(name="getOrders", path="getOrders", httpMethod=HttpMethod.GET)
	public List<Order> getOrders(final User user) throws UnauthorizedException{
		
		if(user==null){
			throw new UnauthorizedException("Authorization required user = " + null);
		}
		
		Key<DeliveryMan> deliveryManKey = Key.create(DeliveryMan.class, user.getEmail());
		DeliveryMan deliveryMan = ofy().load().key(deliveryManKey).now();
		
		if(deliveryMan==null){
			throw new UnauthorizedException("Authentication fail deliveryMan = " + String.valueOf(deliveryMan));
		}
		
		List<Order> orders = DELIVERY_BUSINESS.getOrdersByCoverageArea(deliveryMan);
		
		for(Order order: orders){
			System.out.println(order.toString());
		}
		
    	return orders;
	}
	

	@ApiMethod(name="updateOrder", path="updateOrder", httpMethod=HttpMethod.POST)
	public OrderResponse updateOrder(User user, Order order) throws UnauthorizedException{
		
		if(user==null){
			throw new UnauthorizedException("Authorization required");
		}
		
		Key<DeliveryMan> deliveryManKey = Key.create(DeliveryMan.class, user.getEmail());
		DeliveryMan deliveryMan = ofy().load().key(deliveryManKey).now();
		
		if(deliveryMan==null){
			throw new UnauthorizedException("Authentication fail");
		}
		
		return DELIVERY_BUSINESS.updateOrder(order, deliveryMan);
	}
	
	
	@ApiMethod(name="updateNotificationToken", path="updateNotificationToken", httpMethod=HttpMethod.POST)
	public DeliveryMan updateNotificationToken(User user, NotificationToken token) throws UnauthorizedException{
		
		log.severe("--------------------user = "+ user);
		
		if(user==null){
			throw new UnauthorizedException("Authorization required");
		}
		
		Key<DeliveryMan> deliveryManKey = Key.create(DeliveryMan.class, user.getEmail());
		DeliveryMan deliveryMan = ofy().load().key(deliveryManKey).now();
		
		log.severe("--------------------deliveryMan = "+ deliveryMan);	
		
		if(deliveryMan==null){
			throw new UnauthorizedException("Authentication fail");
		}
		
		if(token==null || token.getToken()==null){
			throw new IllegalArgumentException("token is missing");
		}
		
		
    	return DELIVERY_BUSINESS.updateMessengerToken(deliveryMan, token);
	}
	
	@ApiMethod(name="sendNotification", path="sendNotification", httpMethod=HttpMethod.POST)
	public BooleanWrapper sendNotification(User user, Notification notification) throws UnauthorizedException{
		
		if(user==null){
			throw new UnauthorizedException("Authorization required");
		}

		Key<DeliveryMan> deliveryManKey = Key.create(DeliveryMan.class, user.getEmail());
		DeliveryMan deliveryMan = ofy().load().key(deliveryManKey).now();
		
		
		
		if(deliveryMan==null){
			throw new UnauthorizedException("Authentication fail");
		}
		if(notification==null || notification.getMessage()==null){
			throw new IllegalArgumentException("message is missing");
		}
		
    	return DELIVERY_BUSINESS.sendNotification(deliveryMan, notification);
	}
	
}
