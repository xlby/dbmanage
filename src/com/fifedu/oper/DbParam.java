package com.fifedu.oper;

import java.io.Serializable;
/**
 * 测试类
 * @author lz
 *
 */
public class DbParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String  payCode;
	private String userRealName;
	private String phone;
	
	private String certifNo;
	
	private String mailingAddress;
	
	private String orgName;
	
	private String payType;
	
	private String payStatus;

	
	
	
	public DbParam() {
	}


	

	public DbParam(String payCode, String userRealName, String phone, String certifNo, String mailingAddress,
			String orgName, String payType, String payStatus) {
		this.payCode = payCode;
		this.userRealName = userRealName;
		this.phone = phone;
		this.certifNo = certifNo;
		this.mailingAddress = mailingAddress;
		this.orgName = orgName;
		this.payType = payType;
		this.payStatus = payStatus;
	}




	public String getPayCode() {
		return payCode;
	}



	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}



	public String getUserRealName() {
		return userRealName;
	}



	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getCertifNo() {
		return certifNo;
	}



	public void setCertifNo(String certifNo) {
		this.certifNo = certifNo;
	}



	public String getMailingAddress() {
		return mailingAddress;
	}



	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}



	public String getOrgName() {
		return orgName;
	}



	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}



	public String getPayType() {
		return payType;
	}



	public void setPayType(String payType) {
		this.payType = payType;
	}



	public String getPayStatus() {
		return payStatus;
	}



	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
}
