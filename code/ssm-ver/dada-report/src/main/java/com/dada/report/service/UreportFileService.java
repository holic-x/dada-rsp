package com.dada.report.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.pojo.UreportFile;

/**
 * <p>项目名称:dada-report</p>
 * <p>包名称:com.dada.report.service.UreportFileService</p>
 * <p>文件名称:UreportFileService.java</p>
 * <p>功能描述: 机构报表文件管理 </p>
 * <p>其他说明: 针对机构用户的报表文件管理(对报表文件基本信息的管理)   </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月9日下午4:24:34 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface UreportFileService {
    /**
     *  针对用户的报表管理问题,机构用户只能够管理存储在数据库中的报表数据
     *  即对应的机构报表文件存储器,而公共的报表文件则由后台管理员进行管理(备份)
     */
    
    // 新增报表数据(直接跳转到相应的报表设计器页面)

    // 编辑报表数据(此处的编辑提供两个入口:一是对报表基本信息的管理,再者是对报表内容的管理)
    public boolean updateUreportFile(UreportFile ureportFile);
    
    // 删除数据库中的报表文件
    public boolean deleteUreportFile(String fileId);
    
    // 根据fileId查找报表文件信息
    public UreportFile getUreportFile(String fileId);
    
    // 根据筛选条件分页列出报表信息
    public List<PageData> listUreportFileByPage(Page page);
    
}
