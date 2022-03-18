package com.dada.report.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.dada.common.constant.ConstantUtils;
import com.dada.config.DataSourceConfig;
import com.dada.mapper.DataSourceInfoMapper;
import com.dada.pojo.DataSourceInfo;
import com.dada.pojo.DataSourceInfoExample;
import com.dada.pojo.DataSourceInfoExample.Criteria;
import com.dada.report.service.DataSourceService;

/**
 * <p>项目名称:dada-rest</p>
 * <p>包名称:com.dada.rest.datasource.service.impl.DataSourceServiceImpl</p>
 * <p>文件名称:DataSourceServiceImpl.java</p>
 * <p>功能描述:数据源管理相关实现类</p>
 * <p>其他说明:实现数据源基本CRUD功能    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月25日下午12:01:53 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */

@Service("dataSourceServiceImpl")
@Transactional
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceInfoMapper dataSourceInfoMapper;

    // 使用threadLocal获取当前登录用户信息
    @Override
    public DataSourceConfig getCategoryDataSource(String categoryId,String preferState) {
        DataSourceInfoExample dataSourceInfoExample = new DataSourceInfoExample();
        Criteria criteria = dataSourceInfoExample.createCriteria();
        if (!StringUtils.isEmpty(categoryId)) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if(!StringUtils.isEmpty(preferState)) {
            criteria.andPreferStateEqualTo(preferState);
        }
        // 默认查找未被删除的数据
        criteria.andDelTagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        // 查找的时候将text文本属性的数据一并筛选出来
        List<DataSourceInfo> dataSourceList = dataSourceInfoMapper.selectByExampleWithBLOBs(dataSourceInfoExample);
        if(!CollectionUtils.isEmpty(dataSourceList)) {
            // 将对应配置的configContent转化为相应的数据源配置对象
            String configContent = dataSourceList.get(0).getConfigContent();
            DataSourceConfig dataSourceConfig = JSON.parseObject(configContent, DataSourceConfig.class);
            return dataSourceConfig;
        }
        return null;
    }

    // 调用dada-sso服务获取当前登录用户信息
    @Override
    public DataSourceConfig getCategoryDataSource(String preferState) {

        
        return null;
    }
    
    
    
    

}
