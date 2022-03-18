package com.dada.rest.organization.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.UserInfoDTO;
import com.dada.manager.custom.mapper.UserInfoCustomMapper;
import com.dada.mapper.UserInfoMapper;
import com.dada.pojo.UserInfo;
import com.dada.pojo.UserInfoExample;
import com.dada.pojo.UserInfoExample.Criteria;
import com.dada.rest.organization.service.UserInfoService;

/**       
 * <p>项目名称:dada-manager-service</p>
 * <p>包名称:com.dada.manager.service.impl.UserInfoServiceImpl</p>
 * <p>文件名称:UserInfoServiceImpl.java</p>
 * <p>功能描述:用户ServiceImpl</p>
 * <p>其他说明:实现用户信息基本的CRUD操作  </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月6日下午3:08:49 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>    
 */
@Service("userInfoServiceImpl")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    
    @Autowired
    private UserInfoCustomMapper userInfoCustomMapper;
    
    @Override
    public String addUserInfo(UserInfo userInfo) {
        int i = userInfoMapper.insert(userInfo);
        if(i>0) {
            return userInfo.getRoleId();
        }
        return null;
    }

    @Override
    public boolean deleteUserInfoById(String userId) {
        int i = userInfoMapper.deleteByPrimaryKey(userId);
        return (i>0);
    }

    @Override
    public boolean BatchDeleteUserInfo(List<String> userIds) {
        if(CollectionUtils.isEmpty(userIds)) {
            throw new CommonException("请至少选择一条数据!");
        }
        for(String userId : userIds) {
            userInfoMapper.deleteByPrimaryKey(userId);
        }
        return true;
    }
    
    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        userInfo.setModifyTime(new Date());
        int i = userInfoMapper.updateByPrimaryKeyWithBLOBs(userInfo);
        return (i>0);
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<UserInfo> listUserInfo(UserInfoDTO userInfoDTO) {
        // 封装筛选条件
        UserInfoExample userInfoExample = new UserInfoExample();
        Criteria criteria = userInfoExample.createCriteria();
        if(userInfoDTO!=null) {
            // 根据角色分类查找用户信息
            if(!StringUtils.isEmpty(userInfoDTO.getRoleId())) {
                criteria.andRoleIdEqualTo(userInfoDTO.getRoleId());
            }
            // 根据机构分类查找用户信息
            if(!StringUtils.isEmpty(userInfoDTO.getCategoryId())) {
                criteria.andCategoryIdEqualTo(userInfoDTO.getCategoryId());
            }
            // 根据部门分类查找用户信息
            if(!StringUtils.isEmpty(userInfoDTO.getDeptId())){
                criteria.andDeptIdEqualTo(userInfoDTO.getDeptId());
            }
            // 根据用户名称模糊查找用户信息
            if(!StringUtils.isEmpty(userInfoDTO.getUserName())) {
                criteria.andUserNameLike("%"+userInfoDTO.getUserName()+"%");
            }
            // 根据用户联系方式模糊查找用户信息
            if(!StringUtils.isEmpty(userInfoDTO.getPhone())){
                criteria.andPhoneLike("%"+userInfoDTO.getPhone()+"%");
            }
            // 根据用户邮箱模糊查找用户信息
            if(!StringUtils.isEmpty(userInfoDTO.getEmail())){
               // criteria.andEmailLike("%"+userInfoDTO.getEmail()+"%");
                criteria.andEmailEqualTo(userInfoDTO.getEmail());
            }
            // 根据用户账号状态查找用户信息
            if(!StringUtils.isEmpty(userInfoDTO.getUserState())) {
                criteria.andUserStateEqualTo(userInfoDTO.getUserState());
            }
            // 根据用户是普通用户还是归属某个机构分类划分查找
            if(!StringUtils.isEmpty(userInfoDTO.getUserCategory())) {
                criteria.andUserCategoryEqualTo(userInfoDTO.getUserCategory());
            }
            // 根据用户登录账号模糊查找用户信息
            if(!StringUtils.isEmpty(userInfoDTO.getLoginAccount())) {
                // criteria.andLoginAccountLike("%"+userInfoDTO.getLoginAccount()+"%");
                criteria.andLoginAccountEqualTo(userInfoDTO.getLoginAccount());
            }
            // 默认查找未删除的用户信息
            criteria.andDelTagEqualTo("0");
        }
        return userInfoMapper.selectByExampleWithBLOBs(userInfoExample);
    }

    @Override
    public List<PageData> listUserInfoByPage(Page page) {
        return userInfoCustomMapper.selectUserInfoByPage(page);
    }

}
