package com.dada.rest.organization.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.UserInfoDTO;
import com.dada.pojo.UserInfo;
import com.dada.rest.organization.service.UserInfoService;
import com.dada.rest.utils.BaseController;
import com.dada.rest.utils.ResultManage;

@Controller
@RequestMapping("/rest/organCategory/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    // 添加用户信息
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
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

    // 修改用户信息
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateUserInfo(@RequestBody UserInfo userInfo) {
        userInfoService.updateUserInfo(userInfo);
        return getJsonResult();
    }

    // 根据用户id查找指定用户信息
    @RequestMapping("getUserInfoById/{userId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getUserInfoById(@PathVariable String userId) {
        UserInfo userInfo = userInfoService.getUserInfo(userId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", userInfo);
        return getJsonResult(resultMap);
    }

    // 根据筛选条件查找指定用户信息
    @RequestMapping("/listUserInfo")
    @ResponseBody
    public ResponseEntity<ResultManage> listUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        List<UserInfo> userInfoList = userInfoService.listUserInfo(userInfoDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", userInfoList);
        return getJsonResult(resultMap);
    }

    // dataTable对应格式
    @RequestMapping(value = "/listUserInfoByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listUserInfoByPage(@RequestBody Page page) {
        List<PageData> userInfoList = userInfoService.listUserInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", userInfoList);
        return getJsonResult(resultMap);
    }

}
