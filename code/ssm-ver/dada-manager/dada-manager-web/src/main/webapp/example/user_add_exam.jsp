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

<title>哒哒报表服务平台后台管理</title>
<!-- 引入相关的css、js文件 -->
<%@include file="/public/common.jspf"%>

</head>

<!-- <script language="JavaScript"> -->
<script type="text/javascript">
	// 加载时间
	/* window.onload=function(){
		var today=new Date();
		var submitTime=today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate(); 
		$("#createTime").attr('value',submitTime);
	} */
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
		// 注意跨域请求异常
		// $.post("localhost:8080/manager/data/listLeafByDataCode", param,
		/* $.post(contentPath + "/dataDictionary/listLeafByDataCode", param,
			function(returnData) {
				alert(returnData);
			}); */
		// 2.请求、封装json数据
		$.ajax({
			url : contentPath + '/dataDictionary/listLeafByDataCode',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			async : false,
			dataType : 'json',
			data : JSON.stringify(param_gender),
			/* Error: function (xhr, type, errorThrown) {
			    console.log(JSON.stringify(xhr));
			    console.log(type);
			    console.log(errorThrown);
			}, */
			success : function(responseData) {
				console.log(responseData);
				// 返回的是JSON对象,直接根据dataList数据封装下拉框数据
				// <option value="1">女</option>
				// alert(responseData.data.dataList[0].dataName)
				var data = responseData.data.dataList;
				for (var i = 0; i < data.length; i++) {
					var option = "<option value="+"'"+data[i].dataId+"'"+">"
							+ data[i].dataName + "</option>";
					// alert(option);
					$("#gender").append(option);
				}
			}
		});

		// 封装机构分类信息(请求机构分类表中所有可用数据)
		/* $.ajax({
			url : contentPath + '/organizationCategory/list',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			async : false,
			dataType : 'json',
			data : null,
			success : function(responseData) {
				console.log(responseData);
				var data = responseData.data.dataList;
				for (var i = 0; i < data.length; i++) {
					var option = "<option value="+"'"+data[i].dataId+"'"+">"
							+ data[i].dataName + "</option>";
					$("#categoryId").append(option);
				}
			}
		}); */
		// 根据用户用户选择的机构分类封装对应的用户角色信息(二级联动),初始化时暂不加载
	}

	// 如果用户当前变动了所属机构分类，则自动刷新对应的机构用户角色信息
	function refreshRole() {
		// 获取当前用户选择的机构id
		var categoryId = $("#categoryId").val();
		// 清空当前的下拉框信息
		// $("#roleId").find("option").remove();
		$("#roleId").empty();
		var option = "<option>请选择所属机构</option>";
		$("#roleId").append(option);
		// 根据当前用户选择的机构id查找对应的机构用户角色信息
		$.ajax({
			url : contentPath + '/role/list',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			async : false,
			dataType : 'json',
			data : categoryId,
			success : function(responseData) {
				console.log(responseData);
				var data = responseData.data.dataList;
				for (var i = 0; i < data.length; i++) {
					var option = "<option value="+"'"+data[i].dataId+"'"+">"
							+ data[i].dataName + "</option>";
					$("#roleId").append(option);
				}
			}
		});
	}

	// 激活/禁用下拉框选型
	function changeSelect() {
		// 根据用户选择类型激活、禁用用户所属机构、用户角色的下拉框
		var userCategory = $("#userCategory").val();
		if (userCategory == "0") {
			// 普通用户：禁用下拉框
			$('#categoryId').attr("disabled", "disabled");
			// $('#categoryId').trigger("chosen:updated");
			$('#roleId').attr("disabled", true);

		} else if (userCategory == "1") {
			// 机构用户：启用下拉框
			$('#categoryId').removeAttr("disabled");
			$('#roleId').attr("disabled", false);
			/* 
			$("roleId").each(function () {
				 $("#" + this.id).attr("disabled",true);
				});
			$("roleId").each(function () {
				 $("#" + this.id).removeAttr("disabled");
				});
			 */
		}
	}
</script>

<body onload="loadData()">

	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="/common/navigation.jsp"%>
		<!-- Navigation end -->

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">添加用户信息</h1>
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
								<li><a href="#">机构维护</a></li>
								<li><a href="#">机构用户管理</a></li>
								<li>添加用户信息</li>
							</ul>
						</div>

						<div class="panel-body">
							<%-- <form
								action="${pageContext.request.contextPath }/manager/user/addUserInfo"
								class="form-horizontal" > --%>
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
										<div class="form-group">
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
													class="form-control input-sm">
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
												<select id="categoryId" name="categoryId"
													class="form-control input-sm" onchange="refreshRole()"
													disabled="disabled">
													<option>请选择所属机构</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户角色</label>
											<div class="col-sm-9">
												<select id="roleId" name="roleId"
													class="form-control input-sm" disabled="disabled">
													<option>请选择用户角色</option>
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
										<div class="form-group">
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
									<div class="col-sm-3 col-sm-offset-4">
										<!-- <input type="submit" class="btn btn-success" value="保存" />  -->
										<input id="submitButton" type="submit" class="btn btn-success"
											value="保存" /> <input type="reset" class="btn btn-grey"
											value="取消" /> <a class="btn btn-warning"
											href="${pageContext.request.contextPath }/manager/user/list">返回上一级</a>
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

	<!-- 封装页面数据:提交数据到服务器实现用户数据添加  -->
	<script type="text/javascript">
		/* 
			$(function(){
			　　// test 的点击事件
			　　$("#submitButton").click(function(){
			                
			　　　　alert("点击了");
			　　});
			                
			　　// 调用 test 的点击事件的两种方法
			　　//  $("#submitButton").trigger("click");
			　　//  $("#submitButton").click()
			});
		 */

		function checkEditFrom() {
			var loginAccount = $("#loginAccount").val();
			var contentPath = "/" + location.pathname.split("/")[1];
			/* var validateRepeatPath = contentPath
					+ "/user/validateRepeatLoginAccount/"+$("#loginAccount").val(); */
			var validateRepeatPath = contentPath
					+ "/user/validateRepeatLoginAccount";
			alert(validateRepeatPath);
			var validateRepeatData = {
				"loginAccount" : $("#loginAccount").val()
			}
			$("#submitForm").bootstrapValidator({
				live : 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
				excluded : [ ':disabled', ':hidden', ':not(:visible)' ],//排除无需验证的控件，比如被禁用的或者被隐藏的
				submitButtons : '#submitButton',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
				message : '通用的验证失败消息',//好像从来没出现过
				feedbackIcons : {//根据验证结果显示的各种图标
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				submitButtons: 'button[type="submit"]',
				fields : {
					// 用户名称验证
					userName : {
						message : '用户名称验证失败',
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							},
							stringLength : { //长度限制
								min : 6,
								max : 18,
								message : '用户名长度必须在6到18位之间'
							},
							regexp : { // 数据验证正则表达式
								regexp : /^[a-zA-Z0-9_]+$/,
								message : '用户名只能包含大写、小写、数字和下划线'
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
							remote : {
								url : validateRepeatPath,
								type : 'POST',
								message : '当前登录账号已存在,请重新输入',
								// delay : 1000,
								data : {
									loginAccount : function() {
										return $("#loginAccount").val();
									}
								}
							}
						}
					}
				}
			});
		}
 
		$(document).ready(function() {
			// $("input").focus(function(){
			//   $("input").css("background-color","#FFFFCC");
			// });
			/* $("input").blur(function() {
				$("#submitForm").bootstrapValidator(checkEditFrom());
				var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
				validator.validate(); // 手动触发验证
				alert('验证结果：' + validator.isValid())
			}); */
			
		/* 	$("#userName").blur(function() {
				// $("#submitForm").data("bootstrapValidator").validateField("userName");
				$("#submitForm").data("bootstrapValidator").validateField("userName");
			}); */
		});
 
		// 如果是submit则直接验证，如果是button类型则调用验证
		$("#submitButton")
				.click(
						function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
							// $("#submitForm").bootstrapValidator('validate');
							// var flag = $('#submitForm').data("bootstrapValidator").isValid();
							$("#submitForm")
									.bootstrapValidator(checkEditFrom());
							var validator = $('#submitForm').data(
									"bootstrapValidator"); //获取validator对象
							validator.validate(); // 手动触发验证
							alert('验证结果：' + validator.isValid());
							if (validator.isValid()) {
								lockUI();
								/*当校验失败  默认阻止了提交*/
								/*当校验成功  默认就提交了*/
								/*阻止默认的提交方式  改用ajax提交方式*/

								// 1.封装页面json数据
								var contentPath = "/"
										+ location.pathname.split("/")[1];
								// var dataObj = $("#submitForm").serialize(); 转化为地址栏参数传入
								// var dataObj = $("#submitForm").serializeJSON(); // 转化为json数据传入
								// var dataObj = $("#submitForm").serializeArray(); // 转化为数组
								// alert(dataObj); // 显示数据为地址栏字符串形式
								// var dataJson = dataObj.parseJSON();
								// console.log(dataObj);
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
								$
										.ajax({
											url : contentPath
													+ '/user/addUserInfo',
											type : 'POST',
											contentType : 'application/json; charset=UTF-8',
											async : false,
											dataType : 'json',
											// data : JSON.stringify(dataObj),
											// data : { jsondata: JSON.stringify(dataObj) },
											data : JSON.stringify(submitData),
											success : function(responseData) {
												console.log(responseData);
												// 如果数据添加成功跳转到list页面
												var url = "${pageContext.request.contextPath }/manager/page/other?url=userInfo/user_list";
												window.location.href = url;
											}
										});

								alert('hhhh');
							} else {
								alert('3333');
							}
						});
	</script>


</body>

</html>
