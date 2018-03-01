package com.lidebeta.spi.bean;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Store {
	
	@Id
	private Long id;
	
	private String name;
	private LatLng location;
	private String imageName;
	private int priority;
	
	@Index
	private boolean open;
	
	@Ignore
	private Long coverageAreaId;
	
	@Ignore
	private String coverageAreaName;
	
	@Ignore
	private Sync sync;
	
    @Parent
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<CoverageArea> coverageAreaKey;
	
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Key<CoverageArea> getCoverageAreaKey() {
		return coverageAreaKey;
	}

	public void setCoverageAreaKey(Key<CoverageArea> coverageAreaKey) {
		this.coverageAreaKey = coverageAreaKey;
	}

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng location) {
		this.location = location;
	}

	public Long getCoverageAreaId() {
		return coverageAreaId;
	}

	public void setCoverageAreaId(Long coverageAreaId) {
		this.coverageAreaId = coverageAreaId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Sync getSync() {
		return sync;
	}

	public void setSync(Sync sync) {
		this.sync = sync;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", location=" + location + ", imageName=" + imageName
				+ ", priority=" + priority + ", open=" + open + ", coverageAreaId=" + coverageAreaId + ", sync=" + sync
				+ ", coverageAreaKey=" + coverageAreaKey + "]";
	}

	public String getCoverageAreaName() {
		return coverageAreaName;
	}

	public void setCoverageAreaName(String coverageAreaName) {
		this.coverageAreaName = coverageAreaName;
	}
	
	
	
}
