package com.dao;

import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.Sensor;

/**
 * 
 * @author HartLen
 * @version 2016-8-13 17:44:40
 *
 */
public interface DataDao {
	//创建data
	public void createData(Data data);

	//更新data
	public void updateData(Data data);

	//删除data
	public void deleteData(Data data);
	
	//创建warn
	public void createWarn(Alert alert);
	
	//创建device
	public void createDevice(Device device);
	
	//创建sensor
	public void createSensor(Sensor sensor);
}
