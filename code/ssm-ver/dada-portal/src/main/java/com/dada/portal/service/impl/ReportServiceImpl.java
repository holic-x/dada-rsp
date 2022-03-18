package com.dada.portal.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bstek.ureport.Utils;
import com.bstek.ureport.export.ExportManager;
import com.bstek.ureport.export.html.HtmlReport;
import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.HttpClientUtil;
import com.dada.common.utils.IDUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.ReportClassifyDTO;
import com.dada.dto.UreportManagerDTO;
import com.dada.pojo.ReportClassify;
import com.dada.pojo.ReportClassifyLink;
import com.dada.pojo.UreportBackup;
import com.dada.pojo.UreportFile;
import com.dada.portal.service.ReportService;
import com.dada.portal.utils.ResultManage;

@Service("reportServiceImpl")
@Transactional
public class ReportServiceImpl implements ReportService {

    @Value("${REPORT_DATA_BASE_URL}")
    private String REPORT_DATA_BASE_URL;

    @Value("${REPORT_DATA_UPDATE}")
    private String REPORT_DATA_UPDATE;

    @Value("${REPORT_DATA_DELETE}")
    private String REPORT_DATA_DELETE;

    @Value("${REPORT_DATA_GET}")
    private String REPORT_DATA_GET;

    @Value("${REPORT_DATA_LIST_BY_PAGE}")
    private String REPORT_DATA_LIST_BY_PAGE;
    
    // ------ 报表分类、关联管理相关 -------- //
    @Value("${REPORT_CLASSIFY_BASE_URL}")
    private String REPORT_CLASSIFY_BASE_URL;
    
    @Value("${REPORT_CLASSIFY_ADD}")
    private String REPORT_CLASSIFY_ADD;
    
    @Value("${REPORT_CLASSIFY_DELETE}")
    private String REPORT_CLASSIFY_DELETE;
    
    @Value("${REPORT_CLASSIFY_UPDATE}")
    private String REPORT_CLASSIFY_UPDATE;
    
    @Value("${REPORT_CLASSIFY_GET}")
    private String REPORT_CLASSIFY_GET;
    
    @Value("${REPORT_CLASSIFY_LIST}")
    private String REPORT_CLASSIFY_LIST;
    
    @Value("${REPORT_CLASSIFY_LIST_PAGE}")
    private String REPORT_CLASSIFY_LIST_PAGE;
    
    @Value("${REPORT_CLASSIFY_LINK_SET}")
    private String REPORT_CLASSIFY_LINK_SET;
    
    @Value("${REPORT_CLASSIFY_LINK_LIST_PAGE}")
    private String REPORT_CLASSIFY_LINK_LIST_PAGE;
    
    @Value("${REPORT_CLASSIFY_LINK_HIDE_STATUS_SET}")
    private String REPORT_CLASSIFY_LINK_HIDE_STATUS_SET;

    @Override
    public boolean updateUreportData(UreportManagerDTO ureportManagerDTO) {
        // 根据不同的操作类型进行不同的处理
        if (StringUtils.isEmpty(ureportManagerDTO.getOperatorType())) {
            throw new CommonException("dada-portal:传入的操作类型operatorType不能为空!");
        }
        if (ConstantUtils.REPORT_OPERATOR_TYPE_ORGAN.equals(ureportManagerDTO.getOperatorType())) {
            // 机构报表管理操作
            UreportFile ureportFile = ureportManagerDTO.getUreportFile();
            if (ureportFile != null) {
                ureportFile.setModifyTime(new Date());
            }
        } else if (ConstantUtils.REPORT_OPERATOR_TYPE_DADA.equals(ureportManagerDTO.getOperatorType())) {
            // 平台备用报表管理操作
            UreportBackup ureportBackup = ureportManagerDTO.getUreportBackup();
            if (ureportBackup != null) {
                ureportBackup.setModifyTime(new Date());
            }
        }
        String jsonParam = JSON.toJSONString(ureportManagerDTO);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_DATA_BASE_URL + REPORT_DATA_UPDATE, jsonParam);
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
    public boolean deleteUreportData(UreportManagerDTO ureportManagerDTO) {
        String jsonParam = JSON.toJSONString(ureportManagerDTO);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_DATA_BASE_URL + REPORT_DATA_DELETE, jsonParam);
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

    /**
     * listUreportData:分页获取报表信息 
     * @author thanos
     * @param fileId
     * @param operatorType
     * @return  <br/>
     * @since JDK 1.8
     */
    private Map<String, Object> listUreportData(String operatorType, Page page) {
        if (StringUtils.isEmpty(operatorType)) {
            throw new CommonException("dada-portal:传入的操作类型operatorType不能为空!");
        }
        UreportManagerDTO ureportManagerDTO = new UreportManagerDTO();
        ureportManagerDTO.setOperatorType(operatorType);
        ureportManagerDTO.setPage(page);
        // 根据不同的操作类型进行不同的处理
        String jsonParam = JSON.toJSONString(ureportManagerDTO);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_DATA_BASE_URL + REPORT_DATA_LIST_BY_PAGE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表信息获取失败");
        }
        // 获取报表文件信息
        Map<String, Object> resultMap = result.getData();
        return resultMap;
    }

    @Override
    public List<PageData> listUreportFileByPage(Page page) {
        Map<String, Object> resultMap = listUreportData(ConstantUtils.REPORT_OPERATOR_TYPE_ORGAN, page);
        List<PageData> ureportDataList = JSON.parseArray(resultMap.get("data").toString(), PageData.class);
        return ureportDataList;
    }

    @Override
    public List<PageData> listUreportBackupByPage(Page page) {
        Map<String, Object> resultMap = listUreportData(ConstantUtils.REPORT_OPERATOR_TYPE_DADA, page);
        List<PageData> ureportDataList = JSON.parseArray(resultMap.get("data").toString(), PageData.class);
        return ureportDataList;
    }

    /**
     * getUreportData:私有封装获取报表文件数据的方法 
     * @author thanos
     * @param fileId 文件数据id
     * @param operatorType 操作类型
     * @return  Map<String,Object>数据
     * @since JDK 1.8
     */
    private Map<String, Object> getUreportData(String fileId, String operatorType) {
        if (StringUtils.isEmpty(fileId)) {
            throw new CommonException("dada-portal:传入的操作类型fileId不能为空!");
        }
        if (StringUtils.isEmpty(operatorType)) {
            throw new CommonException("dada-portal:传入的操作类型operatorType不能为空!");
        }
        UreportManagerDTO ureportManagerDTO = new UreportManagerDTO();
        ureportManagerDTO.setFileId(fileId);
        ureportManagerDTO.setOperatorType(operatorType);
        // 根据不同的操作类型进行不同的处理
        String jsonParam = JSON.toJSONString(ureportManagerDTO);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_DATA_BASE_URL + REPORT_DATA_GET, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表信息删除失败");
        }
        // 获取报表文件信息
        Map<String, Object> resultMap = result.getData();
        return resultMap;
    }

    @Override
   // public UreportFile getUreportFile(String fileId,String operatorType) {
   public UreportFile getUreportFile(String fileId) {
        Map<String, Object> resultMap = getUreportData(fileId,ConstantUtils.REPORT_OPERATOR_TYPE_ORGAN);
        UreportFile ureportFile = JSON.toJavaObject((JSONObject) resultMap.get("data"), UreportFile.class);
        return ureportFile;
    }

    @Override
   // public UreportBackup getUreportBackup(String fileId,String operatorType) {
     public UreportBackup getUreportBackup(String fileId ) {
        Map<String, Object> resultMap = getUreportData(fileId,ConstantUtils.REPORT_OPERATOR_TYPE_DADA);
        UreportBackup ureportBackup = JSON.toJavaObject((JSONObject) resultMap.get("data"), UreportBackup.class);
        return ureportBackup;
    }

    // 无效方法
    @Override
    public void previewReport(HttpServletRequest request , HttpServletResponse response) {
        ExportManager exportManager=(ExportManager)Utils.getApplicationContext().getBean(ExportManager.BEAN_ID);
        Map<String,Object> parameters=new HashMap<String,Object>();
        HtmlReport htmlReport = exportManager.exportHtml("file:2.ureport.xml",request.getContextPath(),parameters);
        PrintWriter pw;
        try {
            pw = response.getWriter();
            //输出CSS样式
            pw.println("打印数据呢");
            pw.println("<style type=\"text/css\">");
            pw.println(htmlReport.getStyle());
            pw.println("</style>");
            //输出报表内容
            pw.println(htmlReport.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------ 报表分类管理相关 ------------------ //
    @Override
    public String addReportClassify(ReportClassify classify) {
        // 设置classify默认属性
        String classifyId = IDUtils.genRandomUUId();
        classify.setClassifyId(classifyId);
        classify.setCreateTime(new Date());
        classify.setModifyTime(new Date());
        classify.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        String jsonParam = JSON.toJSONString(classify);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_CLASSIFY_BASE_URL + REPORT_CLASSIFY_ADD, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类信息添加失败");
        }
        return classifyId;
    }

    @Override
    public boolean deleteReportClassifyById(String classifyId) {
        ReportClassifyDTO classifyDTO = new ReportClassifyDTO();
        classifyDTO.setClassifyId(classifyId);
        String jsonParam = JSON.toJSONString(classifyDTO);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_CLASSIFY_BASE_URL + REPORT_CLASSIFY_DELETE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类信息删除失败");
        }
        return true;
    }

    @Override
    public boolean updateReportClassify(ReportClassify classify) {
        // 设置classify默认属性
        classify.setModifyTime(new Date());
        String jsonParam = JSON.toJSONString(classify);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_CLASSIFY_BASE_URL + REPORT_CLASSIFY_UPDATE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类信息添加失败");
        }
        return true;
    }

    @Override
    public ReportClassify getReportClassify(String classifyId) {
        String jsonResult = HttpClientUtil.doPost(REPORT_CLASSIFY_BASE_URL+REPORT_CLASSIFY_GET+"/"+classifyId);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类信息获取失败");
        }
        // 获取报表文件信息
        Map<String, Object> resultMap = result.getData();
        ReportClassify classify = JSON.toJavaObject((JSONObject)resultMap.get("data"), ReportClassify.class);
        return classify;
    }

    @Override
    public List<ReportClassify> listReportClassify(ReportClassifyDTO classifyDTO) {
        // classifyDTO:根据categoryId、deptId查找指定分类
        String categoryId = classifyDTO.getCategoryId();
        String deptId = classifyDTO.getDeptId();
        if(StringUtils.isEmpty(categoryId)) {
            throw new CommonException("dada-portal:传入的categoryId不能为空");
        }
        if(StringUtils.isEmpty(deptId)) {
            throw new CommonException("dada-portal:传入的deptId不能为空");
        }
        String jsonParam = JSON.toJSONString(classifyDTO);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_CLASSIFY_BASE_URL + REPORT_CLASSIFY_LIST, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<ReportClassify> classifyList = JSONArray.parseArray(resultMap.get("data").toString(), ReportClassify.class);
        return classifyList;
    }

    @Override
    public List<PageData> listReportClassifyByPage(Page page) {
        String jsonParam = JSON.toJSONString(page);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_CLASSIFY_BASE_URL + REPORT_CLASSIFY_LIST_PAGE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<PageData> classifyList = JSONArray.parseArray(resultMap.get("data").toString(), PageData.class);
        return classifyList;
    }

    // ------------- 报表分类关联管理相关:管理对应分类下的报表信息 ------------------------ //
    @Override
    public boolean setReportClassifyLink(ReportClassifyDTO classifyDTO) {
        String operatorType = classifyDTO.getOperatorType();
        // ReportClassifyDTO:categoryId、deptId、classifyId作为主要主体
        ReportClassifyLink link ;
        String categoryId = classifyDTO.getCategoryId();
        String deptId = classifyDTO.getDeptId();
        String classifyId = classifyDTO.getClassifyId();
        String userId = classifyDTO.getUserId();
        List<String> linkIds = classifyDTO.getLinkIds();
        List<String> reportIds = classifyDTO.getReportIds();
        if(ConstantUtils.LINK_OPERATOR_TYPR_SAVE.equals(operatorType)) {
            if(StringUtils.isEmpty(operatorType)) {
                throw new CommonException("dada-portal:需指定operatorType标识要指定操作");
            }
            if(StringUtils.isEmpty(categoryId)) {
                throw new CommonException("dada-portal:传入的categoryId不能为空");
            }
            if(StringUtils.isEmpty(deptId)) {
                throw new CommonException("dada-portal:传入的deptId不能为空");
            }
            if(StringUtils.isEmpty(classifyId)) {
                throw new CommonException("dada-portal:传入的classifyId不能为空");
            }
            // 标识添加报表分类关联操作,封装要进行添加的关联管理
            if(CollectionUtils.isEmpty(reportIds)) {
                return true;
            }
            List<ReportClassifyLink> linkList = new ArrayList<>(reportIds.size());
            for(String reportId : reportIds) {
                link = new ReportClassifyLink();
                link.setLinkId(IDUtils.genRandomUUId());
                // 默认发布标识hide_status为0,标识发布
                link.setClassifyId(classifyId);
                link.setHideStatus(ConstantUtils.REPORT_FILE_HIDE_STATUS_PUBLIC);
                link.setUserId(userId);
                link.setCategoryId(categoryId);
                link.setDeptId(deptId);
                link.setReportId(reportId);
                linkList.add(link);
            }
            // 设置linkList属性
            classifyDTO.setLinkList(linkList);
        }else if(ConstantUtils.LINK_OPERATOR_TYPR_DELETE.equals(operatorType)) {
            // 暂不作任何操作(直接将封装的linkIds传入即可)
            if(CollectionUtils.isEmpty(linkIds)) {
                return true;
            }
        }
        String jsonParam = JSON.toJSONString(classifyDTO);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_CLASSIFY_BASE_URL + REPORT_CLASSIFY_LINK_SET, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类关联信息修改失败");
        }
        return true;
    }

    @Override
    public List<PageData> listLinkList(Page page) {
        String jsonParam = JSON.toJSONString(page);
        String jsonResult = HttpClientUtil.doPostJson(REPORT_CLASSIFY_BASE_URL + REPORT_CLASSIFY_LINK_LIST_PAGE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<PageData> linkList = JSONArray.parseArray(resultMap.get("data").toString(), PageData.class);
        return linkList;
    }

    @Override
    public boolean setLinkHideStatus(ReportClassifyDTO classifyDTO) {
        String jsonParam = JSON.toJSONString(classifyDTO); 
        String jsonResult = HttpClientUtil.doPostJson(REPORT_CLASSIFY_BASE_URL + REPORT_CLASSIFY_LINK_HIDE_STATUS_SET, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("dada-portal:调用dada-report服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-report服务出错-报表分类关联信息修改失败");
        }
        return true;
    }

}
