package com.dada.mapper;

import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.OrganizationCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrganizationCategoryMapper {
    int countByExample(OrganizationCategoryExample example);

    int deleteByExample(OrganizationCategoryExample example);

    int deleteByPrimaryKey(String categoryId);

    int insert(OrganizationCategory record);

    int insertSelective(OrganizationCategory record);

    List<OrganizationCategory> selectByExampleWithBLOBs(OrganizationCategoryExample example);

    List<OrganizationCategory> selectByExample(OrganizationCategoryExample example);

    OrganizationCategory selectByPrimaryKey(String categoryId);

    int updateByExampleSelective(@Param("record") OrganizationCategory record, @Param("example") OrganizationCategoryExample example);

    int updateByExampleWithBLOBs(@Param("record") OrganizationCategory record, @Param("example") OrganizationCategoryExample example);

    int updateByExample(@Param("record") OrganizationCategory record, @Param("example") OrganizationCategoryExample example);

    int updateByPrimaryKeySelective(OrganizationCategory record);

    int updateByPrimaryKeyWithBLOBs(OrganizationCategory record);

    int updateByPrimaryKey(OrganizationCategory record);
}