package com.mb.main.system.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.main.system.setting.SecretSetting;

public class RequestVO {
	/**
	 * 
	 */
	@NotBlank
	@SecretSetting(decrypt = false)
    private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
