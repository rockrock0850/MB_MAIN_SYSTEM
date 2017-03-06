package com.mb.main.system.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.BEnterpriseMapper;
import com.mb.main.system.model.BEnterprise;
import com.mb.main.system.model.BEnterpriseExample;
import com.mb.main.system.model.BEnterpriseExample.Criteria;
import com.mb.main.system.service.EnterpriseService;
import com.mb.main.system.utils.Constant;
import com.mb.main.system.utils.ShareTool;
import com.mb.main.system.vo.EnterpriseVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class EnterpriseServiceImpl implements EnterpriseService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BEnterpriseMapper enterpriseMapper;

	@Override
	public List<?> select() {
		return enterpriseMapper.selectByExample(new BEnterpriseExample());
	}

	@Override
	public BEnterprise selectByGuid(String guid) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(guid);
		
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<EnterpriseVO> selectVerifyStatus() throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andStatusEqualTo(10);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		List<EnterpriseVO> voList = new ArrayList<>();

		if(list != null){
			list.forEach(parent -> {
				EnterpriseVO vo = new EnterpriseVO();
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
	public List<EnterpriseVO> selectTryonStatus() throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andStatusEqualTo(30);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		List<EnterpriseVO> voList = new ArrayList<>();
		
		if(list != null){
			list.forEach(parent -> {
				EnterpriseVO vo = new EnterpriseVO();
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
	public List<EnterpriseVO> selectEffectiveStatus() throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andStatusEqualTo(60);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		List<EnterpriseVO> voList = new ArrayList<>();

		if(list != null){
			list.forEach(parent -> {
				EnterpriseVO vo = new EnterpriseVO();
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
	public List<EnterpriseVO> selectInvalidStatus() throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andStatusEqualTo(40);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		List<EnterpriseVO> voList = new ArrayList<>();

		if(list != null){
			list.forEach(parent -> {
				EnterpriseVO vo = new EnterpriseVO();
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
	public List<EnterpriseVO> selectFailStatus() throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria cirteria = example.createCriteria();
		cirteria.andStatusEqualTo(20);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		List<EnterpriseVO> voList = new ArrayList<>();

		if(list != null){
			list.forEach(parent -> {
				EnterpriseVO vo = new EnterpriseVO();
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
	public BEnterprise selectStoreNo(String storeNo) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(storeNo);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public BEnterprise selectUsedStoreNo(String storeNo) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(storeNo);
		criteria.andStatusNotEqualTo(1);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public BEnterprise selectDefaultStoreNo(String storeNo) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(storeNo);
		criteria.andAccountEqualTo("");
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<BEnterprise> selectByAgentsEffective(String guid) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andBAgentsGuidEqualTo(guid);
		criteria.andStatusEqualTo(60);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public List<BEnterprise> selectByAgentsTryon(String guid) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andBAgentsGuidEqualTo(guid);
		criteria.andStatusEqualTo(30);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public List<BEnterprise> selectByAgentsInvalid(String guid) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andBAgentsGuidEqualTo(guid);
		criteria.andStatusEqualTo(40);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}
	
	@Override
	public List<BEnterprise> selectByAgents(String guid) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andBAgentsGuidEqualTo(guid);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public List<BEnterprise> selectByAgentsFail(String guid) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andBAgentsGuidEqualTo(guid);
		criteria.andStatusEqualTo(20);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public BEnterprise selectByAccount(String account) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public BEnterprise selectByStoreNo(String storeNo) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(storeNo);
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Object create(Object record, String creatorName) {
		BEnterprise vo = (BEnterprise) record;
		vo.setGuid(UUID.randomUUID().toString());
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		
		return enterpriseMapper.insert(vo) > 0 ? vo : null;
	}

	@Override
	public Object update(Object record, String creatorName) {
		BEnterprise vo = (BEnterprise) record;
		
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andGuidEqualTo(vo.getGuid());
		
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return enterpriseMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public BEnterprise notUseToVerify(Object record, String creatorName) throws Exception {
		BEnterprise vo = (BEnterprise) record;
		
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		criteria.andStatusEqualTo(1);
		
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return enterpriseMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public BEnterprise verifyToTryon(Object record, String creatorName) throws Exception {
		BEnterprise vo = (BEnterprise) record;
		
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		criteria.andStatusEqualTo(10);

		vo.setStatus(30);
		vo.setCreatorGuid(vo.getGuid());
		vo.setCreatorName(creatorName);
		return enterpriseMapper.updateByExampleSelective(vo, example) > 0 ? vo : null;
	}

	@Override
	public BEnterprise verifyToFail(Object record, String creatorName) throws Exception {
		BEnterprise enterprise = (BEnterprise) record;
		
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(enterprise.getStoreNo());
		criteria.andStatusEqualTo(10);

		enterprise.setStatus(20);
		enterprise.setCreatorGuid(enterprise.getGuid());
		enterprise.setCreatorName(creatorName);
		return enterpriseMapper.updateByExampleSelective(enterprise, example) > 0 ? enterprise : null;
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}

	@Override
	public BEnterprise selectLoginInfo(BEnterprise vo) throws Exception {
		BEnterpriseExample example = new BEnterpriseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		criteria.andAccountEqualTo(vo.getAccount());
		
		List<BEnterprise> list = enterpriseMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}
}
