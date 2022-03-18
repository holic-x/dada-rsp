package com.dada.sso.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dada.pojo.UserInfo;
import com.dada.sso.dto.LoginDTO;

public interface LoginService {

	// 验证用户登录信息
	public Map<String, Object> login(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response);

	// 根据指定的token查找相应的用户信息
	public UserInfo getUserInfoByToken(String token);
	
}
