<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>用户注册</title>

<!--include file=  /resource/public/register.jspf -->

<style type="text/css">
body {
	font: normal 15px/1.5 Arial, Helvetica, Free Sans, sans-serif;
	color: #222;
	background: url(pattern.png);
	overflow-y: scroll;
	padding: 60px 0 0 0;
}

#my-form {
	width: 755px;
	margin: 0 auto;
	border: 1px solid #ccc;
	padding: 3em;
	border-radius: 3px;
	box-shadow: 0 0 2px rgba(0, 0, 0, .2);
}

#comments {
	width: 350px;
	height: 100px;
}
</style>

</head>


<!-- <body onload="loadData()"> -->
<body>
<div class="row">

	<div class="eightcol last">
		
		<div style="height：5px;text-align:center;font-size:25px"> 欢迎您注册！</div>     
		
		<!-- Begin Form -->
		<form id="my-form" name="my-form" action="${pageContext.request.contextPath }/ecm/register/registerUser.action" method="post" class="idealforms" onsubmit="return checkLoginname()">
			
			<!-- 添加用户类型字段：标识此处注册添加的是普通用户 -->
			<input type="hidden" name="userType" value="0" />
			<!-- 添加删除标识：0标识删除  1标识未删除 -->
			<input type="hidden" name="deleteFlag" value="1" />
			
			<section name="基本信息完善">

			<div>
				<label>真实姓名:</label><input id="userName" name="userName" type="text" />
			</div>

			<div>
				<label>用户年龄:</label><input id="age" name="age" type="text" />
			</div>

			<div>
				<label>用户性别:</label> <select class="form-control input-sm"
					id="gender" name="gender">
					<option value="保密">保密</option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</div>

			</section>

			<section name="身份信息完善">

			<div>
				<label>联系方式:</label>
				<input id="phone" type="text" name="phone" />
<!-- 				<label>联系方式:</label><input type="tel" name="phone"
					data-ideal="phone" /> -->
			</div>

			<div>
				<label>电子邮箱:</label><input id="email" name="email"
					data-ideal="required email" type="email" />
			</div>

			<div>
				<label>用户籍贯:</label> 
				<select id="address" name="address">
					<!-- onchange="getCity()">
					<option value="所在省份">所在省份</option>
					<option value="浙江省">浙江省</option>
					<option value="广东省">广东省</option>
					<option value="山东省">山东省</option>
					<option value="江苏省">江苏省</option>
					<option value="福建省">福建省</option>
					<option value="甘肃省">甘肃省</option> -->
					<c:forEach var="pro" items="${pro }">
						<option value="${pro.key }">${pro.key }</option>
					</c:forEach>
				</select>

			</div>

		<!-- 	<div>
				<label>家庭住址:</label> <select id="city" name="city">
					<option value="default">所在城市</option>
				</select>
			</div> -->

			</section>

			<section name="账号信息完善">
			<div>
				<label>用户名:</label><input id="loginname" name="loginname"
					type="text" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="loginname_error" style="color:#FF0000"></span>
			</div>
			<div>
				<!-- 创建非法标识:0标识合法  1标识非法 -->
				<input type="hidden" id="flag" value="1" />
			</div>
			
			<div>
				<label>密码:</label><input id="password" name="password"
					type="password" />
			</div>

			<div>
				<label>密码确认:</label><input id="confirmpassword"
					name="confirmpassword" type="password" />
			</div>

			</section>

			<div>
				<hr />
			</div>

			<div>
				<button type="submit">提交</button>
				<button id="reset" type="button">重置</button>
				<div>
					<span style="float: center; color: #333333;">我已注册，马上<span
						style="color: #4689cb;"><a
							href="${pageContext.request.contextPath }/ecm/login/loginUI.action">登录</a></span></span>
				</div>
			</div>

		</form>
		<!-- End Form -->

	</div>

</div>

<script type="text/javascript">

 var options = {

	onFail: function(){
		alert( $myform.getInvalid().length +'非法输入' )
	},

	inputs: {
		'userName': {
			filters: 'required my_username',
			/* data: {
			   ajax: { url:'validate.php' }
			}, */
		},
		'age': {
			filters: 'required my_age',
		},
		'loginname': {
			filters: 'required my_loginname',
			data: {
			 // ajax: { url:'validate.php' }
			}
		},
		'password': {
			filters: 'required my_password',
		},
		'confirmpassword': {
			filters: 'equalto',
			data:{
				equalto:[password]
			} ,
			errors : {
				equalto: '两次密码不一致.'
			} 
		},
		'email': {
			filters: 'required my_email',
		},
		'phone': {
			filters: 'required my_phone',
		},
		/* 'file': {
			filters: 'extension',
			data: { extension: ['jpg'] }
		}, */
		
		'address': {
			filters: 'exclude',
			data: { exclude: ['default'] },
			errors : {
				exclude: '选择籍贯.'
			}
		},
		
	}
	
};

 var $myform = $('#my-form').idealforms(options).data('idealforms');  

</script>

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
    
    function checkLoginname(){
    	// 判断标识是否合法方能进行提交
		var flag = $("#flag").val();
    	if(flag=="0"){
    		return true;
    	}
    	return false;
    }
</script>
<body>
</html>