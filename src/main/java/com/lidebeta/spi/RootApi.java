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
import com.lidebeta.spi.bean.Admin;
import com.lidebeta.spi.bean.BooleanWrapper;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Customer;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.DeliveryManQuery;
import com.lidebeta.spi.bean.FcmMessage;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.OrderQuery;
import com.lidebeta.spi.bean.Root;
import com.lidebeta.spi.bean.Store;
import com.lidebeta.spi.dao.RootDAO;
import com.lidebeta.spi.dao.RootDAOImpl;

@Api(	name = "rootApi", 
		version = "v1", 
		scopes = { Constants.EMAIL_SCOPE }, 
		clientIds = {
				Constants.WEB_CLIENT_ID, 
				Constants.API_EXPLORER_CLIENT_ID }, 
		description = "API for manage products."
	)
public class RootApi {
	  
	private static final Logger log = Logger.getLogger(RootApi.class.getName());
	private static final RootDAO ROOT_DAO = new RootDAOImpl();
	
	@ApiMethod(name="updateAdmin", path="updateAdmin", httpMethod=HttpMethod.POST)
	public Admin updateAdmin(User user, Admin admin) throws UnauthorizedException{
		authorize(user);
		return ROOT_DAO.updateAdmin(admin);
	}
	
	@ApiMethod(name="fetchAdmin", path="fetchAdmin", httpMethod=HttpMethod.POST)
	public List<Admin> fetchAdmin(User user, Admin admin) throws UnauthorizedException{
		authorize(user);
		return ROOT_DAO.fetchAdmin(admin);
	}
	
	@ApiMethod(name="updateDeliveryMan", path="updateDeliveryMan", httpMethod=HttpMethod.POST)
	public DeliveryMan updateDeliveryMan(User user, DeliveryMan deliveryMan) throws UnauthorizedException{
		authorize(user);
		return ROOT_DAO.updateDeliveryMan(deliveryMan);
	}

	@ApiMethod(name="fetchDeliveryMan", path="fetchDeliveryMan", httpMethod=HttpMethod.POST)
	public List<DeliveryMan> getDeliveryMan(User user, DeliveryManQuery deliveryManQuery) throws UnauthorizedException{
		authorize(user);
		return ROOT_DAO.fetchDeliveryMan(deliveryManQuery);
	}

	@ApiMethod(name="fetchOrder", path="fetchOrder", httpMethod=HttpMethod.POST)
	public List<Order> fetchOrder(User user, OrderQuery orderQuery) throws UnauthorizedException{
		authorize(user);
		return ROOT_DAO.fetchOrder(orderQuery);
	
	}

	@ApiMethod(name="updateOrder", path="updateOrder", httpMethod=HttpMethod.POST)
	public Order updateOrder(User user, Order order) throws UnauthorizedException{
		authorize(user);
		return ROOT_DAO.updateOrder(order);
	
	}
	
	@ApiMethod(name="updateCoverageArea", path="updateCoverageArea", httpMethod=HttpMethod.POST)
	public CoverageArea updateCoverageArea(User user, CoverageArea coverageArea) throws UnauthorizedException{
		authorize(user);
		return ROOT_DAO.updateCoverageArea(coverageArea);
	
	}
	
	@ApiMethod(name="updateStore", path="updateStore", httpMethod=HttpMethod.POST)
	public Store updateStore(User user, Store store) throws UnauthorizedException{
		authorize(user);
		return ROOT_DAO.updateStore(store);
	}
	
	@ApiMethod(name="sendTopicNotification", path="sendTopicNotification", httpMethod=HttpMethod.POST)
	public void sendTopicNotification(User user, Store store) throws UnauthorizedException{
		authorize(user);

		FcmMessage fcmMessage = new FcmMessage();
		fcmMessage.setTo("/topics/5629499534213120");
		fcmMessage.setTitle("TOPIC MESSAGE");
		fcmMessage.setBody("Compra mas!");
		fcmMessage.setIcon(FcmMessage.TITLE_ICON_NOTIFICATION);
		fcmMessage.send();
		
	}
	
	@ApiMethod(name="blockCustomer", path="blockCustomer", httpMethod=HttpMethod.POST)
	public Customer blockCustomer(User user, Customer customer) throws UnauthorizedException{
		authorize(user);

		return ROOT_DAO.blockCustomer(customer);
		
	}
	
	private void authorize(User user) throws UnauthorizedException{
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required user = " + user);
    	}
		Key<Root> rootKey = Key.create(Root.class, user.getEmail());
		Root root = ofy().load().key(rootKey).now();
		if(root==null){
			throw new UnauthorizedException("Authentication fail root = " + root);
		}
	}	
	
	// @ApiMethod(name="rootUp", path="rootUp", httpMethod=HttpMethod.POST)
	// public Root rootUp(User user) throws UnauthorizedException{
	// 	Root root = new Root();
	// 	root.setId(user.getEmail());
	// 	ofy().save().entity(root).now();
	// 	return root;
	// }
	
}
