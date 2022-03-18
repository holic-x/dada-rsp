package com.dada.manager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dada.common.base.DataValidation;
import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.CryptoUtil;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.common.utils.SecretKey;
import com.dada.dto.UserInfoDTO;
import com.dada.manager.custom.mapper.UserInfoCustomMapper;
import com.dada.manager.service.UserInfoService;
import com.dada.mapper.UserInfoMapper;
import com.dada.pojo.UserInfo;
import com.dada.pojo.UserInfoExample;
import com.dada.pojo.UserInfoExample.Criteria;

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
        // 验证数据userInfo
      //  validateData(userInfo);
        // 默认数据
        String userId = UUID.randomUUID().toString().replaceAll("-", "");
        userInfo.setUserId(userId);
        // 设置用户账号启用状态/删除标识
        // userInfo.setUserState(ConstantUtils.USER_STATE_ENABLE);
        userInfo.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        // 使用自定义工具类实现密码加密
        userInfo.setLoginPassword(CryptoUtil.decode(ConstantUtils.PASSWORD_SECRET_KEY, userInfo.getLoginPassword()));
        // 设置创建时间修改时间
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        userInfoMapper.insert(userInfo);
        return userId;
    }

    @Override
    public boolean deleteUserInfoById(String userId) {
        int i = userInfoMapper.deleteByPrimaryKey(userId);
        return (i>0);
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        userInfo.setModifyTime(new Date());
        // 设置密码加密
        userInfo.setLoginPassword(CryptoUtil.encode(ConstantUtils.PASSWORD_SECRET_KEY,userInfo.getLoginPassword()));
        int i = userInfoMapper.updateByPrimaryKeyWithBLOBs(userInfo);
        return (i>0);
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        // 返回登录密码解密后的用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        // 防止空指针异常
        if(userInfo!=null) {
            userInfo.setLoginPassword(CryptoUtil.decode(ConstantUtils.PASSWORD_SECRET_KEY,userInfo.getLoginPassword()));
        }
        return userInfo;
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
                criteria.andEmailLike("%"+userInfoDTO.getEmail()+"%");
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
                criteria.andLoginAccountLike("%"+userInfoDTO.getLoginAccount()+"%");
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
    
    private void validateData(UserInfo userInfo) {
        // 验证数据
        // 用户角色id验证
        if(StringUtils.isEmpty(userInfo.getRoleId())) {
            throw new CommonException("用户角色id不能为空");
        }
        // 用户分类验证
        if(StringUtils.isEmpty(userInfo.getUserCategory())) {
            throw new CommonException("用户所属分类不能为空!");
        }else {
            // 如果是选择机构用户则判断是否选择了指定的机构分类，否则不作任何操作
            if(userInfo.getUserCategory().equals("1")) {
                if(StringUtils.isEmpty(userInfo.getCategoryId())) {
                    // 如果没有选择指定的机构分类则报错
                    throw new CommonException("用户所属分类机构id不能为空!");
                }
            }
        }
        
        // 用户名称验证
        if(StringUtils.isEmpty(userInfo.getUserName())) {
            // 用户名称不能为空且必须满足指定条件
          //  DataValidation.isValidDate(str)
            throw new CommonException("用户名称不能为空!");
        }
        
        // 联系方式验证
        if(StringUtils.isEmpty(userInfo.getPhone())) {
            throw new CommonException("用户联系方式不能为空!");
        }else {
            // 联系方式验证
            if(!DataValidation.isValidPhone(userInfo.getPhone())) {
                throw new CommonException("输入了无效的联系方式!");
            }
        }
        
        // 电子邮箱验证
        if(StringUtils.isEmpty(userInfo.getEmail())) {
            throw new CommonException("用户电子邮箱不能为空!");
        }else {
            // 电子邮箱验证
            if(!DataValidation.isValidEmail(userInfo.getEmail())) {
                throw new CommonException("输入了无效的电子邮箱!");
            }
        }
        
        // 用户登录信息验证
        if(StringUtils.isEmpty(userInfo.getLoginAccount())) {
            throw new CommonException("用户登录账号信息不能为空");
        }else {
            // 用户登录账号不能重复
            
            
            
            
        }
        if(StringUtils.isEmpty(userInfo.getLoginPassword())) {
            throw new CommonException("用户登录账号密码不能为空");
        }
        
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
    public boolean validateRepeatLoginAccount(String loginAccount,String currentUserId) {
        UserInfoExample userInfoExample = new UserInfoExample();
        Criteria criteria = userInfoExample.createCriteria();
        criteria.andLoginAccountEqualTo(loginAccount);
        List<UserInfo> userInfoList = userInfoMapper.selectByExampleWithBLOBs(userInfoExample);
        if(CollectionUtils.isEmpty(userInfoList)) {
            // 查找数据为空说明不存在该账号
            return false;
        }else {
            // 区分修改时针对同一个userId需要去重判断，否则验证始终无法通过
            if(!StringUtils.isEmpty(currentUserId)) {
                // 判断id是否相同，如果相同则不视为重复的情况
                if(userInfoList.get(0).getUserId().equals(currentUserId)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public boolean validateRepeatEmail(String email,String currentUserId) {
        UserInfoExample userInfoExample = new UserInfoExample();
        Criteria criteria = userInfoExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<UserInfo> userInfoList = userInfoMapper.selectByExampleWithBLOBs(userInfoExample);
        if(CollectionUtils.isEmpty(userInfoList)) {
            // 查找数据为空说明不存在该账号
            return false;
        }else {
            // 区分修改时针对同一个email需要去重判断，否则验证始终无法通过
            if(!StringUtils.isEmpty(currentUserId)) {
                // 判断id是否相同，如果相同则不视为重复的情况
                if(userInfoList.get(0).getUserId().equals(currentUserId)) {
                    return false;
                }
            }
        }
        return true;
    }

  

    
}
