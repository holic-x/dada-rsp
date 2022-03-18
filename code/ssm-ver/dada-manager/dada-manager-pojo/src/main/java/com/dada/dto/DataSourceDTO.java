package com.dada.dto;

import java.io.Serializable;
import java.util.List;

import com.dada.config.DataSourceConfig;

public class DataSourceDTO implements Serializable {
    
    private String configId;
    
    private String categoryId;
    
    private String userId;
    
    private String configName;
    
    private String preferState;
    
    private List<String> configIds;
    
    private DataSourceConfig dataSourceConfig;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getPreferState() {
        return preferState;
    }

    public void setPreferState(String preferState) {
        this.preferState = preferState;
    }

    public List<String> getConfigIds() {
        return configIds;
    }

    public void setConfigIds(List<String> configIds) {
        this.configIds = configIds;
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    @Override
    public String toString() {
        return "DataSourceDTO [configId=" + configId + ", categoryId=" + categoryId + ", userId=" + userId
                + ", configName=" + configName + ", preferState=" + preferState + ", configIds=" + configIds
                + ", dataSourceConfig=" + dataSourceConfig + "]";
    }

    

}
