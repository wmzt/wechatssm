package com.frame.service.impl;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.frame.service.WechatIntelService;
import com.frame.util.SpringContextUtil;

public class sendTempMsgcallThread implements Callable<String> {
	private static final Logger logger = LoggerFactory.getLogger(sendTempMsgcallThread.class);
	private static String fresult = null;
	// @Autowired
//	private    WechatIntelService wechatIntelService ;

	private String openid;
	private String access_token;

	public sendTempMsgcallThread(String access_token, String openid) {

		this.openid = openid;
		this.access_token = access_token;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		System.out.println("sendTempcallMsgThread=============" + openid + access_token);
		// 借助工具类获取 service，@Autowired不能 使用
		WechatIntelService wechatIntelService = SpringContextUtil.getBean("WechatIntelService");
		result = wechatIntelService.SendTempMsgtoUser(access_token, openid);
		return result;
	}

}
