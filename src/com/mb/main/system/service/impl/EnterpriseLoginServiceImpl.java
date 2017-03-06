package com.mb.main.system.service.impl;

import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.main.system.mapper.VEnterpriseLoginMapper;
import com.mb.main.system.model.VEnterpriseLogin;
import com.mb.main.system.model.VEnterpriseLoginExample;
import com.mb.main.system.model.VEnterpriseLoginExample.Criteria;
import com.mb.main.system.service.EnterpriseLoginService;
import com.mb.main.system.utils.Constant;
import com.mb.main.system.vo.form.LoginEnterpriseVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class EnterpriseLoginServiceImpl implements EnterpriseLoginService{
	@Autowired
	private VEnterpriseLoginMapper enterpriseLoginMapper;
	
	@Override
	public List<?> select() throws Exception {
		return enterpriseLoginMapper.selectByExample(new VEnterpriseLoginExample());
	}

	@Override
	public VEnterpriseLogin selectByAccountPassword(LoginEnterpriseVO vo) throws Exception {
		VEnterpriseLoginExample example = new VEnterpriseLoginExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(vo.getAccount());
		
		/*
		 * 
		 */
		
		String data = vo.getPassword();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(Constant.IV.getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(Constant.KEY.getBytes(), "AES");
		Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ciper.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] doFinal = ciper.doFinal(data.getBytes());//data.toString()
		
		data = Base64.getEncoder().encodeToString(doFinal);
		
		criteria.andPasswdEqualTo(data);
		
		List<VEnterpriseLogin> list = enterpriseLoginMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public VEnterpriseLogin selectByGuid(String guid) {
		VEnterpriseLoginExample example = new VEnterpriseLoginExample();
		Criteria criteria = example.createCriteria();
		criteria.andPkEqualTo(guid);
		
		List<VEnterpriseLogin> list = enterpriseLoginMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
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

	@Override
	public VEnterpriseLogin selectByAccount(LoginEnterpriseVO vo) throws Exception {
		VEnterpriseLoginExample example = new VEnterpriseLoginExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(vo.getAccount());
		
		List<VEnterpriseLogin> list = enterpriseLoginMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public VEnterpriseLogin selectByLoginInfo(LoginEnterpriseVO vo) throws Exception {
		VEnterpriseLoginExample example = new VEnterpriseLoginExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		criteria.andAccountEqualTo(vo.getAccount());
		
		/*
		 * 
		 */
		
		String data = vo.getPassword();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(Constant.IV.getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(Constant.KEY.getBytes(), "AES");
		Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ciper.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] doFinal = ciper.doFinal(data.getBytes());//data.toString()
		
		data = Base64.getEncoder().encodeToString(doFinal);
		
		criteria.andPasswdEqualTo(data);
		
		List<VEnterpriseLogin> list = enterpriseLoginMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public VEnterpriseLogin selectByAccountStoreNo(LoginEnterpriseVO vo) throws Exception {
		VEnterpriseLoginExample example = new VEnterpriseLoginExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreNoEqualTo(vo.getStoreNo());
		criteria.andAccountEqualTo(vo.getAccount());
		
		List<VEnterpriseLogin> list = enterpriseLoginMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}
}
