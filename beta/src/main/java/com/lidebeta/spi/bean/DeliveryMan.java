package com.lidebeta.spi.bean;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class DeliveryMan {
	
	
	@Id
	private String id;
	
	private String name;
	
	private double rating;
	
	@Index
	private boolean active;
	
	private String messengerToken;
	
	@Index
	private int avaibleNumberOfOrders;
	
	@Index
	private Long coverageAreaId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
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

	public String getMessengerToken() {
		return messengerToken;
	}

	public void setMessengerToken(String messengerToken) {
		this.messengerToken = messengerToken;
	}

	@Override
	public String toString() {
		return "DeliveryMan [id=" + id + ", name=" + name + ", rating=" + rating + ", active=" + active
				+ ", messengerToken=" + messengerToken + ", coverageAreaId=" + coverageAreaId + "]";
	}

	public int getAvaibleNumberOfOrders() {
		return avaibleNumberOfOrders;
	}

	public void setAvaibleNumberOfOrders(int avaibleNumberOfOrders) {
		this.avaibleNumberOfOrders = avaibleNumberOfOrders;
	}

	
}
