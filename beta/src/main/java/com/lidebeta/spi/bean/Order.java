package com.lidebeta.spi.bean;

import java.util.Date;
import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Order {

	public static final String ORDER_STATUS_PENDING 		=  "Orden Recibida";
	public static final String ORDER_STATUS_ASSIGN 			=  "Orden En Camino";
	public static final String ORDER_STATUS_CANCEL 			=  "Orden Cancelada";
	public static final String ORDER_STATUS_SUCCESS 		=  "Orden Entregada";
	
	public static final int ORDER_STATUS_PENDING_CODE 		=  1;
	public static final int ORDER_STATUS_ASSIGN_CODE 		=  2;
	public static final int ORDER_STATUS_CANCEL_CODE 		=  3;
	public static final int ORDER_STATUS_SUCCESS_CODE 		=  4;
	
	@Parent
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<CoverageArea> coverageAreaKey;
	
	@Ignore
	private Long coverageAreaId;
	
	@Id
	private Long Id;
	
	public boolean equals(Order order){
		return Id.equals(order.getId());
	}
	
	@Index
	private String userID;
	
	@Index
	private Date date;
		
	private LatLng origin;
	
	private LatLng destination;
	
	private List<CompactProduct> products;
	
	private double total;
	
	private String status;
	
	@Index
	private Integer statusCode;
	
	@Index
	private boolean open;
	
	@Index
	private boolean success;
	
	private String observation;
	
	@Index
	private String deliveryManId; 

	public Key<CoverageArea> getCoverageAreaKey() {
		return coverageAreaKey;
	}

	public void setCoverageAreaKey(Key<CoverageArea> coverageAreaKey) {
		this.coverageAreaKey = coverageAreaKey;
	}

	public Long getCoverageAreaId() {
		return coverageAreaId;
	}

	public void setCoverageAreaId(Long coverageAreaId) {
		this.coverageAreaId = coverageAreaId;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LatLng getOrigin() {
		return origin;
	}

	public void setOrigin(LatLng origin) {
		this.origin = origin;
	}

	public LatLng getDestination() {
		return destination;
	}

	public void setDestination(LatLng destination) {
		this.destination = destination;
	}

	public List<CompactProduct> getProducts() {
		return products;
	}

	public void setProducts(List<CompactProduct> products) {
		this.products = products;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String onservation) {
		this.observation = onservation;
	}

	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(String deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "Order [coverageAreaKey=" + coverageAreaKey + ", coverageAreaId=" + coverageAreaId + ", Id=" + Id
				+ ", userID=" + userID + ", date=" + date + ", origin=" + origin + ", destination=" + destination
				+ ", products=" + products + ", total=" + total + ", status=" + status + ", statusCode=" + statusCode
				+ ", open=" + open + ", success=" + success + ", observation=" + observation + ", deliveryManId="
				+ deliveryManId + "]";
	}

	
	
}
