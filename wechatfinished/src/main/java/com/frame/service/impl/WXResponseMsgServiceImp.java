package com.frame.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frame.model.Text;
import com.frame.model.WXResMessage;
import com.frame.service.WXResponseMsgService;

import HttpClient.HttpClient;
import net.sf.json.JSONObject;

public class WXResponseMsgServiceImp implements WXResponseMsgService {
/*
 * 转化成对象
 * */
	private static final Logger logger = LoggerFactory.getLogger(WXResponseMsgServiceImp.class);
	@Override
	public WXResMessage createWXResMessage(String openid,String content,String msgtype) {
	
		// TODO Auto-generated method stub
		//System.out.println("===========createWXResMessage");
		logger.info(String.format("进入createWXResMessage"));
		WXResMessage msg = new WXResMessage();
		Text text = new Text();
		text.setContent(content);
		msg.setMsgtype("text");
		msg.setTouser(openid);
		msg.setText(text);
//		System.out.println("wxres tocken:"+access_token);
//		String url ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESSTOKEN" ;//获取accesstoken在地图那篇文章里有介绍
//		url=url.replace("ACCESSTOKEN", access_token);
//	
//		System.out.println("sendurl:"+url);
//		String smsg=JSONObject.fromObject(msg).toString();
//	//	String  smsg="{\"touser\":\"oWllf0SnnTswZtmFBxMwFnREZNiU\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World\" }}";
//		System.out.println("===========smsg"+smsg);
//		
//		//发送消息
//		String result = HttpClient.doPostStr(url, smsg);
//	    System.out.println("createWXResMessageresult"+result);
		//return result;
	    return msg;
	
	}
	
	@Override
	 public String sendResMessage(WXResMessage  msg,String access_token) {
		System.out.println("wxres tocken:"+access_token);
		String url ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESSTOKEN" ;//获取accesstoken在地图那篇文章里有介绍
		url=url.replace("ACCESSTOKEN", access_token);
	
		System.out.println("sendurl:"+url);
		String smsg=JSONObject.fromObject(msg).toString();
	//	String  smsg="{\"touser\":\"oWllf0SnnTswZtmFBxMwFnREZNiU\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World\" }}";
	//	System.out.println("===========smsg"+smsg);
		 logger.info(String.format("接口获取smsg结果：%s",smsg));
		//发送消息
		String result = HttpClient.doPostStr(url, smsg);
	    System.out.println("createWXResMessageresult"+result);
	    return result;
	}

}
