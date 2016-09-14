package com.dao;

import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.LastData;

/**
 * 
 * @author HartLen
 * @version 2016-8-13 17:44:40
 *
 */
public interface DataDao {
	//验证登录
	public boolean login(String username, String password);
	
	//创建data
	public void createData(Data data);

	//更新data
	public void updateData(Data data);

	//删除data
	public void deleteData(Data data);
	
	//查询last_data表中是否已经存在设备的记录
	public boolean queryLastData(String username, int deviceid);
	
	//创建最新data
	public void createLastData(LastData lastData);
	
	//更新最新data
	public void updateLastData(LastData lastData);
	
	//创建warn
	public void createWarn(Alert alert);
	
	//创建device
	public void createDevice(Device device);

}
