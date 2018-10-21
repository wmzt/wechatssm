package com.frame.controller;

import com.frame.service.WechatIntelService;
import com.frame.service.impl.sendTempMsgThread;
import com.frame.service.impl.sendTempMsgcallThread;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api")
public class WechatIntelController {
	private static final Logger logger = LoggerFactory.getLogger(WechatIntelController.class);
	@Autowired
	private WechatIntelService wechatIntelService;

	/**
	 * 发送模板消息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sendTempMsg", method = RequestMethod.GET)
	public String sendTempMsg(@ModelAttribute("openid") String openid) {
		logger.info(String.format("进入sendmsg"));
		logger.info(String.format("接口获取openid结果：%s", openid));
		String access_token = null;
		String result = null;

		// 获取access_token
		access_token = wechatIntelService.getAccessToken();
		logger.info(String.format("接口获取getaccess_token结果：%s", access_token));

		// 发送消息 多线程
//
//		sendTempMsgThread sendTempMsgthread = new sendTempMsgThread(
//				access_token, openid);
//		Thread thread = new Thread(sendTempMsgthread);
//		thread.start();
//		result = sendTempMsgthread.getResult();
		// 线程池
		ExecutorService pool = Executors.newCachedThreadPool();
		// 创建线程
		sendTempMsgcallThread c = new sendTempMsgcallThread(access_token, openid);
		// 执行任务并获取Future对象
		Future<String> f = pool.submit(c);
		try {
			result = f.get();
			logger.info(String.format("接口获取sendresult结果：%s", result));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 关闭线程池
		pool.shutdown();

		if (result != null) {
			if (result.equals("ok")) {
				return "hello";
			} else {
				// 已经签到
				if (result.equals("hassignin")) {
					return "hassignin";
				} else {
					return "error";
				}

			}

		} else {
			return "error";
		}

	}



}
