package com.mb.main.system.service;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.BIndustry;

public interface IndustryService extends BaseService{
	public BIndustry selectByEterpriseGuid(String guid) throws Exception;
}
