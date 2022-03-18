package com.dada.sso.dto;

import java.io.Serializable;

/**
 * @author hahabibu
 * 系统名称: 
 * 模块名称: 
 * 类 名 称: LoginDTO
 * 软件版权: 淘淘商城
 * 功能说明：
 * 系统版本：
 * 开发时间: create in 2019年2月21日 下午1:21:36
 * 开发说明: 
 */
public class LoginDTO implements Serializable {
	private String loginAccount;
	private String loginPassword;
	private String categoryCode;
	private String saveStatus;
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getSaveStatus() {
		return saveStatus;
	}
	public void setSaveStatus(String saveStatus) {
		this.saveStatus = saveStatus;
	}
	@Override
	public String toString() {
		return "LoginDTO [loginAccount=" + loginAccount + ", loginPassword=" + loginPassword + ", categoryCode="
				+ categoryCode + ", saveStatus=" + saveStatus + "]";
	}
}
