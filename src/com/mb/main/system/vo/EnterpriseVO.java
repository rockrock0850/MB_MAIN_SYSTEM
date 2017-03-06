package com.mb.main.system.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.mb.main.system.model.BEnterprise;

public class EnterpriseVO extends BEnterprise{
	@NotBlank(message = "請輸入公司電話")
	@Size(max = 10, min = 9, message = "請輸入正確的公司電話")
	private String companyPhone;

	@NotBlank(message = "請輸入行動電話")
	@Size(max = 10, min = 10, message = "請輸入正確的行動電話")
	private String applicantMobile;

	@NotBlank(message = "請輸入電子郵件")
	@Email(message = "請輸入正確的電子郵件")
	private String email = "";

	private Integer emailValidate = 1;

	private Integer numberOfEmployees = 5;

	private Integer status = 1;

	private String remark = "";
	
	private String industry;
	
	private String password;
	
	private String createDate;
	
	/*
	 * 
	 */

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getApplicantMobile() {
		return applicantMobile;
	}

	public void setApplicantMobile(String applicantMobile) {
		this.applicantMobile = applicantMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailValidate() {
		return emailValidate;
	}

	public void setEmailValidate(Integer emailValidate) {
		this.emailValidate = emailValidate;
	}

	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
