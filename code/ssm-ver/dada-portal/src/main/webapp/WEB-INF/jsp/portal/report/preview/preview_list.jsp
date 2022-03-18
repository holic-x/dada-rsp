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
				onRemove: onRemove
				// onClick: showInfo // 捕获节点被点击时触发的事件回调函数
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
		/*
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
		*/
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</SCRIPT>

<body>

	<div id="wrapper">

		<!-- 页面显示内容 -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">&nbsp;报表信息预览</h1>
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
				    
					<!-- 当前选中的分类id  -->		    
				    <input id="classifyId" type="hidden">
				    
				    <!-- 存放对应分类的详细信息 -->
					 <div class="col-sm-10">
					 	<div class="row">
					 		<!-- 按钮组合:显示当前分类所有报表信息 -->
							<button id="show_report_by_classify" type="button" class="btn bg-default">
								<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>&nbsp;查看报表
							</button>
					 	</div>
					 	&nbsp;
					 	
					 	<div class="row">
					      	<!--  方式2:通过后台Controller实现跳转（借助ZTree中的回调方法进行页面路径赋值） -->
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
									<th>所属分类</th>
									<th>报表名称</th>
									<th>发布状态</th>
								</tr>
							</thead>
							<tbody id="reportData">
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
					<button id="previewSelectReport" class="btn btn-sm btn-success" >预览报表</button>
					<button id="closeModal" type="button" class="btn btn-sm btn-default">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	// 对按钮进行操作处理(展示对应分类下的报表信息)
	$("#show_report_by_classify").click(function() {
		// 弹出相应模态框,回显对应可见的报表数据
		console.log('show report by classify');// 控制台打印信息
		// 获取选中的节点信息,跳转到编辑页面
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			alert("友情提示^_^:请先选择一个节点.");
			return;
		}
		var treeNode = nodes[0];
		console.log(treeNode);
		// 请求封装模态框数据,随后显示模态框信息
		// 设置classifyId信息
		$("#classifyId").val(treeNode.nodeId);
		// 此处查找是当前所属分类的报表信息（hideStatus为0）
		var categoryId = '${sessionScope.loginUser.categoryId}';
		var deptId = '${sessionScope.loginUser.deptId}';
		var submitData = {
			"pageData":{
				"categoryId":categoryId,
				"deptId":deptId,
				"classifyId":$("#classifyId").val(),
				"hideStatus":"0"
			}
		}
		// 获取指定分类下的报表数据
		$.ajax({
			url : '${pageContext.request.contextPath }/portal/report/manager/listLinkList',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(submitData),
			success : function(data) {
				// alert(data.result_code);
				if (data.result_code == "1") {
					// 获取当前分类报表信息
					var defaultList = data.data.data;
					// console.log(data);
					// console.log(defaultList);
					var htmlStr = "";
					for(var i=0;i<defaultList.length;i++){
						htmlStr += '<tr>'
							// +'<td><input type="radio" id="linkIds" name="linkIds" value="'+defaultList[i].linkId+'"</td>'
							+'<td><input type="radio" id="reportNames" name="reportNames" value="'+defaultList[i].reportName+'"</td>'
							+'<td>'+defaultList[i].classifyName+'</td>'
							+'<td>'+defaultList[i].reportName+'</td>'
							+'<td>'+defaultList[i].hideStatus+'</td>'
							+'</tr>';
					}
					console.log(htmlStr);
					// 拼接前清空数据,再次拼接,否则重复点击会导致数据拼接重复
					// $('#box').html('');可能会导致内存泄漏
					$("#reportData").empty();
					$("#reportData").append(htmlStr);
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
	
	// 用户选中报表,执行预览操作
	$("#previewSelectReport").click(function() {
		console.log('preview select report');// 控制台打印信息
		/*
			$("input[name='killOrder']:checked").val();
    		$('input:radio:checked').val()；
    		$("input[type='radio']:checked").val();
    		$(":radio[checked]").each(function(radio){alert($(this).val());
		*/
		// 获取单选框选中数据
		// var selectLinkId = $("input[name='linkIds']:checked").val();
		var selectReportName = $("input[name='reportNames']:checked").val();
		if(selectReportName==''){
			return ;
		}
		// 隐藏模态框
		$("#showNoClassifyReport").modal('hide');
		// 指定url,显示数据到指定的页面
		var height = jQuery(window).height()-50;
		var url = "http://localhost:8084/ureport/preview?_u=dada-mysql-custom:"+selectReportName;
		$("#mainframe").attr("height",height+"px");
		$("#mainframe").attr("src",url);
	});
	
	
	
	</script>
	
</body>

</html>
