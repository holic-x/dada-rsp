package com.dada.manager.controller;

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
import com.dada.manager.entity.CustomJsonIgnore;
import com.dada.manager.entity.ZTreeNode;
import com.dada.manager.service.AuthorityInfoService;
import com.dada.manager.web.utils.BaseController;
import com.dada.manager.web.utils.ResultManage;
import com.dada.pojo.AuthorityInfo;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * <p>项目名称:dada-manager-web</p>
 * <p>包名称:com.dada.manager.controller.AuthorityInfoController</p>
 * <p>文件名称:AuthorityInfoController.java</p>
 * <p>功能描述:权限/菜单控制器</p>
 * <p>其他说明:    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年4月8日下午11:15:04 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
@Controller
@RequestMapping("/manager/menu")
public class AuthorityInfoController extends BaseController {

    @Autowired
    private AuthorityInfoService authorityInfoService;

    // 添加"权限/菜单"信息
    @RequestMapping(value = "/addAuthorityInfo", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addAuthorityInfo(@RequestBody AuthorityInfo authorityInfo) {
        // 添加"权限/菜单"信息
        String id = authorityInfoService.addAuthorityInfo(authorityInfo);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定"权限/菜单"信息
    @RequestMapping(value = "/deleteAuthorityInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteAuthorityInfo(@RequestBody AuthorityInfo authorityInfo) {
        authorityInfoService.deleteAuthorityInfoById(authorityInfo.getAuthorityId());
        return getJsonResult();
    }

    // 修改"权限/菜单"信息页面跳转
    @RequestMapping(value = "/updateAuthorityInfoUI")
    public String updateAuthorityInfoUI(@RequestParam(value = "authorityId") String authorityId, Model model) {
        AuthorityInfo findAuthorityInfo = authorityInfoService.getAuthorityInfo(authorityId);
        model.addAttribute("authorityInfo", findAuthorityInfo);
        return "menu/menu_edit";
    }

    // 修改"权限/菜单"信息
    @RequestMapping(value = "/updateAuthorityInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateAuthorityInfo(@RequestBody AuthorityInfo authorityInfo) {
        authorityInfoService.updateAuthorityInfo(authorityInfo);
        return getJsonResult();
    }

    // 根据筛选条件查找指定数据信息
    @RequestMapping("/listAuthorityInfo")
    @ResponseBody
    public ResponseEntity<ResultManage> listAuthorityInfo(@RequestBody AuthorityInfo authorityInfo) {
        List<AuthorityInfo> authorityList = authorityInfoService.listAuthorityInfo(authorityInfo);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("dataList", authorityList);
        return getJsonResult(resultMap);
    }

    // 验证重复的authority编码
    @RequestMapping(value = "/validateaAuthorityCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> validateaAuthorityCode(@RequestBody AuthorityInfo authorityInfo) {
        boolean validateRepeat = authorityInfoService.validateAuthorityCode(authorityInfo.getAuthorityCode(),
                authorityInfo.getAuthorityId());
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("valid", String.valueOf(validateRepeat));
        return getJsonResult(resultMap);
    }

    // 验证重复的authorityURL
    @RequestMapping(value = "/validateaAuthorityUrl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> validateaAuthorityUrl(@RequestBody AuthorityInfo authorityInfo) {
        boolean validateRepeat = authorityInfoService.validateAuthorityUrl(authorityInfo.getAuthorityUrl(),
                authorityInfo.getAuthorityId());
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("valid", String.valueOf(validateRepeat));
        return getJsonResult(resultMap);
    }
    
    // 查看"权限/菜单"信息页面跳转
    @RequestMapping(value = "/showAuthorityInfoUI")
    public String showAuthorityInfoUI(@RequestParam(value = "authorityId") String authorityId, Model model) {
        AuthorityInfo findAuthorityInfo = authorityInfoService.getAuthorityInfo(authorityId);
        model.addAttribute("authorityInfo", findAuthorityInfo);
        return "menu/menu_show";
    }

    // 根据"权限/菜单"id查找指定"权限/菜单"信息
    @RequestMapping("getAuthorityInfoById/{authorityId}")
    @ResponseBody
    public AuthorityInfo getAuthorityInfoById(@PathVariable String authorityId) {
        AuthorityInfo authorityInfo = authorityInfoService.getAuthorityInfo(authorityId);
        return authorityInfo;
    }

    // dataTable对应格式
    @RequestMapping(value = "/listAuthorityInfoByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listAuthorityInfoByPage(@RequestBody Page page) {
        List<PageData> authorityInfoList = authorityInfoService.listAuthorityInfoByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", authorityInfoList);
        return resultMap;
    }

    // 根据筛选条件查找指定的菜单数据
    @RequestMapping("/listMenuData")
    @ResponseBody
    public ResponseEntity<ResultManage> listAuthorityInfo() {
        List<AuthorityInfo> authorityList = authorityInfoService.listAuthorityInfo(null);
        Map<String, Object> resultMap = new HashMap<>(4);
        // 可根据权限封装现有的节点信息
        List<ZTreeNode> zTreeNodes = new ArrayList<>(authorityList.size());
        for (AuthorityInfo authorityInfo : authorityList) {
            ZTreeNode node = new ZTreeNode();
            // 基本ZTreeNode属性
            node.setNodeId(authorityInfo.getAuthorityId());
            node.setNodePid(authorityInfo.getParentId());
            node.setName(authorityInfo.getAuthorityName());
            // ZTree其余属性
            // 如果parentId为‘-1’,则为父节点(默认为true,并默认展开)
            String parentId = authorityInfo.getParentId();
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

}
