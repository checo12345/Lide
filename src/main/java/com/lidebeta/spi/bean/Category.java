package com.lidebeta.spi.bean;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Category {
	
	@Id
	Long id;	
	
	String name;
	
	String description;
	
	String image;
	
	@Parent
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<Store> storeKey;
	
	@Ignore
	Long coverage_area_id;
	
	@Ignore
	Long store_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Key<Store> getStoreKey() {
		return storeKey;
	}
	public void setStoreKey(Key<Store> storeKey) {
		this.storeKey = storeKey;
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
