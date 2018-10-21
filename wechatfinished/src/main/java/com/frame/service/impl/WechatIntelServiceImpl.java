package com.frame.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.frame.model.WXTempMessage;
import com.frame.service.WechatIntelService;

import HttpClient.HttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.frame.service.WXTempMsgService;
import com.frame.util.PropertiesUtil;

@Service("WechatIntelService")
public class WechatIntelServiceImpl implements WechatIntelService {

	private static final Logger logger = LoggerFactory
			.getLogger(WechatIntelServiceImpl.class);

	@Autowired
	private WXTempMsgService WXTempMsgService;
	@Autowired
	private WXuserInfoService WXuserInfoService;

	private HttpClient hc = new HttpClient();


	/**
	 * 获取微信全局access_token
	 * 
	 * @return
	 */
	@Override
	public String getAccessToken() {
		String access_token = "";
		// 读取配置文件中微信基础参数
		// 获取公众号相关信息
		String appid = "";
		String appsecret = "";
		String access_token_url = "";
		appid = PropertiesUtil.getProperty("appid");
		appsecret = PropertiesUtil.getProperty("secret");
		// "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
		access_token_url = PropertiesUtil
				.getProperty("wechatapi_access_token_url");
		access_token_url = access_token_url.replaceAll("APPID", appid)
				.replaceAll("SECRET", appsecret);
		logger.info(String.format("access_token_url结果：%s", access_token_url));
		
		// 获取access_token
		String result = hc.doGet(access_token_url);
		JSONObject jsonresult = JSONObject.parseObject(result);
		logger.info(String.format("接口获取result结果：%s", result));

		if (jsonresult.containsKey("access_token")) {

			access_token = jsonresult.getString("access_token");
		}

		logger.info(String.format("接口获取token结果：%s", access_token));
		return access_token;
	}

	/**
	 * 给用户发送模板消息
	 * 
	 * @return
	 */
	@Override
	public String SendTempMsgtoUser(String access_token, String openid) {
		logger.info("进入SendTempMsgtoUser");
		logger.info(String.format("openid：：%s",openid));
		String result = "";
		WXTempMessage tmsg;
		// 创建消息
		tmsg = this.WXTempMsgService.createWXTempMessage(openid);

		// 发送消息
		result = this.WXTempMsgService.sendTempMessage(tmsg, access_token);
		logger.info(String.format("获取sendresult结果：%s", result));
		// 微信接口返回的数据格式
		// {
		// "errcode":0,
		// "errmsg":"ok",
		// "msgid":200228332
		// }
		//
		//
		// 判断发送模板消息是否发送成功，返回ok签到成功
		JSONObject jsonresult = JSONObject.parseObject(result);
		String errmsg = jsonresult.getString("errmsg");
		logger.info(String.format("获取sendtempmsg结果：%s", errmsg));
		// =============================test
		// String errmsg="ok";

		// 发送成功
		if (errmsg.equals("ok")) {
			// 存储结果
			int saveresult = 0;
			// 存储签到信息
			saveresult = this.WXuserInfoService.writetoRecord(openid);
			if (1 == saveresult) {
				result = "ok";
				logger.info(String.format("存储成功获取result结果：%s", result));

			} else {

				if (2 == saveresult) {
					result = "hassignin";
					logger.info(String.format("已经存在此用户获取result结果：%s", result));
				} else {
					result = "error";
					logger.info(String.format("存储失败获取result结果：%s", result));
				}

			}

		} else {
			result = "error";
		}

		return result;
	}

	// /**
	// * 给用户发消息
	// * @return
	// */
	// @Override
	// public String SendMsgtoUser(String access_token,String openid,String
	// content,String msytype ) {
	// //给用户发消息
	//
	// System.out.println("sendmsg:"+openid+"  "+content+"  "+msytype);
	// // String openid="oWllf0SnnTswZtmFBxMwFnREZNiU";
	// // String content="hello tongxue";
	// WXResMessage msg= WXResponseMsgService.createWXResMessage(openid,
	// content, msytype);
	// String result=WXResponseMsgService.sendResMessage(msg, access_token);
	// System.out.println(result);
	// return result;

	// }

	// System.out.println("access_token_url"+access_token_url);
	// 调用接口获取token
	// HttpMethod method = new GetMethod(access_token_url);
	// String access_token = null;
	// String res = HttpClientUtils.httpRequest(method);

}
