package com.dada.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.UserInfoDTO;
import com.dada.manager.service.UserInfoService;
import com.dada.manager.web.utils.BaseController;
import com.dada.manager.web.utils.ResultManage;
import com.dada.pojo.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>项目名称:dada-manager-web</p>
 * <p>包名称:com.dada.manager.controller.UserInfoController</p>
 * <p>文件名称:UserInfoController.java</p>
 * <p>功能描述:用户管理模块</p>
 * <p>其他说明:    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年4月8日下午11:31:48 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
@Controller
@RequestMapping("/manager/user")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

  /*
   // 拟定解决日期转化问题,在实体类上添加相应的日期转化格式注解  
   // @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
   @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    */
    
    /*---------------------------------------------*/
    // 添加用户信息
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, consumes="application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addUser(@RequestBody UserInfo userInfo) {
        // 添加用户信息
        String id = userInfoService.addUserInfo(userInfo);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定用户信息
    @RequestMapping(value = "/deleteUserInfos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteUserInfos(@RequestBody UserInfoDTO userInfoDTO) {
        userInfoService.BatchDeleteUserInfo(userInfoDTO.getUserIds());
        return getJsonResult();
    }

    // 修改用户信息页面跳转
    @RequestMapping(value = "/updateUserInfoUI")
    public String updateUserInfoUI(@RequestParam(value="userId") String userId,Model model) {
        UserInfo findUserInfo = userInfoService.getUserInfo(userId);
        model.addAttribute("userInfo", findUserInfo);
        return "userInfo/user_edit";
    }
    
    // 修改用户信息
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateUserInfo(@RequestBody UserInfo userInfo) {
        userInfoService.updateUserInfo(userInfo);
        return getJsonResult();
    }

    // 验证重复的登录账号
    /*
    @RequestMapping("/validateRepeatLoginAccount/{loginAccount}")
    @RequestMapping(value="/validateRepeatLoginAccount", method = RequestMethod.POST)
    @ResponseBody
       public boolean validateRepeatLoginAccount(@RequestParam(value="loginAccount") String loginAccount) {
        boolean validateRepeat = userInfoService.validateRepeatLoginAccount(loginAccount);
        return validateRepeat;
    }*/
    @RequestMapping(value="/validateRepeatLoginAccount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> validateRepeatLoginAccount(@RequestBody UserInfo userInfo) {
            boolean validateRepeat = userInfoService.validateRepeatLoginAccount(userInfo.getLoginAccount(),userInfo.getUserId());
            Map<String, Object> resultMap = new HashMap<>(4);
            resultMap.put("valid", String.valueOf(validateRepeat));
            return getJsonResult(resultMap);
        }
    
    @RequestMapping(value="/validateRepeatEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> validateRepeatEmail(@RequestBody UserInfo userInfo) {
            boolean validateRepeat = userInfoService.validateRepeatEmail(userInfo.getEmail(),userInfo.getUserId());
            Map<String, Object> resultMap = new HashMap<>(4);
            resultMap.put("valid", String.valueOf(validateRepeat));
            return getJsonResult(resultMap);
        }
    /*
    public ResponseEntity<ResultManage> validateRepeatLoginAccount(@RequestBody UserInfo userInfo) {
    public String validateRepeatLoginAccount(@RequestParam(value="loginAccount") String loginAccount) {
        boolean validateRepeat = userInfoService.validateRepeatLoginAccount(loginAccount);
        // Map<String, Boolean> map = new HashMap<>();
        // remote要求返回true or false : map.put("valid", validateRepeat);
        Map<String, String> map = new HashMap<>();
        map.put("valid", String.valueOf(validateRepeat));
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }
    */
    
    // 根据用户id查找指定用户信息
    @RequestMapping("getUserInfoById/{userId}")
    @ResponseBody
    public UserInfo getUserInfoById(@PathVariable String userId) {
        UserInfo userInfo = userInfoService.getUserInfo(userId);
        return userInfo;
    }

    // 根据筛选条件查找指定用户信息
    @RequestMapping("/listUserInfo")
    @ResponseBody
    public ResponseEntity<ResultManage> listUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        List<UserInfo> userInfoList = userInfoService.listUserInfo(userInfoDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("dataList", userInfoList);
        return getJsonResult(resultMap);
    }

    @RequestMapping("/table.action")
    public String showTable() {
        return "userInfo/user_list";
    }

    
    /*
    @RequestMapping(value = "/listUserInfoByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listUserInfoByPage(@RequestBody Page page) {
        List<PageData> userInfoList = userInfoService.listUserInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("totalPage",page.getTotalPage());
        resultMap.put("totalResult",page.getTotalResult());
        resultMap.put("dataList", userInfoList);
        return getJsonResult(resultMap);
    }
    */
    
    // dataTable对应格式
    @RequestMapping(value = "/listUserInfoByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listUserInfoByPage(@RequestBody Page page) {
        List<PageData> userInfoList = userInfoService.listUserInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data",userInfoList);
        return resultMap;
    }
    
}
