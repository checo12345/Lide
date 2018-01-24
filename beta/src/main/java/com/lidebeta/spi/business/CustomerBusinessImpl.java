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
import com.lidebeta.spi.dao.CustomerDAO;
import com.lidebeta.spi.dao.CustomerDAOImpl;

public class CustomerBusinessImpl implements CustomerBusiness{
	
	private static final CustomerDAO PRODUCTS_CUSTOMER_DAO = new CustomerDAOImpl();
	
	@Override
	public List<Product> getProductsByCategoryCustomer(Category category) {
		return PRODUCTS_CUSTOMER_DAO.getProductsByCategoryCustomer(category);
	}

	@Override
	public List<Product> getProductsByKeywordCustomer(Keyword keyword) {
		return PRODUCTS_CUSTOMER_DAO.getProductsByKeywordCustomer(keyword);
	}

	@Override
	public List<CoverageArea> fetchCoveragesAreasCustomer() {
		return PRODUCTS_CUSTOMER_DAO.fetchCoveragesAreasCustomer();
	}

	@Override
	public List<Store> fetchStoresByCoverageAreaCustomer(CoverageArea coverageArea) {
		return PRODUCTS_CUSTOMER_DAO.fetchStoresByCoverageAreaCustomer(coverageArea);
	}

	@Override
	public List<Category> fetchCategoriesByStoreCustomer(Store store) {
		return PRODUCTS_CUSTOMER_DAO.fetchCategoriesByStoreCustomer(store);
	}

	@Override
	public Total getTotal(Total total, Long coverageAreaId) {
		return PRODUCTS_CUSTOMER_DAO.getTotal(total, coverageAreaId);
	}

	@Override
	public Response putOrder(Order order) {
		return PRODUCTS_CUSTOMER_DAO.putOrder(order);
	}

	@Override
	public BooleanWrapper updateNotificationToken(Customer customer) {
		return PRODUCTS_CUSTOMER_DAO.updateNotificationToken(customer);
	}
	
	@Override
	public BooleanWrapper sendNotification(Notification notification) {
		return PRODUCTS_CUSTOMER_DAO.sendNotification(notification);
	}

}
