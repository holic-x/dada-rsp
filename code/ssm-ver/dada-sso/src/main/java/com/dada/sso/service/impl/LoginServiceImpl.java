package com.dada.sso.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.CookieUtils;
import com.dada.common.utils.CryptoUtil;
import com.dada.common.utils.SecretKey;
import com.dada.mapper.UserInfoMapper;
import com.dada.pojo.UserInfo;
import com.dada.pojo.UserInfoExample;
import com.dada.pojo.UserInfoExample.Criteria;
import com.dada.sso.dto.LoginDTO;
import com.dada.sso.service.CheckService;
import com.dada.sso.service.LoginService;

/**       
 * <p>项目名称:dada-sso</p>
 * <p>包名称:com.dada.sso.service.impl.LoginServiceImpl</p>
 * <p>文件名称:LoginServiceImpl.java</p>
 * <p>功能描述:登录Service实现类</p>
 * <p>其他说明:    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月23日上午10:10:57 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>    
 */
@Service("loginServiceImpl")
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CheckService checkService;

    @Value("${JSESSIONID}")
    private String JSESSIONID;
    
    @Value("${COOKIE_NAME}")
    private String COOKIE_NAME;

    @Value("${COOKIE_EXPIRE}")
    private Integer COOKIE_EXPIRE;
    
    @Value("${JSESSION_EXPIRE}")
    private Integer JSESSION_EXPIRE;

    @Override
    public Map<String, Object> login(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        String loginAccount = loginDTO.getLoginAccount();
        String loginPassword = loginDTO.getLoginPassword();
        String categoryCode = loginDTO.getCategoryCode();
        String saveStatus = loginDTO.getSaveStatus();
        if (StringUtils.isEmpty(loginAccount)) {
            throw new CommonException("用户登录名称不能为空");
        }
        if (StringUtils.isEmpty(loginPassword)) {
            throw new CommonException("用户登录密码不能为空");
        }
        if (StringUtils.isEmpty(categoryCode)) {
            throw new CommonException("用户所属机构不能为空");
        }
        // 验证信息
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("result", ConstantUtils.LOGIN_CHECK_RESULT_SUCCESS);
        resultMap.put("message", ConstantUtils.LOGIN_CHECK_MESSAGE_SUCCESS);
        // 根据当前用戶填写的机构编码返回机构数据id
        String categoryId = checkService.checkCategoryCode(categoryCode);
        if (StringUtils.isEmpty(categoryId)) {
            resultMap.replace("result", ConstantUtils.LOGIN_CHECK_RESULT_ERROR);
            resultMap.replace("message", ConstantUtils.LOGIN_CHECK_MESSAGE_ERROR_CATEGORY);
        } else {
            // 如果机构信息存在，根据用户登录名称(用户邮箱)、密码、机构id查找相应的用户信息
            // 校验用户名密码是否正确
            UserInfoExample userInfoExample = new UserInfoExample();
            Criteria criteria = userInfoExample.createCriteria();
            // 验证用户名、密码、所属机构编码是否正确
            // 根据传入的是否包含@字符区分邮箱、用户名称两种登录方式
            if (loginAccount.contains("@")) {
                criteria.andEmailEqualTo(loginAccount);
            } else {
                criteria.andLoginAccountEqualTo(loginAccount);
            }
            // 密码为加密后的数据进行验证
            criteria.andLoginPasswordEqualTo(CryptoUtil.encode(ConstantUtils.PASSWORD_SECRET_KEY,loginPassword));
            criteria.andCategoryIdEqualTo(categoryId);
            List<UserInfo> userList = userInfoMapper.selectByExample(userInfoExample);
            if (CollectionUtils.isEmpty(userList)) {
                resultMap.replace("result", ConstantUtils.LOGIN_CHECK_RESULT_ERROR);
                resultMap.replace("message", ConstantUtils.LOGIN_CHECK_MESSAGE_ERROR_INFO);
            } else {
                // 校验用户启用状态
                UserInfo userInfo = userList.get(0);
                if(ConstantUtils.USER_STATE_FORBIDDEN.equals(userInfo.getUserState())) {
                    // 用户处于禁用状态
                    resultMap.replace("result", ConstantUtils.LOGIN_CHECK_RESULT_ERROR);
                    resultMap.replace("message", ConstantUtils.LOGIN_CHECK_MESSAGE_ERROR_FORBIDDEN);
                }else {
                    /**
                     *  1.传统实现方法:
                     *  借助session将用户信息传入session域，在服务器启动期间有效
                     *  传入参数：HttpSession session
                     *  获取查找到的用户信息
                     *  设置session域：session.setAttribute("loginuser", loginuser);
                     */
                    // --------------------------------------------------------------
                    /**
                     *  2.cookie客户端存储方法（可结合redis数据库实现）
                     *  方式1：结合redis实现
                     *  a.随机生成token数据(IDUtils随机32char):String token = IDUtils.genRandomUUId();
                     *  b.设置存储的cookie名称,对应value存储生成的token
                     *  c.查找用户时根据cookieName借助CookieUtils获取当前客户端存储的token信息
                     *  d.根据token借助jedis访问redis数据库中存储的用户信息(json字符串需转化)
                     *  对象转化为json字符串--json:JsonUtils.objectToJson(user)
                     *  
                     *  方式2：纯cookie存储
                     *  a.判断查找的用户信息是否存在,生成相应的token:此处token存储的是userId
                     *  b.存储的cookies：借助CookieUtils将token数据写入客户端
                     *  c.查找用户时根据当前存储的token(userId)查找用户信息
                     *  
                     *  问题分析：
                     *  单用户登录:
                     *  cookie存储在客户端的名称是固定名称，多个用户在同一个浏览器中登录会覆盖数据
                     */
                    String token = userList.get(0).getUserId();
                    // 设置cookie默认过期时间
                    if (ConstantUtils.LOGIN_SAVE_STATUS_SAVE.equals(saveStatus)) {
                        // 设置默认过期时间
                        CookieUtils.setCookie(request, response, COOKIE_NAME, token, COOKIE_EXPIRE);
                    } else if (ConstantUtils.LOGIN_SAVE_STATUS_UNSAVE.equals(saveStatus)) {
                        // 默认浏览器关闭即失效
                        CookieUtils.setCookie(request, response, COOKIE_NAME, token);
                    }
                    // Cookie与session结合使用,此处设置一个JESSIONID存储标识唯一SESSION的数据
                    // String sessionId = IDUtils.genRandomUUId();
                    // CookieUtils.setCookie(request, response, JSESSIONID, sessionId);
                    // 将当前的用户登录信息存储到dada-sso的session域(HttpSession session)中
                    // HttpSession session = request.getSession();
                    // session.setAttribute(sessionId, userInfo);
                    // session.setMaxInactiveInterval(JSESSION_EXPIRE);
                    /**
                     * 返回页面跳转路径设置：
                     * 方式1：考虑后台管理、前台用户使用不同的登录页面,访问相应的主页面
                     * 方式2：统一用同一个页面去处理，根据用户传入的机构编码判断要返回的主页面
                     * 考虑用户报表平台页面由后台数据动态生成，实现访问权限的管理
                     */
                    
                    // 方式1
                    // 根据登录用户的机构判断跳转的页面（后台管理、报表平台？）
                    // 报表平台统一使用一个页面，菜单则动态生成，涉及相关的权限等
                    // 用户登录则根据其相应的权限动态生成菜单信息

                    // 方式2
                    // 管理员和普通用户访问不同的页面，，如此一来针对权限判断的意义相对不大？ 对应的菜单图标也要添加

                }
            }
        }

       
        return resultMap;
    }

    @Override
    public UserInfo getUserInfoByToken(String token) {
        // 如果使用了redis缓存,则此处需要根据token查询相应的redis缓存信息

        // 如果没有使用redis缓存,则直接根据客户端存储的token查找,返回相应的用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(token);
        // 用户账号密码解密
        if(userInfo!=null) {
            userInfo.setLoginPassword(CryptoUtil.decode(ConstantUtils.PASSWORD_SECRET_KEY,userInfo.getLoginPassword()));
        }
        return userInfo;
    }

}
