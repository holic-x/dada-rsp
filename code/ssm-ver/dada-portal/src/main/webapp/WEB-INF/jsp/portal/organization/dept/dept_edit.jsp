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

<title>哒哒报表服务平台后台管理</title>
<!-- 引入相关的css、js文件 -->
<%@include file="/public/common.jspf"%>

</head>

<!-- <script language="JavaScript"> -->
<script type="text/javascript">
	// 考虑到实际的复杂性,在部门添加完成后,只允许用户对部门的基本信息进行扩展,不允许用户修改部门父级分类
</script>

<body onload="loadData()">

	<div id="wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- 导航条 -->
						<div style="padding: 0px; margin: 0px;">
							<ul class="breadcrumb" style="margin: 0px;">
								<li><a href="#">机构维护</a></li>
								<li><a href="#">部门管理</a></li>
								<li>修改部门信息</li>
							</ul>
						</div>

						<div class="panel-body">
							<%-- <form
								action="${pageContext.request.contextPath }/manager/user/addUserInfo"
								class="form-horizontal" > --%>
							<form id="submitForm" class="form-horizontal" action="#">
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
								<!-- 设置隐藏属性 -->
								<input type="hidden" id="deptId" name="deptId" value="${dept.deptId }" /> 
								<input type="hidden" id="createTime" name="createTime"
									value="<fmt:formatDate value="${dept.createTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
								<input type="hidden" id="modifyTime" name="modifyTime"
									value="<fmt:formatDate value="${dept.modifyTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
								<input type="hidden" id="delTag" name="delTag" value="${dept.delTag }" />
								<!-- parentId、categoryId不允许用户修改,只供编辑 -->
								<input type="hidden" id="categoryId" name="categoryId" value="${dept.categoryId }" />
								<input type="hidden" id="parentId" name="parentId" value="${dept.parentId }" />

								<!-- 部门名称、上级部门 start -->
								<div class="row">
									<div class="col-sm-5">
										<div id="authorityCodeGroup" class="form-group">
											<label class="col-sm-3 control-label">部门名称</label>
											<div class="col-sm-9">
												<input type="text" id="deptName" name="deptName" value="${dept.deptName }"
													class="form-control input-sm" placeholder="请输入部门名称" />
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">上级部门</label>
											<div class="col-sm-9">
												<c:if test="${dept.parentId == '-1' }">
													<input type="text" value="无上级部门" class="form-control input-sm" disabled="disabled" />
												</c:if>
												<c:if test="${dept.parentId != '-1' }">
													<input type="text" value="当前所属上级部门" class="form-control input-sm" disabled="disabled" />
												</c:if>
											</div>
										</div>
									</div>
								</div>
								<!-- 部门名称、上级部门 end -->

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
								<div class="row">
									<div class="col-sm-10">
										<div class="form-group">
											<label class="col-sm-3 control-label">备注信息</label>
											<div class="col-sm-9">
												<textarea class="form-control" rows="3" cols="50"
													id="deptDescr" name="deptDescr"
													placeholder="请填写部门备注信息">${dept.deptDescr }</textarea>
											</div>
										</div>
									</div>
								</div>


								<div class="row">
									<div class="col-sm-3 col-sm-offset-4">
										<input id="submitButton" type="submit" class="btn btn-success" value="保存" /> 
										<input type="reset" class="btn btn-grey" value="取消" /> 
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
	<!-- /#wrapper -->

	<!-- 封装页面部门:提交部门到服务器实现部门修改  -->
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
					// 部门名称验证
					deptName : {
						message : '部门名称验证失败',
						validators : {
							notEmpty : {
								message : '部门名称不能为空'
							},
							stringLength : { //长度限制
								min : 2,
								max : 20,
								message : '部门名称长度限制在20字以内'
							},
							regexp : { // 部门验证正则表达式
								// regexp : /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/,
								regexp : /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
								message : '部门名称需要匹配中文，英文字母和数字及下划线'
							}
						}
					},
					deptDescr : {
						validators : {
							stringLength : { //长度限制
								min : 0,
								max : 300,
								message : '部门描述长度在0-300位之间'
							}
						}
					}
				}
			});
		}

		// 如果是submit则直接验证，如果是button类型则调用验证
		$("#submitButton").click(function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
			$("#submitForm").bootstrapValidator(checkEditFrom());
			var validator = $('#submitForm').data("bootstrapValidator"); //获取validator对象
			validator.validate(); // 手动触发验证
			if (validator.isValid()) {
				// 提交表单部门
				// 禁用提交按钮，防止表单重复提交
				$('#submitButton').attr("disabled", true);
				// 1.封装页面json部门
				var submitData = {
					// 封装隐藏数据
					"deptId" : $("#deptId").val(),
					"parentId" : $("#parentId").val(),
					"createTime" : $("#createTime").val(),
					"modifyTime" : $("#modifyTime").val(),
					"delTag" : $("#delTag").val(),
					"categoryId" : $("#categoryId").val(),
					// 封装显示数据	
					"deptName" : $("#deptName").val(),
					"deptDescr" : $("#deptDescr").val()
				};

				// 2.请求服务器传送json部门，修改信息
				$.ajax({
					url : '${pageContext.request.contextPath}/portal/organization/updateDepartment',
					type : 'POST',
					contentType : 'application/json; charset=UTF-8',
					// async : false,
					dataType : 'json',
					data : JSON.stringify(submitData),
					success : function(responseData) {
						console.log(responseData);
						var result_code = responseData.result_code;
						if (result_code == "1") {
							// 返回状态码为1，部门访问成功
							alert('操作反馈^_^:部门修改成功,正在返回页面.');
							// 如果部门修改成功跳转到list页面
							var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/organization/dept/dept_list";
							// window.location.href = url;
							// 针对内嵌页面内容重叠的情况,指定父级框架页面进行链接
							window.parent.location.href = url;
						} else {
							alert('服务器反馈^_^:服务器访问错误,请联系管理员.');
						}
					}
				});
			} else {
				alert('操作反馈^_^:还有部分表单信息尚未完善，请确认后再次提交!');
			}
		});
	</script>

</body>
</html>
