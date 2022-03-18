<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
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
<%@include file="/public/common.jspf"%>

</head>

<!-- <script language="JavaScript"> -->
<script type="text/javascript">
	// 封装页面数据源
	function loadData() {
		// 数据源(单表操作,此处加载页面数据源)
	}
</script>

<body onload="loadData()">

	<div id="wrapper">

		<!-- Navigation -->
		<!-- 后台导航模块引入,替换前台框架后不需要引入,只显示局部页面 -->
		<%-- <%@include file="/common/admin_navigation.jsp"%> --%>
		<!-- Navigation end -->

		<!-- Page Content -->
		<!-- 取消后台导航样式：替换样式 -->
		<!-- <div id="page-wrapper">   -->
		<div class="admin-content">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">&nbsp;修改数据源信息</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- 导航条 -->
						<div style="padding: 0px; margin: 0px;">
							<ul class="breadcrumb" style="margin: 0px;">
								<li><a href="#">数据源管理</a></li>
								<li><a href="#">数据源信息</a></li>
								<li>修改数据源信息</li>
							</ul>
						</div>

						<div class="panel-body">
							<form id="submitForm" class="form-horizontal" action="#">
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
								<!-- 添加隐藏属性:配置数据源信息 -->
								<input type="hidden" id="configId" name="configId" value="${dataSourceInfo.configId }"/>
								<input type="hidden" id="categoryId" name="categoryId" value="${dataSourceInfo.categoryId }"/>
								<input type="hidden" id="createTime" name="createTime"
									value="<fmt:formatDate value="${dataSourceInfo.createTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
								<input type="hidden" id="modifyTime" name="modifyTime"
									value="<fmt:formatDate value="${dataSourceInfo.modifyTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
								<input type="hidden" id="delTag" name="delTag" value="${dataSourceInfo.delTag }" />
								<input type="hidden" id="preferState" name="preferState" value="${dataSourceInfo.preferState }" />
								
								<!-- 数据源名称 start -->
								<div class="row">
									<div class="col-sm-5">
										<div id="roleCodeGroup" class="form-group">
											<label class="col-sm-3 control-label">数据源名称</label>
											<div class="col-sm-9">
												<input type="text" id="configName" name="configName" value="${dataSourceInfo.configName}"
													class="form-control input-sm" placeholder="请输入数据源名称" />
											</div>
										</div>
									</div>
									<div class="col-sm-5">
										<div id="roleCodeGroup" class="form-group">
											<label class="col-sm-3 control-label">数据源类型</label>
											<div class="col-sm-9">
												<select id="configType" name="configType">
													<option value="MySql" ${config.configType=='MySql'?'selected':'' }>MySql</option>												
													<option value="Oracle" ${config.configType=='Oracle'?'selected':'' }>Oracle</option>												
													<option value="SQL Server" ${config.configType=='SQL Server'?'selected':'' }>SQL Server</option>												
													<option value="More" ${config.configType=='More'?'selected':'' }>More</option>												
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- 数据源名称  end -->

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">详细配置</h5>
								<!-- 登录用户名、登录密码 start -->
								<div class="row">
									<div class="col-sm-5">
										<div id="usernameGroup" class="form-group">
											<label class="col-sm-3 control-label">登录名称</label>
											<div class="col-sm-9">
												<input type="text" id="username" name="username" value="${config.username }"
													class="form-control input-sm" placeholder="请输入登录名称" />
											</div>
										</div>
									</div>
									
									<div class="col-sm-5">
										<div id="passwordGroup" class="form-group">
											<label class="col-sm-3 control-label">登录密码</label>
											<div class="col-sm-9">
												<input type="text" id="password" name="password" value="${config.password }"
													class="form-control input-sm" placeholder="请输入登录密码" />
											</div>
										</div>
									</div>
								</div>
								<!-- 登录用户名、登录密码 end -->
								
								
								<!-- 数据库驱动、数据库url start -->
								<div class="row">
									<div class="col-sm-5">
										<div id="driverGroup" class="form-group">
											<label class="col-sm-3 control-label">数据库驱动</label>
											<div class="col-sm-9">
												<input type="text" id="driver" name="driver" value="${config.driver }"
													class="form-control input-sm" placeholder="请输入数据库驱动" />
											</div>
										</div>
									</div>
									
									<div class="col-sm-5">
										<div id="urlGroup" class="form-group">
											<label class="col-sm-3 control-label">数据库url</label>
											<div class="col-sm-9">
												<input type="text" id="url" name="url" value="${config.url }"
													class="form-control input-sm" placeholder="请输入数据库url" />
											</div>
										</div>
									</div>
								</div>
								<!-- 数据库驱动、数据库url end -->
								<!-- 
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
								<div class="row">
									<div class="col-sm-10">
										<div class="form-group">
											<label class="col-sm-3 control-label">备注信息</label>
											<div class="col-sm-9">
												<textarea class="form-control" rows="3" cols="50"
													id="roleDescr" name="roleDescr"
													placeholder="请填写数据源备注信息">${config.username }</textarea>
											</div>
										</div>
									</div>
								</div>
 								-->
 								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">配置属性</h5>
								<div class="row">
									<div class="col-sm-10">
										<div class="form-group">
											<label class="col-sm-3 control-label">配置状态</label>
											<div class="col-sm-6">
												<select id="newPreferState">
													<option value="-1" ${dataSourceInfo.preferState=='-1'?'selected':'' }>默认普通数据源</option>
													<option value="1" ${dataSourceInfo.preferState=='1'?'selected':'' }>首选数据源</option>
													<option value="2" ${dataSourceInfo.preferState=='2'?'selected':'' }>备选数据源</option>
												</select>
											</div>
											<div class="col-sm-3">
												<input type="button" id="updatePreferState" class="btn btn-success" value="更新状态" />
											</div>
										</div>
									</div>
								</div>
 								
								<div class="row">
									<div class="col-sm-3 col-sm-offset-4">
										<input id="submitButton" type="submit" class="btn btn-success" value="保存" /> 
										<input id="testConnection" type="button" class="btn btn-grey" value="测试连接" />
										<!-- <input type="reset" class="btn btn-grey" value="取消" /> --> 
										<%-- <a class="btn btn-warning" href="${pageContext.request.contextPath }/portal/page/other?url=admin/system/roleInfo/role_list">返回上一级</a> --%>
									</div>
								</div>
							</form>
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
	</div>
	<!-- /#wrapper -->

	<!-- 封装页面数据源:提交数据源到服务器实现用户数据源添加  -->
	<script type="text/javascript">
		function checkEditFrom() {
			$("#submitForm").bootstrapValidator({
				live : 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
				excluded : [ ':disabled', ':hidden', ':not(:visible)' ],//排除无需验证的控件，比如被禁用的或者被隐藏的
				// submitButtons : '#submitButton',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
				message : '通用的验证失败消息',//好像从来没出现过
				feedbackIcons : {//根据验证结果显示的各种图标
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					// 数据源名称验证
					configName : {
						message : '数据源名称验证失败',
						validators : {
							notEmpty : {
								message : '数据源名称不能为空'
							},
							stringLength : { //长度限制
								min : 2,
								max : 20,
								message : '数据源名称长度限制在20字以内'
							},
							regexp : { // 数据源验证正则表达式
								// regexp : /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/,
								regexp : /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
								message : '中文名称需要匹配中文，英文字母和数字及下划线'
							}
						}
					},
					username : {
						message : '登录名称验证失败',
						validators : {
							notEmpty : {
								message : '登录名称不能为空'
							}
						}
					},
					password : {
						message : '登录密码验证失败',
						validators : {
							notEmpty : {
								message : '登录密码不能为空'
							}
						}
					},
					driver : {
						message : '数据源驱动验证失败',
						validators : {
							notEmpty : {
								message : '数据源驱动不能为空'
							}
						}
					},
					url : {
						message : '数据源url验证失败',
						validators : {
							notEmpty : {
								message : '数据源url不能为空'
							}
						}
					}
				}
			});
		}

		$(function(){
			$("#submitForm").bootstrapValidator(checkEditFrom());
			// configName失去焦点触发验证  
			$("#configName").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('configName'); 
			});
			
			// username失去焦点触发验证
			$("#username").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('username'); 
			});
			
			// password失去焦点触发验证
			$("#password").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('password');
			});
			
			// driver失去焦点触发验证
			$("#driver").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('driver');
			});
			
			// url失去焦点触发验证
			$("#url").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('url');
			});
			
		});
		
		// 如果是submit则直接验证，如果是button类型则调用验证
		$("#submitButton").click(function() {
			// 非submit按钮点击后进行验证，如果是submit则无需此句直接验证
			// $("#submitForm").bootstrapValidator(checkEditFrom());
			var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
			validator.validate(); // 手动触发验证
			if (validator.isValid()) {
				// 提交表单数据源
				// 禁用提交按钮，防止表单重复提交
				$('#submitButton').attr("disabled", true);
				// 1.封装页面json数据源
				// var categoryId = '${sessionScope.loginUser.categoryId}';
				var userId = '${sessionScope.loginUser.userId}';
				var dataSourceConfig = JSON.stringify({
					"configType":$("#configType").val(),
					"username":$("#username").val(),
					"password":$("#password").val(),
					"driver":$("#driver").val(),
					"url":$("#url").val()
				});
				var submitData = {
					"configId":$("#configId").val(),
					"configName" : $("#configName").val(),
					"categoryId":$("#categoryId").val(),
					"userId":userId,
					"configContent":dataSourceConfig,
					"createTime":$("#createTime").val(),
					"modifyTime":$("#modifyTime").val(),
					"preferState":$("#preferState").val(),
					"delTag":$("#delTag").val()
				};
				console.log(submitData);
				// 2.请求服务器传送json数据源，添加用户信息
				// url : contentPath + '/admin/system/roleInfo/addRole',
				$.ajax({
					url :'${pageContext.request.contextPath}/portal/dataSource/updateDataSource',
					type : 'POST',
					contentType : 'application/json; charset=UTF-8',
					async : false,
					dataType : 'json',
					data : JSON.stringify(submitData),
					success : function(responseData) {
						console.log(responseData);
						var result_code = responseData.result_code;
						if (result_code == "1") {
							// 返回状态码为1，数据源访问成功
							alert('服务器反馈^_^:数据源修改成功,正在返回页面!');
							// 如果数据源修改成功跳转到list页面
							var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/datasource/datasource_list";
							window.location.href = url;
						} else {
							alert('服务器反馈^_^:服务器访问错误!');
						}
					}
				});
			} else {
				$('#submitButton').attr("disabled", false);
				alert('服务器反馈^_^:还有部分表单信息尚未完善，请确认后再次提交!');
			}
		});
		
		// 测试连接
		$("#testConnection").click(function() {
			var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
			validator.validate(); // 手动触发验证
			if (!validator.isValid()) {
				// 如果验证不通过,提醒用户完善数据源信息
				alert('友情提示^_^:请完善数据源信息.');
				return ;
			}
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
		
		// 更新状态:(此处仅仅更新配置的首选、备选状态)
		$("#updatePreferState").click(function() {
			alert('友情提示^_^:机构默认只能配置一个首选、备选数据源,配置完成不建议做出大变动,可能对已有依赖报表造成影响.');
			var ensure = confirm('友情提示^_^:确认更新配置状态?');
			if(!ensure){
				alert('服务器反馈^_^:用户取消了操作.');
				return ;
			}
			var categoryId = '${sessionScope.loginUser.categoryId}';
			var submitData = {
				"categoryId":categoryId,
				"configId":$("#configId").val(),
				"preferState":$("#newPreferState").val()
			};
			console.log(submitData);
			$.ajax({
				url : '${pageContext.request.contextPath }/portal/dataSource/setPreferState',
				type : 'POST',
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(submitData),
				success : function(data) {
					// alert(data.result_code);
					if (data.result_code == "1") {
						// 数据源状态更新成功
						alert('服务器反馈^_^:数据源配置状态更新成功.');
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
