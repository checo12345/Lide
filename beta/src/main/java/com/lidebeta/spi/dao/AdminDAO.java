package com.lidebeta.spi.dao;

import java.util.List;

import com.google.api.server.spi.auth.common.User;
import com.lidebeta.spi.bean.Admin;
import com.lidebeta.spi.bean.Category;
import com.lidebeta.spi.bean.Keyword;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.bean.Response;
import com.lidebeta.spi.bean.Store;
import com.lidebeta.spi.bean.Sync;

public interface AdminDAO {
	void cleanIndex(String coverageAreaId);
	Product updateProduct(Product product);
	List<Product> fetchProductsByKeyword(Keyword keyword);
	Product fetchProductByCb(Product product);
	Store updateStore(Store store);
	List<Order> getOrders(Sync sync);
	Product fetchProductById(Product product);
}
