package com.mb.main.system.model;

import java.util.Date;

import com.mb.main.system.base.BaseModel;

public class REmployeeLoginHistory extends BaseModel{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.guid
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private String guid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.id
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.b_enterprise_employee_guid
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private String bEnterpriseEmployeeGuid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.store_no
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private String storeNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.account
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private String account;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.token
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private String token = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.retry_times
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private Integer retryTimes = 0;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.unblock_date
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private Date unblockDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.force_block
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private Integer forceBlock = 1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column r_employee_login_history.status
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	private String status;
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.guid
	 * @return  the value of r_employee_login_history.guid
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.guid
	 * @param guid  the value for r_employee_login_history.guid
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.id
	 * @return  the value of r_employee_login_history.id
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.id
	 * @param id  the value for r_employee_login_history.id
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.b_enterprise_employee_guid
	 * @return  the value of r_employee_login_history.b_enterprise_employee_guid
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public String getbEnterpriseEmployeeGuid() {
		return bEnterpriseEmployeeGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.b_enterprise_employee_guid
	 * @param bEnterpriseEmployeeGuid  the value for r_employee_login_history.b_enterprise_employee_guid
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setbEnterpriseEmployeeGuid(String bEnterpriseEmployeeGuid) {
		this.bEnterpriseEmployeeGuid = bEnterpriseEmployeeGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.store_no
	 * @return  the value of r_employee_login_history.store_no
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public String getStoreNo() {
		return storeNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.store_no
	 * @param storeNo  the value for r_employee_login_history.store_no
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.account
	 * @return  the value of r_employee_login_history.account
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.account
	 * @param account  the value for r_employee_login_history.account
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.token
	 * @return  the value of r_employee_login_history.token
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public String getToken() {
		return token;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.token
	 * @param token  the value for r_employee_login_history.token
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.retry_times
	 * @return  the value of r_employee_login_history.retry_times
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public Integer getRetryTimes() {
		return retryTimes;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.retry_times
	 * @param retryTimes  the value for r_employee_login_history.retry_times
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.unblock_date
	 * @return  the value of r_employee_login_history.unblock_date
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public Date getUnblockDate() {
		return unblockDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.unblock_date
	 * @param unblockDate  the value for r_employee_login_history.unblock_date
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setUnblockDate(Date unblockDate) {
		this.unblockDate = unblockDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.force_block
	 * @return  the value of r_employee_login_history.force_block
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public Integer getForceBlock() {
		return forceBlock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.force_block
	 * @param forceBlock  the value for r_employee_login_history.force_block
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setForceBlock(Integer forceBlock) {
		this.forceBlock = forceBlock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column r_employee_login_history.status
	 * @return  the value of r_employee_login_history.status
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column r_employee_login_history.status
	 * @param status  the value for r_employee_login_history.status
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}