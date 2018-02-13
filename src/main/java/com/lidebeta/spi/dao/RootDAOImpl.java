package com.lidebeta.spi.dao;

import static com.lidebeta.spi.service.OfyService.ofy;

import java.util.List;

import com.google.api.server.spi.auth.common.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Query;
import com.lidebeta.spi.bean.Admin;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Customer;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.DeliveryManQuery;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.OrderQuery;
import com.lidebeta.spi.bean.Root;
import com.lidebeta.spi.bean.Store;

public class RootDAOImpl implements RootDAO{

	@Override
	public Admin updateAdmin(Admin admin) {
		Key<Admin> adminKey = ofy().save().entity(admin).now();
		return adminKey!=null?admin:null;
	
	}


	@Override
	public List<Admin> fetchAdmin(Admin admin) {
		
		LoadType<Admin> orderLoader = ofy().load().type(Admin.class);
		Query<Admin> query = null;
	
		
		if(query!=null){
			return query.list();
		}else{
			return orderLoader.list();
		}
		
	}
	
	@Override
	public DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan) {
		Key<DeliveryMan> deliveryManKey = ofy().save().entity(deliveryMan).now();
		return deliveryManKey!=null?deliveryMan:null;
	}
	
	@Override
	public List<DeliveryMan> fetchDeliveryMan(DeliveryManQuery deliveryManQuery) {
		LoadType<DeliveryMan> orderLoader = ofy().load().type(DeliveryMan.class);
		Query<DeliveryMan> query = null;
		
		if(deliveryManQuery.getCoverageAreaId()!=null){
			query = orderLoader.filter("coverageAreaId =", deliveryManQuery.getCoverageAreaId());
		}
		
		if(deliveryManQuery.getActive()!=null){
			if(query!=null){
				query = query.filter("active", deliveryManQuery.getActive());
			}else{
				query = orderLoader.filter("active", deliveryManQuery.getActive());
			}
		}
		
		if(query!=null){
			return query.list();
		}else{
			return orderLoader.list();
		}
	}

	@Override
	public List<Order> fetchOrder(OrderQuery orderQuery) {
		LoadType<Order> orderLoader = ofy().load().type(Order.class);
		Query<Order> query = null;
		
		if(orderQuery.getCoverageAreaId()!=null){
			Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, orderQuery.getCoverageAreaId());
			query = orderLoader.ancestor(coverageAreaKey);
		}
		
		if(orderQuery.getDeliveryManId()!=null){
			if(query!=null){
				query = query.filter("deliveryManId =", orderQuery.getDeliveryManId());
			}else{
				query = orderLoader.filter("deliveryManId =", orderQuery.getDeliveryManId());
			}
		}
		
		if(orderQuery.getOpen()!=null){
			if(query!=null){
				query = query.filter("open", orderQuery.getOpen());
			}else{
				query = orderLoader.filter("open", orderQuery.getOpen());
			}
		}
		
		if(orderQuery.getBefore()!=null){
			if(query!=null){
				query = query.filter("date <", orderQuery.getBefore());
			}else{
				query = orderLoader.filter("date <", orderQuery.getBefore());
			}
		}
		
		if(orderQuery.getBefore()!=null){
			if(query!=null){
				query = query.filter("date >", orderQuery.getAfter());
			}else{
				query = orderLoader.filter("date >", orderQuery.getAfter());
			}
		}
		
		if(orderQuery.getStatusCode()!=null){
			if(query!=null){
				query = query.filter("statusCode =", orderQuery.getStatusCode());
			}else{
				query = orderLoader.filter("statusCode =", orderQuery.getStatusCode());
			}
		}
		
		if(query!=null){
			return query.list();
		}else{
			return orderLoader.list();
		}
		
	}

	@Override
	public Order updateOrder(Order order) {
		Key<Order> orderKey = ofy().save().entity(order).now();
		return orderKey!=null?order:null;
	}

	@Override
	public CoverageArea updateCoverageArea(CoverageArea coverageArea) {
		Key<CoverageArea> coverageAreaKey = ofy().save().entity(coverageArea).now();
		return coverageAreaKey!=null?coverageArea:null;
	}

	@Override
	public Store updateStore(Store store) {
		Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, store.getCoverageAreaId());
		CoverageArea coverageArea = ofy().load().key(coverageAreaKey).now();
		
		if(coverageArea==null){
			throw new IllegalArgumentException("Coverage Area with id="+store.getCoverageAreaId()+" does not exist");
		}
		
		store.setCoverageAreaKey(coverageAreaKey);
		
		Key<Store> storeKey = ofy().save().entity(store).now();
		return storeKey!=null?store:null;
	}
	
	@Override
	public Customer blockCustomer(Customer customer) {
		Key<Customer> customerKey = Key.create(Customer.class, customer.getId());
		customer = ofy().load().key(customerKey).now();
		
		if(customer==null){
			throw new IllegalArgumentException("Client does not exist");
		}
		
		customer.setActive(false);
		
		
		
		customerKey = ofy().save().entity(customer).now();
		return customerKey!=null?customer:null;
	}

	
}
