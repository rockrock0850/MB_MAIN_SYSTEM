package com.mb.main.system.vo.form;

import org.hibernate.validator.constraints.NotBlank;

public class NewAgentsStoreVO{
	@NotBlank(message = "請輸入特電編號數量")
	private String storeNos;
	
	private String serialNo;
	
	private String bAgentsGuid;

	public String getStoreNos() {
		return storeNos;
	}

	public void setStoreNos(String storeNos) {
		this.storeNos = storeNos;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getbAgentsGuid() {
		return bAgentsGuid;
	}

	public void setbAgentsGuid(String bAgentsGuid) {
		this.bAgentsGuid = bAgentsGuid;
	}
}
