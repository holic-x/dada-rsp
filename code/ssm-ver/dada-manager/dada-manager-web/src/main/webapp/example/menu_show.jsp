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
<%@include file="/public/table.jspf"%>

</head>

<!-- 引入ztree相关的css、js -->
<link href="${pageContext.request.contextPath }/resource/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/resource/ztree/js/jquery-1.4.4.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/ztree/js/jquery.ztree.core.js"></script>
<script src="${pageContext.request.contextPath }/resource/ztree/js/jquery.ztree.exhide.js"></script>


<script type="text/javascript">

	var setting = {
		data : {
			key : {
				title : "title"
			},
			simpleData : {
				enable : true
			}
        }
	};

	function setTitle(node) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = node ? [ node ] : zTree.transformToArray(zTree.getNodes());
		for ( var i = 0, l = nodes.length; i < l; i++) {
        	var n = nodes[i];
        	n.title = "[" + n.id + "] isFirstNode = " + n.isFirstNode + ", isLastNode = " + n.isLastNode;
        	zTree.updateNode(n);
		}
	}

	function count() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), hiddenCount = zTree.getNodesByParam("isHidden", true).length;
		$("#hiddenCount").text(hiddenCount);
	}

	function showNodes() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree.getNodesByParam("isHidden", true);
		zTree.showNodes(nodes);
		setTitle();
		count();
	}

	function hideNodes() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			alert("请至少选择一个节点");
			return;
		}
		zTree.hideNodes(nodes);
		setTitle();
		count();
	}

	//从后台读取数据用来保存结点的数组
	var treeNodes = [];
	// 初始化树
	$(document).ready(function() {
		//初始化zNodes数组——treeNodes
		$(function() {
	        //从后台获取json串的连接
	        var url = "http://localhost:8080/test/listTreeData";
	        //right是从session获取并保存在jsp页面body中的一个hidden标签中
	        var right = document.getElementById("right").value;
	        $.ajax({
	                 url : url,
	                 type : 'post',
	                 //定义传到后台的数据，后台就是根据它获取zTree结点信息的，它是一个json格式的串
	                 // data : {right : right},
	                 data : {},
	                 async : false,
	                 success : function(data) {
	           			// data是ajax传回的数据一个json串
	               		/*
	               		json串=
	               		{
	           			 "right":
	               			[{"id":"1","isHidden":0,"name":"公司","open":1,"pId":"0","target":"","title":"","url":""},
	                    	 {"id":"11","isHidden":0,"name":"超级管理员","open":0,"pId":"1","target":"mainF","title":"","url":""},
	                         {"id":"111","isHidden":0,"name":"显示报表","open":0,"pId":"11","target":"mainF","title":"","url":"http://localhost:8080/InspurUser/chart/index.jsp"},
	                         {"id":"112","isHidden":0,"name":"管理用户","open":0,"pId":"11","target":"mainF","title":"","url":"http://localhost:8080/InspurUser/DirectTo?method=toUserManager"}
	                        ]
	           			}
	 					*/
						// 如果传入的是字符串: var msg = eval('(' + data + ')');
	           			var msg = data.data;
	                           // 使用遍历函数封装树节点信息
	                           $.each(msg, function(i, item) {
		                           	//此处i=right
		                         	//item是json串的后半部分
		                          	$.each(item,function(ii,iit){//遍历json数据（对我们有用的——树的节点的所有信息）
		                            	treeNodes.push({"id":iit.id,"name":iit.name,"pId":iit.pId,"url":iit.url,"target":iit.target});
		                         	});
	                    		});
	                 	}
	        	});
		});

		// 初始化zTree的函数
		//但$("#treeDemo")中的treeDemo是要存放zTree的div的id
		$.fn.zTree.init($("#treeDemo"), setting, treeNodes);
		$("#hideNodesBtn").bind("click", { type : "rename" }, hideNodes);
		$("#showNodesBtn").bind("click", { type : "icon" }, showNodes);
		setTitle();
		count();
	});


</script>

<body>

	<!-- 存放ztree -->
	<div>
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	 <%--
		   这就是存放权限right的hidden标签，在穿到后面的时候用到
 	--%>
	<input type="hidden" name="right" id="right" value="${sessionScope.user.right }">
	

	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="/common/navigation.jsp"%>
		<!-- Navigation end -->

		<!-- 页面显示内容 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">数据字典管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- <div class="panel-heading">机构维护/机构用户管理</div> -->
						<div style="padding: 0px; margin: 0px;">
							<ul class="breadcrumb" style="margin: 0px;">
								<li><a href="#">平台维护</a></li>
								<li>数据字典管理</li>
							</ul>
						</div>

						<!-- 操作按钮组合  class="btn-group operation"  -->
						<div class="panel-heading btn-group operation">
							<button id="btn_add" type="button" class="btn bg-primary">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
							</button>
							<button id="btn_edit" type="button" class="btn bg-info">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>查看
							</button>
							<!-- 
							<button id="btn_edit" type="button" class="btn bg-info">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
							</button>
							<button id="btn_delete" type="button" class="btn btn-success">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
							</button>
							 -->
						</div>
						<!-- 操作按钮组合 end -->

						<!-- /.panel-heading -->
						<div class="panel-body">
							<table width="100%"
								class="table table-striped table-bordered table-hover"
								id="dataTables-example" data-show-columns="false">
								<!-- 表头 -->
								<thead>
									<tr>
										<th class="text-center">菜单编号</th>
										<th class="text-center">父级菜单</th>
										<th class="text-center">菜单编码</th>
										<th class="text-center">菜单名称</th>
										<th class="text-center">菜单描述</th>
										<th class="text-center">访问URL</th>
										<th class="text-center">父/子节点</th>
										<th class="text-center">创建时间</th>
										<th class="text-center">修改时间</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<!-- 表体数据 -->
								<tbody>
									<!-- <tr class="gradeU">
										<td>Other browsers</td>
										<td>All others</td>
										<td>-</td>
									</tr> -->
								</tbody>
							</table>
							<!-- /.table-responsive -->

						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
		<!-- 页面显示内容 end -->

	</div>
	<!-- /#wrapper -->

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<%-- 
	<script>
		/*  $(document).ready(function() {
				$('#dataTables-example').DataTable({
					 responsive : true,
				});
			});  */

		$(document)
				.ready(
						function() {
							// 生成操作按钮
							var operatorButton = '<button type="button" id="editrow" data-toggle="modal" class="btn btn-outline btn-circle btn-sm purple"><i class="fa fa-edit">编辑</i></button>  <button id="delrow" class="btn btn-outline btn-circle dark btn-sm black"><i class="fa fa-trash-o">删除</i></button>';

							var contentPath = "/"
									+ location.pathname.split("/")[1];

							$('#dataTables-example').dataTable().fnDestroy();//作用是在翻页的时候，需要将先前实用datatable生成的table摧毁，以便在翻页或者条件查找的时候，新的表格的搭建。 
							$('#dataTables-example')
									.DataTable(
											{
												// dom : 'Bit',dom : '<"wrapper"flipt>',dom : 'Bit <"top"i>rt<"bottom"flp><"clear">',
												dom : 'Bit <lf<t>ip>',
												paging : true,
												"info" : false,
												"searching" : true,
												"ordering" : true,
												"stateSave" : true,
												buttons : [ 'colvis' ],
												'language' : {
													'emptyTable' : '没有数据',
													'loadingRecords' : '加载中...',
													'processing' : '查询中...',
													'search' : '数据检索:',
													'lengthMenu' : '每页 _MENU_ 条数据',
													'zeroRecords' : '没有数据',
													'paginate' : {
														'first' : '第一页',
														'last' : '最后一页',
														'next' : '下一页',
														'previous' : '上一页'
													},
													'info' : '第 _PAGE_ 页 / 总 _PAGES_ 页',
													'infoEmpty' : '没有数据',
													'infoFiltered' : '(过滤总件数 _MAX_ 条)'
												},
												ajax : function(data, callback,
														settings) {
													//封装请求参数
													var param = {
														"pageData" : {
															// "dataCode" : "gender"
														}
													};
													// 封装查找参数
													// param.loginAccount = "hahbibu";

													//ajax请求数据
													$
															.ajax({
																url : contentPath
																		+ '/menu/listAuthorityInfoByPage',
																type : "POST",
																contentType : 'application/json; charset=UTF-8',
																// cache: false, //禁用缓存
																dataType : "json",
																data : JSON
																		.stringify(param),
																success : function(
																		result) {
																	//封装返回数据
																	var returnData = {};
																	returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
																	returnData.recordsTotal = result.recordsTotal;//返回数据全部记录
																	returnData.recordsFiltered = result.recordsFiltered;//后台不实现过滤功能，每次查询均视作全部结果
																	returnData.data = result.data;//返回的数据列表
																	//console.log(returnData);
																	//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
																	//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
																	callback(returnData);
																}
															});
												},
												// 设置属性，封装数据
												columns : [
														{
															"data" : "authorityId",
															"name" : "数据编号",
															"sDefaultContent" : "", //默认空字符串 
															"sClass" : "text-center",
															"visible" : false // 设置默认隐藏
														},
														{
															"orderable" : true,
															"data" : "parentAuthorityName",
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center"
														},
														{
															"orderable" : true,
															"data" : "authorityCode",
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center"
														},
														{
															"orderable" : true,
															"data" : "authorityName",
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center"
														},
														/* {
															"orderable" : true,
															"data" : "categoryName",
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center",
															"visible" : false // 设置默认隐藏
														}, */
														{
															"orderable" : true,
															"data" : "authorityDescr",
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center",
															"visible" : false // 设置默认隐藏
														},
														{
															"orderable" : true,
															"data" : "authorityUrl",
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center",
															"visible" : false // 设置默认隐藏
														},
														{
															"orderable" : true,
															"data" : "isleaf",
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center",
															"visible" : false // 设置默认隐藏
														},
														{
															"orderable" : true,
															//"data" : "birthday",
															"data" : function(
																	obj) {
																return getDate(obj.createTime)//通过调用函数来格式化所获取的时间戳
															},
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center"
														},
														{
															"orderable" : true,
															//"data" : "birthday",
															"data" : function(
																	obj) {
																return getDate(obj.modifyTime)//通过调用函数来格式化所获取的时间戳
															},
															"sDefaultContent" : "", //默认空字符串 
															'sClass' : "text-center"
														},
														{
															"orderable" : false,
															"data" : null,
															"className" : "center",
															"defaultContent" : '<button type="button" id="editrow" data-toggle="modal" class="btn btn-outline btn-circle btn-sm purple"><i class="fa fa-edit">编辑</i></button> &nbsp;  <button id="delrow" class="btn btn-outline btn-circle dark btn-sm black"><i class="fa fa-trash-o">删除</i></button>'
															// "defaultContent" : operatorButton
														}]
											});
						});

		// 添加信息
		$("#btn_add")
				.click(
						function() {
							console.log('add');// 控制台打印信息
							alert('add');
							// 跳转到用户添加页面
							var url = "${pageContext.request.contextPath }/manager/page/other?url=menu/menu_add";
							window.location.href = url;
						});

		//表数据删除
		$('#dataTables-example')
				.on(
						'click',
						'button#delrow',
						function(even) {
							even.preventDefault;//方法阻止元素发生默认的行为（例如，当点击提交按钮时阻止对表单的提交）。
							if (confirm("确定删除该数据信息?(删除父节点将导致对应子节点信息也一并删除)") == false) {
								return;
							} else {
								/* 得到当前对象的值 */
								var data = $("#dataTables-example").DataTable()
										.row($(this).parents('tr')).data();
								// 封装选择的数据行
								/* var selrow = {
									dataIds : [ data.dataId ]
								}; */
								var selrow = {
									authorityId :  data.authorityId 
								};

								$
										.ajax({
											url : '${pageContext.request.contextPath }/manager/menu/deleteAuthorityInfo',
											type : 'POST',
											contentType : 'application/json; charset=UTF-8',
											dataType : 'json',
											data : JSON.stringify(selrow),
											success : function(data) {
												// alert(data.result_code);
												if (data.result_code == "1") {
													alert('菜单/权限信息删除成功!');
													start = $(
															"#dataTables-example")
															.dataTable()
															.fnSettings()._iDisplayStart;//从第几行开始显示数据
													total = $(
															"#dataTables-example")
															.dataTable()
															.fnSettings()
															.fnRecordsDisplay();//
													//alert(start);
													//alert(total);
													window.location.reload();
													if ((total - start) == 1) {
														if (start > 0) {
															$(
																	"#dataTables-example")
																	.dataTable()
																	.fnPageChange(
																			'previous',
																			true);//已回到上一页中 
															/* 注意$("#sorting-advanced").dataTable().fnPageChange( ‘previous‘); 是不行的，必需进行刷新，否则页面中显示的iDisplayStart会从cookie中取得，还是删除前的iDisplayStart */
														}
													}
												}
											},
											error : function(data) {
												// alert(data.result_code);
												console.log(data);
												alert('服务器无响应，请联系管理员!');
											}
										})
							}
						});

		//表数据编辑(跳转页面)
		$('#dataTables-example')
				.on(
						'click',
						'button#editrow',
						function(even) {
							even.preventDefault;//方法阻止元素发生默认的行为（例如，当点击提交按钮时阻止对表单的提交）。
							/* 得到当前对象的值 */
							var data = $("#dataTables-example").DataTable()
									.row($(this).parents('tr')).data();
							// 封装选择的数据行
							var url = "${pageContext.request.contextPath }/manager/menu/updateAuthorityInfoUI?authorityId="
									+ data.authorityId;
							window.location.href = url;
						});
		// 时间转化
		function getDate(time) {
			if (typeof (time) == "undefined") {
				return "";
			}
			var oDate = new Date(time), oYear = oDate.getFullYear(), oMonth = oDate
					.getMonth() + 1, oDay = oDate.getDate(), oHour = oDate
					.getHours(), oMin = oDate.getMinutes(), oSen = oDate
					.getSeconds(),
			// oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
			oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);//最后拼接时间  yy-mm-dd
			return oTime;
		};

		//补0操作,当时间数据小于10的时候，给该数据前面加一个0  
		function getzf(num) {
			if (parseInt(num) < 10) {
				num = '0' + num;
			}
			return num;
		}
	</script>

--%>

</body>

</html>
