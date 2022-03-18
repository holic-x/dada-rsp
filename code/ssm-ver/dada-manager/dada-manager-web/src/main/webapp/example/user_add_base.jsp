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
<%@include file="/public/table.jspf"%>

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
		
		// 封装用户性别概念
		
		
		
		// 封装用户状态概念
		
		// 封装用户分类概念
		
		// 封装机构分类信息
		
		// 根据用户用户选择的机构分类封装对应的用户角色信息
		
		
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
							<form
								action="${pageContext.request.contextPath }/manager/user/addUserInfo"
								class="form-horizontal">
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
								<!-- 用户名称、出生年月 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户名称</label>
											<div class="col-sm-9">
												<input type="text" name="userName"
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

								<!--用户性别、家庭住址 start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户性别</label>
											<div class="col-sm-4">
												<select class="form-control input-sm" name="gender">
													<!-- 
													<option value="-1">保密</option>
													<option value="0">男</option>
													<option value="1">女</option>
													 -->
													<c:forEach var="gender" items="${genderList}">
														<option value="${gender.dataId}">${roleInfo.dataName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">家庭住址</label>
											<div class="col-sm-9">
												<input type="text" name="address"
													class="form-control input-sm" placeholder="请输入家庭住址 " />
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
												<input type="text" name="phone"
													class="form-control input-sm" placeholder="请输入联系方式" />
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">电子邮箱</label>
											<div class="col-sm-9">
												<input type="email" name="email"
													class="form-control input-sm" placeholder="请输入电子邮箱" />
											</div>
										</div>
									</div>
								</div>
								<!--联系方式、电子邮箱 end -->

								<!-- 用户状态：0.启用   1.禁用 -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户状态</label>
											<div class="col-sm-9">
												<select name="userState" class="form-control input-sm">
													<c:forEach var="roleInfo" items="${roleInfoList}">
														<option value="${roleInfo.roleId}">${roleInfo.roleName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户分类</label>
											<div class="col-sm-9">
												<select name="department" class="form-control input-sm">
													<c:forEach var="dept" items="${departmentList}">
														<option value="${dept.dataId}">${dept.dataContent}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!--结束 -->



								<!--开始 -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户角色</label>
											<div class="col-sm-9">
												<select name="roleId" class="form-control input-sm">
													<c:forEach var="roleInfo" items="${roleInfoList}">
														<option value="${roleInfo.roleId}">${roleInfo.roleName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">所属部门</label>
											<div class="col-sm-9">
												<select name="department" class="form-control input-sm">
													<c:forEach var="dept" items="${departmentList}">
														<option value="${dept.dataId}">${dept.dataContent}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!--结束 -->

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
								<div class="row">
									<div class="col-sm-10">
										<div class="form-group">
											<label class="col-sm-3 control-label">备注信息</label>
											<div class="col-sm-9">
												<textarea class="form-control" rows="3" cols="50"></textarea>
											</div>
										</div>
									</div>
								</div>

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">账号信息</h5>
								<!--开始 -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户账号</label>
											<div class="col-sm-9">
												<input type="text" name="loginAccount"
													class="form-control input-sm" placeholder="请输入用户账号" />
											</div>
										</div>
									</div>
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">用户密码</label>
											<div class="col-sm-9">
												<input type="password" name="loginPassword"
													class="form-control input-sm" placeholder="请输入密码" />
											</div>
										</div>
									</div>
								</div>
								<!-- 结束 -->

								<div class="row">
									<div class="col-sm-3 col-sm-offset-4">
										<input type="submit" class="btn btn-success" value="保存" /> <input
											type="reset" class="btn btn-grey" value="取消" /> <a
											class="btn btn-warning"
											href="${pageContext.request.contextPath }/user/userinfo/listUserInfo.action">返回上一级</a>
									</div>
								</div>
							</form>




							<div class="row">
								<div class="col-lg-6">
									<form role="form">
										<div class="form-group">
											<label>Text Input</label> <input class="form-control">
											<p class="help-block">Example block-level help text here.</p>
										</div>
										<div class="form-group">
											<label>Text Input with Placeholder</label> <input
												class="form-control" placeholder="Enter text">
										</div>
										<div class="form-group">
											<label>Static Control</label>
											<p class="form-control-static">email@example.com</p>
										</div>
										<div class="form-group">
											<label>File input</label> <input type="file">
										</div>
										<div class="form-group">
											<label>Text area</label>
											<textarea class="form-control" rows="3"></textarea>
										</div>
										<div class="form-group">
											<label>Checkboxes</label>
											<div class="checkbox">
												<label> <input type="checkbox" value="">Checkbox
													1
												</label>
											</div>
											<div class="checkbox">
												<label> <input type="checkbox" value="">Checkbox
													2
												</label>
											</div>
											<div class="checkbox">
												<label> <input type="checkbox" value="">Checkbox
													3
												</label>
											</div>
										</div>
										<div class="form-group">
											<label>Inline Checkboxes</label> <label
												class="checkbox-inline"> <input type="checkbox">1
											</label> <label class="checkbox-inline"> <input
												type="checkbox">2
											</label> <label class="checkbox-inline"> <input
												type="checkbox">3
											</label>
										</div>
										<div class="form-group">
											<label>Radio Buttons</label>
											<div class="radio">
												<label> <input type="radio" name="optionsRadios"
													id="optionsRadios1" value="option1" checked>Radio 1
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="optionsRadios"
													id="optionsRadios2" value="option2">Radio 2
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="optionsRadios"
													id="optionsRadios3" value="option3">Radio 3
												</label>
											</div>
										</div>
										<div class="form-group">
											<label>Inline Radio Buttons</label> <label
												class="radio-inline"> <input type="radio"
												name="optionsRadiosInline" id="optionsRadiosInline1"
												value="option1" checked>1
											</label> <label class="radio-inline"> <input type="radio"
												name="optionsRadiosInline" id="optionsRadiosInline2"
												value="option2">2
											</label> <label class="radio-inline"> <input type="radio"
												name="optionsRadiosInline" id="optionsRadiosInline3"
												value="option3">3
											</label>
										</div>
										<div class="form-group">
											<label>Selects</label> <select class="form-control">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
												<option>5</option>
											</select>
										</div>
										<div class="form-group">
											<label>Multiple Selects</label> <select multiple
												class="form-control">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
												<option>5</option>
											</select>
										</div>
										<button type="submit" class="btn btn-default">Submit
											Button</button>
										<button type="reset" class="btn btn-default">Reset
											Button</button>
									</form>
								</div>
								<!-- /.col-lg-6 (nested) -->


								<div class="col-lg-6">
									<h1>Disabled Form States</h1>
									<form role="form">
										<fieldset disabled>
											<div class="form-group">
												<label for="disabledSelect">Disabled input</label> <input
													class="form-control" id="disabledInput" type="text"
													placeholder="Disabled input" disabled>
											</div>
											<div class="form-group">
												<label for="disabledSelect">Disabled select menu</label> <select
													id="disabledSelect" class="form-control">
													<option>Disabled select</option>
												</select>
											</div>
											<div class="checkbox">
												<label> <input type="checkbox">Disabled
													Checkbox
												</label>
											</div>
											<button type="submit" class="btn btn-primary">Disabled
												Button</button>
										</fieldset>
									</form>
									<h1>Form Validation States</h1>
									<form role="form">
										<div class="form-group has-success">
											<label class="control-label" for="inputSuccess">Input
												with success</label> <input type="text" class="form-control"
												id="inputSuccess">
										</div>
										<div class="form-group has-warning">
											<label class="control-label" for="inputWarning">Input
												with warning</label> <input type="text" class="form-control"
												id="inputWarning">
										</div>
										<div class="form-group has-error">
											<label class="control-label" for="inputError">Input
												with error</label> <input type="text" class="form-control"
												id="inputError">
										</div>
									</form>
									<h1>Input Groups</h1>
									<form role="form">
										<div class="form-group input-group">
											<span class="input-group-addon">@</span> <input type="text"
												class="form-control" placeholder="Username">
										</div>
										<div class="form-group input-group">
											<input type="text" class="form-control"> <span
												class="input-group-addon">.00</span>
										</div>
										<div class="form-group input-group">
											<span class="input-group-addon"><i class="fa fa-eur"></i>
											</span> <input type="text" class="form-control"
												placeholder="Font Awesome Icon">
										</div>
										<div class="form-group input-group">
											<span class="input-group-addon">$</span> <input type="text"
												class="form-control"> <span
												class="input-group-addon">.00</span>
										</div>
										<div class="form-group input-group">
											<input type="text" class="form-control"> <span
												class="input-group-btn">
												<button class="btn btn-default" type="button">
													<i class="fa fa-search"></i>
												</button>
											</span>
										</div>
									</form>
								</div>
								<!-- /.col-lg-6 (nested) -->
							</div>
							<!-- /.row (nested) -->
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

<!-- 封装页面数据  -->
<script type="text/javascript">
$(function(){
	$('#loginname').blur(function() {    
		var contentPath = "/" + location.pathname.split("/")[1];
		
		//1、失去焦点，获得输入框的内容
	    var loginname = $(this).val();
	    // alert('当前用户名'+loginname);
		// 验证当前注册用户的用户名是否已经存在
		var param={
				"loginname":loginname,
		};
	 	$.post(contentPath + "/ecm/register/CheckLoginname.action",param,
			function(returnData){
				if(returnData == "illegal"){
						// alert("当前用户名已存在，换一个试试吧！");
						$("#loginname_error").html("当前用户名已存在，换一个试试吧！");
						$("#flag").val('1');	
				}else if(returnData == "legal"){
						// alert("合法用户名");
						$("#loginname_error").html("合法用户名");
						$("#flag").val('0');
					}
				});  
	/* 	 $.post(
                "${pageContext.request.contextPath}/ecm/register/CheckLoginname.action",
                {"loginname":loginname},
                function(returnData){
					 alert(returnData);
				}
            );  */
	});     
});
	

</script>


</body>

</html>
