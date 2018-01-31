package com.lidebeta.spi.bean;

public class CompactProduct{
	
	private Long storeId;
	private int quantity;
	private String id;
	private String name;
	private double price;
	private String imageName;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public String toString() {
		return "CompactProduct [storeId=" + storeId + ", quantity=" + quantity + ", id=" + id + ", name=" + name
				+ ", price=" + price + ", imageName=" + imageName + "]";
	}
	
}