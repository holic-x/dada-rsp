package com.dada.rest.organization.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.mapper.OrganizationCategoryMapper;
import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.OrganizationCategoryExample;
import com.dada.pojo.OrganizationCategoryExample.Criteria;
import com.dada.rest.organization.service.OrganCategoryService;

@Service("organCategoryServiceImpl")
@Transactional
public class OrganCategoryServiceImpl implements OrganCategoryService {

    @Autowired
    private OrganizationCategoryMapper categoryMapper;
    
    @Override
    public boolean updateOrganCategory(OrganizationCategory category) {
        int i = categoryMapper.updateByPrimaryKeyWithBLOBs(category);
        return (i > 0);
    }

    @Override
    public OrganizationCategory getOrganCategory(String categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<OrganizationCategory> listOrganCategory(OrganizationCategory category) {
        OrganizationCategoryExample categoryExample = new OrganizationCategoryExample();
        Criteria criteria = categoryExample.createCriteria();
        if (category != null) {
            // 根据机构分类编码查找
            if (!StringUtils.isEmpty(category.getCategoryCode())) {
                criteria.andCategoryCodeEqualTo(category.getCategoryCode());
            }
            // 根据机构名称模糊查找
            if (!StringUtils.isEmpty(category.getCategoryName())) {
                criteria.andCategoryNameLike("%" + category.getCategoryName() + "%");
            }
            // 根据机构启用状态查找
            if (!StringUtils.isEmpty(category.getDisableStatus())) {
                criteria.andDisableStatusEqualTo(category.getDisableStatus());
            }
            // 设置状态
            criteria.andDelTagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        }
        return categoryMapper.selectByExample(categoryExample);
    }

}
