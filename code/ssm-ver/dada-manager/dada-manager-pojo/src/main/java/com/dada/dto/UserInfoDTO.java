package com.dada.dto;

import java.io.Serializable;
import java.util.List;

public class UserInfoDTO implements Serializable{

    private String roleId;

    private String categoryId;
    
    private String deptId;

    private String userName;

    private String phone;

    private String email;

    private String userState;

    private String userCategory;

    private String loginAccount;
    
    private List<String> userIds;
    
    public UserInfoDTO() {
        
    }
    
    public UserInfoDTO(String categoryId,String deptId) {
        this.categoryId = categoryId;
        this.deptId = deptId;
    }

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
    
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "UserInfoDTO [roleId=" + roleId + ", categoryId=" + categoryId + ", deptId=" + deptId + ", userName="
                + userName + ", phone=" + phone + ", email=" + email + ", userState=" + userState + ", userCategory="
                + userCategory + ", loginAccount=" + loginAccount + ", userIds=" + userIds + "]";
    }

}
