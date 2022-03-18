<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
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

<title>哒哒报表服务平台后台管理</title>
<!-- 引入相关的css、js文件 -->
<%@include file="/public/common.jspf"%>

</head>

<!-- <script language="JavaScript"> -->
<script type="text/javascript">
	// 封装页面数据
	// function loadData() { }
	/* 
	$(document).ready(function() {
		var contentPath = "/" + location.pathname.split("/")[1];

        // 初始化时根据当前登录的用户所属机构封装对应机构的数据信息
        var categoryId = $("#categoryId").val();
        // 根据当前用户选择的机构id查找对应的机构用户角色信息
        $.ajax({
            url : contentPath + '/organization/listRoleInfoCategoryId/'+ categoryId,
            type : 'GET',
            contentType : 'application/json; charset=UTF-8',
            async : false,
            // dataType : 'json',
            // data : categoryId,
            success : function(responseData) {
                console.log(responseData);
                var data = responseData.data.data;
                for (var i = 0; i < data.length; i++) {
                    var option = "<option value="+"'"+data[i].roleId+"'"+">"
                        + data[i].roleName + "</option>";
                    $("#roleId").append(option);
                }
                // 设置默认属性
                var s = document.getElementById("roleId");
                var ops = s.options;
                for(var i=0;i<ops.length; i++){
                    var tempValue = ops[i].value;
                    if(tempValue == '${userInfo.roleId}')
                    {
                        ops[i].selected = true;
                    }
                }
            }
        });
	});
	  */
</script>

<body onload="loadData()">

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
					<h1 class="page-header">&nbsp;个人资料编辑</h1>
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
								<li><a href="#">个人资料</a></li>
								<li>个人资料编辑</li>
							</ul>
						</div>

						<div class="panel-body">
							<%-- <form
								action="${pageContext.request.contextPath }/manager/user/addUserInfo"
								class="form-horizontal" > --%>
							<form id="submitForm" class="form-horizontal" action="#">
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
								<!-- 用户只能编辑基本信息,与角色、权限相关的属性隐藏 -->
								<!-- 针对没有显示的数据需要提供隐藏插件封装属性:用户id、创建日期、修改日期、删除标识、头像信息 -->
								<%-- <input type="hidden" id="userId" name="userId" value="${userInfo.userId }" />
								<input type="hidden" id="createTime" name="createTime"
									value="<fmt:formatDate value="${userInfo.createTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
								<input type="hidden" id="modifyTime" name="modifyTime"
									value="<fmt:formatDate value="${userInfo.modifyTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
								<input type="hidden" id="delTag" name="delTag" value="${userInfo.delTag }" /> 
								<input type="hidden" id="loginImage" name="loginImage" value="${userInfo.loginImage }" />
								<input type="hidden" id="userState" name="userState" value="${userInfo.userState }" />
								<input type="hidden" id="userCategory" name="userCategory" value="${userInfo.userCategory }" />
								<input type="hidden" id="categoryId" name="categoryId" value="${userInfo.categoryId }" />
								<input type="hidden" id="roleId" name="roleId" value="${userInfo.roleId }" /> --%>
								<input type="hidden" id="userId" name="userId" value="${loginUser.userId }" />
								<input type="hidden" id="createTime" name="createTime" value="" />
								<input type="hidden" id="modifyTime" name="modifyTime" value="" />
								<input type="hidden" id="delTag" name="delTag" value="" /> 
								<input type="hidden" id="loginImage" name="loginImage" value="" />
								<input type="hidden" id="userState" name="userState" value="" />
								<input type="hidden" id="userCategory" name="userCategory" value="" />
								<input type="hidden" id="categoryId" name="categoryId" value="" />
								<input type="hidden" id="roleId" name="roleId" value="" />
								<input type="hidden" id="deptId" name="deptId" value="" />

								<!-- 用户名称、出生年月 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户名称</label>
											<div class="col-sm-9">
												<input type="text" id="userName" name="userName"
													value="" class="form-control input-sm"
													placeholder="请输入用戶名称" />
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">出生年月</label>
											<div class="col-sm-9">
											<%
												Date d = new Date();
												SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
												String now = df.format(d);
											%>
												<input type="text" id="birthday" name="birthday"
													class="form-control input-sm" onclick="WdatePicker()"
													<%-- value="<fmt:formatDate value='${userInfo.birthday}' type="both" pattern="yyyy-MM-dd"/>" --%>
													<%-- value="<fmt:formatDate value="<%=now %>" type="both" pattern="yyyy-MM-dd"/>" --%>
													value=""
													readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
								<!-- 用户名称、出生年月 end -->

								<!--用户性别、家庭住址 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户性别</label>
											<div class="col-sm-9">
												<select class="form-control input-sm" id="gender"
													name="gender">
													<!-- ajax加载 -->
													<%-- 
													<option value="-1" ${user.gender=='-1'?'selected':'' }>保密</option>
													<option value="0" ${user.gender=='0'?'selected':'' }>男</option>
													<option value="1" ${user.gender=='1'?'selected':'' }>女</option> 
													--%>
													<option value="-1" >保密</option>
													<option value="0" >男</option>
													<option value="1" >女</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">家庭住址</label>
											<div class="col-sm-9">
												<input type="text" id="address" name="address"
													value="" class="form-control input-sm"
													placeholder="请输入家庭住址 " />
											</div>
										</div>
									</div>
								</div>
								<!--用户性别、家庭住址 end -->

								<!--联系方式、电子邮箱 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">联系方式</label>
											<div class="col-sm-9">
												<input type="text" id="phone" name="phone"
													value="" class="form-control input-sm"
													placeholder="请输入联系方式" />
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div id="emailGroup" class="form-group">
											<label class="col-sm-3 control-label">电子邮箱</label>
											<div class="col-sm-9">
												<input type="email" id="email" name="email"
													value="" class="form-control input-sm"
													placeholder="请输入电子邮箱" />
											</div>
										</div>
									</div>
								</div>
								<!--联系方式、电子邮箱 end -->

								<!-- 用户状态、用户分类 start -->
								<%-- 
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户状态</label>
											<div class="col-sm-9">
												<select id="userState" name="userState"
													class="form-control input-sm">
													<option value="0" ${user.userState=='0'?'selected':'' }>启用</option>
													<option value="1" ${user.userState=='1'?'selected':'' }>禁用</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户分类</label>
											<div class="col-sm-9">
												<select id="userCategory" name="userCategory"
													class="form-control input-sm" disabled="disabled">
													<!-- <option value="0" ${user.userCategory=='0'?'selected':'' }>普通用户</option> -->
													<option value="1" ${user.userCategory=='1'?'selected':'' }>机构用户</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								--%>
								<!-- 用户状态、用户分类 end -->

								<!--所属机构、用户角色 start -->
								<%-- 
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">所属机构</label>
											<div class="col-sm-9">
												<select id="categoryId" name="categoryId"
													class="form-control input-sm"disabled="disabled">
													<option value="${userInfo.categoryId }">默认当前机构</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户角色</label>
											<div class="col-sm-9">
												<select id="roleId" name="roleId" class="form-control input-sm"  >
													<option value="-1">请选择用户角色</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								--%>
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
													value=""
													class="form-control input-sm" placeholder="请输入用户账号" />
											</div>
										</div>
									</div>
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户密码</label>
											<div class="col-sm-9">
												<input type="password" id="loginPassword"
													value="" name="loginPassword" disabled="disabled"
													class="form-control input-sm" placeholder="请输入密码" />
											</div>
										</div>
									</div>
								</div>
								<!--用户登录信息 end-->

								<div class="row">
									<div class="col-sm-3 col-sm-offset-4">
										<!-- <input type="submit" class="btn btn-success" value="保存" />  -->
										<input id="submitButton" type="submit" class="btn btn-success" value="保存" />  
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
	
	// 请求数据封装页面信息
	// $(function(){
	$(document).ready(function() {
		// 获取当前登录用户id
		var userId = $("#userId").val();
		// 2.请求服务器获取当前机构分类信息
		$.ajax({
			// url : contentPath + '/organCategory/addCategory',
			url : '${pageContext.request.contextPath}/portal/organization/getUserInfoById/'+ userId,
				type : 'GET',
				contentType : 'application/json; charset=UTF-8',
				async : false,
				// dataType : 'json',
				// data : JSON.stringify(submitData),
				success : function(responseData) {
					console.log(responseData);
					var result_code = responseData.result_code;
					if (result_code == "1") {
						// 返回状态码为1，机构分类访问成功
						// alert('机构信息获取成功,正跳转页面!');
						var userInfo = responseData.data.data ;
						// 封装页面数据
						// 以js直接方法val()、html()数据无法直接回显到页面,需要通过dom节点操作数据
						document.getElementById("userId").value=userInfo.userId;
						document.getElementById("createTime").value=userInfo.createTime;
						document.getElementById("modifyTime").value=userInfo.modifyTime;
						document.getElementById("delTag").value=userInfo.delTag;
						document.getElementById("loginImage").value=userInfo.loginImage;
						document.getElementById("userState").value=userInfo.userState;
						document.getElementById("userCategory").value=userInfo.userCategory;
						document.getElementById("categoryId").value=userInfo.categoryId;
						document.getElementById("roleId").value=userInfo.roleId;
						document.getElementById("deptId").value=userInfo.deptId;
						document.getElementById("userName").value=userInfo.userName;
						document.getElementById("birthday").value=userInfo.birthday;
						// document.getElementById("gender").value=userInfo.gender;
						document.getElementById("address").value=userInfo.address;
						document.getElementById("phone").value=userInfo.phone;
						document.getElementById("email").value=userInfo.email;
						document.getElementById("userDescr").value=userInfo.userDescr;
						document.getElementById("loginAccount").value=userInfo.loginAccount;
						document.getElementById("loginPassword").value=userInfo.loginPassword;
						
						// 设置下拉框默认属性
						var s = document.getElementById("gender");  
					    var ops = s.options;  
					    for(var i=0;i<ops.length; i++){  
					        var tempValue = ops[i].value;  
					        if(tempValue == userInfo.gender)  
					        {  
					            ops[i].selected = true;  
					        }  
					    }   
					} else {
						alert('服务器反馈^_^:服务器访问错误.');
					}
				}
			});
		});
	
	
	function checkEditFrom() {
		var contentPath = "/" + location.pathname.split("/")[1];
		var validateDataPath = contentPath + "/organization/checkUserData";
		$("#submitForm").bootstrapValidator(
		{
			live : 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
			excluded : [ ':disabled', ':hidden',':not(:visible)' ],//排除无需验证的控件，比如被禁用的或者被隐藏的
			// submitButtons : '#submitButton',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
			message : '通用的验证失败消息',//好像从来没出现过
			feedbackIcons : {//根据验证结果显示的各种图标
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			submitButtons : 'button[type="submit"]',
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
					threshold :  4 , // 有两个字符以上才发送ajax请求(否则默认input每输入一个字符,向服务器发送一次请求)
					remote : {
						// remote对服务器端返回的内容有一定要求(valid、message)
						url : validateDataPath,
						type : 'GET',
						message : '用户注册邮箱已存在',
						delay : 1000,
						data : {
							dataValue : function() {
								return $("#email").val();
							},
							dataField : function() {
								return 'email';
							},
							userId : function() {
								return $("#userId").val();
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
					threshold :  4 ,
					remote : {
						url : validateDataPath,
						type : 'GET',
						message : '当前登录账号已存在,请重新输入',
						delay : 1000,
						data : {
							dataValue : function() {
								return $("#loginAccount").val();
							},
							dataField : function() {
								return 'loginAccount';
							},
							userId : function() {
								return $("#userId").val();
							}
						}
					}
				}
			},
			/* loginPassword : {
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
			} */
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
		
		/* // categoryCode失去焦点触发验证
		$("#categoryCode").blur(function(){
			// $("#submitForm").bootstrapValidator(checkEditFrom());
			$('#submitForm').data("bootstrapValidator").validateField('categoryCode'); //获取validator对象
		}); */
		
		// loginAccount失去焦点触发验证  
		$("#loginAccount").blur(function(){
			$('#submitForm').data("bootstrapValidator").validateField('loginAccount'); 
		});
		
		// loginPassword失去焦点触发验证
		/* $("#loginPassword").blur(function(){
			// $("#submitForm").bootstrapValidator(checkEditFrom());
			$('#submitForm').data("bootstrapValidator").validateField('loginPassword'); //获取validator对象
		}); */
	});
	
		// 如果是submit则直接验证，如果是button类型则调用验证
		$("#submitButton").click(function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
			// $("#submitForm").bootstrapValidator(checkEditFrom());
			var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
			validator.validate(); // 手动触发验证
			if (validator.isValid()) {
				// 表单基本信息验证完成
				var contentPath = "/" + location.pathname.split("/")[1];
				// 提交表单数据,禁用提交按钮，防止表单重复提交
				$('#submitButton').attr("disabled", true);
				// 1.封装页面json数据
				var submitData = {
					// 封装隐藏属性 
					"userId" : $("#userId").val(),
					"createTime" : $("#createTime").val(),
					"modifyTime" : $("#modifyTime").val(),
					"delTag" : $("#delTag").val(),
					"loginImage" : $("#loginImage").val(),
					// 封装显示属性
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
					"deptId" : $("#deptId").val(),
					"userDescr" : $("#userDescr").val(),
					"loginAccount" : $("#loginAccount").val(),
					"loginPassword" : $("#loginPassword").val()
				};

				// 2.请求服务器传送json数据，添加用户信息
				$.ajax({
					url : contentPath + '/organization/updateUserInfo',
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
							alert('数据修改成功,正在返回页面!');
							// 如果数据修改成功跳转到list页面
							var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/organization/userInfo/user_list";
							window.location.href = url;
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
