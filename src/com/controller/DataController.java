package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.DeviceInfo;
import com.entity.IntelUserInfoEntity;
import com.entity.LastData;
import com.service.DataService;
import com.util.Push;
/**
 * Data控制器
 * 
 * @author HartLen
 * @version 2016-8-13 17:44:53
 */

@Controller
public class DataController {
	@Resource
	private DataService dataService;
	
	@RequestMapping("login")
    public void String(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(dataService.login(username, password)){
            response.setStatus(200);
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
        }else {
        	response.setStatus(404);
        }
    }
	
	/**
	 * Set the id and location of one device
	 * 
	 * @param username
	 * @param devID
	 * @param location
	 */
	@RequestMapping(value = "/setDevice")
	public void setDevice(String username, int devID, String location){
		long registerTime = System.currentTimeMillis();
		System.out.println(location);
		Device device = new Device(username, devID, location, registerTime);
		
		dataService.createDevice(device);
		
		IntelUserInfoEntity userInfo = dataService.getDeviceByUsername(username);
		int num = userInfo.getDevnum() + 1;
		dataService.updateDeviceNum(username, num);
	}
	
	
	/**
	 * Get all information of one user's devices
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/getDeviceInfo")
	@ResponseBody
	public Object getDeviceInfo(String username){
		List<Device> devices = dataService.getDeviceInfo(username);
		
		List<DeviceInfo> infos = new ArrayList();
		if(devices != null){
			
			for(Device device : devices){
				DeviceInfo info = new DeviceInfo(device.getDeviceid(), device.getLocation());
				infos.add(info);
			}
		}else{
			DeviceInfo info = new DeviceInfo(1, "无");
			infos.add(info);
		}
		
		return JSON.toJSON(infos);
	}
	
	
	/**
	 * Upload normal data to database, includes temperature, 
	 * humidity, air index and pictures.
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/uploadData")
	public void uploadData(HttpServletRequest request){
		String username = request.getParameter("username");
		String deviceid = request.getParameter("devID");
		String temperature = request.getParameter("temp");
		String dampness = request.getParameter("hum");
		String pm25 = request.getParameter("air");
		String pic = request.getParameter("pic");
		
		Long recetime = System.currentTimeMillis();
		
		Data data = new Data();
		data.setUsername(username);
		data.setDeviceid(Integer.parseInt(deviceid));
		data.setTemperature(temperature);
		data.setDampness(dampness);
		data.setPm25(pm25);
		data.setPrictureurl(username+"_"+deviceid+"_"+Long.toString(recetime)+".png");
		data.setRecetime(recetime);
		
		dataService.createData(data, pic);
		
		LastData lastData = new LastData();
		lastData.setUsername(username);
		lastData.setDeviceid(Integer.parseInt(deviceid));
		lastData.setTemperature(temperature);
		lastData.setDampness(dampness);
		lastData.setPm25(pm25);
		lastData.setPrictureurl(username+"_"+deviceid+"_"+Long.toString(recetime)+".png");
		lastData.setRecetime(recetime);
		
		if(dataService.queryLastData(username, Integer.parseInt(deviceid))){
			dataService.updateLastData(lastData);
		}else{
			dataService.createLastData(lastData);
		}
	}
	
	/**
	 * Upload warning details in order to send information to
	 * users so that they can know their situations of their
	 * own home in time
	 * 
	 */
	@RequestMapping(value = "/warn")
	public void warn(String username, int devID, int sensorID, int type){
		Alert alert = new Alert();
		alert.setUsername(username);
		alert.setDeviceid(devID);
		alert.setSensorid(sensorID);
		alert.setAlerttype(type);
		alert.setAlerttime(Long.toString(System.currentTimeMillis()));
		alert.setCheck(0);
		
		dataService.createWarn(alert);
	}
	
	@RequestMapping(value = "/video")
	public Object video(String username,int devID, int req){
		String target = username+"_"+devID;
		String message = "{\"type\":\""+req+"\"}";
		
		int code = Push.sendMessage(target, message);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);

		return JSON.toJSON(result);
	}
}
