package com.dada.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataSourceInfo {
    private String configId;

    private String configName;

    private String userId;

    private String categoryId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    private String preferState;

    private String delTag;

    private String configContent;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
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

    public String getPreferState() {
        return preferState;
    }

    public void setPreferState(String preferState) {
        this.preferState = preferState == null ? null : preferState.trim();
    }

    public String getDelTag() {
        return delTag;
    }

    public void setDelTag(String delTag) {
        this.delTag = delTag == null ? null : delTag.trim();
    }

    public String getConfigContent() {
        return configContent;
    }

    public void setConfigContent(String configContent) {
        this.configContent = configContent == null ? null : configContent.trim();
    }
}