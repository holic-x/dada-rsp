package com.dada.mapper;

import com.dada.pojo.DataSourceInfo;
import com.dada.pojo.DataSourceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataSourceInfoMapper {
    int countByExample(DataSourceInfoExample example);

    int deleteByExample(DataSourceInfoExample example);

    int deleteByPrimaryKey(String configId);

    int insert(DataSourceInfo record);

    int insertSelective(DataSourceInfo record);

    List<DataSourceInfo> selectByExampleWithBLOBs(DataSourceInfoExample example);

    List<DataSourceInfo> selectByExample(DataSourceInfoExample example);

    DataSourceInfo selectByPrimaryKey(String configId);

    int updateByExampleSelective(@Param("record") DataSourceInfo record, @Param("example") DataSourceInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") DataSourceInfo record, @Param("example") DataSourceInfoExample example);

    int updateByExample(@Param("record") DataSourceInfo record, @Param("example") DataSourceInfoExample example);

    int updateByPrimaryKeySelective(DataSourceInfo record);

    int updateByPrimaryKeyWithBLOBs(DataSourceInfo record);

    int updateByPrimaryKey(DataSourceInfo record);
}