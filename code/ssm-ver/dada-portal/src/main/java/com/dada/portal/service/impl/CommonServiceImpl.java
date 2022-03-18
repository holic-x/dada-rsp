package com.dada.portal.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.CookieUtils;
import com.dada.common.utils.HttpClientUtil;
import com.dada.pojo.UserInfo;
import com.dada.portal.service.CommonService;
import com.dada.portal.utils.ResultManage;
import com.dada.vo.MenuVO;

@Service("commonServiceImpl")
@Transactional
public class CommonServiceImpl implements CommonService {

    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;

    @Value("${SSO_USER_TOKEN_SERVICE}")
    private String SSO_USER_TOKEN_SERVICE;

    @Value("${COOKIE_NAME}")
    private String COOKIE_NAME;

    @Value("${REST_BASE_SYSTEM_URL}")
    private String REST_BASE_SYSTEM_URL;

    @Value("${REST_ROLE_MENU_GET}")
    private String REST_ROLE_MENU_GET;

    
    // 方式1：获取token:调用dada-rest服务获取
    @Override
    public UserInfo getUserInfoByToken(HttpServletRequest request, HttpServletResponse response) {
        // 1、从cookie中取token(可以调用自定义的service方法,此处直接获取)
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        // 2、获取token,调用dada-sso系统的服务查询用户信息
        String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
        if(StringUtils.isEmpty(json)) {
            throw new CommonException("调用dada-rest服务,返回结果为空");
        }
        // 3、把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(json, ResultManage.class);
        // @SuppressWarnings("unchecked")
        Map<String, Object> resultMap = result.getData();
        UserInfo userInfo = null;
        if (result.getResult_code() == 1) {
            // 获取对应的用户对象(loginUser)
            userInfo = JSON.toJavaObject((JSONObject) resultMap.get("loginUser"), UserInfo.class);
        }
        return userInfo;
    }
    
    /**
     *  方式2：获取token：如果只用到token则只根据本地token获取数据即可
     *  (但需要访问数据库,脱离实际框架设计)
     *  可以借助拦截器将数据进行拦截,将拦截到的对象存储到session域中
     *  前台jsp页面直接访问
     */
    

    @Override
    public List<MenuVO> selectMenuByUser(HttpServletRequest request, HttpServletResponse response) {
        // 根据userId访问用户权限表获取所有有效的父菜单信息
        UserInfo loginUser = getUserInfoByToken(request, response);
        // 根据当前用户角色id调用rest服务获取用户角色对应的权限信息
        String url = REST_BASE_SYSTEM_URL + REST_ROLE_MENU_GET + "/" + loginUser.getRoleId();
        String jsonResult = HttpClientUtil.doGet(url);
        // 判断访问是否成功,把json数据转换成java对象,并将相应的数据转化为相应的实体类
        ResultManage result = JSON.parseObject(jsonResult, ResultManage.class);
        if (result.getResult_code() != 1) {
            // 返回结果不为1,说明访问rest服务出错,抛出异常
            throw new CommonException("dada-portal:调用dada-rest服务出错-菜单信息获取失败");
        }
        Map<String, Object> resultMap = result.getData();
        // 将数据转化为数组返回
        List<MenuVO> MenuVOList = JSONArray.parseArray(resultMap.get("data").toString(), MenuVO.class);
        return MenuVOList;
    }
}
