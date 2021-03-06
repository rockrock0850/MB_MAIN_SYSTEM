package com.mb.main.system.mapper;

import com.mb.main.system.model.REnterprisePasswordHistory;
import com.mb.main.system.model.REnterprisePasswordHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface REnterprisePasswordHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int countByExample(REnterprisePasswordHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int deleteByExample(REnterprisePasswordHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int deleteByPrimaryKey(String guid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int insert(REnterprisePasswordHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int insertSelective(REnterprisePasswordHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    List<REnterprisePasswordHistory> selectByExample(REnterprisePasswordHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    REnterprisePasswordHistory selectByPrimaryKey(String guid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int updateByExampleSelective(@Param("record") REnterprisePasswordHistory record, @Param("example") REnterprisePasswordHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int updateByExample(@Param("record") REnterprisePasswordHistory record, @Param("example") REnterprisePasswordHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int updateByPrimaryKeySelective(REnterprisePasswordHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_enterprise_password_history
     *
     * @mbggenerated Thu May 26 15:22:10 CST 2016
     */
    int updateByPrimaryKey(REnterprisePasswordHistory record);
}