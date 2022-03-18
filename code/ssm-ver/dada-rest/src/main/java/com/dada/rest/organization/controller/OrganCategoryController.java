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

import com.dada.pojo.OrganizationCategory;
import com.dada.rest.organization.service.OrganCategoryService;
import com.dada.rest.utils.BaseController;
import com.dada.rest.utils.ResultManage;

@Controller
@RequestMapping("/rest/organCategory/base")
public class OrganCategoryController extends BaseController {

    @Autowired
    private OrganCategoryService organCategoryService;

    // 修改机构分类信息
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateAuthorityInfo(@RequestBody OrganizationCategory category) {
        organCategoryService.updateOrganCategory(category);
        return getJsonResult();
    }

    // 根据机构分类id查找指定机构分类信息
    @RequestMapping("getCategoryById/{categoryId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getCategoryById(@PathVariable String categoryId) {
        OrganizationCategory category = organCategoryService.getOrganCategory(categoryId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", category);
        return getJsonResult(resultMap);
    }

    // 根据筛选条件查找机构分类信息
    @RequestMapping(value = "/listOrganCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listOrganCategory(@RequestBody OrganizationCategory category) {
        List<OrganizationCategory> categoryList = organCategoryService.listOrganCategory(category);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", categoryList);
        return getJsonResult(resultMap);
    }

}
