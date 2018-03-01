package com.lidebeta.spi.bean;

import java.util.List;

public class OrderWrapper {

	private Order order;
	private List<LatLng> locations;
	private List<Product> products;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<LatLng> getLocations() {
		return locations;
	}
	public void setLocations(List<LatLng> locations) {
		this.locations = locations;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
