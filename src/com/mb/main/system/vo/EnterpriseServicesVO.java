package com.mb.main.system.vo;

import com.mb.main.system.model.DEnterpriseService;

public class EnterpriseServicesVO extends DEnterpriseService{
	private String title;
	
	private String serioNo;
	
	private int sortNo;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSerioNo() {
		return serioNo;
	}

	public void setSerioNo(String serioNo) {
		this.serioNo = serioNo;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
}
