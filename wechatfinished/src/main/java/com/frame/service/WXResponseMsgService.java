package com.frame.service;

import com.frame.model.WXResMessage;

public interface WXResponseMsgService {
	public WXResMessage createWXResMessage(String openId,String content,String msgtype);
    public String sendResMessage(WXResMessage  msg,String access_token);
}