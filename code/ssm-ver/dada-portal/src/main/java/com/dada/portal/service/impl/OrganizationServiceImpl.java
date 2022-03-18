package com.dada.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.CryptoUtil;
import com.dada.common.utils.HttpClientUtil;
import com.dada.common.utils.IDUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DepartmentDTO;
import com.dada.dto.RoleInfoDTO;
import com.dada.dto.UserInfoDTO;
import com.dada.pojo.Department;
import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.RoleInfo;
import com.dada.pojo.UserInfo;
import com.dada.portal.constant.DataCheck;
import com.dada.portal.service.OrganizationService;
import com.dada.portal.utils.ResultManage;

@Service("organizationServiceImpl")
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    // 机构相关基本路径
    @Value("${REST_BASE_ORGANIZATION_URL}")
    private String REST_BASE_ORGANIZATION_URL;

    // ---------机构信息维护相关--------------- //
    @Value("${REST_ORGAN_CATEGORY_UPDATE}")
    private String REST_ORGAN_CATEGORY_UPDATE;

    @Value("${REST_ORGAN_CATEGORY_LIST}")
    private String REST_ORGAN_CATEGORY_LIST;

    @Value("${REST_ORGAN_CATEGORY_GET}")
    private String REST_ORGAN_CATEGORY_GET;

    // ----------用户信息管理------------//
    @Value("${REST_USER_INFO_ADD}")
    private String REST_USER_INFO_ADD;

    @Value("${REST_USER_INFO_BATCH_DELETE}")
    private String REST_USER_INFO_BATCH_DELETE;

    @Value("${REST_USER_INFO_UPDATE}")
    private String REST_USER_INFO_UPDATE;

    @Value("${REST_USER_INFO_GET}")
    private String REST_USER_INFO_GET;

    @Value("${REST_USER_INFO_LIST}")
    private String REST_USER_INFO_LIST;

    @Value("${REST_USER_INFO_LIST_PAGE}")
    private String REST_USER_INFO_LIST_PAGE;

    @Value("${REST_BASE_SYSTEM_URL}")
    private String REST_BASE_SYSTEM_URL;

    @Value("${REST_ROLE_INFO_LIST}")
    private String REST_ROLE_INFO_LIST;

    // -------------机构部门管理相关-------------//
    @Value("${REST_DEPT_ADD}")
    private String REST_DEPT_ADD;

    @Value("${REST_DEPT_DELETE}")
    private String REST_DEPT_DELETE;

    @Value("${REST_DEPT_UPDATE}")
    private String REST_DEPT_UPDATE;

    @Value("${REST_DEPT_GET}")
    private String REST_DEPT_GET;

    @Value("${REST_DEPT_LIST}")
    private String REST_DEPT_LIST;

    @Value("${REST_DEPT_LIST_PAGE}")
    private String REST_DEPT_LIST_PAGE;

    // ----------------机构基本信息相关--------------------------- //
    @Override
    public boolean updateOrganCategory(OrganizationCategory category) {
        // 更新修改时间
        category.setModifyTime(new Date());
        String jsonParam = JSON.toJSONString(category);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_ORGANIZATION_URL + REST_ORGAN_CATEGORY_UPDATE,
                jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构基本信息修改失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public OrganizationCategory getOrganCategory(String categoryId) {
        // 调用rest服务获取机构信息
        String url = REST_BASE_ORGANIZATION_URL + REST_ORGAN_CATEGORY_GET + "/" + categoryId;
        String jsonResult = HttpClientUtil.doGet(url);
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构基本信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        OrganizationCategory category = JSON.toJavaObject((JSONObject) resultMap.get("data"),
                OrganizationCategory.class);
        return category;
    }

    @Override
    public boolean checkCategoryData(String dataValue, String dataField, String categoryId) {
        OrganizationCategory category = new OrganizationCategory();
        if (DataCheck.CATEGORY_CODE.getDataField().equals(dataField)) {
            category.setCategoryCode(dataValue);
        }
        // 调用rest服务获取机构分类信息
        String url = REST_BASE_ORGANIZATION_URL + REST_ORGAN_CATEGORY_LIST;
        String jsonParam = JSON.toJSONString(category);
        String jsonResult = HttpClientUtil.doPostJson(url, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构用户信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<OrganizationCategory> categoryList = JSONArray.parseArray(resultMap.get("data").toString(),
                OrganizationCategory.class);
        if (CollectionUtils.isEmpty(categoryList)) {
            // 查找数据为空，说明对象不存在，当前数据可用，返回true
            return true;
        } else {
            // 区分修改时针对同一个数据需要去重判断,否则验证始终无法通过
            if (!StringUtils.isEmpty(categoryId)) {
                // 判断id是否相同,如果相同则不视为重复的情况,返回true
                if (categoryList.get(0).getCategoryId().equals(categoryId)) {
                    return true;
                }
            }
        }
        return false;
    }

    // ----------------机构用户管理相关--------------------------- //

    @Override
    public String addUserInfo(UserInfo userInfo) {
        // 默认数据
        userInfo.setUserId(IDUtils.genRandomUUId());
        // 设置用户账号启用状态/删除标识
        // userInfo.setUserState(ConstantUtils.USER_STATE_ENABLE);
        userInfo.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        // 使用自定义的工具类实现密码加密
        userInfo.setLoginPassword(CryptoUtil.encode(ConstantUtils.PASSWORD_SECRET_KEY, userInfo.getLoginPassword()));
        // 设置创建时间修改时间
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        // 将userInfo转化为json数据
        String jsonParam = JSON.toJSONString(userInfo);
        // 调用rest服务实现roleInfo数据添加
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_ORGANIZATION_URL + REST_USER_INFO_ADD, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构用户信息添加失败");
        }
        Map<String, Object> resultMap = result.getData();
        String id = JSON.toJSONString(resultMap.get("id"));
        return id;
    }

    @Override
    public boolean BatchDeleteUserInfo(List<String> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            throw new CommonException("至少传入一条数据!");
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserIds(userIds);
        String jsonParam = JSON.toJSONString(userInfoDTO);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_ORGANIZATION_URL + REST_USER_INFO_BATCH_DELETE,
                jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-角色信息添加失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        // 更新修改时间
        userInfo.setModifyTime(new Date());
        // 使用自定义工具实现密码加密
        userInfo.setLoginPassword(CryptoUtil.encode(ConstantUtils.PASSWORD_SECRET_KEY, userInfo.getLoginPassword()));
        String jsonParam = JSON.toJSONString(userInfo);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_ORGANIZATION_URL + REST_USER_INFO_UPDATE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构用户信息修改失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        // 调用rest服务获取用户角色
        String url = REST_BASE_ORGANIZATION_URL + REST_USER_INFO_GET + "/" + userId;
        String jsonResult = HttpClientUtil.doGet(url);
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构用户信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        UserInfo userInfo = JSON.toJavaObject((JSONObject) resultMap.get("data"), UserInfo.class);
        // 密码解密
        userInfo.setLoginPassword(CryptoUtil.decode(ConstantUtils.PASSWORD_SECRET_KEY, userInfo.getLoginPassword()));
        return userInfo;
    }
    
    @Override
    public List<UserInfo> listUserInfo(UserInfoDTO userInfoDTO) {
     // 调用rest服务获取机构用户分页信息
        String url = REST_BASE_ORGANIZATION_URL + REST_USER_INFO_LIST;
        String jsonParam = JSON.toJSONString(userInfoDTO);
        String jsonResult = HttpClientUtil.doPostJson(url, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构用户信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<UserInfo> userInfoList = JSON.parseArray(resultMap.get("data").toString(), UserInfo.class);
        return userInfoList;
    }

    @Override
    public List<PageData> listUserInfoByPage(Page page) {
        // 调用rest服务获取机构用户分页信息
        String url = REST_BASE_ORGANIZATION_URL + REST_USER_INFO_LIST_PAGE;
        String jsonParam = JSON.toJSONString(page);
        String jsonResult = HttpClientUtil.doPostJson(url, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构用户信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<PageData> userInfoList = JSON.parseArray(resultMap.get("data").toString(), PageData.class);
        return userInfoList;
    }

    @Override
    public boolean checkUserData(String dataValue, String dataField, String userId) {
        // 根据用户选择的数据类型（字段）检查相应的字段
        // 用户登录名、用户注册邮箱验证
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        if (DataCheck.USER_EMAIL.getDataField().equals(dataField)) {
            userInfoDTO.setEmail(dataValue);
        } else if (DataCheck.USER_LOGIN_ACCOUNT.getDataField().equals(dataField)) {
            userInfoDTO.setLoginAccount(dataValue);
        }
        // 调用rest服务获取用户信息
        String url = REST_BASE_ORGANIZATION_URL + REST_USER_INFO_LIST;
        String jsonParam = JSON.toJSONString(userInfoDTO);
        String jsonResult = HttpClientUtil.doPostJson(url, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构用户信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        // List<UserInfo> userInfoList =
        // JSON.parseArray(resultMap.get("data").toString(), UserInfo.class);
        List<UserInfo> userInfoList = JSONArray.parseArray(resultMap.get("data").toString(), UserInfo.class);
        if (CollectionUtils.isEmpty(userInfoList)) {
            // 查找数据为空，说明对象不存在，当前数据可用，返回true
            return true;
        } else {
            // 区分修改时针对同一个数据需要去重判断,否则验证始终无法通过
            if (!StringUtils.isEmpty(userId)) {
                // 判断id是否相同,如果相同则不视为重复的情况,返回true
                if (userInfoList.get(0).getUserId().equals(userId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<RoleInfo> listRoleInfoByCategoryId(String categoryId) {
        RoleInfoDTO roleInfoDTO = new RoleInfoDTO();
        if (StringUtils.isEmpty(categoryId)) {
            throw new CommonException("传入的categoryId为空");
        }
        roleInfoDTO.setCategoryId(categoryId);
        // 调用rest服务获取机构对应的角色信息
        String url = REST_BASE_SYSTEM_URL + REST_ROLE_INFO_LIST;
        String jsonParam = JSON.toJSONString(roleInfoDTO);
        String jsonResult = HttpClientUtil.doPostJson(url, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构用户信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<RoleInfo> roleInfoList = JSONArray.parseArray(resultMap.get("data").toString(), RoleInfo.class);
        return roleInfoList;
    }
    
    // -------------- 部门信息管理  ------------------- //

    @Override
    public String addDepartment(Department dept) {
        // 封装默认属性
        String deptId = IDUtils.genRandomUUId();
        dept.setDeptId(deptId);
        dept.setCreateTime(new Date());
        dept.setModifyTime(new Date());
        dept.setDelTag(ConstantUtils.DEL_TAG_SAVE);
        // 如果传入的categoryId为空,说明数据传送异常,设置categoryId为"-1"标识错误信息
        if (StringUtils.isEmpty(dept.getCategoryId())) {
            dept.setCategoryId(ConstantUtils.DEFAULT_DATA);
        }
        // 获取客户端token数据,调用rest服务查找当前登录的用户信息(在前端页面进行控制)
        // 将roleInfo转化为json数据
        String jsonParam = JSON.toJSONString(dept);
        // 调用rest服务实现roleInfo数据添加
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_ORGANIZATION_URL + REST_DEPT_ADD, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构部门信息添加失败");
        }
        Map<String, Object> resultMap = result.getData();
        // 数据转化:String类型数据不能转化为JSONObject(以下方式抛出异常)
        // String id =
        // JSON.toJavaObject((JSONObject)resultMap.get("id"),String.class);
        String id = JSON.toJSONString(resultMap.get("id"));
        return id;
    }

    @Override
    public boolean deleteDepartmentById(DepartmentDTO departmentDTO) {
        // categoryId、deptId
        String categoryId = departmentDTO.getCategoryId();
        String deptId = departmentDTO.getDeptId();
        if(StringUtils.isEmpty(categoryId)) {
            throw new CommonException("传入categoryId不能为空");
        }
        if(StringUtils.isEmpty(deptId)) {
            throw new CommonException("传入deptId不能为空");
        }
        // 删除部门会移除当前部门用户信息
        UserInfoDTO searchParam = new UserInfoDTO(categoryId, deptId);
        List<UserInfo> findUserList = listUserInfo(searchParam);
        for(UserInfo userInfo : findUserList) {
            // 设置用户所属部门为-1,标识无所属部门
            userInfo.setDeptId(ConstantUtils.DEFAULT_DATA);
            // 修改用户信息
            updateUserInfo(userInfo);
        }
        // 删除部门信息
        String jsonParam = JSON.toJSONString(departmentDTO);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_ORGANIZATION_URL + REST_DEPT_DELETE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构部门信息删除失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public boolean updateDepartment(Department dept) {
        // 更新修改时间
        dept.setModifyTime(new Date());
        String jsonParam = JSON.toJSONString(dept);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_ORGANIZATION_URL + REST_DEPT_UPDATE, jsonParam);
        if (StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构部门信息修改失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public Department getDepartment(String deptId) {
        // 调用rest服务获取机构部门角色
        String url = REST_BASE_ORGANIZATION_URL + REST_DEPT_GET + "/" + deptId;
        String jsonResult = HttpClientUtil.doGet(url);
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构部门信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        Department dept = JSON.toJavaObject((JSONObject) resultMap.get("data"), Department.class);
        return dept;
    }

    @Override
    public List<Department> listDepartment(DepartmentDTO departmentDTO) {
        // 调用rest服务获取机构部门信息
        String url = REST_BASE_ORGANIZATION_URL + REST_DEPT_LIST ;
        String jsonParam = JSON.toJSONString(departmentDTO);
        String jsonResult = HttpClientUtil.doPostJson(url,jsonParam);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构部门信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<Department> deptList = JSON.parseArray(resultMap.get("data").toString(), Department.class);
        return deptList;
    }

    @Override
    public List<PageData> listDepartmentByPage(Page page) {
        // 调用rest服务获取用户用具角色分页信息
        String url = REST_BASE_ORGANIZATION_URL + REST_DEPT_LIST_PAGE ;
        String jsonParam = JSON.toJSONString(page);
        String jsonResult = HttpClientUtil.doPostJson(url,jsonParam);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-机构部门信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<PageData> deptList = JSON.parseArray(resultMap.get("data").toString(), PageData.class);
        return deptList;
    }
    
    @Override
    public void updateUserFromDept(DepartmentDTO deptDTO) {
        List<String> userIds = deptDTO.getUserIds();
        String deptId = deptDTO.getDeptId();
        if(CollectionUtils.isEmpty(userIds)) {
            throw new CommonException("至少传入一条用户信息");
        }
        if(StringUtils.isEmpty(deptId)) {
            // 不传入数据默认不指定部门id(即用户不指定部门信息)
            deptId = ConstantUtils.DEFAULT_DATA;
        }
        for(String userId : userIds) {
            UserInfo userInfo = getUserInfo(userId);
            userInfo.setDeptId(deptDTO.getDeptId());
            // 修改用户信息
            updateUserInfo(userInfo);
        }
    }
}
