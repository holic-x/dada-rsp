package com.dada.mapper;

import com.dada.pojo.ReportClassify;
import com.dada.pojo.ReportClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportClassifyMapper {
    int countByExample(ReportClassifyExample example);

    int deleteByExample(ReportClassifyExample example);

    int deleteByPrimaryKey(String classifyId);

    int insert(ReportClassify record);

    int insertSelective(ReportClassify record);

    List<ReportClassify> selectByExampleWithBLOBs(ReportClassifyExample example);

    List<ReportClassify> selectByExample(ReportClassifyExample example);

    ReportClassify selectByPrimaryKey(String classifyId);

    int updateByExampleSelective(@Param("record") ReportClassify record, @Param("example") ReportClassifyExample example);

    int updateByExampleWithBLOBs(@Param("record") ReportClassify record, @Param("example") ReportClassifyExample example);

    int updateByExample(@Param("record") ReportClassify record, @Param("example") ReportClassifyExample example);

    int updateByPrimaryKeySelective(ReportClassify record);

    int updateByPrimaryKeyWithBLOBs(ReportClassify record);

    int updateByPrimaryKey(ReportClassify record);
}