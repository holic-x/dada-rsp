package com.dada.portal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.HttpClientUtil;
import com.dada.common.utils.IDUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.common.utils.PingYinUtil;
import com.dada.dto.RoleAuthorityDTO;
import com.dada.dto.RoleInfoDTO;
import com.dada.pojo.RoleAuthorityInfo;
import com.dada.pojo.RoleInfo;
import com.dada.portal.service.CommonService;
import com.dada.portal.service.SystemService;
import com.dada.portal.utils.ResultManage;
import com.dada.vo.MenuVO;
import com.dada.vo.RoleAuthorityVO;

@Service("systemServiceImpl")
@Transactional
public class SystemServiceImpl implements SystemService{
    
    @Value("${REST_BASE_SYSTEM_URL}")
    private String REST_BASE_SYSTEM_URL;

    @Value("${REST_ROLE_INFO_ADD}")
    private String REST_ROLE_INFO_ADD;

    @Value("${REST_ROLE_INFO_DELETE}")
    private String REST_ROLE_INFO_DELETE;
    
    @Value("${REST_ROLE_INFO_UPDATE}")
    private String REST_ROLE_INFO_UPDATE;
    
    @Value("${REST_ROLE_INFO_GET}")
    private String REST_ROLE_INFO_GET;
    
    @Value("${REST_ROLE_INFO_LIST_PAGE}")
    private String REST_ROLE_INFO_LIST_PAGE;
    
    @Value("${REST_ROLE_AUTHORITY_BATCH_UPDATE}")
    private String REST_ROLE_AUTHORITY_BATCH_UPDATE;
    
    @Value("${REST_ROLE_AUTHORITY_GET}")
    private String REST_ROLE_AUTHORITY_GET;

    @Value("${REST_ALL_MENU_GET}")
    private String REST_ALL_MENU_GET;
    
    @Autowired
    CommonService commonService;

    // -----------------------------------用户角色管理相关-----------------------------------------------//
    @Override
    public String addRoleInfo(RoleInfo roleInfo,HttpServletRequest request, HttpServletResponse response) {
        /**
         *  调用rest服务:添加角色信息(封装对象信息)
         *  1.将要传送的数据(对象)转化为json字符串
         *  方式1：String json =  JsonUtils.objectToJson(roleInfo);
         *  方式2：String jsonParam = JSON.toJSONString(roleInfo) ;
         *  2.通过HttpClientUtil.doGet(...)方法请求rest服务器
         *  HttpClientUtil.doPostJson(URL,json);
         */
        // 封装默认属性
        String roleId = IDUtils.genRandomUUId();
        roleInfo.setRoleId(roleId);
        // 设置角色编码（保留其他字符、中文选择拼音首字母）
        roleInfo.setRoleCode(PingYinUtil.getFirstSpell(roleInfo.getRoleName()));
        roleInfo.setCreateTime(new Date());
        roleInfo.setModifyTime(new Date());
        roleInfo.setDelFlag(ConstantUtils.DEL_TAG_SAVE);
        // 如果传入的categoryId为空,说明数据传送异常,设置categoryId为"-1"标识错误信息
        if(StringUtils.isEmpty(roleInfo.getCategoryId())) {
            roleInfo.setCategoryId(ConstantUtils.DEFAULT_DATA);
        }
        // 获取客户端token数据,调用rest服务查找当前登录的用户信息(在前端页面进行控制)
        /*
        UserInfo loginUser = commonService.getUserInfoByToken(request, response);
        if(loginUser!=null) {
            // 设置默认属性(当前用户所属机构id)
            roleInfo.setCategoryId(loginUser.getCategoryId());
        }else {
            // 当前用户不存在,机构信息设置默认机构(-1)(避免突发情况)
            roleInfo.setCategoryId(ConstantUtils.DEFAULT_DATA);
        }
        */
        
        // 将roleInfo转化为json数据
        String jsonParam = JSON.toJSONString(roleInfo) ;
        // 调用rest服务实现roleInfo数据添加
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_SYSTEM_URL + REST_ROLE_INFO_ADD,jsonParam);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-角色信息添加失败");
        }
        Map<String, Object> resultMap = result.getData();
        // 数据转化:String类型数据不能转化为JSONObject(以下方式抛出异常)
        //  String id = JSON.toJavaObject((JSONObject)resultMap.get("id"),String.class);
        String id = JSON.toJSONString(resultMap.get("id"));
        return id;
    }

    @Override
    public boolean deleteRoleInfoById(RoleInfoDTO roleInfoDTO) {
        String jsonParam = JSON.toJSONString(roleInfoDTO);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_SYSTEM_URL + REST_ROLE_INFO_DELETE,jsonParam);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-角色信息添加失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public boolean updateRoleInfo(RoleInfo roleInfo) {
        // 更新修改时间
        roleInfo.setModifyTime(new Date());
        String jsonParam = JSON.toJSONString(roleInfo);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_SYSTEM_URL + REST_ROLE_INFO_UPDATE,jsonParam);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-角色信息修改失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public RoleInfo getRoleInfo(String roleId) {
        // 调用rest服务获取用户角色
        String url = REST_BASE_SYSTEM_URL + REST_ROLE_INFO_GET + "/" + roleId;
        String jsonResult = HttpClientUtil.doGet(url);
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-角色信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        RoleInfo roleInfo = JSON.toJavaObject((JSONObject)resultMap.get("data"),RoleInfo.class);
        return roleInfo;
    }

    @Override
    public List<PageData> listRoleInfoByPage(Page page) {
        // 调用rest服务获取用户用具角色分页信息
        String url = REST_BASE_SYSTEM_URL + REST_ROLE_INFO_LIST_PAGE ;
        String jsonParam = JSON.toJSONString(page);
        String jsonResult = HttpClientUtil.doPostJson(url,jsonParam);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-角色信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        List<PageData> roleInfoList = JSON.parseArray(resultMap.get("data").toString(), PageData.class);
        return roleInfoList;
    }

    // -----------------------------------用户角色权限管理相关-----------------------------------------------//
    
    @Override
    public boolean updateRoleAuthority(RoleAuthorityDTO roleAuthorityDTO) {
        // 用户通过传入roleId、roleAuthorityIds封装用户权限信息,再进行封装
        if(StringUtils.isEmpty(roleAuthorityDTO.getRoleId())) {
            throw new CommonException("传入roleId不能为空!");
        }
        if(StringUtils.isEmpty(roleAuthorityDTO.getCategoryId())) {
            // 如果传入的categoryId为空,则设置为默认属性-1
            roleAuthorityDTO.setCategoryId(ConstantUtils.DEFAULT_DATA);
        }
        List<RoleAuthorityInfo> roleAuthorityList = new ArrayList<>(roleAuthorityDTO.getAuthorityIds().size());       
        // 封装角色权限信息
        if(!CollectionUtils.isEmpty(roleAuthorityDTO.getAuthorityIds())) {
            RoleAuthorityInfo roleAuthority ;
            for(String authorityId : roleAuthorityDTO.getAuthorityIds()) {
                roleAuthority = new RoleAuthorityInfo();
                roleAuthority.setRoleId(roleAuthorityDTO.getRoleId());
                roleAuthority.setCategoryId(roleAuthorityDTO.getCategoryId());
                roleAuthority.setRoleAuthorityId(IDUtils.genRandomUUId());
                roleAuthority.setAuthorityId(authorityId);
                // 插入角色权限关系
                roleAuthorityList.add(roleAuthority);
            }
        }
        // 封装角色权限关系
        roleAuthorityDTO.setRoleAuthorityList(roleAuthorityList);
        String jsonParam = JSON.toJSONString(roleAuthorityDTO);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_SYSTEM_URL + REST_ROLE_AUTHORITY_BATCH_UPDATE,jsonParam);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 处理返回结果
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-角色权限信息访问失败");
        }
        // 访问数据成功
        return true;
    }

    @Override
    public List<RoleAuthorityVO> listRoleAuthorityByRoleId(String roleId) {
        // 调用rest服务获取用户角色对应的权限信息
        String url = REST_BASE_SYSTEM_URL + REST_ROLE_AUTHORITY_GET + "/" + roleId;
        String jsonResult = HttpClientUtil.doGet(url);
        if(StringUtils.isEmpty(jsonResult)) {
            throw new CommonException("调用dada-rest服务,返回结果为空!");
        }
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if(result.getResult_code()!= 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-角色信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        // 将数据转化为数组返回
        List<RoleAuthorityVO> roleAuthorityVOList = JSONArray.parseArray(resultMap.get("data").toString(), RoleAuthorityVO.class);
        return roleAuthorityVOList;
    }

    @Override
    public List<MenuVO> selectAllMenu() {
        // 根据当前用户角色id调用rest服务获取用户角色对应的权限信息
        String url = REST_BASE_SYSTEM_URL + REST_ALL_MENU_GET ;
        String jsonResult = HttpClientUtil.doGet(url);
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("调用dada-rest服务出错-菜单信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        // 将数据转化为数组返回
        List<MenuVO> MenuVOList = JSONArray.parseArray(resultMap.get("data").toString(), MenuVO.class);
        return MenuVOList;
    }

}
