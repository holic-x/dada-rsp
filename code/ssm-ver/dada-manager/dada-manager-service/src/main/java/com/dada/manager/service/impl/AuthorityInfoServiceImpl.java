package com.dada.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.IDUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.common.utils.PingYinUtil;
import com.dada.manager.custom.mapper.AuthorityInfoCustomMapper;
import com.dada.manager.service.AuthorityInfoService;
import com.dada.mapper.AuthorityInfoMapper;
import com.dada.mapper.RoleAuthorityInfoMapper;
import com.dada.pojo.AuthorityInfo;
import com.dada.pojo.AuthorityInfoExample;
import com.dada.pojo.AuthorityInfoExample.Criteria;
import com.dada.pojo.RoleAuthorityInfo;
import com.dada.pojo.RoleAuthorityInfoExample;

/**
 * <p>项目名称:dada-manager-service</p>
 * <p>包名称:com.dada.manager.service.impl.AuthorityInfoServiceImpl</p>
 * <p>文件名称:AuthorityInfoServiceImpl.java</p>
 * <p>功能描述: 权限/菜单实现类 </p>
 * <p>其他说明: 权限/菜单CRUD  </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月16日下午10:49:42 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
@Service("authorityInfoService")
@Transactional
public class AuthorityInfoServiceImpl implements AuthorityInfoService {

    @Autowired
    private AuthorityInfoMapper authorityInfoMapper;

    @Autowired
    private AuthorityInfoCustomMapper authorityInfoCustomMapper;
    
    @Autowired
    private RoleAuthorityInfoMapper roleAuthorityInfoMapper;

    @Override
    public String addAuthorityInfo(AuthorityInfo authorityInfo) {
        // 验证数据authorityInfo
        validateData(authorityInfo);
        // 默认数据
        String authorityId = IDUtils.genRandomUUId();
        authorityInfo.setAuthorityId(authorityId);
        // 根据当前用户指定菜单名称填充authorityCode(英文字符保持不变,获取中文首字母)
        authorityInfo.setAuthorityCode(PingYinUtil.getFirstSpell(authorityInfo.getAuthorityName()));
        // 设置权限/菜单删除标识
        authorityInfo.setDelFlag(ConstantUtils.DEL_TAG_SAVE);
        // 设置时间
        authorityInfo.setCreateTime(new Date());
        authorityInfo.setModifyTime(new Date());
        // 添加数据
        authorityInfoMapper.insert(authorityInfo);
        return authorityId;
    }

    // 删除菜单的时候需要考虑删除关联的菜单权限数据（在不影响基本数据的情况下）
    private boolean deleteRoleAuthorityLinkByAuthorityId(String authorityId) {
        RoleAuthorityInfoExample roleAuthorityInfoExample = new RoleAuthorityInfoExample();
        com.dada.pojo.RoleAuthorityInfoExample.Criteria criteria = roleAuthorityInfoExample.createCriteria();
        criteria.andAuthorityIdEqualTo(authorityId);
        int i = roleAuthorityInfoMapper.deleteByExample(roleAuthorityInfoExample);
        return (i>0);
    }
    
    
    @Override
    public boolean deleteAuthorityInfoById(String authorityId) {
        // 判断是否为父级菜单
        AuthorityInfo authorityInfo = authorityInfoMapper.selectByPrimaryKey(authorityId);
        if(authorityInfo!=null) {
            // 如果为父节点，依次遍历删除数据
            if(ConstantUtils.MENU_ISLEAF_NO.equals(authorityInfo.getIsleaf())) {
                List<AuthorityInfo> leafList = listLeafByParentId(authorityId);
                for(AuthorityInfo ai : leafList) {
                    int i = authorityInfoMapper.deleteByPrimaryKey(ai.getAuthorityId());
                    // 权限信息是关联在子级菜单上的(第二级菜单,因此只考虑删除第二级菜单的关联信息)
                    boolean delFlag = deleteRoleAuthorityLinkByAuthorityId(authorityId);
                    if(i<0 && delFlag) {
                        return false;
                    }
                }
            }
        }
        // 删除数据(当前数据)
        int j = authorityInfoMapper.deleteByPrimaryKey(authorityId);
        return (j > 0);
    }

    @Override
    public boolean BatchDeleteAuthorityInfo(List<String> authorityIds) {
        if (CollectionUtils.isEmpty(authorityIds)) {
            throw new CommonException("至少选择一条数据!");
        }
        for (String authorityId : authorityIds) {
            /*int i = authorityInfoMapper.deleteByPrimaryKey(authorityId);
            if (i < 0) {
                return false;
            }*/
            boolean flag = deleteAuthorityInfoById(authorityId);
            if(!flag) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateAuthorityInfo(AuthorityInfo authorityInfo) {
        int i = authorityInfoMapper.updateByPrimaryKeyWithBLOBs(authorityInfo);
        return (i > 0);
    }

    @Override
    public AuthorityInfo getAuthorityInfo(String authorityId) {
        return authorityInfoMapper.selectByPrimaryKey(authorityId);
    }

    @Override
    public List<AuthorityInfo> listAuthorityInfo(AuthorityInfo authorityInfo) {
        // 封装筛选条件
        AuthorityInfoExample authorityInfoExample = new AuthorityInfoExample();
        Criteria criteria = authorityInfoExample.createCriteria();
        if (authorityInfo != null) {
            // 根据权限/菜单编码查找
            if (!StringUtils.isEmpty(authorityInfo.getAuthorityCode())) {
                criteria.andAuthorityCodeEqualTo(authorityInfo.getAuthorityCode());
            }
            // 根据权限/菜单名称查找
            if (!StringUtils.isEmpty(authorityInfo.getAuthorityName())) {
                criteria.andAuthorityNameLike("%" + authorityInfo.getAuthorityName() + "%");
            }
            // 根据是否为叶子结点查找
            if (!StringUtils.isEmpty(authorityInfo.getIsleaf())) {
                criteria.andIsleafEqualTo(authorityInfo.getIsleaf());
            }
            // 根据父节点id查找
            if (!StringUtils.isEmpty(authorityInfo.getParentId())) {
                criteria.andParentIdEqualTo(authorityInfo.getParentId());
            }
        }
        // 添加删除标识
        criteria.andDelFlagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        return authorityInfoMapper.selectByExample(authorityInfoExample);
    }

    @Override
    public List<PageData> listAuthorityInfoByPage(Page page) {
        return authorityInfoCustomMapper.selectAuthorityInfoByPage(page);
    }

    @Override
    public boolean validateAuthorityCode(String authorityCode, String authorityId) {
        AuthorityInfoExample authorityInfoExample = new AuthorityInfoExample();
        Criteria criteria = authorityInfoExample.createCriteria();
        criteria.andAuthorityCodeEqualTo(authorityCode);
        List<AuthorityInfo> authorityInfoList = authorityInfoMapper.selectByExample(authorityInfoExample);
        if (CollectionUtils.isEmpty(authorityInfoList)) {
            // 查找数据为空说明该权限、模块编码
            return false;
        } else {
            // 区分修改时针对同一个数据需要去重判断，否则验证始终无法通过
            if (!StringUtils.isEmpty(authorityId)) {
                // 判断id是否相同，如果相同则不视为重复的情况
                if (authorityInfoList.get(0).getAuthorityId().equals(authorityId)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void validateData(AuthorityInfo authorityInfo) {
        // 验证数据
    }

    @Override
    public boolean validateAuthorityUrl(String authorityUrl, String authorityId) {
        AuthorityInfoExample authorityInfoExample = new AuthorityInfoExample();
        Criteria criteria = authorityInfoExample.createCriteria();
        criteria.andAuthorityUrlEqualTo(authorityUrl);
        List<AuthorityInfo> authorityInfoList = authorityInfoMapper.selectByExample(authorityInfoExample);
        if (CollectionUtils.isEmpty(authorityInfoList)) {
            // 查找数据为空说明该权限、模块编码
            return false;
        } else {
            // 区分修改时针对同一个数据需要去重判断，否则验证始终无法通过
            if (!StringUtils.isEmpty(authorityId)) {
                // 判断id是否相同，如果相同则不视为重复的情况
                if (authorityInfoList.get(0).getAuthorityId().equals(authorityId)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<AuthorityInfo> listLeafByParentId(String parentId) {
        // 封装筛选条件
        AuthorityInfoExample authorityInfoExample = new AuthorityInfoExample();
        Criteria criteria = authorityInfoExample.createCriteria();
        // 根据父级id查找
        if (!StringUtils.isEmpty(parentId)) {
            criteria.andParentIdEqualTo(parentId);
        }
        // 添加删除标识
        criteria.andDelFlagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        return authorityInfoMapper.selectByExample(authorityInfoExample);
    }
}
