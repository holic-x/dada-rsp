package com.dada.sso.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dada.common.exception.CommonException;
import com.dada.manager.custom.mapper.DataDictionaryCustomMapper;
import com.dada.mapper.OrganizationCategoryMapper;
import com.dada.mapper.UserInfoMapper;
import com.dada.pojo.DataDictionary;
import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.OrganizationCategoryExample;
import com.dada.pojo.OrganizationCategoryExample.Criteria;
import com.dada.pojo.UserInfo;
import com.dada.pojo.UserInfoExample;
import com.dada.sso.constant.DataCheck;
import com.dada.sso.dto.DataDictionaryDTO;
import com.dada.sso.service.CheckService;

@Service("checkServiceImpl")
@Transactional
public class CheckServiceImpl implements CheckService {

    @Autowired
    private DataDictionaryCustomMapper dataDictionaryCustomMapper;

    @Autowired
    private OrganizationCategoryMapper categoryMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public String checkCategoryCode(String categoryCode) {
        OrganizationCategoryExample categoryExample = new OrganizationCategoryExample();
        Criteria criteria = categoryExample.createCriteria();
        // 根据机构分类编码查找
        if (!StringUtils.isEmpty(categoryCode)) {
            criteria.andCategoryCodeEqualTo(categoryCode);
        }
        List<OrganizationCategory> categoryList = categoryMapper.selectByExample(categoryExample);
        if (CollectionUtils.isEmpty(categoryList)) {
            // 查找数据为空说明该机构分类编码不存在
            return null;
        }
        // 机构编码存在，说明对应机构信息存在，返回相应的机构id信息
        return categoryList.get(0).getCategoryId();
    }

    @Override
    public boolean checkUserData(String dataValue, String dataField) {
        // 根据用户选择的数据类型（字段）检查相应的字段
        // 用户登录名、用户注册邮箱验证
        UserInfoExample userInfoExample = new UserInfoExample();
        com.dada.pojo.UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        if (DataCheck.USER_EMAIL.getDataField().equals(dataField)) {
            criteria.andEmailEqualTo(dataValue);
        } else if (DataCheck.USER_LOGIN_ACCOUNT.getDataField().equals(dataField)) {
            criteria.andLoginAccountEqualTo(dataValue);
        }
        List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);
        if (CollectionUtils.isEmpty(userInfoList)) {
            // 查找数据为空，说明对象不存在，当前数据可用，返回true
            return true;
        }
        return false;
    }

    @Override
    public boolean checkOrganCategoryData(String dataValue, String dataField) {
        // 机构编码验证
        OrganizationCategoryExample categoryExample = new OrganizationCategoryExample();
        Criteria criteria = categoryExample.createCriteria();
        if (DataCheck.CATEGORY_CODE.getDataField().equals(dataField)) {
            criteria.andCategoryCodeEqualTo(dataValue);
        }
        List<OrganizationCategory> categoryList = categoryMapper.selectByExample(categoryExample);
        if (CollectionUtils.isEmpty(categoryList)) {
            // 查找数据为空说明该机构分类编码不存在,当前数据可用，返回true
            return true;
        }
        return false;
    }

    @Override
    public List<DataDictionary> listLeafByDataCode(DataDictionaryDTO dataDTO) {
        if (StringUtils.isEmpty(dataDTO.getDataCode())) {
            throw new CommonException("传入的数据编码不能为空!");
        }
        return dataDictionaryCustomMapper.listLeafByDataCode(dataDTO.getDataCode());
    }
}
