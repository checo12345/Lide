package com.lidebeta.spi;

import static com.lidebeta.spi.service.OfyService.factory;
import static com.lidebeta.spi.service.OfyService.ofy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.PutException;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.StatusCode;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.annotation.Index;
import com.lidebeta.spi.bean.BooleanWrapper;
import com.lidebeta.spi.bean.Category;
import com.lidebeta.spi.bean.CompactProduct;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Customer;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.Keyword;
import com.lidebeta.spi.bean.LatLng;
import com.lidebeta.spi.bean.Notification;
import com.lidebeta.spi.bean.NotificationToken;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.OrderWrapper;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.bean.Response;
import com.lidebeta.spi.bean.Response.Reason;
import com.lidebeta.spi.bean.Store;
import com.lidebeta.spi.bean.Total;
import com.lidebeta.spi.business.CustomerBusiness;
import com.lidebeta.spi.business.CustomerBusinessImpl;




@Api(
		name = "customerApi", 
		version = "v1", 
		scopes = { 
				Constants.EMAIL_SCOPE 
				}, 
				clientIds = {
						Constants.WEB_CLIENT_ID, 
						Constants.API_EXPLORER_CLIENT_ID,
						Constants.ANDROID_CUSTOMER_CLIENT_ID,
						"299646937934-ja5hlk6lhjdl0c7lu252qkftm8738h62.apps.googleusercontent.com"
				}, 
				audiences = {Constants.ANDROID_AUDIENCE},
				description = "API for customers"
	)
public class CustomerApiAction {
	
	private static final Logger log = Logger.getLogger(RootApi.class.getName());
	private static final CustomerBusiness PRODUCTS_CUSTOMER_BUSINESS = new CustomerBusinessImpl();
	
	@ApiMethod(name="getProductsByCategoryCustomer", path="getProductsByCategoryCustomer", httpMethod=HttpMethod.POST)
	public List<Product> getProductsByCategoryCustomer(Category category){
    	return PRODUCTS_CUSTOMER_BUSINESS.getProductsByCategoryCustomer(category);
	}
	
	@ApiMethod(name="getProductsByKeywordCustomer", path="getProductsByKeywordCustomer", httpMethod=HttpMethod.POST)
	public List<Product> getProductsByKeywordCustomer(Keyword keyword){
		
    	return PRODUCTS_CUSTOMER_BUSINESS.getProductsByKeywordCustomer(keyword);
	}
	
	@ApiMethod(name="getCoverageAreas", path="getCoverageAreas", httpMethod=HttpMethod.GET)
	public List<CoverageArea> getCoverageAreas(){
		return PRODUCTS_CUSTOMER_BUSINESS.fetchCoveragesAreasCustomer();
	}
	
	@ApiMethod(name="getStoresByCoverageArea", path="getStoresByCoverageArea", httpMethod=HttpMethod.POST)
	public List<Store> getStoresByCoverageArea(CoverageArea coverageArea){
		return PRODUCTS_CUSTOMER_BUSINESS.fetchStoresByCoverageAreaCustomer(coverageArea);
	}
	
	@ApiMethod(name="getCategoriesByStore", path="getCategoriesByStore", httpMethod=HttpMethod.POST)
	public List<Category> getCategoriesByStore(Store store){
    	return PRODUCTS_CUSTOMER_BUSINESS.fetchCategoriesByStoreCustomer(store);
	}

	@ApiMethod(name="putOrder", path="putOrder", httpMethod=HttpMethod.POST)
	public Response putOrder(Order order){
		return PRODUCTS_CUSTOMER_BUSINESS.putOrder(order);		
	}
	
	@ApiMethod(name="updateNotificationToken", path="updateNotificationToken", httpMethod=HttpMethod.POST)
	public BooleanWrapper updateNotificationToken(Customer customer){	
		return PRODUCTS_CUSTOMER_BUSINESS.updateNotificationToken(customer);
	}
	
	@ApiMethod(name="sendNotification", path="sendNotification", httpMethod=HttpMethod.POST)
	public BooleanWrapper sendNotification(Notification notification) throws UnauthorizedException{
		if(notification==null || notification.getMessage()==null){
			throw new IllegalArgumentException("message is missing");
		}
		
    	return PRODUCTS_CUSTOMER_BUSINESS.sendNotification(notification);
	}
	
	@ApiMethod(name="getOrder", path="getOrder", httpMethod=HttpMethod.POST)
	public Order getOrder(Order order) throws UnauthorizedException{
		
		if(order==null || order.getCoverageAreaId()==null && order.getId()!=null){
			throw new IllegalArgumentException("message is missing");
		}
		
		Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, order.getCoverageAreaId());
		Key<Order> orderKey = Key.create(coverageAreaKey, Order.class, order.getId());
//		order = ofy().load().key(orderKey).now();
		
		List<Order> orders = ofy().load().type(Order.class).ancestor(coverageAreaKey).filterKey(orderKey).list();
		
		if(orders.size()==1){
			order = orders.get(0);
		}
		
		return order;
		
	}
	
}
