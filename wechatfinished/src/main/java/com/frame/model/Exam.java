package com.frame.model;

import java.util.Date;

public class Exam {
 private int Id;
 private String written_time;//考试时间
 private String written_place;//考试地点
 private String written_room;//考场
 private String written_number;//考号
 private int resume_uid;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}

public String getWritten_time() {
	return written_time;
}
public void setWritten_time(String written_time) {
	this.written_time = written_time;
}
public String getWritten_place() {
	return written_place;
}
public void setWritten_place(String written_place) {
	this.written_place = written_place;
}
public String getWritten_room() {
	return written_room;
}
public void setWritten_room(String written_room) {
	this.written_room = written_room;
}
public String getWritten_number() {
	return written_number;
}
public void setWritten_number(String written_number) {
	this.written_number = written_number;
}
public int getResume_uid() {
	return resume_uid;
}
public void setResume_uid(int resume_uid) {
	this.resume_uid = resume_uid;
}





}
