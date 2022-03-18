//package com.dada.common.exception;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.NestedRuntimeException;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
//
//import utry.core.log.UtryLogger;
//import utry.core.log.UtryLoggerFactory;
//import utry.core.util.ExceptionResolverUtils;
//
///**
// * 系统名称: ccweb5.0.1.0
// * 模块名称:
// * 类 名 称:
// * 软件版权: 远传股份有限公司
// * 功能说明：
// * 系统版本： 5.0.1.0
// * 开发人员:  Dingj
// * 开发时间: 2018/3/13  13:14
// * 审核人员:
// * 相关文档:
// * 修改记录: 修改日期 修改人员 修改说明
// */
//public class OBSExceptionResolver implements HandlerExceptionResolver {
//    private static final UtryLogger logger = UtryLoggerFactory.getLogger(OBSExceptionResolver.class);
//
//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        String errorLogId = ExceptionResolverUtils.logException(ex, request);
//        logger.error("处理普通请求异常时响应到客户端异常", errorLogId, ex);
//        String errorMsg = ex.getMessage();
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("result_code", 0);
//        MappingJackson2JsonView view = new MappingJackson2JsonView();
//        mv.setView(view);
//        if (ex instanceof CommonException) {
//            CommonException exc = (CommonException)ex;
//            String code = exc.getErrorCode();
//            if(StringUtils.isNotEmpty(code) || "NOT_LOGIN".equals(code)){
//                mv.addObject("result_code", "NOT_LOGIN");//未登录
//            }
//        } else if (ex instanceof ClassNotFoundException) {
//            errorMsg = "未找到需要的类";
//        } else if (ex instanceof ArrayIndexOutOfBoundsException) {
//            errorMsg = "数组下标越界异常";
//        } else if (ex instanceof ClassCastException) {
//            errorMsg = "类型强制转换异常";
//        } else if (ex instanceof NullPointerException) {
//            errorMsg = "空指针异常";
//        } else if (ex instanceof NumberFormatException) {
//            errorMsg = "字符串格式转换异常";
//        } else if (ex instanceof NestedRuntimeException) {
//            errorMsg = "数据库执行异常";
//        }else {
//            errorMsg = "请求异常,请联系管理员";
//        }
//        mv.addObject("result_message", errorMsg);
//        return mv;
//    }
//}
