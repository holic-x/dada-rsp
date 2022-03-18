package com.dada.sso.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dada.pojo.UserInfo;
import com.dada.sso.dto.LoginDTO;
import com.dada.sso.service.LoginService;
import com.dada.sso.utils.BaseController;
import com.dada.sso.utils.ResultManage;

 
@Controller
@RequestMapping("/sso")
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultManage> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resultMap = loginService.login(loginDTO, request, response);
		return getJsonResult(resultMap);
	}

	@RequestMapping("/login/token/{token}")
    @ResponseBody
    public ResponseEntity<ResultManage> getUserByToken(@PathVariable String token) {
        // 模拟获取用户信息
	    Map<String, Object> resultMap = new HashMap<>();
        UserInfo userInfo = loginService.getUserInfoByToken(token);
        resultMap.put("loginUser", userInfo);
        return getJsonResult(resultMap);
    }
	
	/*
	@RequestMapping("/login/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		Map<String, Object> resultMap = new HashMap<>();
		UserInfo userInfo = loginService.getUserInfoByToken(token);
		resultMap.put("loginUser", userInfo);
		// 支持jsonp调用
		if (StringUtils.isNotBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(getJsonResult(resultMap));
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return getJsonResult(resultMap);
	}
	*/
	
	/*
	@RequestMapping("/login/session/{jessionid}")
    @ResponseBody
    public Object getUser(@PathVariable String jessionid, String callback,HttpServletRequest request) {
	    // 判断当前jessionid是否存在,如果存在返回相应的session数据
	    HttpSession session = request.getSession();
	    // 不需要访问数据库，而是从session域中获取,如果获取失败,则从数据库中查找(类似redis缓存)
        UserInfo loginUser = (UserInfo) session.getAttribute(jessionid);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("loginUser", loginUser);
        // 支持jsonp调用
        if (StringUtils.isNotBlank(callback)) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(getJsonResult(resultMap));
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return getJsonResult(resultMap);
    }
    */
}
