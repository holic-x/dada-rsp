package com.dada.rest.datasource.service;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DataSourceDTO;
import com.dada.pojo.DataSourceInfo;


/**
 * <p>项目名称:dada-rest</p>
 * <p>包名称:com.dada.rest.system.service.RoleService</p>
 * <p>文件名称:DataSourceService.java</p>
 * <p>功能描述:数据源管理相关service</p>
 * <p>其他说明: 实现数据源的CRUD   </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月19日下午9:48:05 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface DataSourceService {

    // 添加数据源信息
    public String addDataSource(DataSourceInfo dataSourceInfo);

    // 根据数据源id删除数据源信息
    public boolean deleteDataSourceById(DataSourceDTO dataSourceDTO);

    // 批量删除数据源信息
    public boolean BatchDeleteDataSource(DataSourceDTO dataSourceDTO);

    // 根据数据源id修改数据源信息
    public boolean updateDataSource(DataSourceInfo dataSourceInfo);

    // 根据数据源id获取数据源信息
    public DataSourceInfo getDataSource(String configId);

    // 根据筛选条件查找数据源信息
    public List<DataSourceInfo> listDataSource(DataSourceDTO dataSourceDTO);
    
    // 根据筛选条件分页查找数据源信息
    public List<PageData> listDataSourceByPage(Page page);
    
    // 设置数据源首选、备选状态(configId、preferState)
    public boolean setPreferState(DataSourceDTO dataSourceDTO);
}
