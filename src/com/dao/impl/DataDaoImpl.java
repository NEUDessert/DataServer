package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.dao.DataDao;
import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.Sensor;

/**
 * Dao层实现
 * 
 * @author HartLen
 * @version 2016-8-13 17:45:54
 */

@Repository
public class DataDaoImpl extends BaseDAO implements DataDao{

	@Override
	public void createData(Data data) {
		super.create(data);
		
	}

	@Override
	public void updateData(Data data) {
		super.update(data);
	}

	@Override
	public void deleteData(Data data) {
		super.delete(data);
	}
	
	@Override
	public void createWarn(Alert alert){
		super.create(alert);
	}

	@Override
	public void createDevice(Device device) {
		super.create(device);
	}

	@Override
	public void createSensor(Sensor sensor) {
		super.create(sensor);
	}
}
