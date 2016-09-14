package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dao.DataDao;
import com.entity.Alert;
import com.entity.Data;
import com.entity.Device;
import com.entity.IntelUserInfoEntity;
import com.entity.LastData;

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
	public boolean queryLastData(String username, int deviceid) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		condition.put("deviceid", deviceid);
		
		if(super.loadByKey(LastData.class, condition) != null){
			return true;
		}
		
		return false;
	}
	
	@Override
	public void createLastData(LastData lastData) {
		super.create(lastData);
	}
	
	@Override
	public void createWarn(Alert alert){
		super.create(alert);
	}

	@Override
	public void createDevice(Device device) {
		super.create(device);
	}
	
	public boolean login(String username, String password){
        try {
            Session session = super.getSessionFactory().getCurrentSession();
            String hql = "from IntelUserInfoEntity where username = '" + username + "' and password = '" + password + "'";
            Query query = session.createQuery(hql);
            List<IntelUserInfoEntity> intelUserInfoEntityList = query.list();
            if (intelUserInfoEntityList.size() == 0) {
                return false;
            } else {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

	
	public void updateLastData(LastData lastData){
		Session session = super.getSessionFactory().getCurrentSession();
		String hql = "update LastData set temperature='"+lastData.getTemperature()+"',dampness='"+lastData.getDampness()+"',pm25='"
				+lastData.getPm25()+"',pictureurl='"+lastData.getPrictureurl()+"',recetime="+lastData.getRecetime()+" where username='"
				+lastData.getUsername()+"' and deviceid="+lastData.getDeviceid();
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}
}
