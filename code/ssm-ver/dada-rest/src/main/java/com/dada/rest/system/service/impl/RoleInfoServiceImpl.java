package com.dada.rest.system.service.impl;

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
import com.dada.dto.RoleInfoDTO;
import com.dada.manager.custom.mapper.RoleInfoCustomMapper;
import com.dada.mapper.RoleInfoMapper;
import com.dada.pojo.RoleInfo;
import com.dada.pojo.RoleInfoExample;
import com.dada.pojo.RoleInfoExample.Criteria;
import com.dada.rest.system.service.RoleInfoService;

@Service("roleInfoServiceImpl")
@Transactional
public class RoleInfoServiceImpl implements RoleInfoService {
   
    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Autowired
    private RoleInfoCustomMapper roleInfoCustomMapper;

    @Override
    public String addRoleInfo(RoleInfo roleInfo) {
        // 封装相关属性
        String roleId = IDUtils.genRandomUUId();
        roleInfo.setCreateTime(new Date());
        roleInfo.setModifyTime(new Date());
        roleInfo.setDelFlag(ConstantUtils.DEL_TAG_SAVE);
        roleInfoMapper.insert(roleInfo);
        return roleId;
    }

    @Override
    public boolean deleteRoleInfoById(RoleInfoDTO roleInfoDTO) {
        if(StringUtils.isEmpty(roleInfoDTO.getRoleId())) {
            throw new CommonException("传入roleId为空");
        }
        int i = roleInfoMapper.deleteByPrimaryKey(roleInfoDTO.getRoleId());
        return (i > 0);
    }

    @Override
    public boolean BatchDeleteRoleInfo(List<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new CommonException("至少传入一条数据id");
        }
        // 循环遍历删除数据
        for (String roleId : roleIds) {
            int i = roleInfoMapper.deleteByPrimaryKey(roleId);
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateRoleInfo(RoleInfo roleInfo) {
        int i = roleInfoMapper.updateByPrimaryKeyWithBLOBs(roleInfo);
        return (i > 0);
    }

    @Override
    public RoleInfo getRoleInfo(String roleId) {
        return roleInfoMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<RoleInfo> listRoleInfo(RoleInfoDTO roleInfoDTO) {
        RoleInfoExample roleInfoExample = new RoleInfoExample();
        Criteria criteria = roleInfoExample.createCriteria();
        if(roleInfoDTO!=null) {
            if(StringUtils.isEmpty(roleInfoDTO.getCategoryId())) {
                criteria.andCategoryIdEqualTo(roleInfoDTO.getCategoryId());
            }
        }
        criteria.andDelFlagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        return roleInfoMapper.selectByExampleWithBLOBs(roleInfoExample);
    }
    
    @Override
    public List<PageData> listRoleInfoByPage(Page page) {
        return roleInfoCustomMapper.selectRoleInfoByPage(page);
    }

    

}
