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

<title>哒哒报表服务平台用户注册</title>
<!-- 引入相关的css、js文件 -->
<%@include file="/public/common.jspf"%>   

</head>

<!-- <script language="JavaScript"> -->
<script type="text/javascript">
	// 封装页面数据
	function loadData() {
		// 封装出生日期(时间数据)
		var today = new Date();
		var submitTime = today.getFullYear() + '-' + (today.getMonth() + 1)
				+ '-' + today.getDate();
		$("#birthday").attr('value', submitTime);

		// 传入dataCode(父节点的数据编码-唯一查找相关的子节点数据信息)
		var contentPath = "/" + location.pathname.split("/")[1];
		// 封装用户性别概念
		// 1.定义变量dataCode:gender
		var param_gender = {
			"dataCode" : "gender",
		};
		// 2.请求、封装json数据
		$.ajax({
			url : contentPath + '/getData/listLeafByDataCode',
			// url : 'http://localhost:8080/manager/dataDictionary/listLeafByDataCode', 跨域
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			async : false,
			dataType : 'json',
			data : JSON.stringify(param_gender),
			success : function(responseData) {
				console.log(responseData);
				// 返回的是JSON对象,直接根据dataList数据封装下拉框数据
				// <option value="1">女</option>
				// alert(responseData.data.dataList[0].dataName)
				var data = responseData.data.dataList;
				for (var i = 0; i < data.length; i++) {
					var option = "<option value="+"'"+data[i].dataId+"'"+">"
							+ data[i].dataName + "</option>";
					$("#gender").append(option);
				}
			}
		});
	}

	// 激活/禁用下拉框选型
	function changeSelect() {
		// 根据用户选择类型激活、禁用用户所属机构（用户角色默认普通用户）的下拉框
		var userCategory = $("#userCategory").val();
		if (userCategory == "0") {
			// 普通用户：禁用下拉框、默认选择首选项
			// $('#categoryId').attr("disabled", "disabled");
			$('#categoryCode').attr("disabled", "disabled");
			$('#categoryCode').val('public');
			// 设置测试id
			$('#categoryId').val('3');
		} else if (userCategory == "1") {
			// 机构用户：启用用户编码选项
			// $('#categoryId').removeAttr("disabled");
			$('#categoryCode').removeAttr("disabled");
			$('#categoryCode').val('');
			$('#categoryId').val('-1');
		}
	}
</script>

<body onload="loadData()">

	<div id="wrapper">

		<!-- Navigation -->
			<%-- <%@include file="/common/navigation.jsp"%>  --%>
		<!-- Navigation end -->

		<!-- Page Content -->
		<!-- <div id="page-wrapper"> -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">&nbsp;用户注册信息</h1>
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
								<li><a href="#">用户注册</a></li>
								<li>用户信息完善</li>
							</ul>
						</div>

						<div class="panel-body">
							<form id="submitForm" class="form-horizontal" action="#">
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
								<!-- 用户名称、出生年月 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户名称</label>
											<div class="col-sm-9">
												<input type="text" id="userName" name="userName"
													class="form-control input-sm" placeholder="请输入用戶名称" />
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">出生年月</label>
											<div class="col-sm-9">
												<input type="text" id="birthday" name="birthday"
													class="form-control input-sm"
													onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"
													readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
								<!-- 用户名称、出生年月 end -->

								<!--用户性别、用户状态、家庭住址 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户性别</label>
											<div class="col-sm-9">
												<select class="form-control input-sm" id="gender"
													name="gender">
													<!-- 
													<option value="-1">保密</option>
													<option value="0">男</option>
													<option value="1">女</option>
													 -->
													<%--  
													<c:forEach var="gender" items="${genderList}">
														<option value="${gender.dataId}">${roleInfo.dataName}</option>
													</c:forEach>
													 --%>
												</select>
											</div>
											<!-- 	
											<label class="col-sm-3 control-label">用户状态</label>
											<div class="col-sm-3">
												<select name="userState" class="form-control input-sm">
													<option value="0">启用</option>
													<option value="1">禁用</option>
												</select>
											</div>
											 -->
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">家庭住址</label>
											<div class="col-sm-9">
												<input type="text" id="address" name="address"
													class="form-control input-sm" placeholder="请输入家庭住址 " />
											</div>
										</div>
									</div>
								</div>
								<!--用户性别、用户状态、家庭住址 end -->

								<!--联系方式、电子邮箱 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">联系方式</label>
											<div class="col-sm-9">
												<input type="text" id="phone" name="phone"
													class="form-control input-sm" placeholder="请输入联系方式" />
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div id="emailGroup" class="form-group">
											<label class="col-sm-3 control-label">电子邮箱</label>
											<div class="col-sm-9">
												<input type="email" id="email" name="email"
													class="form-control input-sm" placeholder="请输入电子邮箱" />
											</div>
										</div>
									</div>
								</div>
								<!--联系方式、电子邮箱 end -->

								<!-- 用户状态、用户分类 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户状态</label>
											<div class="col-sm-9">
												<select id="userState" name="userState"
													class="form-control input-sm" disabled="disabled">
													<option value="0" selected="selected">启用</option>
													<option value="1">禁用</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户分类</label>
											<div class="col-sm-9">
												<select id="userCategory" name="userCategory"
													class="form-control input-sm" onchange="changeSelect()">
													<option value="0" selected="selected">普通用户</option>
													<option value="1">机构用户</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- 用户状态、用户分类 end -->

								<!--所属机构、用户角色 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">所属机构</label>
											<div class="col-sm-9">
											<!-- 
												<select id="categoryId" name="categoryId"
													class="form-control input-sm" onchange="refreshRole()"
													disabled="disabled">
													<option value="-1">请选择所属机构</option>
												</select>
											-->
												<input type="text" id="categoryCode" name="categoryCode" value="public" class="form-control input-sm" placeholder="请输入所属机构编码" disabled="disabled" />
												<input type="hidden" id="categoryId" name="categoryId" value="-1"/>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户角色</label>
											<div class="col-sm-9">
												<select id="roleId" name="roleId"
													class="form-control input-sm" disabled="disabled">
													<option value="-1">此项由机构管理员完善</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!--所属机构、用户角色 end -->

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
								<div class="row">
									<div class="col-sm-10">
										<div class="form-group">
											<label class="col-sm-3 control-label">备注信息</label>
											<div class="col-sm-9">
												<textarea class="form-control" rows="3" cols="50"
													id="userDescr" name="userDescr" placeholder="请填写用户备注信息"></textarea>
											</div>
										</div>
									</div>
								</div>

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">账号信息</h5>
								<!--用户登录信息 start-->
								<div class="row">
									<div class="col-sm-5">
										<div id="loginAccountGroup" class="form-group">
											<label class="col-sm-3 control-label">用户账号</label>
											<div class="col-sm-9">
												<input type="text" id="loginAccount" name="loginAccount"
													class="form-control input-sm" placeholder="请输入用户账号" />
											</div>
										</div>
									</div>
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户密码</label>
											<div class="col-sm-9">
												<input type="password" id="loginPassword"
													name="loginPassword" class="form-control input-sm"
													placeholder="请输入密码" />
											</div>
										</div>
									</div>
								</div>
								<!--用户登录信息 end-->

								<div class="row">
									<div class="col-sm-8 col-sm-offset-4">
										<input id="submitButton" type="submit" class="btn btn-success" value="注册" /> 
										<!-- 隐藏bug,点击重置数据,已验证的默认通过 -->
										<input type="reset" class="btn btn-grey" value="取消" /> 
										<a href="${pageContext.request.contextPath }/sso/page/login">我已有账号,跳转到登录页面</a>
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

	<!-- 封装页面数据:提交数据到服务器实现用户数据添加  -->
	<script type="text/javascript">
		// 验证表单信息
		function checkEditFrom() {
			// 表单基本信息验证完成，此处验证重复登录名称
			var contentPath = "/"+ location.pathname.split("/")[1];
			// 页面加载的时候即初始化,导致此时获取的数据始终为空,因此考虑通过json传入数据
			// var loginAccount = $("#loginAccount").val();
			// var email = $("#email").val();
			// var categoryCode = $("#categoryCode").val();
			// var validateLoginAccountPath = contentPath + "/checkUserData?dataValue="+loginAccount+"&dataField=loginAccount";
			// var validateEmailPath = contentPath + "/checkUserData?dataValue="+email+"&dataField=email";
			// var validateCategoryCodePath = contentPath + "/checkOrganCategoryData?dataValue="+categoryCode+"&dataField=categoryCode";
			// var validateCategoryCodePath = '${pageContext.request.contextPath}/sso/checkOrganCategoryData?dataValue='+categoryCode+'&dataField=categoryCode';
			var validateLoginAccountPath = contentPath + "/checkUserData";
			var validateEmailPath = contentPath + "/checkUserData";
			var validateCategoryCodePath = contentPath + "/checkOrganCategoryData";
			
			$("#submitForm").bootstrapValidator({
				live : 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
				excluded : [ ':disabled', ':hidden',':not(:visible)' ],//排除无需验证的控件，比如被禁用的或者被隐藏的
				submitButtons : '#submitButton',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
				message : '通用的验证失败消息',//好像从来没出现过
				feedbackIcons : {
					//根据验证结果显示的各种图标
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					// 用户名称验证
					userName : {
						message : '用户名称验证失败',
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							},
							stringLength : { //长度限制
								min : 2,
								max : 20,
								message : '用户名长度限制在20字以内'
							},
							regexp : { // 数据验证正则表达式
								regexp : /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/,
								message : '请填写证实有效的用户姓名'
							}
						}
					},
					address : {
						message : '家庭住址验证失败',
						validators : {
							stringLength : { //长度限制
							min : 0,
							max : 50,
							message : '家庭住址请控制在50字以内'
							}
						}
					},
					phone : {
						validators : {
							notEmpty : {
								message : '联系方式不能为空'
							},
							stringLength : { //长度限制
								min : 7,
								max : 14,
								message : '请输入有效的联系方式'
							},
							regexp : { // 数据验证正则表达式
								regexp : /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/,
								message : '请输入有效的联系方式'
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : '邮箱地址不能为空'
							},
							emailAddress : {
								message : '邮箱地址格式有误'
							},
							threshold :  2 , // 有两个字符以上才发送ajax请求(否则默认input每输入一个字符,向服务器发送一次请求)
							remote : {
								// remote对服务器端返回的内容有一定要求(valid、message)
								url : validateEmailPath,
								type : 'GET',
								message : '用户注册邮箱已存在',
								delay : 1000,
								data : {
									dataValue : function() {
										return $("#email").val();
									},
									dataField : function() {
										return 'email';
									}
								}
							} 
						}
					},
					categoryCode : {
						message : '机构编码验证失败',
						validators : {
							notEmpty : {
								message : '机构编码不能为空'
							},
							threshold :  2 , // 有两个字符以上才发送ajax请求(否则默认input每输入一个字符,向服务器发送一次请求)
							remote : {
								// remote对服务器端返回的内容有一定要求(valid、message)
								url : validateCategoryCodePath,
								type : 'GET',
								message : '机构信息不存在,请重新确认',
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
					loginAccount : {
						message : '用户登录账号验证失败',
						validators : {
							notEmpty : {
								message : '登录账号不能为空'
							},
							stringLength : { //长度限制
								min : 6,
								max : 18,
								message : '用户名长度必须在6到18位之间'
							},
							regexp : { // 数据验证正则表达式
								regexp : /^[a-zA-Z0-9_]+$/,
								message : '用户名只能包含大写、小写、数字和下划线'
							},
							threshold :  6 ,
							remote : {
								url : validateLoginAccountPath,
								type : 'GET',
								// type : 'POST',
								// dataType : 'json',
								// contentType : 'application/json; charset=UTF-8',
								message : '当前登录账号已存在,请重新输入',
								delay : 1000,
								data : {
									/*  dataCheckDTO:function() {
										var submitParam = {
											"dataValue":$("#loginAccount").val(),
											"dataField":'loginAccount'
										}
										return JSON.stringify(submitParam);
									}  */
									dataValue : function() {
										return $("#loginAccount").val();
									},
									dataField : function() {
										return 'loginAccount';
									}
								}
							} 
						}
					},
					loginPassword : {
						message : '用户登录密码验证失败',
							validators : {
								notEmpty : {
									message : '登录密码不能为空'
								},
								stringLength : { //长度限制
									min : 6,
									max : 18,
									message : '密码长度必须在6到18位之间'
								},
								regexp : { // 数据验证正则表达式
									regexp : /^[a-zA-Z0-9_]+$/,
									// regexp : /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/,
									message : '用户名只能包含大写、小写、数字和下划线'
									// message : '最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符'
								}
							}
						}
					}
				});
			}

		$(function(){
			$("#submitForm").bootstrapValidator(checkEditFrom());
			// userName address phone email categoryCode loginAccount  loginPassword
			
			// userName失去焦点触发验证  
			$("#userName").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('userName'); 
			});
			
			// address失去焦点触发验证  
			$("#address").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('address'); 
			});
			
			// phone失去焦点触发验证  
			$("#phone").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('phone'); 
			});
			
			// email失去焦点触发验证  
			$("#email").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('email'); 
			});
			
			// categoryCode失去焦点触发验证
			$("#categoryCode").blur(function(){
				// $("#submitForm").bootstrapValidator(checkEditFrom());
				$('#submitForm').data("bootstrapValidator").validateField('categoryCode'); //获取validator对象
			});
			
			// loginAccount失去焦点触发验证  
			$("#loginAccount").blur(function(){
				$('#submitForm').data("bootstrapValidator").validateField('loginAccount'); 
			});
			
			// loginPassword失去焦点触发验证
			$("#loginPassword").blur(function(){
				// $("#submitForm").bootstrapValidator(checkEditFrom());
				$('#submitForm').data("bootstrapValidator").validateField('loginPassword'); //获取validator对象
			});
		});

		// 如果是submit则直接验证，如果是button类型则调用验证
		$("#submitButton").click(function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
			// 重置验证菜单(加入以下语句ajax请求存在问题,页面跳转出错)
			// $("#submitForm").bootstrapValidator('resetForm');
			// $("#submitForm").bootstrapValidator(checkEditFrom());
			var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
			validator.validate(); // 手动触发验证
			if (validator.isValid()) {
				// 表单基本信息验证完成,提交数据到服务器,完成用户数据保存
				var contentPath = "/"+ location.pathname.split("/")[1];
				var validateCategoryCodePath = contentPath + "/checkOrganCategoryData";
				// 禁用提交按钮，防止表单重复提交
				$('#submitButton').attr("disabled", true);
				// 1.封装页面json数据
				var submitData = {
					"userName" : $("#userName").val(),
					"birthday" : $("#birthday").val(),
					"gender" : $("#gender").val(),
					"address" : $("#address").val(),
					"phone" : $("#phone").val(),
					"email" : $("#email").val(),
					"userState" : $("#userState").val(),
					"userCategory" : $("#userCategory").val(),
					"categoryId" : $("#categoryId").val(),
					"roleId" : $("#roleId").val(),
					"userDescr" : $("#userDescr").val(),
					"loginAccount" : $("#loginAccount").val(),
					"loginPassword" : $("#loginPassword").val(),
				};
				// 2.请求服务器传送json数据，添加用户信息
				$.ajax({
					url : contentPath + '/register/user',
					type : 'POST',
					contentType : 'application/json; charset=UTF-8',
					async : false,
					dataType : 'json',
					data : JSON.stringify(submitData),
					success : function(responseData) {
						console.log(responseData);
						var result_code = responseData.result_code;
						if (result_code == "1") {
							// 返回状态码为1，数据访问成功
							alert('数据添加成功,正在返回页面!');
							// 如果数据添加成功跳转到登录页面
							var url = "${pageContext.request.contextPath }/sso/page/login";
							window.location.href = url;
						} else {
							alert('服务器访问错误!');
						}
					}
				});
			}else{
				// 启用提交按钮，防止表单重复提交
				$('#submitButton').attr("disabled", false);
			}
		});
	</script>


</body>

</html>
