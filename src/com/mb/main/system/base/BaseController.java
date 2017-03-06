package com.mb.main.system.base;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mb.main.system.exception.LogicException;
import com.mb.main.system.utils.Constant.Status;
import com.mb.main.system.vo.ResponseVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class BaseController {
	private Logger log = Logger.getLogger(this.getClass());
	
	/*
	 * 
	 */
	
	protected String baseDir;
	protected String baseUri;
	protected DefaultTransactionDefinition def;
	protected TransactionStatus status;
	
	/*
	 * 
	 */
	
	private DataSourceTransactionManager txManager;
	
	/**
	 * 
	 * @param request
	 * @param txManager
	 */
	public void init(HttpServletRequest request, DataSourceTransactionManager txManager){
		String url = request.getRequestURL().toString();
		this.baseUri = url.replace(request.getServletPath(), "");
		this.baseDir = request.getSession().getServletContext().getRealPath("").
				replace(".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp3\\wtpwebapps\\", "");
	    this.def = new DefaultTransactionDefinition();
	    
	    /*
	     * 按照SPC上的解釋，使用PROPAGATION_REQUIRED交易機制最為普遍，但在insert後想做別的操作會出現data lock，導致拋出lock timeout例外錯誤。
	     */
	    this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	    this.status = txManager.getTransaction(def);
	    this.txManager = txManager;
	    System.setProperty("baseDir", baseDir);
	}
	
	public Object handleLogicError(LogicException e) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatus(e.getCode());
		responseVO.setMessage(e.getMessage());
		
		if(!status.isCompleted()){
			txManager.rollback(status);	
		}

		return responseVO;
	}
	
	/**
	 * 
	 * @param req
	 * @param exception
	 * @return
	 */
	public Object handleError(HttpServletRequest req, Exception e) {
		/*ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");*/
		 
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatus(Status.UNKNOWN_ERROR.getCode());
		responseVO.setMessage(Status.UNKNOWN_ERROR.getMessage());
		responseVO.setResult(new StringBuilder("Request: " + req.getRequestURL() + ", Throws ****" + e.getMessage() + "****"));

		if(!status.isCompleted()){
			txManager.rollback(status);	
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(out));
		String cause = null;
		try {
			cause = out.toString("utf-8");
		} catch (UnsupportedEncodingException e1) {
			log.error(e1, e1);
		}
		
		log.error(e, e);
		return cause;
	}
}
