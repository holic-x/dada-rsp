<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>哒哒报表服务平台后台管理</title>
<!-- 引入相关的css、js文件 -->
<%@include file="/public/common.jspf"%>

</head>

<!-- 引入ztree相关的css(针对不同风格的CSS样式)、js -->
<%-- <link href="${pageContext.request.contextPath }/resource/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">  --%>
<link href="${pageContext.request.contextPath }/resource/ztree/css/metroStyle/metroStyle.css" rel="stylesheet" type="text/css">
<%--  <link href="${pageContext.request.contextPath }/resource/ztree/css/awesomeStyle/awesome.css" rel="stylesheet" type="text/css">  --%>
<!-- 避免重复引入jquery引起js冲突 -->
<%-- <script src="${pageContext.request.contextPath }/resource/ztree/js/jquery-1.4.4.min.js"></script> --%>
<script src="${pageContext.request.contextPath }/resource/ztree/js/jquery.ztree.core.js"></script>
<script src="${pageContext.request.contextPath }/resource/ztree/js/jquery.ztree.exhide.js"></script>


	<SCRIPT type="text/javascript">
		
	</SCRIPT>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="/common/navigation.jsp"%>
		<!-- Navigation end -->

		<!-- 页面显示内容 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">消息管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<!-- 导航栏 -->
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- <div class="panel-heading">机构维护/机构用户管理</div> -->
						<div style="padding: 0px; margin: 0px;">
							<ul class="breadcrumb" style="margin: 0px;">
								<li><a href="#">消息管理</a></li>
								<li>消息记录</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-12">
			    	<!-- 存放ztree数据 -->
			    	<!-- 
					<div class="col-sm-2"  style=" padding-left:30px;">
						<ul id="treeDemo" class="ztree"></ul>
				    </div>
				     -->
				    <!-- 存放对应菜单的详细信息 -->
					 <div class="col-sm-10">
					 	<div class="row">
					 		<!-- 添加操作按钮组合 -->
					 		<!-- 考虑到实际操作限制,禁用直接对树节点操作的按钮,通过页面实现节点的添加 -->
					 		<!-- 
					 		<button id="btn_add" type="button" class="btn bg-primary">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;新增菜单
							</button>
							<button id="btn_edit" type="button" class="btn bg-info">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;编辑菜单
							</button>
							<button id="btn_delete" type="button" class="btn bg-danger">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;移除菜单
							</button>
							 -->
					 	</div>
					 	&nbsp;
					 	
					 	<div class="row">
					    	<!-- <iframe id="mainframe" name="mainiframe"  style="width:100%; border:0px; " ></iframe> -->
				    		<pre class="page-404">
							更多精彩内容,敬请期待!
							          .----.
							       _.'__    `.
							   .--($)($$)---/#\
							 .' @          /###\
							 :         ,   #####
							  `-..__.-' _.-\###/
							        `;_:    `"'
							      .'"""""`.
							     /, 哟西  ,\\
							    //  404!  \\
							    `-._______.-'
							    ___`. | .'___
							   (______|______)
							 </pre>
				    	</div>
				    </div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
		<!-- 页面显示内容 end -->
	</div>
	<!-- /#wrapper -->

	<script type="text/javascript">
	
	
	</script>
	
</body>

</html>
