package com.dada.portal.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dada.common.utils.JDBCUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.config.DataSourceConfig;
import com.dada.dto.DataSourceDTO;
import com.dada.pojo.DataSourceInfo;
import com.dada.portal.service.DataSourceService;
import com.dada.portal.utils.BaseController;
import com.dada.portal.utils.ResultManage;

@Controller
@RequestMapping("/portal/dataSource")
public class DataSourceController extends BaseController {

    @Autowired
    private DataSourceService dataSourceService;
    
    // 添加数据源信息
    @RequestMapping(value = "/addDataSource", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ResultManage> addDataSource(@RequestBody DataSourceDTO dataSourceDTO) {
        DataSourceInfo dataSourceInfo = new DataSourceInfo();
        BeanUtils.copyProperties(dataSourceDTO, dataSourceInfo);
        dataSourceInfo.setConfigContent(JSON.toJSONString(dataSourceDTO.getDataSourceConfig()));
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
    
    // 修改数据源信息页面跳转
    @RequestMapping(value = "/updateDataSourceUI/{configId}")
    public String updateDataSourceUI(@PathVariable String configId,Model model) {
        DataSourceInfo dataSourceInfo = dataSourceService.getDataSource(configId);
        // 封装数据源信息到指定页面
        model.addAttribute("dataSourceInfo", dataSourceInfo);
        model.addAttribute("config",JSON.parseObject(dataSourceInfo.getConfigContent(), DataSourceConfig.class)) ;
        return "portal/datasource/datasource_edit";
    }

    // 修改数据源信息
    @RequestMapping(value = "/updateDataSource", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateDataSource(@RequestBody DataSourceInfo dataSourceInfo) {
        dataSourceService.updateDataSource(dataSourceInfo);
        return getJsonResult();
    }

    // 根据数据源id查找指定数据源信息
    @RequestMapping("/getDataSourceById/{configId}")
    @ResponseBody
    public ResponseEntity<ResultManage> getDataSourceById(@PathVariable String configId) {
        DataSourceInfo dataSourceInfo = dataSourceService.getDataSource(configId);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("data", dataSourceInfo);
        resultMap.put("config",JSON.parseObject(dataSourceInfo.getConfigContent(), DataSourceConfig.class)) ;
        return getJsonResult(resultMap);
    }

    // dataTable对应格式
    @RequestMapping(value = "/listDataSourceByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listDataSourceInfoByPage(@RequestBody Page page) {
        List<PageData> dataSourceInfoList = dataSourceService.listDataSourceByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data",dataSourceInfoList);
        return resultMap;
    }

    // 设置数据源属性(首选、备选数据源)
    @RequestMapping(value = "/setPreferState", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> setPreferState(@RequestBody DataSourceDTO dataSourceDTO) {
        dataSourceService.setPreferState(dataSourceDTO);
        return getJsonResult();
    }
    
    
    // 测试数据库连接
    @RequestMapping(value = "/testConnection", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> testConnection(@RequestBody DataSourceConfig dataSourceConfig) {
        String username = dataSourceConfig.getUsername();
        String password = dataSourceConfig.getPassword();
        String driver = dataSourceConfig.getDriver();
        String url = dataSourceConfig.getUrl();
        // 获取单例模式:数据源(java.lang.ExceptionInInitializerError)
        // Connection connection = JDBCUtils.getJDBCUtils().getConnection(username, password, driver, url);
        Connection connection = JDBCUtils.getConnection(username, password, driver, url);
        Map<String, Object> resultMap = new HashMap<>(4);
        String connectionState = "fail";
        if(connection!=null) {
            connectionState = "success";
        }
        resultMap.put("state", connectionState);
        // 释放数据库链接
        JDBCUtils.freeAll(connection, null, null);
        return getJsonResult(resultMap);
    }
}
