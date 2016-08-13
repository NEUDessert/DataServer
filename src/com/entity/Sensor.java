package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensor_info")
public class Sensor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sensor_id")
	private int sensor_id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "deviceid")
	private int deviceid;
	
	@Column(name = "sensorid")
	private int sensorid;
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "location")
	private String location;
	
	public Sensor(){}
	
	public Sensor(String username, int deviceid, int sensorid, int type, String location) {
		this.username = username;
		this.deviceid = deviceid;
		this.sensorid = sensorid;
		this.type = type;
		this.location = location;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(int sensor_id) {
		this.sensor_id = sensor_id;
	}

	public int getSensorid() {
		return sensorid;
	}

	public void setSensorid(int sensorid) {
		this.sensorid = sensorid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
