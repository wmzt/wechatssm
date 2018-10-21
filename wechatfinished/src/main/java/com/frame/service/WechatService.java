package com.frame.service;

import org.springframework.stereotype.Component;

/**
 * 微信基础服务类接口
 */
@Component
public interface WechatService {

    //处理并响应微信公众号消息
    public String processRequest(String request) throws Exception;

}
