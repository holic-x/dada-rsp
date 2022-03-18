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
<title>HTML报表测试-职员基本信息</title>
</head>
<body>

<!-- 引入相关的css、js文件 -->
<%@include file="/public/index.jspf"%>

	<div id="wrapper">
		<!-- 页面显示内容 -->
		<!-- 使用portal前端导航样式：替换样式 -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">&nbsp;平台案例</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div style="padding: 0px; margin: 0px;">
						<ul class="breadcrumb" style="margin: 0px;">
							<li><a href="#">平台案例</a></li>
							<li>employee-员工简单交叉统计报表(部门、职员学历)</li>
						</ul>
					</div>

					<!-- 报表操作按钮组合  class="btn-group operation"  -->
					<div class="panel-heading btn-group operation">
						<button id="btn_add" type="button" class="btn bg-primary">
							<span class="glyphicon glyphicon-save" aria-hidden="true"></span>基本操作
						</button>
						<!-- 考虑实际路径问题,其他工程调用服务时此处需要明确指定report路径 -->
						<a title="WORD导出" href="<%=request.getContextPath() %>/ureport/word?_u=file:employee_cross_base.ureport.xml">
							<img src="${pageContext.request.contextPath }/icon/file_tools/doc.png" />
						</a>
						<a title="EXCEL导出" href="<%=request.getContextPath() %>/ureport/excel?_u=file:employee_cross_base.ureport.xml">
							<img src="${pageContext.request.contextPath }/icon/file_tools/excel.png" />
						</a>
						<a title="分页EXCEL导出" href="<%=request.getContextPath() %>/ureport/excel/paging?_u=file:employee_cross_base.ureport.xml">
							<img src="${pageContext.request.contextPath }/icon/file_tools/excel-page.png" />
						</a>
						<a title="分页分Sheet的EXCEL导出" href="<%=request.getContextPath() %>/ureport/excel/sheet?_u=file:employee_cross_base.ureport.xml">
							<img src="${pageContext.request.contextPath }/icon/file_tools/excel-sheet.png" />
						</a>
						<a title="PDF导出" href="<%=request.getContextPath() %>/ureport/pdf?_u=file:employee_cross_base.ureport.xml">
							<img src="${pageContext.request.contextPath }/icon/file_tools/pdf.png" />
						</a>
						<a title="PDF在线预览" href="<%=request.getContextPath() %>/ureport/pdf/show?_u=file:employee_cross_base.ureport.xml">
							<img src="${pageContext.request.contextPath }/icon/file_tools/pdf-preview.png" />
						</a>
					</div>
					<!-- 操作按钮组合 end -->
					<!-- /.panel-heading -->
					
					<!-- 报表数据面板 -->
					<div class="panel-body">
						<!-- 通过一个HTML链接来导出目标报表模版的PDF格式报表 -->
						<%-- <a href="<%=request.getContextPath() %>/ureport/pdf?_u=file:employee_cross_base.ureport.xml">导出PDF格式报表</a> --%>
						<p></p>
						<%
							ExportManager  exportManager=(ExportManager)Utils.getApplicationContext().getBean(ExportManager.BEAN_ID);
							Map<String,Object> parameters=new HashMap<String,Object>();
							HtmlReport htmlReport = exportManager.exportHtml("file:employee_cross_base.ureport.xml",request.getContextPath(),parameters);
							//输出CSS样式
							out.println("<style type=\"text/css\">");
							out.println(htmlReport.getStyle());
							out.println("</style>");
							//输出报表内容
							out.println(htmlReport.getContent());
						%>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
</body>
</html>


