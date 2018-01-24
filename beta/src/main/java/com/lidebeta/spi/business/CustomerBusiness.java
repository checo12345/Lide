package com.lidebeta.spi.business;

import java.util.List;

import com.lidebeta.spi.bean.BooleanWrapper;
import com.lidebeta.spi.bean.Category;
import com.lidebeta.spi.bean.CompactProduct;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Customer;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.Keyword;
import com.lidebeta.spi.bean.Notification;
import com.lidebeta.spi.bean.NotificationToken;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.bean.Response;
import com.lidebeta.spi.bean.Store;
import com.lidebeta.spi.bean.Total;

public interface CustomerBusiness {
	List<Product> getProductsByCategoryCustomer(Category category);
	List<Product> getProductsByKeywordCustomer(Keyword keyword);
	List<CoverageArea> fetchCoveragesAreasCustomer();
	List<Store> fetchStoresByCoverageAreaCustomer(CoverageArea coverageArea);
	List<Category> fetchCategoriesByStoreCustomer(Store store);
	Total getTotal(Total total, Long coverageAreaId);
	Response putOrder(Order order);
	BooleanWrapper updateNotificationToken(Customer customer);
	BooleanWrapper sendNotification(Notification notification);
}
