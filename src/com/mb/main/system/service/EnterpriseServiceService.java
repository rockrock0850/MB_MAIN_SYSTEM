package com.mb.main.system.service;

import java.util.List;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.DEnterpriseService;

public interface EnterpriseServiceService extends BaseService{

	public List<DEnterpriseService> selectByEnterpriseGuid(String guid);
}
