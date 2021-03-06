package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DataDao;
import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.IntelUserInfoEntity;
import com.entity.LastData;
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
	public boolean login(String username, String password) {
		return dataDao.login(username,password);
	}
	
	@Override
	public void createData(Data data, String pic) {
		//StringToImage.base64StringToImage(pic, data.getPrictureurl(), Constants.picPath);
		dataDao.createData(data);
	}
	
	@Override
	public void updateData(Data data) {
		dataDao.updateData(data);
	}
	
	@Override
	public boolean queryLastData(String username, int deviceid) {
		return dataDao.queryLastData(username, deviceid);
	}
	
	@Override
	public void createLastData(LastData lastData) {
		dataDao.createLastData(lastData);
	}

	@Override
	public void updateLastData(LastData lastData) {
		dataDao.updateLastData(lastData);
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
	public IntelUserInfoEntity getDeviceByUsername(String username){
		return dataDao.getDeviceByUsername(username);
	}
	
	@Override
	public List<Device> getDeviceInfo(String username) {
		return dataDao.getDeviceInfo(username);
	}
	
	@Override
	public void updateDeviceNum(String username, int num){
		dataDao.updateDeviceNum(username, num);
	}
}
