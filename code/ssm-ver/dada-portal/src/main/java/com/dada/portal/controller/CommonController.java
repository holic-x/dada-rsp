package com.dada.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dada.portal.service.CommonService;
import com.dada.portal.utils.BaseController;
import com.dada.portal.utils.ResultManage;
import com.dada.vo.MenuVO;

@Controller
@RequestMapping("/portal/common")
public class CommonController extends BaseController{
    
    @Autowired
    private CommonService commonSercice;
    
    // 封装用户菜单权限信息
    @RequestMapping(value="/listMenu",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultManage> listMenu(HttpServletRequest request, HttpServletResponse response,Model model){
        List<MenuVO> menuVOList = commonSercice.selectMenuByUser(request, response);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("dataList", menuVOList);
        /**
         * 考虑后台封装菜单信息,随后传给前台页面,需要转码处理(较为繁琐)
         */
        /*<li>
            <a href="#">
                <i class="fa fa-wrench fa-fw"></i>平台案例
                <span class="fa arrow"></span>
            </a>
            <ul class="nav nav-second-level">
                <li>
                    <a href="${pageContext.request.contextPath }/manager/page/other?url=">示例1</a>
                </li>
            </ul> 
        </li>*/
        model.addAttribute("menuList", menuVOList);
        return getJsonResult(resultMap);
        
    }

}
