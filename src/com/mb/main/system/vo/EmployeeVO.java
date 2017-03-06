package com.mb.main.system.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.mb.main.system.model.BEnterpriseEmployee;

public class EmployeeVO extends BEnterpriseEmployee{
	private String enterpriseGuid;
	
	@NotBlank(message = "請輸帳號")
	private String account;

	@NotBlank(message = "請輸入密碼")
	private String password;

	@NotBlank(message = "請輸入郵件")
	@Email(message = "請輸入正確的郵件地址")
	private String email;
	
	/*
	 * 
	 */

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnterpriseGuid() {
		return enterpriseGuid;
	}

	public void setEnterpriseGuid(String enterpriseGuid) {
		this.enterpriseGuid = enterpriseGuid;
	}
}
