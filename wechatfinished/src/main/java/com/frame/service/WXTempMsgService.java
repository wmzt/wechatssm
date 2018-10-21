package com.frame.service;

import com.frame.model.WXTempMessage;

public interface WXTempMsgService {
	public WXTempMessage createWXTempMessage(String openid);
	public String sendTempMessage(WXTempMessage  tmsg,String access_token);
}