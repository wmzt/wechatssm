package com.frame.service.impl;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import HttpClient.HttpClient;

import com.alibaba.fastjson.JSONObject;
import com.frame.service.*;
import com.frame.util.PropertiesUtil;

@Service("wechatOAuthService")
public class wechatOAuthServiceImpl implements wechatOAuthService {

	// private Properties prop;

	private static Logger logger = Logger
			.getLogger(wechatOAuthServiceImpl.class);

	@Override
	public HashMap<String, String> getOpenidByCode(String code) {
		HttpClient hc;
		String appid;
		String appsecret;
		String url;
		HashMap<String, String> map = new HashMap<String, String>();

		LocalDate day = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd");

		// 获取公众号相关信息
		appid = PropertiesUtil.getProperty("appid");
		appsecret = PropertiesUtil.getProperty("secret");
		url = PropertiesUtil.getProperty("wechatapi_oauth2_access_token_url");
		url = url.replaceAll("APPID", appid).replaceAll("SECRET", appsecret)
				.replaceAll("CODE", code);
		logger.info(String.format("获取oauthurl结果：%s", url));
		
		// get请求，用code去换取 access_token和openid
		hc = new HttpClient();
		String result = hc.doGet(url);
		//String result = hc.doPost(url, null);
		JSONObject jsonresult = JSONObject.parseObject(result);
		// 判断是否获取成功
		if (jsonresult.containsKey("access_token")) {

			String openid = jsonresult.getString("openid");
			logger.info(String.format("接口获取openid结果：%s", openid));
			String access_token = jsonresult.getString("access_token");
			logger.info(String.format("oauth2接口获取access_token结果：%s", openid));

			// 去用openid 拿用户信息
			// getUserInfo(access_token,openid);
			map.put("openid", openid);
			map.put("access_token", access_token);

			return map;
		}
		return null;

	}
	// /*
	// * 获取用户信息
	// * */
	// @Override
	// public void getUserInfo(String openid,String access_token){
	// System.out.println("userinfo");
	// //3.1 访问此url去微信服务平台拿用户的所有信息
	// String
	// getuserurl="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
	// System.out.println("getuserurl:"+getuserurl);
	// HttpClient hc = new HttpClient();
	// //请求
	// String Jsonuserinfo =hc.doGet(getuserurl);
	// //3.2接收微信给我返回的所有用户信息
	// JSONObject result= JSON.parseObject(Jsonuserinfo);
	//
	//
	// //判断是否获取用户信息成功
	// if(result.containsKey("openid")) {
	// //获取用户信息成功
	// System.out.println("2====openid:"+result.getString("openid"));
	// System.out.println("====nickname:"+result.getString("nickname"));
	// System.out.println("sex:"+result.getString("sex"));
	// System.out.println("province:"+result.getString("province"));
	// System.out.println("city:"+result.getString("city"));
	// System.out.println("country:"+result.getString("country"));
	// System.out.println("headimgurl:"+result.getString("headimgurl"));
	//
	// WXuser wxuser =new WXuser() ;
	// toWXuserService service=new toWXuserService();
	// wxuser =service.toWXuser(result.getString("openid"),
	// result.getString("nickname"));
	// System.out.println(wxuser.getOpenid()+wxuser.getNickname());
	// }
	// }
}
