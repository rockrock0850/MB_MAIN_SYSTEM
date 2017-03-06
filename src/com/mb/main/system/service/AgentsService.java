package com.mb.main.system.service;

import java.util.List;

import com.mb.main.system.base.BaseService;
import com.mb.main.system.model.BAgents;

public interface AgentsService extends BaseService{
	public BAgents selectDefault() throws Exception;

	public BAgents selectByGuid(String guid) throws Exception;

	public BAgents selectByAccountOrSerialNo(BAgents vo) throws Exception;

	public BAgents selectBySerialNo(String serialNo) throws Exception;

	public BAgents selectByAccount(String account) throws Exception;

	public BAgents selectByUniformNumber(String uniformNumber) throws Exception;

	public List<BAgents> selectByGeneral() throws Exception;

	public List<BAgents> selectByNotGeneral() throws Exception;

	public List<BAgents> selectByNotGeneral(String guid) throws Exception;

	public List<BAgents> selectByAgentsGuid(String guid) throws Exception;
}
