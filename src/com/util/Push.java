package com.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.report.ReceivedsResult;

public class Push {
	@SuppressWarnings("deprecation")
	private static JPushClient jpushClient = new JPushClient(Constants.masterSecret, Constants.appKey, 0);
	
	public static int sendMessage(String target, String message){
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.alias(target))
				.setMessage(Message.content(message))
				.build();
		
		try {
            PushResult result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            return 0;
        } catch (APIRequestException e) {
        	return 0;
        }
		
		return 1;
	}
}
