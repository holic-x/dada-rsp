package com.dada.dto;

import java.io.Serializable;
import java.util.List;

import com.dada.pojo.RoleAuthorityInfo;

public class RoleAuthorityDTO implements Serializable{
    
    private String roleId;
    
    private String categoryId;
    
    private List<String> authorityIds;
    
    private List<RoleAuthorityInfo> roleAuthorityList;

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

    public List<String> getAuthorityIds() {
        return authorityIds;
    }

    public void setAuthorityIds(List<String> authorityIds) {
        this.authorityIds = authorityIds;
    }

    public List<RoleAuthorityInfo> getRoleAuthorityList() {
        return roleAuthorityList;
    }

    public void setRoleAuthorityList(List<RoleAuthorityInfo> roleAuthorityList) {
        this.roleAuthorityList = roleAuthorityList;
    }

    @Override
    public String toString() {
        return "RoleAuthorityDTO [roleId=" + roleId + ", categoryId=" + categoryId + ", authorityIds=" + authorityIds
                + ", roleAuthorityList=" + roleAuthorityList + "]";
    }

}
