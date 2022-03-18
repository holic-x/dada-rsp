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
	// 封装页面菜单
	function loadData() {
		var contentPath = "/" + location.pathname.split("/")[1];
		// 封装所有父节点(类似菜单分类)信息
		var param_isleaf = {
			"isleaf" : "0",
		};
		// 请求、封装json菜单
		$.ajax({
			url : contentPath + '/menu/listAuthorityInfo',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			async : false,
			dataType : 'json',
			data : JSON.stringify(param_isleaf),
			success : function(responseData) {
				console.log(responseData);
				// 返回的是JSON对象,直接根据authorityList菜单封装下拉框菜单
				var authority = responseData.data.dataList;
				for (var i = 0; i < authority.length; i++) {
					var option = "<option value="+"'"+authority[i].authorityId+"'"+">"
						+ authority[i].authorityName + "</option>";
					// alert(option);
					$("#parentId").append(option);
				}
			}
		});
	}

	// 激活/禁用下拉框选型
	function changeSelect() {
		// 根据菜单选择是否为叶子节点激活对应的下拉框 类型激活、禁用菜单所属机构、菜单角色的下拉框
		var isleaf = $("#isleaf").val();
		if (isleaf == "0") {
			// 父节点：禁用下拉框、默认选择首选项
			$('#parentId').attr("disabled", "disabled");
			$("#parentId option:first").prop("selected", 'selected');
			// 此处暂不处理分类
		} else if (isleaf == "1") {
			// 子节点：启用父类选择下拉框、
			$('#parentId').removeAttr("disabled");
		}
	}
</script>

<body onload="loadData()">

	<div id="wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- 导航条 -->
						<div style="padding: 0px; margin: 0px;">
							<ul class="breadcrumb" style="margin: 0px;">
								<li><a href="#">平台维护</a></li>
								<li><a href="#">菜单管理</a></li>
								<li>添加菜单信息</li>
							</ul>
						</div>

						<div class="panel-body">
							<form id="submitForm" class="form-horizontal" action="#">
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
								<!-- 菜单编码、菜单名称 start -->
								<div class="row">
									<div class="col-sm-5">
										<!-- 
										<div id="authorityCodeGroup" class="form-group">
											<label class="col-sm-3 control-label">菜单编码</label>
											<div class="col-sm-9">
												<input type="text" id="authorityCode" name="authorityCode"
													class="form-control input-sm" placeholder="请输入菜单编码" disabled="disabled" />
											</div>
										</div>
										 -->
										 <div class="form-group">
											<label class="col-sm-3 control-label">显示图标</label>
											<div class="col-sm-9">
												<!-- 使用font-awesome中的图标信息:默认提供一些基本的图标 -->
												<select class="form-control input-sm" id="authorityIcon" name="authorityIcon" >
													<option value="icon-desktop" selected="selected">icon-desktop</option>
													<option value="icon-search">icon-search</option>
													<option value="icon-table">icon-table</option>
													<option value="icon-dashboard">icon-dashboard</option>
													<option value="icon-sitemap">icon-sitemap</option>
													<option value="icon-envelope">icon-envelope</option>
													<option value="icon-user">icon-user</option>
													<option value="icon-th-list">icon-th-list</option>
													<option value="icon-signal">icon-signal</option>
													<option value="icon-cog">icon-cog</option>
													<option value="icon-cogs">icon-cogs</option>
													<option value="icon-trash">icon-trash</option>
													<option value="icon-home">icon-home</option>
													<option value="icon-lock">icon-lock</option>
													<option value="icon-globe">icon-globe</option>
													<option value="icon-bullhorn">icon-bullhorn</option>
													<option value="icon-folder-open">icon-folder-open</option>
													<option value="icon-comment">icon-comment</option>
													<option value="icon-comments-alt">icon-comments-alt</option>
													<option value="icon-leaf">icon-leaf</option>
													<option value="icon-tasks">icon-tasks</option>
													<option value="icon-group">icon-group</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">菜单名称</label>
											<div class="col-sm-9">
												<input type="text" id="authorityName" name="authorityName"
													class="form-control input-sm" placeholder="请输入菜单名称" />
											</div>
										</div>
									</div>

								</div>
								<!-- 菜单编码、菜单名称  end -->

								<!--是否叶子结点、父id start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">父/子节点</label>
											<div class="col-sm-9">
												<select class="form-control input-sm" id="isleaf"
													name="isleaf" onchange="changeSelect()">
													<option value="0" selected="selected">父节点</option>
													<option value="1">子节点</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">父级菜单</label>
											<div class="col-sm-9">
												<select class="form-control input-sm" id="parentId"
													name="parentId" disabled="disabled">
													<option value="-1">无父级菜单</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!--是否叶子结点、父id end -->

								<!-- 菜单url start -->
								<div class="row">
									<div class="col-sm-10">
										<div id="authorityUrlGroup" class="form-group">
											<label class="col-sm-3 control-label">菜单url</label>
											<div class="col-sm-9">
												<input type="text" id="authorityUrl" name="authorityUrl"
													class="form-control input-sm" placeholder="请输入菜单url"
													value="http://localhost:8082/portal/page/other?url=error/default" />
											</div>
										</div>
									</div>

								</div>
								<!-- 菜单url end -->

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
								<div class="row">
									<div class="col-sm-10">
										<div class="form-group">
											<label class="col-sm-3 control-label">备注信息</label>
											<div class="col-sm-9">
												<textarea class="form-control" rows="3" cols="50"
													id="authorityDescr" name="authorityDescr"
													placeholder="请填写菜单备注信息"></textarea>
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
		<!-- </div> -->
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- 封装页面菜单:提交菜单到服务器实现菜单添加  -->
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
					// 菜单名称验证
					authorityName : {
						message : '菜单名称验证失败',
						validators : {
							notEmpty : {
								message : '菜单名称不能为空'
							},
							stringLength : { //长度限制
								min : 2,
								max : 20,
								message : '菜单名称长度限制在20字以内'
							},
							regexp : { // 菜单验证正则表达式
								// regexp : /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/,
								regexp : /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
								message : '中文名称需要匹配中文，英文字母和数字及下划线'
							}
						}
					},
					/*
					authorityCode : {
						message : '菜单编码验证失败',
						validators : {
							notEmpty : {
								message : '菜单编码不能为空'
							},
							stringLength : { //长度限制
								min : 3,
								max : 20,
								message : '菜单编码长度必须在3到20位之间'
							},
							regexp : { // 菜单验证正则表达式
								regexp : /^[a-zA-Z0-9_]+$/,
								message : '菜单编码只能包含大写、小写、数字和下划线'
							}
						}
					},
					*/
					authorityUrl : {
						validators : {
							notEmpty : {
								message : '菜单URL不能为空'
							},
							stringLength : { //长度限制
								min : 3,
								max : 200,
								message : '菜单url长度在3-200位之间'
							}
						}
					},
					authorityDescr : {
						validators : {
							stringLength : { //长度限制
								min : 0,
								max : 200,
								message : '菜单描述长度在0-300位之间'
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
				// 表单基本信息验证完成，此处验证重复编码吗名称
				var contentPath = "/" + location.pathname.split("/")[1];
				var validateAuthorityCodePath = contentPath + "/menu/validateaAuthorityCode";
				var validateAuthorityUrlPath = contentPath + "/menu/validateaAuthorityUrl";
				/*
				var authorityCodeData = {
					"authorityCode" : $("#authorityCode").val(),
					"authorityId" : ""
				};
				// 验证菜单编码信息重复
				var repeatAuthorityCode = Boolean(false);
				$.ajax({
					url : validateAuthorityCodePath,
					type : 'POST',
					contentType : 'application/json; charset=UTF-8',
					async : false,
					dataType : 'json',
					data : JSON.stringify(authorityCodeData),
					success : function(responseData) {
						console.log(responseData);
						if (responseData.data.valid == "true") {
							alert('操作反馈^_^:菜单编码重复,请确认后重新操作.');
							repeatAuthorityCode = Boolean(true);
							// 设置样式
						$('#authorityCodeGroup').addClass('has-error');
						$('#submitButton').removeAttr("disabled");
						return;
					}
				}
			});
			if (repeatAuthorityCode) {
				return;
			}
			*/
			// 判断输入的url是否为默认的数据,如果为默认数据则不对url进行重复验证
			var defaultUrl = "http://localhost:8082/portal/page/other?url=error/default";
			var reallyUrl = $("#authorityUrl").val();
			var repeatAuthorityUrl = Boolean(false);
			if(defaultUrl != reallyUrl){
				var authorityUrlData = {
					"authorityUrl" : $("#authorityUrl").val(),
					"authorityId" : ""
				};
				$.ajax({
					url : validateAuthorityUrlPath,
					type : 'POST',
					contentType : 'application/json; charset=UTF-8',
					async : false,
					dataType : 'json',
					data : JSON.stringify(authorityUrlData),
					success : function(responseData) {
						// console.log(responseData);
						if (responseData.data.valid == "true") {
							alert('操作反馈^_^:菜单url重复,请确认后重新操作.');
							repeatAuthorityUrl = Boolean(true);
							// 设置样式
							$('#authorityUrlGroup').addClass('has-error');
							$('#submitButton').removeAttr("disabled");
								return;
						}	
					}
				});
			}
			
			if (repeatAuthorityUrl) {
				return;
			}
			// if ((!repeatAuthorityCode)&& (!repeatAuthorityUrl)) {
			if (!repeatAuthorityUrl) {
				// 提交表单菜单
				// 禁用提交按钮，防止表单重复提交
				$('#submitButton').attr("disabled", true);
				// 1.封装页面json菜单
				var submitData = {
					// "authorityCode" : $("#authorityCode").val(),
					"authorityIcon" : $("#authorityIcon").val(),
					"authorityName" : $("#authorityName").val(),
					"authorityUrl" : $("#authorityUrl").val(),
					"authorityDescr" : $("#authorityDescr").val(),
					"isleaf" : $("#isleaf").val(),
					"parentId" : $("#parentId").val()
				};

				// 2.请求服务器传送json菜单，添加信息
				$.ajax({
					url : contentPath + '/menu/addAuthorityInfo',
					type : 'POST',
					contentType : 'application/json; charset=UTF-8',
					// async : false,
					dataType : 'json',
					data : JSON.stringify(submitData),
					success : function(responseData) {
						console.log(responseData);
						var result_code = responseData.result_code;
						if (result_code == "1") {
							// 返回状态码为1，菜单访问成功
							alert('操作反馈^_^:菜单添加成功,正在返回页面.');
							// 如果菜单添加成功跳转到list页面
							var url = "${pageContext.request.contextPath }/manager/page/other?url=menu/menu_list";
							// window.location.href = url;
							// window.location.assign(url);
							// 针对内嵌页面内容重叠的情况,指定父级框架页面进行链接
							window.parent.location.href = url;
						} else {
							alert('服务器反馈^_^:服务器访问错误.');
						}
					}
				});
			}
		} else {
			alert('操作反馈^_^:还有部分表单信息尚未完善,请确认后再次提交.');
		}
	});
	</script>


</body>

</html>
