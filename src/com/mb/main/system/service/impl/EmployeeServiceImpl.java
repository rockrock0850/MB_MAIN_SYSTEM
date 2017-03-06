package com.mb.main.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.BEnterpriseEmployeeMapper;
import com.mb.main.system.model.BEnterpriseEmployee;
import com.mb.main.system.model.BEnterpriseEmployeeExample;
import com.mb.main.system.model.BEnterpriseEmployeeExample.Criteria;
import com.mb.main.system.service.EmployeeService;
import com.mb.main.system.vo.EmployeeVO;

import lombok.extern.log4j.Log4j;

@Component
//@Transactional("transactionManager")
public class EmployeeServiceImpl implements EmployeeService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BEnterpriseEmployeeMapper employeeMapper;

	@Override
	public List<?> select() throws Exception {
		List<BEnterpriseEmployee> list = employeeMapper.selectByExample(new BEnterpriseEmployeeExample());

		List<EmployeeVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				EmployeeVO vo = new EmployeeVO();
				try {
					BeanUtils.copyProperties(vo, parent);
				} catch (Exception e) {
					log.error(e, e);
				}
				voList.add(vo);
			});
		}
		
		return voList.size() > 0 ? voList : null;
	}

	@Override
	public EmployeeVO selectByGuid(String guid)  throws Exception{
		BEnterpriseEmployeeExample example = new BEnterpriseEmployeeExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andGuidEqualTo(guid);
		
		List<BEnterpriseEmployee> list = employeeMapper.selectByExample(example);

		List<EmployeeVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				EmployeeVO vo = new EmployeeVO();
				try {
					BeanUtils.copyProperties(vo, parent);
				} catch (Exception e) {
					log.error(e, e);
				}
				voList.add(vo);
			});
		}
		
		return voList.size() > 0 ? voList.get(0) : null;
	}

	@Override
	public List<EmployeeVO> selectByEnterpriseGuid(String guid)  throws Exception{
		BEnterpriseEmployeeExample example = new BEnterpriseEmployeeExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andBEnterpriseGuidEqualTo(guid);
		
		List<BEnterpriseEmployee> list = employeeMapper.selectByExample(example);

		List<EmployeeVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				EmployeeVO vo = new EmployeeVO();
				try {
					BeanUtils.copyProperties(vo, parent);
				} catch (Exception e) {
					log.error(e, e);
				}
				voList.add(vo);
			});
		}
		
		return voList.size() > 0 ? voList : null;
	}

	@Override
	public BEnterpriseEmployee selectByAccount(String account)  throws Exception{
		BEnterpriseEmployeeExample example = new BEnterpriseEmployeeExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andAccountEqualTo(account);
		
		List<BEnterpriseEmployee> list = employeeMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Object selectByPrimaryKey(int id) throws Exception {
		return null;
	}

	@Override
	public Object create(Object record, String creatorName) throws Exception {
		BEnterpriseEmployee vo = (BEnterpriseEmployee) record;
		vo.setGuid(UUID.randomUUID().toString());
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		
		return employeeMapper.insert(vo) > 0 ? vo : null;
	}

	@Override
	public Object update(Object record, String creatorName) throws Exception {
		BEnterpriseEmployee vo = (BEnterpriseEmployee) record;
		
		BEnterpriseEmployeeExample example = new BEnterpriseEmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(vo.getGuid());
		
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return employeeMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		return 0;
	}

	@Override
	public String deleteByGuid(String guid)  throws Exception{
		BEnterpriseEmployeeExample example = new BEnterpriseEmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(guid);
		return employeeMapper.deleteByExample(example) > 0 ? guid : null;
	}

	@Override
	public BEnterpriseEmployee selectByLoginInfo(BEnterpriseEmployee vo) throws Exception {
		BEnterpriseEmployeeExample example = new BEnterpriseEmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(vo.getAccount());
		criteria.andBEnterpriseGuidEqualTo(vo.getbEnterpriseGuid());
		
		List<BEnterpriseEmployee> list = employeeMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}
}