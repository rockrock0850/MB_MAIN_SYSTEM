package com.mb.main.system.utils;

public class Constant {
	
	/*
	 *  
	 */
	
	public final static String IV = ShareTool.getProperty("aes.iv");
	public final static String KEY = ShareTool.getProperty("aes.key");
	public final static String IMG_ROOT = ShareTool.getProperty("img.root");
	
	/*
	 * Api URI
	 */
	
	public final static String USER_API = ShareTool.getProperty("domain") + "/MB_MAIN_SYSTEM/api/user";
	
	/*
	 * Jsp path
	 */
	
	public final static String SYSTEM_INDEX = "mainSystem/index";
	public final static String SYSTEM_VERIFY = "mainSystem/account-verify";
	public final static String SYSTEM_TRYON = "mainSystem/account-tryon";
	public final static String SYSTEM_EFFECTIVE = "mainSystem/account-effective";
	public final static String SYSTEM_INVALID = "mainSystem/account-invalid";
	public final static String SYSTEM_FAIL = "mainSystem/account-fail";
	public final static String SYSTEM_AGENT_GENERAL = "mainSystem/agent-general";
	public final static String SYSTEM_AGENT_AGENTS = "mainSystem/agent-agents";
	public final static String SYSTEM_AGENT_STORE = "mainSystem/agent-store";
	
	/*
	 * 系統狀態
	 */
	
	public enum Status { 
		SELECT_ENTERPRISE_MSG001("無此企業帳號"),
		SELECT_ENTERPRISE_MSG002("企業帳號停權"),

		SELECT_EMPLOYEE_MSG001("無此個人帳號"),
		
		SELECT_ENTERPRISE_SERVICE_MSG001("無任何服務"),
		
		CREATE_ENTERPRISE_MSG001("帳號已存在"),
		CREATE_ENTERPRISE_MSG002("請確認密碼是否一致"),
		CREATE_ENTERPRISE_MSG003("註冊失敗"),
		CREATE_ENTERPRISE_MSG004("特店編號已被使用"),
		CREATE_ENTERPRISE_MSG005("預設代理商錯誤"),
		CREATE_ENTERPRISE_MSG006("200", "註冊成功，但信件發送失敗，請檢查郵件地址!"),
		CREATE_ENTERPRISE_MSG007("查無特店編號，請重新輸入"),
		CREATE_ENTERPRISE_MSG008("密碼更新失敗"),
		
		CREATE_EMPLOYEE_MSG001("帳號已存在"),
		CREATE_EMPLOYEE_MSG002("新增失敗"),

		CREATE_AGENTS_GENERAL_MSG001("帳號或代理商識別碼已存在"),
		CREATE_AGENTS_GENERAL_MSG002("新增失敗"),

		CREATE_AGENTS_STORE_MSG001("新增失敗"),

		CREATE_ENTERPRISE_LOGIN_HISTORY_MSG001("登入紀錄新增失敗"),
		
		DELETE_ENTERPRISE_MSG001("刪除失敗"),

		DELETE_EMPLOYEE_MSG001("刪除失敗"),

		UPDATE_ENTERPRISE_MSG001("更新企業帳號失敗"),
		UPDATE_ENTERPRISE_MSG002("密碼錯誤"),
		UPDATE_ENTERPRISE_MSG003("密碼建立失敗"),
		UPDATE_ENTERPRISE_MSG004("密碼更新失敗"),
		UPDATE_ENTERPRISE_MSG005("企業帳號重複"),
		UPDATE_ENTERPRISE_MSG006("特店編號重複"),

		UPDATE_EMPLOYEE_MSG001("密碼建立失敗"),
		UPDATE_EMPLOYEE_MSG002("無此帳號，請重新整理頁面"),
		UPDATE_EMPLOYEE_MSG003("更新子帳號失敗"),

		UPDATE_AGENTS_GENERAL_MSG001("更新總代理失敗"),
		UPDATE_AGENTS_GENERAL_MSG002("代理商識別碼重複"),
		UPDATE_AGENTS_GENERAL_MSG003("代理商帳號重複"),
		UPDATE_AGENTS_GENERAL_MSG004("統一編號號重複"),

		UPDATE_AGENTS_MSG001("更新代理商失敗"),
		UPDATE_AGENTS_MSG002("代理商識別碼重複"),
		UPDATE_AGENTS_MSG003("代理商帳號重複"),
		UPDATE_AGENTS_MSG004("統一編號號重複"),

		UPDATE_AGENTS_STORE_MSG001("更新失敗"),
		UPDATE_AGENTS_STORE_MSG002("代理商識別碼重複"),
		UPDATE_AGENTS_STORE_MSG003("代理商帳號重複"),
		UPDATE_AGENTS_STORE_MSG004("統一編號號重複"),
		
		LOGIN_MSG001("1", "帳號不存在"),
		LOGIN_MSG002("10", "特店編號不存在"),
		LOGIN_MSG003("20", "密碼錯誤"),
		LOGIN_MSG004("30", "帳號停權"),
		LOGIN_MSG005("40", "封鎖中拒絕登入"),
		LOGIN_MSG006("200", "登入成功"),
		LOGIN_MSG007("登入失敗"),
		LOGIN_MSG008("帳號審核中"),
		LOGIN_MSG009("個人帳號無效"),
		
		FIELD_MSG001("必要欄位不足"), 
		
		EXCEPTION_RECORD_MSG001("無此錯誤紀錄"),
		EXCEPTION_RECORD_MSG002("新增失敗"),
		
		SUCCESS("200", "執行成功"),
		UNKNOWN_ERROR("999", "未知的錯誤");
		
		private String code;
		private String message;
		
		private Status(String message) {
			this.message = message;
		}
		
		private Status(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		/*
		 * setter getter
		 */
		
		public String getMessage() {
			return message;
		}

		public String getCode() {
			return code;
		}
	}
}
