package com.lidebeta.spi.bean;

import java.sql.Date;

public class OrderQuery {
	
	private Long coverageAreaId;
	private String deliveryManId;
	private Boolean open;
	private Date before;
	private Date after;
	private Integer statusCode;
	
	public Long getCoverageAreaId() {
		return coverageAreaId;
	}
	public void setCoverageAreaId(Long coverageAreaId) {
		this.coverageAreaId = coverageAreaId;
	}
	public String getDeliveryManId() {
		return deliveryManId;
	}
	public void setDeliveryManId(String deliveryManId) {
		this.deliveryManId = deliveryManId;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Date getBefore() {
		return before;
	}
	public void setBefore(Date before) {
		this.before = before;
	}
	public Date getAfter() {
		return after;
	}
	public void setAfter(Date after) {
		this.after = after;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
}
