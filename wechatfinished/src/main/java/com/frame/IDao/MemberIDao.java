package com.frame.IDao;

import com.frame.model.Member;




public interface MemberIDao {
  

	Member selectbyOpenid(String openid);

 
}