package com.dada.mapper;

import com.dada.pojo.UreportFile;
import com.dada.pojo.UreportFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UreportFileMapper {
    int countByExample(UreportFileExample example);

    int deleteByExample(UreportFileExample example);

    int deleteByPrimaryKey(String fileId);

    int insert(UreportFile record);

    int insertSelective(UreportFile record);

    List<UreportFile> selectByExampleWithBLOBs(UreportFileExample example);

    List<UreportFile> selectByExample(UreportFileExample example);

    UreportFile selectByPrimaryKey(String fileId);

    int updateByExampleSelective(@Param("record") UreportFile record, @Param("example") UreportFileExample example);

    int updateByExampleWithBLOBs(@Param("record") UreportFile record, @Param("example") UreportFileExample example);

    int updateByExample(@Param("record") UreportFile record, @Param("example") UreportFileExample example);

    int updateByPrimaryKeySelective(UreportFile record);

    int updateByPrimaryKeyWithBLOBs(UreportFile record);

    int updateByPrimaryKey(UreportFile record);
}