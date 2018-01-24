package com.lidebeta.spi.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Sync {
	
	private Long coverageAreaId;
	
	private Date fromDate;
	private Date toDate;
	
	private List<Order> orders;
	
	private Boolean open;
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "Sync [fromDate=" + fromDate + ", toDate=" + toDate + ", orders=" + orders + "]";
	}
	public Long getCoverageAreaId() {
		return coverageAreaId;
	}
	public void setCoverageAreaId(Long coverageAreaId) {
		this.coverageAreaId = coverageAreaId;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	
}
