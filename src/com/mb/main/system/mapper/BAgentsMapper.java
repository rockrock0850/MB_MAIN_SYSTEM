package com.mb.main.system.mapper;

import com.mb.main.system.model.BAgents;
import com.mb.main.system.model.BAgentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BAgentsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int countByExample(BAgentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int deleteByExample(BAgentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int deleteByPrimaryKey(String guid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int insert(BAgents record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int insertSelective(BAgents record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    List<BAgents> selectByExampleWithBLOBs(BAgentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    List<BAgents> selectByExample(BAgentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    BAgents selectByPrimaryKey(String guid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByExampleSelective(@Param("record") BAgents record, @Param("example") BAgentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") BAgents record, @Param("example") BAgentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByExample(@Param("record") BAgents record, @Param("example") BAgentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByPrimaryKeySelective(BAgents record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(BAgents record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_agents
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByPrimaryKey(BAgents record);
}