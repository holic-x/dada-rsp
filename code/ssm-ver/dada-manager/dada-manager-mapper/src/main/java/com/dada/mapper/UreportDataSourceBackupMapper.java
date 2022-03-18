package com.dada.mapper;

import com.dada.pojo.UreportDataSourceBackup;
import com.dada.pojo.UreportDataSourceBackupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UreportDataSourceBackupMapper {
    int countByExample(UreportDataSourceBackupExample example);

    int deleteByExample(UreportDataSourceBackupExample example);

    int deleteByPrimaryKey(String fileId);

    int insert(UreportDataSourceBackup record);

    int insertSelective(UreportDataSourceBackup record);

    List<UreportDataSourceBackup> selectByExampleWithBLOBs(UreportDataSourceBackupExample example);

    List<UreportDataSourceBackup> selectByExample(UreportDataSourceBackupExample example);

    UreportDataSourceBackup selectByPrimaryKey(String fileId);

    int updateByExampleSelective(@Param("record") UreportDataSourceBackup record, @Param("example") UreportDataSourceBackupExample example);

    int updateByExampleWithBLOBs(@Param("record") UreportDataSourceBackup record, @Param("example") UreportDataSourceBackupExample example);

    int updateByExample(@Param("record") UreportDataSourceBackup record, @Param("example") UreportDataSourceBackupExample example);

    int updateByPrimaryKeySelective(UreportDataSourceBackup record);

    int updateByPrimaryKeyWithBLOBs(UreportDataSourceBackup record);

    int updateByPrimaryKey(UreportDataSourceBackup record);
}