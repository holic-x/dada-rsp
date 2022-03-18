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

<body>
	<div id="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<!-- 导航条 -->
					<div style="padding: 0px; margin: 0px;">
						<ul class="breadcrumb" style="margin: 0px;">
							<li><a href="#">机构维护</a></li>
							<li><a href="#">部门管理</a></li>
							<li>查看部门信息</li>
						</ul>
					</div>
					<div class="panel-body">
						<form id="submitForm" class="form-horizontal" action="#">
							<h5 class="page-header alert-info" style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
							<!-- 回显部门基本信息和部门用户 -->
							<!-- 部门名称、上级部门 start -->
							<div class="row">
								<div class="col-sm-5">
									<div id="authorityCodeGroup" class="form-group">
										<label class="col-sm-3 control-label">部门名称</label>
										<div class="col-sm-9">
											<input type="text" id="deptName" name="deptName" value="${dept.deptName }"
												class="form-control input-sm" placeholder="请输入部门名称" disabled="disabled" />
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

							<!-- 备注信息 start -->
							<h5 class="page-header alert-info" style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
							<div class="row">
								<div class="col-sm-10">
									<div class="form-group">
										<label class="col-sm-3 control-label">备注信息</label>
										<div class="col-sm-9">
											<textarea class="form-control" rows="3" cols="50" id="deptDescr" name="deptDescr"
												placeholder="请填写部门备注信息" disabled="disabled">${dept.deptDescr }</textarea>
										</div>
									</div>
								</div>
							</div>
							<!-- 备注信息 end -->

							<h5 class="page-header alert-info" style="padding: 10px; margin: 0px; margin-bottom: 5px;">部门员工</h5>
							
							<!-- 添加按钮触发模态框：显示所属部门的用户信息 -->
							<input id="showDetail" type="button" class="btn btn-success" value="查看部门员工">
							<!--第1层：模态框的声明-->
					        <div class="modal fade" id="showDeptEmployee">
					        	<!--第2层：窗口的声明-->
					        	<div class="modal-dialog">
					        		<!--第3层：内容的声明-->
					        		<div class="modal-content">
					        			<!--内容声明的标题-->
					        			<div class="modal-header">
					        				<button class="close" data-dismiss="modal"><span>&times;</span></button>
					        				<h4 class="modal-title">查看部门员工信息</h4>
					        			</div>
					        			<!--内容声明的body-->
					        			<div class="modal-body">
					        				<!-- <div class="container-fluid">放置一行
					        					<div class="row">
									    			<input type="text" id="addDeptName" name="addDeptName" placeholder="请填写部门名称"  />
									    		</div>
					        					<div>&nbsp;</div>
					        				</div> -->
					        				<!-- table table-bordered table-striped table-responsive -->
					        				<div class="table-responsive">
											  <table class="table table-bordered table-hover">
											    <caption>${dept.deptName }-部门员工信息一览</caption>
											    <thead>
											      <tr class="success">
											      	<th>员工信息</th>
											        <th>员工姓名</th>
											        <th>联系方式</th>
											        <th>电子邮箱</th>
											       </tr>
											    </thead>
											    <tbody>
											    	<c:forEach var="user" items="${list }">
											    		<!-- active\success\warning\danger -->
												    	<tr>
												    		<td><input type="checkbox" name="userIds" value="${user.userId }" /></td>
													   		<td>${user.userName }</td>
													        <td>${user.phone }</td>
													        <td>${user.email }</td></tr>
														<tr>
											    	</c:forEach>
											    </tbody>
											  </table>
											</div>
					        			</div>
					        			<!--内容声明的footer-->
					        			<div class="modal-footer">
					        				<input id="removeEmpFromDept" type="button" class="btn btn-sm btn-success" value="移除员工" >
					        				<button id="closeModal" type="button" class="btn btn-sm btn-default">关闭</button>
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

	<!-- 封装页面部门员工信息:实现部门用户信息修改  -->
	<script type="text/javascript">
		// 点击按钮触发模态框显示当前部门对应信息
		$("#showDetail").click(function() {
			$("#showDeptEmployee").modal('show');
		});
	
		$("#closeModal").click(function() {
			$("#showDeptEmployee").modal('hide');
		});
		
		// 移除员工到指定部门(修改员工信息列表)
		$("#removeEmpFromDept").click(function() {
			console.log('remove emp from dept');// 控制台打印信息
			var ensure = window.confirm('确认移除选择的员工?');
			if(!ensure){
				return ;
			}
			// 获取当前选择的员工信息列表,修改员工信息
			var selectUserIds = document.getElementsByName("userIds");
			check_val = [];
		    for(k in selectUserIds){
		        if(selectUserIds[k].checked)
		            check_val.push(selectUserIds[k].value);
		    }
		    if(check_val.length==0){
		    	alert('请至少选择一条数据.');
		    	return ;
		    }
		    // alert(check_val);
			var submitData = {
				"userIds":check_val,
				"deptId":"-1"
			}
			console.log(submitData);
			// 调用接口更新用户信息
			$.ajax({
				url : '${pageContext.request.contextPath }/portal/organization/updateUserFromDept',
				type : 'POST',
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(submitData),
				success : function(data) {
					// alert(data.result_code);
					if (data.result_code == "1") {
						// 员工信息更新成功
						alert('服务器反馈^_^:部门员工移除成功!');
						// 刷新页面信息
						// window.location.reload();
					}
				},
				error : function(data) {
					// alert(data.result_code);
					console.log(data);
					alert('服务器反馈^_^:服务器无响应，请联系管理员!');
				}
			});
			// 操作完成隐藏模态框
			$("#showDeptEmployee").modal('hide');
		});
 	</script>

</body>
</html>
