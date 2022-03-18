package com.dada.portal.controller;

import java.util.ArrayList;
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

import com.dada.common.constant.ConstantUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DepartmentDTO;
import com.dada.dto.UserInfoDTO;
import com.dada.pojo.Department;
import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.RoleInfo;
import com.dada.pojo.UserInfo;
import com.dada.portal.entity.CustomJsonIgnore;
import com.dada.portal.entity.ZTreeNode;
import com.dada.portal.service.OrganizationService;
import com.dada.portal.utils.BaseController;
import com.dada.portal.utils.ResultManage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/**
 * <p>项目名称:dada-portal</p>
 * <p>包名称:com.dada.portal.controller.OrganizationController</p>
 * <p>文件名称:OrganizationController.java</p>
 * <p>功能描述:机构管理相关模块</p>
 * <p>其他说明:包括机构信息维护、机构用户管理、机构部门管理等模块    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年4月8日下午11:43:24 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
@Controller
@RequestMapping("/portal/organization")
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organService;
    
    // ----------------机构基本信息相关--------------------------- //
    // 修改机构分类页面跳转
    @RequestMapping(value = "/updateCategoryUI/{categoryId}")
    public String updateCategoryUI(@PathVariable String categoryId,Model model) {
        OrganizationCategory category = organService.getOrganCategory(categoryId);
        // 封装新购信息到指定页面
        model.addAttribute("category", category);
        return "portal/organization/base/category_edit";
    }
    
    // 修改机构分类信息
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateAuthorityInfo(@RequestBody OrganizationCategory category) {
        organService.updateOrganCategory(category);
        return getJsonResult();
    }

    // 根据机构分类id查找指定机构分类信息
    @RequestMapping("/getCategoryById/{categoryId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getCategoryById(@PathVariable String categoryId) {
        OrganizationCategory category = organService.getOrganCategory(categoryId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", category);
        return getJsonResult(resultMap);
    }

    // 验证机构编码是否重复
    @RequestMapping(value = "/checkCategoryData", method = RequestMethod.GET)
    @ResponseBody
    public String checkCategoryData(@RequestParam("dataValue")String dataValue,@RequestParam("dataField")String dataField,@RequestParam("categoryId")String categoryId) {
        boolean flag = organService.checkCategoryData(dataValue, dataField, categoryId);
        Map<String, Object> resultMap = new HashMap<>(4);
        // 验证用户数据是否可用,可用返回true
        resultMap.put("valid", flag);
        // remote要求返回true or false : map.put("valid", validateRepeat);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    // ----------------机构用户管理相关--------------------------- //

    // 添加用户信息
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addUser(@RequestBody UserInfo userInfo) {
        // 添加用户信息
        String id = organService.addUserInfo(userInfo);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定用户信息
    @RequestMapping(value = "/deleteUserInfos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteUserInfos(@RequestBody UserInfoDTO userInfoDTO) {
        organService.BatchDeleteUserInfo(userInfoDTO.getUserIds());
        return getJsonResult();
    }
    
    // 修改用户信息页面跳转
    @RequestMapping(value = "/updateUserInfoUI/{userId}")
    public String updateUserInfoUI(@PathVariable String userId,Model model) {
        UserInfo userInfo = organService.getUserInfo(userId);
        // 封装用户信息到指定页面
        model.addAttribute("userInfo", userInfo);
        return "portal/organization/userInfo/user_edit";
    }

    // 修改用户信息
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateUserInfo(@RequestBody UserInfo userInfo) {
        organService.updateUserInfo(userInfo);
        return getJsonResult();
    }

    // 根据用户id查找指定用户信息
    @RequestMapping("/getUserInfoById/{userId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getUserInfoById(@PathVariable String userId) {
        UserInfo userInfo = organService.getUserInfo(userId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", userInfo);
        return getJsonResult(resultMap);
    }

    // 验证用户信息是否重复(邮箱、用户登录账号)
    @RequestMapping(value = "/checkUserData",method = RequestMethod.GET)
    @ResponseBody
    public String checkUserData(@RequestParam("dataValue")String dataValue,@RequestParam("dataField")String dataField,@RequestParam("userId")String userId) {
        boolean flag = organService.checkUserData(dataValue, dataField, userId);
        Map<String, Object> resultMap = new HashMap<>(4);
        // 验证用户数据是否可用,可用返回true
        resultMap.put("valid", flag);
        // remote要求返回true or false : map.put("valid", validateRepeat);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    // 根据筛选条件获取用户信息
    @RequestMapping(value = "/listUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        List<UserInfo> userInfoList = organService.listUserInfo(userInfoDTO);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", userInfoList);
        return getJsonResult(resultMap);
    }

    // dataTable对应格式
    @RequestMapping(value = "/listUserInfoByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listUserInfoByPage(@RequestBody Page page) {
        List<PageData> userInfoList = organService.listUserInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data",userInfoList);
        return resultMap;
    }
    /*
     * public ResponseEntity<ResultManage> listUserInfoByPage(@RequestBody Page page) {
        List<PageData> userInfoList = organService.listUserInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", userInfoList);
        return getJsonResult(resultMap);
    }*/
    
    // 根据机构id查找相应的角色信息列表
    @RequestMapping("/listRoleInfoCategoryId/{categoryId}")
    @ResponseBody
    public ResponseEntity<ResultManage> listRoleInfoCategoryId(@PathVariable String categoryId) {
        List<RoleInfo> roleInfoList = organService.listRoleInfoByCategoryId(categoryId); 
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleInfoList);
        return getJsonResult(resultMap);
    }

    // --------------- 机构部门管理相关 ---------------- //
    
    // 添加机构部门信息
    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addDepartment(@RequestBody Department dept) {
        // 添加机构部门信息
        String id = organService.addDepartment(dept);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定机构部门信息
    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteDepartment(@RequestBody DepartmentDTO departmentDTO) {
        organService.deleteDepartmentById(departmentDTO);
        return getJsonResult();
    }
    
    // 修改部门信息页面跳转
    @RequestMapping(value = "/updateDepartmentUI/{deptId}")
    public String updateDepartmentUI(@PathVariable String deptId,Model model) {
        Department dept = organService.getDepartment(deptId);
        // 封装部门信息到指定页面
        model.addAttribute("dept", dept);
        return "portal/organization/dept/dept_edit";
    }

    // 修改机构部门信息
    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateDepartment(@RequestBody Department dept) {
        organService.updateDepartment(dept);
        return getJsonResult();
    }

    // 根据机构部门id查找指定机构部门信息,实现页面跳转
    @RequestMapping("/showDepartmentById")
    public String showDepartmentById(@RequestParam(value="categoryId") String categoryId,@RequestParam(value="deptId") String deptId,Model model) {
        Department dept = organService.getDepartment(deptId);
        // 封装部门信息到指定页面
        model.addAttribute("dept", dept);
        // 查找指定的部门用户信息列表
        UserInfoDTO userInfoDTO = new UserInfoDTO(categoryId,deptId);
        List<UserInfo> userList = organService.listUserInfo(userInfoDTO);
        model.addAttribute("list", userList);
        // 查找指定机构的尚未归属分类（归属机构）的用户信息列表
        // userInfoDTO = new UserInfoDTO(categoryId,ConstantUtils.DEFAULT_DATA);
        // List<UserInfo> defaultList = organService.listUserInfo(userInfoDTO);
        // model.addAttribute("defaultList", defaultList);
        return "portal/organization/dept/dept_show";
    }
    
    // 根据机构部门id查找指定机构部门信息
    @RequestMapping("/getDepartmentById/{deptId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getDepartmentById(@PathVariable String deptId) {
        Department dept = organService.getDepartment(deptId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", dept);
        return getJsonResult(resultMap);
    }

    // 根据筛选条件查找用户机构部门信息
    @RequestMapping(value = "/listDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listDepartment(@RequestBody DepartmentDTO departmentDTO) {
        // 查找指定机构的部门信息(categoryId)
        List<Department> deptList = organService.listDepartment(departmentDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        // 可根据权限封装现有的节点信息
        List<ZTreeNode> zTreeNodes = new ArrayList<>(deptList.size());
        for (Department dept : deptList) {
            ZTreeNode node = new ZTreeNode();
            // 基本ZTreeNode属性
            node.setNodeId(dept.getDeptId());
            node.setNodePid(dept.getParentId());
            node.setName(dept.getDeptName());
            // ZTree其余属性
            // 如果parentId为‘-1’,则为父节点(默认为true,并默认展开)
            String parentId = dept.getParentId();
            if(ConstantUtils.DEFAULT_DATA.equals(parentId)) {
                node.setParent(true);
                node.setOpen(true);
            }else {
                node.setParent(false);
            }
            // 将节点加载到集合中
            zTreeNodes.add(node);
        }
        /**
         * 转化json字符串时忽略某些属性：(不同框架处理方式不同)
         * 方式1：
         *  使用jackson时在实体类对应属性上添加@JsonIgnore,标识忽略该属性
         *  fastjson过滤指定字段 :@JSONField(serialize=false)
         *  @JsonIgnoreProperties(value={"xxxx"})
         * 方式2：
         * json-lib包
         *  JSONConfig: 转JSON的配置对象
         *  JSONAarray：将数组和list集合转成JSON
         *  JSONObject: 将对象和Map集合转成JSON
         * 方式3：
         *  创建一个中间类，存放需要转化的属性
         */
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(CustomJsonIgnore.getZTreeNodeAttributes());
        JSONArray jsonArray = JSONArray.fromObject(JSONArray.fromObject(zTreeNodes), jsonConfig);
        // JSONArray jsonArray = JSONArray.fromObject(zTreeNodes);
        // JSONObject jsonObject = new JSONObject();
        // jsonObject.put("jsonObject", json);
        // jb.toString();(将Object转化为json字符串)
        resultMap.put("data", jsonArray);
        return getJsonResult(resultMap);
    }
    
    @RequestMapping(value = "/listDepartmentByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listDepartmentByPage(@RequestBody Page page) {
        List<PageData> deptList = organService.listDepartmentByPage(page);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", deptList);
        return getJsonResult(resultMap);
    }
    
    // 添加\移除指定用户到指定机构、部门(修改用户信息)
    @RequestMapping(value = "/updateUserFromDept", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateUserFromDept(@RequestBody DepartmentDTO deptDTO) {
        organService.updateUserFromDept(deptDTO);
        return getJsonResult();
    }
    
}
