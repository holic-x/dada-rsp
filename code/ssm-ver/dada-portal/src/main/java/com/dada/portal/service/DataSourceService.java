package com.dada.portal.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DataSourceDTO;
import com.dada.pojo.DataSourceInfo;

public interface DataSourceService {
    
    // 添加数据源信息
    public String addDataSource(DataSourceInfo dataSourceInfo);

    // 根据数据源id删除数据源信息
    public boolean deleteDataSourceById(DataSourceDTO dataSourceDTO);

    // 根据数据源id修改数据源信息
    public boolean updateDataSource(DataSourceInfo dataSourceInfo);

    // 根据数据源id获取数据源信息
    public DataSourceInfo getDataSource(String configId);

    // 根据筛选条件分页查找数据源信息
    public List<PageData> listDataSourceByPage(Page page);
    
    // 设置机构首选、备选数据源信息
    public boolean setPreferState(DataSourceDTO dataSourceDTO);
}
