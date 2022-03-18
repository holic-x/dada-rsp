package com.dada.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrganizationCategory {
    private String categoryId;

    private String categoryCode;

    private String categoryName;

    private String disableStatus;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    private String delTag;

    private String categoryDescr;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getDisableStatus() {
        return disableStatus;
    }

    public void setDisableStatus(String disableStatus) {
        this.disableStatus = disableStatus == null ? null : disableStatus.trim();
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

    public String getCategoryDescr() {
        return categoryDescr;
    }

    public void setCategoryDescr(String categoryDescr) {
        this.categoryDescr = categoryDescr == null ? null : categoryDescr.trim();
    }
}