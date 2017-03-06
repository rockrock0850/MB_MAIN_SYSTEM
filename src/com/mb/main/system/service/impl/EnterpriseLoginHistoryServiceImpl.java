package com.mb.main.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.REnterpriseLoginHistoryMapper;
import com.mb.main.system.model.REnterpriseLoginHistory;
import com.mb.main.system.model.REnterpriseLoginHistoryExample;
import com.mb.main.system.model.REnterpriseLoginHistoryExample.Criteria;
import com.mb.main.system.service.EnterpriseLoginHistoryService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class EnterpriseLoginHistoryServiceImpl implements EnterpriseLoginHistoryService{
	@Autowired
	private REnterpriseLoginHistoryMapper enterpriseLoginHistoryMapper;
	
	@Override
	public List<?> select() throws Exception {
		return enterpriseLoginHistoryMapper.selectByExample(new REnterpriseLoginHistoryExample());
	}

	@Override
	public REnterpriseLoginHistory selectByLastRetryTimes(String pk) {
		REnterpriseLoginHistoryExample example = new REnterpriseLoginHistoryExample();
		example.setOrderByClause("retry_times desc");
		
		Criteria criteria = example.createCriteria();
		criteria.andBEnterpriseGuidEqualTo(pk);
		
		List<REnterpriseLoginHistory> list = enterpriseLoginHistoryMapper.selectByExample(example);
//		Collections.sort(list, new Comparator<REnterpriseLoginHistory>() {
//		            public int compare(REnterpriseLoginHistory o1, REnterpriseLoginHistory o2) {
//		                return o2.getRetryTimes()-o1.getRetryTimes();
//		            }
//		        });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Object selectByPrimaryKey(int id) throws Exception {
		return null;
	}

	@Override
	public Object create(Object record, String creatorName) throws Exception {
		REnterpriseLoginHistory vo = (REnterpriseLoginHistory) record;
		vo.setGuid(StringUtils.isBlank(vo.getGuid()) ? UUID.randomUUID().toString() : vo.getGuid());
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		
		return enterpriseLoginHistoryMapper.insert(vo) > 0 ? vo : null;
	}

	@Override
	public Object update(Object record, String creatorName) throws Exception {
		REnterpriseLoginHistory  vo = (REnterpriseLoginHistory ) record;
		
		REnterpriseLoginHistoryExample example = new REnterpriseLoginHistoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(vo.getGuid());
		
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return enterpriseLoginHistoryMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		return 0;
	}

	@Override
	public REnterpriseLoginHistory selectByAccountStoreNo(REnterpriseLoginHistory  vo) {
		REnterpriseLoginHistoryExample example = new REnterpriseLoginHistoryExample();
		example.setOrderByClause("retry_times asc");
		
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(vo.getAccount());
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		
		List<REnterpriseLoginHistory> list = enterpriseLoginHistoryMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public REnterpriseLoginHistory selectByLastModifierDate(REnterpriseLoginHistory vo) {
		REnterpriseLoginHistoryExample example = new REnterpriseLoginHistoryExample();
		example.setOrderByClause("modifier_date desc");
		
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(vo.getAccount());
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		criteria.andCreatorDateLessThan(new Date());
		
		List<REnterpriseLoginHistory> list = enterpriseLoginHistoryMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}
}
