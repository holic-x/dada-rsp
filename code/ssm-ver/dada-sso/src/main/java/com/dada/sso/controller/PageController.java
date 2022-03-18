package com.dada.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**       
 * <p>项目名称:dada-manager-web</p>
 * <p>包名称:com.dada.manager.controller.PageController</p>
 * <p>文件名称:PageController.java</p>
 * <p>功能描述:页面控制器</p>
 * <p>其他说明:设置页面跳转    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月6日下午3:30:28 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>    
 */
@Controller
@RequestMapping("/sso")
public class PageController {

	/**
	 * 打开登录页面
	 */
	@RequestMapping("/page/login")
	public String showIndex() {
		return "login";
	}
	/**
	 * 打开注册页面
	 * <p>Title: showpage</p>
	 * <p>Description: </p>
	 * @param page
	 * @return
	 */
	@RequestMapping("/page/register")
	// @RequestParam与@Param区别需注意区分
    public String showpage(@RequestParam(value="url") String url) {
        return url;
    }
}