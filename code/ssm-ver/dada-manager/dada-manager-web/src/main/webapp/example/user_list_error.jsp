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

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="/common/navigation.jsp"%>
		<!-- Navigation end -->

		<!-- 页面显示内容 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">机构用户管理</h1>
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
								<li><a href="#">机构维护</a></li>
								<li>机构用户管理</li>
							</ul>
						</div>

						<!-- 操作按钮组合  class="btn-group operation"  -->
						<div class="panel-heading btn-group operation">
							<button id="btn_add" type="button" class="btn bg-primary">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
							</button>
							<button id="btn_edit" type="button" class="btn bg-info">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
							</button>
							<button id="btn_delete" type="button" class="btn btn-success">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
							</button>
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
										<th>Rendering engine</th>
										<th>Browser</th>
										<th>Platform(s)</th>
										<th>Engine version</th>
										<th>CSS grade</th>
									</tr>
								</thead>
								<!-- 表体数据 -->
								<tbody>
									<tr class="odd gradeX">
										<td>Trident</td>
										<td>Internet Explorer 4.0</td>
										<td>Win 95+</td>
										<td class="center">4</td>
										<td class="center">X</td>
									</tr>
									<tr class="even gradeC">
										<td>Trident</td>
										<td>Internet Explorer 5.0</td>
										<td>Win 95+</td>
										<td class="center">5</td>
										<td class="center">C</td>
									</tr>
									<tr class="odd gradeA">
										<td>Trident</td>
										<td>Internet Explorer 5.5</td>
										<td>Win 95+</td>
										<td class="center">5.5</td>
										<td class="center">A</td>
									</tr>
									<tr class="even gradeA">
										<td>Trident</td>
										<td>Internet Explorer 6</td>
										<td>Win 98+</td>
										<td class="center">6</td>
										<td class="center">A</td>
									</tr>
									<tr class="odd gradeA">
										<td>Trident</td>
										<td>Internet Explorer 7</td>
										<td>Win XP SP2+</td>
										<td class="center">7</td>
										<td class="center">A</td>
									</tr>
									<tr class="even gradeA">
										<td>Trident</td>
										<td>AOL browser (AOL desktop)</td>
										<td>Win XP</td>
										<td class="center">6</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Firefox 1.0</td>
										<td>Win 98+ / OSX.2+</td>
										<td class="center">1.7</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Firefox 1.5</td>
										<td>Win 98+ / OSX.2+</td>
										<td class="center">1.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Firefox 2.0</td>
										<td>Win 98+ / OSX.2+</td>
										<td class="center">1.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Firefox 3.0</td>
										<td>Win 2k+ / OSX.3+</td>
										<td class="center">1.9</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Camino 1.0</td>
										<td>OSX.2+</td>
										<td class="center">1.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Camino 1.5</td>
										<td>OSX.3+</td>
										<td class="center">1.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Netscape 7.2</td>
										<td>Win 95+ / Mac OS 8.6-9.2</td>
										<td class="center">1.7</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Netscape Browser 8</td>
										<td>Win 98SE+</td>
										<td class="center">1.7</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Netscape Navigator 9</td>
										<td>Win 98+ / OSX.2+</td>
										<td class="center">1.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.0</td>
										<td>Win 95+ / OSX.1+</td>
										<td class="center">1</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.1</td>
										<td>Win 95+ / OSX.1+</td>
										<td class="center">1.1</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.2</td>
										<td>Win 95+ / OSX.1+</td>
										<td class="center">1.2</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.3</td>
										<td>Win 95+ / OSX.1+</td>
										<td class="center">1.3</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.4</td>
										<td>Win 95+ / OSX.1+</td>
										<td class="center">1.4</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.5</td>
										<td>Win 95+ / OSX.1+</td>
										<td class="center">1.5</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.6</td>
										<td>Win 95+ / OSX.1+</td>
										<td class="center">1.6</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.7</td>
										<td>Win 98+ / OSX.1+</td>
										<td class="center">1.7</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Mozilla 1.8</td>
										<td>Win 98+ / OSX.1+</td>
										<td class="center">1.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Seamonkey 1.1</td>
										<td>Win 98+ / OSX.2+</td>
										<td class="center">1.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Gecko</td>
										<td>Epiphany 2.20</td>
										<td>Gnome</td>
										<td class="center">1.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Webkit</td>
										<td>Safari 1.2</td>
										<td>OSX.3</td>
										<td class="center">125.5</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Webkit</td>
										<td>Safari 1.3</td>
										<td>OSX.3</td>
										<td class="center">312.8</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Webkit</td>
										<td>Safari 2.0</td>
										<td>OSX.4+</td>
										<td class="center">419.3</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Webkit</td>
										<td>Safari 3.0</td>
										<td>OSX.4+</td>
										<td class="center">522.1</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Webkit</td>
										<td>OmniWeb 5.5</td>
										<td>OSX.4+</td>
										<td class="center">420</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Webkit</td>
										<td>iPod Touch / iPhone</td>
										<td>iPod</td>
										<td class="center">420.1</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Webkit</td>
										<td>S60</td>
										<td>S60</td>
										<td class="center">413</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Opera 7.0</td>
										<td>Win 95+ / OSX.1+</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Opera 7.5</td>
										<td>Win 95+ / OSX.2+</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Opera 8.0</td>
										<td>Win 95+ / OSX.2+</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Opera 8.5</td>
										<td>Win 95+ / OSX.2+</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Opera 9.0</td>
										<td>Win 95+ / OSX.3+</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Opera 9.2</td>
										<td>Win 88+ / OSX.3+</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Opera 9.5</td>
										<td>Win 88+ / OSX.3+</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Opera for Wii</td>
										<td>Wii</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Nokia N800</td>
										<td>N800</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>Presto</td>
										<td>Nintendo DS browser</td>
										<td>Nintendo DS</td>
										<td class="center">8.5</td>
										<td class="center">C/A<sup>1</sup>
										</td>
									</tr>
									<tr class="gradeC">
										<td>KHTML</td>
										<td>Konqureror 3.1</td>
										<td>KDE 3.1</td>
										<td class="center">3.1</td>
										<td class="center">C</td>
									</tr>
									<tr class="gradeA">
										<td>KHTML</td>
										<td>Konqureror 3.3</td>
										<td>KDE 3.3</td>
										<td class="center">3.3</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeA">
										<td>KHTML</td>
										<td>Konqureror 3.5</td>
										<td>KDE 3.5</td>
										<td class="center">3.5</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeX">
										<td>Tasman</td>
										<td>Internet Explorer 4.5</td>
										<td>Mac OS 8-9</td>
										<td class="center">-</td>
										<td class="center">X</td>
									</tr>
									<tr class="gradeC">
										<td>Tasman</td>
										<td>Internet Explorer 5.1</td>
										<td>Mac OS 7.6-9</td>
										<td class="center">1</td>
										<td class="center">C</td>
									</tr>
									<tr class="gradeC">
										<td>Tasman</td>
										<td>Internet Explorer 5.2</td>
										<td>Mac OS 8-X</td>
										<td class="center">1</td>
										<td class="center">C</td>
									</tr>
									<tr class="gradeA">
										<td>Misc</td>
										<td>NetFront 3.1</td>
										<td>Embedded devices</td>
										<td class="center">-</td>
										<td class="center">C</td>
									</tr>
									<tr class="gradeA">
										<td>Misc</td>
										<td>NetFront 3.4</td>
										<td>Embedded devices</td>
										<td class="center">-</td>
										<td class="center">A</td>
									</tr>
									<tr class="gradeX">
										<td>Misc</td>
										<td>Dillo 0.8</td>
										<td>Embedded devices</td>
										<td class="center">-</td>
										<td class="center">X</td>
									</tr>
									<tr class="gradeX">
										<td>Misc</td>
										<td>Links</td>
										<td>Text only</td>
										<td class="center">-</td>
										<td class="center">X</td>
									</tr>
									<tr class="gradeX">
										<td>Misc</td>
										<td>Lynx</td>
										<td>Text only</td>
										<td class="center">-</td>
										<td class="center">X</td>
									</tr>
									<tr class="gradeC">
										<td>Misc</td>
										<td>IE Mobile</td>
										<td>Windows Mobile 6</td>
										<td class="center">-</td>
										<td class="center">C</td>
									</tr>
									<tr class="gradeC">
										<td>Misc</td>
										<td>PSP browser</td>
										<td>PSP</td>
										<td class="center">-</td>
										<td class="center">C</td>
									</tr>
									<tr class="gradeU">
										<td>Other browsers</td>
										<td>All others</td>
										<td>-</td>
										<td class="center">-</td>
										<td class="center">U</td>
									</tr>
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
	<script>
		/*  $(document).ready(function() {
				$('#dataTables-example').DataTable({
					 responsive : true,
				});
			});  */

		$(document).ready(function() {

			var contentPath = "/" + location.pathname.split("/")[1];

			var searchParams = {
				"loginAccount" : "hahabibu"
			};

			$('#dataTables-example').dataTable().fnDestroy();//作用是在翻页的时候，需要将先前实用datatable生成的table摧毁，以便在翻页或者条件查找的时候，新的表格的搭建。 
			$('#dataTables-example').DataTable({
				// dom : 'Bit',dom : '<"wrapper"flipt>',dom : 'Bit <"top"i>rt<"bottom"flp><"clear">',
				dom : 'Bit <lf<t>ip>',
				paging : true,
				"info" : false,
				"searching" : true,
				"ordering" : true,
				"stateSave" : true,
				buttons : [ 'colvis' ],

				/* ajax : function (request) {
			        $.ajax({
			            type : "POST",
			            url : contentPath + '/user/listUserInfoByPage',
						contentType: "application/json;charset=utf-8",
						// dataType:"jsonp",
						dataType:"json",
						data:'',
						// jsonp:'callback',
			            success : function (msg) {			
							request.success({
			                    row : msg
			                });
			                $('#dataTables-example').bootstrapTable('load', msg);
			            },
						error:function(){
							alert("错误");
						}
			        });
				},
		 */
				
				
				/* ajax : {
					url : contentPath + '/user/listUserInfoByPage',
					dataSrc : 'data'
				}, */
				
				//   columns: [  ],
				/* 'aoColumns' : [ {
					sWidth : "45%",
					bSearchable : false,
					bSortable : false
				}, {
					sWidth : "45%",
					bSearchable : false,
					bSortable : false
				}, {
					sWidth : "10%",
					bSearchable : false,
					bSortable : false
				},
				//match the number of columns here for table1
				], */

				/* ajax : function(data, callback,
						settings) {//ajax配置为function,手动调用异步查询
					 searchParams.pageSize = data.length;
					searchParams.pageNum = data.start
							% data.length === 0 ? (data.start
							/ data.length + 1)
							: ((data.start % data.length) + 2); 
					console
							.log(contentPath + '/user/listUserInfo');
					$
							.ajax({
								type : "GET",
								url : contentPath + '/user/listUserInfo',
								cache : false, //禁用缓存
								data : searchParams, //传入已封装的参数
								dataType : "json",
								success : function(
										res) {
									// data.pageNum += 1;
									// var lists = res.data;
									var lists = res.data;
									//封装返回数据，这里仅演示了修改属性名
									var returnData = {};
									returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
									returnData.recordsTotal = lists.pages;
									returnData.recordsFiltered = lists.total;//后台不实现过滤功能，每次查询均视作全部结果
									returnData.data = lists.list;
									//关闭遮罩
									//$wrapper.spinModal(false);
									//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
									//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
									if (searchParams) {
										$
												.extend(
														searchParams,
														searchParams);
									} 
									callback(returnData);
								},
								error : function(
										XMLHttpRequest,
										textStatus,
										errorThrown) {
									Toast(
											'查询失败',
											'warning',
											'danger');
								},
								columns:[
									{"data":"createTime"},
									{"data":"equipCode"},
									{"data":"pointCode"},
									{"data":"value"},
									{"data":"ruleName"},
									{"data":"windowStart"},
									{"data":"windowEnd"},
									{"data":"",
									"class":"operation",
									"render": function(data, type, row) {
									return "<a target='_blank' class='map' windowStart='"+row.windowStart+"'
																	windowEnd='"+row.windowEnd+"' pointCode='"+row.pointCode+"' equipCode='"+row.equipCode+"'>趋势图</a>
									<a ruleId = "+row.ruleId+" class='queryDetailInfo'>查看报警规则</a>";
									}
									}
									], 
									
							}); */
				// },
				/* ajax : {
					url : contentPath + '/user/listUserInfo',
					type : 'POST',
					contentType : 'application/json; charset=UTF-8',
					async : false,
					dataType : 'json',
					// data : JSON.stringify(searchParams),
					 data : function(request) {
						  request.condition = {
							key : 'value'
						};  
					},  
					
					success : function(responseData) {
						console.log(responseData);
						
					},
					
					
					
					columns:[
						{"data":"createTime"},
						{"data":"equipCode"}
					], 
				},  */

				/* 					 ajax : {
										url : contentPath + '/user/listUserInfo',
										data : function(request) {
											  request.condition = {
												key : 'value'
											};  
										},
										type : 'POST'
									}, 
									"fnDrawCallback" : function(oSettings) {
										// var json = jQuery.parseJSON(oSettings.jqXHR.responseText);
										var json = jQuery.parseJSON(oSettings.jqXHR.responseJSON);
										//获取后台方式 直接可以拿到json 之后进行处理
										console.log(json);
										//此处json就是返回的数据 
									},  */

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
				}
			});
		});

		// 添加信息
		$("#btn_add")
				.click(
						function() {
							console.log('add');// 控制台打印信息
							alert('add');
							// 跳转到用户添加页面
							var url = "${pageContext.request.contextPath }/manager/page/other?url=userInfo/user_add";
							window.location.href = url;
						});
		// 编辑信息
		$("#btn_edit")
				.click(
						function() {
							console.log('add');// 控制台打印信息
							alert('add');
							// 跳转到用户添加页面
							var url = "${pageContext.request.contextPath }/manager/page/other?url=userInfo/user_edit";
							window.location.href = url;
						});
		// 删除信息
		$("#btn_delete")
				.click(
						function() {
							console.log('add');// 控制台打印信息
							alert('add');
							// 跳转到用户添加页面
							var url = "${pageContext.request.contextPath }/page/other?url=userInfo/user_add";
							window.location.href = url;
						});
	</script>



</body>

</html>
