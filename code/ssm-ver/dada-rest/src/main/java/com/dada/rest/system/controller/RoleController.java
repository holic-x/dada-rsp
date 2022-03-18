package com.dada.rest.system.controller;

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
import com.dada.dto.RoleInfoDTO;
import com.dada.pojo.RoleInfo;
import com.dada.rest.system.service.RoleInfoService;
import com.dada.rest.utils.BaseController;
import com.dada.rest.utils.ResultManage;

@Controller
@RequestMapping("/rest/system/roleInfo")
public class RoleController extends BaseController {

    @Autowired
    private RoleInfoService roleInfoService;

    // 添加角色信息
    @RequestMapping(value = "/addRoleInfo", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addRoleInfo(@RequestBody RoleInfo roleInfo) {
        // 添加角色信息
        String id = roleInfoService.addRoleInfo(roleInfo);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定角色信息
    @RequestMapping(value = "/deleteRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteRoleInfo(@RequestBody RoleInfoDTO roleInfoDTO) {
        roleInfoService.deleteRoleInfoById(roleInfoDTO);
        return getJsonResult();
    }

    // 修改角色信息
    @RequestMapping(value = "/updateRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateRoleInfo(@RequestBody RoleInfo roleInfo) {
        roleInfoService.updateRoleInfo(roleInfo);
        return getJsonResult();
    }

    // 根据角色id查找指定角色信息
    @RequestMapping("getRoleInfoById/{roleId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getRoleInfoById(@PathVariable String roleId) {
        RoleInfo roleInfo = roleInfoService.getRoleInfo(roleId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleInfo);
        return getJsonResult(resultMap);
    }

    // 根据筛选条件查找用户角色信息
    @RequestMapping(value = "/listRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listRoleInfo(@RequestBody RoleInfoDTO roleInfoDTO) {
        List<RoleInfo> roleInfoList = roleInfoService.listRoleInfo(roleInfoDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleInfoList);
        return getJsonResult(resultMap);
    }
    
    // dataTable封装对应格式（为了实现通用性，此处返回ResultMange格式,由调用的接口进行转化）
    /*@RequestMapping(value = "/listRoleInfoByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listRoleInfoByPage(@RequestBody Page page) {
        List<PageData> roleInfoList = roleInfoService.listRoleInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", roleInfoList);
        return resultMap;
    }*/
    
    @RequestMapping(value = "/listRoleInfoByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listRoleInfoByPage(@RequestBody Page page) {
        List<PageData> roleInfoList = roleInfoService.listRoleInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleInfoList);
        return getJsonResult(resultMap);
    }
}
