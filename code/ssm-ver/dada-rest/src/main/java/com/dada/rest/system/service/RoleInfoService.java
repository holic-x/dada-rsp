package com.dada.rest.system.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.RoleInfoDTO;
import com.dada.pojo.RoleInfo;

/**
 * <p>项目名称:dada-rest</p>
 * <p>包名称:com.dada.rest.system.service.RoleService</p>
 * <p>文件名称:RoleService.java</p>
 * <p>功能描述:角色管理相关service</p>
 * <p>其他说明: 实现角色的CRUD   </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月19日下午9:48:05 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface RoleInfoService {

    // 添加角色信息
    public String addRoleInfo(RoleInfo roleInfo);

    // 根据角色id删除角色信息
    public boolean deleteRoleInfoById(RoleInfoDTO roleInfoDTO);

    // 批量删除角色信息
    public boolean BatchDeleteRoleInfo(List<String> roleIds);

    // 根据角色id修改角色信息
    public boolean updateRoleInfo(RoleInfo roleInfo);

    // 根据角色id获取角色信息
    public RoleInfo getRoleInfo(String roleId);

    // 根据筛选条件查找角色信息
    public List<RoleInfo> listRoleInfo(RoleInfoDTO roleInfoDTO);
    
    // 根据筛选条件分页查找角色信息
    public List<PageData> listRoleInfoByPage(Page page);
}
