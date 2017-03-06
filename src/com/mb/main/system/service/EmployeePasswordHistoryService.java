package com.mb.main.system.service;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.REnterpriseEmployeePasswordHistory;

public interface EmployeePasswordHistoryService extends BaseService{
	public REnterpriseEmployeePasswordHistory selectByEmployeeGuid(String guid);
}
