package com.dada.dto;

import java.io.Serializable;
import java.util.List;

public class RoleInfoDTO implements Serializable{
    
    private String roleId;
    
    private String categoryId;
    
    private List<String> roleIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "RoleInfoDTO [roleId=" + roleId + ", categoryId=" + categoryId + ", roleIds=" + roleIds + "]";
    }
}
