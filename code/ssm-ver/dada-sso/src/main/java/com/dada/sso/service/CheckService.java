package com.dada.sso.service;

import java.util.List;

import com.dada.pojo.DataDictionary;
import com.dada.sso.dto.DataDictionaryDTO;



public interface CheckService {
	
	// 验证机构分类编码(唯一标识对应的模块)
    public String checkCategoryCode(String categoryCode);
    
    // 验证机构注册的数据(校验的数据、校验的数据类型-即校验哪个字段)
    public boolean checkUserData(String dataValue,String dataField);
    
    // 验证用户注册的数据
    public boolean checkOrganCategoryData(String dataValue,String dataField);
    
    // 根据父节点的数据编码查找对应子节点的数据信息
    public List<DataDictionary> listLeafByDataCode(DataDictionaryDTO dataDTO);

}
