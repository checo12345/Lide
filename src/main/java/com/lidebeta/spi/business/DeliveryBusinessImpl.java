package com.lidebeta.spi.business;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.lidebeta.spi.bean.BooleanWrapper;
import com.lidebeta.spi.bean.Category;
import com.lidebeta.spi.bean.CompactProduct;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.Keyword;
import com.lidebeta.spi.bean.Notification;
import com.lidebeta.spi.bean.NotificationToken;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.OrderResponse;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.bean.Response;
import com.lidebeta.spi.bean.Store;
import com.lidebeta.spi.bean.Total;
import com.lidebeta.spi.dao.DeliveryDAOImpl;
import com.lidebeta.spi.dao.CustomerDAO;
import com.lidebeta.spi.dao.CustomerDAOImpl;

public class DeliveryBusinessImpl implements DeliveryBusiness{
	
	private static final DeliveryDAOImpl DELIVERY_DAO = new DeliveryDAOImpl();
	private static final int DELIVERY_MAN_MAX_NUMBER_OF_ORDERS = 3;	
	
	@Override
	public List<Order> getOrdersByCoverageArea(DeliveryMan deliveryMan) {
		return DELIVERY_DAO.getOrdersByCoverageArea(deliveryMan);
	}

	@Override
	public OrderResponse updateOrder(Order order, DeliveryMan deliveryMan) {
		return DELIVERY_DAO.updateOrder(order, deliveryMan);
	}
	
	@Override
	public DeliveryMan updateMessengerToken(DeliveryMan deliveryMan, NotificationToken messengerToken) {
		return DELIVERY_DAO.updateMessengerToken(deliveryMan, messengerToken);
	}

	@Override
	public BooleanWrapper sendNotification(DeliveryMan deliveryMan, Notification notification) {
		return DELIVERY_DAO.sendNotification(deliveryMan, notification);
	}


}
