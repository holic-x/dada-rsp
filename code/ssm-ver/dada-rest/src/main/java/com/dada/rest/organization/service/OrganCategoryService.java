package com.dada.rest.organization.service;

import java.util.List;

import com.dada.dto.UserInfoDTO;
import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.UserInfo;

/**
 * <p>项目名称:dada-manager-service</p>
 * <p>包名称:com.dada.manager.service.OrganCategoryService</p>
 * <p>文件名称:OrganCategoryService.java</p>
 * <p>功能描述: 机构分类CRUD </p>
 * <p>其他说明: </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月17日上午12:22:11 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface OrganCategoryService {

    // 根据机构分类id修改机构分类信息
    public boolean updateOrganCategory(OrganizationCategory category);

    // 根据机构分类id获取机构分类信息
    public OrganizationCategory getOrganCategory(String categoryId);

    // 根据筛选条件查找机构分类信息
    public List<OrganizationCategory> listOrganCategory(OrganizationCategory category);
}
