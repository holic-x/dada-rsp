package com.dada.rest.datasource.controller;

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
import com.dada.dto.RoleInfoDTO;
import com.dada.pojo.DataSourceInfo;
import com.dada.pojo.RoleInfo;
import com.dada.rest.datasource.service.DataSourceService;
import com.dada.rest.utils.BaseController;
import com.dada.rest.utils.ResultManage;

@Controller
@RequestMapping("/rest/datasource/datasource")
public class DataSourceController extends BaseController {

    @Autowired
    private DataSourceService dataSourceService;

    // 添加数据源信息
    @RequestMapping(value = "/addDataSource", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addDataSource(@RequestBody DataSourceInfo dataSourceInfo) {
        // 添加数据源信息
        String id = dataSourceService.addDataSource(dataSourceInfo);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定数据源信息
    @RequestMapping(value = "/deleteDataSource", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteDataSource(@RequestBody DataSourceDTO dataSourceDTO) {
        dataSourceService.deleteDataSourceById(dataSourceDTO);
        return getJsonResult();
    }

    // 修改数据源信息
    @RequestMapping(value = "/updateDataSource", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateDataSource(@RequestBody DataSourceInfo dataSourceInfo) {
        dataSourceService.updateDataSource(dataSourceInfo);
        return getJsonResult();
    }

    // 根据数据源id查找指定数据源信息
    @RequestMapping("getDataSourceById/{configId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getDataSourceById(@PathVariable String configId) {
        DataSourceInfo dataSourceInfo = dataSourceService.getDataSource(configId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", dataSourceInfo);
        return getJsonResult(resultMap);
    }

    // 根据筛选条件查找用户数据源信息
    @RequestMapping(value = "/listDataSource", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listDataSource(@RequestBody DataSourceDTO dataSourceDTO) {
        List<DataSourceInfo> dataSourceList = dataSourceService.listDataSource(dataSourceDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", dataSourceList);
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
    
    @RequestMapping(value = "/listDataSourceByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> listDataSourceByPage(@RequestBody Page page) {
        List<PageData> dataSourceList = dataSourceService.listDataSourceByPage(page);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", dataSourceList);
        return getJsonResult(resultMap);
    }
    
    // 设置机构数据源首选备选状态
    @RequestMapping(value = "/setPreferState", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> setPreferState(@RequestBody DataSourceDTO dataSourceDTO) {
        dataSourceService.setPreferState(dataSourceDTO);
        return getJsonResult();
    }
}
