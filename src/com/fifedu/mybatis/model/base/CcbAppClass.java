package com.fifedu.mybatis.model.base;

import java.io.Serializable;
import java.util.Date;

import com.fifedu.mybatis.base.BaseBean;


/**
*班级信息表
*/
public class CcbAppClass extends BaseBean  implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键
	*/
	private String id ;
	/**
	* 班级ID
	*/
	private String classId ;
	/**
	* 班级名称
	*/
	private String className ;
	/**
	* 年级
	*/
	private String grade ;
	/**
	* 自然班/教学班
	*/
	private Integer type ;
	/**
	* 学校id
	*/
	private String schoolId ;
	private Integer isEnable ;
	/**
	* 有效期
	*/
	private Date validTime ;
	private String schoolCode ;


	/**
	* 主键
	*/
	public String getId() {
			return this.id;
		}
	/**
	* 主键
	*/
	public void setId(String id) {
			this.id = id;
		}
	/**
	* 班级ID
	*/
	public String getClassId() {
			return this.classId;
		}
	/**
	* 班级ID
	*/
	public void setClassId(String classId) {
			this.classId = classId;
		}
	/**
	* 班级名称
	*/
	public String getClassName() {
			return this.className;
		}
	/**
	* 班级名称
	*/
	public void setClassName(String className) {
			this.className = className;
		}
	/**
	* 年级
	*/
	public String getGrade() {
			return this.grade;
		}
	/**
	* 年级
	*/
	public void setGrade(String grade) {
			this.grade = grade;
		}
	/**
	* 自然班/教学班
	*/
	public Integer getType() {
			return this.type;
		}
	/**
	* 自然班/教学班
	*/
	public void setType(Integer type) {
			this.type = type;
		}
	/**
	* 学校id
	*/
	public String getSchoolId() {
			return this.schoolId;
		}
	/**
	* 学校id
	*/
	public void setSchoolId(String schoolId) {
			this.schoolId = schoolId;
		}
	public Integer getIsEnable() {
			return this.isEnable;
		}
	public void setIsEnable(Integer isEnable) {
			this.isEnable = isEnable;
		}
	/**
	* 有效期
	*/
	public Date getValidTime() {
			return this.validTime;
		}
	/**
	* 有效期
	*/
	public void setValidTime(Date validTime) {
			this.validTime = validTime;
		}
	public String getSchoolCode() {
			return this.schoolCode;
		}
	public void setSchoolCode(String schoolCode) {
			this.schoolCode = schoolCode;
		}
}