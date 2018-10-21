package com.frame.controller;



import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frame.service.*;
import com.frame.service.impl.WXuserInfoService;


@Controller  
@RequestMapping(value = "/wechatcontroller")
public class wechatController {
	
    private static final Logger logger = LoggerFactory.getLogger(wechatController.class);
//	@Resource  
//    private AccessTokenServiceImpl accessTokenService;  
	@Autowired
	private wechatOAuthService wechatOAuthService;
	@Autowired
	WXuserInfoService WXuserInfoService;
	
	LocalDate day = LocalDate.now();
	 LocalTime tNow = LocalTime.now();
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd");


	/*
	 * 网页OAuth认证获取用户openid
	 * */

	@RequestMapping(value = "/getOpenid")
	public String getOpenid(HttpServletRequest request,RedirectAttributes attr){
		logger.info(String.format("==================进入getopenid:")+request.getContextPath()+day+tNow);
		String code = request.getParameter("code");
		logger.info(String.format("接口获取code结果：%s",code));
		HashMap<String, String> map=new HashMap<String, String>();
		
		//获取认证
		
		map =this.wechatOAuthService.getOpenidByCode(code);
		String openid=map.get("openid");
		String access_token=map.get("access_token");
		
		 logger.info(String.format("接口获取openid结果：%s",map.get("openid")));
		 logger.info(String.format("接口获取access_token结果：%s",map.get("access_token")));
		 if(!openid.isEmpty()) {
//			 //获得到openid后存储
//			 this.WXuserInfoService.writetoRecord(openid);
			 //判断是否已经存在，已经存在就不用再发送了
			 if( 0==WXuserInfoService.hasSignin(openid)) {
				 attr.addFlashAttribute("openid",openid);
				 return "redirect:/api/sendTempMsg";
			 }else {
				 
				 return "hassignin";
			 }
			
				
			
		 }
		return "error";
		
	}

	
}
 