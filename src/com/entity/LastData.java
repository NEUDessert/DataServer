package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "last_data")
public class LastData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "data_id")
	private int data_id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "deviceid")
	private int deviceid;
	
	@Column(name = "temperature")
	private String temperature;
	
	@Column(name = "dampness")
	private String dampness;
	
	@Column(name = "pm25")
	private String pm25;
	
	@Column(name = "pictureurl")
	private String prictureurl;
	
	@Column(name = "recetime")
	private long recetime;
	
	public int getData_id(){
		return data_id;
	}
	
	public void setData_id(int data_id){
		this.data_id = data_id;
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

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDampness() {
		return dampness;
	}

	public void setDampness(String dampness) {
		this.dampness = dampness;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getPrictureurl() {
		return prictureurl;
	}

	public void setPrictureurl(String prictureurl) {
		this.prictureurl = prictureurl;
	}

	public long getRecetime() {
		return recetime;
	}

	public void setRecetime(long recetime) {
		this.recetime = recetime;
	}
}
