package com.lidebeta.spi.dao;

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

public interface DeliveryDAO {
	List<Order> getOrdersByCoverageArea(DeliveryMan deliveryMan);
	OrderResponse updateOrder(Order order, DeliveryMan deliveryMan);
	DeliveryMan updateMessengerToken(DeliveryMan deliveryMan, NotificationToken messengerToken);
	BooleanWrapper sendNotification(DeliveryMan deliveryMan, Notification notification);
}
