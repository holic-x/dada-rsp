package com.dada.dto;

import java.io.Serializable;
import java.util.List;

import com.dada.pojo.ReportClassifyLink;

public class ReportClassifyDTO implements Serializable {

    private String classifyId;

    private String userId;

    private String categoryId;

    private String deptId;
    
    private List<String> classifyIds;
    
    private List<ReportClassifyLink> linkList;
    
    private String operatorType;
    
    // 用于删除关联的列表
    private List<String> linkIds;

    // 用于获取要添加关联分类的报表id列表
    private List<String> reportIds;
    
    // 用于设置分类官僚报表的发布状态
    private String hideStatus;
    
    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    
    

    public List<String> getClassifyIds() {
        return classifyIds;
    }

    public void setClassifyIds(List<String> classifyIds) {
        this.classifyIds = classifyIds;
    }

    public List<ReportClassifyLink> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<ReportClassifyLink> linkList) {
        this.linkList = linkList;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public List<String> getLinkIds() {
        return linkIds;
    }

    public void setLinkIds(List<String> linkIds) {
        this.linkIds = linkIds;
    }

    public List<String> getReportIds() {
        return reportIds;
    }

    public void setReportIds(List<String> reportIds) {
        this.reportIds = reportIds;
    }

    public String getHideStatus() {
        return hideStatus;
    }

    public void setHideStatus(String hideStatus) {
        this.hideStatus = hideStatus;
    }

    @Override
    public String toString() {
        return "ReportClassifyDTO [classifyId=" + classifyId + ", userId=" + userId + ", categoryId=" + categoryId
                + ", deptId=" + deptId + ", classifyIds=" + classifyIds + ", linkList=" + linkList + ", operatorType="
                + operatorType + ", linkIds=" + linkIds + ", reportIds=" + reportIds + ", hideStatus=" + hideStatus
                + "]";
    }
}
