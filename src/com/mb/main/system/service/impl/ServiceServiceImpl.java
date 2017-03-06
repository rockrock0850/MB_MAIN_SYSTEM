package com.mb.main.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.BServiceMapper;
import com.mb.main.system.model.BServiceExample;
import com.mb.main.system.service.ServiceService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class ServiceServiceImpl implements ServiceService{

	@Autowired
	private BServiceMapper bServiceMapper;

	@Override
	public List<?> select() throws Exception {
		return bServiceMapper.selectByExample(new BServiceExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) throws Exception {
		return null;
	}

	@Override
	public Object create(Object record, String creatorName) throws Exception {
		return null;
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
