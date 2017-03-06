package com.mb.main.system.vo.form;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.main.system.model.BEnterprise;

public class InvalidEnterpriseVO extends BEnterprise{
	@NotBlank(message = "請輸入特店編號")
	private String storeNo;
	
	@NotBlank(message = "請輸入企業帳號")
	private String account;

	@NotBlank(message = "請選擇總代理")
	private String bAgentsGuid;

	private String bAgentsGuidNotGeneral;
	
	private String password;

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
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

	public String getbAgentsGuid() {
		return bAgentsGuid;
	}

	public void setbAgentsGuid(String bAgentsGuid) {
		this.bAgentsGuid = bAgentsGuid;
	}

	public String getbAgentsGuidNotGeneral() {
		return bAgentsGuidNotGeneral;
	}

	public void setbAgentsGuidNotGeneral(String bAgentsGuidNotGeneral) {
		this.bAgentsGuidNotGeneral = bAgentsGuidNotGeneral;
	}
}
