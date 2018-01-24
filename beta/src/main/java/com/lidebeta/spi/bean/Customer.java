package com.lidebeta.spi.bean;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Customer {
	
	@Id
	private String id;
	
	private String token;

	private boolean active = true;
	
	private Long coverageAreaId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", token=" + token + "]";
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getCoverageAreaId() {
		return coverageAreaId;
	}

	public void setCoverageAreaId(Long coverageAreaId) {
		this.coverageAreaId = coverageAreaId;
	}
	
}
