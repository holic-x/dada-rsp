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

public interface ReportService {
    
    // ----------------机构报表文件存储器操作相关----------------------- //
    // ----------------哒哒公共报表文件存储器操作相关----------------------- //
    // ----------------机构数据源配置文件存储器操作相关----------------------- //
    // ----------------机构公共数据源配置文件存储器操作相关----------------------- //
    
    // 编辑报表数据(此处的编辑提供两个入口:一是对报表基本信息的管理,再者是对报表内容的管理)
    public boolean updateUreportData(UreportManagerDTO ureportManagerDTO);
    
    // 删除数据库中的报表文件
    public boolean deleteUreportData(UreportManagerDTO ureportManagerDTO);
    
    // 根据筛选条件分页列出报表信息(机构适用)
    public List<PageData> listUreportFileByPage(Page page);
    
    // 根据筛选条件分页列出报表信息(平台适用-公共)
    public List<PageData> listUreportBackupByPage(Page page);
    
    // 根据fileId查找报表文件信息(机构适用)
    public UreportFile getUreportFile(String fileId);
    
    // 根据fileId查找报表文件信息(平台适用-公共)
    public UreportBackup getUreportBackup(String fileId);
    
    // 报表数据打印预览
    public void previewReport(HttpServletRequest request ,HttpServletResponse response);
    
    // ------------------ 报表分类管理相关 ------------------ //
    // 添加报表分类信息
    public String addReportClassify(ReportClassify classify);

    // 根据报表分类id删除报表分类信息
    public boolean deleteReportClassifyById(String classifyId);

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
