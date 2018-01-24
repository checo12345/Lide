package com.lidebeta.spi.bean;

public class OrderResponse {
	
	public enum Reason{
		RETURN_NULL_FROM_DATASTORE
	}
	
	private boolean success;
	private Reason reason;
	
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
	
}
