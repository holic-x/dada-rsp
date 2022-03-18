package com.dada.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.HttpClientUtil;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.portal.service.ReportManagerService;
import com.dada.portal.utils.ResultManage;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.pojo.UreportFileManager;

@Service("reportManagerServiceImpl")
public class ReportManagerServiceImpl implements ReportManagerService{

    @Value("${REPORT_MANAGER_BASE_URL}")
    private String REPORT_MANAGER_BASE_URL;

    @Value("${REPORT_MANAGER_UPDATE}")
    private String REPORT_MANAGER_UPDATE;

    @Value("${REPORT_MANAGER_DELETE}")
    private String REPORT_MANAGER_DELETE;

    @Value("${REPORT_MANAGER_GET}")
    private String REPORT_MANAGER_GET;

    @Value("${REPORT_MANAGER_LIST_BY_PAGE}")
    private String REPORT_MANAGER_LIST_BY_PAGE;
    
    @Value("${REPORT_MANAGER_LIST_BY_NO_CLASSIFY}")
    private String REPORT_MANAGER_LIST_BY_NO_CLASSIFY;
    
    // 验证传入表名不能为空
    private void validateTableName(UreportFileSearchParam searchParam) {
        if(searchParam==null) {
            throw new CommonException("dada-portal:必须指定searchParam对象");
        }else {
            if(StringUtils.isEmpty(searchParam.getTableName())) {
                throw new CommonException("dada-portal:指定searchParam对象中tableName属性不能为空");
            }
        }
    }
    
    @Override
    public boolean updateUreportData(UreportFileSearchParam searchParam) {
        // 验证表名操作
        validateTableName(searchParam);
        // 验证要修改的对象参数（考虑dada-report中service层对参数做了限制,此处只负责调用接口不做重复判断）
        /*
        UreportFileManager ureportFileManager = searchParam.getUreportFileManager();
        if(ureportFileManager==null) {
            throw new CommonException("dada-portal:指定searchParam中的ureportFileManager属性不能为空");
        }
        */
        //
        String jsonParam = JSON.toJSONString(searchParam);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_MANAGER_BASE_URL + REPORT_MANAGER_UPDATE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表信息修改失败");
        }
        return true;
    }

    @Override
    public boolean deleteUreportData(UreportFileSearchParam searchParam) {
        String jsonParam = JSON.toJSONString(searchParam);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_MANAGER_BASE_URL + REPORT_MANAGER_DELETE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表信息删除失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public List<PageData> listUreportDataByPage(Page page) {
        String jsonParam = JSON.toJSONString(page);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_MANAGER_BASE_URL + REPORT_MANAGER_LIST_BY_PAGE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表信息获取失败");
        }
        Map<String,Object> resultMap = result.getData();
        List<PageData> ureportFileManagerList = JSON.parseArray(resultMap.get("data").toString(), PageData.class);
        return ureportFileManagerList;
    }

    @Override
    public UreportFileManager getUreportData(UreportFileSearchParam searchParam) {
        String jsonParam = JSON.toJSONString(searchParam);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_MANAGER_BASE_URL + REPORT_MANAGER_GET, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表信息获取失败");
        }
        Map<String,Object> resultMap = result.getData();
        UreportFileManager ureportFileManager = JSONObject.toJavaObject((JSONObject)result.getData().get("data"), UreportFileManager.class);
        return ureportFileManager;
    }

    @Override
    public List<UreportFileManager> listReportFileByNoClassify(UreportFileSearchParam searchParam) {
        String jsonParam = JSON.toJSONString(searchParam);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_MANAGER_BASE_URL + REPORT_MANAGER_LIST_BY_NO_CLASSIFY, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表信息获取失败");
        }
        Map<String,Object> resultMap = result.getData();
        List<UreportFileManager> ureportFileManagerList = JSON.parseArray(resultMap.get("data").toString(), UreportFileManager.class);
        return ureportFileManagerList;
    }

}
