package com.dada.rest.organization.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.UserInfoDTO;
import com.dada.pojo.UserInfo;

/**       
 * <p>项目名称:dada-manager-service</p>
 * <p>包名称:com.dada.manager.service.UserInfoService</p>
 * <p>文件名称:UserInfoService.java</p>
 * <p>功能描述:用户Service</p>
 * <p>其他说明:实现用户信息基本的CRUD操作    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月6日下午2:35:33 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>    
 */
public interface UserInfoService {

    // 添加用户信息
    public String addUserInfo(UserInfo userInfo);
    
    // 根据用户id删除用户信息
    public boolean deleteUserInfoById(String userId);
    
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
    
}
