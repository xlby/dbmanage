package com.fifedu.util.constant;
/*
 * 文件名：SelectOption.java
 * Copyright: 2014-2018 FiF. All Rights Reserved. 
 * @author: 
 * 0 
 * @since: -出题平台
 * 创建时间： 2013-9-11
 * 页面下拉框数据实体包装
 */
public class SelectOption {

    private String key;
    private String value;

    public SelectOption() {

    }

    public SelectOption(String value, String key) {
	super();
	this.key = key;
	this.value = value;
    }

    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

}
