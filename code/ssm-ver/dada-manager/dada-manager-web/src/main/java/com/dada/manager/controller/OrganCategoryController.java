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
import com.dada.manager.service.OrganCategoryService;
import com.dada.manager.web.utils.BaseController;
import com.dada.manager.web.utils.ResultManage;
import com.dada.pojo.OrganizationCategory;

@Controller
@RequestMapping("/manager/organCategory")
public class OrganCategoryController extends BaseController {
    
    @Autowired
    private OrganCategoryService organCategoryService;
  
    // 添加机构分类信息
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, consumes="application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addCategory(@RequestBody OrganizationCategory category) {
        // 添加机构分类信息
        String id = organCategoryService.addOrganCategory(category);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定机构分类信息
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteCategory(@RequestBody OrganizationCategory category) {
        organCategoryService.deleteOrganCategoryById(category.getCategoryId());
        return getJsonResult();
    }

    // 修改机构分类信息页面跳转
    @RequestMapping(value = "/updateCategoryUI")
    public String updateCategoryUI(@RequestParam(value="categoryId") String categoryId,Model model) {
        OrganizationCategory findCategory = organCategoryService.getOrganCategory(categoryId);
        model.addAttribute("category", findCategory);
        return "organCategory/category_edit";
    }
    
    // 修改机构分类信息
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateAuthorityInfo(@RequestBody OrganizationCategory category) {
        organCategoryService.updateOrganCategory(category);
        return getJsonResult();
    }

    // 验证重复的机构分类编码
    @RequestMapping(value="/validateaCategoryCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> validateaCategoryCode(@RequestBody OrganizationCategory category) {
            boolean validateRepeat = organCategoryService.validateCategoryCode(category.getCategoryCode(), category.getCategoryId());
            Map<String, Object> resultMap = new HashMap<>(4);
            resultMap.put("valid", String.valueOf(validateRepeat));
            return getJsonResult(resultMap);
        }
    
    // 根据机构分类id查找指定机构分类信息
    @RequestMapping("getCategoryById/{categoryId}")
    @ResponseBody
    public OrganizationCategory getCategoryById(@PathVariable String categoryId) {
        OrganizationCategory category = organCategoryService.getOrganCategory(categoryId);
        return category;
    }
    
    // dataTable对应格式
    @RequestMapping(value = "/listCategoryByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listCategoryByPage(@RequestBody Page page) {
        List<PageData> categoryList = organCategoryService.listOrganCategoryByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data",categoryList);
        return resultMap;
    }

}

