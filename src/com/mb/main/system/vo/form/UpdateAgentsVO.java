package com.mb.main.system.vo.form;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.main.system.model.BAgents;

public class UpdateAgentsVO extends BAgents{
	@NotBlank(message = "請選擇總代理商")
	private String bAgentsGuid;
	
	@NotBlank(message = "請輸入代理商識別碼")
	private String serialNo;
	
	@NotBlank(message = "請輸入代理商帳號")
	private String account;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getbAgentsGuid() {
		return bAgentsGuid;
	}

	public void setbAgentsGuid(String bAgentsGuid) {
		this.bAgentsGuid = bAgentsGuid;
	}
}
