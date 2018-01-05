package com.fifedu.base.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 登陆者信息 VO
 * @author fly
 *
 */
public class LoginUserInfo implements Serializable{
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 真实姓名
	 */
	private String RealName;

	/**
	 * 角色名称- 唯一
	 */
	private String roleName;
	
	/**
	 * 用户登陆账户
	 */
	private String loginName;
	
	
	/**
	 * 登录IP
	 */
	private String ipAddress;
	
	
	/**
	 * 单位ID
	 */
	private String organId;
	
	/**
	 * schoolCode
	 */
	private String schoolCode;
	
	/**
	 * 用户头像
	 */
	private String userimg;
	
	/**
	 * tgtcache
	 * @return
	 */
	private String tgtid;
	
	


	
	/**
	 * 单位所在区县ID
	 */
	private String orgDistrictId;
	/**
	 * 单位所在市ID
	 */
	private String orgCityId;
	/**
	 * 单位所在省ID
	 */
	private String orgProvinceId;
	/**
	 * 单位所在国家ID
	 */
	private String orgCountryId;
	

	
	private boolean isManage;
	
	private String treeNodePath;
	/**
	 * 单位名称
	 */
	private String orgName;  
	/**
	 * 是否是省级管理员
	 */
	private boolean isProManage;  
	/**
	 * 是否是市级管理员
	 */
	private boolean isCityManage; 
	/**
	 * 是否是区县级管理员
	 */
	private boolean isDisManage;   
    /**
     * 是否是学校管理员
     */
	private boolean isSchManage;
	/**
	 * 上级机构ID
	 */
	private String parentOrgId;

	private String schoolId;

	private String classId;
	/**
	 *1-未登录证书查询  
	 */
	private String type;
	
	public boolean isSchManage() {
		return isSchManage;
	}


	public void setSchManage(boolean isSchManage) {
		this.isSchManage = isSchManage;
	}


	public boolean isProManage() {
		return isProManage;
	}


	public void setProManage(boolean isProManage) {
		this.isProManage = isProManage;
	}


	public boolean isCityManage() {
		return isCityManage;
	}


	public void setCityManage(boolean isCityManage) {
		this.isCityManage = isCityManage;
	}


	public boolean isDisManage() {
		return isDisManage;
	}


	public void setDisManage(boolean isDisManage) {
		this.isDisManage = isDisManage;
	}


	public boolean isManage() {
		return isManage;
	}


	public void setManage(boolean isManage) {
		this.isManage = isManage;
	}


	


	public String getOrgDistrictId() {
		return orgDistrictId;
	}


	public void setOrgDistrictId(String orgDistrictId) {
		this.orgDistrictId = orgDistrictId;
	}


	public String getOrgCityId() {
		return orgCityId;
	}


	public void setOrgCityId(String orgCityId) {
		this.orgCityId = orgCityId;
	}


	public String getOrgProvinceId() {
		return orgProvinceId;
	}


	public void setOrgProvinceId(String orgProvinceId) {
		this.orgProvinceId = orgProvinceId;
	}


	public String getOrgCountryId() {
		return orgCountryId;
	}


	public void setOrgCountryId(String orgCountryId) {
		this.orgCountryId = orgCountryId;
	}


	public String getSchoolCode() {
		return schoolCode;
	}


	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}


	/**
	 * 登录时间
	 */
	private Date loginDttm;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getRealName() {
		return RealName;
	}


	public void setRealName(String realName) {
		RealName = realName;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getOrganId() {
		return organId;
	}


	public void setOrganId(String organId) {
		this.organId = organId;
	}


	public Date getLoginDttm() {
		return loginDttm;
	}


	public void setLoginDttm(Date loginDttm) {
		this.loginDttm = loginDttm;
	}


	public String getUserimg() {
		return userimg;
	}


	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}


	public String getTgtid() {
		return tgtid;
	}


	public void setTgtid(String tgtid) {
		this.tgtid = tgtid;
	}
	

	public String getTreeNodePath() {
		return treeNodePath;
	}


	public void setTreeNodePath(String treeNodePath) {
		this.treeNodePath = treeNodePath;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getParentOrgId() {
		return parentOrgId;
	}


	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
}
