package com.frame.model;

/**
 * 模板消息数据
 * @Author wuwz
 * @TypeName TemplateData
 */
public class TemplateData {
	private String value;
	private String color;

	public TemplateData(String value, String color) {
		this.value = value;
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
