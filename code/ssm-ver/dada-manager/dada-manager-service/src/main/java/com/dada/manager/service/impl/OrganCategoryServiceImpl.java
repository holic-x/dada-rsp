package com.dada.manager.service.impl;

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
import com.dada.manager.custom.mapper.OrganizationCategoryCustomMapper;
import com.dada.manager.service.OrganCategoryService;
import com.dada.mapper.OrganizationCategoryMapper;
import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.OrganizationCategoryExample;
import com.dada.pojo.OrganizationCategoryExample.Criteria;

@Service("organCategoryServiceImpl")
@Transactional
public class OrganCategoryServiceImpl implements OrganCategoryService {

    @Autowired
    private OrganizationCategoryMapper categoryMapper;
    
    @Autowired
    private OrganizationCategoryCustomMapper categoryCustomMapper;

    @Override
    public String addOrganCategory(OrganizationCategory category) {
        // 设置主键id
        String categoryId = IDUtils.genRandomUUId();
        category.setCategoryId(categoryId);
        // 设置删除标识
        category.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        // 设置创建时间、修改时间
        category.setCreateTime(new Date());
        category.setModifyTime(new Date());
        // 添加数据
        categoryMapper.insert(category);
        return categoryId;
    }

    @Override
    public boolean deleteOrganCategoryById(String categoryId) {
        int i = categoryMapper.deleteByPrimaryKey(categoryId);
        return (i > 0);
    }

    @Override
    public boolean BatchDeleteOrganCategory(List<String> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            throw new CommonException("至少选择一条数据!");
        }
        for (String categoryId : categoryIds) {
            int i = categoryMapper.deleteByPrimaryKey(categoryId);
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

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

    @Override
    public List<PageData> listOrganCategoryByPage(Page page) {
        return categoryCustomMapper.selectOrganCategoryByPage(page);
    }

    @Override
    public boolean validateCategoryCode(String categoryCode, String categoryId) {
        OrganizationCategoryExample categoryExample = new OrganizationCategoryExample();
        Criteria criteria = categoryExample.createCriteria();
        // 根据机构分类编码查找
        if (!StringUtils.isEmpty(categoryCode)) {
            criteria.andCategoryCodeEqualTo(categoryCode);
        }
        List<OrganizationCategory> categoryList = categoryMapper.selectByExample(categoryExample);
        if (CollectionUtils.isEmpty(categoryList)) {
            // 查找数据为空说明该机构分类编码不存在
            return false;
        } else {
            // 区分修改时针对同一个数据需要去重判断，否则验证始终无法通过
            if (!StringUtils.isEmpty(categoryId)) {
                // 判断id是否相同，如果相同则不视为重复的情况
                if (categoryList.get(0).getCategoryId().equals(categoryId)) {
                    return false;
                }
            }
        }
        return true;
    }

}
