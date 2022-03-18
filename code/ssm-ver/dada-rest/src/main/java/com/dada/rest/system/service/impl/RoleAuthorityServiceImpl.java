package com.dada.rest.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.RoleAuthorityDTO;
import com.dada.manager.custom.mapper.RoleAuthorityCustomMapper;
import com.dada.mapper.AuthorityInfoMapper;
import com.dada.mapper.RoleAuthorityInfoMapper;
import com.dada.pojo.AuthorityInfo;
import com.dada.pojo.AuthorityInfoExample;
import com.dada.pojo.RoleAuthorityInfo;
import com.dada.pojo.RoleAuthorityInfoExample;
import com.dada.pojo.RoleAuthorityInfoExample.Criteria;
import com.dada.rest.system.service.RoleAuthorityService;
import com.dada.vo.MenuVO;
import com.dada.vo.RoleAuthorityVO;

@Service("roleAuthorityServiceImpl")
@Transactional
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Autowired
    private RoleAuthorityInfoMapper roleAuthorityMapper;

    @Autowired
    private RoleAuthorityCustomMapper roleAuthorityCustomMapper;

    @Autowired
    private AuthorityInfoMapper authorityInfoMapper;

    @Override
    public boolean batchAddRoleAuthority(RoleAuthorityDTO roleAuthorityDTO) {
        List<RoleAuthorityInfo> roleAuthorityList = roleAuthorityDTO.getRoleAuthorityList();
        if (CollectionUtils.isEmpty(roleAuthorityList)) {
            throw new CommonException("至少传入一条数据!");
        }
        for (RoleAuthorityInfo roleAuthority : roleAuthorityList) {
            int i = roleAuthorityMapper.insert(roleAuthority);
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean batchDeleteRoleAuthority(RoleAuthorityDTO roleAuthorityDTO) {
        List<String> roleAuthorityIds = roleAuthorityDTO.getAuthorityIds();
        if (CollectionUtils.isEmpty(roleAuthorityIds)) {
            throw new CommonException("至少传入一条数据!");
        }
        for (String roleAuthorityId : roleAuthorityIds) {
            int i = roleAuthorityMapper.deleteByPrimaryKey(roleAuthorityId);
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateRoleAuthority(RoleAuthorityDTO roleAuthorityDTO) {
        // 传入roleId、roleAuthorityList进行配置
        String roleId = roleAuthorityDTO.getRoleId();
        List<RoleAuthorityInfo> roleAuthorityList = roleAuthorityDTO.getRoleAuthorityList();
        if (StringUtils.isEmpty(roleId)) {
            throw new CommonException("传入的roleId为空");
        }
        // 根据角色id删除对应的数据
        RoleAuthorityInfoExample roleAuthorityExample = new RoleAuthorityInfoExample();
        Criteria criteria = roleAuthorityExample.createCriteria();
        if (!StringUtils.isEmpty(roleAuthorityDTO.getRoleId())) {
            criteria.andRoleIdEqualTo(roleId);
        }
        // 批量删除数据信息
        int i = roleAuthorityMapper.deleteByExample(roleAuthorityExample);
        boolean flag = false;
        if (!CollectionUtils.isEmpty(roleAuthorityList)) {
            // 允许用户角色不设置权限,如果数据不为空,批量导入数据信息(角色权限)
            flag = batchAddRoleAuthority(roleAuthorityDTO);
        }
        return (flag && (i > 0));
    }

    @Override
    public List<RoleAuthorityVO> listRoleAuthorityByRoleId(String roleId) {
        return roleAuthorityCustomMapper.selectRoleAuthorityByRoleId(roleId);
    }

    @Override
    public List<PageData> listRoleAuthorityByPage(Page page) {
        return roleAuthorityCustomMapper.selectRoleAuthorityByPage(page);
    }

    @Override
    public List<MenuVO> getMenuByRoleId(String roleId) {
        /**
         * 分析：
         * 1.获取封装好的数据信息（父节点及其对应子菜单数据）
         * 2.依次遍历数据，判断当前用户角色权限中是否包含该菜单访问权限
         * 3.如果包含则设置菜单可见，反之不可见
         */
        if (StringUtils.isEmpty(roleId)) {
            throw new CommonException("dada-rest:传入roleId为空");
        }

        // 封装筛选条件,查找所有的父级菜单
        AuthorityInfoExample authorityInfoExample = new AuthorityInfoExample();
        com.dada.pojo.AuthorityInfoExample.Criteria criteria = authorityInfoExample.createCriteria();
        // 查找父级菜单/父级权限、添加删除标识
        criteria.andIsleafEqualTo(ConstantUtils.DATA_ISLEAF_NO);
        criteria.andDelFlagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        List<AuthorityInfo> authorityInfoList = authorityInfoMapper.selectByExample(authorityInfoExample);

        // 根据角色id获取相应的角色权限信息(简化:用户权限暂定只存储子级菜单权限)
        List<RoleAuthorityVO> roleAuthorityVOList = listRoleAuthorityByRoleId(roleId);
        // 将用户权限数据转化为map存储
        Map<String, String> map = new HashMap<>();
        for (RoleAuthorityVO roleAuthorityVO : roleAuthorityVOList) {
            // 用户角色权限暂定默认只存储子节点数据(父节点存储考虑)
            map.put(roleAuthorityVO.getAuthorityId(), roleAuthorityVO.getAuthorityName());
        }

        List<MenuVO> parentMenuList = new ArrayList<>(authorityInfoList.size());
        MenuVO menuVO = new MenuVO();
        // 获取所有的父级权限/父级菜单信息,依次封装为MenuVO(外层循环)
        for (AuthorityInfo authorityInfo : authorityInfoList) {
            // 封装MenuVO基本属性
            BeanUtils.copyProperties(authorityInfo, menuVO);
            // 内层循环,依次遍历所有父级权限/父级菜单对应的子菜单信息,对应封装为子菜单列表
            authorityInfoExample = new AuthorityInfoExample();
            criteria = authorityInfoExample.createCriteria();
            // 查找父级菜单/父级权限,添加删除标识
            criteria.andParentIdEqualTo(authorityInfo.getAuthorityId());
            criteria.andDelFlagEqualTo(ConstantUtils.DEL_TAG_SAVE);
            List<AuthorityInfo> leafAuthorityList = authorityInfoMapper.selectByExample(authorityInfoExample);
            List<MenuVO> leafMenuList = new ArrayList<>();
            for (AuthorityInfo leafAuthority : leafAuthorityList) {
                MenuVO leafMenu = new MenuVO();
                leafMenu.setVisableStatus(ConstantUtils.MENU_VISABLE_STATUS_DISABLE);
                // 判断对应用户角色是否包含当前的权限记录,如果包含,设置可见菜单状态为enable,反之置为disable(包括其他基本属性)
                if (map.containsKey(leafAuthority.getAuthorityId())) {
                    BeanUtils.copyProperties(leafAuthority, leafMenu);
                    leafMenu.setVisableStatus(ConstantUtils.MENU_VISABLE_STATUS_ENABLE);
                    leafMenuList.add(leafMenu);
                }
            }
            // 封装属性并设置默认状态为可用
            menuVO.setVisableStatus(ConstantUtils.MENU_VISABLE_STATUS_ENABLE);
            menuVO.setLeafMenuList(leafMenuList);
            parentMenuList.add(menuVO);
            // 重置menuVO属性
            menuVO = new MenuVO();
        }
        return parentMenuList;
    }

    @Override
    public List<MenuVO> getAllMenu() {
        // 封装筛选条件,查找所有的父级菜单
        AuthorityInfoExample authorityInfoExample = new AuthorityInfoExample();
        com.dada.pojo.AuthorityInfoExample.Criteria criteria = authorityInfoExample.createCriteria();
        // 查找父级菜单/父级权限、添加删除标识
        criteria.andIsleafEqualTo(ConstantUtils.DATA_ISLEAF_NO);
        criteria.andDelFlagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        List<AuthorityInfo> authorityInfoList = authorityInfoMapper.selectByExample(authorityInfoExample);
        // 封装菜单信息
        List<MenuVO> parentMenuList = new ArrayList<>(authorityInfoList.size());
        MenuVO menuVO = new MenuVO();
        // 获取所有的父级权限/父级菜单信息,依次封装为MenuVO(外层循环)
        for (AuthorityInfo authorityInfo : authorityInfoList) {
            // 封装MenuVO基本属性
            BeanUtils.copyProperties(authorityInfo, menuVO);
            // 内层循环,依次遍历所有父级权限/父级菜单对应的子菜单信息,对应封装为子菜单列表
            authorityInfoExample = new AuthorityInfoExample();
            criteria = authorityInfoExample.createCriteria();
            // 查找父级菜单/父级权限,添加删除标识
            criteria.andParentIdEqualTo(authorityInfo.getAuthorityId());
            criteria.andDelFlagEqualTo(ConstantUtils.DEL_TAG_SAVE);
            List<AuthorityInfo> leafAuthorityList = authorityInfoMapper.selectByExample(authorityInfoExample);
            List<MenuVO> leafMenuList = new ArrayList<>();
            for (AuthorityInfo leafAuthority : leafAuthorityList) {
                MenuVO leafMenu = new MenuVO();
                BeanUtils.copyProperties(leafAuthority, leafMenu);
                leafMenuList.add(leafMenu);
            }
            menuVO.setLeafMenuList(leafMenuList);
            parentMenuList.add(menuVO);
            // 重置menuVO属性
            menuVO = new MenuVO();
        }
        return parentMenuList;
    }
}
