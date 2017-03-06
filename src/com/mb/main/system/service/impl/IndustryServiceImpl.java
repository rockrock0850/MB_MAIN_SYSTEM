package com.mb.main.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.BIndustryMapper;
import com.mb.main.system.model.BIndustry;
import com.mb.main.system.model.BIndustryExample;
import com.mb.main.system.model.BIndustryExample.Criteria;
import com.mb.main.system.service.IndustryService;
import com.mb.main.system.utils.Constant.Status;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class IndustryServiceImpl implements IndustryService{

	@Autowired
	private BIndustryMapper bIndustryMapper;

	@Override
	public List<?> select() {
		BIndustryExample example = new BIndustryExample();
		example.setOrderByClause("sort_no asc");
		return bIndustryMapper.selectByExample(example);
	}

	@Override
	public BIndustry selectByEterpriseGuid(String guid) throws Exception {
		BIndustryExample example = new BIndustryExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(guid);
		
		List<BIndustry> list = bIndustryMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Object create(Object record, String creatorName) {
		return null;
	}

	@Override
	public Status update(Object record, String creatorName) {
		return null;
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}
}
