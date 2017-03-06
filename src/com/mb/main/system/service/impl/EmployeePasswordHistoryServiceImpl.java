package com.mb.main.system.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.REnterpriseEmployeePasswordHistoryMapper;
import com.mb.main.system.model.BEnterpriseEmployee;
import com.mb.main.system.model.BEnterpriseEmployeeExample;
import com.mb.main.system.model.REnterpriseEmployeePasswordHistory;
import com.mb.main.system.model.REnterpriseEmployeePasswordHistoryExample;
import com.mb.main.system.model.REnterpriseEmployeePasswordHistoryExample.Criteria;
import com.mb.main.system.service.EmployeePasswordHistoryService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class EmployeePasswordHistoryServiceImpl implements EmployeePasswordHistoryService{
	@Autowired
	private REnterpriseEmployeePasswordHistoryMapper rEnterpriseEmployeePasswordHistoryMapper;
	
	@Override
	public List<?> select() throws Exception {
		return rEnterpriseEmployeePasswordHistoryMapper.selectByExample(new REnterpriseEmployeePasswordHistoryExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) throws Exception {
		return null;
	}

	@Override
	public REnterpriseEmployeePasswordHistory selectByEmployeeGuid(String guid) {
		REnterpriseEmployeePasswordHistoryExample example = new REnterpriseEmployeePasswordHistoryExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andBEnterpriseEmployeeGuidEqualTo(guid);
		
		List<REnterpriseEmployeePasswordHistory> list = rEnterpriseEmployeePasswordHistoryMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Object create(Object record, String creatorName) throws Exception {
		REnterpriseEmployeePasswordHistory vo = (REnterpriseEmployeePasswordHistory) record;
		vo.setGuid(UUID.randomUUID().toString());
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		
		return rEnterpriseEmployeePasswordHistoryMapper.insert(vo) > 0 ? vo : null;
	}

	@Override
	public Object update(Object record, String creatorName) throws Exception {
		REnterpriseEmployeePasswordHistory vo = (REnterpriseEmployeePasswordHistory) record;
		
		REnterpriseEmployeePasswordHistoryExample example = new REnterpriseEmployeePasswordHistoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(vo.getGuid());
		
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return rEnterpriseEmployeePasswordHistoryMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		return 0;
	}
}
