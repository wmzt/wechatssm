package com.frame.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

@Component
public interface WechatIntelService {

    //获取微信公众号全局token
    public String getAccessToken();

//    //发送模板消息
//    public String SendTempMsgtoUser(String  access_token,String openid);
    //发送模板消息
    public String  SendTempMsgtoUser(String  access_token,String openid);
    //给用户发送消息
 //   public String SendMsgtoUser(String  access_token,String openid,String content,String msgtype);
  
   
}
