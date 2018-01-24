package com.lidebeta.spi.bean;

import java.util.List;

public class Total {
	
	private Double total;
	private List<CompactProduct> products;
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<CompactProduct> getProducts() {
		return products;
	}

	public void setProducts(List<CompactProduct> products) {
		this.products = products;
	}
	
}
