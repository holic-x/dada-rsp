package com.dada.manager.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.pojo.OrganizationCategory;
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

    // 添加机构分类信息
    public String addOrganCategory(OrganizationCategory category);

    // 根据机构分类id删除机构分类信息
    public boolean deleteOrganCategoryById(String categoryId);

    // 批量删除机构分类信息
    public boolean BatchDeleteOrganCategory(List<String> categoryIds);

    // 根据机构分类id修改机构分类信息
    public boolean updateOrganCategory(OrganizationCategory category);

    // 根据机构分类id获取机构分类信息
    public OrganizationCategory getOrganCategory(String categoryId);

    // 根据筛选条件查找机构分类信息
    public List<OrganizationCategory> listOrganCategory(OrganizationCategory category);

    // 根据筛选条件分页查找机构分类信息
    public List<PageData> listOrganCategoryByPage(Page page);

    // 验证重复的机构分类编码(唯一标识对应的模块)
    public boolean validateCategoryCode(String categoryCode, String categoryId);

}
