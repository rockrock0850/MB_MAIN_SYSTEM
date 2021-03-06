package com.mb.main.system.model;

import java.util.Date;

import com.mb.main.system.base.BaseModel;

public class DEnterpriseService extends BaseModel{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column d_enterprise_service.guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    private String guid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column d_enterprise_service.id
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column d_enterprise_service.b_service_guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    private String bServiceGuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column d_enterprise_service.b_enterprise_guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    private String bEnterpriseGuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column d_enterprise_service.start_time
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    private Date startTime = new Date();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column d_enterprise_service.end_time
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    private Date endTime = new Date();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column d_enterprise_service.url
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    private String url = "";

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column d_enterprise_service.guid
     *
     * @return the value of d_enterprise_service.guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public String getGuid() {
        return guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column d_enterprise_service.guid
     *
     * @param guid the value for d_enterprise_service.guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column d_enterprise_service.id
     *
     * @return the value of d_enterprise_service.id
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column d_enterprise_service.id
     *
     * @param id the value for d_enterprise_service.id
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column d_enterprise_service.b_service_guid
     *
     * @return the value of d_enterprise_service.b_service_guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public String getbServiceGuid() {
        return bServiceGuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column d_enterprise_service.b_service_guid
     *
     * @param bServiceGuid the value for d_enterprise_service.b_service_guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public void setbServiceGuid(String bServiceGuid) {
        this.bServiceGuid = bServiceGuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column d_enterprise_service.b_enterprise_guid
     *
     * @return the value of d_enterprise_service.b_enterprise_guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public String getbEnterpriseGuid() {
        return bEnterpriseGuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column d_enterprise_service.b_enterprise_guid
     *
     * @param bEnterpriseGuid the value for d_enterprise_service.b_enterprise_guid
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public void setbEnterpriseGuid(String bEnterpriseGuid) {
        this.bEnterpriseGuid = bEnterpriseGuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column d_enterprise_service.start_time
     *
     * @return the value of d_enterprise_service.start_time
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column d_enterprise_service.start_time
     *
     * @param startTime the value for d_enterprise_service.start_time
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column d_enterprise_service.end_time
     *
     * @return the value of d_enterprise_service.end_time
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column d_enterprise_service.end_time
     *
     * @param endTime the value for d_enterprise_service.end_time
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column d_enterprise_service.url
     *
     * @return the value of d_enterprise_service.url
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column d_enterprise_service.url
     *
     * @param url the value for d_enterprise_service.url
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    public void setUrl(String url) {
        this.url = url;
    }
}