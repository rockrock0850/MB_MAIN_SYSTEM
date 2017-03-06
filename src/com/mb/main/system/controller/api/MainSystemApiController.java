package com.mb.main.system.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
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

import com.mb.main.system.base.BaseController;
import com.mb.main.system.exception.LogicException;
import com.mb.main.system.model.BAgents;
import com.mb.main.system.model.BEnterprise;
import com.mb.main.system.model.BEnterpriseEmployee;
import com.mb.main.system.model.BIndustry;
import com.mb.main.system.model.BService;
import com.mb.main.system.model.DEnterpriseService;
import com.mb.main.system.model.REmployeeLoginHistory;
import com.mb.main.system.model.REnterpriseEmployeePasswordHistory;
import com.mb.main.system.model.REnterpriseLoginHistory;
import com.mb.main.system.model.REnterprisePasswordHistory;
import com.mb.main.system.model.VEnterpriseLogin;
import com.mb.main.system.service.AgentsService;
import com.mb.main.system.service.EmployeeLoginHistoryService;
import com.mb.main.system.service.EmployeePasswordHistoryService;
import com.mb.main.system.service.EmployeeService;
import com.mb.main.system.service.EnterpriseLoginHistoryService;
import com.mb.main.system.service.EnterpriseLoginService;
import com.mb.main.system.service.EnterprisePasswordHistoryService;
import com.mb.main.system.service.EnterpriseService;
import com.mb.main.system.service.EnterpriseServiceService;
import com.mb.main.system.service.IndustryService;
import com.mb.main.system.service.ServiceService;
import com.mb.main.system.utils.Constant;
import com.mb.main.system.utils.Constant.Status;
import com.mb.main.system.utils.ShareTool;
import com.mb.main.system.vo.EmployeeVO;
import com.mb.main.system.vo.EnterpriseServicesVO;
import com.mb.main.system.vo.EnterpriseVO;
import com.mb.main.system.vo.ResponseVO;
import com.mb.main.system.vo.SimpleFieldVO;
import com.mb.main.system.vo.TokenVO;
import com.mb.main.system.vo.form.DeleteEmployeeVO;
import com.mb.main.system.vo.form.LoginEnterpriseVO;
import com.mb.main.system.vo.form.UpdateEmployeeVO;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;

@Log4j
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/MainSystemApi")
public class MainSystemApiController extends BaseController{
	private Logger log = Logger.getLogger(this.getClass());	
	
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private IndustryService industryService;
	@Autowired
	private AgentsService agentsService;
	@Autowired
	private EnterprisePasswordHistoryService enterprisePasswordHistoryService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeePasswordHistoryService employeePasswordHistoryService;
	@Autowired
	private EnterpriseLoginService enterpriseLoginService;
	@Autowired
	private EnterpriseLoginHistoryService enterpriseLoginHistoryService;
	@Autowired
	private EmployeeLoginHistoryService employeeLoginHistoryService;
	@Autowired
	private EnterpriseServiceService enterpriseServiceService;
	@Autowired
	private ServiceService serviceService;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO register(HttpServletRequest request, @RequestBody @Valid EnterpriseVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			List<SimpleFieldVO> simpleFieldList = ShareTool.simpleFieldError(result.getFieldErrors());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(simpleFieldList);
			return responseVO;
		}
		
		/*
		 * 更新已送出的特店編號
		 */
		
		if(StringUtils.isNotBlank(requestVO.getStoreNo())){
			BEnterprise bEnterprise = enterpriseService.selectUsedStoreNo(requestVO.getStoreNo());
			if(bEnterprise != null){
				responseVO.setStatus(Status.CREATE_ENTERPRISE_MSG004.getCode());
				responseVO.setMessage(Status.CREATE_ENTERPRISE_MSG004.getMessage());
				return responseVO;
			}
			
			bEnterprise = enterpriseService.selectStoreNo(requestVO.getStoreNo());
			if(bEnterprise == null){
				responseVO.setStatus(Status.CREATE_ENTERPRISE_MSG007.getCode());
				responseVO.setMessage(Status.CREATE_ENTERPRISE_MSG007.getMessage());
				return responseVO;
			}

			REnterprisePasswordHistory rEnterprisePasswordHistory = new REnterprisePasswordHistory();
			rEnterprisePasswordHistory.setbEnterpriseGuid(bEnterprise.getGuid());
			rEnterprisePasswordHistory.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getEmail()));
			if(enterprisePasswordHistoryService.create(rEnterprisePasswordHistory, "Adam") == null){
				throw new LogicException(Status.UPDATE_ENTERPRISE_MSG003);
			}
			
			requestVO.setbAgentsGuid(bEnterprise.getbAgentsGuid());
			requestVO.setStatus(10);
			if(enterpriseService.notUseToVerify(requestVO, "Adam") == null){
				throw new LogicException(Status.UPDATE_ENTERPRISE_MSG001);
			}
			
			responseVO.setStatus(Status.SUCCESS.getCode());
			responseVO.setMessage(Status.SUCCESS.getMessage());
			responseVO.setResult(requestVO.getStoreNo());
			
			txManager.commit(status);

//			sendEmail(responseVO, requestVO);
			return responseVO;
		}

		BAgents bAgents = agentsService.selectDefault();
		if(bAgents == null){
			responseVO.setStatus(Status.CREATE_ENTERPRISE_MSG005.getCode());
			responseVO.setMessage(Status.CREATE_ENTERPRISE_MSG005.getMessage());
		}

		/*
		 * 產特店編號時，量大的話可能會有效能問題
		 */
		
		boolean selected = false;
		String defaultStoreNo;
		do {
			defaultStoreNo = "ew" + ShareTool.getNextIntT();
			BEnterprise bEnterprise = enterpriseService.selectDefaultStoreNo(defaultStoreNo);
			if(bEnterprise == null){
				selected = true;
			}
		} while (!selected);

		requestVO.setStoreNo(defaultStoreNo);
		requestVO.setbAgentsGuid(bAgents.getGuid());
		requestVO.setStatus(10);

		
		BEnterprise vo = (BEnterprise) enterpriseService.create(requestVO, "Adam");
		if(vo == null){
			throw new LogicException(Status.CREATE_ENTERPRISE_MSG003);
		}

		REnterprisePasswordHistory vo2 = new REnterprisePasswordHistory();
		vo2.setbEnterpriseGuid(vo.getGuid());
		vo2.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getEmail()));
		vo2 = (REnterprisePasswordHistory) enterprisePasswordHistoryService.create(vo2, "Adam");
		if(vo2 == null){
			throw new LogicException(Status.CREATE_ENTERPRISE_MSG008);
		}

		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO.getGuid());
		txManager.commit(status);
		
//		sendEmail(responseVO, requestVO);
		return responseVO;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO login(HttpServletRequest request, @RequestBody @Valid LoginEnterpriseVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}

		REnterpriseLoginHistory enterpriseLoginHistoryVO = new REnterpriseLoginHistory();
		enterpriseLoginHistoryVO.setAccount(requestVO.getAccount());
		enterpriseLoginHistoryVO.setStoreNo(requestVO.getStoreNo());

		/*
		 * 帳號不存在
		 */
		
		VEnterpriseLogin enterpriseLoginVO = enterpriseLoginService.selectByAccount(requestVO);
		if(enterpriseLoginVO == null){
			enterpriseLoginHistoryVO.setStatus(1);
			
			REnterpriseLoginHistory enterpriseLoginHistoryVO2 = 
					enterpriseLoginHistoryService.selectByAccountStoreNo(enterpriseLoginHistoryVO);
			if(enterpriseLoginHistoryVO2 == null){
				enterpriseLoginHistoryVO2 = (REnterpriseLoginHistory) 
						enterpriseLoginHistoryService.create(enterpriseLoginHistoryVO, "Adam");
			}else{
				enterpriseLoginHistoryVO.setGuid(enterpriseLoginHistoryVO2.getGuid());
				enterpriseLoginHistoryVO.setRetryTimes(enterpriseLoginHistoryVO2.getRetryTimes()+1);
				enterpriseLoginHistoryVO2 = (REnterpriseLoginHistory) 
						enterpriseLoginHistoryService.update(enterpriseLoginHistoryVO, "Adam");
			}
			
			if(enterpriseLoginHistoryVO2 == null){
				throw new LogicException(Status.CREATE_ENTERPRISE_LOGIN_HISTORY_MSG001);
			}
			
			responseVO.setStatus(Status.LOGIN_MSG001.getCode());
			responseVO.setMessage(Status.LOGIN_MSG001.getMessage());
			
			txManager.commit(status);
			return responseVO;
		}
		
		/*
		 * 特店編號不存在
		 */

		enterpriseLoginVO = enterpriseLoginService.selectByAccountStoreNo(requestVO);
		if(enterpriseLoginVO == null){
			enterpriseLoginHistoryVO.setStatus(10);
			
			REnterpriseLoginHistory enterpriseLoginHistoryVO2 = 
					enterpriseLoginHistoryService.selectByAccountStoreNo(enterpriseLoginHistoryVO);
			if(enterpriseLoginHistoryVO2 == null){
				enterpriseLoginHistoryVO2 = (REnterpriseLoginHistory) 
						enterpriseLoginHistoryService.create(enterpriseLoginHistoryVO, "Adam");
			}else{
				enterpriseLoginHistoryVO.setGuid(enterpriseLoginHistoryVO2.getGuid());
				enterpriseLoginHistoryVO.setRetryTimes(enterpriseLoginHistoryVO2.getRetryTimes()+1);
				enterpriseLoginHistoryVO2 = (REnterpriseLoginHistory) 
						enterpriseLoginHistoryService.update(enterpriseLoginHistoryVO, "Adam");
			}
			
			if(enterpriseLoginHistoryVO2 == null){
				throw new LogicException(Status.CREATE_ENTERPRISE_LOGIN_HISTORY_MSG001);
			}
			
			responseVO.setStatus(Status.LOGIN_MSG002.getCode());
			responseVO.setMessage(Status.LOGIN_MSG002.getMessage());

			txManager.commit(status);
			return responseVO;
		}
		
		/*
		 * 密碼錯誤
		 */

		enterpriseLoginVO = enterpriseLoginService.selectByLoginInfo(requestVO);
		if(enterpriseLoginVO == null){
			enterpriseLoginVO = enterpriseLoginService.selectByAccountStoreNo(requestVO);
			enterpriseLoginVO.setStatus((long)20);
		}

		boolean loginError = false;
		
		/*
		 * 企業帳號審核中
		 */
		
		BEnterprise enterpriseVO = enterpriseService.selectByGuid(enterpriseLoginVO.getPk());
		if(enterpriseVO != null && enterpriseVO.getStatus() == 10){
			responseVO.setStatus(Status.LOGIN_MSG008.getCode());
			responseVO.setMessage(Status.LOGIN_MSG008.getMessage());
			loginError = true;
		}
		
		/*
		 * 個人帳號無效
		 */
		
		EmployeeVO enterpriseEmployeeVO = employeeService.selectByGuid(enterpriseLoginVO.getPk());
		if(enterpriseEmployeeVO != null && enterpriseEmployeeVO.getStatus() == 10){
			responseVO.setStatus(Status.LOGIN_MSG009.getCode());
			responseVO.setMessage(Status.LOGIN_MSG009.getMessage());
			loginError = true;
		}
		
		/*
		 * 其他帳號狀態登入錯誤處理
		 */

		if(Long.valueOf(Status.LOGIN_MSG003.getCode()) == enterpriseLoginVO.getStatus()){// 密碼錯誤
			responseVO.setStatus(Status.LOGIN_MSG003.getCode());
			responseVO.setMessage(Status.LOGIN_MSG003.getMessage());
			loginError = true;
		}else if(Long.valueOf(Status.LOGIN_MSG004.getCode()) == enterpriseLoginVO.getStatus()){// 帳號停權
			responseVO.setStatus(Status.LOGIN_MSG004.getCode());
			responseVO.setMessage(Status.LOGIN_MSG004.getMessage());
			loginError = true;
		}else if(Long.valueOf(Status.LOGIN_MSG005.getCode()) == enterpriseLoginVO.getStatus()){// 封鎖中拒絕登入
			responseVO.setStatus(Status.LOGIN_MSG005.getCode());
			responseVO.setMessage(Status.LOGIN_MSG005.getMessage());
			loginError = true;
		}

		if(loginError){
			if(StringUtils.equals(enterpriseLoginVO.getType(), "enterprise")){
				REnterpriseLoginHistory enterpriseLoginHistoryVO2 = new REnterpriseLoginHistory();
				enterpriseLoginHistoryVO2.setbEnterpriseGuid(enterpriseLoginVO.getPk());
				enterpriseLoginHistoryVO2.setStoreNo(StringUtils.isBlank(enterpriseLoginVO.getStoreNo()) ? "" : 
					enterpriseLoginVO.getStoreNo());
				enterpriseLoginHistoryVO2.setAccount(enterpriseLoginVO.getAccount());
				enterpriseLoginHistoryVO2.setStatus((int) (long) enterpriseLoginVO.getStatus()); 
				
				REnterpriseLoginHistory enterpriseLoginHistoryVO3 = 
						enterpriseLoginHistoryService.selectByLastModifierDate(enterpriseLoginHistoryVO2);
				if(enterpriseLoginHistoryVO3 == null){
					enterpriseLoginHistoryVO3 = (REnterpriseLoginHistory) enterpriseLoginHistoryService.create(
							enterpriseLoginHistoryVO2, "Adam");
				}else{
					enterpriseLoginHistoryVO2.setRetryTimes(enterpriseLoginHistoryVO3.getRetryTimes()+1);
					enterpriseLoginHistoryVO2.setGuid(enterpriseLoginHistoryVO3.getGuid());
					
					if(enterpriseLoginHistoryVO3.getStatus() != 50){
						enterpriseLoginHistoryVO3 = (REnterpriseLoginHistory) 
								enterpriseLoginHistoryService.update(enterpriseLoginVO, "Adam");
					}else{
						enterpriseLoginHistoryVO2.setGuid(UUID.randomUUID().toString());
						enterpriseLoginHistoryVO3 = (REnterpriseLoginHistory) 
								enterpriseLoginHistoryService.create(enterpriseLoginVO, "Adam");
					}
				}
				
				if(enterpriseLoginHistoryVO3 == null){
					throw new LogicException(Status.CREATE_ENTERPRISE_LOGIN_HISTORY_MSG001);
				}
			}else{
				REmployeeLoginHistory employeeLoginHistoryVO = new REmployeeLoginHistory();
				employeeLoginHistoryVO.setbEnterpriseEmployeeGuid(enterpriseLoginVO.getPk());
				employeeLoginHistoryVO.setStoreNo(StringUtils.isBlank(enterpriseLoginVO.getStoreNo()) ? "" : 
					enterpriseLoginVO.getStoreNo());
				employeeLoginHistoryVO.setAccount(enterpriseLoginVO.getAccount());
				employeeLoginHistoryVO.setStatus(enterpriseLoginVO.getStatus().toString()); 
				
				REmployeeLoginHistory enterpriseLoginHistoryVO2 = employeeLoginHistoryService.selectByLastModifierDate(
						employeeLoginHistoryVO);

				if(enterpriseLoginHistoryVO2 == null){
					enterpriseLoginHistoryVO2 = (REmployeeLoginHistory) employeeLoginHistoryService.create(
							employeeLoginHistoryVO, "Adam");
				}else{
					employeeLoginHistoryVO.setRetryTimes(enterpriseLoginHistoryVO2.getRetryTimes()+1);
					employeeLoginHistoryVO.setGuid(enterpriseLoginHistoryVO2.getGuid());

					if(!StringUtils.equals(enterpriseLoginHistoryVO2.getStatus(), "50")){
						enterpriseLoginHistoryVO2 = (REmployeeLoginHistory) 
								employeeLoginHistoryService.update(employeeLoginHistoryVO, "Adam");
					}else{
						employeeLoginHistoryVO.setGuid(UUID.randomUUID().toString());
						enterpriseLoginHistoryVO2 = (REmployeeLoginHistory) 
								employeeLoginHistoryService.create(employeeLoginHistoryVO, "Adam");
					}
				}
				
				if(enterpriseLoginHistoryVO2 == null){
					throw new LogicException(Status.CREATE_ENTERPRISE_LOGIN_HISTORY_MSG001);
				}
			}
			
			txManager.commit(status);
			return responseVO;	
		}
		
		/*
		 * 登入成功
		 */

		if(StringUtils.equals(enterpriseLoginVO.getType(), "enterprise")){
			REnterpriseLoginHistory enterpriseLoginHistoryVO2 = new REnterpriseLoginHistory();
			enterpriseLoginHistoryVO2.setGuid(UUID.randomUUID().toString());
			enterpriseLoginHistoryVO2.setbEnterpriseGuid(enterpriseLoginVO.getPk());
			enterpriseLoginHistoryVO2.setStoreNo(StringUtils.isBlank(enterpriseLoginVO.getStoreNo()) ? "" : 
				enterpriseLoginVO.getStoreNo());
			enterpriseLoginHistoryVO2.setAccount(enterpriseLoginVO.getAccount());
			enterpriseLoginHistoryVO2.setStatus((int) (long) enterpriseLoginVO.getStatus());

			/*
			 * 產TOKEN
			 */
			
			JSONObject json  = new JSONObject();
			json.put("pk", enterpriseLoginVO.getPk());
			json.put("name", enterpriseLoginVO.getAccount());
			json.put("type", enterpriseLoginVO.getType());
			json.put("loginHistory", enterpriseLoginHistoryVO2.getGuid());
			json.put("corporation", enterpriseLoginVO.getPk());
			enterpriseLoginHistoryVO2.setToken(json.toString());
			
			enterpriseLoginHistoryVO2 = (REnterpriseLoginHistory) 
					enterpriseLoginHistoryService.create(enterpriseLoginHistoryVO2, "Adam");
			if(enterpriseLoginHistoryVO2 == null){
				throw new LogicException(Status.CREATE_ENTERPRISE_LOGIN_HISTORY_MSG001);
			}
		}else{
			REmployeeLoginHistory employeeLoginHistoryVO = new REmployeeLoginHistory();
			employeeLoginHistoryVO.setGuid(UUID.randomUUID().toString());
			employeeLoginHistoryVO.setbEnterpriseEmployeeGuid(enterpriseLoginVO.getPk());
			employeeLoginHistoryVO.setStoreNo(StringUtils.isBlank(enterpriseLoginVO.getStoreNo()) ? "" : 
				enterpriseLoginVO.getStoreNo());
			employeeLoginHistoryVO.setAccount(enterpriseLoginVO.getAccount());
			employeeLoginHistoryVO.setStatus(enterpriseLoginVO.getStatus().toString()); 

			/*
			 * 產TOKEN
			 */
			
			JSONObject json  = new JSONObject();
			json.put("pk", enterpriseLoginVO.getPk());
			json.put("name", enterpriseLoginVO.getAccount());
			json.put("type", enterpriseLoginVO.getType());
			json.put("loginHistory", employeeLoginHistoryVO.getGuid());
			
			EmployeeVO employeeVO = employeeService.selectByGuid(enterpriseLoginVO.getPk());
			json.put("corporation", employeeVO.getbEnterpriseGuid());
			
			employeeLoginHistoryVO.setToken(json.toString());

			employeeLoginHistoryVO = (REmployeeLoginHistory) employeeLoginHistoryService.create(employeeLoginHistoryVO, "Adam");
			if(employeeLoginHistoryVO == null){
				throw new LogicException(Status.CREATE_ENTERPRISE_LOGIN_HISTORY_MSG001);
			}
		}
		
		enterpriseLoginVO = enterpriseLoginService.selectByGuid(enterpriseLoginVO.getPk());
		if(enterpriseLoginVO == null){
			responseVO.setStatus(Status.LOGIN_MSG007.getCode());
			responseVO.setMessage(Status.LOGIN_MSG007.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.LOGIN_MSG006.getCode());
		responseVO.setMessage(Status.LOGIN_MSG006.getMessage());
		
		TokenVO tokenVO = new TokenVO();
		tokenVO.setToken(enterpriseLoginVO.getToken());
		responseVO.setResult(tokenVO);
		
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 取得企業帳號所屬服務
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getEnterpriseServices/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO getEnterpriseServices(HttpServletRequest request, @PathVariable String guid) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		BEnterpriseEmployee vo = employeeService.selectByGuid(guid);
		if(vo == null){
			responseVO.setStatus(Status.SELECT_EMPLOYEE_MSG001.getCode());
			responseVO.setMessage(Status.SELECT_EMPLOYEE_MSG001.getMessage());
			return responseVO;
		}
		
		List<DEnterpriseService> enterpriseServiceList = (List<DEnterpriseService>) 
				enterpriseServiceService.selectByEnterpriseGuid(vo.getbEnterpriseGuid());
		if(enterpriseServiceList == null){
			responseVO.setStatus(Status.SELECT_ENTERPRISE_SERVICE_MSG001.getCode());
			responseVO.setMessage(Status.SELECT_ENTERPRISE_SERVICE_MSG001.getMessage());
			return responseVO;
		}
		
		List<EnterpriseServicesVO> enterpriseServicesVOList = new ArrayList<>();
		if(enterpriseServiceList != null){
			enterpriseServiceList.forEach(parent -> {
				EnterpriseServicesVO vo2 = new EnterpriseServicesVO();
				try {
					BeanUtils.copyProperties(vo2, parent);
				} catch (Exception e) {
					log.error(e, e);
				}
				enterpriseServicesVOList.add(vo2);
			});
		}
		
		List<BService> serviceList = (List<BService>) serviceService.select();
		enterpriseServicesVOList.forEach(vo2 -> {
			serviceList.forEach(service -> {
				if(StringUtils.equals(vo2.getbServiceGuid(), service.getGuid())){
					vo2.setTitle(service.getTitle());
					vo2.setSerioNo(service.getSerialNo());
					vo2.setSortNo(service.getSortNo());
					if(StringUtils.isBlank(vo2.getUrl())){
						vo2.setUrl(service.getUrl());
					}
				}
			});
		});
		
		Collections.sort(enterpriseServicesVOList, new Comparator<EnterpriseServicesVO>() {
		            public int compare(EnterpriseServicesVO o1, EnterpriseServicesVO o2) {
		                return o2.getSortNo()-o1.getSortNo();
		            }
		        });

		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(enterpriseServicesVOList);
		
		return responseVO;
	}
	
	/*
	 * 取得行業別清單
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getIndustryList", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO getIndustryList() throws Exception{
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult((List<BIndustry>) industryService.select());
		return responseVO;
	}
	
	/*
	 * 個人帳號前端的API
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/employee/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO employee(HttpServletRequest request, @PathVariable String guid) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		BEnterprise enterprise = enterpriseService.selectByGuid(guid);
		if(enterprise == null){
			responseVO.setMessage(Status.SELECT_ENTERPRISE_MSG001.getMessage());
			return responseVO;
		}
		
		if(enterprise.getStatus() != 30 && enterprise.getStatus() != 60){
			responseVO.setMessage(Status.SELECT_ENTERPRISE_MSG002.getMessage());
			return responseVO;
		}
		
		TreeMap<String, Object> data = new TreeMap<>();
		List<EmployeeVO> employeeList = (List<EmployeeVO>) employeeService.selectByEnterpriseGuid(guid);
		List<REnterpriseEmployeePasswordHistory> employeePasswordHistoryList = (List<REnterpriseEmployeePasswordHistory>) 
				employeePasswordHistoryService.select();

	    if(employeeList != null){
	    	employeeList.forEach(employee -> {
	    		employeePasswordHistoryList.forEach(password -> {
		    		if(StringUtils.equals(employee.getGuid(), password.getbEnterpriseEmployeeGuid())){
		    			try {
							employee.setPassword(ShareTool.decrypt(Constant.IV, Constant.KEY, password.getPasswd()));
						} catch (Exception e) {
							log.error(e, e);
						}
		    		}
		    	});
		    });	
	    }
	    
	    data.put("employeeList", employeeList);
	    data.put("enterpriseGuid", guid);
	    
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(data);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/newEmployee", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newEmployee(HttpServletRequest request, HttpSession session, @RequestBody @Valid EmployeeVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BEnterpriseEmployee vo = employeeService.selectByAccount(requestVO.getAccount());
		if(vo != null){
			responseVO.setMessage(Status.CREATE_EMPLOYEE_MSG001.getMessage());
			return responseVO;
		}
		
		requestVO.setbEnterpriseGuid(requestVO.getEnterpriseGuid());
		vo = (BEnterpriseEmployee) employeeService.create(requestVO, "Adam");
		if(vo == null){
			throw new LogicException(Status.CREATE_EMPLOYEE_MSG002);
		}
		
		REnterpriseEmployeePasswordHistory vo2 = new REnterpriseEmployeePasswordHistory();
		vo2.setbEnterpriseEmployeeGuid(vo.getGuid());
		vo2.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPassword()));
		if(employeePasswordHistoryService.create(vo2, "Adam") == null){
			throw new LogicException(Status.UPDATE_EMPLOYEE_MSG001);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(requestVO);
		txManager.commit(status);
		return responseVO;
	}

	@RequestMapping(value = "/editEmployeeDetail/{guid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO editEmployeeDetail(HttpServletRequest request, @PathVariable String guid) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		EmployeeVO employee = employeeService.selectByGuid(guid);
		
		if(employee == null){
			responseVO.setMessage(Status.UPDATE_EMPLOYEE_MSG002.getMessage());
			return responseVO;
		}
		
		REnterpriseEmployeePasswordHistory employeePasswordHistory = employeePasswordHistoryService.
				selectByEmployeeGuid(employee.getGuid());

		employee.setPassword(employeePasswordHistory == null ? "" : ShareTool.decrypt(Constant.IV, Constant.KEY, 
				employeePasswordHistory.getPasswd()));
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(employee);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody Object updateEmployee(HttpServletRequest request, @RequestBody @Valid UpdateEmployeeVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BEnterpriseEmployee employee = (BEnterpriseEmployee) employeeService.update(requestVO, "Adam");
		if(employee == null){
			throw new LogicException(Status.UPDATE_EMPLOYEE_MSG003);
		}
		
		REnterpriseEmployeePasswordHistory record = employeePasswordHistoryService.selectByEmployeeGuid(requestVO.getGuid());
		record.setbEnterpriseEmployeeGuid(requestVO.getGuid());
		record.setPasswd(ShareTool.encrypt(Constant.IV, Constant.KEY, requestVO.getPassword()));
		record = (REnterpriseEmployeePasswordHistory) employeePasswordHistoryService.update(record, "Adam");
		if(record == null){
			throw new LogicException(Status.UPDATE_EMPLOYEE_MSG003);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(employee);

		txManager.commit(status);
		return responseVO;
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO deleteEmployee(HttpServletRequest request, @RequestBody DeleteEmployeeVO requestVO) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		String vo = employeeService.deleteByGuid(requestVO.getGuid());
		if(vo == null){
			throw new LogicException(Status.DELETE_EMPLOYEE_MSG001);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo);
		
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 自用的測試Api
	 */
	
	@RequestMapping(value = "/getGuid", method = RequestMethod.GET)
	public @ResponseBody ResponseVO getGuid() throws Exception{
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(UUID.randomUUID());
		return responseVO;
	}

	@RequestMapping(value = "/decrypt", method = RequestMethod.POST)
	public @ResponseBody ResponseVO decrypt(@RequestBody String data) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(ShareTool.decrypt(Constant.IV, Constant.KEY, data));
		return responseVO;
	}

	@RequestMapping(value = "/encrypt", method = RequestMethod.POST)
	public @ResponseBody ResponseVO encrypt(@RequestBody String data) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(ShareTool.encrypt(Constant.IV, Constant.KEY, data));
		return responseVO;
	}
	
	/*
	 * 送信: 目前無法送出，且送信失敗後會產生data lock情形，待查出原因
	 */
	
	private void sendEmail(ResponseVO responseVO, BEnterprise requestVO){
		try {
			String to = requestVO.getApplicantEmail();// 收件人電子郵箱
			String from = "service@everywhere.com.tw";// 發件人電子郵箱
			String host = "localhost";// 指定發送郵件的主機為localhost
			String content = String.format(
					"<p>親愛的 %s 您好：<br />感謝您申請錢老闆雲端平台帳號! 請靜候管理者審核。<br/>-------------------------------------------------------------<br/>若會員非您本人申請加入，請您與管理者聯繫<br/>預祝您愉快！<br/>※此信件為系統發出信件，請勿直接回覆。<br/>如果您有任何疑問，歡迎管理者聯繫，我們將竭誠為您服務 再次感謝您對錢老闆雲端平台的支持與愛護<br/>-------------------------------------------------------------<br/>錢老闆雲端平台<br/>116 臺北市信義區松隆路 102 號 17 樓<br/>Tel: 0800-003387<br/>E-mail: service@sbcs.com.tw<br/>http://www.sbcs.com.tw<br/></p>", requestVO.getApplicantName());

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