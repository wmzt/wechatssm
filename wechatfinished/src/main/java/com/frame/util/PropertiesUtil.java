package com.frame.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class PropertiesUtil {
	   private static final Logger logger = LoggerFactory
			.getLogger(PropertiesUtil.class);

	    private static Properties props;
	    //静态代码块>>普通代码块>>构造代码块
	    static {
	        String fileName = "C:/Users/Administrator/Desktop/software/tomcat/apache-tomcat-8.0.53/webapps/ssm-0.0.1-SNAPSHOT/WEB-INF/classes/wechat.properties";
	    	//String fileName = "D:/javaeeprogram/ssm-master-wechat/src/main/resources/wechat.properties";
	    	props = new Properties();
	        try {
	            //读取propertiesUtil类的配置
	            //利用反射加载类信息，获取配置文件的文件流，并指点编码格式
	        	BufferedReader in = new BufferedReader(new FileReader(fileName));
	        	props.load(in);
	          //  props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
	        } catch (IOException e) {
	            logger.error("配置文件读取异常",e);
	        }
	    }

	    public static String getProperty(String key){
	        String value = props.getProperty(key.trim());
	        if(StringUtils.isBlank(value)){
	            return null;
	        }
	        return value.trim();
	    }

	    public static String getProperty(String key,String defaultValue){
	        //提供默认值的方法，如果在配置文件中找不到对应的key,就返回参数中的默认值
	        String value = props.getProperty(key.trim());
	        if(StringUtils.isBlank(value)){
	            value = defaultValue;
	        }
	        return value.trim();
	    }
	    
	    
	    
	}

