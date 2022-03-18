package com.dada.portal.controller;

import java.util.ArrayList;
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

import com.dada.common.constant.ConstantUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DepartmentDTO;
import com.dada.dto.ReportClassifyDTO;
import com.dada.dto.UreportManagerDTO;
import com.dada.pojo.Department;
import com.dada.pojo.ReportClassify;
import com.dada.pojo.UreportBackup;
import com.dada.pojo.UreportFile;
import com.dada.portal.entity.CustomJsonIgnore;
import com.dada.portal.entity.ZTreeNode;
import com.dada.portal.service.ReportManagerService;
import com.dada.portal.service.ReportService;
import com.dada.portal.utils.BaseController;
import com.dada.portal.utils.ResultManage;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.pojo.UreportFileManager;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * <p>项目名称:dada-portal</p>
 * <p>包名称:com.dada.portal.controller.ReportController</p>
 * <p>文件名称:ReportController.java</p>
 * <p>功能描述: 报表管理相关 </p>
 * <p>其他说明: 报表文件基本信息管理,提供接口供用户管理报表 </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月10日上午9:28:17 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */

@Controller
@RequestMapping("/portal/report/manager")
public class ReportController extends BaseController {

    @Autowired
    private ReportService reportService;
    
    // 添加报表数据直接使页面跳转到报表设计器(dada-report整合的报表设计器)

    // 修改机构报表基本信息(不包括报表设计内容,仅仅包括基本的信息权限管理)
    @RequestMapping(value = "/updateUreportFileUI")
    public String updateUreportFileUI(@RequestParam(value = "fileId") String fileId, Model model) {
        // 查找报表信息()
        // UreportFile ureportFile = reportService.getUreportFile(fileId,operatorType);
        UreportFile ureportFile = reportService.getUreportFile(fileId);
        model.addAttribute("ureportData", ureportFile);
        return "portal/report/organization/report_base_edit";
    }

    // 修改公共报表基本信息(不包括报表设计内容,仅仅包括基本的信息权限管理)
    @RequestMapping(value = "/updateUreportBackupUI")
    public String updateUreportBackupUI(@RequestParam(value = "fileId") String fileId, String operatorType,
            Model model) {
        // 查找报表信息()
        // UreportBackup ureportBackup = reportService.getUreportBackup(fileId,operatorType);
        UreportBackup ureportBackup = reportService.getUreportBackup(fileId);
        model.addAttribute("ureportData", ureportBackup);
        return "portal/report/dada/report_base_edit";
    }

    // 修改报表信息(由前端控制修改报表要调用的接口)
    @RequestMapping(value = "/updateUreportData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateUreportData(@RequestBody UreportManagerDTO ureportManagerDTO) {
        reportService.updateUreportData(ureportManagerDTO);
        return getJsonResult();
    }

    // 删除报表信息(由前端删除修改报表要调用的接口)
    @RequestMapping(value = "/deleteUreportData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteUreportData(@RequestBody UreportManagerDTO ureportManagerDTO) {
        reportService.deleteUreportData(ureportManagerDTO);
        return getJsonResult();
    }

    // 获取分页报表信息(机构适用)
    @RequestMapping(value = "/listUreportFileByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listUreportFileByPage(@RequestBody Page page) {
        List<PageData> ureporFileList = reportService.listUreportFileByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", ureporFileList);
        return resultMap;
    }

    // 获取分页报表信息(平台备份)
    @RequestMapping(value = "/listUreportBackupByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listRoleInfoByPage(@RequestBody Page page) {
        List<PageData> ureporBackupList = reportService.listUreportBackupByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", ureporBackupList);
        return resultMap;
    }

    // 根据文件id查找指定机构文件信息(机构适用)
    @RequestMapping("/getUreportFileById/{fileId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getUreportFileById(@PathVariable String fileId) {
        UreportFile ureportFile = reportService.getUreportFile(fileId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", ureportFile);
        return getJsonResult(resultMap);
    }

    // 根据文件id查找指定平台公共文件信息(平台备份)
    @RequestMapping("/getUreportBackupById/{fileId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getRoleInfoById(@PathVariable String fileId) {
        UreportBackup ureportBackup = reportService.getUreportBackup(fileId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", ureportBackup);
        return getJsonResult(resultMap);
    }

    
    // ---------------- 报表分类管理相关 ----------------- //
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
    @RequestMapping(value = "/deleteReportClassify/{classifyId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteReportClassify(@PathVariable String classifyId) {
        reportService.deleteReportClassifyById(classifyId);
        return getJsonResult();
    }
    
    // 报表分类信息编辑页面跳转
    @RequestMapping(value = "/updateReportClassifyUI", method = RequestMethod.POST)
    public String updateReportClassifyUI(@PathVariable String classifyId,Model model) {
        ReportClassify reportClassify = reportService.getReportClassify(classifyId);
        model.addAttribute("reportClassify", reportClassify);
        return "portal/report/classify/classify_edit";
    }
    
    // 根据报表分类id修改报表分类信息
    @RequestMapping(value = "/updateReportClassify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateReportClassify(@RequestBody ReportClassify classify) {
        reportService.updateReportClassify(classify);
        return getJsonResult();
    }
    
    // 报表分类信息查看页面跳转
    @RequestMapping(value = "/showReportClassifyById/{classifyId}")
    public String showReportClassifyById(@PathVariable String classifyId,Model model) {
        ReportClassify reportClassify = reportService.getReportClassify(classifyId);
        model.addAttribute("reportClassify", reportClassify);
        // 查找对应分类下的报表信息,封装数据到指定页面(此处考虑通过页面请求获取数据)
        return "portal/report/classify/classify_show";
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
    /*
    @RequestMapping(value = "/listReportClassify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listReportClassify(@RequestBody ReportClassifyDTO classifyDTO) {
        List<ReportClassify> reportClassifyList = reportService.listReportClassify(classifyDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", reportClassifyList);
        return getJsonResult(resultMap);
    }
    */
    
    // 根据筛选条件查找报表分类信息(树状数据封装)
    @RequestMapping(value = "/listReportClassify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listReportClassify(@RequestBody ReportClassifyDTO classifyDTO) {
        // 查找指定机构的报表分类信息(classifyId)
        List<ReportClassify> reportClassifyList = reportService.listReportClassify(classifyDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        // 可根据权限封装现有的节点信息
        List<ZTreeNode> zTreeNodes = new ArrayList<>(reportClassifyList.size());
        for (ReportClassify classify : reportClassifyList) {
            ZTreeNode node = new ZTreeNode();
            // 基本ZTreeNode属性
            node.setNodeId(classify.getClassifyId());
            node.setNodePid(classify.getParentId());
            node.setName(classify.getClassifyName());
            // ZTree其余属性
            // 如果parentId为‘-1’,则为父节点(默认为true,并默认展开)
            String parentId = classify.getParentId();
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
    
    // dataTable封装对应格式（为了实现通用性，此处返回ResultMange格式,由调用的接口进行转化）
    @RequestMapping(value = "/listReportClassifyByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listReportClassifyByPage(@RequestBody Page page) {
        List<PageData> reportClassifyList = reportService.listReportClassifyByPage(page);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", reportClassifyList);
        return resultMap;
    }
    
    /*
    // 根据筛选条件分页查找报表分类信息
    @RequestMapping(value = "/listReportClassifyByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listReportClassifyByPage(@RequestBody Page page) {
        List<PageData> reportClassifyList = reportService.listReportClassifyByPage(page);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", reportClassifyList);
        return getJsonResult(resultMap);
    }
    */
    
    // ------------- 报表分类关联管理相关:管理对应分类下的报表信息 ------------------------ //
    
    // 修改对应报表分类下的报表关联关系(修改报表隐藏状态-hideState)
    @RequestMapping(value = "/setLinkHideStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> setLinkHideStatus(@RequestBody ReportClassifyDTO classifyDTO) {
        reportService.setLinkHideStatus(classifyDTO);
        return getJsonResult();
    }
    
    // 设置对应报表分类下的报表关联关系
    @RequestMapping(value = "/setReportClassifyLink", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> setReportClassifyLink(@RequestBody ReportClassifyDTO classifyDTO) {
        reportService.setReportClassifyLink(classifyDTO);
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
