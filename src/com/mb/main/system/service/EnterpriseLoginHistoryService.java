package com.mb.main.system.service;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.REnterpriseLoginHistory;

public interface EnterpriseLoginHistoryService extends BaseService{

	public REnterpriseLoginHistory selectByLastRetryTimes(String pk);

	public REnterpriseLoginHistory selectByAccountStoreNo(REnterpriseLoginHistory  vo);

	public REnterpriseLoginHistory selectByLastModifierDate(REnterpriseLoginHistory vo);
}
