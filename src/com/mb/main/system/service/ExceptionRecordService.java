package com.mb.main.system.service;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.ExceptionRecord;

public interface ExceptionRecordService extends BaseService{
	public ExceptionRecord selectByDate(ExceptionRecord er);
	
	public ExceptionRecord selectByTarget(ExceptionRecord er);
}
