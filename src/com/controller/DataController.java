package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.Sensor;
import com.service.DataService;
import com.util.Communicate;
import com.util.Constants;
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
	
	/**
	 * Set the id and location of one device
	 * 
	 * @param username
	 * @param devID
	 * @param location
	 */
	@RequestMapping(value = "/setDevice")
	public void setDevice(String username, int devID, String location){
		Device device = new Device(username, devID, location);
		
		dataService.createDevice(device);
	}
	
	/**
	 * Set the id, type and location of sensors
	 * 
	 * @param username
	 * @param devID
	 * @param location
	 */
	@RequestMapping(value = "/setSensor")
	public void setSensor(String username, int devID, int sensorid, int type, String location){
		Sensor sensor = new Sensor(username, devID, sensorid, type, location);
		
		dataService.createSensor(sensor);
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
		String battery = request.getParameter("elec");
		String pic = request.getParameter("pic");
		
		//check whether battery power is enough
		if(Integer.parseInt(battery) <= Constants.batteryBoundary){
			String batteryWarn = "username=" + username + "&devID=" + deviceid + "&warn=1";
			new Communicate(Constants.warnUrl).send(batteryWarn);
		}
		
		JSONArray infos = null;
		//check whether temperature sensors work proper
		if(temperature.equals("failed")){
			infos = JSON.parseArray(temperature);
			for(Object info: infos){
				if(((JSONObject)info).getString("data").equals("failed")){
					String sensorid = ((JSONObject)info).getString("id");
					String tempWarn = "username=" + username + "&devID=" + deviceid + "&warn=2_"+ sensorid;
					new Communicate(Constants.warnUrl).send(tempWarn);
				}
			}
		}
		//check whether dampness sensors work proper
		if(dampness.equals("failed")){
			infos = JSON.parseArray(dampness);
			for(Object info: infos){
				if(((JSONObject)info).getString("data").equals("failed")){
					String sensorid = ((JSONObject)info).getString("id");
					String dampWarn = "username=" + username + "&devID=" + deviceid + "&warn=2_"+ sensorid;
					new Communicate(Constants.warnUrl).send(dampWarn);
				}
			}
		}
		//check whether pm25 sensors work proper
		if(pm25.equals("failed")){
			infos = JSON.parseArray(pm25);
			for(Object info: infos){
				if(((JSONObject)info).getString("data").equals("failed")){
					String sensorid = ((JSONObject)info).getString("id");
					String pmWarn = "username=" + username + "&devID=" + deviceid + "&warn=2_"+ sensorid;
					new Communicate(Constants.warnUrl).send(pmWarn);
				}
			}
		}
		
		Long recetime = System.currentTimeMillis();
		
		Data data = new Data();
		data.setUsername(username);
		data.setDeviceid(Integer.parseInt(deviceid));
		data.setTemperature(temperature);
		data.setDampness(dampness);
		data.setPm25(pm25);
		data.setBattery(Integer.parseInt(battery));
		data.setPrictureurl(username+"_"+deviceid+"_"+Long.toString(recetime)+".png");
		data.setRecetime(Long.toString(recetime));
		
		dataService.createData(data, pic);
	}
	
	/**
	 * Upload warning details in order to send information to
	 * users so that they can know their situations of their
	 * own home in time
	 * 
	 */
	@RequestMapping(value = "/warn")
	public void warn(String username, int devID, int sensorID, int type){
		String warn = "username=" + username +"&devID=" + devID +"&sensorID=" + sensorID + "&type=" + type;
		new Communicate(Constants.warnUrl).send(warn);
		
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
