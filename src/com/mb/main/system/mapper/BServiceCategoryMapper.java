package com.mb.main.system.mapper;

import com.mb.main.system.model.BServiceCategory;
import com.mb.main.system.model.BServiceCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BServiceCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int countByExample(BServiceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int deleteByExample(BServiceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int deleteByPrimaryKey(String guid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int insert(BServiceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int insertSelective(BServiceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    List<BServiceCategory> selectByExample(BServiceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    BServiceCategory selectByPrimaryKey(String guid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByExampleSelective(@Param("record") BServiceCategory record, @Param("example") BServiceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByExample(@Param("record") BServiceCategory record, @Param("example") BServiceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByPrimaryKeySelective(BServiceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_service_category
     *
     * @mbggenerated Thu May 26 15:22:09 CST 2016
     */
    int updateByPrimaryKey(BServiceCategory record);
}