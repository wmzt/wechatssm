package com.frame.controller;

//        import cn.sun.my_wechat.common.WechatSetting;
//        import cn.sun.my_wechat.service.WechatService;
//        import cn.sun.my_wechat.util.SignaUtils;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.frame.service.WechatService;
import com.frame.util.SignaUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

/**
 * 接入微信
 */
@Controller
//@RequestMapping(value = "/joinUp")
public class JoinUpController {
	
@Autowired
private WechatService wechatServiceImpl;
//    @Autowired
//  //  private WechatSetting wechatSetting;


/**
 * 聊天消息接收
 *
 * @return
 */
//  @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/xml")
//@RequestMapping(value = "", method = RequestMethod.POST)
public String getMessage(@RequestBody String request, HttpServletResponse response) throws Exception {
    //调用核心业务类接受并处理消息
	System.out.println("=======chuli");
	System.out.println(request);
   // String respMessage = wechatServiceImpl.processRequest(request);
  //  System.out.println(respMessage);
    
    return request+"===re";
    
//    respMessage=  "<xml>\n" + 
//    "  <ToUserName><![CDATA[ziteng]]></ToUserName>\n" + 
//    "  <FromUserName><![CDATA[gukejijijigjoijisodjoigjoijoigjdu]]></FromUserName>\n" + 
//    "  <CreateTime><![CDATA[-1333741947]]></CreateTime>\n" +
//    "  <MsgType><![CDATA[text]]></MsgType>\n" + 
//    "  <Content><![CDATA[kjdiji]]></Content>\n" + 
//    "</xml>";
//   return respMessage;
  //  response.getWriter().write(respMessage);
  //  response.getOutputStream().println(respMessage);
   // return  "sss";
}
    /**
     * 接入微信
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String paramsCheck(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("============joinUpget");
        //微信签名
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");
        //微信接口配置token
      //  String token = wechatSetting.getToken();
        String token = "wechatmsg";
        //进行signature签名验证
        boolean checkResult = SignaUtils.signatureCheck(signature, token, nonce, timestamp);

        if (checkResult) {
            System.out.println("微信接入成功");

//            PrintWriter print;
//
//            try {
//				print = response.getWriter();
//				print.write(echostr);
//	            print.flush();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            try {
//			//	response.getOutputStream().println(echostr);
//            	return echostr;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            return echostr;
        } else {
            System.out.println("微信接入失败");
        }
        return null;
    }

   
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    @ResponseBody
//    public String getTest(@RequestBody  String request) {
//    	//JSONObject
//    	System.out.println("test");
//    	System.out.println(request);
//    	return "<xml>  <ToUserName>< ![CDATA[toUser] ]></ToUserName>  <FromUserName>< ![CDATA[fromUser] ]></FromUserName>  <CreateTime>1348831860</CreateTime>  <MsgType>< ![CDATA[text] ]></MsgType>  <Content>< ![CDATA[this is a test] ]></Content>  <MsgId>1234567890123456</MsgId>  </xml>";
//    }
}
