package com.lidebeta.spi.bean;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable{

	private Long datastoreId;//
	
	private Long coverageAreaId;
	
	private Long storeId;
	
	private String id;

	private String name;
	
	private String description;
	
	private String keywords;
	
	private String categories;
	
	private boolean avaible;
	
	private double price;
	
	private Date lasUpdate;
	
	private String image;
	
	private int quantity;
	
	private int pedido;

	private String codigoBarras;
	
	public Long getDatastoreId() {
		return datastoreId;
	}

	public void setDatastoreId(Long datastoreId) {
		this.datastoreId = datastoreId;
	}

	public Long getCoverageAreaId() {
		return coverageAreaId;
	}

	public void setCoverageAreaId(Long coverageAreaId) {
		this.coverageAreaId = coverageAreaId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public boolean isAvaible() {
		return avaible;
	}

	public void setAvaible(boolean avaible) {
		this.avaible = avaible;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getLasUpdate() {
		return lasUpdate;
	}

	public void setLasUpdate(Date lasUpdate) {
		this.lasUpdate = lasUpdate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	
	@Override
	public String toString() {
		return "Product [datastoreId=" + datastoreId + ", coverageAreaId=" + coverageAreaId + ", storeId=" + storeId
				+ ", id=" + id + ", name=" + name + ", description=" + description + ", keywords=" + keywords
				+ ", categories=" + categories + ", avaible=" + avaible + ", price=" + price + ", lasUpdate="
				+ lasUpdate + ", image=" + image + ", quantity=" + quantity + ", codigoBarras=" + codigoBarras + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}
	
	
	
}


