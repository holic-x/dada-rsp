package com.dada.sso.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.utils.CryptoUtil;
import com.dada.common.utils.IDUtils;
import com.dada.mapper.AuthorityInfoMapper;
import com.dada.mapper.OrganizationCategoryMapper;
import com.dada.mapper.RoleAuthorityInfoMapper;
import com.dada.mapper.RoleInfoMapper;
import com.dada.mapper.UserInfoMapper;
import com.dada.pojo.AuthorityInfo;
import com.dada.pojo.AuthorityInfoExample;
import com.dada.pojo.AuthorityInfoExample.Criteria;
import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.RoleAuthorityInfo;
import com.dada.pojo.RoleInfo;
import com.dada.pojo.UserInfo;
import com.dada.sso.service.RegisterService;
import com.dada.sso.utils.DataGenerator;

@Service("registerServiceImpl")
@Transactional
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private OrganizationCategoryMapper categoryMapper;
	
	@Autowired
	private RoleInfoMapper roleInfoMapper;
	
	@Autowired
	private AuthorityInfoMapper authorityInfoMapper;
	
	@Autowired
	private RoleAuthorityInfoMapper roleAuthorityInfoMapper;
	
	@Override
	public String registerUser(UserInfo userInfo) {
		// 默认数据
        String userId = UUID.randomUUID().toString().replaceAll("-", "");
        userInfo.setUserId(userId);
        // 设置用户账号启用状态/删除标识
        // userInfo.setUserState(ConstantUtils.USER_STATE_ENABLE);
        userInfo.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        // 使用自定义工具类实现密码加密
        userInfo.setLoginPassword(CryptoUtil.encode(ConstantUtils.PASSWORD_SECRET_KEY,userInfo.getLoginPassword()));
        // 设置创建时间修改时间
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        userInfoMapper.insert(userInfo);
        return userId;
	}
	
	private List<RoleAuthorityInfo> packRoleAuthority(String categoryId,String roleId){
	    AuthorityInfoExample authorityInfoExample = new AuthorityInfoExample();
	    Criteria criteria = authorityInfoExample.createCriteria();
	    criteria.andIsleafEqualTo(ConstantUtils.MENU_ISLEAF_YES);
	    // 查找当前平台设置的所有子模块信息
	    List<AuthorityInfo> menuList = authorityInfoMapper.selectByExample(authorityInfoExample);
	    RoleAuthorityInfo roleAuthorityInfo;
	    if(CollectionUtils.isEmpty(menuList)) {
	        return null;
	    }
	    List<RoleAuthorityInfo> roleAuthorityInfoList = new ArrayList<RoleAuthorityInfo>(menuList.size());
            
	    for(AuthorityInfo menu : menuList) {
	        roleAuthorityInfo = new RoleAuthorityInfo();
	        roleAuthorityInfo.setRoleAuthorityId(IDUtils.genRandomUUId());
	        roleAuthorityInfo.setCategoryId(categoryId);
	        roleAuthorityInfo.setRoleId(roleId);
	        roleAuthorityInfo.setAuthorityId(menu.getAuthorityId());
	        roleAuthorityInfoList.add(roleAuthorityInfo);
	    }
	    return roleAuthorityInfoList;
	}
	
	@Override
    public String registerOrganization(OrganizationCategory category) {
        // 设置主键id
        String categoryId = IDUtils.genRandomUUId();
        category.setCategoryId(categoryId);
        // 设置删除标识
        category.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        // 设置创建时间、修改时间
        category.setCreateTime(new Date());
        category.setModifyTime(new Date());
        // 添加数据
        int i = categoryMapper.insert(category);
        // 机构信息添加成功，设置默认的机构用户角色、机构用户、默认权限
        String categoryCode = category.getCategoryCode();
        String categoryName = category.getCategoryName();
        if(i>0) {
            /**
             *  设置默认的机构用户角色、相关的权限、机构用户属性
             *  为了避免用户误删除数据，需要限制至少保留一个用户超级管理员角色、账号
             *  del_tag进行标识（禁止操作、保存状态、删除状态）
             *  初始化默认添加超级管理员角色,能够访问平台提供的所有模块
             */
            // 初始化步骤1:添加默认超级管理员角色
            RoleInfo adminRole = DataGenerator.getAdminRole(categoryId, categoryCode,categoryName);
            roleInfoMapper.insert(adminRole);
            // 初始化步骤2:添加默认的超级管理员用户信息
            UserInfo adminUser = DataGenerator.getAdminUser(adminRole.getRoleId(), categoryId, categoryName);
            userInfoMapper.insert(adminUser);
            // 初始化步骤3:为指定的超级管理员角色设置默认的用户权限信息
            // 方案1:为当前超级管理员角色设定所有模块信息访问权限(针对子模块)
            // 方案2:仅仅为当前超级管理员角色设定‘系统设置’模块,但后续数据库数据变动可能对源代码访问造成影响
            List<RoleAuthorityInfo> roleAuthorityInfoList = packRoleAuthority(categoryId,adminRole.getRoleId());
            if(CollectionUtils.isEmpty(roleAuthorityInfoList)) {
                return null;
            }
            for(RoleAuthorityInfo roleAuthorityInfo : roleAuthorityInfoList) {
                int flag = roleAuthorityInfoMapper.insert(roleAuthorityInfo);
                if(flag<0) {
                    return null;
                }
            }
            // 默认超级管理员登录账号:admin/000000
        }
        return categoryId;
    }

	/*
	@Override
	public String registerOrganization(OrganizationCategory category) {
		// 设置主键id
        String categoryId = IDUtils.genRandomUUId();
        category.setCategoryId(categoryId);
        // 设置删除标识
        category.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        // 设置创建时间、修改时间
        category.setCreateTime(new Date());
        category.setModifyTime(new Date());
        // 添加数据
        int i = categoryMapper.insert(category);
        // 机构信息添加成功，设置默认的机构用户角色、机构用户、默认权限
        String categoryCode = category.getCategoryCode();
        String categoryName = category.getCategoryName();
        if(i>0) {
        	// 设置默认的机构用户角色、相关的权限、机构用户属性
        	// (为了避免用户误删除数据，需要限制至少保留一个用户超级管理员角色、账号)
        	// del_tag进行标识（禁止操作、保存状态、删除状态）
        	// 添加默认的用户角色信息
        	RoleInfo adminRole = DataGenerator.getAdminRole(categoryId, categoryCode,categoryName);
        	RoleInfo normalRole = DataGenerator.getNormalRole(categoryId, categoryCode,categoryName);
        	roleInfoMapper.insert(adminRole);
        	roleInfoMapper.insert(normalRole);
        	// 添加默认的用户信息（默认用户信息不允许修改、删除）
        	UserInfo adminUser = DataGenerator.getAdminUser(adminRole.getRoleId(), categoryId, categoryName);
        	UserInfo normalUser = DataGenerator.getNormalUser(normalRole.getRoleId(), categoryId, categoryName);
        	// 添加默认的用户权限信息
        }
        return categoryId;
	}
	*/

}
