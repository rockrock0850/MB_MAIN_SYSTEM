package com.mb.main.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.REmployeeLoginHistoryMapper;
import com.mb.main.system.model.REmployeeLoginHistory;
import com.mb.main.system.model.REmployeeLoginHistoryExample;
import com.mb.main.system.model.REmployeeLoginHistoryExample.Criteria;
import com.mb.main.system.model.REnterpriseLoginHistory;
import com.mb.main.system.model.REnterpriseLoginHistoryExample;
import com.mb.main.system.service.EmployeeLoginHistoryService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class EmployeeLoginHistoryServiceImpl implements EmployeeLoginHistoryService{
	@Autowired
	private REmployeeLoginHistoryMapper employeeLoginHistoryMapper;
	
	@Override
	public List<?> select() throws Exception {
		return null;
	}

	@Override
	public REmployeeLoginHistory selectByLastRetryTimes(String pk) {
		REmployeeLoginHistoryExample example = new REmployeeLoginHistoryExample();
		example.setOrderByClause("retry_times desc");
		
		Criteria criteria = example.createCriteria();
		criteria.andBEnterpriseEmployeeGuidEqualTo(pk);
		
		List<REmployeeLoginHistory> list = employeeLoginHistoryMapper.selectByExample(example);
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
		REmployeeLoginHistory vo = (REmployeeLoginHistory) record;
		vo.setGuid(StringUtils.isBlank(vo.getGuid()) ? UUID.randomUUID().toString() : vo.getGuid());
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		
		return employeeLoginHistoryMapper.insert(vo) > 0 ? vo : null;
	}

	@Override
	public Object update(Object record, String creatorName) throws Exception {
		REmployeeLoginHistory  vo = (REmployeeLoginHistory ) record;
		
		REmployeeLoginHistoryExample example = new REmployeeLoginHistoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(vo.getGuid());
		
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return employeeLoginHistoryMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		return 0;
	}

	@Override
	public REmployeeLoginHistory selectByAccountStoreNo(REmployeeLoginHistory vo) {
		REmployeeLoginHistoryExample example = new REmployeeLoginHistoryExample();
		example.setOrderByClause("retry_times asc");
		
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(vo.getAccount());
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		
		List<REmployeeLoginHistory> list = employeeLoginHistoryMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public REmployeeLoginHistory selectByLastModifierDate(REmployeeLoginHistory vo) {
		REmployeeLoginHistoryExample example = new REmployeeLoginHistoryExample();
		example.setOrderByClause("modifier_date desc");
		
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(vo.getAccount());
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		criteria.andCreatorDateLessThan(new Date());
		
		List<REmployeeLoginHistory> list = employeeLoginHistoryMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}
}
