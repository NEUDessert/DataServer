package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DataDao;
import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.Sensor;
import com.service.DataService;
import com.util.Constants;
import com.util.StringToImage;

/**
 * Service层实现
 * 
 * @author HartLen
 * @version 2016-8-13 17:46:50
 */
@Service
public class DataServiceImpl implements DataService {
	
	@Resource
	private DataDao dataDao;
	
	@Override
	public void createData(Data data, String pic) {
		StringToImage.base64StringToImage(pic, data.getPrictureurl(), Constants.picPath);
		dataDao.createData(data);
	}

	@Override
	public void updateData(Data data) {
		dataDao.updateData(data);
	}
	
	@Override
	public void createWarn(Alert alert){
		dataDao.createWarn(alert);
	}

	@Override
	public void createDevice(Device device) {
		dataDao.createDevice(device);
	}

	@Override
	public void createSensor(Sensor sensor) {
		dataDao.createSensor(sensor);
	}
}
