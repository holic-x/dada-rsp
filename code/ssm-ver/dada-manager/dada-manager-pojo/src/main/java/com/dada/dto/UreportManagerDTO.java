package com.dada.dto;

import java.io.Serializable;

import com.dada.common.utils.Page;
import com.dada.pojo.UreportBackup;
import com.dada.pojo.UreportDataSourceBackup;
import com.dada.pojo.UreportDataSourceFile;
import com.dada.pojo.UreportFile;

/**
 * <p>项目名称:dada-manager-pojo</p>
 * <p>包名称:com.dada.dto.UreportManagerDTO</p>
 * <p>文件名称:UreportManagerDTO.java</p>
 * <p>功能描述:UreportManagerDTO报表管理器操作入参 </p>
 * <p>其他说明: 接收报表管理相关的参数   </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月9日下午4:54:01 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public class UreportManagerDTO implements Serializable {
    
    private String fileId;

    private String operatorType;
    
    private UreportFile ureportFile;
    
    private UreportBackup ureportBackup;
    
    private UreportDataSourceFile ureportDataSourceFile;
    
    private UreportDataSourceBackup ureportDataSourceBackup;
    
    // 分页筛选参数
    private Page page;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public UreportFile getUreportFile() {
        return ureportFile;
    }

    public void setUreportFile(UreportFile ureportFile) {
        this.ureportFile = ureportFile;
    }

    public UreportBackup getUreportBackup() {
        return ureportBackup;
    }

    public void setUreportBackup(UreportBackup ureportBackup) {
        this.ureportBackup = ureportBackup;
    }

    public UreportDataSourceFile getUreportDataSourceFile() {
        return ureportDataSourceFile;
    }

    public void setUreportDataSourceFile(UreportDataSourceFile ureportDataSourceFile) {
        this.ureportDataSourceFile = ureportDataSourceFile;
    }

    public UreportDataSourceBackup getUreportDataSourceBackup() {
        return ureportDataSourceBackup;
    }

    public void setUreportDataSourceBackup(UreportDataSourceBackup ureportDataSourceBackup) {
        this.ureportDataSourceBackup = ureportDataSourceBackup;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "UreportManagerDTO [fileId=" + fileId + ", operatorType=" + operatorType + ", ureportFile=" + ureportFile
                + ", ureportBackup=" + ureportBackup + ", ureportDataSourceFile=" + ureportDataSourceFile
                + ", ureportDataSourceBackup=" + ureportDataSourceBackup + ", page=" + page + "]";
    }

}
