package com.dada.manager.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DataDictionaryDTO;
import com.dada.pojo.DataDictionary;

public interface DataDictionaryService {

    // 添加数据信息
    public String addDataDictionary(DataDictionary data);

    // 根据数据id删除数据信息
    public boolean deleteDataDictionaryById(String dataId);

    // 批量删除数据信息
    public boolean BatchDeleteDataDictionary(List<String> dataIds);

    // 根据数据id修改数据信息
    public boolean updateDataDictionary(DataDictionary data);

    // 根据数据id获取数据信息
    public DataDictionary getDataDictionary(String dataId);

    // 根据筛选条件查找数据信息
    public List<DataDictionary> listDataDictionary(DataDictionaryDTO dataDTO);

    // 根据父节点的数据编码查找对应子节点的数据信息
    public List<DataDictionary> listLeafByDataCode(DataDictionaryDTO dataDTO);

    // 验证重复的数据编码(唯一标识)
    public boolean validateDataCode(String dataCode, String dataId);
    
    // 根据筛选条件分页查找数据信息
    public List<PageData> listDataByPage(Page page);

}
