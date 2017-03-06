package com.mb.main.system.service;

import java.util.List;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.BEnterprise;
import com.mb.main.system.vo.EnterpriseVO;
import com.mb.main.system.vo.form.LoginEnterpriseVO;

public interface EnterpriseService extends BaseService{
	public BEnterprise selectByGuid(String guid) throws Exception;
	
	public List<EnterpriseVO> selectVerifyStatus() throws Exception;

	public List<EnterpriseVO> selectTryonStatus() throws Exception;
	
	public List<EnterpriseVO> selectEffectiveStatus() throws Exception;
	
	public List<EnterpriseVO> selectInvalidStatus() throws Exception;
	
	public List<EnterpriseVO> selectFailStatus() throws Exception;

	public List<BEnterprise> selectByAgentsEffective(String guid) throws Exception;

	public List<BEnterprise> selectByAgentsTryon(String guid) throws Exception;

	public List<BEnterprise> selectByAgentsFail(String guid) throws Exception;

	public List<BEnterprise> selectByAgentsInvalid(String guid) throws Exception;
	
	public List<BEnterprise> selectByAgents(String guid) throws Exception;

	public BEnterprise selectByAccount(String account) throws Exception;

	public BEnterprise selectStoreNo(String storeNo) throws Exception;
	
	public BEnterprise selectUsedStoreNo(String storeNo) throws Exception;

	public BEnterprise selectDefaultStoreNo(String storeNo) throws Exception;

	public BEnterprise selectByStoreNo(String storeNo) throws Exception;

	public BEnterprise notUseToVerify(Object record, String creatorName) throws Exception;

	public BEnterprise verifyToTryon(Object record, String creatorName) throws Exception;

	public BEnterprise verifyToFail(Object record, String creatorName) throws Exception;

	public BEnterprise selectLoginInfo(BEnterprise enterpriseVO) throws Exception;
}
