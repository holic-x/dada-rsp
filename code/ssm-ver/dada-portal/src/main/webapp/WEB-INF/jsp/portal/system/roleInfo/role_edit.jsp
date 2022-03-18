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
    // 封装页面角色
    function loadData() {
        // 角色(单表操作,此处加载页面角色)
    }
</script>

<body onload="loadData()">

<div id="wrapper">

	<!-- Navigation -->
	<%-- <%@include file="/common/admin_navigation.jsp"%> --%>
	<!-- Navigation end -->

	<!-- Page Content -->
	<!-- 取消后台导航样式：替换样式 -->
	<!-- <div id="page-wrapper">   -->
	<div class="admin-content">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">&nbsp;修改角色信息</h1>
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
							<li><a href="#">系统管理</a></li>
							<li><a href="#">角色管理</a></li>
							<li>修改角色信息</li>
						</ul>
					</div>

					<div class="panel-body">
						<%-- <form
                            action="${pageContext.request.contextPath }/manager/user/addUserInfo"
                            class="form-horizontal" > --%>
						<form id="submitForm" class="form-horizontal" action="#">
							<h5 class="page-header alert-info"
								style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
							<!-- 封装隐藏属性 -->
							
							<input type="hidden" id="roleId" name="roleId" value="${roleInfo.roleId}" />
							<input type="hidden" id="createTime" name="createTime"
								   value="<fmt:formatDate value="${roleInfo.createTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
							<input type="hidden" id="modifyTime" name="modifyTime"
								   value="<fmt:formatDate value="${roleInfo.modifyTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
							<input type="hidden" id="delFlag" name="delFlag" value="${roleInfo.delFlag }" />
							<input type="hidden" id="categoryId" name="categoryId" value="${roleInfo.categoryId }" />


							<!-- 角色编码、角色名称 start -->
							<div class="row">
								<div class="col-sm-5">
									<div id="roleCodeGroup" class="form-group">
										<label class="col-sm-3 control-label">角色编码</label>
										<div class="col-sm-9">
											<input type="text" id="roleCode" name="roleCode" value="${roleInfo.roleCode }"
												   class="form-control input-sm" placeholder="请输入角色编码" disabled="disabled"/>
										</div>
									</div>
								</div>

								<div class="col-sm-5">
									<div class="form-group">
										<label class="col-sm-3 control-label">角色名称</label>
										<div class="col-sm-9">
											<input type="text" id="roleName" name="roleName" value="${roleInfo.roleName }"
												   class="form-control input-sm" placeholder="请输入角色名称" />
										</div>
									</div>
								</div>

							</div>
							<!-- 角色编码、角色名称  end -->

							<!-- 角色归属机构  start -->
							<%--
							<div class="row">
								<div class="col-sm-5">
									<div class="form-group">
										<label class="col-sm-3 control-label">归属机构</label>
										<div class="col-sm-9">
											<input type="text" id="categoryId" name="categoryId"
												   class="form-control input-sm" value=""/>
										</div>
									</div>
								</div>
							</div>
							--%>
							<!-- 角色归属机构、  end -->

							<h5 class="page-header alert-info"
								style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
							<div class="row">
								<div class="col-sm-10">
									<div class="form-group">
										<label class="col-sm-3 control-label">备注信息</label>
										<div class="col-sm-9">
												<textarea class="form-control" rows="3" cols="50"
														  id="roleDescr" name="roleDescr"
														  placeholder="请填写角色备注信息">${roleInfo.roleDescr }</textarea>
										</div>
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm-3 col-sm-offset-4">
									<!-- <input type="submit" class="btn btn-success" value="保存" />  -->
									<input id="submitButton" type="submit" class="btn btn-success" value="保存" /> 
									<!-- <input type="reset" class="btn btn-grey" value="取消" /> --> 
									<a class="btn btn-warning" href="${pageContext.request.contextPath }/portal/page/other?url=portal/system/roleInfo/role_list">返回上一级</a>
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

<!-- 封装页面角色:提交角色到服务器实现用户角色修改  -->
<script type="text/javascript">
    function checkEditFrom() {

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
            submitButtons : 'button[type="submit"]',
            fields : {
                // 角色名称验证
                roleName : {
                    message : '角色名称验证失败',
                    validators : {
                        notEmpty : {
                            message : '角色名称不能为空'
                        },
                        stringLength : { //长度限制
                            min : 2,
                            max : 20,
                            message : '角色名称长度限制在20字以内'
                        },
                        regexp : { // 角色验证正则表达式
                            // regexp : /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/,
                            regexp : /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
                            message : '中文名称需要匹配中文，英文字母和数字及下划线'
                        }
                    }
                },
                /*
                roleCode : {
                    message : '角色编码验证失败',
                    validators : {
                        notEmpty : {
                            message : '角色编码不能为空'
                        },
                        stringLength : { //长度限制
                            min : 3,
                            max : 20,
                            message : '角色编码长度必须在3到20位之间'
                        },
                        regexp : { // 角色验证正则表达式
                            regexp : /^[a-zA-Z0-9_]+$/,
                            message : '角色编码只能包含大写、小写、数字和下划线'
                        }
                    }
                },
                */
                roleDescr : {
                    validators : {
                        stringLength : { //长度限制
                            min : 0,
                            max : 200,
                            message : '角色描述长度在0-300位之间'
                        }
                    }
                }
            }
        });
    }
    
    $(function(){
		$("#submitForm").bootstrapValidator(checkEditFrom());
		// loginAccount失去焦点触发验证  
		$("#roleName").blur(function(){
			$('#submitForm').data("bootstrapValidator").validateField('roleName'); 
		});
		
		// roleCode失去焦点触发验证
		/*
		$("#roleCode").blur(function(){
			$('#submitForm').data("bootstrapValidator").validateField('roleCode'); 
		});
		*/
		
		// roleDescr失去焦点触发验证
		$("#roleDescr").blur(function(){
			$('#submitForm').data("bootstrapValidator").validateField('roleDescr');
		});
		
	});
	

    // 如果是submit则直接验证，如果是button类型则调用验证
    $("#submitButton").click(function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
   		$("#submitForm").bootstrapValidator(checkEditFrom());
      	var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
      	validator.validate(); // 手动触发验证
     	if (validator.isValid()) {
      		// 提交表单角色
        	// 禁用提交按钮，防止表单重复提交
        	$('#submitButton').attr("disabled", true);
         	/*当校验失败  默认阻止了提交*/
          	/*当校验成功  默认就提交了*/
         	/*阻止默认的提交方式  改用ajax提交方式*/
        	// 1.封装页面json角色
         	var submitData = {
            	// 封装隐藏属性
				"roleId":$("#roleId").val(),
				"categoryId":$("#categoryId").val(),
				"createTime":$("#createTime").val(),
				"modifyTime":$("#modifyTime").val(),
            	"delFlag":$("#delFlag").val(),
				// 封装显示属性
           		"roleCode" : $("#roleCode").val(),
                "roleName" : $("#roleName").val(),
           		"roleDescr" : $("#roleDescr").val()
         	};

       		// 2.请求服务器传送json角色，修改用户信息
           	// url : contentPath + '/admin/system/roleInfo/addRole',
           	$.ajax({
           		url :'${pageContext.request.contextPath}/portal/system/updateRoleInfo',
               	type : 'POST',
           		contentType : 'application/json; charset=UTF-8',
             	async : false,
              	dataType : 'json',
               	data : JSON.stringify(submitData),
              	success : function(responseData) {
              		console.log(responseData);
              		var result_code = responseData.result_code;
              		if (result_code == "1") {
               			// 返回状态码为1，角色访问成功
                 		alert('操作反馈^_^:角色修改成功,正在返回页面.');
                  		// 如果角色修改成功跳转到list页面
                   		var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/system/roleInfo/role_list";
                 		window.location.href = url;
               		} else {
                		alert('服务器反馈^_^:服务器访问错误.');
            		}
          		}
      		});
  		} else {
  			alert('操作反馈^_^:还有部分表单信息尚未完善，请确认后再次提交.');
     		$('#submitButton').attr("disabled", false);
    	}
 	});
</script>
</body>

</html>
