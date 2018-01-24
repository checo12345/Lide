package com.lidebeta.spi.bean;

import java.util.Date;

public class Response {
	
	public enum Reason{
		NULL_PARAMETER_NEDED,
		MISSING_COVERAGE_AREA_ID,
		ZERO_PRODUCTS,
		ORDER_RETURN_NULL_FROM_DATASTORE,
		COVERAGE_AREA_ID_NOT_EXIST,
		SUCCESS,
		NULL_TOTAL,
		EMPTY_TOTAL,
		HAS_ORDER_OPEN,
		CLOSE_STORE
	}
	
	private boolean success;
	private Reason reason;
	private Long orderId;
	private Date orderDate;
	private Double total;
	
	public Response(boolean success, Reason reason){
		this.success = success;
		this.reason = reason;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}


}
