package com.dada.sso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dada.pojo.DataDictionary;
import com.dada.sso.dto.DataDictionaryDTO;
import com.dada.sso.service.CheckService;
import com.dada.sso.utils.BaseController;
import com.dada.sso.utils.ResultManage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@Controller
@RequestMapping("/sso")
public class CheckDataController extends BaseController {
    
	@Autowired
	private CheckService checkService;
	
	/*
	@RequestMapping(value = "/checkUserData")
	@ResponseBody
	public ResponseEntity<ResultManage> checkUserData(@RequestParam("dataValue")String dataValue,@RequestParam("dataField")String dataField) {
	public ResponseEntity<ResultManage> checkUserData(@RequestBody DataCheckDTO dataCheckDTO) {
	// 访问路径:localhost:8083/sso/checkUserData?dataValue=xx&dataField=xx
	    // remote传入了默认的数据，以post、json传入会报415错误
	    boolean flag = checkService.checkUserData(dataValue, dataField);
		Map<String, Object> resultMap = new HashMap<>(4);
		resultMap.put("valid", flag);
		return getJsonResult(resultMap);
	}
	 */
	@RequestMapping(value = "/checkUserData")
    @ResponseBody
    public String checkUserData(@RequestParam("dataValue")String dataValue,@RequestParam("dataField")String dataField) {
        boolean flag = checkService.checkUserData(dataValue, dataField);
        Map<String, Object> resultMap = new HashMap<>(4);
        // 验证用户数据是否可用,可用返回true
        resultMap.put("valid", flag);
        // remote要求返回true or false : map.put("valid", validateRepeat);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }
	
	/*
	@RequestMapping(value = "/checkOrganCategoryData")
	@ResponseBody
	public ResponseEntity<ResultManage> checkOrganCategoryData(@RequestParam("dataValue")String dataValue,@RequestParam("dataField")String dataField) {
	public ResponseEntity<ResultManage> checkOrganCategoryData(@RequestBody DataCheckDTO dataCheckDTO)	
		boolean flag = checkService.checkOrganCategoryData(dataValue, dataField);
		// flag返回true标识机构分类不存在,后台remote验证取相反的数据
		Map<String, Object> resultMap = new HashMap<>(4);
		resultMap.put("valid", !flag);
		return getJsonResult(resultMap);
	}
	 */
	@RequestMapping(value = "/checkOrganCategoryData")
    @ResponseBody
    public String checkOrganCategoryData(@RequestParam("dataValue")String dataValue,@RequestParam("dataField")String dataField) {
	    boolean flag = checkService.checkOrganCategoryData(dataValue, dataField);
        // flag返回true标识机构分类不存在,后台remote验证取相反的数据
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("valid", !flag);
        // remote要求返回true or false : map.put("valid", validateRepeat);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }
	
	@RequestMapping(value = "/checkRepeatCategoryCode")
    @ResponseBody
    public String checkRepeatCategoryCode(@RequestParam("dataValue")String dataValue,@RequestParam("dataField")String dataField) {
        boolean flag = checkService.checkOrganCategoryData(dataValue, dataField);
        // flag返回true标识机构分类不存在,后台remote验证取相反的数据
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("valid", flag);
        // remote要求返回true or false : map.put("valid", validateRepeat);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }
	
	// 根据dataCode查找对应子节点信息
    @RequestMapping("/getData/listLeafByDataCode")
    @ResponseBody
    public ResponseEntity<ResultManage> listLeafByDataCode(@RequestBody DataDictionaryDTO dataDTO) {
        List<DataDictionary> dataList = checkService.listLeafByDataCode(dataDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("dataList", dataList);
        return getJsonResult(resultMap);
    }

}
