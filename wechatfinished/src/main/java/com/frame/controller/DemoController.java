package com.frame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frame.IDao.MemberIDao;
import com.frame.IDao.ExamIDao;
import com.frame.IDao.signInRecordIDao;
import com.frame.dao.WXuserinfoDao;
import com.frame.model.Exam;
import com.frame.model.Member;
import com.frame.model.SignInRecord;
import com.frame.model.Text;

//import com.alibaba.fastjson.JSONObject;

import com.frame.model.WXResMessage;
import com.frame.model.WXTempMessage;
import com.frame.model.WXuser;
import com.frame.service.DemoService;
import com.frame.service.WechatIntelService;
import com.frame.service.wechatOAuthService;
import com.frame.service.impl.WXuserInfoService;
import com.frame.service.impl.sendTempMsgThread;
import com.frame.service.impl.sendTempMsgcallThread;

import HttpClient.HttpClient;
import net.sf.json.JSONObject;

import com.frame.service.WXTempMsgService;
@Controller
public class DemoController {

	@Resource
	private signInRecordIDao reIDao;
	@Resource
	MemberIDao MemberIDao;
	@Resource
	ExamIDao ExamIDao;
	@Autowired
	private DemoService demoService;
	@Autowired
	wechatOAuthService wechatOAuthService;
	@Autowired
	WechatIntelService WechatIntelService;
	
	@Autowired
	WXTempMsgService WXTempMsgService;
	@Autowired
	WXuserInfoService WXuserInfoService;

	@Autowired
	WXuserinfoDao WXuserinfoDao;
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("执行hello控制器方法");
		// 调用业务层执行查询操作
		//demoService.test();
	String openid="oWllf0SnnTswZtmFBxMwFnREZNiU";
//		WXuser user=new WXuser();
//		//user.setId(3);
//		user.setOpenid("1995622");
//		user.setNickname("测试2");
		//dao.test();
	//	infos.save(user);
//		List<Map> resultlist=new ArrayList<Map>();
//		resultlist=	WXuserinfoDao.selectbyOpenid(openid);
//		String name=(String) resultlist.get(0).get("name");
//		System.out.println(resultlist.get(0).get("name"));
//		SignInRecord signInRecord=new SignInRecord();
//		signInRecord.setOpenid("DDD");
//		signInRecord.setUserid("LKJIJI");
		//WXuserinfoDao.insert(signInRecord);
		
		
	//	WXuserInfoService.writetoRecord("oWllf0SnnTswZtmFBxMwFnREZNiU");
	//	return new ModelAndView("hello");
	
	//wechatOAuthService.getOpenidByCode("aaa");
	//WechatIntelService.getAccessToken();
	WXTempMessage tmsg=new WXTempMessage ();
	WXTempMsgService.sendTempMessage(tmsg, "sgg");
	   return  "forward:result";
	}
	@RequestMapping("/test")
	public String test( RedirectAttributes attr) {
		System.out.println("执行test方法");
//		HttpClient	hc = new HttpClient();
//		HashMap map=new HashMap();
//		map.put("key", "value");
//		String re=hc.doPost("www.baidu.com", map);
	//	String re=createWXResMessage()
	//	System.out.println("============="+re);
	//	System.out.println("wxres tocken:"+"");
//		String access_token="aaa";
//		String url ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESSTOKEN" ;//获取accesstoken在地图那篇文章里有介绍
//		url=url.replace("ACCESSTOKEN", access_token);
//		System.out.println("sendurl1:"+url);
//		url=url.replaceFirst("ACCESSTOKEN", access_token);
//		
//		System.out.println("sendurl2:"+url);
	//	String  smsg="{\"touser\":\"oWllf0SnnTswZtmFBxMwFnREZNiU\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World\" }}";
		
//		WXResMessage msg = new WXResMessage();
//		Text text = new Text();
//		text.setContent("httllllll");
//		msg.setMsgtype("text");
//		msg.setTouser("djiji");
//		msg.setText(text);
//		String smsg=JSONObject.fromObject(msg).toString();
//		System.out.println(smsg);
		//attr.addAttribute("openid", "sjdkjgoijoidj");
		//attr.addFlashAttribute("openid", "sjdkjgoijoidj");
	return "redirect:https://www.baidu.com";
	//	return "hello";
	}
	

public static String createWXResMessage(String openId,String content,String access_token){
	WXResMessage msg = new WXResMessage();
//		Text text = new Text();
//		text.setContent(content);
		msg.setMsgtype("text");
		msg.setTouser(openId);
		//msg.setText(content);
		String url ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token+" " ;//获取accesstoken在地图那篇文章里有介绍
		
		String result = HttpClient.doPostStr(url, JSONObject.fromObject(msg).toString());
	    System.out.println(result);
		return result;
}

@RequestMapping("/testtemp")
public String testtemp() {
	
	String openid="oWllf0SnnTswZtmFBxMwFnREZNiU";
	
	WXTempMessage	tmsg=WXTempMsgService.createWXTempMessage(openid);
	String  result=WXTempMsgService.sendTempMessage(tmsg, null);
//String access_token=	WechatIntelService.getAccessToken();
//String  result=WechatIntelService.SendTempMsgtoUser(access_token, openid);
	return result;
}

@RequestMapping("/testExam")
public void testExam() {
	String openid="aaa";
	Exam exam=new Exam();
	int uid=123;
	
//	Member m=new Member();
//	m=MemberIDao.selectbyOpenid(openid);
//	System.out.println(m.getUid());
	
	SignInRecord  signInRecord=new SignInRecord();
//	signInRecord.setUid(uid);
//	signInRecord.setOpenid("testtest");
	signInRecord=reIDao.selectbyOpenid("jij");
	if(signInRecord!=null) {
		System.out.println("已经存在");
	}else {
		System.out.println("bu存在");
	}
	
//	WXuserinfoDao.insert(signInRecord);
	//reIDao.insert(signInRecord);
	
	
//	exam=ExamIDao.selectbyUid(uid);
//	System.out.println("uid:"+exam.getResume_uid()+"location:"+exam.getWritten_place()+"  "+exam.getWritten_room()+"time:"+exam.getWritten_time());

//	int re=WXuserInfoService.save(signInRecord);
//	System.out.println(re);
	//	int uid=WXuserinfoDao.selectbyOpenid(openid);
//	System.out.println(uid);
	
//String access_token=	WechatIntelService.getAccessToken();
//String  result=WechatIntelService.SendTempMsgtoUser(access_token, openid);
	//return result;
}


@RequestMapping("/testthread")
public String testthread() {
	
	String	access_token="ssss";
	String	openid="oWllf0SnnTswZtmFBxMwFnREZNiU";
String result="ok";

sendTempMsgThread sendTempMsgthread=new sendTempMsgThread(access_token,openid);
Thread thread = new Thread(sendTempMsgthread);  
thread.start();
result=sendTempMsgthread.getResult();
System.out.println(result);
if(result!=null) {
	 if(result.equals("ok")) {
    	  return "hello";
      }else {
    	  //已经签到
    	  if(result.equals("hassignin")) {
    		  return "hassignin";
    	  }else {
    		  return "error";
    	  }
    	 
      }
//	return result;

}
else {
	return result;
}
//	 ExecutorService pool = Executors.newCachedThreadPool();
//	    sendTempMsgcallThread c = new sendTempMsgcallThread(access_token,openid);
//     // 执行任务并获取Future对象
//     Future<String> f = pool.submit(c);
//     while(f!=null) {
//
//			try {
//				result=f.get();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		   // logger.info(String.format("接口获取sendresult结果：%s",result));\
//			System.out.println(result);
//		    if(result!=null) {
//				 if(result.equals("ok")) {
//			    	  return "hello";
//			      }else {
//			    	  //已经签到
//			    	  if(result.equals("hassignin")) {
//			    		  return "hassignin";
//			    	  }else {
//			    		  return "error";
//			    	  }
//			    	 
//			      }
//			//	return result;
//		
//		    }
//		    else {
//		    	return result;
//		    }
//     }
		
   
}


	
}