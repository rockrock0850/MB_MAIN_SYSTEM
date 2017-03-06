package com.mb.main.system.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.REnterprisePasswordHistoryMapper;
import com.mb.main.system.model.REnterprisePasswordHistory;
import com.mb.main.system.model.REnterprisePasswordHistoryExample;
import com.mb.main.system.model.REnterprisePasswordHistoryExample.Criteria;
import com.mb.main.system.service.EnterprisePasswordHistoryService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class EnterprisePasswordHistoryServiceImpl implements EnterprisePasswordHistoryService{
	@Autowired
	private REnterprisePasswordHistoryMapper rEnterprisePasswordHistoryMapper;
	
	@Override
	public List<?> select() throws Exception {
		return null;
	}

	@Override
	public Object selectByPrimaryKey(int id) throws Exception {
		return null;
	}

	@Override
	public REnterprisePasswordHistory selectByEnterpriseGuid(String guid) throws Exception {
		REnterprisePasswordHistoryExample example = new REnterprisePasswordHistoryExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andBEnterpriseGuidEqualTo(guid);
		
		List<REnterprisePasswordHistory> list = rEnterprisePasswordHistoryMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Object create(Object record, String creatorName) throws Exception {
		REnterprisePasswordHistory vo = (REnterprisePasswordHistory) record;
		vo.setGuid(UUID.randomUUID().toString());
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		
		return rEnterprisePasswordHistoryMapper.insert(vo) > 0 ? vo : null;
	}

	@Override
	public Object update(Object record, String creatorName) throws Exception {
		REnterprisePasswordHistory vo = (REnterprisePasswordHistory) record;
		
		REnterprisePasswordHistoryExample example = new REnterprisePasswordHistoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(vo.getGuid());
		
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return rEnterprisePasswordHistoryMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		return 0;
	}
}
