package com.lidebeta.spi.dao;

import java.util.List;

import com.google.api.server.spi.auth.common.User;
import com.lidebeta.spi.bean.Admin;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Customer;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.DeliveryManQuery;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.OrderQuery;
import com.lidebeta.spi.bean.Root;
import com.lidebeta.spi.bean.Store;

public interface RootDAO {
	Admin updateAdmin(Admin admin);
	List<Admin> fetchAdmin(Admin admin);
	DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan);
	List<DeliveryMan> fetchDeliveryMan(DeliveryManQuery deliveryManQuery);
	List<Order> fetchOrder(OrderQuery orderQuery);
	Order updateOrder(Order order);
	CoverageArea updateCoverageArea(CoverageArea coverageArea);
	Store updateStore(Store store);
	Customer blockCustomer(Customer customer);
	
}
