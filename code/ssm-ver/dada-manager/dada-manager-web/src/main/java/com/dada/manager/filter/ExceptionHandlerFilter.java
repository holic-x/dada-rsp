package com.dada.manager.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.druid.support.json.JSONUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.PageData;
// 方式1
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // 自定义异常的类，用户返回给客户端相应的JSON格式的信息
            CommonException commonException ;
            if(e instanceof CommonException) {
                commonException = (CommonException)e;
            }else {
                commonException = new CommonException("未知错误");
            }
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PageData pageData = new PageData();
            pageData.put("errorCode", commonException.getErrorCode());
            pageData.put("errorMessage", commonException.getErrorMsg());
//            String userJson = convertObjectToJson(pageData);
            String json = JSONUtils.toJSONString(pageData);
            OutputStream out = response.getOutputStream();
            out.write(json.getBytes("UTF-8"));
            out.flush();
        }
    }

//    public String convertObjectToJson(Object object) throws JsonProcessingException {
//        if (object == null) {
//            return null;
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(object);
//    }
}


