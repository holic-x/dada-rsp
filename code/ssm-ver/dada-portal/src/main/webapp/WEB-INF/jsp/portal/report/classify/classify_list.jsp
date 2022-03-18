<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>哒哒报表服务平台-报表平台</title>
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
		var setting = {
			view: {
				selectedMulti: false,
				showTitle : true , // 是否显示title属性
			},
			edit: {
				// 编辑节点开关
				enable: true,
				removeTitle: "删除节点信息", // 设置删除按钮提示信息
				renameTitle: "重命名节点名称",// 设置重命名按钮信息
				showRemoveBtn: false, // 显示true/隐藏false 删除按钮
				showRenameBtn: false // 显示true/隐藏false 编辑名称按钮
			},
			check: {
				enable:false // 是否在节点上显示单选/复选框
			},
			data: {
				keep: {
					parent:true,// 锁定true(针对 isParent=true的节点,即使该节点的子节点被全部删除或移走，依旧保持父节点状态)/不锁定false叶子节点属性(反之)
					leaf:true // 锁定true(针对 isParent=false的节点，都无法添加子节点)/不锁定false叶子节点属性(反之)
				},
				/*
				key: {
					
				},
				*/
				simpleData: {
					// 此处需要参考API,注意属性的设置(尤其是大小写问题),如果属性设置不正确,则树状图展示可能存在问题(例如全为显示子节点效果)
					// zTree分为简单数据格式、标准数据格式
					// zTree的默认配置是不启用简单数据格式，使用简单数据格式一定要在setting中进行简单数据格式的相关配置
					enable:true,//表示使用简单数据模式
		            // idKey:"id",//设置之后id为在简单数据模式中的父子节点关联的桥梁
		            // pIdKey:"pId",//设置之后pid为在简单数据模式中的父子节点关联的桥梁和id互相对应
		            // rootId:"null"//pid为null的表示根节点
		           	idKey:"nodeId",
		           	pIdKey:"nodePid",
		            rootPId:"-1"//pid为null的表示根节点
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onRemove: onRemove,
				onClick: showInfo // 捕获节点被点击时触发的事件回调函数
			}
		};
		
		// 获取当前登录用户所属机构、所属部门
		var categoryId = '${sessionScope.loginUser.categoryId}';
		var deptId = '${sessionScope.loginUser.deptId}';
		var submitData = {
			// "categoryId":$("#categoryId").val() 异步加载尚未加载该categoryId组件属性
			"categoryId": categoryId,
			"deptId":deptId
		}
		// alert(categoryId);
		// 异步获取节点数据
		$.ajax({
			url : '${pageContext.request.contextPath}/portal/report/manager/listReportClassify',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			async : false,
			dataType : 'json',
			data : JSON.stringify(submitData),
			success : function(responseData) {
				console.log(responseData);
				var result_code = responseData.result_code;
				if (result_code == "1") {
					// 返回状态码为1，机构分类访问成功
					// 从后台获取的节点信息
		        	zNodes = responseData.data.data;
		        	console.log('back:'+zNodes);
		        	// console.log('back:'+zNodes);
					var dept = responseData.data.data ;
					console.log(dept);
				} else {
					alert('服务器反馈^_^:从后台获取数据失败,请联系管理员!');
				}
			}
		});

		/*	
		$.ajax({
	        url : '${pageContext.request.contextPath}/portal/organization/listDepartment',
	        async : false,
	        type : 'POST',
	        dataType : 'json',
	        success : function(response) {
	        	// 从后台获取的节点信息
	        	zNodes = response.data.data;
	        	// console.log('back:'+zNodes);
	        },
	        error : function() {
	            alert("服务器反馈^_^:从后台获取数据失败,请联系管理员!");
	        }
	    });
		*/
		var log, className = "dark";
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		function beforeRemove(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
		}
		function onRemove(e, treeId, treeNode) {
			showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		}
		function beforeRename(treeId, treeNode, newName) {
			if (newName.length == 0) {
				alert("节点名称不能为空.");
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				setTimeout(function(){zTree.editName(treeNode)}, 10);
				return false;
			}
			return true;
		}
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		var newCount = 1;
		function add(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			isParent = e.data.isParent,
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (treeNode) {
				treeNode = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, isParent:isParent, name:"new node" + (newCount++)});
			} else {
				treeNode = zTree.addNodes(null, {id:(100 + newCount), pId:0, isParent:isParent, name:"new node" + (newCount++)});
			}
			if (treeNode) {
				zTree.editName(treeNode[0]);
			} else {
				alert("叶子节点被锁定，无法增加子节点");
			}
		};
		function edit() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			zTree.editName(treeNode);
		};
		function remove(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			var callbackFlag = $("#callbackTrigger").attr("checked");
			zTree.removeNode(treeNode, callbackFlag);
		};
		function clearChildren(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0 || !nodes[0].isParent) {
				alert("请先选择一个父节点");
				return;
			}
			zTree.removeChildNodes(treeNode);
		};
		
		// 定义鼠标点击事件回调方法showInfo
		function showInfo(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			var treeNode = nodes[0];
			var height = jQuery(window).height()-50;
			// var url = "/portal/organization/getDepartmentById?deptId="+treeNode.nodeId;
			// var categoryId = '${sessionScope.loginUser.categoryId}';
			var url = "/portal/report/manager/showReportClassifyById/"+treeNode.nodeId;
			$("#mainframe").attr("height",height+"px");
			$("#mainframe").attr("src",url);
		};
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#addParent").bind("click", {isParent:true}, add);
			$("#addLeaf").bind("click", {isParent:false}, add);
			$("#edit").bind("click", edit);
			$("#remove").bind("click", remove);
			$("#clearChildren").bind("click", clearChildren);
		});
	</SCRIPT>

<body>

	<div id="wrapper">

		<!-- 页面显示内容 -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">&nbsp;报表分类管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
			    	<!-- 存放ztree数据 -->
					<div class="col-sm-2"  style=" padding-left:30px;">
						<ul id="treeDemo" class="ztree"></ul>
				    </div>
				    
				    <!-- 定义默认的提交属性 -->
					<!-- categoryId:当前所属机构 -->
					<input id="categoryId" name="categoryId" type="hidden" value="${loginUser.categoryId }" />
					<!-- deptId:当前所属部门id -->
					<input id="deptId" name="deptId" type="hidden" value="${loginUser.deptId }" />	
					<!-- deptId:当前登录用户 -->
					<input id="userId" name="userId" type="hidden" value="${loginUser.userId }" />	
					<!-- parentId:父级分类id -->		
					<input id="parentId" name="parentId" type="hidden" value="-1" />
					<!-- classifyName:分类名称 -->
					<input id="classifyName" name="classifyName" type="hidden" value="" />
					
					
					<!-- 当前选中的分类id  -->		    
				    <input id="classifyId" type="hidden">
				    
				    <!-- 存放对应分类的详细信息 -->
					 <div class="col-sm-10">
					 	<div class="row">
					 		<!-- 添加操作按钮组合 -->
					 		<!--  
						 	[ <a id="addParent" href="#" title="增加父节点" onclick="return false;">增加父节点</a> ]
							[ <a id="addLeaf" href="#" title="增加叶子节点" onclick="return false;">增加叶子节点</a> ]
							[ <a id="edit" href="#" title="编辑名称" onclick="return false;">编辑名称</a> ]
							[ <a id="remove" href="#" title="删除节点" onclick="return false;">删除节点</a> ]
							[ <a id="clearChildren" href="#" title="清空子节点" onclick="return false;">清空子节点</a> ]
					 	 	-->
					 		<!-- 考虑到实际操作限制,禁用直接对树节点操作的按钮,通过页面实现节点的添加 -->
					 		<button id="btn_add_parent" type="button" class="btn bg-primary">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;新增父级分类
							</button>
					 		<button id="btn_add_leaf" type="button" class="btn bg-success">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;新增子级分类
							</button>
							<button id="btn_edit" type="button" class="btn bg-info">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;编辑分类
							</button>
							<button id="btn_delete" type="button" class="btn bg-danger">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;移除分类
							</button>
							<button id="btn_add_classify_link" type="button" class="btn bg-default">
								<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>&nbsp;引入报表
							</button>
					 	</div>
					 	&nbsp;
					 	
					 	<div class="row">
					    	<%-- 
					    	// 方式1:
					    	<iframe id="mainframe" name="mainiframe"  style="width:100%; border:0px; " ></iframe>
							<script type="text/javascript">
					        	// var height = jQuery(window).height()-50;
								// jQuery("#mainframe").attr("height",height+"px");
					        	// jQuery("#mainframe").attr("src","#");
					        </script>
					        --%>
					      	<!--  方式2:通过后台Controller实现跳转（借助ZTree中的回调方法进行页面路径赋值） -->
					       <!-- <iframe id="mainframe" name="mainframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=600px src=""></iframe> -->
				    		<iframe id="mainframe" name="mainiframe"  style="width:100%; border:0px; " ></iframe>
				    	</div>
				    </div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		<!-- 页面显示内容 end -->
	</div>
	<!-- /#wrapper -->
	
	<!--第1层：模态框的声明-->
	<div class="modal fade" id="showNoClassifyReport">
		<!--第2层：窗口的声明-->
		<div class="modal-dialog">
			<!--第3层：内容的声明-->
			<div class="modal-content">
				<!--内容声明的标题-->
				<div class="modal-header">
					<button class="close" data-dismiss="modal"><span>&times;</span></button>
					<h4 class="modal-title">查看报表信息</h4>
				</div>
				<!--内容声明的body-->
				<div class="modal-body">
				<!-- table table-bordered table-striped table-responsive -->
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<caption>未归属分类报表信息一览</caption>
							<thead>
								<tr class="success">
									<th>报表信息</th>
									<th>报表名称</th>
									<th>创建时间</th>
									<!--
									<th>报表预览</th>
									<th>公开状态</th>
									<th>隐藏状态</th>
									 -->
								</tr>
							</thead>
							<tbody id="fileData">
								<!-- 
								<c:forEach var="user" items="${list }">
								active\success\warning\danger
									<tr>
										<td>${user.userName }</td>
										<td>${user.phone }</td>
										<td>${user.email }</td>
									</tr>
								</c:forEach>
								 -->
							</tbody>
						</table>
					</div>
				</div>
				<!--内容声明的footer-->
				<div class="modal-footer">
					<button id="addReportToClassify" class="btn btn-sm btn-success" >引入报表</button>
					<button id="closeModal" type="button" class="btn btn-sm btn-default">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	// 点击选中某个节点,查看对应的部门信息(由ztree的回调函数触发)
	function addClassify(classifyName,parentId){
		// 添加报表分类信息
		// 封装提交数据
		var categoryId = '${sessionScope.loginUser.categoryId}' ;
		var deptId = '${sessionScope.loginUser.deptId}' ;
		var userId = '${sessionScope.loginUser.userId}' ;
		var submitData = {
			classifyName : classifyName ,
			parentId : parentId ,
			categoryId : categoryId, // $("#categoryId").val()
			deptId : deptId, // $("#deptId").val()
			userId : userId, // $("#userId").val()
		};
		// alert(JSON.stringify(submitData));
		// 请求服务器执行添加操作
		$.ajax({
			url : '${pageContext.request.contextPath }/portal/report/manager/addReportClassify',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(submitData),
			success : function(data) {
				// alert(data.result_code);
				if (data.result_code == "1") {
					alert('操作反馈^_^:报表分类信息添加成功.');
					// 添加完成后重新跳转到报表分类管理页面(刷新页面信息)
					var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/report/classify/classify_list";
					window.location.href = url;
				}
			},
			error : function(data) {
				// alert(data.result_code);
				console.log(data);
				alert('服务器反馈^_^:服务器无响应，请联系管理员!');
			}
		});
	}
	
	// 对按钮进行操作处理
	// 添加父级部门信息
	$("#btn_add_parent").click(function() {
		console.log('add_parent_dept');// 控制台打印信息
		// 接收用户输入的部门名称,添加部门数据
		var classifyName = window.prompt("请输入分类名称","输入要添加的分类名称");
		// alert(classifyName);
		if(classifyName != null){
			// 用户点击取消返回数据为null,点击确定返回当前文本字符串数据
			if("输入要添加的分类名称" == classifyName){
				// 用户没有指定部门名称,抛出提示
				alert("友情提示^_^:请指定分类名称!");
			}else{
				// 调用方法请求后台添加分类信息(默认父节点对应parent_id为-1)
				addClassify(classifyName,'-1');
			}
		}
	});
	
	// 添加子级分类信息
	$("#btn_add_leaf").click(function() {
		console.log('add_leaf');// 控制台打印信息
		// 判断用户是否选中了部门信息
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			alert("友情提示^_^:请先选择一个节点.");
			return;
		}
		var treeNode = nodes[0];
		console.log(treeNode);
		// 用户选择了某个节点,设置当前要添加的节点的父节点为当前用户默认选择的数据
		var nodeId = treeNode.nodeId;
		// 接收用户输入的部门名称,添加部门数据
		var classifyName = window.prompt("请输入分类名称","输入要添加的分类名称");
		// alert(classifyName);
		if(classifyName != null){
			// 用户点击取消返回数据为null,点击确定返回当前文本字符串数据
			if("输入要添加的分类名称" == classifyName){
				// 用户没有指定部门名称,抛出提示
				alert("友情提示^_^:请指定分类名称!");
			}else{
				// 调用方法请求后台添加部门信息
				addClassify(classifyName,nodeId);
			}
		}
	});
	
	// 编辑分类信息（只允许编辑分类名称）
	$("#btn_edit").click(function() {
		console.log('edit');// 控制台打印信息
		// 获取选中的节点信息,跳转到编辑页面
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			alert("友情提示^_^:请先选择一个节点.");
			return;
		}
		var treeNode = nodes[0];
		console.log(treeNode);
		// 跳转到部门编辑跳转页面(此处跳转是通过Controller实现页面跳转)
		var height = jQuery(window).height()-50;
		var url = "/portal/report/manager/updateReportClassifyUI/"+treeNode.nodeId;
		$("#mainframe").attr("height",height+"px");
		$("#mainframe").attr("src",url);
	});
	
	// 删除分类信息（如果当前分类下存在报表，对应删除相应关联关系）
	$("#btn_delete").click(function() {
		// 只允许删除单条数据(鉴于数据维护,不允许删除父节点)
		console.log('delete');// 控制台打印信息
		// 获取选中的节点信息,跳转到编辑页面
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			alert("友情提示^_^:请先选择一个节点.");
			return;
		}
		var treeNode = nodes[0];
		console.log(treeNode);
		// 判断当前选择的节点是否为父节点,如果为父节点,不允许用户操作
		if(treeNode.isParent){
			alert("友情提示^_^:您当前要进行删除的数据是一个父节点,服务器拒绝了你的请求.")
			return ;
		}
		// 提示用户对应部门的用户所属部门信息一并重置为默认
		alert('友情提示^_^:当前操作将会重置相关报表信息(包括已发布的报表),请对无归属分类的报表及时进行更新!');
		var ensure = confirm('友情提示^_^:是否确认移除该分类信息?');
		if(!ensure){
			return ;
		}
		// 封装操作数据(此处直接根据id删除)
		var classifyId = treeNode.nodeId
		/*
		var categoryId = ${sessionScope.loginUser.categoryId} ;
		var deptId = ${sessionScope.loginUser.deptId} ;
		var selrow = {
			categoryId : categoryId,
			deptId : deptId,
			classifyId : treeNode.nodeId
		};
		*/
		// 请求服务器执行删除操作
		$.ajax({
			url : '${pageContext.request.contextPath }/portal/report/manager/deleteReportClassify/'+classifyId,
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			// data : JSON.stringify(selrow),
			success : function(data) {
				// alert(data.result_code);
				if (data.result_code == "1") {
					alert('操作反馈^_^:分类信息删除成功.');
					// 删除完成后重新跳转到分类信息管理页面(刷新页面信息)
					var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/report/classify/classify_list";
					window.location.href = url;
				}
			},
			error : function(data) {
				// alert(data.result_code);
				console.log(data);
				alert('服务器反馈^_^:服务器无响应，请联系管理员!');
			}
		});
	});
	
	// 引入报表信息
	$("#btn_add_classify_link").click(function() {
		// 接受后台数据,获取尚未归属分类的报表信息列表defaultList
		// public_state/visible_state
		console.log('btn_add_classify_link');// 控制台打印信息
		// 获取选中的节点信息,跳转到编辑页面
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			alert("友情提示^_^:请先选择一个节点.");
			return;
		}
		var treeNode = nodes[0];
		console.log(treeNode);
		// 考虑是否对父节点做出显示,此处不对父节点做出限制,允许用户在父节点处引入报表信息(可理解为综合概念) 
		/*
		if(treeNode.isParent){
			alert("友情提示^_^:您当前要操作的数据是一个父节点,服务器拒绝了你的请求.")
			return ;
		}
		*/
		// 设置classifyId信息
		$("#classifyId").val(treeNode.nodeId);
		// 此处查找是当前所属机构所有未进行分类的报表
		var categoryId = '${sessionScope.loginUser.categoryId}';
		var deptId = '${sessionScope.loginUser.deptId}';
		var submitData = {
			"tableName":"ureport_file",
			"categoryId":categoryId,
			"deptId":deptId
		}
		// 获取未指定分类的报表信息
		$.ajax({
			url : '${pageContext.request.contextPath }/portal/report/manager/common/listUreportDataByNoClassify',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(submitData),
			success : function(data) {
				// alert(data.result_code);
				if (data.result_code == "1") {
					// 回显尚未归属分类的报表信息,弹出相应的模态框,拼接js代码回显数据
					var defaultList = data.data.data;
					// console.log(data);
					// console.log(defaultList);
					var htmlStr = "";
					for(var i=0;i<defaultList.length;i++){
						htmlStr += '<tr>'
							+'<td><input type="checkbox" id="fileIds" name="fileIds" value="'+defaultList[i].fileId+'"</td>'
							+'<td>'+defaultList[i].fileName+'</td>'
							+'<td>'+defaultList[i].createTime+'</td>'
							+'</tr>';
					}
					console.log(htmlStr);
					// 拼接前清空数据,再次拼接,否则重复点击会导致数据拼接重复
					// $('#box').html('');可能会导致内存泄漏
					$("#fileData").empty();
					$("#fileData").append(htmlStr);
					// 弹出模态框(show-弹出/hide-隐藏)
					$("#showNoClassifyReport").modal('show');
				}
			},
			error : function(data) {
				// alert(data.result_code);
				console.log(data);
				alert('服务器反馈^_^:服务器无响应，请联系管理员!');
			}
		});
	});
	
	// 关闭模态框
	$("#closeModal").click(function() {
		console.log('hide modal');// 控制台打印信息
		$("#showNoClassifyReport").modal('hide');
	});
	
	// 将选择报表进行归类
	$("#addReportToClassify").click(function() {
		console.log('add report to classify');// 控制台打印信息
		// 获取当前选择的报表信息列表,引入报表信息
		var selectFileIds = document.getElementsByName("fileIds");
		check_val = [];
	    for(k in selectFileIds){
	        if(selectFileIds[k].checked)
	            check_val.push(selectFileIds[k].value);
	    }
	    if(check_val.length==0){
	    	alert('友情提示^_^:请至少选择一条数据.');
	    	return ;
	    }
	    // alert(check_val);
	    var ensure = window.confirm('操作反馈^_^:确认引入选择的报表?');
		if(!ensure){
			return ;
		}
		var categoryId = '${sessionScope.loginUser.categoryId}' ;
		var deptId = '${sessionScope.loginUser.deptId}' ;
		var userId = '${sessionScope.loginUser.userId}' ;
		var submitData = {
			"operatorType":"1", // 1标识添加操作
			"categoryId":categoryId ,
			"deptId":deptId ,
			"classifyId":$("#classifyId").val() ,
			"userId":userId,
			"reportIds":check_val
		}
		console.log(submitData);
		// 调用接口更新用户信息
		$.ajax({
			url : '${pageContext.request.contextPath }/portal/report/manager/setReportClassifyLink',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(submitData),
			success : function(data) {
				// alert(data.result_code);
				if (data.result_code == "1") {
					// 添加报表关联关系
					alert('服务器反馈^_^:对应分类引入报表成功.');
					// 刷新页面信息
					// window.location.reload();
				}
			},
			error : function(data) {
				// alert(data.result_code);
				// console.log(data);
				alert('服务器反馈^_^:服务器无响应，请联系管理员.');
			}
		});
		// 操作完成隐藏模态框
		$("#showNoClassifyReport").modal('hide');
	});
	</script>
	
</body>

</html>
