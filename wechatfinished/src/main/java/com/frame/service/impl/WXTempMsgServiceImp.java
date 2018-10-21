package com.frame.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.model.Exam;
import com.frame.model.TemplateData;
import com.frame.model.WXTempMessage;
import com.frame.service.WXTempMsgService;
import com.frame.util.PropertiesUtil;

import HttpClient.HttpClient;
import net.sf.json.JSONObject;

@Service("WXTempMsgService")
public class WXTempMsgServiceImp implements WXTempMsgService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(WXTempMsgServiceImp.class);
	
	
	@Autowired
	WXuserInfoService WXuserInfoService;

	/*
	 * 转成TempMessage对象
	 * */
	@Override
	public WXTempMessage createWXTempMessage(String openid) {

		
		// TODO Auto-generated method stub
		logger.info(String.format("进入createWXTempMessage"));
		logger.info(String.format("openid：：%s",openid));
		BufferedReader in = null;
		Exam  exam;
		WXTempMessage tmsg=new WXTempMessage();
		
		// 设置发送消息内容 
		String template_id = PropertiesUtil.getProperty("template_id");
		String url = PropertiesUtil.getProperty("template_url");
		
		tmsg.setTouser(openid);
		tmsg.setTemplate_id(template_id);
		tmsg.setUrl(url);
		Map<String, TemplateData> data = new HashMap<String, TemplateData>();
		//获取用户uid
		int uid = WXuserInfoService.getUserid(openid);
		if (uid != 0) {
			//根据uid从written表中获取exam时间、地点等信息
			exam = WXuserInfoService.getExamInfo(uid);
			if (exam != null) {
				String sfirst = "您好，" + exam.getWritten_number()
						+ "考生您已经签到成功！\n";
				String skeyword1 = "公务员考试";
				String skeyword2 = "考试时间：" + exam.getWritten_time();
				String skeyword3 = "考场信息：" + exam.getWritten_place()
						+ exam.getWritten_room();
				String sremark = "祝考试成功!";
				TemplateData first = new TemplateData(sfirst, "#173177");
				TemplateData keyword1 = new TemplateData(skeyword1, "#173177");
				TemplateData keyword2 = new TemplateData(skeyword2, "#173177");
				TemplateData keyword3 = new TemplateData(skeyword3, "#173177");
				TemplateData remark = new TemplateData(sremark, "#173177");
				data.put("first", first);
				data.put("keyword1", keyword1);
				data.put("keyword2", keyword2);
				data.put("keyword3", keyword3);
				data.put("remark", remark);
			}

		}

		tmsg.setData(data);

		return tmsg;

	}

	
	/*
	 * 将设置好的tmsg发送给微信服务器
	 * 
	*/
	@Override
	public String sendTempMessage(WXTempMessage tmsg, String access_token) {
		logger.info(String.format("进入sendTempMessage"));
		String url="";  //发送消息api
		String result = "";  //发送是否成功结果
		logger.info(String.format("access_token：：%s",access_token));
		
		//String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		url=PropertiesUtil.getProperty("wechatapi_template_send_url");
		url = url.replace("ACCESS_TOKEN", access_token);
		logger.info(String.format("wechatapi_template_send_url结果：%s", url));
		
		// tmsg 转化成Json
		String stmsg = JSONObject.fromObject(tmsg).toString();
		logger.info(String.format("json获取stmsg结果：%s", stmsg));
		
		// 发送消息
	//================test
		result = HttpClient.doPostStr(url, stmsg);
		logger.info(String.format("tempsend结果：%s", result));
         
		return result;
	}

}
