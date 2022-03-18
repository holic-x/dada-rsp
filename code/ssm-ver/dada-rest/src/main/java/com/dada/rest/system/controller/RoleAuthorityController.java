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
import com.dada.dto.RoleAuthorityDTO;
import com.dada.rest.system.service.RoleAuthorityService;
import com.dada.rest.utils.BaseController;
import com.dada.rest.utils.ResultManage;
import com.dada.vo.MenuVO;
import com.dada.vo.RoleAuthorityVO;

@Controller
@RequestMapping("/rest/system/roleAuthority")
public class RoleAuthorityController extends BaseController {

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    // 批量添加角色权限信息
    @RequestMapping(value = "/batchAddRoleAuthority", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> batchAddRoleAuthority(@RequestBody RoleAuthorityDTO roleAuthorityDTO) {
        // 添加角色权限信息
        boolean flag = roleAuthorityService.batchAddRoleAuthority(roleAuthorityDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("result", flag);
        return getJsonResult(resultMap);
    }

    // 批量删除指定角色权限信息
    @RequestMapping(value = "/batchDeleteRoleAuthority", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> batchDeleteRoleAuthority(@RequestBody RoleAuthorityDTO roleAuthorityDTO) {
        boolean flag = roleAuthorityService.batchDeleteRoleAuthority(roleAuthorityDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("result", flag);
        return getJsonResult(resultMap);
    }

    // 修改角色权限信息(根据角色id删除角色信息,重新加载角色信息)
    @RequestMapping(value = "/updateRoleAuthority", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateRoleAuthority(@RequestBody RoleAuthorityDTO roleAuthorityDTO) {
        boolean flag = roleAuthorityService.updateRoleAuthority(roleAuthorityDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("result", flag);
        return getJsonResult(resultMap);
    }

    // 根据角色id查找指定相应的角色权限信息
    @RequestMapping("listRoleAuthorityByRoleId/{roleId}")
    @ResponseBody
    public ResponseEntity<ResultManage> listRoleAuthorityByRoleId(@PathVariable String roleId) {
        List<RoleAuthorityVO> roleAuthorityList = roleAuthorityService.listRoleAuthorityByRoleId(roleId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleAuthorityList);
        return getJsonResult(resultMap);
    }

    // dataTable对应格式
    @RequestMapping(value = "/listRoleAuthorityByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listRoleAuthorityByPage(@RequestBody Page page) {
        List<PageData> roleAuthorityList = roleAuthorityService.listRoleAuthorityByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", roleAuthorityList);
        return resultMap;
    }
    
    // 根据角色id查找指定相应的角色权限信息
    @RequestMapping("getMenuByRoleId/{roleId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getMenuByRoleId(@PathVariable String roleId) {
        List<MenuVO> roleAuthorityList = roleAuthorityService.getMenuByRoleId(roleId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleAuthorityList);
        return getJsonResult(resultMap);
    }
    
    // 根据角色id查找指定相应的角色权限信息
    @RequestMapping("getAllMenu")
    @ResponseBody
    public ResponseEntity<ResultManage> getAllMenu() {
        List<MenuVO> roleAuthorityList = roleAuthorityService.getAllMenu();
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleAuthorityList);
        return getJsonResult(resultMap);
    }
    
}
