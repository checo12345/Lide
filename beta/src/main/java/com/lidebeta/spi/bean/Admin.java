package com.lidebeta.spi.bean;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Admin {

	@Id
	private String id;

	private List<Long> coverageAreaIdList;
	
	private List<Long> storeIdList;
	
	private List<CoverageArea> coverageAreaList;
	
	private String messengerToken;
	
	@Index
	private boolean active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Long> getCoverageAreaIdList() {
		return coverageAreaIdList;
	}

	public void setCoverageAreaIdList(List<Long> coverageAreaIdList) {
		this.coverageAreaIdList = coverageAreaIdList;
	}

	public List<Long> getStoreIdList() {
		return storeIdList;
	}

	public void setStoreIdList(List<Long> storeIdList) {
		this.storeIdList = storeIdList;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<CoverageArea> getCoverageAreaList() {
		return coverageAreaList;
	}

	public void setCoverageAreaList(List<CoverageArea> coverageAreaList) {
		this.coverageAreaList = coverageAreaList;
	}

	public String getMessengerToken() {
		return messengerToken;
	}

	public void setMessengerToken(String messengerToken) {
		this.messengerToken = messengerToken;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", coverageAreaIdList=" + coverageAreaIdList + ", storeIdList=" + storeIdList
				+ ", coverageAreaList=" + coverageAreaList + ", messengerToken=" + messengerToken + ", active=" + active
				+ "]";
	}

	
	
	
}
