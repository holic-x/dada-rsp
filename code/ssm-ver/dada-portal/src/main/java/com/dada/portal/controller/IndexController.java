package com.dada.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dada.common.utils.CookieUtils;
import com.dada.portal.service.CommonService;
import com.dada.portal.utils.BaseController;
import com.dada.vo.MenuVO;

@Controller
@RequestMapping(value = "/portal/index")
public class IndexController extends BaseController{
    
    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;
    
    @Value("${SSO_USER_REGISTER_URL}")
    private String SSO_USER_REGISTER_URL;
    
    @Value("${SSO_CATEGORY_REGISTER_URL}")
    private String SSO_CATEGORY_REGISTER_URL;
    
    @Value("${COOKIE_NAME}")
    private String COOKIE_NAME;
    
    @Autowired
    private CommonService commonService;
    
    /*
     *  用户登录注册由dada-sso进行处理,如果用户需要注销登录,则调用相应接口实现注销
     *  此处登录页面跳转实现由拦截器进行处理,当检测用户没有相应的登录信息时则可通过拦截器
     *  请求相应dada-sso接口服务,跳转到dada-sso的登录或注册页面
     *  即将登录注册功能的实现交由dada-sso进行管理,此处只需要调用相应dada-sso服务
     *  获取对应的用户信息即可
     *  此外,拦截器请求获取相应的用户信息,将对应的数据封装到session域中,在调用的时候
     *  则通过前台一步步传入到指定的代码层,实现相应的代码逻辑
     *  附:在拦截器中借助ThreadLocal概念,实现登录用户信息的存储,使得在任意代码层
     *  均可通过工具类CustomHolder获取当前登录的用户信息,而不需要一步步回调前端传入的参数
     */
    
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 默认登录退出即为删除客户端对应cookies
        CookieUtils.deleteCookie(request, response, COOKIE_NAME);
        // 如果是使用了redis缓存，则不干涉客户端数据而是通过设置对应redis数据过期时间为0进行处理
        // 跳转到单点登录系统的登录页面(此处路径相应会有前后缀导致返回路径出错,应该设置重定向,或者是返回主页,由拦截器控制跳转页面)
        // return SSO_LOGIN_URL;
        /*
        // 重定向操作,此处可不进行处理,交由相应拦截器进行跳转操作
        try {
            response.sendRedirect(SSO_LOGIN_URL);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        */
        return "portal/index";
    }
    
    // 跳转到用户报表平台
	@RequestMapping("/home")
	public String showIndex(HttpServletRequest request, HttpServletResponse response,Model model) {
	    List<MenuVO> menuVOList = commonService.selectMenuByUser(request, response);
        // 封装用户可以访问的菜单信息
	    model.addAttribute("menuList", menuVOList);
	    // 封装导航条数据信息
	    model.addAttribute("message", "10");
	    return "portal/index";
	}
}