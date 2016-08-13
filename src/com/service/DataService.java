package com.service;

import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.Sensor;

/**
 * Service层接口
 * 
 * @author HartLen
 * @version 2016-8-13 17:46:24
 */

public interface DataService {
	public void createData(Data data, String pic);
	
	public void updateData(Data data);
	
	public void createWarn(Alert alert);
	
	public void createDevice(Device device);
	
	public void createSensor(Sensor sensor);
}
