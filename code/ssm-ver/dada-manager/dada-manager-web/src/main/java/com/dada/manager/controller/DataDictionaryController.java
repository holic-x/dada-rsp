package com.dada.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dada.dto.DataDictionaryDTO;
import com.dada.manager.entity.CustomJsonIgnore;
import com.dada.manager.entity.ZTreeNode;
import com.dada.manager.service.DataDictionaryService;
import com.dada.manager.web.utils.BaseController;
import com.dada.manager.web.utils.ResultManage;
import com.dada.pojo.AuthorityInfo;
import com.dada.pojo.DataDictionary;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/manager/dataDictionary")
public class DataDictionaryController extends BaseController {

    @Autowired
    private DataDictionaryService dataService;

    // 添加数据信息
    @RequestMapping(value = "/addData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> addDataDictionary(@RequestBody DataDictionary data) {
        // 添加数据信息
        String id = dataService.addDataDictionary(data);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("id", id);
        return getJsonResult(resultMap);
    }

    // 删除指定数据信息
    @RequestMapping(value = "/deleteDatas", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> deleteDatas(@RequestBody DataDictionaryDTO dataDTO) {
        dataService.BatchDeleteDataDictionary(dataDTO.getDataIds());
        return getJsonResult();
    }

    // 修改数据信息页面跳转
    @RequestMapping(value = "/updateDataUI")
    public String updateDataUI(@RequestParam(value = "dataId") String dataId, Model model) {
        DataDictionary data = dataService.getDataDictionary(dataId);
        model.addAttribute("data", data);
        return "dataDictionary/data_edit";
    }

    // 修改数据信息
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> updateDataDictionary(@RequestBody DataDictionary data) {
        dataService.updateDataDictionary(data);
        return getJsonResult();
    }

    // 根据数据id查找指定数据信息
    @RequestMapping("/{dataId}")
    @ResponseBody
    public DataDictionary getDataById(@PathVariable String dataId) {
        DataDictionary data = dataService.getDataDictionary(dataId);
        return data;
    }
    
    // 显示数据信息页面跳转
    @RequestMapping(value = "/showDataUI")
    public String showDataUI(@RequestParam(value = "dataId") String dataId, Model model) {
        DataDictionary data = dataService.getDataDictionary(dataId);
        model.addAttribute("data", data);
        return "dataDictionary/data_show";
    }

    // 根据筛选条件查找指定数据信息
    @RequestMapping("/listData")
    @ResponseBody
    public ResponseEntity<ResultManage> listDataDictionary(@RequestBody DataDictionaryDTO dataDTO) {
        List<DataDictionary> dataList = dataService.listDataDictionary(dataDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("dataList", dataList);
        return getJsonResult(resultMap);
    }

    // 根据dataCode查找对应子节点信息
    @RequestMapping("/listLeafByDataCode")
    @ResponseBody
    public ResponseEntity<ResultManage> listLeafByDataCode(@RequestBody DataDictionaryDTO dataDTO) {
        List<DataDictionary> dataList = dataService.listLeafByDataCode(dataDTO);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("dataList", dataList);
        return getJsonResult(resultMap);
    }

    @RequestMapping(value = "/validateRepeatDataCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultManage> validateRepeatDataCode(@RequestBody DataDictionaryDTO dataDTO) {
        boolean validateRepeat = dataService.validateDataCode(dataDTO.getDataCode(), dataDTO.getDataId());
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("valid", String.valueOf(validateRepeat));
        return getJsonResult(resultMap);
    }
    
    @RequestMapping(value = "/listDataByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listDataByPage(@RequestBody Page page) {
        List<PageData> dataList = dataService.listDataByPage(page);
        Map<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("data",dataList);
        return resultMap;
    }
    
    // 根据筛选条件查找指定的数据字典数据
    @RequestMapping("/listDataByTree")
    @ResponseBody
    public ResponseEntity<ResultManage> listDataByTree() {
        List<DataDictionary> dataList = dataService.listDataDictionary(null);
        Map<String, Object> resultMap = new HashMap<>(4);
        // 可根据权限封装现有的节点信息
        List<ZTreeNode> zTreeNodes = new ArrayList<>(dataList.size());
        for (DataDictionary data : dataList) {
            ZTreeNode node = new ZTreeNode();
            // 基本ZTreeNode属性
            node.setNodeId(data.getDataId());
            node.setNodePid(data.getParentId());
            node.setName(data.getDataName());
            // ZTree其余属性
            // 如果parentId为‘-1’,则为父节点(默认为true,并默认展开)
            String parentId = data.getParentId();
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

}
