package com.mb.main.system.model;

import com.mb.main.system.base.BaseModel;

public class REnterpriseEmployeePasswordHistory extends BaseModel{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_enterprise_employee_password_history.guid
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	private String guid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_enterprise_employee_password_history.id
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_enterprise_employee_password_history.b_enterprise_employee_guid
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	private String bEnterpriseEmployeeGuid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_enterprise_employee_password_history.passwd
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	private String passwd;
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_enterprise_employee_password_history.guid
	 * @return  the value of r_enterprise_employee_password_history.guid
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_enterprise_employee_password_history.guid
	 * @param guid  the value for r_enterprise_employee_password_history.guid
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_enterprise_employee_password_history.id
	 * @return  the value of r_enterprise_employee_password_history.id
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_enterprise_employee_password_history.id
	 * @param id  the value for r_enterprise_employee_password_history.id
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_enterprise_employee_password_history.b_enterprise_employee_guid
	 * @return  the value of r_enterprise_employee_password_history.b_enterprise_employee_guid
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	public String getbEnterpriseEmployeeGuid() {
		return bEnterpriseEmployeeGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_enterprise_employee_password_history.b_enterprise_employee_guid
	 * @param bEnterpriseEmployeeGuid  the value for r_enterprise_employee_password_history.b_enterprise_employee_guid
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	public void setbEnterpriseEmployeeGuid(String bEnterpriseEmployeeGuid) {
		this.bEnterpriseEmployeeGuid = bEnterpriseEmployeeGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_enterprise_employee_password_history.passwd
	 * @return  the value of r_enterprise_employee_password_history.passwd
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_enterprise_employee_password_history.passwd
	 * @param passwd  the value for r_enterprise_employee_password_history.passwd
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}