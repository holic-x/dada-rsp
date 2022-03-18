package com.dada.report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.UreportManagerDTO;
import com.dada.pojo.UreportBackup;
import com.dada.pojo.UreportFile;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.pojo.UreportFileManager;
import com.dada.report.service.UreportFileManagerService;
import com.dada.report.service.UreportFileService;
import com.dada.report.utils.BaseController;
import com.dada.report.utils.ResultManage;

/**
 * <p>项目名称:dada-report</p>
 * <p>包名称:com.dada.report.controller.UreportManagerController</p>
 * <p>文件名称:UreportManagerController.java</p>
 * <p>功能描述: ureport文件管理器 </p>
 * <p>其他说明: 管理ureport报表相关的文件信息  </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月9日下午5:40:00 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
@Controller
@RequestMapping(value = "/report/manager")
public class UreportFileManagerController extends BaseController {
    /**
     * 方式1:根据OPERATOR_TYPE控制调用不同的service
     * 根据不同的需求对报表文件进行管理,由于在数据库中存储的数据都是相关的文件信息
     * 只是在实际的业务需求上整合有所不同,因此报表控制器则直接根据传入的参数类型
     * 来确定要执行的操作(OPERATOR_TYPE)
     * 1.机构报表文件(针对报表存储器)操作
     * 2.哒哒公共报表文件(针对报表存储器)操作
     * 3.机构数据源配置文件(针对数据源配置文件存储器)操作
     * 4.哒哒公共数据源配置文件(针对数据源配置文件存储器)操作
     * 
     * 方式2:通过UreportFileSearchParam封装筛选条件,控制dao层数据
     * 
     */
    @Autowired
    private UreportFileManagerService ureportFileManagerService;

    // 编辑报表数据(此处的编辑提供两个入口:一是对报表基本信息的管理,再者是对报表内容的管理)
    @RequestMapping(value = "/updateUreportData", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> updateUreportData(@RequestBody UreportFileSearchParam searchParam) {
        ureportFileManagerService.updateUreportFile(searchParam);
        return getJsonResult();
    }

    // 删除数据库中的报表文件
    @RequestMapping(value = "/deleteUreportData", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> deleteUreportData(@RequestBody UreportFileSearchParam searchParam) {
        ureportFileManagerService.deleteUreportFile(searchParam);
        return getJsonResult();
    }

    // 根据筛选条件分页列出报表信息
    @RequestMapping(value = "/listUreportDataByPage", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> listUreportDataByPage(@RequestBody Page page) {
        List<PageData> dataList = null;
        dataList = ureportFileManagerService.listUreportFileByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", dataList);
        return getJsonResult(resultMap);
    }

    // 根据fileId查找相应的数据信息(由于返回的类型不同，portal调用服务需要分情况处理)
    @RequestMapping(value = "/getUreportData", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> getUreportData(@RequestBody UreportFileSearchParam searchParam) {
        Map<String, Object> resultMap = new HashMap<>(8);
        UreportFileManager ureportFileManager = ureportFileManagerService.getUreportFile(searchParam);
        resultMap.put("data", ureportFileManager);
        return getJsonResult(resultMap);
    }

    // 查找尚未指定分类的报表文件信息
    @RequestMapping(value = "/listUreportDataByNoClassify", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> listUreportDataByNoClassify(@RequestBody UreportFileSearchParam searchParam) {
        List<UreportFileManager> dataList = null;
        dataList = ureportFileManagerService.listReportFileByNoClassify(searchParam);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", dataList);
        return getJsonResult(resultMap);
    }
}
