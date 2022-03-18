package com.dada.report.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.pojo.UreportFile;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.pojo.UreportFileManager;

/**
 * <p>项目名称:dada-report</p>
 * <p>包名称:com.dada.report.service.UreportFileManagerService</p>
 * <p>文件名称:UreportFileManagerService.java</p>
 * <p>功能描述:通用报表文件管理</p>
 * <p>其他说明:根据用户指定的筛选条件,完成对不同表数据的操作  </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年4月6日下午12:12:32 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface UreportFileManagerService {
    /**
     *  针对用户的报表管理问题,机构用户只能够管理存储在数据库中的报表数据
     *  即对应的机构、部门相关的报表文件存储器,
     *  而公共的报表文件则由后台管理员进行管理(备份)-待定
     */
    
    // 新增报表数据(直接跳转到相应的报表设计器页面,在相应的文件存储器中进行控制)

    // 编辑报表数据(此处的编辑提供两个入口:一是对报表基本信息的管理,再者是对报表内容的管理)
    public boolean updateUreportFile(UreportFileSearchParam searchParam);
    
    // 删除数据库中的报表文件(可指定fileId等价于categoryId、deptId、fileName联查)
    public boolean deleteUreportFile(UreportFileSearchParam searchParam);
    
    // 根据fileId查找报表文件信息(可指定fileId等价于categoryId、deptId、fileName联查)
    public UreportFileManager getUreportFile(UreportFileSearchParam searchParam);
    
    // 根据筛选条件分页列出报表信息(page对象需指定tableName属性)
    public List<PageData> listUreportFileByPage(Page page);
    
    // 查找尚未指定分类的报表文件信息
    public List<UreportFileManager> listReportFileByNoClassify(UreportFileSearchParam searchParam);
    
}
