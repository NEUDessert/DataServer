package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dev_info")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dev_id")
	private int dev_id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "deviceid")
	private int deviceid;
	
	@Column(name = "location")
	private String location;

	@Column(name = "registerTime")
	private long registerTime;
	
	public Device(){
	}
	
	public Device(String username, int deviceid, String location, long registerTime) {
		this.username = username;
		this.deviceid = deviceid;
		this.location = location;
		this.registerTime = registerTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
	

}
