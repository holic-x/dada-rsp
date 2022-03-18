<%@ page import="com.bstek.ureport.export.html.HtmlReport" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.bstek.ureport.export.ExportManager" %>      
<%@ page import="com.bstek.ureport.Utils" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>HTML报表测试</title>
</head>   
<body>
<!-- 通过一个HTML链接来导出目标报表模版的PDF格式报表 -->
<a href="http://localhost:8084/ureport/pdf?_u=file:2.ureport.xml">导出PDF格式报表</a>
<p></p>
	<%
		ExportManager  exportManager=(ExportManager)Utils.getApplicationContext().getBean(ExportManager.BEAN_ID);
		Map<String,Object> parameters=new HashMap<String,Object>();
		HtmlReport htmlReport = exportManager.exportHtml("file:2.ureport.xml",request.getContextPath(),parameters);
		//输出CSS样式
		out.println("<style type=\"text/css\">");
		out.println(htmlReport.getStyle());
		out.println("</style>");
		//输出报表内容
		out.println(htmlReport.getContent());
	%>
</body>
</html>