package com.dada.report.service;

import com.dada.config.DataSourceConfig;


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

    // 根据筛选条件查找数据源信息
    public DataSourceConfig getCategoryDataSource(String categoryId,String preferState);
    
    // 根据筛选条件查找数据源信息
    public DataSourceConfig getCategoryDataSource(String preferState);
    
}
