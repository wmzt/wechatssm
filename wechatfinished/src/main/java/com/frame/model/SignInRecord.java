package com.frame.model;

import java.sql.Timestamp;

public class SignInRecord {
 private int id;
 private String openid;
 private int uid;
 private Timestamp time;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getOpenid() {
	return openid;
}
public void setOpenid(String openid) {
	this.openid = openid;
}

public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}


}
