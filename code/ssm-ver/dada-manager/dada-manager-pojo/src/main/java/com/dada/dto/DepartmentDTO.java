package com.dada.dto;

import java.io.Serializable;
import java.util.List;

public class DepartmentDTO implements Serializable {
    
    private String deptId;
    
    private String categoryId;
    
    private List<String> deptIds;
    
    private List<String> userIds;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "DepartmentDTO [deptId=" + deptId + ", categoryId=" + categoryId + ", deptIds=" + deptIds + ", userIds="
                + userIds + "]";
    }
    
}
