package com.dada.portal.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.CookieUtils;
import com.dada.common.utils.HttpClientUtil;
import com.dada.common.utils.UserSession;
import com.dada.pojo.UserInfo;
import com.dada.portal.utils.ResultManage;


public class LoginInterceptor implements HandlerInterceptor {

    @Value("${COOKIE_NAME}")
    private String COOKIE_NAME;

    @Value("${LOGIN_USER_SESSION_NAME}")
    private String LOGIN_USER_SESSION_NAME;
    
    @Value("${JSESSIONID}")
    private String JSESSIONID;
    
    @Value("${JSESSION_EXPIRE}")
    private String JSESSION_EXPIRE;
    
    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;

    @Value("${SSO_USER_TOKEN_SERVICE}")
    private String SSO_USER_TOKEN_SERVICE;

    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;

    /**
     * cookie与redis结合使用
     * cookie与session结合使用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 1、拦截请求的url(在springmvc.xml中配置登录拦截器)
        
        // 解决用户重复登录的情况
        // a.获取当前客户端用户的cookie存储的数据

        // 判断当前session域中是否存在用户信息(同一个浏览器session数据是一样的)
       /* HttpSession session = request.getSession();
        UserInfo loginUser = (UserInfo) session.getAttribute(LOGIN_USER_SESSION_NAME);
        if(loginUser!=null) {
            // 如果查找的用户信息不为空,则直接放行
            return true;
        }*/

        // 2、从cookie中取token(可以调用自定义的service方法,此处直接获取)
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        // 3、如果没有相应的token则强制跳转到登录页面
        if (StringUtils.isEmpty(token)) {
            // 跳转到登录页面(当前服务器跳转、重定向)
            // request.getRequestDispatcher("URL").forward(request,response);
            response.sendRedirect(SSO_LOGIN_URL);
            return false;
        }
        // 4、获取token,调用dada-sso系统的服务查询用户信息
        String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
        // 5、把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(json, ResultManage.class);
        if(result==null) {
            throw new CommonException("dada-portal:调用dada-sso服务返回结果为null");
        }
        Map<String, Object> resultMap = result.getData();
        if (result.getResult_code() != 1) {
            // 判断访问是否成功(返回结果为1:访问成功、返回结果为0:访问失败,访问失败回转到登录页面)
            // request.getRequestDispatcher("URL").forward(request,response);
            response.sendRedirect(SSO_LOGIN_URL);
            return false;
        }
        // 获取对应的用户对象(loginUser)
        UserInfo userInfo = JSON.toJavaObject((JSONObject)resultMap.get("loginUser"),UserInfo.class);
        // 6、如果用户数据已经过期,跳转到登录页面(针对redis缓存而言,亦避免数据更新带来的隐患)
        if (userInfo == null) {
            response.sendRedirect(SSO_LOGIN_URL);
            return false;
        }
        /**
         *  将当前登录用户存储到session域中供页面调用
         *  逻辑实现：
         *  1.普通的实现方式
         *  在登录的时候查找相应的用户信息并将用户信息封装到session域中
         *  拦截器实现:通过判断session域中是否存在登录用户信息来决定页面拦截
         *  
         *  2.调用SSO单点登录服务系统实现
         *  通过cookie+redis或cookie+session形式实现用户信息存储
         *  平台调用SSO服务,通过SSO提供的查找用户信息的方法判断当前用户登录状态来决定页面拦截
         *  拦截器将每次查找到的用户信息封装到对应服务器的session域中供页面调用
         *  
         *  (参考网上数据,session域数据获取可以通过${sessionScope.data.propertie}形式获取)
         *  此外需要考虑相关的用户登录退出和用户切换关系
         */
        // 将当前登录用户存储到session域中供页面调用
        // request.getSession().setAttribute("loginUser", userInfo);
        
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", userInfo);
        // 使用ThreadLocal存储用户信息
        UserSession.setUserSession(session);
        UserInfo user = (UserInfo)UserSession.getAttribute("loginUser");
        // 7、如果数据没有过期,将数据放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

}
