package com.mb.main.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mb.main.system.base.BaseController;
import com.mb.main.system.exception.LogicException;
import com.mb.main.system.model.BAgents;
import com.mb.main.system.model.BEnterprise;
import com.mb.main.system.model.BEnterpriseEmployee;
import com.mb.main.system.model.BIndustry;
import com.mb.main.system.model.BService;
import com.mb.main.system.model.DEnterpriseService;
import com.mb.main.system.model.REnterprisePasswordHistory;
import com.mb.main.system.service.AgentsService;
import com.mb.main.system.service.EmployeeService;
import com.mb.main.system.service.EnterprisePasswordHistoryService;
import com.mb.main.system.service.EnterpriseService;
import com.mb.main.system.service.EnterpriseServiceService;
import com.mb.main.system.service.IndustryService;
import com.mb.main.system.service.ServiceService;
import com.mb.main.system.utils.Constant;
import com.mb.main.system.utils.Constant.Status;
import com.mb.main.system.utils.ShareTool;
import com.mb.main.system.vo.EmployeeVO;
import com.mb.main.system.vo.EnterpriseVO;
import com.mb.main.system.vo.ResponseVO;
import com.mb.main.system.vo.form.EffectiveEnterpriseVO;
import com.mb.main.system.vo.form.FailEnterpriseVO;
import com.mb.main.system.vo.form.InvalidEnterpriseVO;
import com.mb.main.system.vo.form.NewAgentsStoreVO;
import com.mb.main.system.vo.form.NewAgentsVO;
import com.mb.main.system.vo.form.NewGeneralVO;
import com.mb.main.system.vo.form.TryonEnterpriseVO;
import com.mb.main.system.vo.form.UpdateAgentsStoreVO;
import com.mb.main.system.vo.form.UpdateAgentsVO;
import com.mb.main.system.vo.form.UpdateGeneralVO;
import com.mb.main.system.vo.form.VerifyEnterpriseVO;

import lombok.extern.log4j.Log4j;


@Log4j
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/MainSystem")
public class MainSystemController extends BaseController{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private IndustryService industryService;
	@Autowired
	private EnterprisePasswordHistoryService enterprisePasswordHistoryService;
	@Autowired
	private AgentsService agentsService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private EnterpriseServiceService enterpriseServiceService;
	@Autowired
	private EmployeeService employeeService;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object index(HttpServletRequest request) throws Exception{
		return new ModelAndView(Constant.SYSTEM_INDEX);
	}
	
	/*
	 * 企業審核
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public @ResponseBody Object verify(HttpServletRequest request) throws Exception{
		HashMap<String, Object> verifyMap = new HashMap<String, Object>();
	    List<EnterpriseVO> verifyList = (List<EnterpriseVO>) enterpriseService.selectVerifyStatus();
	    List<BIndustry> industryList = (List<BIndustry>) industryService.select();
	    List<BAgents> generalList = (List<BAgents>) agentsService.selectByGeneral();
	    
	    if(verifyList != null && industryList != null){
		    verifyList.forEach(bEnterprise -> {
		    	industryList.forEach(bIndustry -> {
		    		if(StringUtils.equals(bEnterprise.getbIndustryGuid(), bIndustry.getGuid())){
		    			bEnterprise.setIndustry(bIndustry.getName());
		    		}
		    	});
		    });	
	    }

	    verifyMap.put("count", verifyList == null ? null : verifyList.size());
	    verifyMap.put("verifyList", verifyList);
	    verifyMap.put("industryList", industryList);
	    verifyMap.put("generalList", generalList);
	    
		return new ModelAndView(Constant.SYSTEM_VERIFY, "verifyMap", verifyMap);
	}
	
	@RequestMapping(value = "/verifyTofail", method = RequestMethod.POST)
	public @ResponseBody Object verifyTofail(HttpServletRequest request, @RequestBody EnterpriseVO requestVO) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		BEnterprise enterprise = enterpriseService.selectByGuid(requestVO.getGuid());
		enterprise.setRemark(requestVO.getRemark());
		
		enterprise = enterpriseService.verifyToFail(enterprise, "Adam");
		if(enterprise == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG001);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/verifyToTryon", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody Object verifyToTryon(HttpServletRequest request, @RequestBody EnterpriseVO requestVO) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		BEnterprise vo = enterpriseService.selectByGuid(requestVO.getGuid());
		if(vo == null){
			responseVO.setMessage("無此筆資料，請檢察資料庫是否已被異動");
			return responseVO;
		}else if(StringUtils.isBlank(vo.getStoreNo()) || StringUtils.isBlank(vo.getAccount())){
			responseVO.setMessage("特店編號或企業帳號不可為空");
			return responseVO;
		}
		
		if(enterpriseService.verifyToTryon(vo, "Adam") == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG001);
		}
		
		/*
		 * 建立帳號所屬服務
		 */
		
		List<BService> serviceList = (List<BService>) serviceService.select();
		if(serviceList != null){
			serviceList.forEach(service -> {
				DEnterpriseService vo2 = new DEnterpriseService();
				vo2.setbServiceGuid(service.getGuid());
				vo2.setbEnterpriseGuid(requestVO.getGuid());
				try {
					enterpriseServiceService.create(vo2, "Adam");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
//		REnterprisePasswordHistory vo3 = enterprisePasswordHistoryService.selectByEnterpriseGuid(requestVO.getGuid());
//		sendEmail(responseVO, vo, ShareTool.decrypt(Constant.IV, Constant.KEY, vo3.getPasswd()));

		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo);
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/editVerifyDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object editVerifyDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String,Object> verifyMap = new HashMap<String, Object>();
		
		BEnterprise enterprise = enterpriseService.selectByGuid(guid);
		verifyMap.put("enterprise", enterprise);

		BAgents agents = agentsService.selectByGuid(enterprise.getbAgentsGuid());
		if(StringUtils.isBlank(agents.getbAgentsGuid())){	
			verifyMap.put("general", agents);
		}else{
			verifyMap.put("notGeneral", agents);
			agents = agentsService.selectByGuid(agents.getbAgentsGuid());
			verifyMap.put("general", agents);
			List<BAgents> notGeneralList = agentsService.selectByNotGeneral(agents.getGuid());
			verifyMap.put("notGeneralList", notGeneralList);
		}

		REnterprisePasswordHistory enterprisePasswordHistory = enterprisePasswordHistoryService.selectByEnterpriseGuid(guid);
		enterprisePasswordHistory.setPasswd(ShareTool.decrypt(Constant.IV, Constant.KEY, enterprisePasswordHistory.getPasswd()));
		verifyMap.put("enterprisePasswordHistory", enterprisePasswordHistory);
		
		return verifyMap;
	}
	
	@RequestMapping(value = "/updateVerify", method = RequestMethod.POST)
	public @ResponseBody Object updateVerify(HttpServletRequest request, @RequestBody @Valid VerifyEnterpriseVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BEnterprise enterprise = enterpriseService.selectByAccount(requestVO.getAccount());
		if(enterprise != null && !StringUtils.equals(enterprise.getAccount(), requestVO.getAccount())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG005.getMessage());
			return responseVO;
		}
		
		enterprise = enterpriseService.selectByStoreNo(requestVO.getStoreNo());
		if(enterprise != null && !StringUtils.equals(enterprise.getStoreNo(), requestVO.getStoreNo())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG006.getMessage());
			return responseVO;
		}
		
		requestVO.setStatus(enterprise.getStatus());
		requestVO.setbAgentsGuid(StringUtils.isNotBlank(requestVO.getbAgentsGuidNotGeneral()) ? 
				requestVO.getbAgentsGuidNotGeneral() : requestVO.getbAgentsGuid());
		enterprise = (BEnterprise) enterpriseService.update(requestVO, "Adam");
		if(enterprise == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG001);
		}
		
		REnterprisePasswordHistory vo3 = enterprisePasswordHistoryService.selectByEnterpriseGuid(requestVO.getGuid());
		vo3.setbEnterpriseGuid(requestVO.getGuid());
		vo3.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPassword()));
		vo3 = (REnterprisePasswordHistory) enterprisePasswordHistoryService.update(vo3, "Adam");
		if(vo3 == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG004);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 企業試用
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tryon", method = RequestMethod.GET)
	public @ResponseBody Object tryon(HttpServletRequest request) throws Exception{
		HashMap<String, Object> tryonMap = new HashMap<String, Object>();
	    List<EnterpriseVO> tryonList = (List<EnterpriseVO>) enterpriseService.selectTryonStatus();
	    List<BIndustry> industryList = (List<BIndustry>) industryService.select();
	    List<BAgents> generalList = (List<BAgents>) agentsService.selectByGeneral();
	    List<BAgents> notGeneralList = (List<BAgents>) agentsService.selectByNotGeneral();
	    
	    if(tryonList != null && industryList != null){
		    tryonList.forEach(bEnterprise -> {
		    	industryList.forEach(bIndustry -> {
		    		if(StringUtils.equals(bEnterprise.getbIndustryGuid(), bIndustry.getGuid())){
		    			bEnterprise.setIndustry(bIndustry.getName());
		    		}
		    	});
		    });	
	    }
	    
	    tryonMap.put("tryonList", tryonList);
	    tryonMap.put("count", tryonList == null ? null : tryonList.size());
	    tryonMap.put("industryList", industryList);
	    tryonMap.put("generalList", generalList);
	    tryonMap.put("notGeneralList", notGeneralList);
		return new ModelAndView(Constant.SYSTEM_TRYON, "tryonMap", tryonMap);
	}

	@RequestMapping(value = "/tryonToEffective", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody Object tryonToEffective(HttpServletRequest request, @RequestBody TryonEnterpriseVO requestVO) 
			throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		BEnterprise vo = enterpriseService.selectByGuid(requestVO.getGuid());
		vo.setStatus(vo == null ? vo.getStatus() : 60);
		responseVO.setResult(enterpriseService.update(vo, "Adam"));
		
		txManager.commit(status);
		return responseVO;
	}

	@RequestMapping(value = "/tryonToInvalid", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody Object tryonToInvalid(HttpServletRequest request, @RequestBody TryonEnterpriseVO requestVO) 
			throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		BEnterprise enterprise = enterpriseService.selectByGuid(requestVO.getGuid());
		enterprise.setStatus(enterprise == null ? enterprise.getStatus() : 40);
		enterprise.setRemark(requestVO.getRemark());
		enterpriseService.update(enterprise, "Adam");
		responseVO.setResult(enterpriseService.update(enterprise, "Adam"));
		
		List<EmployeeVO> employeeList = employeeService.selectByEnterpriseGuid(enterprise.getGuid());
		for (EmployeeVO vo : employeeList) {
			vo.setStatus(10);
			BEnterpriseEmployee employee = (BEnterpriseEmployee) employeeService.update(vo, "adam");
			if(employee == null){
				throw new LogicException(Status.UPDATE_EMPLOYEE_MSG003);
			}
		}
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/editTryonDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object editTryonDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String, Object> tryonMap = new HashMap<String, Object>();
		
		BEnterprise enterprise = enterpriseService.selectByGuid(guid);
		tryonMap.put("enterprise", enterprise);

		BAgents agents = agentsService.selectByGuid(enterprise.getbAgentsGuid());
		if(StringUtils.isBlank(agents.getbAgentsGuid())){	
			tryonMap.put("general", agents);
		}else{
			tryonMap.put("notGeneral", agents);
			agents = agentsService.selectByGuid(agents.getbAgentsGuid());
			tryonMap.put("general", agents);
			List<BAgents> notGeneralList = agentsService.selectByNotGeneral(agents.getGuid());
			tryonMap.put("notGeneralList", notGeneralList);
		}

		REnterprisePasswordHistory vo3 = enterprisePasswordHistoryService.selectByEnterpriseGuid(guid);
		vo3.setPasswd(ShareTool.decrypt(Constant.IV, Constant.KEY, vo3.getPasswd()));
		tryonMap.put("enterprisePasswordHistory", vo3);
		
		return tryonMap;
	}
	
	@RequestMapping(value = "/updateTryon", method = RequestMethod.POST)
	public @ResponseBody Object updateTryon(HttpServletRequest request, @RequestBody @Valid TryonEnterpriseVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BEnterprise vo = enterpriseService.selectByGuid(requestVO.getGuid());

		BEnterprise vo2 = enterpriseService.selectByStoreNo(requestVO.getStoreNo());
		if(vo != null && !StringUtils.equals(vo.getStoreNo(), requestVO.getStoreNo())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG006.getMessage());
			return responseVO;
		}
		
		vo2 = enterpriseService.selectByAccount(requestVO.getAccount());
		if(vo2 != null && !StringUtils.equals(vo.getAccount(), requestVO.getAccount())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG005.getMessage());
			return responseVO;
		}
		
		requestVO.setStatus(vo.getStatus());
		requestVO.setbAgentsGuid(StringUtils.isNotBlank(requestVO.getbAgentsGuidNotGeneral()) ? 
				requestVO.getbAgentsGuidNotGeneral() : requestVO.getbAgentsGuid());
		vo = (BEnterprise) enterpriseService.update(requestVO, "Adam");
		if(vo == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG001);
		}
		
		REnterprisePasswordHistory vo3 = enterprisePasswordHistoryService.selectByEnterpriseGuid(requestVO.getGuid());
		vo3.setbEnterpriseGuid(requestVO.getGuid());
		vo3.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPassword()));
		vo3 = (REnterprisePasswordHistory) enterprisePasswordHistoryService.update(vo3, "Adam");
		if(vo3 == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG004);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 企業有效
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/effective", method = RequestMethod.GET)
	public @ResponseBody Object effective(HttpServletRequest request) throws Exception{
		HashMap<String, Object> effectiveMap = new HashMap<String, Object>();
	    List<EnterpriseVO> effectiveList = (List<EnterpriseVO>) enterpriseService.selectEffectiveStatus();
	    List<BIndustry> industryList = (List<BIndustry>) industryService.select();
	    List<BAgents> generalList = (List<BAgents>) agentsService.selectByGeneral();
	    List<BAgents> notGeneralList = (List<BAgents>) agentsService.selectByNotGeneral();

	    if(effectiveList != null && industryList != null){
		    effectiveList.forEach(bEnterprise -> {
		    	industryList.forEach(bIndustry -> {
		    		if(StringUtils.equals(bEnterprise.getbIndustryGuid(), bIndustry.getGuid())){
		    			bEnterprise.setIndustry(bIndustry.getName());
		    		}
		    	});
		    });
		    
		    effectiveList.forEach(vo -> vo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(vo.getCreatorDate())));
	    }

	    effectiveMap.put("effectiveList", effectiveList);
	    effectiveMap.put("industryList", industryList);
	    effectiveMap.put("generalList", generalList);
	    effectiveMap.put("notGeneralList", notGeneralList);
		return new ModelAndView(Constant.SYSTEM_EFFECTIVE, "effectiveMap", effectiveMap);
	}
	
	@RequestMapping(value = "/editEffetiveDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object editEffetiveDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String,Object> effectiveMap = new HashMap<String, Object>();

		BEnterprise enterprise = enterpriseService.selectByGuid(guid);
		effectiveMap.put("enterprise", enterprise);

		BAgents agents = agentsService.selectByGuid(enterprise.getbAgentsGuid());
		if(StringUtils.isBlank(agents.getbAgentsGuid())){	
			effectiveMap.put("general", agents);
		}else{
			effectiveMap.put("notGeneral", agents);
			agents = agentsService.selectByGuid(agents.getbAgentsGuid());
			effectiveMap.put("general", agents);
			List<BAgents> notGeneralList = agentsService.selectByNotGeneral(agents.getGuid());
			effectiveMap.put("notGeneralList", notGeneralList);
		}

		REnterprisePasswordHistory vo3 = enterprisePasswordHistoryService.selectByEnterpriseGuid(guid);
		vo3.setPasswd(ShareTool.decrypt(Constant.IV, Constant.KEY, vo3.getPasswd()));
		effectiveMap.put("enterprisePasswordHistory", vo3);
		
		return effectiveMap;
	}
	
	@RequestMapping(value = "/updateEffective", method = RequestMethod.POST)
	public @ResponseBody Object updateEffective(HttpServletRequest request, @RequestBody @Valid EffectiveEnterpriseVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BEnterprise enterprise = enterpriseService.selectByGuid(requestVO.getGuid());
		if(enterprise != null && !StringUtils.equals(enterprise.getStoreNo(), requestVO.getStoreNo())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG006.getMessage());
			return responseVO;
		}
		
		BEnterprise enterprise2 = enterpriseService.selectByAccount(requestVO.getAccount());
		if(enterprise2 != null && !StringUtils.equals(enterprise.getAccount(), requestVO.getAccount())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG005.getMessage());
			return responseVO;
		}
		
		requestVO.setStatus(enterprise.getStatus());
		requestVO.setbAgentsGuid(StringUtils.isNotBlank(requestVO.getbAgentsGuidNotGeneral()) ? 
				requestVO.getbAgentsGuidNotGeneral() : requestVO.getbAgentsGuid());
		enterprise = (BEnterprise) enterpriseService.update(requestVO, "Adam");
		if(enterprise == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG001);
		}
		
		REnterprisePasswordHistory enterprisePasswordHistory = 
				enterprisePasswordHistoryService.selectByEnterpriseGuid(requestVO.getGuid());
		enterprisePasswordHistory.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPassword()));
		enterprisePasswordHistory = (REnterprisePasswordHistory) 
				enterprisePasswordHistoryService.update(enterprisePasswordHistory, "Adam");
		if(enterprise2 == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG004);
		}

		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}

	/*
	 * 企業無效
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/invalid", method = RequestMethod.GET)
	public @ResponseBody Object invalid(HttpServletRequest request) throws Exception{
		HashMap<String, Object> invalidMap = new HashMap<String, Object>();
	    List<EnterpriseVO> invalidList = (List<EnterpriseVO>) enterpriseService.selectInvalidStatus();
	    List<BIndustry> industryList = (List<BIndustry>) industryService.select();
	    List<BAgents> generalList = (List<BAgents>) agentsService.selectByGeneral();
	    List<BAgents> notGeneralList = (List<BAgents>) agentsService.selectByNotGeneral();

	    if(invalidList != null && industryList != null){
		    invalidList.forEach(bEnterprise -> {
		    	industryList.forEach(bIndustry -> {
		    		if(StringUtils.equals(bEnterprise.getbIndustryGuid(), bIndustry.getGuid())){
		    			bEnterprise.setIndustry(bIndustry.getName());
		    		}
		    	});
		    });
		    
		    invalidList.forEach(vo -> vo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(vo.getCreatorDate())));
	    }
	    
	    invalidMap.put("invalidList", invalidList);
	    invalidMap.put("industryList", industryList);
	    invalidMap.put("generalList", generalList);
	    invalidMap.put("notGeneralList", notGeneralList);
		return new ModelAndView(Constant.SYSTEM_INVALID, "invalidMap", invalidMap);
	}
	
	@RequestMapping(value = "/editInvalidDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object editInvalidDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String,Object> invalidMap = new HashMap<String, Object>();

		BEnterprise enterprise = enterpriseService.selectByGuid(guid);
		invalidMap.put("enterprise", enterprise);

		BAgents agents = agentsService.selectByGuid(enterprise.getbAgentsGuid());
		if(StringUtils.isBlank(agents.getbAgentsGuid())){	
			invalidMap.put("general", agents);
		}else{
			invalidMap.put("notGeneral", agents);
			agents = agentsService.selectByGuid(agents.getbAgentsGuid());
			invalidMap.put("general", agents);
			List<BAgents> notGeneralList = agentsService.selectByNotGeneral(agents.getGuid());
			invalidMap.put("notGeneralList", notGeneralList);
		}

		REnterprisePasswordHistory enterprisePasswordHistory = enterprisePasswordHistoryService.selectByEnterpriseGuid(guid);
		enterprisePasswordHistory.setPasswd(ShareTool.decrypt(Constant.IV, Constant.KEY, enterprisePasswordHistory.getPasswd()));
		invalidMap.put("enterprisePasswordHistory", enterprisePasswordHistory);
		
		return invalidMap;
	}
	
	@RequestMapping(value = "/updateInvalid", method = RequestMethod.POST)
	public @ResponseBody Object updateInvalid(HttpServletRequest request, @RequestBody @Valid InvalidEnterpriseVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BEnterprise enterprise = enterpriseService.selectByGuid(requestVO.getGuid());
		if(enterprise != null && !StringUtils.equals(enterprise.getStoreNo(), requestVO.getStoreNo())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG006.getMessage());
			return responseVO;
		}
		
		BEnterprise enterprise2 = enterpriseService.selectByAccount(requestVO.getAccount());
		if(enterprise2 != null && !StringUtils.equals(enterprise.getAccount(), requestVO.getAccount())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG005.getMessage());
			return responseVO;
		}
		
		requestVO.setStatus(enterprise.getStatus());
		requestVO.setbAgentsGuid(StringUtils.isNotBlank(requestVO.getbAgentsGuidNotGeneral()) ? 
				requestVO.getbAgentsGuidNotGeneral() : requestVO.getbAgentsGuid());
		enterprise = (BEnterprise) enterpriseService.update(requestVO, "Adam");
		if(enterprise == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG001);
		}
		
		REnterprisePasswordHistory enterprisePasswordHistory = 
				enterprisePasswordHistoryService.selectByEnterpriseGuid(requestVO.getGuid());
		enterprisePasswordHistory.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPassword()));
		enterprisePasswordHistory = (REnterprisePasswordHistory) 
				enterprisePasswordHistoryService.update(enterprisePasswordHistory, "Adam");
		if(enterprisePasswordHistory == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG004);
		}

		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}

	/*
	 * 企業不通過
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fail", method = RequestMethod.GET)
	public @ResponseBody Object fail(HttpServletRequest request) throws Exception{
		HashMap<String, Object> failMap = new HashMap<String, Object>();
	    List<EnterpriseVO> failList = (List<EnterpriseVO>) enterpriseService.selectFailStatus();
	    List<BIndustry> industryList = (List<BIndustry>) industryService.select();
	    List<BAgents> generalList = (List<BAgents>) agentsService.selectByGeneral();
	    List<BAgents> notGeneralList = (List<BAgents>) agentsService.selectByNotGeneral();

	    if(failList != null && industryList != null){
		    failList.forEach(bEnterprise -> {
		    	industryList.forEach(bIndustry -> {
		    		if(StringUtils.equals(bEnterprise.getbIndustryGuid(), bIndustry.getGuid())){
		    			bEnterprise.setIndustry(bIndustry.getName());
		    		}
		    	});
		    });
		    
		    failList.forEach(vo -> vo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(vo.getCreatorDate())));
	    }
	    
	    failMap.put("failList", failList);
	    failMap.put("industryList", industryList);
	    failMap.put("generalList", generalList);
	    failMap.put("notGeneralList", notGeneralList);
		return new ModelAndView(Constant.SYSTEM_FAIL, "failMap", failMap);
	}
	
	@RequestMapping(value = "/editFailDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object editFailDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String,Object> failMap = new HashMap<String, Object>();

		BEnterprise enterprise = enterpriseService.selectByGuid(guid);
		failMap.put("enterprise", enterprise);

		BAgents agents = agentsService.selectByGuid(enterprise.getbAgentsGuid());
		if(StringUtils.isBlank(agents.getbAgentsGuid())){	
			failMap.put("general", agents);
		}else{
			failMap.put("notGeneral", agents);
			agents = agentsService.selectByGuid(agents.getbAgentsGuid());
			failMap.put("general", agents);
			List<BAgents> notGeneralList = agentsService.selectByNotGeneral(agents.getGuid());
			failMap.put("notGeneralList", notGeneralList);
		}

		REnterprisePasswordHistory vo3 = enterprisePasswordHistoryService.selectByEnterpriseGuid(guid);
		vo3.setPasswd(ShareTool.decrypt(Constant.IV, Constant.KEY, vo3.getPasswd()));
		failMap.put("enterprisePasswordHistory", vo3);
		
		return failMap;
	}
	
	@RequestMapping(value = "/updatefail", method = RequestMethod.POST)
	public @ResponseBody Object updatefail(HttpServletRequest request, @RequestBody @Valid FailEnterpriseVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BEnterprise enterprise = enterpriseService.selectByGuid(requestVO.getGuid());
		if(enterprise != null && !StringUtils.equals(enterprise.getStoreNo(), requestVO.getStoreNo())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG006.getMessage());
			return responseVO;
		}
		
		BEnterprise enterprise2 = enterpriseService.selectByAccount(requestVO.getAccount());
		if(enterprise2 != null && !StringUtils.equals(enterprise.getAccount(), requestVO.getAccount())){
			responseVO.setMessage(Status.UPDATE_ENTERPRISE_MSG005.getMessage());
			return responseVO;
		}
		
		requestVO.setStatus(enterprise.getStatus());
		requestVO.setbAgentsGuid(StringUtils.isNotBlank(requestVO.getbAgentsGuidNotGeneral()) ? 
				requestVO.getbAgentsGuidNotGeneral() : requestVO.getbAgentsGuid());
		enterprise = (BEnterprise) enterpriseService.update(requestVO, "Adam");
		if(enterprise == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG001);
		}
		
		REnterprisePasswordHistory enterprisePasswordHistory = enterprisePasswordHistoryService.selectByEnterpriseGuid(requestVO.getGuid());
		enterprisePasswordHistory.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPassword()));
		enterprisePasswordHistory = (REnterprisePasswordHistory) enterprisePasswordHistoryService.update(enterprisePasswordHistory, "Adam");
		if(enterprisePasswordHistory == null){
			throw new LogicException(Status.UPDATE_ENTERPRISE_MSG004);
		}

		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/general", method = RequestMethod.GET)
	public @ResponseBody Object general(HttpServletRequest request) throws Exception{
		HashMap<String, Object> generalMap = new HashMap<String, Object>();
	    List<BAgents> generalList = (List<BAgents>) agentsService.selectByGeneral();

    	/*
    	 * 排序
    	 */
	    
	    if(generalList != null){
			Collections.sort(generalList, new Comparator<BAgents>() {
	            public int compare(BAgents o1, BAgents o2) {
	                return o2.getCompanyName().compareTo(o1.getCompanyName());
	            }
	        });
	    	
	    }

	    List<BEnterprise> enterpriseList = (List<BEnterprise>) enterpriseService.select();
    	List<Object> countList = new ArrayList<>();

	    /*
	     * 試用帳號數量
	     */
	    
	    HashMap<String, Object> tryonCountMap = new HashMap<>();
	    if(generalList != null && enterpriseList != null){
	    	for (BAgents general : generalList) {
	        	for (BEnterprise enterprise : enterpriseList) {
		    		if(StringUtils.equals(general.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 30){
		    			countList.add(enterprise);
		    		}
	    		}
	        	tryonCountMap.put(general.getGuid(), (countList == null ? 0 : countList.size()));
	        	countList.clear();
			}
	    }else{
	    	for (BAgents general : generalList) {
	    		tryonCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
	    
	    /*
	     * 正式帳號數量
	     */
	    
	    HashMap<String, Object> effectiveCountMap = new HashMap<>();
	    if(generalList != null && enterpriseList != null){
	    	for (BAgents general : generalList) {
	        	for (BEnterprise enterprise : enterpriseList) {
		    		if(StringUtils.equals(general.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 60){
		    			countList.add(enterprise);
		    		}
	    		}
	        	effectiveCountMap.put(general.getGuid(), (countList == null ? 0 : countList.size()));
	        	countList.clear();
			}
	    }else{
	    	for (BAgents general : generalList) {
	    		effectiveCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
	    
	    /*
	     * 無效帳號數量
	     */
	    
	    HashMap<String, Object> invalidCountMap = new HashMap<>();
	    if(generalList != null && enterpriseList != null){
	    	for (BAgents general : generalList) {
	        	for (BEnterprise enterprise : enterpriseList) {
		    		if(StringUtils.equals(general.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 40){
		    			countList.add(enterprise);
		    		}
	    		}
	        	invalidCountMap.put(general.getGuid(), (countList == null ? 0 : countList.size()));
	        	countList.clear();
			}
	    }else{
	    	for (BAgents general : generalList) {
	    		invalidCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
    	
	    List<BAgents> notGeneralList = (List<BAgents>) agentsService.selectByNotGeneral();
	    
	    /*
	     * 代理商數量
	     */
	    
	    HashMap<String, Object> agentsCountMap = new HashMap<>();
	    if(generalList != null && notGeneralList != null){
	    	for (BAgents general : generalList) {
	        	for (BAgents notGeneral : notGeneralList) {
		    		if(StringUtils.equals(general.getGuid(), notGeneral.getbAgentsGuid())){
		    			countList.add(notGeneral);
		    		}
	    		}
	        	agentsCountMap.put(general.getGuid(), (countList == null ? 0 : countList.size()));
	        	countList.clear();
			}
    	}else{
	    	for (BAgents general : generalList) {
	        	agentsCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
	    
	    generalMap.put("generalList", generalList);
	    generalMap.put("tryonCountMap", tryonCountMap);
	    generalMap.put("effectiveCountMap", effectiveCountMap);
	    generalMap.put("invalidCountMap", invalidCountMap);
	    generalMap.put("agentsCountMap", agentsCountMap);
	    
		return new ModelAndView(Constant.SYSTEM_AGENT_GENERAL, "generalMap", generalMap);
	}
	
	@RequestMapping(value = "/newGeneral", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newGeneral(HttpServletRequest request, HttpSession session, @RequestBody @Valid NewGeneralVO 
			requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BAgents vo = agentsService.selectByAccountOrSerialNo(requestVO);
		if(vo != null){
			responseVO.setMessage(Status.CREATE_AGENTS_GENERAL_MSG001.getMessage());
			return responseVO;
		}
		
		vo = (BAgents) agentsService.create(requestVO, "Adam");
		if(vo == null){
			throw new LogicException(Status.CREATE_AGENTS_GENERAL_MSG002);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/editGeneralDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object editGeneralDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String,Object> generalMap = new HashMap<String, Object>();

		BAgents vo = agentsService.selectByGuid(guid);
		vo.setPasswd(ShareTool.decrypt(Constant.IV, Constant.KEY, vo.getPasswd()));
		generalMap.put("agents", vo);
		
		return generalMap;
	}
	
	@RequestMapping(value = "/updateGeneral", method = RequestMethod.POST)
	public @ResponseBody Object updateGeneral(HttpServletRequest request, @RequestBody @Valid UpdateGeneralVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BAgents vo = agentsService.selectByGuid(requestVO.getGuid());

		BAgents vo2 = agentsService.selectBySerialNo(requestVO.getSerialNo() == null ? "" : requestVO.getSerialNo());
		if(vo2 != null && !StringUtils.equals(vo.getSerialNo(), requestVO.getSerialNo())){
			throw new LogicException(Status.UPDATE_AGENTS_GENERAL_MSG002);
		}
		
		vo2 = agentsService.selectByAccount(requestVO.getAccount());
		if(vo2 != null && !StringUtils.equals(vo.getAccount(), requestVO.getAccount() == null ? "" : requestVO.getAccount())){
			throw new LogicException(Status.UPDATE_AGENTS_GENERAL_MSG003);
		}
		
		vo2 = agentsService.selectByUniformNumber(requestVO.getUniformNumber());
		if(vo2 != null && !StringUtils.equals(vo.getUniformNumber(), 
				requestVO.getUniformNumber() == null ? "" : requestVO.getUniformNumber())){
			throw new LogicException(Status.UPDATE_AGENTS_GENERAL_MSG004);
		}
		
		requestVO.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPasswd()));
		vo2 = (BAgents) agentsService.update(requestVO, "Adam");
		if(vo2 == null){
			throw new LogicException(Status.UPDATE_AGENTS_GENERAL_MSG001);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/angentsView", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object angentsView(HttpServletRequest request) throws Exception{
		return agentsService.selectByNotGeneral();
	}
	
	@RequestMapping(value = "/angentsView/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object angentsView(HttpServletRequest request, @PathVariable String guid) throws Exception{
		return agentsService.selectByAgentsGuid(guid);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/agents", method = RequestMethod.GET)
	public @ResponseBody Object agents(HttpServletRequest request) throws Exception{
		HashMap<String, Object> agentsMap = new HashMap<String, Object>();
	    List<BAgents> agentsList = (List<BAgents>) agentsService.selectByNotGeneral();

    	/*
    	 * 排序
    	 */
	    if(agentsList != null){
			Collections.sort(agentsList, new Comparator<BAgents>() {
	            public int compare(BAgents o1, BAgents o2) {
	                return (o1.getbAgentsGuid() == null ? "" : o1.getbAgentsGuid()).compareTo(
	                		(o2.getbAgentsGuid() == null ? "" : o2.getbAgentsGuid()));
	            }
	        });
	    }
		
	    /*
	     * 總代理商清單
	     */
	    
	    List<BAgents> generalList = (List<BAgents>) agentsService.selectByGeneral();
	    if(agentsList != null && generalList != null){
		    generalList.forEach(general -> {
		    	agentsList.forEach(agents -> {
			    	if(StringUtils.equals(general.getGuid(), agents.getbAgentsGuid())){
			    		agentsMap.put(agents.getGuid(), general.getCompanyName());
			    	}
		    	});
		    });
	    }

	    List<BEnterprise> enterpriseList = (List<BEnterprise>) enterpriseService.select();
    	List<Object> countList = new ArrayList<>();
	    
	    /*
	     * 正式帳號數量
	     */
	    
	    HashMap<String, Object> effectiveCountMap = new HashMap<>();
	    if(agentsList != null && enterpriseList != null){
	    	for (BAgents agents : agentsList) {
	        	for (BEnterprise enterprise : enterpriseList) {
		    		if(StringUtils.equals(agents.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 60){
		    			countList.add(enterprise);
		    		}
	    		}
	        	effectiveCountMap.put(agents.getGuid(), (countList == null ? 0 : countList.size()));
	        	countList.clear();
			}
    	}
	    
	    agentsMap.put("agentsList", agentsList);
	    agentsMap.put("generalList", generalList);
	    agentsMap.put("effectiveCountMap", effectiveCountMap);
	    
		return new ModelAndView(Constant.SYSTEM_AGENT_AGENTS, "agentsMap", agentsMap);
	}
	
	@RequestMapping(value = "/newAgents", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newAgents(HttpServletRequest request, HttpSession session, @RequestBody @Valid NewAgentsVO 
			requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BAgents vo = agentsService.selectByAccountOrSerialNo(requestVO);
		if(vo != null){
			responseVO.setMessage(Status.CREATE_AGENTS_GENERAL_MSG001.getMessage());
			return responseVO;
		}
		
		vo = (BAgents) agentsService.create(requestVO, "Adam");
		if(vo == null){
			throw new LogicException(Status.CREATE_AGENTS_GENERAL_MSG002);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/editAgentsDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object editAgentsDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String,Object> agentsMap = new HashMap<String, Object>();

		BAgents vo = agentsService.selectByGuid(guid);
		vo.setPasswd(ShareTool.decrypt(Constant.IV, Constant.KEY, vo.getPasswd()));
		agentsMap.put("agents", vo);
		
		return agentsMap;
	}
	
	@RequestMapping(value = "/updateAgents", method = RequestMethod.POST)
	public @ResponseBody Object updateAgents(HttpServletRequest request, @RequestBody @Valid UpdateAgentsVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BAgents vo = agentsService.selectByGuid(requestVO.getGuid());

		BAgents vo2 = agentsService.selectBySerialNo(requestVO.getSerialNo() == null ? "" : requestVO.getSerialNo());
		if(vo2 != null && !StringUtils.equals(vo.getSerialNo(), requestVO.getSerialNo())){
			throw new LogicException(Status.UPDATE_AGENTS_MSG002);
		}
		
		vo2 = agentsService.selectByAccount(requestVO.getAccount());
		if(vo2 != null && !StringUtils.equals(vo.getAccount(), requestVO.getAccount() == null ? "" : requestVO.getAccount())){
			throw new LogicException(Status.UPDATE_AGENTS_MSG003);
		}
		
		vo2 = agentsService.selectByUniformNumber(requestVO.getUniformNumber());
		if(vo2 != null && !StringUtils.equals(vo.getUniformNumber(), 
				requestVO.getUniformNumber() == null ? "" : requestVO.getUniformNumber())){
			throw new LogicException(Status.UPDATE_AGENTS_MSG004);
		}
		
		requestVO.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPasswd()));
		vo2 = (BAgents) agentsService.update(requestVO, "Adam");
		if(vo2 == null){
			throw new LogicException(Status.UPDATE_AGENTS_MSG001);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/agentsStore", method = RequestMethod.GET)
	public @ResponseBody Object agentsStore(HttpServletRequest request) throws Exception{
		HashMap<String, Object> agentsStoreMap = new HashMap<String, Object>();
	    List<BAgents> agentsList = (List<BAgents>) agentsService.select();
		
    	/*
    	 * 排序
    	 */
	    
	    if(agentsList != null){
			Collections.sort(agentsList, new Comparator<BAgents>() {
	            public int compare(BAgents o1, BAgents o2) {
	                return (o1.getbAgentsGuid() == null ? "" : o1.getbAgentsGuid()).compareTo(
	                		(o2.getbAgentsGuid() == null ? "" : o2.getbAgentsGuid()));
	            }
	        });
	    }
		
	    List<BEnterprise> enterpriseList = (List<BEnterprise>) enterpriseService.select();
    	List<Object> countList = new ArrayList<>();

	    /*
	     * 未開通帳號數量
	     */
    	
	    HashMap<String, Object> nouseCountMap = new HashMap<>();
    	if(agentsList != null && enterpriseList != null){
        	for (BAgents agents : agentsList) {
            	for (BEnterprise enterprise : enterpriseList) {
    	    		if(StringUtils.equals(agents.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 1){
    	    			countList.add(enterprise);
    	    		}
        		}
            	nouseCountMap.put(agents.getGuid(), (countList == null ? 0 : countList.size()));
            	countList.clear();
    		}
    	}else{
	    	for (BAgents general : agentsList) {
	    		nouseCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
    	
	    /*
	     * 試用帳號數量
	     */
    	
	    HashMap<String, Object> tryonCountMap = new HashMap<>();
    	if(agentsList != null && enterpriseList != null){
        	for (BAgents agents : agentsList) {
            	for (BEnterprise enterprise : enterpriseList) {
    	    		if(StringUtils.equals(agents.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 30){
    	    			countList.add(enterprise);
    	    		}
        		}
            	tryonCountMap.put(agents.getGuid(), (countList == null ? 0 : countList.size()));
            	countList.clear();
    		}
    	}else{
	    	for (BAgents general : agentsList) {
	    		tryonCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
	    
	    /*
	     * 正式帳號數量
	     */

	    HashMap<String, Object> effectiveCountMap = new HashMap<>();
    	if(agentsList != null && enterpriseList != null){
        	for (BAgents agents : agentsList) {
            	for (BEnterprise enterprise : enterpriseList) {
    	    		if(StringUtils.equals(agents.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 60){
    	    			countList.add(enterprise);
    	    		}
        		}
            	effectiveCountMap.put(agents.getGuid(), (countList == null ? 0 : countList.size()));
            	countList.clear();
    		}
    	}else{
	    	for (BAgents general : agentsList) {
	    		effectiveCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
	    
	    /*
	     * 無效帳號數量
	     */

	    HashMap<String, Object> invalidCountMap = new HashMap<>();
    	if(agentsList != null && enterpriseList != null){
        	for (BAgents agents : agentsList) {
            	for (BEnterprise enterprise : enterpriseList) {
    	    		if(StringUtils.equals(agents.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 40){
    	    			countList.add(enterprise);
    	    		}
        		}
            	invalidCountMap.put(agents.getGuid(), (countList == null ? 0 : countList.size()));
            	countList.clear();
    		}
    	}else{
	    	for (BAgents general : agentsList) {
	    		invalidCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
	    
	    /*
	     * 不通過帳號數量
	     */

	    HashMap<String, Object> failCountMap = new HashMap<>();
    	if(agentsList != null && enterpriseList != null){
        	for (BAgents agents : agentsList) {
            	for (BEnterprise enterprise : enterpriseList) {
    	    		if(StringUtils.equals(agents.getGuid(), enterprise.getbAgentsGuid()) && enterprise.getStatus() == 20){
    	    			countList.add(enterprise);
    	    		}
        		}
            	failCountMap.put(agents.getGuid(), (countList == null ? 0 : countList.size()));
            	countList.clear();
    		}
    	}else{
	    	for (BAgents general : agentsList) {
	    		failCountMap.put(general.getGuid(), 0);
	        	countList.clear();
			}
    	}
    	
	    agentsStoreMap.put("agentsList", agentsList);
	    agentsStoreMap.put("nouseCountMap", nouseCountMap);
	    agentsStoreMap.put("tryonCountMap", tryonCountMap);
	    agentsStoreMap.put("effectiveCountMap", effectiveCountMap);
	    agentsStoreMap.put("failCountMap", failCountMap);
	    agentsStoreMap.put("invalidCountMap", invalidCountMap);
	    
		return new ModelAndView(Constant.SYSTEM_AGENT_STORE, "agentsStoreMap", agentsStoreMap);
	}
	
	@RequestMapping(value = "/newAgentsStoreDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object newAgentsStoreDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String,Object> agentsMap = new HashMap<String, Object>();

		BAgents vo2 = agentsService.selectByGuid(guid);
		BAgents vo = agentsService.selectByGuid((vo2.getbAgentsGuid() == null ? "" : vo2.getbAgentsGuid()));
		agentsMap.put("general", vo);
		agentsMap.put("agents", vo2);
		
		return agentsMap;
	}
	
	@RequestMapping(value = "/newAgentsStore", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newAgentsStore(HttpServletRequest request, HttpSession session, 
			@RequestBody @Valid NewAgentsStoreVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		int storeNos = Integer.valueOf(requestVO.getStoreNos());
		if(StringUtils.isNotBlank(requestVO.getSerialNo())){
			for (int x = 0; x < storeNos; x++) {
				BEnterprise vo = new BEnterprise();

				/*
				 * 產特店編號時，量大的話可能會有效能問題
				 */
				
				boolean selected = false;
				String defaultStoreNo;
				do {
					defaultStoreNo = requestVO.getSerialNo() + ShareTool.getNextIntT();
					BEnterprise vo2 = enterpriseService.selectDefaultStoreNo(defaultStoreNo);
					if(vo2 == null){
						selected = true;
					}
				} while (!selected);
				
				vo.setbAgentsGuid(requestVO.getbAgentsGuid());
				vo.setStoreNo(defaultStoreNo);
				vo = (BEnterprise) enterpriseService.create(vo, "Adam");
				if(vo == null){
					throw new LogicException(Status.CREATE_AGENTS_STORE_MSG001);
				}
			}
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/editAgentsStoreDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object editAgentsStoreDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		HashMap<String,Object> agentsMap = new HashMap<String, Object>();
		
		BAgents vo2 = agentsService.selectByGuid(guid);
		BAgents vo = agentsService.selectByGuid((vo2.getbAgentsGuid() == null ? "" : vo2.getbAgentsGuid()));
		agentsMap.put("general", vo);
		agentsMap.put("agents", vo2);
		
		List<BEnterprise> enterList = enterpriseService.selectByAgents(guid);
		agentsMap.put("enterList", enterList);
		
		return agentsMap;
	}
	
	@RequestMapping(value = "/updateAgentsStore", method = RequestMethod.POST)
	public @ResponseBody Object updateAgentsStore(HttpServletRequest request, @RequestBody @Valid UpdateAgentsStoreVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BAgents vo = agentsService.selectByGuid(requestVO.getGuid());

		BAgents vo2 = agentsService.selectBySerialNo(requestVO.getSerialNo() == null ? "" : requestVO.getSerialNo());
		if(vo2 != null && !StringUtils.equals(vo.getSerialNo(), requestVO.getSerialNo())){
			throw new LogicException(Status.UPDATE_AGENTS_STORE_MSG002);
		}
		
		vo2 = agentsService.selectByAccount(requestVO.getAccount());
		if(vo2 != null && !StringUtils.equals(vo.getAccount(), requestVO.getAccount() == null ? "" : requestVO.getAccount())){
			throw new LogicException(Status.UPDATE_AGENTS_STORE_MSG003);
		}
		
		vo2 = agentsService.selectByUniformNumber(requestVO.getUniformNumber());
		if(vo2 != null && !StringUtils.equals(vo.getUniformNumber(), 
				requestVO.getUniformNumber() == null ? "" : requestVO.getUniformNumber())){
			throw new LogicException(Status.UPDATE_AGENTS_STORE_MSG004);
		}
		
		requestVO.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPasswd()));
		vo2 = (BAgents) agentsService.update(requestVO, "Adam");
		if(vo2 == null){
			throw new LogicException(Status.UPDATE_AGENTS_STORE_MSG001);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/getNotGeneralList/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object getNotGeneralList(HttpServletRequest request, @PathVariable String guid) throws Exception{
		List<BAgents> notGeneralList = agentsService.selectByNotGeneral(guid);
		return notGeneralList;
	}
	
	/*
	 * 私有方法
	 */
	
	private void sendEmail(ResponseVO responseVO, BEnterprise requestVO, String password){
		try {
			String to = requestVO.getApplicantEmail();// 收件人電子郵箱
			String from = "service@everywhere.com.tw";// 發件人電子郵箱
			String host = "localhost";// 指定發送郵件的主機為localhost
			String content = String.format(
					"感謝您申請錢老闆雲端平台企業帳號! 您的主帳號已生效<br/>-------------------------------------------------------------"
					+ "<br/>主帳號 : %s<br/>密碼 : %s <br/>店號 : %s <br/>請進入會員中心啟動您所需要的功能及設定使用帳號權限<br/>https://ctr.sbcs.co"
					+ "m.tw/ <br/><br/>預祝您愉快！<br/><br/>※此信件為系統發出信件，請勿直接回覆。<br/>如果您有任何疑問，歡迎管理者聯繫，我們將竭誠為您服務 再次感謝您"
					+ "對錢老闆雲端平台的支持與愛護<br/>-------------------------------------------------------------<br/>錢老闆雲端平台<br/>11"
					+ "6 臺北市信義區松隆路 102 號 17 樓<br/>Tel: 0800-003387<br/>E-mail: service@sbcs.com.tw<br/>http://www.sbcs.com.tw<br/>",
					requestVO.getAccount(), password, requestVO.getStoreNo());

			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);

			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("EveryWhere","UTF-8");
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
		} catch (Exception e) {
			log.error(e, e);
			responseVO.setStatus(Status.CREATE_ENTERPRISE_MSG006.getCode());
			responseVO.setMessage(Status.CREATE_ENTERPRISE_MSG006.getMessage());
		}
	}
	
	/*
	 * LogicException Handler
	 */
	
	@Override
	@ExceptionHandler(LogicException.class)
	public Object handleLogicError(LogicException e) {
		return super.handleLogicError(e);
	}

	/*
	 * Exception Handler
	 */
	
	@Override
	@ExceptionHandler(Exception.class)
	public Object handleError(HttpServletRequest req, Exception ex) {
		return super.handleError(req, ex);
	}
}