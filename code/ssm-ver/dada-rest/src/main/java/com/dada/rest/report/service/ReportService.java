package com.dada.rest.report.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.ReportClassifyDTO;
import com.dada.pojo.ReportClassify;
import com.dada.pojo.ReportClassifyLink;


/**
 * <p>项目名称:dada-report</p>
 * <p>包名称:com.dada.report.service.ReportClassifyService</p>
 * <p>文件名称:ReportClassifyService.java</p>
 * <p>功能描述:报表分类管理相关    </p>
 * <p>其他说明:实现报表分类管理    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年4月5日上午9:30:59 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface ReportService {
    
    // ------------- 报表分类管理相关 ------------------------ //

    // 添加报表分类信息
    public String addReportClassify(ReportClassify classify);

    // 根据报表分类id删除报表分类信息
    public boolean deleteReportClassifyById(ReportClassifyDTO classifyDTO);

    // 批量删除报表分类信息
    public boolean BatchDeleteReportClassify(ReportClassifyDTO classifyDTO);

    // 根据报表分类id修改报表分类信息
    public boolean updateReportClassify(ReportClassify classify);

    // 根据报表分类id获取报表分类信息
    public ReportClassify getReportClassify(String classifyId);

    // 根据筛选条件查找报表分类信息
    public List<ReportClassify> listReportClassify(ReportClassifyDTO classifyDTO);
    
    // 根据筛选条件分页查找报表分类信息
    public List<PageData> listReportClassifyByPage(Page page);
    
    // ------------- 报表分类关联管理相关:管理对应分类下的报表信息 ------------------------ //
    
    // 设置对应报表分类下的报表关联关系
    public boolean setReportClassifyLink(ReportClassifyDTO classifyDTO);
    
    // 根据指定条件获取对应分类下的报表信息
    public List<PageData> listLinkList(Page page);
    
    // 修改对应报表分类下的报表关联关系(修改报表隐藏状态-hideStatus)
    public boolean setLinkHideStatus(ReportClassifyDTO classifyDTO);
    
}
