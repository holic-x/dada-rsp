package com.dada.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.dada.dto.RoleAuthorityDTO;
import com.dada.dto.RoleInfoDTO;
import com.dada.pojo.RoleInfo;
import com.dada.portal.service.SystemService;
import com.dada.portal.utils.BaseController;
import com.dada.portal.utils.ResultManage;
import com.dada.vo.MenuVO;
import com.dada.vo.RoleAuthorityVO;

@Controller
@RequestMapping("/portal/system")
public class SystemController extends BaseController{
    
    /*
    // 在service请求rest服务,随后再controller层调用相应service层服务
    @Value("${REST_BASE_SYSTEM_URL}")
    private String REST_BASE_SYSTEM_URL;

    @Value("${REST_ROLE_INFO_ADD}")
    private String REST_ROLE_INFO_ADD;

    @Value("${REST_ROLE_INFO_DELETE}")
    private String REST_ROLE_INFO_DELETE;
    
    @Value("${REST_ROLE_INFO_UPDATE}")
    private String REST_ROLE_INFO_UPDATE;
    
    @Value("${REST_ROLE_INFO_GET}")
    private String REST_ROLE_INFO_GET;
    
    @Value("${REST_ROLE_INFO_LIST_PAGE}")
    private String REST_ROLE_INFO_LIST_PAGE;
    */
    
    @Autowired
    private SystemService systemService;

    /*@Autowired
    private CommonService commonService;
    
    // 添加角色信息
    @RequestMapping(value = "/addRoleInfo", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public String addRoleInfo(@RequestBody RoleInfo roleInfo,HttpServletRequest request, HttpServletResponse response) {
        roleInfo.setRoleId(IDUtils.genRandomUUId());
        UserInfo loginUser = commonService.getUserInfoByToken(request, response);
        if(loginUser!=null) {
            // 设置默认属性(当前用户所属机构id)
            roleInfo.setCategoryId(loginUser.getCategoryId());
        }else {
            // 当前用户不存在,机构信息设置默认机构(-1)
            roleInfo.setCategoryId(ConstantUtils.DEFAULT_DATA);
        }
        // 调用rest服务:添加角色信息
        // 1.将要传送的数据(对象)转化为json字符串
        String json =  JsonUtils.objectToJson(roleInfo);
        String jsonParam = JSON.toJSONString(roleInfo) ;
        // 2.通过HttpClientUtil.doGet(...)方法请求rest服务器;
        // String jsonResult = HttpClientUtil.doPostJson(REST_BASE_SYSTEM_URL + REST_ROLE_INFO_ADD,jsonParam);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_SYSTEM_URL + REST_ROLE_INFO_ADD,json);
        return jsonResult;
    }*/
    
    // 添加角色信息
    @RequestMapping(value = "/addRoleInfo", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addRoleInfo(@RequestBody RoleInfo roleInfo,HttpServletRequest request, HttpServletResponse response) {
        // 添加角色信息
        String id = systemService.addRoleInfo(roleInfo, request, response);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定角色信息
    @RequestMapping(value = "/deleteRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteRoleInfo(@RequestBody RoleInfoDTO roleInfoDTO) {
        systemService.deleteRoleInfoById(roleInfoDTO);
        return getJsonResult();
    }
    
    @RequestMapping(value = "/updateRoleInfoUI")
    public String updateDataUI(@RequestParam(value = "roleId") String roleId, Model model) {
        RoleInfo roleInfo = systemService.getRoleInfo(roleId);
        model.addAttribute("roleInfo", roleInfo);
        return "portal/system/roleInfo/role_edit";
    }

    // 修改角色信息
    @RequestMapping(value = "/updateRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateRoleInfo(@RequestBody RoleInfo roleInfo) {
        systemService.updateRoleInfo(roleInfo);
        return getJsonResult();
    }

    // 根据角色id查找指定角色信息
    @RequestMapping("/getRoleInfoById/{roleId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getRoleInfoById(@PathVariable String roleId) {
        RoleInfo roleInfo = systemService.getRoleInfo(roleId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleInfo);
        return getJsonResult(resultMap);
    }

    // dataTable对应格式
    @RequestMapping(value = "/listRoleInfoByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listRoleInfoByPage(@RequestBody Page page) {
        List<PageData> roleInfoList = systemService.listRoleInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", roleInfoList);
        return resultMap;
    }
    
    // --------------------------------角色权限管理相关----------------------------------------- //
    // 跳转到权限修改页面(查找所有菜单信息、对应角色所拥有的权限信息)
    @RequestMapping("/updateRoleAuthorityUI/{roleId}")
    public String updateRoleAuthorityUI(@PathVariable String roleId,Model model) {
        // 查找所有的菜单信息
        List<MenuVO> menuVOList = systemService.selectAllMenu();
        // 查找用户角色对应的权限信息
        List<RoleAuthorityVO> roleAuthorityList = systemService.listRoleAuthorityByRoleId(roleId);
        // 传递数据到页面
        model.addAttribute("roleId", roleId);
        model.addAttribute("menuVOList", menuVOList);
        model.addAttribute("roleAuthorityList", roleAuthorityList);
        return "portal/system/authority/roleAuthority_edit";
    }
    
    // 修改角色权限信息(根据角色id删除角色信息,重新加载角色信息)
    @RequestMapping(value = "/updateRoleAuthority", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateRoleAuthority(@RequestBody RoleAuthorityDTO roleAuthorityDTO) {
        boolean flag = systemService.updateRoleAuthority(roleAuthorityDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("result", flag);
        return getJsonResult(resultMap);
    }
    
    // 根据角色id查找指定相应的角色权限信息
    @RequestMapping("/listRoleAuthorityByRoleId/{roleId}")
    @ResponseBody
    public ResponseEntity<ResultManage> listRoleAuthorityByRoleId(@PathVariable String roleId) {
        List<RoleAuthorityVO> roleAuthorityList = systemService.listRoleAuthorityByRoleId(roleId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", roleAuthorityList);
        return getJsonResult(resultMap);
    }
}
