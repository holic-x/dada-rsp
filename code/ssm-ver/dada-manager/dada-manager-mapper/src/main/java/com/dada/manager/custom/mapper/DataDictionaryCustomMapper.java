package com.dada.manager.custom.mapper;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.pojo.DataDictionary;

public interface DataDictionaryCustomMapper {
    // 根据数据节点编码查找对应的子节点信息
    List<DataDictionary> listLeafByDataCode(String dataCode);
    
    // 分页查找数据信息
    List<PageData> selectDataByPage(Page page);
    
}