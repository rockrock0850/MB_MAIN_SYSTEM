package com.mb.main.system.base;

import java.util.Date;

import lombok.extern.log4j.Log4j;

@Log4j
public class BaseModel {
	private String guid;
	
	private Integer isInvalid = 1;
	
	private String creatorGuid = "";
	
	private String creatorName = "";
	
	private Date creatorDate = new Date();
	
	private String modifierGuid = "";
	
	private String modifierName = "";
	
	private Date modifierDate = new Date();
	
	private String invalidGuid = "";
	
	private String invalidName = "";
	
	private Date invalidDate = new Date();

	/*
	 * 
	 */
	
	public Integer getIsInvalid() {
		return isInvalid;
	}

	public void setIsInvalid(Integer isInvalid) {
		this.isInvalid = isInvalid;
	}

	public String getCreatorGuid() {
		return creatorGuid;
	}

	public void setCreatorGuid(String creatorGuid) {
		this.creatorGuid = creatorGuid;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreatorDate() {
		return creatorDate;
	}

	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}

	public String getModifierGuid() {
		return modifierGuid;
	}

	public void setModifierGuid(String modifierGuid) {
		this.modifierGuid = modifierGuid;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public Date getModifierDate() {
		return modifierDate;
	}

	public void setModifierDate(Date modifierDate) {
		this.modifierDate = modifierDate;
	}

	public String getInvalidGuid() {
		return invalidGuid;
	}

	public void setInvalidGuid(String invalidGuid) {
		this.invalidGuid = invalidGuid;
	}

	public String getInvalidName() {
		return invalidName;
	}

	public void setInvalidName(String invalidName) {
		this.invalidName = invalidName;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}
