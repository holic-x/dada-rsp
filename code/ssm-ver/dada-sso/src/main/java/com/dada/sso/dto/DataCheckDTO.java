package com.dada.sso.dto;

import java.io.Serializable;

public class DataCheckDTO implements Serializable{
    
    private String dataField;
    
    private String dataValue;
    
    
    public String getDataField() {
        return dataField;
    }


    public void setDataField(String dataField) {
        this.dataField = dataField;
    }


    public String getDataValue() {
        return dataValue;
    }


    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }


    @Override
    public String toString() {
        return "DataCheckDTO [dataField=" + dataField + ", dataValue=" + dataValue + "]";
    }
    
    

}
