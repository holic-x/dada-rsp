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
		
		// 异步获取节点数据
		$.ajax({
			url : '${pageContext.request.contextPath}/portal/organization/listDepartment',
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
		        	// console.log('back:'+zNodes);
					var dept = responseData.data.data ;
					console.log(dept);
				} else {
					alert('服务器反馈^_^:从后台获取数据失败,请联系管理员!');
				}
			}
		});
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
			var url = "/portal/organization/getDepartmentById?deptId="+treeNode.nodeId;
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
					<h1 class="page-header">&nbsp;部门管理</h1>
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
					<!-- parentId:父级部门id -->		
					<input id="parentId" name="parentId" type="hidden" value="-1" />
					<!-- deptName:部门名称 -->
					<input id="deptName" name="deptName" type="hidden" value="" />
				    
				    <!-- 存放对应部门的详细信息 -->
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
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增父级部门
							</button>
					 		<button id="btn_add_leaf" type="button" class="btn bg-success">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增子级部门
							</button>
							<button id="btn_edit" type="button" class="btn bg-info">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑部门
							</button>
							<button id="btn_delete" type="button" class="btn bg-danger">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>移除部门
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
		
		<!-- 部门信息添加弹出框 -->
		<!--第1层：模态框的声明-->
        <div class="modal fade" id="addDeptModal">
        	<!--第2层：窗口的声明-->
        	<div class="modal-dialog">
        		<!--第3层：内容的声明-->
        		<div class="modal-content">
        			<!--内容声明的标题-->
        			<div class="modal-header">
        				<button class="close" data-dismiss="modal"><span>&times;</span></button>
        				<h4 class="modal-title">添加父级部门信息</h4>
        			</div>
        			<!--内容声明的body-->
        			<div class="modal-body">
        				<div class="container-fluid"><!--放置一行-->
        					<div class="row">
				    			<input type="text" id="addDeptName" name="addDeptName" placeholder="请填写部门名称"  />
				    		</div>
        					<div>&nbsp;</div>
        				</div>
        			</div>
        			<!--内容声明的footer-->
        			<div class="modal-footer">
        				<button id="addDept" class="btn btn-sm btn-success" >添加</button>
        				<button id="closeModal" type="reset" class="btn btn-sm btn-default">取消</button>
        			</div>
        		</div>
        	</div>
        </div>
	</div>
	<!-- /#wrapper -->

	<script type="text/javascript">
	$("#closeModal").click(function() {
		console.log('hide modal');// 控制台打印信息
		$("#addDeptModal").modal('hide');
	});
	
	// 点击选中某个节点,查看对应的部门信息(由ztree的回调函数触发)
	
	// 对按钮进行操作处理
	// 添加父级部门
	$("#btn_add_parent").click(function() {
		console.log('add');// 控制台打印信息
		// 判断当前所选节点是否为父节点（考虑基础应用,此处只设置两级菜单联动）
		// 跳转到部门添加页面(此处跳转是通过Controller实现页面跳转)
		// 多级数据如果实现数据联动：初步考虑可以通过页面中加载树状部门信息,由用户自定义选择
		// 此处为了简化操作,考虑直接通过弹出框或者是模态框实现
		// var height = jQuery(window).height()-50;
		// $("#mainframe").attr("height",height+"px");
		// $("#mainframe").attr("src","/manager/page/other?url=menu/menu_add");
		// var url = "${pageContext.request.contextPath }/manager/page/other?url=menu/menu_add";
		// window.location.href = url;
		// 显示添加部门模态框 (鉴于只需要接收一个名称属性，不引入模态框)
		$("#addDeptModal").modal('show');
	});
	
	// 添加子级部门
	$("#btn_add_leaf").click(function() {
		console.log('add');// 控制台打印信息
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
		$("#parentId").val(treeNode.nodeId);
		// 显示添加部门模态框 
		$("#addDeptModal").modal('show');
	});
	
	// 点击添加按钮,addDept,提交数据
	$("#addDept").click(function() {
		// 封装提交数据
		var submitData = {
			deptName : $("#addDeptName").val(),
			parentId : $("#parentId").val(),
			categoryId : $("#categoryId").val()
		};
		
		alert(JSON.stringify(submitData));
		
		// 请求服务器执行添加操作
		$.ajax({
			url : '${pageContext.request.contextPath }/portal/organization/addDepartment',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(submitData),
			success : function(data) {
				// alert(data.result_code);
				if (data.result_code == "1") {
					alert('操作反馈^_^:部门信息添加成功.');
					// 添加完成后重新跳转到部门管理页面(刷新页面信息)
					var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/organization/dept/dept_list";
					window.parent.href = url;
				}
			},
			error : function(data) {
				// alert(data.result_code);
				console.log(data);
				alert('服务器反馈^_^:服务器无响应，请联系管理员!');
			}
		});
	});
	
	// 编辑部门
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
		var url = "/portal/organization/updateDepartmentUI?deptId="+treeNode.nodeId;
		$("#mainframe").attr("height",height+"px");
		$("#mainframe").attr("src",url);
	});
	
	// 删除部门
	$("#btn_delete").click(function() {
		// 只允许删除单条数据(子节点直接删除,父节点提示删除将会导致其下子节点信息一并删除)
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
		alert('友情提示^_^:当前操作回重置当前所选部门下员工的归属部门信息,请对无归属部门的员工及时进行更新!');
		
		// 封装操作数据
		var selrow = {
			deptId : treeNode.nodeId 
		};
		
		// 请求服务器执行删除操作
		$.ajax({
			url : '${pageContext.request.contextPath }/portal/organization/deleteDepartment',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(selrow),
			success : function(data) {
				// alert(data.result_code);
				if (data.result_code == "1") {
					alert('操作反馈^_^:部门信息删除成功.');
					// 删除完成后重新跳转到部门管理页面(刷新页面信息)
					var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/organization/dept/dept_list";
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
	
	</script>
	
</body>

</html>
