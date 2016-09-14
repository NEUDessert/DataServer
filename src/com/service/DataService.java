package com.service;

import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.LastData;

/**
 * Service层接口
 * 
 * @author HartLen
 * @version 2016-8-13 17:46:24
 */

public interface DataService {
	public boolean login(String username, String password);
	
	public void createData(Data data, String pic);
	
	public void updateData(Data data);
	
	public boolean queryLastData(String username, int deviceid);
	
	public void createLastData(LastData lastData);
	
	public void updateLastData(LastData lastData);
	
	public void createWarn(Alert alert);
	
	public void createDevice(Device device);
}
