package com.dada.mapper;

import com.dada.pojo.ReportClassifyLink;
import com.dada.pojo.ReportClassifyLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportClassifyLinkMapper {
    int countByExample(ReportClassifyLinkExample example);

    int deleteByExample(ReportClassifyLinkExample example);

    int deleteByPrimaryKey(String linkId);

    int insert(ReportClassifyLink record);

    int insertSelective(ReportClassifyLink record);

    List<ReportClassifyLink> selectByExample(ReportClassifyLinkExample example);

    ReportClassifyLink selectByPrimaryKey(String linkId);

    int updateByExampleSelective(@Param("record") ReportClassifyLink record, @Param("example") ReportClassifyLinkExample example);

    int updateByExample(@Param("record") ReportClassifyLink record, @Param("example") ReportClassifyLinkExample example);

    int updateByPrimaryKeySelective(ReportClassifyLink record);

    int updateByPrimaryKey(ReportClassifyLink record);
}