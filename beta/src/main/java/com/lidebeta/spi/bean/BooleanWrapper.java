package com.lidebeta.spi.bean;

public class BooleanWrapper {
	private final boolean success;
	private final String reason;
	public BooleanWrapper(boolean success, String reason) {
		super();
		this.success = success;
		this.reason = reason;
	}
	
	public BooleanWrapper(boolean success) {
		super();
		this.success = success;
		this.reason = null;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getReason() {
		return reason;
	}
}
