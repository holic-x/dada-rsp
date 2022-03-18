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

<title>哒哒报表服务平台</title>
<!-- 引入相关的css、js文件 -->
<%@include file="/public/dataTable.jspf"%>

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<!-- 后台导航模块引入,替换前台框架后不需要引入,只显示局部页面 -->
		<%-- <%@include file="/common/admin_navigation.jsp"%> --%>
		<!-- Navigation end -->

		<!-- 页面显示内容 -->
		<!-- 取消后台导航样式：替换样式 -->
		<!-- <div id="page-wrapper">   -->
		<div class="admin-content">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">&nbsp;数据源管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div style="padding: 0px; margin: 0px;">
							<ul class="breadcrumb" style="margin: 0px;">
								<li><a href="#">数据源管理</a></li>
								<li>数据源信息</li>
							</ul>
						</div>
						
						<!-- 添加隐藏属性：存储当前登录用户所属机构的categoryId -->
						<input type="hidden" id="categoryId" value="${loginUser.categoryId }" />

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
										<th class="text-center">配置编号</th>
										<th class="text-center">配置名称</th>
										<th class="text-center">最近修改人</th>
										<th class="text-center">所属机构</th>
										<th class="text-center">配置内容</th>
										<th class="text-center">创建时间</th>
										<th class="text-center">修改时间</th>
										<th class="text-center">配置属性</th>
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

	<!-- 添加按钮触发模态框：显示数据源配置信息 -->
		<!--第1层：模态框的声明-->
		<div class="modal fade" id="showDataSourceConfig">
			<!--第2层：窗口的声明-->
			<div class="modal-dialog">
				<!--第3层：内容的声明-->
				<div class="modal-content">
					<!--内容声明的标题-->
					<div class="modal-header">
						<button class="close" data-dismiss="modal"><span>&times;</span></button>
						<h4 class="modal-title">查看数据源配置信息</h4>
					</div>
					<!--内容声明的body-->
					<div class="modal-body">
					   	<div class="row">
							<label class="col-sm-3 control-label">数据库连接类型</label>
							<div class="col-sm-9">
								<input type="text" id="configType" name="configType" class="form-control input-sm" />
							</div>
							 <!-- 
								<div class="col-sm-9">
									<select id="configType" name="configType">
										<option value="MySql">MySql</option>												
										<option value="Oracle">Oracle</option>												
										<option value="SQL Server">SQL Server</option>												
										<option value="More">More</option>												
									</select>
								</div>
							-->
					      	<div>&nbsp;</div>
						</div>
						
						<div class="row">
							<label class="col-sm-3 control-label">数据库登录名称</label>
							<div class="col-sm-9">
								<input type="text" id="username" name="username" class="form-control input-sm" />
							</div>
					      	<div>&nbsp;</div>
						</div>
						
						<div class="row">
							<label class="col-sm-3 control-label">数据库登录密码</label>
							<div class="col-sm-9">
								<input type="text" id="password" name="password" class="form-control input-sm" />
							</div>
					      	<div>&nbsp;</div>
						</div>
						
						<div class="row">
							<label class="col-sm-3 control-label">数据库连接驱动</label>
							<div class="col-sm-9">
								<input type="text" id="driver" name="driver" class="form-control input-sm" />
							</div>
					      	<div>&nbsp;</div>
						</div>
						
						<div class="row">
							<label class="col-sm-3 control-label">数据库连接url</label>
							<div class="col-sm-9">
								<input type="text" id="url" name="url" class="form-control input-sm" />
							</div>
					      	<div>&nbsp;</div>
						</div>
						<!-- 
						<div class="container-fluid">数据库连接url
					   		<div class="row">
								<input type="text" id="url" name="url" />
							</div>
					      	<div>&nbsp;</div>
						</div>
						 -->
					</div>
					<!--内容声明的footer-->
					<div class="modal-footer">
						<input id="testConnection" type="button" class="btn btn-sm btn-success" value="测试连接" >
						<button id="closeModal" type="button" class="btn btn-sm btn-default">关闭</button>
					</div>
				</div>
			</div>
		</div>


	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			// 生成操作按钮
			// var operatorButton = '<button type="button" id="editrow" data-toggle="modal" class="btn btn-outline btn-circle btn-sm purple"><i class="fa fa-edit">编辑</i></button>  <button id="delrow" class="btn btn-outline btn-circle dark btn-sm black"><i class="fa fa-trash-o">删除</i></button>';
			var contentPath = "/" + location.pathname.split("/")[1];
			$('#dataTables-example').dataTable().fnDestroy();//作用是在翻页的时候，需要将先前实用datatable生成的table摧毁，以便在翻页或者条件查找的时候，新的表格的搭建。 
			$('#dataTables-example').DataTable(
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
			ajax : function(data, callback,settings) {
				//封装请求参数
				var param = {
					"pageData" : {
						"categoryId" : $("#categoryId").val()
					}
				};

				//ajax请求数据
				$.ajax({
					url : contentPath + '/dataSource/listDataSourceByPage',
					type : "POST",
					contentType : 'application/json; charset=UTF-8',
					// cache: false, //禁用缓存
					dataType : "json",
					data : JSON.stringify(param),
					success : function(result) {
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
					"data" : "configId",
					"name" : "配置编号",
					"sDefaultContent" : "", //默认空字符串 
					"sClass" : "text-center",
					"visible" : false // 设置默认隐藏
				},
				{
					"orderable" : true,
					"data" : "configName",
					"sDefaultContent" : "", //默认空字符串 
					'sClass' : "text-center"
				},
				{
					"orderable" : true,
					"data" : "userName",
					"sDefaultContent" : "", //默认空字符串 
					'sClass' : "text-center"
				},
				{
					"orderable" : true,
					"data" : "categoryName",
					"sDefaultContent" : "", //默认空字符串 
					'sClass' : "text-center",
					"visible" : false // 设置默认隐藏
				},
				{
					"orderable" : false,
					// "data" : "configContent",// 配置内容(点击弹出配置框)
					"data" : null,
					"sDefaultContent" : '<button type="button" id="showConfig" data-toggle="modal" class="btn btn-outline btn-circle btn-sm purple"><i class="fa fa-link">查看配置</i>', //默认按钮
					'sClass' : "text-center",
					// "visible" : false // 设置默认隐藏
				},
				{
					"orderable" : true,
					"data" : function(obj) {
						return getDate(obj.createTime)//通过调用函数来格式化所获取的时间戳
					},
					"sDefaultContent" : "", //默认空字符串 
					'sClass' : "text-center",
					"visible" : false
				},
				{
					"orderable" : true,
					"data" : function(obj) {
						return getDate(obj.modifyTime)//通过调用函数来格式化所获取的时间戳
					},
					"sDefaultContent" : "", //默认空字符串 
					'sClass' : "text-center"
				},
				{
					"orderable" : false,
					"data" : "preferState",
					"sDefaultContent" : "", //默认空字符串 
					'sClass' : "text-center"
				},
				{
					"orderable" : false,
					"data" : null,
					"className" : "center",
					"defaultContent" : '<button type="button" id="editrow" data-toggle="modal" class="btn btn-outline btn-circle btn-sm purple"><i class="fa fa-edit">编辑</i></button> &nbsp; <button id="delrow" class="btn btn-outline btn-circle dark btn-sm black"><i class="fa fa-trash-o">删除</i></button>'
				}
			]
		});
	});

	// 添加信息
	$("#btn_add").click(function() {
		console.log('add');// 控制台打印信息
		// 跳转到数据源信息添加页面
		var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/datasource/datasource_add";
		window.location.href = url;
	});

	//表数据删除
	$('#dataTables-example').on('click','button#delrow',function(even) {
		even.preventDefault;//方法阻止元素发生默认的行为（例如，当点击提交按钮时阻止对表单的提交）。
		alert('友情提示^_^:数据源删除可能对部分启用该数据源的报表造成影响,请谨慎操作.');
		if (confirm("友情提示^_^:确定删除该数据源信息?") == false) {
			return;
		} else {
			/* 得到当前对象的值 */
			var data = $("#dataTables-example").DataTable().row($(this).parents('tr')).data();
			// 封装选择的数据行
			/* var selrow = {
					dataIds : [ data.dataId ]
			}; */
			var selrow = {
				configId : data.configId 
			};
			$.ajax({
				url : '${pageContext.request.contextPath }/portal/dataSource/deleteDataSource',
				type : 'POST',
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(selrow),
				success : function(data) {
					// alert(data.result_code);
					if (data.result_code == "1") {
						alert('服务器反馈^_^:数据源信息删除成功!');
						start = $("#dataTables-example").dataTable().fnSettings()._iDisplayStart;//从第几行开始显示数据
						total = $("#dataTables-example").dataTable().fnSettings().fnRecordsDisplay();// 
						//alert(start);
						//alert(total);
						window.location.reload();
						if ((total - start) == 1) {
							if (start > 0) {
								$("#dataTables-example").dataTable().fnPageChange('previous',true);//已回到上一页中 
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
			});
		}
	});

	//表数据编辑(跳转页面)
	$('#dataTables-example').on('click','button#editrow',function(even) {
		even.preventDefault;//方法阻止元素发生默认的行为（例如，当点击提交按钮时阻止对表单的提交）。
		/* 得到当前对象的值 */
		var data = $("#dataTables-example").DataTable().row($(this).parents('tr')).data();
		// 封装选择的数据行
		var url = "${pageContext.request.contextPath }/portal/dataSource/updateDataSourceUI/"+ data.configId;
		window.location.href = url;
	});
		
	// 时间转化
	function getDate(time) {
		if (typeof (time) == "undefined") {
			return "";
		}
		var oDate = new Date(time), oYear = oDate.getFullYear(), oMonth = oDate.getMonth() + 1, 
			oDay = oDate.getDate(), oHour = oDate.getHours(), oMin = oDate.getMinutes(), 
			oSen = oDate.getSeconds(),
			oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
			// oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);//最后拼接时间  yy-mm-dd
			return oTime;
		};

	//补0操作,当时间数据小于10的时候，给该数据前面加一个0  
	function getzf(num) {
		if (parseInt(num) < 10) {
			num = '0' + num;
		}
		return num;
	}
	
	//-------------------- 模态框操作 --------------------------------//
	
	// 回显模态框数据源配置信息
	$('#dataTables-example').on('click','button#showConfig',function(even) {
		even.preventDefault;//方法阻止元素发生默认的行为（例如，当点击提交按钮时阻止对表单的提交）。
		/* 得到当前对象的值 */
		var data = $("#dataTables-example").DataTable().row($(this).parents('tr')).data();
		// 请求后台数据,封装数据,回显到模态框
		$.ajax({
				url : '${pageContext.request.contextPath }/portal/dataSource/getDataSourceById/'+data.configId,
				type : 'POST',
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				// data : JSON.stringify(selrow),
				success : function(data) {
					// alert(data.result_code);
					if (data.result_code == "1") {
						// 获取数据成功,封装数据到模态框
						var datasource = data.data.data;
						var config = data.data.config;
						$("#configName").val(datasource.configName);
						$("#configType").val(config.configType);
						$("#username").val(config.username);
						$("#password").val(config.password);
						$("#driver").val(config.driver);
						$("#url").val(config.url);
						// 弹出模态框
						$("#showDataSourceConfig").modal('show');
					}
				},
				error : function(data) {
					// alert(data.result_code);
					console.log(data);
					alert('服务器反馈^_^:服务器无响应，请联系管理员!');
				}
			});
	});
	
	$("#closeModal").click(function() {
		$("#showDataSourceConfig").modal('hide');
	});
	
	// 测试连接
	$("#testConnection").click(function() {
		var submitData = {
				"configType":$("#configType").val(),
				"username":$("#username").val(),
				"password":$("#password").val(),
				"driver":$("#driver").val(),
				"url":$("#url").val()
		};
		$.ajax({
			url : '${pageContext.request.contextPath }/portal/dataSource/testConnection',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(submitData),
			success : function(data) {
				// alert(data.result_code);
				if (data.result_code == "1") {
					// 获取数据成功,封装数据到模态框
					var state = data.data.state;
					if(state=='success'){
						alert('服务器反馈^_^:测试连接成功.');
					}else if(state=='fail'){
						alert('服务器反馈^_^:测试连接失败,请检查您的配置.');
					}
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
