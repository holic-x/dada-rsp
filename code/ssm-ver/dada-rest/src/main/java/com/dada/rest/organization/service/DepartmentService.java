package com.dada.rest.organization.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DepartmentDTO;
import com.dada.pojo.Department;


/**
 * <p>项目名称:dada-rest</p>
 * <p>包名称:com.dada.rest.organization.service.DataSourceService</p>
 * <p>文件名称:DepartmentService.java</p>
 * <p>功能描述:机构部门管理相关</p>
 * <p>其他说明:机构部门管理CRUD </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月25日下午1:42:15 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface DepartmentService {

    // 添加机构部门信息
    public String addDepartment(Department dept);

    // 根据机构部门id删除机构部门信息
    public boolean deleteDepartmentById(DepartmentDTO departmentDTO);

    // 批量删除机构部门信息
    public boolean BatchDeleteDepartment(DepartmentDTO departmentDTO);

    // 根据机构部门id修改机构部门信息
    public boolean updateDepartment(Department dept);

    // 根据机构部门id获取机构部门信息
    public Department getDepartment(String deptId);

    // 根据筛选条件查找机构部门信息
    public List<Department> listDepartment(DepartmentDTO departmentDTO);
    
    // 根据筛选条件分页查找机构部门信息
    public List<PageData> listDepartmentByPage(Page page);
}
