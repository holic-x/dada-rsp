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
		/* 	$(document).ready(function() {
				$('#dataTables-example').DataTable({
					responsive : true
				});
			}); */

		$(document).ready(function() {
			$('#dataTables-example').dataTable().fnDestroy();//注意这句话，作用是在翻页的时候，需要将先前实用datatable生成的table摧毁，以便在翻页或者条件查找的时候，新的表格的搭建。 
			$('#dataTables-example').DataTable({
				dom : 'Bit',  //表示排序 搜索 分页表格等等出现的顺序 
				paging : true,// 是否分页
				"info" : true,
				"searching" : true, // 是否搜索
				"ordering" : true, // 是否排序
				"stateSave" : true,// 是否存储在cookie中（就是一次用户不想看到这一列，下次登录的时候还是不会出现这个列，是永久存储的） 
				buttons : [ 'colvis' ]
			});
		});
	</script>

<!-- 引入插件内容 -->

<link
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.datatables.net/buttons/1.5.0/css/buttons.dataTables.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.5.0/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.5.0/js/buttons.colVis.min.js"></script>



</body>

</html>
