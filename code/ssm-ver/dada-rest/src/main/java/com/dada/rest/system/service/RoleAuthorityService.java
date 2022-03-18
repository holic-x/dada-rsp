package com.dada.rest.system.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.RoleAuthorityDTO;
import com.dada.vo.MenuVO;
import com.dada.vo.RoleAuthorityVO;

/**
 * <p>项目名称:dada-rest</p>
 * <p>包名称:com.dada.rest.system.service.RoleAuthorityService</p>
 * <p>文件名称:RoleAuthorityService.java</p>
 * <p>功能描述: 角色权限权限管理</p>
 * <p>其他说明: 实现角色权限对应权限的管理   </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月21日下午7:29:05 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface RoleAuthorityService {
    
    // 批量添加角色权限权限信息
    public boolean batchAddRoleAuthority(RoleAuthorityDTO roleAuthorityDTO);  

    // 批量删除角色权限信息
    public boolean batchDeleteRoleAuthority(RoleAuthorityDTO roleAuthorityDTO);
    
    // 修改角色权限信息
    public boolean updateRoleAuthority(RoleAuthorityDTO roleAuthorityDTO);

    // 根据角色id获取角色相应的权限信息
    public List<RoleAuthorityVO> listRoleAuthorityByRoleId(String roleId);
    
    // 根据筛选条件分页查找角色权限信息
    public List<PageData> listRoleAuthorityByPage(Page page);
    
    // 根据角色id获取角色相应的权限信息（封装为菜单信息）
    public List<MenuVO> getMenuByRoleId(String roleId);
    
    // 封装当前提供的菜单信息
    public List<MenuVO> getAllMenu();
    
}
