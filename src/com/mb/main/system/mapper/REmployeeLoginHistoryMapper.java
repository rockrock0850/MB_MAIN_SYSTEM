package com.mb.main.system.mapper;

import com.mb.main.system.model.REmployeeLoginHistory;
import com.mb.main.system.model.REmployeeLoginHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface REmployeeLoginHistoryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int countByExample(REmployeeLoginHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int deleteByExample(REmployeeLoginHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int deleteByPrimaryKey(String guid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int insert(REmployeeLoginHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int insertSelective(REmployeeLoginHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	List<REmployeeLoginHistory> selectByExample(REmployeeLoginHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	REmployeeLoginHistory selectByPrimaryKey(String guid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int updateByExampleSelective(@Param("record") REmployeeLoginHistory record, @Param("example") REmployeeLoginHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int updateByExample(@Param("record") REmployeeLoginHistory record, @Param("example") REmployeeLoginHistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int updateByPrimaryKeySelective(REmployeeLoginHistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table r_employee_login_history
	 * @mbggenerated  Sat Jun 04 15:56:34 CST 2016
	 */
	int updateByPrimaryKey(REmployeeLoginHistory record);
}