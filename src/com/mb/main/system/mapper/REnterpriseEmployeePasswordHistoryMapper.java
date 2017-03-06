package com.mb.main.system.mapper;

import com.mb.main.system.model.REnterpriseEmployeePasswordHistory;
import com.mb.main.system.model.REnterpriseEmployeePasswordHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface REnterpriseEmployeePasswordHistoryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int countByExample(REnterpriseEmployeePasswordHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int deleteByExample(REnterpriseEmployeePasswordHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int deleteByPrimaryKey(String guid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int insert(REnterpriseEmployeePasswordHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int insertSelective(REnterpriseEmployeePasswordHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	List<REnterpriseEmployeePasswordHistory> selectByExample(REnterpriseEmployeePasswordHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	REnterpriseEmployeePasswordHistory selectByPrimaryKey(String guid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int updateByExampleSelective(@Param("record") REnterpriseEmployeePasswordHistory record, @Param("example") REnterpriseEmployeePasswordHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int updateByExample(@Param("record") REnterpriseEmployeePasswordHistory record, @Param("example") REnterpriseEmployeePasswordHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int updateByPrimaryKeySelective(REnterpriseEmployeePasswordHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_enterprise_employee_password_history
	 * @mbggenerated  Fri Jun 03 17:06:25 CST 2016
	 */
	int updateByPrimaryKey(REnterpriseEmployeePasswordHistory record);
}