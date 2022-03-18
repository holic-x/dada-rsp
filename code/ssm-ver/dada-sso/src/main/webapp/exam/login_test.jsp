<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>用户登录</title>

<!-- 引入相关的css、js文件 -->
<%@include file="/public/common.jspf"%>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">用户登录</h3>
					</div>
					<div class="panel-body">
						<form role="form" id="submitForm">
							<fieldset>
								<!-- 设置隐藏属性 -->
								<input type="hidden" id="saveStatus" name="saveStatus" />
								<div class="form-group">
									<input class="form-control" type="text" placeholder="请输入登录名称/注册邮箱" id="loginAccount" name="loginAccount" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" type="password" placeholder="请输入登录密码" id="loginPassword" name="loginPassword">
								</div>
								<div class="form-group">
									<input class="form-control" type="text" placeholder="请输入机构编码(企业号)" id="categoryCode" name="categoryCode">
								</div>
								<div class="checkbox">
									<label> 
										<input id="isSave" type="checkbox" value="">下次自动登录
									</label>
								</div>
								<button id="submitButton" class="btn btn-lg btn-success btn-block">登录</button>
								<!-- <a href="#" class="btn btn-lg btn-success btn-block">Login</a> -->
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		// 验证表单信息
		function checkEditFrom() {
			var loginAccount = $("#loginAccount").val();
			// var contentPath = "/" + location.pathname.split("/")[1];
			$("#submitForm").bootstrapValidator({
				live : 'enabled',//验证时机:enabled是内容有变化就验证(默认)/disabled和submitted是提交再验证
				excluded : [ ':disabled', ':hidden', ':not(:visible)' ],//排除无需验证的控件，比如被禁用的或者被隐藏的
				// submitButtons : '#submitButton',//指定提交按钮，如果验证失败则变成disabled，加了这句话非submit按钮也会提交到action指定页面
				message : '通用的验证失败消息',//好像从来没出现过
				feedbackIcons : {//根据验证结果显示的各种图标
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				submitButtons : 'button[type="submit"]',
				fields : {
					// 用户登录验证（可能是邮箱/可能是登录名称）
					loginAccount : {
						message : '用户名称验证失败',
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							}
						}
					},
					// 密码验证
					loginPassword : {
						message : '用户登录密码验证失败',
						validators : {
							notEmpty : {
								message : '登录密码不能为空'
							}
						}
					},
					// 机构编码验证
					categoryCode : {
						message : '机构编码验证失败',
						validators : {
							notEmpty : {
								message : '机构编码不能为空'
							}
						}
					}
				}
			});
		}
		
		// loginAccount失去焦点触发验证
		$("#loginAccount").blur(function(){
			$("#submitForm").bootstrapValidator(checkEditFrom());
			var validator = $('#submitForm').data("bootstrapValidator").validateField('loginAccount'); //获取validator对象
		});
		

		
		// 如果是submit则直接验证，如果是button类型则调用验证
		$("#submitButton").click(function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
			// $("#submitForm").bootstrapValidator('validate');
			// var flag = $('#submitForm').data("bootstrapValidator").isValid();
		//	$("#submitForm").bootstrapValidator(checkEditFrom());
			//var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
		//	validator.validate(); // 手动触发验证
//			if (validator.isValid()) {
			if (true) {
				// 表单基本信息验证完成，此处验证登录信息
				// 提交表单数据，禁用提交按钮，防止表单重复提交
				// $('#submitButton').attr("disabled", true);

				// 处理复选框按钮(选中按钮值置为"save",未选按钮值置为"unsave")
				if($("#isSave").is(":checked")){
					// 设置存储状态为save
					$("#saveStatus").val("save");
				}else{
					// 设置存储状态为unsave
					$("#saveStatus").val("unsave");
				}
				// 1.封装页面json数据
				var submitData = {
					"loginAccount" : $("#loginAccount").val(),
					"loginPassword" : $("#loginPassword").val(),
					"categoryCode" : $("#categoryCode").val(),
					"saveStatus" : $("#saveStatus").val()
				};
				// 2.请求服务器传送json数据，添加用户信息
				var contentPath = "/" + location.pathname.split("/")[1];
				$.ajax({
					// url : contentPath + '/login' ,
					url : '${pageContext.request.contextPath}/sso/login',
					type : 'POST',
					contentType : 'application/json; charset=UTF-8',
					// async : false,
					dataType : 'json',
					data : JSON.stringify(submitData),
					success : function(responseData) {
						console.log(responseData);
						var result_code = responseData.result_code;
						if (result_code == "1") {
							// 返回状态码为1，数据访问成功
							// 判断访问的状态result：success\error
							var result = responseData.data.result;
							if(result='success'){
								alert(responseData.data.message);
								// 如果数据添加成功跳转到相应的访问页面(由后台返回要跳转的页面)
								// var url = "${pageContext.request.contextPath }/manager/page/other?url=userInfo/user_list";
								// window.location.href = url;
							}else if(result='error'){
								alert(responseData.data.message);
								return ;
							}
						} else {
							alert('服务器访问错误!');
						}
					}
				});
			} else {
				alert('还有部分表单信息尚未完善，请确认后再次提交!');
			}
		});
	</script>

</body>

</html>
