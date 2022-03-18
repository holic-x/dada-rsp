package com.dada.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dada.pojo.UserInfo;
import com.dada.vo.MenuVO;

public interface CommonService {
    
    // 获取token数据,调用rest服务查找用户信息
    public UserInfo getUserInfoByToken(HttpServletRequest request, HttpServletResponse response);
    
    // 根据当前登录的用户id获取用户权限信息，对应封装为菜单数据
    List<MenuVO> selectMenuByUser(HttpServletRequest request, HttpServletResponse response);
    
}
