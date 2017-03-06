package com.mb.main.system.service;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.VEnterpriseLogin;
import com.mb.main.system.vo.form.LoginEnterpriseVO;

public interface EnterpriseLoginService extends BaseService{
	
	public VEnterpriseLogin selectByAccount(LoginEnterpriseVO vo) throws Exception;

	public VEnterpriseLogin selectByAccountPassword(LoginEnterpriseVO vo) throws Exception;

	public VEnterpriseLogin selectByAccountStoreNo(LoginEnterpriseVO vo) throws Exception;

	public VEnterpriseLogin selectByLoginInfo(LoginEnterpriseVO vo) throws Exception;

	public VEnterpriseLogin selectByGuid(String guid) throws Exception;
}
