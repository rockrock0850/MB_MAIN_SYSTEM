package com.mb.main.system.model;

import com.mb.main.system.base.BaseModel;

public class REnterprisePasswordHistory extends BaseModel{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column r_enterprise_password_history.guid
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    private String guid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column r_enterprise_password_history.id
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column r_enterprise_password_history.b_enterprise_guid
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    private String bEnterpriseGuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column r_enterprise_password_history.passwd
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    private String passwd;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column r_enterprise_password_history.guid
     *
     * @return the value of r_enterprise_password_history.guid
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    public String getGuid() {
        return guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column r_enterprise_password_history.guid
     *
     * @param guid the value for r_enterprise_password_history.guid
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column r_enterprise_password_history.id
     *
     * @return the value of r_enterprise_password_history.id
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column r_enterprise_password_history.id
     *
     * @param id the value for r_enterprise_password_history.id
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column r_enterprise_password_history.b_enterprise_guid
     *
     * @return the value of r_enterprise_password_history.b_enterprise_guid
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    public String getbEnterpriseGuid() {
        return bEnterpriseGuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column r_enterprise_password_history.b_enterprise_guid
     *
     * @param bEnterpriseGuid the value for r_enterprise_password_history.b_enterprise_guid
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    public void setbEnterpriseGuid(String bEnterpriseGuid) {
        this.bEnterpriseGuid = bEnterpriseGuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column r_enterprise_password_history.passwd
     *
     * @return the value of r_enterprise_password_history.passwd
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column r_enterprise_password_history.passwd
     *
     * @param passwd the value for r_enterprise_password_history.passwd
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}