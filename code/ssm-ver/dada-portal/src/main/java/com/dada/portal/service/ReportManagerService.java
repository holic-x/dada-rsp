package com.dada.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.ReportClassifyDTO;
import com.dada.dto.UreportManagerDTO;
import com.dada.pojo.ReportClassify;
import com.dada.pojo.UreportBackup;
import com.dada.pojo.UreportFile;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.pojo.UreportFileManager;

public interface ReportManagerService {
    
    // 报表基本信息管理相关
    // 编辑报表数据(此处的编辑提供两个入口:一是对报表基本信息的管理,再者是对报表内容的管理)
    public boolean updateUreportData(UreportFileSearchParam searchParam);
    
    // 删除数据库中的报表文件
    public boolean deleteUreportData(UreportFileSearchParam searchParam);
    
    // 根据筛选条件分页列出报表信息
    public List<PageData> listUreportDataByPage(Page page);
    
    // 根据fileId查找报表文件信息
    public UreportFileManager getUreportData(UreportFileSearchParam searchParam);
    
    // 查找尚未指定分类的报表文件信息
    public List<UreportFileManager> listReportFileByNoClassify(UreportFileSearchParam searchParam);
    
    // ------------------ 报表分类管理相关 ------------------ //
    // 添加报表分类信息
    // public String addReportClassify(ReportClassify classify);

    // 根据报表分类id删除报表分类信息
    // public boolean deleteReportClassifyById(String classifyId);

    // 根据报表分类id修改报表分类信息
    // public boolean updateReportClassify(ReportClassify classify);

    // 根据报表分类id获取报表分类信息
    // public ReportClassify getReportClassify(String classifyId);

    // 根据筛选条件查找报表分类信息
    // public List<ReportClassify> listReportClassify(ReportClassifyDTO classifyDTO);
    
    // 根据筛选条件分页查找报表分类信息
    // public List<PageData> listReportClassifyByPage(Page page);
    
    // ------------- 报表分类关联管理相关:管理对应分类下的报表信息 ------------------------ //
    
    // 设置对应报表分类下的报表关联关系
    // public boolean setReportClassifyLink(ReportClassifyDTO classifyDTO);
    
    // 根据指定条件获取对应分类下的报表信息
    // public List<PageData> listLinkList(Page page);
    
}
