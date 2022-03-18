package com.dada.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.dada.dto.ReportClassifyDTO;
import com.dada.dto.UreportManagerDTO;
import com.dada.pojo.ReportClassify;
import com.dada.pojo.UreportBackup;
import com.dada.pojo.UreportFile;
import com.dada.portal.service.ReportManagerService;
import com.dada.portal.service.ReportService;
import com.dada.portal.utils.BaseController;
import com.dada.portal.utils.ResultManage;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.pojo.UreportFileManager;

/**
 * <p>项目名称:dada-portal</p>
 * <p>包名称:com.dada.portal.controller.ReportManagerController</p>
 * <p>文件名称:ReportManagerController.java</p>
 * <p>功能描述: 报表文件信息通用管理相关 </p>
 * <p>其他说明: 报表文件基本信息管理,提供接口供用户管理报表(通用) </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月10日上午9:28:17 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */

@Controller
@RequestMapping("/portal/report/manager/common")
public class ReportManagerController extends BaseController {

    @Autowired
    private ReportManagerService reportManagerService;

    // 添加报表数据直接使页面跳转到报表设计器(dada-report整合的报表设计器)

    // 修改机构报表基本信息(不包括报表设计内容,仅仅包括基本的信息权限管理)
    @RequestMapping(value = "/updateUreportDataUI")
    public String updateUreportDataUI(UreportFileSearchParam searchParam, Model model) {
        // 查找报表信息()
        UreportFileManager ureportFileManager = reportManagerService.getUreportData(searchParam);
        model.addAttribute("ureportData", ureportFileManager);
        return "portal/report/organization/report_base_edit";
    }
    
    // 修改报表信息(由前端控制修改报表要调用的接口-指定表名以及要调用的数据)
    @RequestMapping(value = "/updateUreportData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateUreportData(@RequestBody UreportFileSearchParam searchParam) {
        reportManagerService.updateUreportData(searchParam);
        return getJsonResult();
    }

    // 删除报表信息(由前端删除修改报表要调用的接口)
    @RequestMapping(value = "/deleteUreportData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteUreportData(@RequestBody UreportFileSearchParam searchParam) {
        reportManagerService.deleteUreportData(searchParam);
        return getJsonResult();
    }

    // 获取分页报表信息(机构适用)
    @RequestMapping(value = "/listUreportDataByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listUreportDataByPage(@RequestBody Page page) {
        List<PageData> ureporFileList = reportManagerService.listUreportDataByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", ureporFileList);
        return resultMap;
    }

    
    // 根据文件id查找指定机构文件信息(机构适用)
    @RequestMapping("/getUreportDataById")
    @ResponseBody
    public ResponseEntity<ResultManage> getUreportDataById(@RequestBody UreportFileSearchParam searchParam) {
        UreportFileManager ureportFileManager = reportManagerService.getUreportData(searchParam);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", ureportFileManager);
        return getJsonResult(resultMap);
    }
    
    // 获取尚未进行分类的报表信息
    @RequestMapping(value = "/listUreportDataByNoClassify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listUreportDataByNoClassify(@RequestBody UreportFileSearchParam searchParam) {
        List<UreportFileManager> ureporFileList = reportManagerService.listReportFileByNoClassify(searchParam);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", ureporFileList);
        return getJsonResult(resultMap);
    }
}
