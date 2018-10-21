package com.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.service.WechatIntelService;
import com.frame.util.SpringContextUtil;

public class sendTempMsgThread implements Runnable {
	// @Autowired
	// private WechatIntelService wechatIntelService ;
	private String result="";
	private String openid;
	private String access_token;

	public sendTempMsgThread(String access_token, String openid) {
		this.openid = openid;
		this.access_token = access_token;
	}

	public String getResult() {
		while(result!=null) {
			return result;
		}
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("sendTempMsgThread==========" + openid
				+ access_token);
		// 借助工具类获取 service，@Autowired不能 使用
		WechatIntelService wechatIntelService = SpringContextUtil
				.getBean("WechatIntelService");
		this.result = wechatIntelService
				.SendTempMsgtoUser(access_token, openid);
		
	}

}
