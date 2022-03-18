package com.dada.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dada.manager.entity.Tree;
import com.dada.manager.entity.TreeNodes;
import com.dada.manager.service.AuthorityInfoService;
import com.dada.manager.web.utils.BaseController;
import com.dada.manager.web.utils.MenuTreeUtil;
import com.dada.manager.web.utils.ResultManage;
import com.dada.pojo.AuthorityInfo;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/test")
public class CommonController extends BaseController {

    @Autowired
    private AuthorityInfoService authorityInfoService;

    // 根据筛选条件查找指定数据信息
    @RequestMapping("/listTreeData")
    @ResponseBody
    public ResponseEntity<ResultManage> listAuthorityInfo() {
        List<AuthorityInfo> authorityList = authorityInfoService.listAuthorityInfo(null);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("dataList", authorityList);

        // 可根据权限封装现有的的节点信息
        List<TreeNodes> treeNodes = new ArrayList<>(authorityList.size());
        for (AuthorityInfo authorityInfo : authorityList) {
            TreeNodes node = new TreeNodes();
            node.setId(authorityInfo.getAuthorityId());
            node.setPid(authorityInfo.getParentId());
            node.setName(authorityInfo.getAuthorityName());
            node.setTitle(authorityInfo.getAuthorityName());
            node.setOpen(0);
            node.setIshidden(0);
            // url?target?对应设置目标页面
            node.setUrl(authorityInfo.getAuthorityUrl());
            node.setTarget("");
            treeNodes.add(node);
        }
        System.out.println("right list个数=" + treeNodes.size());
        JSONArray json = JSONArray.fromObject(treeNodes);
        JSONObject jb = new JSONObject();
        jb.put("right", json);
        System.out.println("json串=" + jb.toString());
        resultMap.put("data", json);
        return getJsonResult(resultMap);
    }

    /*
     * @RequestMapping(value = { "getAPPTree" }, produces =
     * "text/html;charset=UTF-8")
     * 
     * @ResponseBody public String getAPPTree(HttpServletRequest request,
     * HttpServletResponse response) throws Exception { Map<String, Object>
     * returnmap = new HashMap<>(); MenuTreeUtil menuTree = new MenuTreeUtil();
     * PageData pd = this.getPageData(); try {
     * //这里的方法是根据前台的机构类型代码来查找数据库数据的，这里不多加解释，因人而异 List<Tree> list =
     * dataDicService.buildTree(pd.getString("instType")); List<Object> menuList
     * = menuTree.menuList(list);
     * //区别于web端，这边APP端list不能转为json格式，直接将list传给前台，转成json对象的话vuejs前台无法识别渲染
     * returnmap.put("list", menuList); } catch (Exception e) {
     * logger.error(e.getMessage()); } return
     * JsonMapper.toJsonString(returnmap); }
     */

    @RequestMapping("/getTree")
    @ResponseBody
    public ResponseEntity<ResultManage> getTree() {
        MenuTreeUtil menuTree = new MenuTreeUtil();
        List<AuthorityInfo> authorityList = authorityInfoService.listAuthorityInfo(null);
        Map<String, Object> resultMap = new HashMap<>(4);
        // 这里的方法是根据前台的机构类型代码来查找数据库数据的，这里不多加解释，因人而异
        List<Tree> treeNodes = new ArrayList<>(authorityList.size());
        for (AuthorityInfo authorityInfo : authorityList) {
            Tree node = new Tree();
            node.setId(authorityInfo.getAuthorityId());
            node.setpId(authorityInfo.getParentId());
            node.setName(authorityInfo.getAuthorityName());
            treeNodes.add(node);
        }
        List<Object> menuList = menuTree.menuList(treeNodes);
        // 区别于web端，APP端list不能转为json格式，直接将list传给前台，转成json对象的话vuejs前台无法识别渲染
        resultMap.put("list", menuList);
        return getJsonResult(resultMap);
    }

}
