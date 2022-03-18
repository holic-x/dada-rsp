package com.dada.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuthorityInfo {
    private String authorityId;

    private String parentId;

    private String authorityCode;

    private String authorityName;

    private String authorityIcon;

    private String authorityUrl;

    private String isleaf;

    private String delFlag;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifyTime;

    private String authorityDescr;

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId == null ? null : authorityId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode == null ? null : authorityCode.trim();
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName == null ? null : authorityName.trim();
    }

    public String getAuthorityIcon() {
        return authorityIcon;
    }

    public void setAuthorityIcon(String authorityIcon) {
        this.authorityIcon = authorityIcon == null ? null : authorityIcon.trim();
    }

    public String getAuthorityUrl() {
        return authorityUrl;
    }

    public void setAuthorityUrl(String authorityUrl) {
        this.authorityUrl = authorityUrl == null ? null : authorityUrl.trim();
    }

    public String getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf == null ? null : isleaf.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
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

    public String getAuthorityDescr() {
        return authorityDescr;
    }

    public void setAuthorityDescr(String authorityDescr) {
        this.authorityDescr = authorityDescr == null ? null : authorityDescr.trim();
    }
}