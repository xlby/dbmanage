package com.fifedu.mybatis.model.base;

import java.io.Serializable;
import java.util.Date;

import com.fifedu.mybatis.base.BaseBean;
/**
*用户基本信息表
*/
public class UcUser  extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 用户id
	*/
	private String id ;
	/**
	* 删除标记，0-正常，1-已删除，2-待审核，3-冻结，4-未通过
	*/
	private String delFlag ;
	/**
	* 登陆名
	*/
	private String loginName ;
	/**
	* 电子邮箱
	*/
	private String email ;
	/**
	* 手机
	*/
	private String mobile ;
	/**
	* 密码
	*/
	private String userPassword ;
	/**
	* 编号
	*/
	private String userCode ;
	/**
	* 用户名
	*/
	private String userName ;
	/**
	* 性别，0-女性；1-男性
	*/
	private String gender ;
	/**
	* 电话
	*/
	private String phone ;
	/**
	* 用户名拼音
	*/
	private String pinyinName ;
	/**
	* 用户名拼音
	*/
	private Date birthDate ;
	/**
	* 即时通讯
	*/
	private String im ;
	/**
	* 家庭住址
	*/
	private String homeAddress ;
	/**
	* 证件号
	*/
	private String idCardNo ;
	/**
	* 备注
	*/
	private String remark ;
	/**
	* 应用名称
	*/
	private String appName ;
	/**
	* 创建者
	*/
	private String creator ;
	/**
	* 创建时间
	*/
	private Date createTime ;
	/**
	* 修改者
	*/
	private String modificator ;
	/**
	* 修改时间
	*/
	private Date updateTime ;
	private Integer reserved1 ;
	private String reserved2 ;
	/**
	* 标记数据状态，0-正常
	*/
	private String forbidden ;
	/**
	* 角色名称
	*/
	private String roleName ;
	/**
	* 所在机构id
	*/
	private String orgId ;
	/**
	* 所在班级id
	*/
	private String classId ;
	/**
	* 所在学校id
	*/
	private String schoolId ;
	private String reserved3 ;
	private String reserved4 ;
	private String reserved5 ;
	/**
	* 密码明码
	*/
	private String reserved6 ;
	/**
	* 0是系统生存密码
	*/
	private String reserved7 ;
	private String reserved8 ;
	private Integer reserved9 ;
	private Integer reserved10 ;
	private String faceno ;
	private String voiceno ;
	/**
	* 材料
	*/
	private String reserved11 ;
	/**
	* QQ号码
	*/
	private String qq ;
	/**
	* xuexing
	*/
	private String bloodType ;
	/**
	* tongxin
	*/
	private String mailAddress ;


	/**
	* 用户id
	*/
	public String getId() {
			return this.id;
		}
	/**
	* 用户id
	*/
	public void setId(String id) {
			this.id = id;
		}
	/**
	* 删除标记，0-正常，1-已删除，2-待审核，3-冻结，4-未通过
	*/
	public String getDelFlag() {
			return this.delFlag;
		}
	/**
	* 删除标记，0-正常，1-已删除，2-待审核，3-冻结，4-未通过
	*/
	public void setDelFlag(String delFlag) {
			this.delFlag = delFlag;
		}
	/**
	* 登陆名
	*/
	public String getLoginName() {
			return this.loginName;
		}
	/**
	* 登陆名
	*/
	public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
	/**
	* 电子邮箱
	*/
	public String getEmail() {
			return this.email;
		}
	/**
	* 电子邮箱
	*/
	public void setEmail(String email) {
			this.email = email;
		}
	/**
	* 手机
	*/
	public String getMobile() {
			return this.mobile;
		}
	/**
	* 手机
	*/
	public void setMobile(String mobile) {
			this.mobile = mobile;
		}
	/**
	* 密码
	*/
	public String getUserPassword() {
			return this.userPassword;
		}
	/**
	* 密码
	*/
	public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
	/**
	* 编号
	*/
	public String getUserCode() {
			return this.userCode;
		}
	/**
	* 编号
	*/
	public void setUserCode(String userCode) {
			this.userCode = userCode;
		}
	/**
	* 用户名
	*/
	public String getUserName() {
			return this.userName;
		}
	/**
	* 用户名
	*/
	public void setUserName(String userName) {
			this.userName = userName;
		}
	/**
	* 性别，0-女性；1-男性
	*/
	public String getGender() {
			return this.gender;
		}
	/**
	* 性别，0-女性；1-男性
	*/
	public void setGender(String gender) {
			this.gender = gender;
		}
	/**
	* 电话
	*/
	public String getPhone() {
			return this.phone;
		}
	/**
	* 电话
	*/
	public void setPhone(String phone) {
			this.phone = phone;
		}
	/**
	* 用户名拼音
	*/
	public String getPinyinName() {
			return this.pinyinName;
		}
	/**
	* 用户名拼音
	*/
	public void setPinyinName(String pinyinName) {
			this.pinyinName = pinyinName;
		}
	/**
	* 用户名拼音
	*/
	public Date getBirthDate() {
			return this.birthDate;
		}
	/**
	* 用户名拼音
	*/
	public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}
	/**
	* 即时通讯
	*/
	public String getIm() {
			return this.im;
		}
	/**
	* 即时通讯
	*/
	public void setIm(String im) {
			this.im = im;
		}
	/**
	* 家庭住址
	*/
	public String getHomeAddress() {
			return this.homeAddress;
		}
	/**
	* 家庭住址
	*/
	public void setHomeAddress(String homeAddress) {
			this.homeAddress = homeAddress;
		}
	/**
	* 证件号
	*/
	public String getIdCardNo() {
			return this.idCardNo;
		}
	/**
	* 证件号
	*/
	public void setIdCardNo(String idCardNo) {
			this.idCardNo = idCardNo;
		}
	/**
	* 备注
	*/
	public String getRemark() {
			return this.remark;
		}
	/**
	* 备注
	*/
	public void setRemark(String remark) {
			this.remark = remark;
		}
	/**
	* 应用名称
	*/
	public String getAppName() {
			return this.appName;
		}
	/**
	* 应用名称
	*/
	public void setAppName(String appName) {
			this.appName = appName;
		}
	/**
	* 创建者
	*/
	public String getCreator() {
			return this.creator;
		}
	/**
	* 创建者
	*/
	public void setCreator(String creator) {
			this.creator = creator;
		}
	/**
	* 创建时间
	*/
	public Date getCreateTime() {
			return this.createTime;
		}
	/**
	* 创建时间
	*/
	public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	/**
	* 修改者
	*/
	public String getModificator() {
			return this.modificator;
		}
	/**
	* 修改者
	*/
	public void setModificator(String modificator) {
			this.modificator = modificator;
		}
	/**
	* 修改时间
	*/
	public Date getUpdateTime() {
			return this.updateTime;
		}
	/**
	* 修改时间
	*/
	public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
	public Integer getReserved1() {
			return this.reserved1;
		}
	public void setReserved1(Integer reserved1) {
			this.reserved1 = reserved1;
		}
	public String getReserved2() {
			return this.reserved2;
		}
	public void setReserved2(String reserved2) {
			this.reserved2 = reserved2;
		}
	/**
	* 标记数据状态，0-正常
	*/
	public String getForbidden() {
			return this.forbidden;
		}
	/**
	* 标记数据状态，0-正常
	*/
	public void setForbidden(String forbidden) {
			this.forbidden = forbidden;
		}
	/**
	* 角色名称
	*/
	public String getRoleName() {
			return this.roleName;
		}
	/**
	* 角色名称
	*/
	public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
	/**
	* 所在机构id
	*/
	public String getOrgId() {
			return this.orgId;
		}
	/**
	* 所在机构id
	*/
	public void setOrgId(String orgId) {
			this.orgId = orgId;
		}
	/**
	* 所在班级id
	*/
	public String getClassId() {
			return this.classId;
		}
	/**
	* 所在班级id
	*/
	public void setClassId(String classId) {
			this.classId = classId;
		}
	/**
	* 所在学校id
	*/
	public String getSchoolId() {
			return this.schoolId;
		}
	/**
	* 所在学校id
	*/
	public void setSchoolId(String schoolId) {
			this.schoolId = schoolId;
		}
	public String getReserved3() {
			return this.reserved3;
		}
	public void setReserved3(String reserved3) {
			this.reserved3 = reserved3;
		}
	public String getReserved4() {
			return this.reserved4;
		}
	public void setReserved4(String reserved4) {
			this.reserved4 = reserved4;
		}
	public String getReserved5() {
			return this.reserved5;
		}
	public void setReserved5(String reserved5) {
			this.reserved5 = reserved5;
		}
	/**
	* 密码明码
	*/
	public String getReserved6() {
			return this.reserved6;
		}
	/**
	* 密码明码
	*/
	public void setReserved6(String reserved6) {
			this.reserved6 = reserved6;
		}
	/**
	* 0是系统生存密码
	*/
	public String getReserved7() {
			return this.reserved7;
		}
	/**
	* 0是系统生存密码
	*/
	public void setReserved7(String reserved7) {
			this.reserved7 = reserved7;
		}
	public String getReserved8() {
			return this.reserved8;
		}
	public void setReserved8(String reserved8) {
			this.reserved8 = reserved8;
		}
	public Integer getReserved9() {
			return this.reserved9;
		}
	public void setReserved9(Integer reserved9) {
			this.reserved9 = reserved9;
		}
	public Integer getReserved10() {
			return this.reserved10;
		}
	public void setReserved10(Integer reserved10) {
			this.reserved10 = reserved10;
		}
	public String getFaceno() {
			return this.faceno;
		}
	public void setFaceno(String faceno) {
			this.faceno = faceno;
		}
	public String getVoiceno() {
			return this.voiceno;
		}
	public void setVoiceno(String voiceno) {
			this.voiceno = voiceno;
		}
	/**
	* 材料
	*/
	public String getReserved11() {
			return this.reserved11;
		}
	/**
	* 材料
	*/
	public void setReserved11(String reserved11) {
			this.reserved11 = reserved11;
		}
	/**
	* QQ号码
	*/
	public String getQq() {
			return this.qq;
		}
	/**
	* QQ号码
	*/
	public void setQq(String qq) {
			this.qq = qq;
		}
	/**
	* xuexing
	*/
	public String getBloodType() {
			return this.bloodType;
		}
	/**
	* xuexing
	*/
	public void setBloodType(String bloodType) {
			this.bloodType = bloodType;
		}
	/**
	* tongxin
	*/
	public String getMailAddress() {
			return this.mailAddress;
		}
	/**
	* tongxin
	*/
	public void setMailAddress(String mailAddress) {
			this.mailAddress = mailAddress;
		}
}