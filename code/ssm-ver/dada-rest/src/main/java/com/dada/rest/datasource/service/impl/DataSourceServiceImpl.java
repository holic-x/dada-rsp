package com.dada.rest.datasource.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.IDUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DataSourceDTO;
import com.dada.manager.custom.mapper.DataSourceCustomMapper;
import com.dada.mapper.DataSourceInfoMapper;
import com.dada.pojo.DataSourceInfo;
import com.dada.pojo.DataSourceInfoExample;
import com.dada.pojo.DataSourceInfoExample.Criteria;
import com.dada.rest.datasource.service.DataSourceService;

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
public class DataSourceServiceImpl implements DataSourceService{

    @Autowired
    private DataSourceInfoMapper dataSourceInfoMapper;
    
    @Autowired
    private DataSourceCustomMapper dataSourceCustomMapper;
    
    @Override
    public String addDataSource(DataSourceInfo dataSourceInfo) {
        int i = dataSourceInfoMapper.insert(dataSourceInfo);
        if(i>0) {
            return dataSourceInfo.getConfigId();
        }
        return null;
    }

    @Override
    public boolean deleteDataSourceById(DataSourceDTO dataSourceDTO) {
        String configId = dataSourceDTO.getCategoryId();
        if(StringUtils.isEmpty(configId)) {
            throw new CommonException("传入的id不能为空");
        }
        int i = dataSourceInfoMapper.deleteByPrimaryKey(configId);
        return i>0;
    }

    @Override
    public boolean BatchDeleteDataSource(DataSourceDTO dataSourceDTO) {
        List<String> configIds = dataSourceDTO.getConfigIds();
        if(CollectionUtils.isEmpty(configIds)) {
            throw new CommonException("至少传入一条数据id");
        }
        for(String configId : configIds) {
            int i = dataSourceInfoMapper.deleteByPrimaryKey(configId);
            if(i<0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateDataSource(DataSourceInfo dataSourceInfo) {
        int i = dataSourceInfoMapper.updateByPrimaryKeyWithBLOBs(dataSourceInfo);
        return i>0;
    }

    @Override
    public DataSourceInfo getDataSource(String configId) {
        return dataSourceInfoMapper.selectByPrimaryKey(configId);
    }

    @Override
    public List<DataSourceInfo> listDataSource(DataSourceDTO dataSourceDTO) {
        DataSourceInfoExample dataSourceInfoExample = new DataSourceInfoExample();
        Criteria criteria = dataSourceInfoExample.createCriteria();
        if(dataSourceDTO!=null) {
            if(!StringUtils.isEmpty(dataSourceDTO.getConfigId())) {
                criteria.andConfigIdEqualTo(dataSourceDTO.getConfigId());
            }
            if(!StringUtils.isEmpty(dataSourceDTO.getCategoryId())) {
                criteria.andCategoryIdEqualTo(dataSourceDTO.getCategoryId());
            }
            if(!StringUtils.isEmpty(dataSourceDTO.getPreferState())) {
                criteria.andPreferStateEqualTo(dataSourceDTO.getPreferState());
            }
        }
        // 默认查找未被删除的数据
        criteria.andDelTagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        // 查找的时候将text文本属性的数据一并筛选出来
        return dataSourceInfoMapper.selectByExampleWithBLOBs(dataSourceInfoExample);
    }

    @Override
    public List<PageData> listDataSourceByPage(Page page) {
        return dataSourceCustomMapper.selectDataSourceByPage(page);
    }

    @Override
    public boolean setPreferState(DataSourceDTO dataSourceDTO) {
        if(StringUtils.isEmpty(dataSourceDTO.getConfigId())) {
            throw new CommonException("传入的configId不能为空");
        }
        if(StringUtils.isEmpty(dataSourceDTO.getPreferState())) {
            throw new CommonException("至少选择一种状态(首选、备选)");
        }
        if(StringUtils.isEmpty(dataSourceDTO.getCategoryId())) {
            throw new CommonException("必须指定机构id");
        }
        /**
         *  根据当前传入的preferState状态,判断当前状态是否被占用
         *  一个机构中只能存在一个首选数据源、一个备选数据源
         *  在不考虑数据库中脏数据的情况下,此处操作逻辑只需要判断该机构对应的
         *  数据源列表中是否存在该preferState状态的数据源配置,如果存在,则只需要
         *  重置该数据源状态为“普通数据源”,随后再更新用户指定的configId数据源状态即可
         */
        DataSourceDTO searchParam = new DataSourceDTO();
        searchParam.setCategoryId(dataSourceDTO.getCategoryId());
        searchParam.setPreferState(dataSourceDTO.getPreferState());
        List<DataSourceInfo> dataSourceList = listDataSource(searchParam);
        if(!CollectionUtils.isEmpty(dataSourceList)) {
            // 当前preferState状态被占用
            DataSourceInfo dataSource = dataSourceList.get(0);
            dataSource.setPreferState(ConstantUtils.DEFAULT_DATA);
            updateDataSource(dataSource);
        }
        // 获取用户指定configId的数据源信息,更新状态
        DataSourceInfo updateDataSource = getDataSource(dataSourceDTO.getConfigId());
        updateDataSource.setPreferState(dataSourceDTO.getPreferState());
        return updateDataSource(updateDataSource);
    }
}
