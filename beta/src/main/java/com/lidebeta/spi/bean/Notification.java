package com.lidebeta.spi.bean;

import java.util.Date;

import org.json.JSONObject;

/**
 * Created by razp on 3/01/17.
 */

public class Notification {

    public static final int CUSTOMER        = 1;
    public static final int DELIVERY_MAN    = 2;
    public static final int ADMIN           = 3;

    private Date date;
    private String message;
    private int source;
    private String customerId;
    private Long orderId;
    private Long coverageAreaId;
    private String token;
    
    public Notification(){}
	public Notification(Date date, String message, int source, String customerId, Long orderId, Long coverageAreaId,
			String token) {
		super();
		this.date = date;
		this.message = message;
		this.source = source;
		this.customerId = customerId;
		this.orderId = orderId;
		this.coverageAreaId = coverageAreaId;
		this.token = token;
	}

	public String jsonForCustomer(){
		JSONObject json = new JSONObject();
		json.put("date", date.getTime());
		json.put("message", message);
		json.put("source", source);
		return json.toString();
	}

	public String jsonForDeliveryMan(){
		JSONObject json = new JSONObject();
		json.put("date", date.getTime());
		json.put("message", message);
		json.put("source", source);
		json.put("orderId", orderId);
		json.put("coverageAreaId", coverageAreaId);
		return json.toString();
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCoverageAreaId() {
		return coverageAreaId;
	}

	public void setCoverageAreaId(Long coverageAreaId) {
		this.coverageAreaId = coverageAreaId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Notification [date=" + date + ", message=" + message + ", source=" + source + ", customerId="
				+ customerId + ", orderId=" + orderId + ", coverageAreaId=" + coverageAreaId + ", token=" + token + "]";
	}
	
}

