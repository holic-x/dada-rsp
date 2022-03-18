package com.dada.mapper;

import com.dada.pojo.UreportBackup;
import com.dada.pojo.UreportBackupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UreportBackupMapper {
    int countByExample(UreportBackupExample example);

    int deleteByExample(UreportBackupExample example);

    int deleteByPrimaryKey(String fileId);

    int insert(UreportBackup record);

    int insertSelective(UreportBackup record);

    List<UreportBackup> selectByExampleWithBLOBs(UreportBackupExample example);

    List<UreportBackup> selectByExample(UreportBackupExample example);

    UreportBackup selectByPrimaryKey(String fileId);

    int updateByExampleSelective(@Param("record") UreportBackup record, @Param("example") UreportBackupExample example);

    int updateByExampleWithBLOBs(@Param("record") UreportBackup record, @Param("example") UreportBackupExample example);

    int updateByExample(@Param("record") UreportBackup record, @Param("example") UreportBackupExample example);

    int updateByPrimaryKeySelective(UreportBackup record);

    int updateByPrimaryKeyWithBLOBs(UreportBackup record);

    int updateByPrimaryKey(UreportBackup record);
}