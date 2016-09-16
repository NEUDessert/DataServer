package com.entity;

public class DeviceInfo {
	private int devID;
	
	private String location;

	public DeviceInfo(){}
	
	public DeviceInfo(int devID, String location){
		this.devID = devID;
		this.location = location;
	}
	
	public int getDevID() {
		return devID;
	}

	public void setDevID(int devID) {
		this.devID = devID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
