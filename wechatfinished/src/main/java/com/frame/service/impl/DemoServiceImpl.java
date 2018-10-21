package com.frame.service.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.model.WXuser;
import com.frame.service.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// mybatis sql模板的命名空间
	private static final String NAMESPACE = "com.frame.mapper.DemoMapper";

	@Override
	public void test() {
	//	sqlSessionTemplate.selectCursor(NAMESPACE + ".getTestbyId", "444");
	//	sqlSessionTemplate.insert(statement, parameter);
		WXuser user=new WXuser();
		//user.setId(3);
		user.setOpenid("25522");
		user.setNickname("sjijeoigj");
		System.out.println("insert插入 -> " + sqlSessionTemplate.insert(NAMESPACE + ".insert",user));
		System.out.println("seletcbyid返回查询结果集 -> " + sqlSessionTemplate.selectList(NAMESPACE + ".getTestbyId", "444")); // 查询结果集
		System.out.println("返回查询结果集 -> " + sqlSessionTemplate.selectList(NAMESPACE + ".getTest")); // 查询结果集
	}
}