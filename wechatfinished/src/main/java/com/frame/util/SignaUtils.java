package com.frame.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;

public class SignaUtils {

    private static final Logger logger = LoggerFactory.getLogger(SignaUtils.class);

    /**
     * 生成加密签名
     *
     * @param token
     * @param nonce
     * @param timestamp
     * @return
     */
    public static boolean signatureCheck(String signature, String token, String nonce, String timestamp) {
    	ArrayList<String> list = new ArrayList<String>();
        //将token、timestamp、nonce进行字典排序
    	if(token!=null) {
    		  
    	        list.add(token);
    	        list.add(nonce);
    	        list.add(timestamp);
    	        Collections.sort(list);
    	}
      

        //字符串拼接进行sha1加密
        String signature2 = DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));

        //签名对比
        if (signature.equals(signature2)) {
            return true;
        }
        return false;
    }


}
