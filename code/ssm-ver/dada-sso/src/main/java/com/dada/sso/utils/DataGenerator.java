package com.dada.sso.utils;
/**
 * @author hahabibu
 * 系统名称: 
 * 模块名称: 
 * 类 名 称: DataGenerator
 * 软件版权: 淘淘商城
 * 功能说明：
 * 系统版本：
 * 开发时间: create in 2019年2月20日 下午1:35:17
 * 开发说明: 
 */

import java.util.Date;
import java.util.List;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.utils.CryptoUtil;
import com.dada.common.utils.IDUtils;
import com.dada.pojo.RoleInfo;
import com.dada.pojo.UserInfo;

public class DataGenerator {
	
	private static RoleInfo adminRole;

	private static RoleInfo normalRole;
	
	private static UserInfo adminUser;
	
	private static UserInfo normalUser;
	
	public static RoleInfo getAdminRole(String categoryId,String categoryCode,String categoryName) {
		// 清空数据
		adminRole = new RoleInfo();
		adminRole.setRoleId(IDUtils.genRandomUUId());
		adminRole.setRoleCode(categoryCode+"-admin");
		adminRole.setRoleName(categoryName+"-管理员");
		adminRole.setCategoryId(categoryId);
		adminRole.setRoleDescr("默认机构管理员角色,不可删除!");
		adminRole.setCreateTime(new Date());
		adminRole.setModifyTime(new Date());
		adminRole.setDelFlag(ConstantUtils.DEL_TAG_FORBID);
		return adminRole;
	}

	public static RoleInfo getNormalRole(String categoryId,String categoryCode,String categoryName) {
		// 清空数据
		normalRole = new RoleInfo();
		normalRole.setRoleId(IDUtils.genRandomUUId());
		normalRole.setRoleCode(categoryCode+"-user");
		normalRole.setRoleName(categoryName+"-用户");
		normalRole.setCategoryId(categoryId);
		normalRole.setRoleDescr("默认机构用户角色,不可删除!");
		normalRole.setCreateTime(new Date());
		normalRole.setModifyTime(new Date());
		normalRole.setDelFlag(ConstantUtils.DEL_TAG_FORBID);
		return normalRole;
	}

	public static UserInfo getAdminUser(String roleId,String categoryId,String categoryName) {
		// 清空数据
		adminUser = new UserInfo();
		adminUser.setUserId(IDUtils.genRandomUUId());
		adminUser.setRoleId(roleId);
		adminUser.setCategoryId(categoryId);
		adminUser.setUserName(categoryName+"-默认管理员");
		adminUser.setCreateTime(new Date());
		adminUser.setModifyTime(new Date());
		adminUser.setUserState(ConstantUtils.USER_STATE_ENABLE);
		adminUser.setUserDescr("默认管理员，不可删除！");
		adminUser.setLoginAccount(ConstantUtils.DEFAULT_ADMIN_LOGIN_ACCOUNT);
		String password = ConstantUtils.DEFAULT_ADMIN_LOGIN_PASSWORD;
		// 使用自定义加密工具实现密码加密
		adminUser.setLoginPassword(CryptoUtil.encode(ConstantUtils.PASSWORD_SECRET_KEY,password));
		adminUser.setLoginImage(ConstantUtils.DEFAULT_LOGIN_IMAGE);
		return adminUser;
	}

	public static UserInfo getNormalUser(String roleId,String categoryId,String categoryName) {
		// 清空数据
		normalUser = new UserInfo();
		normalUser.setUserId(IDUtils.genRandomUUId());
		normalUser.setRoleId(roleId);
		normalUser.setCategoryId(categoryId);
		normalUser.setUserName(categoryName+"-默认用户");
		normalUser.setCreateTime(new Date());
		normalUser.setModifyTime(new Date());
		normalUser.setUserState(ConstantUtils.USER_STATE_ENABLE);
		normalUser.setUserDescr("默认用户，不可删除！");
		normalUser.setLoginAccount(ConstantUtils.DEFAULT_USER_LOGIN_ACCOUNT);
		String password = ConstantUtils.DEFAULT_USER_LOGIN_PASSWORD;
		// 使用自定义加密工具实现密码加密
		normalUser.setLoginPassword(CryptoUtil.encode(ConstantUtils.PASSWORD_SECRET_KEY,password));
		normalUser.setLoginImage(ConstantUtils.DEFAULT_LOGIN_IMAGE);
		return normalUser;
	}
}
