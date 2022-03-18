package com.dada.report.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dada.common.utils.CookieUtils;
import com.dada.common.utils.HttpClientUtil;
import com.dada.pojo.UserInfo;
import com.dada.report.utils.ResultManage;

public class LoginFilter implements Filter {

    private String SSO_LOGIN_URL = "http://localhost:8083/sso/page/login";
    
    private String COOKIE_NAME = "DADA_LOGIN_TOKEN";
    
    private String SSO_BASE_URL = "http://localhost:8083";
    
    private String SSO_USER_TOKEN_SERVICE = "/sso/login/token/";
    
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1、拦截请求的url(在springmvc.xml中配置登录拦截器)
        // 2、从cookie中取token(可以调用自定义的service方法,此处直接获取)
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        // 3、如果没有相应的token则强制跳转到登录页面
        if (StringUtils.isEmpty(token)) {
            // 跳转到登录页面(当前服务器跳转、重定向)
            // request.getRequestDispatcher("URL").forward(request,response);
            response.sendRedirect(SSO_LOGIN_URL);
            return;
        }
        // 4、获取token,调用dada-sso系统的服务查询用户信息
        String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
        // 5、把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(json, ResultManage.class);
        Map<String, Object> resultMap = result.getData();
        if (result.getResult_code() != 1) {
            // 判断访问是否成功(返回结果为1:访问成功、返回结果为0:访问失败,访问失败回转到登录页面)
            // request.getRequestDispatcher("URL").forward(request,response);
            response.sendRedirect(SSO_LOGIN_URL);
            // 重定向后执行return: 避免出错Cannot call sendRedirect() after the response has been committed
            return ;
        }
        // 获取对应的用户对象(loginUser)
        UserInfo userInfo = JSON.toJavaObject((JSONObject)resultMap.get("loginUser"),UserInfo.class);
        // 6、如果用户session已经过期,跳转到登录页面(针对redis缓存而言,亦避免数据更新带来的隐患)
        if (userInfo == null) {
            response.sendRedirect(SSO_LOGIN_URL);
            return ;
        }
        // 将当前登录用户存储到session域中供页面调用
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", userInfo);
        // 使用ThreadLocal存储用户信息
        // UserSession.setUserSession(session);
        // UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");
        CustomHolder.add(userInfo);
        CustomHolder.add(request);
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    public void destroy() {

    }
}