package com.lidebeta.spi.bean;

public class LatLng{
	
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private Float accuracy;
	
	public LatLng() {}

	public LatLng(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Float accuracy) {
		this.accuracy = accuracy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "LatLng [id=" + id + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", accuracy=" + accuracy + "]";
	}
	
}
