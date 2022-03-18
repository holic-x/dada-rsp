package com.dada.manager.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.pojo.AuthorityInfo;

/**
 * <p>项目名称:dada-manager-service</p>
 * <p>包名称:com.dada.manager.service.AuthorityInfoService</p>
 * <p>文件名称:AuthorityInfoService.java</p>
 * <p>功能描述:权限、菜单管理</p>
 * <p>其他说明:    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月16日下午10:42:25 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface AuthorityInfoService {

    // 添加“权限/菜单”信息
    public String addAuthorityInfo(AuthorityInfo authorityInfo);

    // 根据“权限/菜单”id删除“权限/菜单”信息
    public boolean deleteAuthorityInfoById(String authorityId);

    // 批量删除“权限/菜单”信息
    public boolean BatchDeleteAuthorityInfo(List<String> authorityIds);

    // 根据“权限/菜单”id修改“权限/菜单”信息
    public boolean updateAuthorityInfo(AuthorityInfo authorityInfo);

    // 根据“权限/菜单”id获取“权限/菜单”信息
    public AuthorityInfo getAuthorityInfo(String authorityId);

    // 根据筛选条件查找“权限/菜单”信息
    public List<AuthorityInfo> listAuthorityInfo(AuthorityInfo authorityInfo);

    // 根据筛选条件分页查找“权限/菜单”信息
    public List<PageData> listAuthorityInfoByPage(Page page);

    // 验证重复的权限编码(唯一标识对应的模块)
    public boolean validateAuthorityCode(String authorityCode, String authorityId);
    
    // 验证重复的权限url(唯一标识对应的模块)
    public boolean validateAuthorityUrl(String authorityUrl, String authorityId);

    // 根据父类id获取子类菜单信息
    public List<AuthorityInfo> listLeafByParentId(String parentId);
}
