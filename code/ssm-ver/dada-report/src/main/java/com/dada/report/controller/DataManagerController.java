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
@RequestMapping(value="/report/data")
public class DataManagerController extends BaseController {
    /**
     * 根据不同的需求对报表文件进行管理,由于在数据库中存储的数据都是相关的文件信息
     * 只是在实际的业务需求上整合有所不同,因此报表控制器则直接根据传入的参数类型
     * 来确定要执行的操作(OPERATOR_TYPE)
     * 1.机构报表文件(针对报表存储器)操作
     * 2.哒哒公共报表文件(针对报表存储器)操作
     * 3.机构数据源配置文件(针对数据源配置文件存储器)操作
     * 4.哒哒公共数据源配置文件(针对数据源配置文件存储器)操作
     */
    // private final String REPORT_OPERATOR_TYPE_ORGAN = "1";
    // private final String REPORT_OPERATOR_TYPE_DADA = "2";
    // private final String DATA_SOURCE_OPERATOR_TYPE_ORGAN = "3";
    // private final String DATA_SOURCE_OPERATOR_TYPE_DADA = "4";

    @Autowired
    private UreportFileService ureportFileService;

    // 编辑报表数据(此处的编辑提供两个入口:一是对报表基本信息的管理,再者是对报表内容的管理)
    @RequestMapping(value = "/updateUreportData", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> updateUreportData(@RequestBody UreportManagerDTO ureportManagerDTO) {
        // 根据不同的操作类型进行不同的处理
        if (StringUtils.isEmpty(ureportManagerDTO.getOperatorType())) {
            throw new CommonException("传入的操作类型operatorType不能为空!");
        }
        // 判断传入的操作类型,选择相应调用的service方法
        if(ConstantUtils.REPORT_OPERATOR_TYPE_ORGAN.equals(ureportManagerDTO.getOperatorType())) {
            ureportFileService.updateUreportFile(ureportManagerDTO.getUreportFile());
        }else if(ConstantUtils.REPORT_OPERATOR_TYPE_DADA.equals(ureportManagerDTO.getOperatorType())) {
            
        }
        return getJsonResult();
    }

    // 删除数据库中的报表文件
    @RequestMapping(value = "/deleteUreportData", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> deleteUreportData(@RequestBody UreportManagerDTO ureportManagerDTO) {
        // 根据不同的操作类型进行不同的处理
        if (StringUtils.isEmpty(ureportManagerDTO.getOperatorType())) {
            throw new CommonException("传入的操作类型operatorType不能为空!");
        }
        if (StringUtils.isEmpty(ureportManagerDTO.getFileId())) {
            throw new CommonException("传入的操作类型fileId不能为空!");
        }
        // 判断传入的操作类型,选择相应调用的service方法
        if(ConstantUtils.REPORT_OPERATOR_TYPE_ORGAN.equals(ureportManagerDTO.getOperatorType())) {
            ureportFileService.deleteUreportFile(ureportManagerDTO.getFileId());
        }
        return getJsonResult();
    }

    // 根据筛选条件分页列出报表信息
    @RequestMapping(value = "/listUreportDataByPage", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> listUreportDataByPage(@RequestBody UreportManagerDTO ureportManagerDTO) {
        // 根据不同的operatorType调用不同的方法
        if (StringUtils.isEmpty(ureportManagerDTO.getOperatorType())) {
            throw new CommonException("传入的操作类型operatorType不能为空!");
        }
        Page page = ureportManagerDTO.getPage();
        List<PageData> dataList = null;
        // 判断传入的操作类型,选择相应调用的service方法
        if(ConstantUtils.REPORT_OPERATOR_TYPE_ORGAN.equals(ureportManagerDTO.getOperatorType())) {
            // 分页查找机构报表信息(机构适用)
            dataList = ureportFileService.listUreportFileByPage(page);
        }else if(ConstantUtils.REPORT_OPERATOR_TYPE_DADA.equals(ureportManagerDTO.getOperatorType())) {
            // 分页查找公共报表信息(平台备用)
            dataList = null;
        }
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data", dataList);
        return getJsonResult(resultMap);
    }
    
    
    
    // 根据fileId查找相应的数据信息(由于返回的类型不同，portal调用服务需要分情况处理)
    @RequestMapping(value = "/getUreportData", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> getUreportData(@RequestBody UreportManagerDTO ureportManagerDTO) {
        Map<String, Object> resultMap = new HashMap<>(8);
        // 根据不同的操作类型进行不同的处理
        if (StringUtils.isEmpty(ureportManagerDTO.getOperatorType())) {
            throw new CommonException("传入的操作类型operatorType不能为空!");
        }
        if (StringUtils.isEmpty(ureportManagerDTO.getFileId())) {
            throw new CommonException("传入的操作类型fileId不能为空!");
        }
        // 判断传入的操作类型,选择相应调用的service方法
        if(ConstantUtils.REPORT_OPERATOR_TYPE_ORGAN.equals(ureportManagerDTO.getOperatorType())) {
            // 查找机构报表信息(机构适用)
            UreportFile ureportFile = ureportFileService.getUreportFile(ureportManagerDTO.getFileId());
            resultMap.put("data", ureportFile);
        }else if(ConstantUtils.REPORT_OPERATOR_TYPE_DADA.equals(ureportManagerDTO.getOperatorType())) {
            // 查找公共报表信息(平台备用)
            UreportBackup ureportBackup = null;
            resultMap.put("data", ureportBackup);
        }
        return getJsonResult(resultMap);
    }
    
}
