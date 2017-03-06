package com.mb.main.system.service;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.REmployeeLoginHistory;

public interface EmployeeLoginHistoryService extends BaseService{

	public REmployeeLoginHistory selectByLastRetryTimes(String pk);

	public REmployeeLoginHistory selectByAccountStoreNo(REmployeeLoginHistory vo);

	public REmployeeLoginHistory selectByLastModifierDate(REmployeeLoginHistory vo);
}
