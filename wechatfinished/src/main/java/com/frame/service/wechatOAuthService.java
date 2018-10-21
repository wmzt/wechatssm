package com.frame.service;

import java.util.HashMap;

public interface wechatOAuthService {
    
    public HashMap<String, String> getOpenidByCode(String code);
  //  public  void getUserInfo(String openid,String access_token);
    
}
