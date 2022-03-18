package com.dada.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.HttpClientUtil;
import com.dada.common.utils.IDUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DataSourceDTO;
import com.dada.pojo.DataSourceInfo;
import com.dada.pojo.Department;
import com.dada.portal.service.DataSourceService;
import com.dada.portal.utils.ResultManage;

@Service("dataSourceServiceImpl")
public class DataSourceServiceImpl implements DataSourceService{
    
    // 数据源管理相关基本路径
    @Value("${REST_BASE_DATA_SOURCE_URL}")
    private String REST_BASE_DATA_SOURCE_URL;

    // ---------机构信息维护相关--------------- //
    @Value("${REST_DATA_SOURCE_ADD}")
    private String REST_DATA_SOURCE_ADD;

    @Value("${REST_DATA_SOURCE_DELETE}")
    private String REST_DATA_SOURCE_DELETE;

    @Value("${REST_DATA_SOURCE_UPDATE}")
    private String REST_DATA_SOURCE_UPDATE;

    @Value("${REST_DATA_SOURCE_GET}")
    private String REST_DATA_SOURCE_GET;

    @Value("${REST_DATA_SOURCE_LIST}")
    private String REST_DATA_SOURCE_LIST;

    @Value("${REST_DATA_SOURCE_LIST_PAGE}")
    private String REST_DATA_SOURCE_LIST_PAGE;
    
    @Value("${REST_DATA_SOURCE_SET_PREFER}")
    private String REST_DATA_SOURCE_SET_PREFER;
    
    @Override
    public String addDataSource(DataSourceInfo dataSourceInfo) {
        // 封装默认属性
        String configId = IDUtils.genRandomUUId();
        dataSourceInfo.setConfigId(configId);
        dataSourceInfo.setCreateTime(new Date());
        dataSourceInfo.setModifyTime(new Date());
        dataSourceInfo.setPreferState(ConstantUtils.DEFAULT_DATA);
        dataSourceInfo.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        // 如果传入的categoryId为空,说明数据传送异常,设置categoryId为"-1"标识错误信息
        if (StringUtils.isEmpty(dataSourceInfo.getCategoryId())) {
            dataSourceInfo.setCategoryId(ConstantUtils.DEFAULT_DATA);
        }
        // 获取客户端token数据,调用rest服务查找当前登录的用户信息(在前端页面进行控制)
        // 将roleInfo转化为json数据
        String jsonParam = JSON.toJSONString(dataSourceInfo);
        // 调用rest服务实现roleInfo数据添加
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_DATA_SOURCE_URL + REST_DATA_SOURCE_ADD, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构数据源信息添加失败");
        }
        Map<String, Object> resultMap = result.getData();
        // 数据转化:String类型数据不能转化为JSONObject(以下方式抛出异常)
        // String id =
        // JSON.toJavaObject((JSONObject)resultMap.get("id"),String.class);
        String id = JSON.toJSONString(resultMap.get("id"));
        return id;
    }

    @Override
    public boolean deleteDataSourceById(DataSourceDTO dataSourceDTO) {
        String jsonParam = JSON.toJSONString(dataSourceDTO);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_DATA_SOURCE_URL + REST_DATA_SOURCE_DELETE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构数据源信息删除失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public boolean updateDataSource(DataSourceInfo dataSourceInfo) {
        // 更新修改时间
        dataSourceInfo.setModifyTime(new Date());
        String jsonParam = JSON.toJSONString(dataSourceInfo);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_DATA_SOURCE_URL + REST_DATA_SOURCE_UPDATE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构数据源信息修改失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public DataSourceInfo getDataSource(String configId) {
        // 调用rest服务获取机构数据源角色
        String url = REST_BASE_DATA_SOURCE_URL + REST_DATA_SOURCE_GET + "/" + configId;
        String jsonResult = HttpClientUtil.doGet(url);
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构数据源信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        DataSourceInfo dataSourceInfo = JSON.toJavaObject((JSONObject) resultMap.get("data"), DataSourceInfo.class);
        return dataSourceInfo;
    }

    @Override
    public List<PageData> listDataSourceByPage(Page page) {
        // 调用rest服务获取机构数据源分页信息
        String url = REST_BASE_DATA_SOURCE_URL + REST_DATA_SOURCE_LIST_PAGE ;
        String jsonParam = JSON.toJSONString(page);
        String jsonResult = HttpClientUtil.doPostJson(url,jsonParam);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构数据源信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<PageData> dataSourceList = JSON.parseArray(resultMap.get("data").toString(), PageData.class);
        return dataSourceList;
    }

    @Override
    public boolean setPreferState(DataSourceDTO dataSourceDTO) {
        // 设置机构数据源状态(1.首选 2.备选  -1.默认普鸥汀)
        String jsonParam = JSON.toJSONString(dataSourceDTO);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_DATA_SOURCE_URL + REST_DATA_SOURCE_SET_PREFER, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构数据源状态变更失败");
        }
        // 访问数据成功
        return true;
    }

}
