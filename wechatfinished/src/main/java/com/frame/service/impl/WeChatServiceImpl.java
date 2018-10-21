package com.frame.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.frame.model.TextMessage;
import com.frame.service.WechatService;
import com.frame.util.MessageUtils;

import java.util.Date;
import java.util.Map;

/**
 * 微信接口调用
 */
@Service
public class WeChatServiceImpl implements WechatService {

    private static final Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);

    @Override
    public String processRequest(String request) throws Exception {

        String respContent = null;

        //解析微信公众号的xml请求信息
        Map<String, String> map = MessageUtils.parseXmlString(request);
        //接收方openId
        String toUserName = map.get("ToUserName");
        //开发者
        String fromUserName = map.get("FromUserName");
        //消息接收时间
        String createTime = map.get("CreateTime");
        //接收的消息类型
        String msgType = map.get("MsgType");

        //根据消息类型进行相应的消息处理响应
        if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_EVENT)) {
            //事件推送类型
            String eventType = map.get("Event");
            respContent = this.eventRespContent(eventType);

        } else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_TEXT)) {
            //文本消息
            respContent = "您发送的是文本消息";
        } else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_IMAGE)) {
            //图片消息
            respContent = "您发送的是图片消息";
        } else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_VOICE)) {
            //语音消息
            respContent = "您发送的是语音消息";
        } else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_VIDEO)) {
            //视频消息
            respContent = "您发送的是视频消息";
        } else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
            //短视频消息
            respContent = "您发送的是短视频消息";
        } else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_LOCATION)) {
            //地理位置消息
            respContent = "您发送的是地理位置消息";
        } else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_LINK)) {
            //链接消息
            respContent = "您发送的是链接消息";
        } else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_EVENT)) {
            respContent = "您发送的是事件推送消息";
        }

        //回复文本消息
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        System.out.println("user"+textMessage.getToUserName());
        textMessage.setFromUserName(toUserName);
        System.out.println("gongzhonghao"+textMessage.getFromUserName());
        textMessage.setCreateTime((int)(new Date().getTime()));
        textMessage.setMsgType("text");
        textMessage.setContent(respContent);

        String response = MessageUtils.textMessageToXml(textMessage);

        return response;
    }

    /**
     * 事件推送类型响应信息
     *
     * @return
     */
    public String eventRespContent(String eventType) {

        String respMessage = null;

        if (eventType.equals(MessageUtils.EVENT_PUSH_TYPE_SUBSCRIBE)) {
            //关注公众号
            respMessage = "欢迎关注***公众号！";
        } else if (eventType.equals(MessageUtils.EVENT_PUSH_TYPE_UNSUBSCRIBE)) {
            //取消关注公众号
            respMessage = "***取消关注公众号";
        } else if (eventType.equals(MessageUtils.EVENT_PUSH_TYPE_CLICK)) {
            //菜单点击拉取消息
            respMessage = "***点击了菜单拉取消息";
        } else if (eventType.equals(MessageUtils.EVENT_PUSH_TYPE_VIEW)) {
            //菜单点击跳转链接
            respMessage = "***点击了菜单跳转链接";
        } else if (eventType.equals(MessageUtils.EVENT_PUSH_TYPE_LOCATION)) {
            //上报地理位置
            respMessage = "***上报了地理位置";
        }
        return respMessage;
    }


}
