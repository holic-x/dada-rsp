package com.dada.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.RoleAuthorityDTO;
import com.dada.dto.RoleInfoDTO;
import com.dada.pojo.RoleInfo;
import com.dada.vo.MenuVO;
import com.dada.vo.RoleAuthorityVO;
/**
 * <p>项目名称:dada-portal</p>
 * <p>包名称:com.dada.portal.service.SystemService</p>
 * <p>文件名称:SystemService.java</p>
 * <p>功能描述: 平台-系统管理相关Service</p>
 * <p>其他说明: 包含角色管理、角色权限管理   </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月25日下午8:40:57 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface SystemService {
    // -----------------------------------用户角色管理相关-----------------------------------------------//
    // 添加角色信息
    public String addRoleInfo(RoleInfo roleInfo,HttpServletRequest request, HttpServletResponse response);

    // 根据角色id删除角色信息
    public boolean deleteRoleInfoById(RoleInfoDTO roleInfoDTO);

    // 批量删除角色信息
    // public boolean BatchDeleteRoleInfo(RoleInfoDTO roleInfoDTO);

    // 根据角色id修改角色信息
    public boolean updateRoleInfo(RoleInfo roleInfo);

    // 根据角色id获取角色信息
    public RoleInfo getRoleInfo(String roleId);

    // 根据筛选条件分页查找角色信息
    public List<PageData> listRoleInfoByPage(Page page);

    // -----------------------------------用户角色权限管理相关-----------------------------------------------//

    // 修改角色权限信息
    public boolean updateRoleAuthority(RoleAuthorityDTO roleAuthorityDTO);

    // 根据角色id获取角色相应的权限信息
    public List<RoleAuthorityVO> listRoleAuthorityByRoleId(String roleId);

    // 获取当前系统提供的所有封装好的菜单信息
    List<MenuVO> selectAllMenu();
    

}
