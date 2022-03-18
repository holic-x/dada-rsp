package com.dada.sso.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.UserInfo;
import com.dada.sso.service.RegisterService;
import com.dada.sso.utils.BaseController;
import com.dada.sso.utils.ResultManage;

@Controller
@RequestMapping("/sso")
public class RegisterController extends BaseController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> userRegister(@RequestBody UserInfo userInfo) {
        // 添加用户信息
        String id = registerService.registerUser(userInfo);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    @RequestMapping(value = "/register/organCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> categoryRegister(@RequestBody OrganizationCategory category) {
        // 添加机构信息和默认的数据
        String id = registerService.registerOrganization(category);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

}
