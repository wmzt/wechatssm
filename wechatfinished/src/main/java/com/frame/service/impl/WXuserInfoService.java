package com.frame.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.frame.IDao.ExamIDao;
import com.frame.IDao.MemberIDao;
import com.frame.IDao.signInRecordIDao;
import com.frame.controller.wechatController;
import com.frame.dao.WXuserinfoDao;
import com.frame.model.Exam;
import com.frame.model.Member;
import com.frame.model.SignInRecord;
import com.frame.model.WXuser;

import HttpClient.HttpClient;

/*
 * 获取用户信息
 * */
@Service("WXuserInfoService")
public class WXuserInfoService {

	private static final Logger logger = LoggerFactory.getLogger(WXuserInfoService.class);
//	@Autowired
//	private WXuserinfoDao WXuserinfoDao;
	@Resource
	private signInRecordIDao signInRecordIDao;
	@Resource
	MemberIDao MemberIDao;
	@Resource
	ExamIDao ExamIDao;

	// private HttpClient hc = new HttpClient();

	// private WXuserinfoDao WXuserinfoDao =new WXuserinfoDao();
	// 写入签到信息记录表
	public int writetoRecord(String openid) {
		int result = 0;
		SignInRecord signInRecord = new SignInRecord();
		int uid = getUserid(openid);
		if (uid != 0) {
			signInRecord.setOpenid(openid);
			signInRecord.setUid(uid);
			;
			result = save(signInRecord);
		}

		return result;
	}

	// 根据userid获取用户考场信息
	public Exam getExamInfo(int uid) {
		// Exam exam= WXuserinfoDao.selectbyUid(uid);
		Exam exam = new Exam();
		exam = ExamIDao.selectbyUid(uid);
		return exam;
	}

	public int save(SignInRecord signInRecord) {
		int result = 0;
		if (signInRecord != null) {

			// result= WXuserinfoDao.insert(signInRecord);
			try {
				result = signInRecordIDao.insert(signInRecord);
				result = 1;
			} catch (Exception e) {
				System.out.println("===========" + e.getClass().getName());
				if (e.getClass().getName().equals("org.springframework.dao.DuplicateKeyException")) {

					result = 2;
				}
			}
		} else {
			logger.info("user为null");
		}
		return result;
	}

	public int getUserid(String openid) {
		Member member;
		int uid = 0;
		if (openid != null) {
			// uid= WXuserinfoDao.selectbyOpenid(openid);
			member = MemberIDao.selectbyOpenid(openid);
			uid = member.getUid();
		} else {
			logger.info("openid为null");
		}
		return uid;

	}

//已经存在 ，1存在，0不存在
	public int hasSignin(String openid) {
		SignInRecord signInRecord = new SignInRecord();
		int result=0;
		if (openid != null) {
			// uid= WXuserinfoDao.selectbyOpenid(openid);
			signInRecord =signInRecordIDao.selectbyOpenid(openid);
		
			if(signInRecord!=null) {
				
				System.out.println("已经存在");
				result= 1;
			}else {
				System.out.println("不存在");
				result=0;
			}
		} else {
			logger.info("openid为null");
		}
		return result;

	}
	// //根据userid获取用户考场信息
	// public Exam getExamInfo(int uid) {
	// Exam exam= WXuserinfoDao.selectbyUid(uid);
	// return exam;
	// }
	//
	// public int save(SignInRecord signInRecord) {
	// int result=0;
	// if(signInRecord!=null) {
	//
	// result= WXuserinfoDao.insert(signInRecord);
	// }else {
	// logger.info("user为null");
	// }
	//
	// return result;
	// }
	//
	// public int getUserid(String openid) {
	// int uid=0;
	// if(openid!=null) {
	// uid= WXuserinfoDao.selectbyOpenid(openid);
	//
	// }else {
	// logger.info("openid为null");
	// }
	// return uid;
	//
	// }
	// public int getUserInfo(String access_token,String openid){
	// System.out.println("==============userinfo");
	// int sresult=0;
	// //3.1 访问此url去微信服务平台拿用户的所有信息
	// String
	// getuserurl="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
	// System.out.println("getuserurl:"+getuserurl);
	//
	// //请求
	// String userinfo =hc.doGet(getuserurl);
	// logger.info(String.format("接口获取userinfo结果：%s",userinfo));
	// //3.2接收微信给我返回的所有用户信息
	// JSONObject result= JSON.parseObject(userinfo);
	//
	//
	// //判断是否获取用户信息成功
	// if(result.containsKey("openid")) {
	// //获取用户信息成功
	//
	// System.out.println("2====openid:"+result.getString("openid"));
	// System.out.println("====nickname:"+result.getString("nickname"));
	// System.out.println("sex:"+result.getString("sex"));
	// System.out.println("province:"+result.getString("province"));
	// System.out.println("city:"+result.getString("city"));
	// System.out.println("country:"+result.getString("country"));
	// System.out.println("headimgurl:"+result.getString("headimgurl"));
	//
	//
	// // toWXuserService service=new toWXuserService();
	// // wxuser =service.toWXuser(result.getString("openid"),
	// result.getString("nickname"));
	// // System.out.println(wxuser.getOpenid()+wxuser.getNickname());
	//
	//
	// //写入记录表
	//
	// // String userid =getUserid();
	// SignInRecord signInRecord =new SignInRecord();
	// signInRecord.setOpenid(result.getString("openid"));
	//
	// sresult=save(signInRecord);
	// if(sresult==1) {
	// logger.info(String.format("存储sresult结果：%s",sresult));
	// }else {
	// logger.info(String.format("存储sresult结果：%s",sresult));
	// }
	//
	//
	// }
	//
	// return sresult;
	// }
	//
	//

}
