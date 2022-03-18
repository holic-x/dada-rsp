package com.dada.dto;

import java.io.Serializable;
import java.util.List;

public class DataDictionaryDTO implements Serializable{
    
    private String dataId;
    
    private String dataCode;

    private String dataName;

    private String dataType;

    private String dataStatus;

    private String isleaf;

    private List<String> dataIds;
    
    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode == null ? null : dataCode.trim();
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    public String getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

    public List<String> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<String> dataIds) {
        this.dataIds = dataIds;
    }

    @Override
    public String toString() {
        return "DataDictionaryDTO [dataId=" + dataId + ", dataCode=" + dataCode + ", dataName=" + dataName
                + ", dataType=" + dataType + ", dataStatus=" + dataStatus + ", isleaf=" + isleaf + ", dataIds="
                + dataIds + "]";
    }

}
