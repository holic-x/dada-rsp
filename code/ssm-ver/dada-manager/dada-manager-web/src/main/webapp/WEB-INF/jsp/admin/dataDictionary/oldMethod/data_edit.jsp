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
<!-- <script language="JavaScript"> -->
<script type="text/javascript">
	// 封装页面数据
	function loadData() {
		// 传入dataCode(父节点的数据编码-唯一查找相关的子节点数据信息)
		var contentPath = "/" + location.pathname.split("/")[1];

		// 封装所有父节点(类似数据分类)信息
		var param_isleaf = {
			"isleaf" : "0",
		};
		// 请求、封装json数据
		$.ajax({
			url : contentPath + '/dataDictionary/listData',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			async : false,
			dataType : 'json',
			data : JSON.stringify(param_isleaf),
			success : function(responseData) {
				console.log(responseData);
				// 返回的是JSON对象,直接根据dataList数据封装下拉框数据
				var data = responseData.data.dataList;
				for (var i = 0; i < data.length; i++) {
					var option = "<option value="+"'"+data[i].dataId+"'"+">"
							+ data[i].dataName + "</option>";
					// alert(option);
					$("#parentId").append(option);
				}
				// 设置默认属性
				var s = document.getElementById("parentId");
				var ops = s.options;
				for (var i = 0; i < ops.length; i++) {
					var tempValue = ops[i].value;
					if (tempValue == '${data.parentId}') {
						ops[i].selected = true;
					}
				}
			}
		});
	}

	// 激活/禁用下拉框选型
	function changeSelect() {
		// 根据用户选择是否为叶子节点激活对应的下拉框 类型激活、禁用用户所属机构、用户角色的下拉框
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

		<!-- Navigation -->
		<%@include file="/common/navigation.jsp"%>
		<!-- Navigation end -->

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">修改数据信息</h1>
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
								<li><a href="#">平台维护</a></li>
								<li><a href="#">数据字典管理</a></li>
								<li>修改数据信息</li>
							</ul>
						</div>

						<div class="panel-body">
							<%-- <form
								action="${pageContext.request.contextPath }/manager/user/addUserInfo"
								class="form-horizontal" > --%>
							<form id="submitForm" class="form-horizontal" action="#">
								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
								<!-- 设置默认隐藏属性 -->
								<input type="hidden" id="dataId" name="dataId"
									value="${data.dataId }" /> 
								<input type="hidden" id="createTime" name="createTime"
									value="<fmt:formatDate value="${data.createTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
								<input type="hidden" id="modifyTime" name="modifyTime"
									value="<fmt:formatDate value="${data.modifyTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />

								<!-- 数据编码、数据名称 start -->
								<div class="row">
									<div class="col-sm-5">
										<div id="dataCodeGroup" class="form-group">
											<label class="col-sm-3 control-label">数据编码</label>
											<div class="col-sm-9">
												<input type="text" id="dataCode" name="dataCode"
													value="${data.dataCode }" class="form-control input-sm"
													placeholder="请输入数据编码" />
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">数据名称</label>
											<div class="col-sm-9">
												<input type="text" id="dataName" name="dataName"
													value="${data.dataName }" class="form-control input-sm"
													placeholder="请输入数据名称" />
											</div>
										</div>
									</div>

								</div>
								<!-- 数据编码、数据名称  end -->

								<!--是否叶子结点、父id start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">父/子节点</label>
											<div class="col-sm-9">
												<select class="form-control input-sm" id="isleaf"
													name="isleaf" onchange="changeSelect()">
													<option value="0" ${data.isleaf=='0'?'selected':'' }>父节点</option>
													<option value="1" ${data.isleaf=='1'?'selected':'' }>子节点</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">父级数据</label>
											<div class="col-sm-9">
												<select class="form-control input-sm" id="parentId"
													name="parentId">
													<option value="-1">无父级数据</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!--是否叶子结点、父id end -->

								<!-- 数据启用状态、数据分类(默认父级分类) start -->
								<div class="row">
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">启用状态</label>
											<div class="col-sm-9">
												<select id="dataStatus" name="dataStatus"
													class="form-control input-sm">
													<option value="0" ${data.dataStatus=='0'?'selected':'' }>启用</option>
													<option value="1" ${data.dataStatus=='1'?'selected':'' }>禁用</option>
												</select>
											</div>
										</div>
									</div>

									<!-- 
									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">数据分类</label>
											<div class="col-sm-9">
												<input type="text" id="dataType" name="dataType"
													class="form-control input-sm" placeholder="请输入数据分类" />
											</div>
										</div>
									</div> 
									-->

								</div>
								<!-- 数据启用状态、数据分类(默认父级分类) end -->

								<h5 class="page-header alert-info"
									style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
								<div class="row">
									<div class="col-sm-10">
										<div class="form-group">
											<label class="col-sm-3 control-label">备注信息</label>
											<div class="col-sm-9">
												<textarea class="form-control" rows="3" cols="50"
													id="dataDesc" name="dataDesc" placeholder="请填写数据备注信息">${data.dataDesc }</textarea>
											</div>
										</div>
									</div>
								</div>


								<div class="row">
									<div class="col-sm-3 col-sm-offset-4">
										<!-- <input type="submit" class="btn btn-success" value="保存" />  -->
										<input id="submitButton" type="submit" class="btn btn-success"
											value="保存" /> <input type="reset" class="btn btn-grey"
											value="取消" /> <a class="btn btn-warning"
											href="${pageContext.request.contextPath }/manager/page/other?url=dataDictionary/data_list">返回上一级</a>
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
					// 用户名称验证
					dataName : {
						message : '数据名称验证失败',
						validators : {
							notEmpty : {
								message : '数据名称不能为空'
							},
							stringLength : { //长度限制
								min : 1,
								max : 20,
								message : '数据名称长度限制在20字以内'
							},
							regexp : { // 数据验证正则表达式
								// regexp : /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/,
								regexp : /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
								message : '中文名称需要匹配中文，英文字母和数字及下划线'
							}
						}
					},
					dataCode : {
						message : '数据编码验证失败',
						validators : {
							notEmpty : {
								message : '数据编码不能为空'
							},
							stringLength : { //长度限制
								min : 3,
								max : 20,
								message : '数据编码长度必须在3到20位之间'
							},
							regexp : { // 数据验证正则表达式
								regexp : /^[a-zA-Z0-9_]+$/,
								message : '数据编码只能包含大写、小写、数字和下划线'
							}
						}
					},
					dataType : {
						validators : {
							notEmpty : {
								message : '数据类型不能为空'
							},
							stringLength : { //长度限制
								min : 3,
								max : 30,
								message : '数据类型长度在3-30位之间'
							}
						}
					},
					dataDesc : {
						validators : {
							stringLength : { //长度限制
								min : 0,
								max : 200,
								message : '数据描述长度在0-300位之间'
							}
						}
					}
				}
			});
		}

		// 如果是submit则直接验证，如果是button类型则调用验证
		$("#submitButton")
				.click(
						function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
							$("#submitForm")
									.bootstrapValidator(checkEditFrom());
							var validator = $('#submitForm').data(
									"bootstrapValidator"); //获取validator对象
							validator.validate(); // 手动触发验证

							if (validator.isValid()) {
								// 表单基本信息验证完成，此处验证重复编码吗名称
								var contentPath = "/"
										+ location.pathname.split("/")[1];

								var validateDataCodePath = contentPath
										+ "/dataDictionary/validateRepeatDataCode";

								var dataCodeData = {
									"dataCode" : $("#dataCode").val(),
									"dataId" : $("#dataId").val()
								};
								// 验证数据编码信息重复
								var repeatDataCode = Boolean(false);
								$
										.ajax({
											url : validateDataCodePath,
											type : 'POST',
											contentType : 'application/json; charset=UTF-8',
											async : false,
											dataType : 'json',
											data : JSON.stringify(dataCodeData),
											success : function(responseData) {
												console.log(responseData);
												if (responseData.data.valid == "true") {
													alert('数据编码重复，请确认后重新操作!');
													repeatDataCode = Boolean(true);
													// 设置样式
													$('#dataCodeGroup')
															.addClass(
																	'has-error');
													$('#submitButton')
															.removeAttr(
																	"disabled");
													return;
												}
											}
										});
								if (repeatDataCode) {
									return;
								}

								if (!repeatDataCode) {
									// 提交表单数据
									// 禁用提交按钮，防止表单重复提交
									$('#submitButton').attr("disabled", true);
									/*当校验失败  默认阻止了提交*/
									/*当校验成功  默认就提交了*/
									/*阻止默认的提交方式  改用ajax提交方式*/
									// 1.封装页面json数据
									var submitData = {
										// 设置默认数据
										"dataId" : $("#dataId").val(),
										"createTime" : $("#createTime").val(),
										"modifyTime" : $("#modifyTime").val(),
										// 设置显示数据
										"dataCode" : $("#dataCode").val(),
										"dataName" : $("#dataName").val(),
										"dataType" : $("#dataType").val(),
										"dataDesc" : $("#dataDesc").val(),
										"dataStatus" : $("#dataStatus").val(),
										"isleaf" : $("#isleaf").val(),
										"parentId" : $("#parentId").val()
									};
									
									// 2.请求服务器传送json数据，添加用户信息
									$
											.ajax({
												url : contentPath
														+ '/dataDictionary/updateData',
												type : 'POST',
												contentType : 'application/json; charset=UTF-8',
												async : false,
												dataType : 'json',
												data : JSON
														.stringify(submitData),
												success : function(responseData) {
													console.log(responseData);
													var result_code = responseData.result_code;
													if (result_code == "1") {
														// 返回状态码为1，数据访问成功
														alert('数据添加成功,正在返回页面!');
														// 如果数据添加成功跳转到list页面
														var url = "${pageContext.request.contextPath }/manager/page/other?url=dataDictionary/data_list";
														window.location.href = url;
													} else {
														alert('服务器访问错误!');
													}
												}
											});
								}
							} else {
								alert('还有部分表单信息尚未完善，请确认后再次提交!');
							}
						});
	</script>


</body>

</html>
