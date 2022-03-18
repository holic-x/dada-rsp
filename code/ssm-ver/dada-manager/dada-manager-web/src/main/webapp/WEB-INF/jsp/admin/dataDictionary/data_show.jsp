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
													disabled="disabled"/>
											</div>
										</div>
									</div>

									<div class="col-sm-5">
										<div class="form-group">
											<label class="col-sm-3 control-label">数据名称</label>
											<div class="col-sm-9">
												<input type="text" id="dataName" name="dataName"
													value="${data.dataName }" class="form-control input-sm"
													disabled="disabled"/>
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
													name="isleaf" onchange="changeSelect()" disabled="disabled">
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
													name="parentId" disabled="disabled">
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
													class="form-control input-sm" disabled="disabled">
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
													id="dataDesc" name="dataDesc" disabled="disabled">${data.dataDesc }</textarea>
											</div>
										</div>
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

</body>

</html>
