package com.lidebeta.spi.bean;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class CoverageArea {
	
	@Id
	private Long id;
	
	@Index
	private String name;
	
	private String description;
	
	private LatLng center;
	private Float altitude;
	private List<LatLng> coverageArea;
	
	private List<Store> stroList;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LatLng getCenter() {
		return center;
	}
	public void setCenter(LatLng center) {
		this.center = center;
	}
	public Float getAltitude() {
		return altitude;
	}
	public void setAltitude(Float altitude) {
		this.altitude = altitude;
	}
	public List<LatLng> getCoverageArea() {
		return coverageArea;
	}
	public void setCoverageArea(List<LatLng> coverageArea) {
		this.coverageArea = coverageArea;
	}
	@Override
	public String toString() {
		return "CoverageArea [id=" + id + ", name=" + name + ", description=" + description + ", center=" + center
				+ ", altitude=" + altitude + ", coverageArea=" + coverageArea + "]";
	}
	public List<Store> getStroList() {
		return stroList;
	}
	public void setStroList(List<Store> stroList) {
		this.stroList = stroList;
	}
	
	
	
}
