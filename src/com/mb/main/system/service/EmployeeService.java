package com.mb.main.system.service;

import java.util.List;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.BEnterpriseEmployee;
import com.mb.main.system.vo.EmployeeVO;

public interface EmployeeService extends BaseService{
	
	public EmployeeVO selectByGuid(String guid) throws Exception;

	public BEnterpriseEmployee selectByAccount(String account) throws Exception;
	
	public String deleteByGuid(String guid) throws Exception;

	public List<EmployeeVO> selectByEnterpriseGuid(String guid) throws Exception;

	public BEnterpriseEmployee selectByLoginInfo(BEnterpriseEmployee enterpriseEmployeeVO) throws Exception;
}
