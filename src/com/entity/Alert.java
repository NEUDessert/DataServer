package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alert")
public class Alert {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alert_id")
	private int alert_id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "deviceid")
	private int deviceid;
	
	@Column(name = "sensorid")
	private int sensorid;
	
	@Column(name = "alerttype")
	private int alerttype;
	
	@Column(name = "alerttime")
	private String alerttime;
	
	@Column(name = "check")
	private int check;

	public int getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(int alert_id) {
		this.alert_id = alert_id;
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

	public int getSensorid(){
		return sensorid;
	}
	
	public void setSensorid(int sensorid){
		this.sensorid = sensorid;
	}
	
	public int getAlerttype() {
		return alerttype;
	}

	public void setAlerttype(int alerttype) {
		this.alerttype = alerttype;
	}

	public String getAlerttime() {
		return alerttime;
	}

	public void setAlerttime(String alerttime) {
		this.alerttime = alerttime;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}
}
