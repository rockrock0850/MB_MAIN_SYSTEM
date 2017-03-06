package com.mb.main.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.BAgentsMapper;
import com.mb.main.system.model.BAgents;
import com.mb.main.system.model.BAgentsExample;
import com.mb.main.system.model.BAgentsExample.Criteria;
import com.mb.main.system.service.AgentsService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class AgentsServiceImpl implements AgentsService{

	@Autowired
	private BAgentsMapper bAgentsMapper;

	@Override
	public List<?> select() {
		return bAgentsMapper.selectByExample(new BAgentsExample());
	}

	@Override
	public BAgents selectByAccountOrSerialNo(BAgents vo) throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andAccountEqualTo(vo.getAccount());
		cirteria.andSerialNoEqualTo(vo.getSerialNo());
		example.or(cirteria);
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public BAgents selectByAccount(String account) throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andAccountEqualTo(account);
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public BAgents selectBySerialNo(String serialNo) throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andSerialNoEqualTo(serialNo);
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public BAgents selectDefault() throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andAccountEqualTo("everywhere");
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<BAgents> selectByGeneral() throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andBAgentsGuidIsNull();
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public List<BAgents> selectByNotGeneral() throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andBAgentsGuidIsNotNull();
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public List<BAgents> selectByAgentsGuid(String guid)  throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andBAgentsGuidEqualTo(guid);
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list == null ? new ArrayList<BAgents>() : list;
	}

	@Override
	public BAgents selectByGuid(String guid) throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andGuidEqualTo(guid);
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public BAgents selectByUniformNumber(String uniformNumber) throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andUniformNumberEqualTo(uniformNumber);
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Object create(Object record, String creatorName)  throws Exception {
		BAgents vo = (BAgents) record;
		vo.setGuid(UUID.randomUUID().toString());
		vo.setAccount(vo.getAccount());
		vo.setSerialNo(vo.getSerialNo());
		return bAgentsMapper.insert(vo) > 0 ? vo : null;
	}

	@Override
	public Object update(Object record, String creatorName) {
		BAgents vo = (BAgents) record;
		
		BAgentsExample example = new BAgentsExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(vo.getGuid());
		
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return bAgentsMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public Object selectByPrimaryKey(int id)  throws Exception {
		return null;
	}

	@Override
	public int deleteByPrimaryKey(String id)  throws Exception {
		return 0;
	}

	@Override
	public List<BAgents> selectByNotGeneral(String guid) throws Exception {
		BAgentsExample example = new BAgentsExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andBAgentsGuidEqualTo(guid);
		
		List<BAgents> list = bAgentsMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}
}
