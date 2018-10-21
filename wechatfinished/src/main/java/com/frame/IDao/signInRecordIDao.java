package com.frame.IDao;


import com.frame.model.SignInRecord;



public interface signInRecordIDao {
  

    int insert(SignInRecord signInRecord);
    
    SignInRecord selectbyOpenid(String openid);

 
}