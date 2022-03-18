package com.dada.rest.report.controller;

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
import com.dada.dto.DataSourceDTO;
import com.dada.dto.ReportClassifyDTO;
import com.dada.dto.RoleInfoDTO;
import com.dada.pojo.DataSourceInfo;
import com.dada.pojo.ReportClassify;
import com.dada.pojo.RoleInfo;
import com.dada.rest.datasource.service.DataSourceService;
import com.dada.rest.report.service.ReportService;
import com.dada.rest.utils.BaseController;
import com.dada.rest.utils.ResultManage;

@Controller
@RequestMapping("/rest/report/manager")
public class ReportController extends BaseController {

    @Autowired
    private ReportService reportService;

    // 添加报表分类信息
    @RequestMapping(value = "/addReportClassify", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addReportClassify(@RequestBody ReportClassify classify) {
        // 添加数据源信息
        String id = reportService.addReportClassify(classify);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 根据报表分类id删除报表分类信息
    @RequestMapping(value = "/deleteReportClassify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteReportClassify(@RequestBody ReportClassifyDTO classifyDTO) {
        reportService.deleteReportClassifyById(classifyDTO);
        return getJsonResult();
    }
    
    // 根据报表分类id修改报表分类信息
    @RequestMapping(value = "/updateReportClassify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateReportClassify(@RequestBody ReportClassify classify) {
        reportService.updateReportClassify(classify);
        return getJsonResult();
    }

    // 根据报表分类id获取报表分类信息
    @RequestMapping("getReportClassify/{classifyId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getReportClassify(@PathVariable String classifyId) {
        ReportClassify reportClassify = reportService.getReportClassify(classifyId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", reportClassify);
        return getJsonResult(resultMap);
    }

    // 根据筛选条件查找报表分类信息
    @RequestMapping(value = "/listReportClassify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listReportClassify(@RequestBody ReportClassifyDTO classifyDTO) {
        List<ReportClassify> reportClassifyList = reportService.listReportClassify(classifyDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", reportClassifyList);
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
    
    // 根据筛选条件分页查找报表分类信息
    @RequestMapping(value = "/listReportClassifyByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listReportClassifyByPage(@RequestBody Page page) {
        List<PageData> reportClassifyList = reportService.listReportClassifyByPage(page);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", reportClassifyList);
        return getJsonResult(resultMap);
    }
    
    // ------------- 报表分类关联管理相关:管理对应分类下的报表信息 ------------------------ //
    
    // 设置对应报表分类下的报表关联关系
    @RequestMapping(value = "/setReportClassifyLink", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> setReportClassifyLink(@RequestBody ReportClassifyDTO classifyDTO) {
        reportService.setReportClassifyLink(classifyDTO);
        return getJsonResult();
    }
    
    // 修改对应报表分类下的报表关联关系(修改报表隐藏状态-hideStatus)
    @RequestMapping(value = "/setLinkHideStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> setLinkHideStatus(@RequestBody ReportClassifyDTO classifyDTO) {
        reportService.setLinkHideStatus(classifyDTO);
        return getJsonResult();
    }
    
    
    
    // 根据指定条件获取对应分类下的报表信息
    @RequestMapping(value = "/listLinkList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listLinkList(@RequestBody Page page) {
        List<PageData> linkList = reportService.listLinkList(page);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", linkList);
        return getJsonResult(resultMap);
    }
    
}
