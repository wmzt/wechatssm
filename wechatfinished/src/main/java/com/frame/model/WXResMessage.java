package com.frame.model;

public class WXResMessage {
	private String touser;
	
	private String msgtype;
//	private String text;
	private Text text;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}



	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	
}
