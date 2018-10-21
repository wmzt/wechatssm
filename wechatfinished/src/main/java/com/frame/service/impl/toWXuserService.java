package com.frame.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.frame.model.WXuser;



public class toWXuserService {
//	BaseSqlAction action=new BaseSqlAction();
	public WXuser toWXuser(String openid,String name) {
		WXuser wxuser=new WXuser();
		wxuser.setOpenid(openid);
		wxuser.setNickname(name);
		return wxuser;
		
	}

}
