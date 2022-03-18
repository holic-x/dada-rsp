package com.dada.portal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DepartmentDTO;
import com.dada.dto.UserInfoDTO;
import com.dada.pojo.Department;
import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.RoleInfo;
import com.dada.pojo.UserInfo;
import com.dada.portal.utils.ResultManage;

/**
 * <p>项目名称:dada-portal</p>
 * <p>包名称:com.dada.portal.service.OrganCategoryService</p>
 * <p>文件名称:OrganCategoryService.java</p>
 * <p>功能描述: 机构信息维护 </p>
 * <p>其他说明: 机构基本信息管理、机构用户管理   </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月3日上午1:19:27 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface OrganizationService {
    
    // ------------机构基本信息管理相关------------------------------ //
    
    // 根据机构分类id修改机构分类信息
    public boolean updateOrganCategory(OrganizationCategory category);

    // 根据机构分类id获取机构分类信息
    public OrganizationCategory getOrganCategory(String categoryId);

    // 验证重复的机构分类编码(唯一标识对应的模块)
    public boolean checkCategoryData(String dataValue,String dataField,String categoryId);
    
    // ------------机构用户信息管理相关------------------------------ //
    
    // 添加用户信息
    public String addUserInfo(UserInfo userInfo);
    
    // 批量删除用户信息
    public boolean BatchDeleteUserInfo(List<String> userIds);

    // 根据用户id修改用户信息
    public boolean updateUserInfo(UserInfo userInfo);

    // 根据用户id获取用户信息
    public UserInfo getUserInfo(String userId);

    // 根据筛选条件查找用户信息
    public List<UserInfo> listUserInfo(UserInfoDTO userInfoDTO);
    
    // 根据筛选条件分页查找用户信息
    public List<PageData> listUserInfoByPage(Page page);
    
    // 验证机构用户编辑的数据(校验的数据、校验的数据类型-即校验哪个字段)
    public boolean checkUserData(String dataValue,String dataField,String userId);

    // 根据当前登录的用户所属机构获取相应的角色信息
    List<RoleInfo> listRoleInfoByCategoryId(String categoryId);
    
    // -----------------------------------机构部门管理相关-----------------------------------------------//
  
    // 添加机构部门信息
    public String addDepartment(Department dept);

    // 根据机构部门id删除机构部门信息
    public boolean deleteDepartmentById(DepartmentDTO departmentDTO);

    // 根据机构部门id修改机构部门信息
    public boolean updateDepartment(Department dept);

    // 根据机构部门id获取机构部门信息
    public Department getDepartment(String deptId);

    // 根据筛选条件查找机构部门信息
    public List<Department> listDepartment(DepartmentDTO departmentDTO);
    
    // 根据筛选条件分页查找机构部门信息
    public List<PageData> listDepartmentByPage(Page page);
    
    // 更新指定用户状态(指定的机构、部门:deptId默认-1)
    public void updateUserFromDept(DepartmentDTO deptDTO);
    
}
