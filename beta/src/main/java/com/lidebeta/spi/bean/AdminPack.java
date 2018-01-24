package com.lidebeta.spi.bean;

import java.util.List;

public class AdminPack {
	
	private List<CoverageArea> coverageArea;
	private List<Store> stores;
	
	public List<CoverageArea> getCoverageArea() {
		return coverageArea;
	}
	public void setCoverageArea(List<CoverageArea> coverageArea) {
		this.coverageArea = coverageArea;
	}
	public List<Store> getStores() {
		return stores;
	}
	public void setStores(List<Store> stores) {
		this.stores = stores;
	}
	
}
