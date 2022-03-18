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
import com.dada.dto.DepartmentDTO;
import com.dada.pojo.Department;
import com.dada.rest.organization.service.DepartmentService;
import com.dada.rest.utils.BaseController;
import com.dada.rest.utils.ResultManage;

@Controller
@RequestMapping("/rest/organCategory/dept")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    // 添加机构部门信息
    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addDepartment(@RequestBody Department dept) {
        // 添加机构部门信息
        String id = departmentService.addDepartment(dept);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定机构部门信息
    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.deleteDepartmentById(departmentDTO);
        return getJsonResult();
    }

    // 修改机构部门信息
    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateDepartment(@RequestBody Department dept) {
        departmentService.updateDepartment(dept);
        return getJsonResult();
    }

    // 根据机构部门id查找指定机构部门信息
    @RequestMapping("getDepartmentById/{deptId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getDepartmentById(@PathVariable String deptId) {
        Department dept = departmentService.getDepartment(deptId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", dept);
        return getJsonResult(resultMap);
    }

    // 根据筛选条件查找用户机构部门信息
    @RequestMapping(value = "/listDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listDepartment(@RequestBody DepartmentDTO departmentDTO) {
        List<Department> deptList = departmentService.listDepartment(departmentDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", deptList);
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
    
    @RequestMapping(value = "/listDepartmentByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listDepartmentByPage(@RequestBody Page page) {
        List<PageData> deptList = departmentService.listDepartmentByPage(page);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", deptList);
        return getJsonResult(resultMap);
    }
}
