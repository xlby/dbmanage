package com.fifedu.base.vo;

import java.io.Serializable;

/**
 * 操作结果
 *
 */
public class OperResult {
	
	/**
	 * 操作成功1
	 * 意义固定
	 */
	public static final int OPER_SUCCESS = 1;
	/**
	 * 平台返回码status 状态描述
	 */
	public static final String SUCCESS = "SUCCESS";
	
	public static final String FAILURE = "FAILURE";

	/**
	 * 系统错误
	 *  意义固定
	 */
	public static final int OPER_SYSTEM_ERR = -99;
	
	/**
	 * 操作失败0
	 */
	public static final int OPER_FAILURE = 0;
	
	/**
	 * 访问平台接口失败
	 */
	public static final String OPER_IPLAT_FAILURE = "用户名或密码错误！";
	/**
	 * 未知错误或异常
	 */
	public static final int OPER_UNKNOWN = -1;
	
	/**
	 * 数据重复
	 */
	public static final int OPER_REPEAT = -2;
	
	/**
	 * 错误的数据状态
	 */
	public static final int OPER_ERR_STATE = -3;
	
	/**
	 * 数据不存在
	 */
	public static final int OPER_NOTEXIST = -4;
	
	/**
	 * 没有权限
	 */
	public static final int OPER_NO_RIGHT = -5;
	
	public static final String RESPONSE_STATUS = "status";
	
	public static final String RESPONSE_MSG = "msg";
	
	public static final String RESPONSE_MSG_SUCCESS = "成功"
			;
	public static final String RESPONSE_MSG_FAILURE = "用户名或密码错误！";


	/**
	 * 操作结果代码
	 */
	private int status;
	
	/**
	 * 操作结果信息
	 */
	private String msg = "";
	
	/**
	 * 操作结果对象
	 */
	private Serializable data;
	
	/*****************Constructor**********************/
	public OperResult() {
		this.status = OPER_SUCCESS;
	}
	
	public OperResult(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public OperResult(int status, String msg, Serializable data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	/*****************Getter Setter**********************/
	public Serializable getData() {
		return data;
	}

	public void setData(Serializable data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setStatus(boolean status) {
		this.status = status ? OPER_SUCCESS : OPER_FAILURE;
	}
	
	/**
	 * 返回是否成功。
	 * @return true 成功
	 */
	public boolean success(){
		return this.status == OPER_SUCCESS;
	}
	
	@Override
	public String toString() {
		return "{status:" + this.status + ", msg:" + this.msg + ",data:" + data.toString() + "}"; 
	}
}
