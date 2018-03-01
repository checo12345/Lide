package com.lidebeta.spi.bean;

public class Keyword {
	
	String name;

	Long coverage_area_id;
	
	Long store_id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public Long getCoverage_area_id() {
		return coverage_area_id;
	}

	public void setCoverage_area_id(Long coverage_area_id) {
		this.coverage_area_id = coverage_area_id;
	}

	
}
