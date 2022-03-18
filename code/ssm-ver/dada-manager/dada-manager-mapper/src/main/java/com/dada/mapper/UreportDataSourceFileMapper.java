package com.dada.mapper;

import com.dada.pojo.UreportDataSourceFile;
import com.dada.pojo.UreportDataSourceFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UreportDataSourceFileMapper {
    int countByExample(UreportDataSourceFileExample example);

    int deleteByExample(UreportDataSourceFileExample example);

    int deleteByPrimaryKey(String fileId);

    int insert(UreportDataSourceFile record);

    int insertSelective(UreportDataSourceFile record);

    List<UreportDataSourceFile> selectByExampleWithBLOBs(UreportDataSourceFileExample example);

    List<UreportDataSourceFile> selectByExample(UreportDataSourceFileExample example);

    UreportDataSourceFile selectByPrimaryKey(String fileId);

    int updateByExampleSelective(@Param("record") UreportDataSourceFile record, @Param("example") UreportDataSourceFileExample example);

    int updateByExampleWithBLOBs(@Param("record") UreportDataSourceFile record, @Param("example") UreportDataSourceFileExample example);

    int updateByExample(@Param("record") UreportDataSourceFile record, @Param("example") UreportDataSourceFileExample example);

    int updateByPrimaryKeySelective(UreportDataSourceFile record);

    int updateByPrimaryKeyWithBLOBs(UreportDataSourceFile record);

    int updateByPrimaryKey(UreportDataSourceFile record);
}