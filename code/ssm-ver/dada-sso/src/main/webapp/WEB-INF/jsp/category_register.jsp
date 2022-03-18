<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>哒哒报表服务平台机构注册</title>
<!-- 引入相关的css、js文件 -->
<%@include file="/public/common.jspf"%>

</head>

<!-- <script language="JavaScript"> -->
<script type="text/javascript">
	// 封装页面机构分类
	function loadData() {
		// 机构分类(单表操作,此处加载页面机构分类)
	}
</script>

<body onload="loadData()">

	<div id="wrapper">

		<!-- Navigation -->
		<%-- <%@include file="/common/navigation.jsp"%> --%>
		<!-- Navigation end -->

		<!-- Page Content -->
		<!-- <div id="page-wrapper"> -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">&nbsp;机构注册信息</h1>
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
								<li><a href="#">机构注册</a></li>
								<li>机构信息完善</li>
							</ul>
						</div>

						<div class="panel-body">
							<form id="submitForm" class="form-horizontal" action="#">
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
								<!-- 机构分类编码、机构分类名称 start -->
								<div class="row">
									<div class="col-sm-5">
										<div id="categoryCodeGroup" class="form-group">
											<label class="col-sm-3 control-label">机构分类编码</label>
											<div class="col-sm-9">
												<input type="text" id="categoryCode" name="categoryCode" class="form-control input-sm" placeholder="请输入机构分类编码" />
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">机构分类名称</label>
											<div class="col-sm-9">
												<input type="text" id="categoryName" name="categoryName" class="form-control input-sm" placeholder="请输入机构分类名称" />
											</div>
										</div>
									</div>

								</div>
								<!-- 机构分类编码、机构分类名称  end -->

								<!-- 机构分类启用状态、  start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">启用状态</label>
											<div class="col-sm-9">
												<select id="disableStatus" name="disableStatus"
													class="form-control input-sm">
													<option value="0" selected="selected">启用</option>
													<option value="1">禁用</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- 机构分类启用状态、  end -->

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
								<div class="row">
									<div class="col-sm-10">
										<div class="form-group">
											<label class="col-sm-3 control-label">备注信息</label>
											<div class="col-sm-9">
												<textarea class="form-control" rows="3" cols="50"
													id="categoryDescr" name="categoryDescr"
													placeholder="请填写机构分类备注信息"></textarea>
											</div>
										</div>
									</div>
								</div>


								<div class="row">
									<div class="col-sm-3 col-sm-offset-4">
										<input id="submitButton" type="submit" class="btn btn-success" value="注册" /> 
										<!-- <input type="reset" class="btn btn-grey" value="取消" /> --> 
										<a class="btn btn-warning" href="${pageContext.request.contextPath }/sso/page/login">返回登录页面</a>
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
		<!-- </div> -->
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- 封装页面机构分类:提交机构分类到服务器实现用户机构分类添加  -->
	<script type="text/javascript">
		function checkEditFrom() {
			var contentPath = "/" + location.pathname.split("/")[1];
			var validateCategoryCodePath = contentPath + "/checkRepeatCategoryCode";
			
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
				// submitButtons : 'button[type="submit"]',
				fields : {
					// 机构分类名称验证
					categoryName : {
						message : '机构分类名称验证失败',
						validators : {
							notEmpty : {
								message : '机构分类名称不能为空'
							},
							stringLength : { //长度限制
								min : 2,
								max : 20,
								message : '机构分类名称长度限制在20字以内'
							},
							regexp : { // 机构分类验证正则表达式
								// regexp : /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/,
								regexp : /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
								message : '中文名称需要匹配中文，英文字母和数字及下划线'
							}
						}
					},
					categoryCode : {
						message : '机构分类编码验证失败',
						validators : {
							notEmpty : {
								message : '机构分类编码不能为空'
							},
							stringLength : { //长度限制
								min : 3,
								max : 20,
								message : '机构分类编码长度必须在3到20位之间'
							},
							regexp : { // 机构分类验证正则表达式
								regexp : /^[a-zA-Z0-9_]+$/,
								message : '机构分类编码只能包含大写、小写、数字和下划线'
							},
							threshold :  2 , // 有两个字符以上才发送ajax请求(否则默认input每输入一个字符,向服务器发送一次请求)
							remote : {
								// remote对服务器端返回的内容有一定要求(valid、message)
								url : validateCategoryCodePath,
								type : 'GET',
								message : '当前机构编号已存在',
								delay : 1000,
								data : {
									dataValue : function() {
										return $("#categoryCode").val();
									},
									dataField : function() {
										return 'categoryCode';
									}
								}
							} 
						}
					},
					categoryDescr : {
						validators : {
							stringLength : { //长度限制
								min : 0,
								max : 200,
								message : '机构分类描述长度在0-300位之间'
							}
						}
					}
				}
			});
		}
		
		$(function(){
			$("#submitForm").bootstrapValidator(checkEditFrom());
			
			// categoryCode失去焦点触发验证
			$("#categoryCode").blur(function(){
				// $("#submitForm").bootstrapValidator(checkEditFrom());
				$('#submitForm').data("bootstrapValidator").validateField('categoryCode'); //获取validator对象
			});
			
			// categoryName失去焦点触发验证  
			$("#categoryName").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('categoryName'); 
			});
			
			// categoryDescr失去焦点触发验证
			$("#categoryDescr").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('categoryDescr'); //获取validator对象
			});
		});

		// 如果是submit则直接验证，如果是button类型则调用验证
		$("#submitButton").click(function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
			// $("#submitForm").bootstrapValidator(checkEditFrom());
			var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
			validator.validate(); // 手动触发验证
			if (validator.isValid()) {
				// 表单基本信息验证完成,提交数据至服务器
				var contentPath = "/" + location.pathname.split("/")[1];
				// 禁用提交按钮，防止表单重复提交
				$('#submitButton').attr("disabled", true);
				/*当校验失败  默认阻止了提交*/
				/*当校验成功  默认就提交了*/
				/*阻止默认的提交方式  改用ajax提交方式*/
				// 1.封装页面json机构分类
				var submitData = {
					"categoryCode" : $("#categoryCode").val(),
					"categoryName" : $("#categoryName").val(),
					"categoryDescr" : $("#categoryDescr").val(),
					"disableStatus" : $("#disableStatus").val()
				};

				// 2.请求服务器传送json机构分类，添加机构分类信息
				$.ajax({
					url : contentPath + '/register/organCategory',
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
							alert('操作反馈^_^:机构信息注册成功,正在返回页面.');
							alert('友情提示^_^:当前机构注册默认超级管理员:登录账号-admin\登录密码-000000');
							// 如果机构分类添加成功跳转到登陆页面
							var url = "${pageContext.request.contextPath }/sso/page/login";
							window.location.href = url;
						} else {
							alert('服务器反馈^_^:服务器访问错误,请联系管理员.');
						}
					}
				});
			} else {
				// alert('还有部分表单信息尚未完善，请确认后再次提交!');
				return ;
			}
		});
	</script>
</body>

</html>
