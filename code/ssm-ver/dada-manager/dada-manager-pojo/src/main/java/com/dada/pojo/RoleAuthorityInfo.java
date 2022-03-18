package com.dada.pojo;

public class RoleAuthorityInfo {
    private String roleAuthorityId;

    private String authorityId;

    private String roleId;

    private String categoryId;

    public String getRoleAuthorityId() {
        return roleAuthorityId;
    }

    public void setRoleAuthorityId(String roleAuthorityId) {
        this.roleAuthorityId = roleAuthorityId == null ? null : roleAuthorityId.trim();
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId == null ? null : authorityId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }
}