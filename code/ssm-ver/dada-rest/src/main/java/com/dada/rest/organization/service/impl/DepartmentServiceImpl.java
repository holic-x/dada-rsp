package com.dada.rest.organization.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DepartmentDTO;
import com.dada.dto.UserInfoDTO;
import com.dada.manager.custom.mapper.DepartmentCustomMapper;
import com.dada.mapper.DepartmentMapper;
import com.dada.pojo.Department;
import com.dada.pojo.DepartmentExample;
import com.dada.pojo.DepartmentExample.Criteria;
import com.dada.pojo.UserInfo;
import com.dada.rest.organization.service.DepartmentService;
import com.dada.rest.organization.service.UserInfoService;

@Service("departmentServiceImpl")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Autowired
    private DepartmentCustomMapper departmentCustomMapper;
    
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public String addDepartment(Department dept) {
        int i = departmentMapper.insert(dept);
        if(i>0) {
            return dept.getDeptId();
        }
        return null ;
    }

    @Override
    public boolean deleteDepartmentById(DepartmentDTO departmentDTO) {
        String deptId = departmentDTO.getDeptId();
        if(StringUtils.isEmpty(deptId)) {
            throw new CommonException("传入的deptId不能为空");
        }
        // 根据当前用户传入的deptId查找其下对应的部门信息
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setDeptId(deptId);
        List<UserInfo> userList = userInfoService.listUserInfo(userInfoDTO);
        if(!CollectionUtils.isEmpty(userList)) {
            for(UserInfo userInfo : userList) {
                // 更新当前部门所有员工的信息
                userInfo.setDeptId(ConstantUtils.DEFAULT_DATA);
                userInfoService.updateUserInfo(userInfo);
            }
        }
        int i = departmentMapper.deleteByPrimaryKey(deptId);
        return i>0;
    }

    @Override
    public boolean BatchDeleteDepartment(DepartmentDTO departmentDTO) {
        List<String> deptIds = departmentDTO.getDeptIds();
        if(CollectionUtils.isEmpty(deptIds)) {
            throw new CommonException("至少传入一条数据id");
        }
        for(String deptId : deptIds) {
            int i = departmentMapper.deleteByPrimaryKey(deptId);
            if(i<0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateDepartment(Department dept) {
        int i = departmentMapper.updateByPrimaryKeyWithBLOBs(dept);
        return i>0;
    }

    @Override
    public Department getDepartment(String deptId) {
        return departmentMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public List<Department> listDepartment(DepartmentDTO departmentDTO) {
        DepartmentExample departmentExample = new DepartmentExample();
        Criteria criteria = departmentExample.createCriteria();
        if(departmentDTO!=null) {
            if(!StringUtils.isEmpty(departmentDTO.getDeptId())) {
                criteria.andDeptIdEqualTo(departmentDTO.getDeptId());
            }
            if(!StringUtils.isEmpty(departmentDTO.getCategoryId())) {
                criteria.andCategoryIdEqualTo(departmentDTO.getCategoryId());
            }
        }
        // 默认查找处于保存状态的数据
        criteria.andDelTagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        return departmentMapper.selectByExampleWithBLOBs(departmentExample);
    }

    @Override
    public List<PageData> listDepartmentByPage(Page page) {
        return departmentCustomMapper.selectDepartmentByPage(page);
    }

}
