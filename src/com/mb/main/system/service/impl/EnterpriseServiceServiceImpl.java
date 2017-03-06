package com.mb.main.system.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.DEnterpriseServiceMapper;
import com.mb.main.system.model.DEnterpriseService;
import com.mb.main.system.model.DEnterpriseServiceExample;
import com.mb.main.system.model.DEnterpriseServiceExample.Criteria;
import com.mb.main.system.service.EnterpriseServiceService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class EnterpriseServiceServiceImpl implements EnterpriseServiceService{

	@Autowired
	private DEnterpriseServiceMapper dEnterpriseServiceMapper;

	@Override
	public List<?> select() throws Exception {
		return null;
	}

	@Override
	public Object selectByPrimaryKey(int id) throws Exception {
		return null;
	}

	@Override
	public List<DEnterpriseService> selectByEnterpriseGuid(String guid) {
		DEnterpriseServiceExample example = new DEnterpriseServiceExample();
		Criteria criteria = example.createCriteria();
		criteria.andBEnterpriseGuidEqualTo(guid);
		
		List<DEnterpriseService> list = dEnterpriseServiceMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public Object create(Object record, String creatorName) throws Exception {
		DEnterpriseService vo = (DEnterpriseService) record;
		vo.setGuid(UUID.randomUUID().toString());
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return dEnterpriseServiceMapper.insert(vo) > 0 ? vo : null;
	}

	@Override
	public Object update(Object record, String creatorName) throws Exception {
		return null;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		return 0;
	}
}
