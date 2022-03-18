package com.dada.report.dto;

import com.dada.report.pojo.UreportFileManager;

/**
 * <p>项目名称:dada-manager-pojo</p>
 * <p>包名称:com.dada.report.dto.UreportFileSearchParam</p>
 * <p>文件名称:UreportFileSearchParam.java</p>
 * <p>功能描述: 封装查找参数(根据不同的表名操作不同的表数据)</p>
 * <p>其他说明:    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年4月6日上午11:31:32 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public class UreportFileSearchParam {
    
    /**
     * 表名：
     * 机构相关存储器：ureport_file、ureport_data_source_file
     * 平台公共存储器：ureport_backup、ureport_data_source_backup
     */
    private String tableName;
    
    /**
     * 操作实体类(用于数据添加、修改等操作)
     */
    private UreportFileManager ureportFileManager;
    
    /**
     * 查找参数(用于查找指定的数据信息)
     * 此处根据需求设定涉及如下参数
     * 所属机构id:categoryId
     * 所属部门id:deptId
     * 报表名称:fileName
     * 报表id:报表唯一标识
     */
    private String categoryId;
    
    private String deptId;
    
    private String fileName;
    
    private String fileId;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public UreportFileManager getUreportFileManager() {
        return ureportFileManager;
    }

    public void setUreportFileManager(UreportFileManager ureportFileManager) {
        this.ureportFileManager = ureportFileManager;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "UreportFileSearchParam [tableName=" + tableName + ", ureportFileManager=" + ureportFileManager
                + ", categoryId=" + categoryId + ", deptId=" + deptId + ", fileName=" + fileName + ", fileId=" + fileId
                + "]";
    }
    
}
