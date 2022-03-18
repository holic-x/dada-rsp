package com.dada.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Department {
    private String deptId;

    private String deptName;

    private String parentId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    private String delTag;

    private String categoryId;

    private String deptDescr;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDelTag() {
        return delTag;
    }

    public void setDelTag(String delTag) {
        this.delTag = delTag == null ? null : delTag.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getDeptDescr() {
        return deptDescr;
    }

    public void setDeptDescr(String deptDescr) {
        this.deptDescr = deptDescr == null ? null : deptDescr.trim();
    }
}