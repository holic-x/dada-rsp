package com.dada.manager.custom.mapper;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.ReportClassifyDTO;
import com.dada.pojo.ReportClassify;
import com.dada.pojo.ReportClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportCustomMapper {
    // 分页查找数据信息(分类)
    List<PageData> selectReportClassifyByPage(Page page);
    
    // 根据categoryId、deptId、classifyId删除对应的数据信息(报表分类关联关系)
    public int deleteClassifyLink(ReportClassifyDTO reportClassifyDTO);
    
    // 分页查找报表分类关联关系
    public List<PageData> listLinkList(Page page);
    
}