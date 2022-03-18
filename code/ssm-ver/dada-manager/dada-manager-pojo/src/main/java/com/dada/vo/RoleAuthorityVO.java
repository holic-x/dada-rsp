package com.dada.vo;

import java.io.Serializable;

public class RoleAuthorityVO implements Serializable{
    
    private String roleAuthorityId;
    
    private String authorityId;
    
    private String authorityName;
    
    private String roleName;
    
    private String categoryName;

    public String getRoleAuthorityId() {
        return roleAuthorityId;
    }

    public void setRoleAuthorityId(String roleAuthorityId) {
        this.roleAuthorityId = roleAuthorityId;
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "RoleAuthorityVO [roleAuthorityId=" + roleAuthorityId + ", authorityId=" + authorityId
                + ", authorityName=" + authorityName + ", roleName=" + roleName + ", categoryName=" + categoryName
                + "]";
    }
    
}
