package com.mb.main.system.service;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.REnterprisePasswordHistory;

public interface EnterprisePasswordHistoryService extends BaseService{
	public REnterprisePasswordHistory selectByEnterpriseGuid(String guid) throws Exception;
}
