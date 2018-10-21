package com.frame.dao;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.controller.wechatController;
import com.frame.model.Exam;
import com.frame.model.SignInRecord;
import com.frame.model.Member;
import com.frame.model.WXuser;

import com.frame.service.DemoService;

@Service("WXuserinfoDao")
public class  WXuserinfoDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	//private SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(null);
	
	// mybatis sql模板的命名空间
	private static final String NAMESPACE = "com.frame.mapper.DemoMapper";
//如果重复登录，会出现异常，异常r=2,已经登录 ； r=0,登录失败；r=1，登录成功
	public int insert(SignInRecord signInRecord) {
		int r=0;
		//System.out.println("insert插入 -> " + sqlSessionTemplate.insert(NAMESPACE + ".insert",signInRecord));
		//System.out.println("seletcbyid返回查询结果集 -> " + sqlSessionTemplate.selectList(NAMESPACE + ".getTestbyId", "444")); // 查询结果集
		try {
			 r=sqlSessionTemplate.insert(NAMESPACE + ".insert",signInRecord);
			 r=1;
		}catch(Exception e) {
			System.out.println("==========="+e.getClass().getName());
		     if(e.getClass().getName().equals("org.springframework.dao.DuplicateKeyException")){
		    	 
		        r=2;
			 }
		    
		}
		System.out.println("==========="+r);
		return r;
	}
//	public int insert(WXuser user) {
//
//		System.out.println("insert插入 -> " + sqlSessionTemplate.insert(NAMESPACE + ".insert",user));
//		//System.out.println("seletcbyid返回查询结果集 -> " + sqlSessionTemplate.selectList(NAMESPACE + ".getTestbyId", "444")); // 查询结果集
//		System.out.println("返回查询结果集 -> " + sqlSessionTemplate.selectList(NAMESPACE + ".getTest")); // 查询结果集
//		int r=sqlSessionTemplate.insert(NAMESPACE + ".insert",user);
//		if(r==1) {
//			return 1;
//		}else {
//			return 0;
//		}
//
//	}
	
    public int selectbyOpenid(String openid) {
        Member user=new Member();
  
    	int uid=0;
    	List<Member> resultlist=new ArrayList<Member>();
    	resultlist=sqlSessionTemplate.selectList(NAMESPACE + ".selectbyOpenid",openid);
    	//System.out.println("seletcbyid返回查询结果集 -> " + sqlSessionTemplate.selectList(NAMESPACE + ".selectbyOpenid",openid)); // 查询结果集
	    if(resultlist!=null) {
	    	user=resultlist.get(0);
	    	uid=user.getUid();
	    }
    	return uid;
    }
    
    
    //根据userid 获取考场信息
    public Exam selectbyUid(int uid) {
    	Exam exam=new Exam();
    	List<Exam> resultlist=new ArrayList<Exam>();
    	if(uid!=0) {
    		resultlist=sqlSessionTemplate.selectList(NAMESPACE + ".selectbyUid",uid);
    		exam= resultlist.get(0);
  	    	  	
  	    }
      	return exam;
    }
    
 
}