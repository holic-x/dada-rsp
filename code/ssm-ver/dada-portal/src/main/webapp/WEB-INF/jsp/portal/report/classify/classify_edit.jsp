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
	
</script>

<body>
	<div id="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<!-- 导航条 -->
					<div style="padding: 0px; margin: 0px;">
						<ul class="breadcrumb" style="margin: 0px;">
							<li><a href="#">报表管理</a></li>
							<li><a href="#">报表分类</a></li>
							<li>编辑报表分类信息</li>
						</ul>
					</div>
					<div class="panel-body">
						<form id="submitForm" class="form-horizontal" action="#">
							<h5 class="page-header alert-info" style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
							<!-- 回显报表分类信息以及对应分类下的报表数据 -->
							<input type="hidden" id="classifyId" name="classifyId" value="${reportClassify.classifyId }" />
							<input type="hidden" id="parentId" name="parentId" value="${reportClassify.parentId }" />
							<input type="hidden" id="createTime" name="createTime"
								value="<fmt:formatDate value="${reportClassify.createTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
							<input type="hidden" id="modifyTime" name="modifyTime"
								value="<fmt:formatDate value="${reportClassify.modifyTime }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
							<input type="hidden" id="delTag" name="delTag" value="${reportClassify.delTag }" />
							<input type="hidden" id="categoryId" name="categoryId" value="${reportClassify.categoryId }" />
							<input type="hidden" id="deptId" name="deptId" value="${reportClassify.deptId }" />
							
							<!-- 分类名称、上级分类 start -->
							<div class="row">
								<div class="col-sm-5">
									<div id="authorityCodeGroup" class="form-group">
										<label class="col-sm-3 control-label">分类名称</label>
										<div class="col-sm-9">
											<input type="text" id="classifyName" name="classifyName" value="${reportClassify.classifyName }"
												class="form-control input-sm" placeholder="请输入报表分名称" disabled="disabled" />
										</div>
									</div>
								</div>

								<div class="col-sm-5">
									<div class="form-group">
										<label class="col-sm-3 control-label">上级分类</label>
										<div class="col-sm-9">
											<c:if test="${reportClassify.parentId == '-1' }">
												<input type="text" value="无上级分类" class="form-control input-sm" disabled="disabled" />
											</c:if>
											<c:if test="${reportClassify.parentId != '-1' }">
												<input type="text" value="当前所属上级分类" class="form-control input-sm" disabled="disabled" />
											</c:if>
										</div>
									</div>
								</div>
							</div>
							<!-- 分类名称、上级分类 end -->

							<!-- 备注信息 start -->
							<h5 class="page-header alert-info" style="padding: 10px; margin: 0px; margin-bottom: 5px;">备注信息</h5>
							<div class="row">
								<div class="col-sm-10">
									<div class="form-group">
										<label class="col-sm-3 control-label">备注信息</label>
										<div class="col-sm-9">
											<textarea class="form-control" rows="3" cols="50" id="classifyDescr" name="classifyDescr"
												placeholder="请输入报表分类备注信息">${reportClassify.classifyDescr }</textarea>
										</div>
									</div>
								</div>
							</div>
							<!-- 备注信息 end -->
							
							<!-- 按钮操作 -->
							<div class="row">
								<div class="col-sm-3 col-sm-offset-4">
									<input id="submitButton" type="submit" class="btn btn-success" value="保存" /> 
									<input type="reset" class="btn btn-grey" value="取消" /> 
								</div>
							</div>
							
							<h5 class="page-header alert-info" style="padding: 10px; margin: 0px; margin-bottom: 5px;">报表信息</h5>
							
							<!-- 添加按钮触发模态框：显示当前所属分类的报表 -->
							<input id="showDetail" type="button" class="btn btn-success" value="查看当前分类报表信息">
							<!--第1层：模态框的声明-->
					        <div class="modal fade" id="showClassifyReport">
					        	<!--第2层：窗口的声明-->
					        	<div class="modal-dialog">
					        		<!--第3层：内容的声明-->
					        		<div class="modal-content">
					        			<!--内容声明的标题-->
					        			<div class="modal-header">
					        				<button class="close" data-dismiss="modal"><span>&times;</span></button>
					        				<h4 class="modal-title">查看指定分类的报表信息</h4>
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
											    <caption>${reportClassify.classifyName }-分类报表信息一览</caption>
											    <thead>
											      <tr class="success">
											      	<th>报表信息</th>
											      	<th>所属分类</th>
											        <th>报表名称</th>
											        <th>发布状态</th>
											       </tr>
											    </thead>
											    <tbody id="reportData">
											    	<%-- 
											    	<c:forEach var="user" items="${list }">
											    		<!-- active\success\warning\danger -->
												    	<tr>
												    		<td><input type="checkbox" name="userIds" value="${user.userId }" /></td>
													   		<td>${user.userName }</td>
														<tr>
											    	</c:forEach>
											    	--%>
											    </tbody>
											  </table>
											</div>
					        			</div>
					        			<!--内容声明的footer-->
					        			<div class="modal-footer">
					        				<input id="batchAppear" type="button" class="btn btn-sm btn-warning" value="批量上报" >
					        				<input id="batchHide" type="button" class="btn btn-sm btn-danger" value="批量隐藏" >
					        				<input id="removeReportFromClassify" type="button" class="btn btn-sm btn-success" value="移除报表" >
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
		// 点击按钮触发模态框显示当前分类对应报表信息
		var categoryId = '${sessionScope.loginUser.categoryId}';
		var deptId = '${sessionScope.loginUser.deptId}';
		$("#showDetail").click(function() {
			var submitData = {
				"categoryId":categoryId,
				"deptId":deptId,
				"classifyId":$("#classifyId").val()
			}
			// 请求接口获取报表数据并封装到页面指定位置
			$.ajax({
				url : '${pageContext.request.contextPath }/portal/report/manager/listLinkList',
				type : 'POST',
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(submitData),
				success : function(data) {
					// alert(data.result_code);
					if (data.result_code == "1") {
						// 获取当前分类报表信息
						var defaultList = data.data.data;
						// console.log(data);
						// console.log(defaultList);
						var htmlStr = "";
						for(var i=0;i<defaultList.length;i++){
							htmlStr += '<tr>'
								+'<td><input type="checkbox" id="linkIds" name="linkIds" value="'+defaultList[i].linkId+'"</td>'
								+'<td>'+defaultList[i].classifyName+'</td>'
								+'<td>'+defaultList[i].reportName+'</td>'
								+'<td>'+defaultList[i].hideStatus+'</td>'
								+'</tr>';
						}
						console.log(htmlStr);
						// 拼接前清空数据,再次拼接,否则重复点击会导致数据拼接重复
						// $('#box').html('');可能会导致内存泄漏
						$("#reportData").empty();
						$("#reportData").append(htmlStr);
						// 弹出模态框(show-弹出/hide-隐藏)
						$("#showNoClassifyReport").modal('show');
					}
				},
				error : function(data) {
					// alert(data.result_code);
					console.log(data);
					alert('服务器反馈^_^:服务器无响应，请联系管理员!');
				}
			});
			$("#showClassifyReport").modal('show');
		});
	
		$("#closeModal").click(function() {
			$("#showClassifyReport").modal('hide');
		});
		
		// 移除指定分类选择的报表信息
		$("#removeReportFromClassify").click(function() {
			console.log('remove report from classify');// 控制台打印信息
			var ensure = window.confirm('操作反馈^_^:确认移除当前分类下选择的报表信息?');
			if(!ensure){
				return ;
			}
			// 获取当前选择的报表信息列表,设置关联关系
			var selectLinkIds = document.getElementsByName("linkIds");
			check_val = [];
		    for(k in selectLinkIds){
		        if(selectLinkIds[k].checked)
		            check_val.push(selectLinkIds[k].value);
		    }
		    if(check_val.length==0){
		    	alert('友情提示^_^:请至少选择一条数据.');
		    	return ;
		    }
		    // alert(check_val);
			var submitData = {
				"operatorType":"2", // operatorType2标识移除分类报表信息
				"linkIds":check_val
			}
			console.log(submitData);
			// 调用接口更新用户信息
			$.ajax({
				url : '${pageContext.request.contextPath }/portal/report/manager/setReportClassifyLink',
				type : 'POST',
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(submitData),
				success : function(data) {
					// alert(data.result_code);
					if (data.result_code == "1") {
						// 员工信息更新成功
						alert('服务器反馈^_^:指定分类报表信息移除成功');
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
			$("#showClassifyReport").modal('hide');
		});
		
		
		// 批量设置报表hideStatus属性
		function setLinkHideStatus(hideStatus){
			console.log('batch set link hideStatus ');// 控制台打印信息
			var ensure = window.confirm('操作反馈^_^:确认修改当前选中报表信息.');
			if(!ensure){
				return ;
			}
			// 获取当前选择报表信息
			var selectLinkIds = document.getElementsByName("linkIds");
			check_val = [];
		    for(k in selectLinkIds){
		        if(selectLinkIds[k].checked)
		            check_val.push(selectLinkIds[k].value);
		    }
		    if(check_val.length==0){
		    	alert('友情提示^_^:请至少选择一条数据.');
		    	return ;
		    }
		    // alert(check_val);
			var submitData = {
				"hideStatus": hideStatus, // hideStatus为0标识上报、hideStatus为1标识隐藏
				"linkIds":check_val
			}
			console.log(submitData);
			// 调用接口更新用户信息
			$.ajax({
				url : '${pageContext.request.contextPath }/portal/report/manager/setLinkHideStatus',
				type : 'POST',
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(submitData),
				success : function(data) {
					// alert(data.result_code);
					if (data.result_code == "1") {
						// 员工信息更新成功
						alert('服务器反馈^_^:指定分类报表信息配置修改成功');
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
		}
		
		// 批量上报数据(供报表预览模块获取)
		$("#batchAppear").click(function() {
			console.log('batch appear report');// 控制台打印信息
			// 调用方法设置批量上报报表信息
			setLinkHideStatus('0');
			// 操作完成隐藏模态框
			$("#showClassifyReport").modal('hide');
		});
		
		// 批量隐藏数据
		$("#batchHide").click(function() {
			console.log('batch hide report');// 控制台打印信息
			// 调用方法设置批量上报报表信息
			setLinkHideStatus('1');
			// 操作完成隐藏模态框
			$("#showClassifyReport").modal('hide');
		});
		
		// 点击保存触发修改事件
		$("#submitButton").click(function() {
			// 封装提交数据
			// var categoryId = '${sessionScope.loginUser.categoryId}' ;
			// var deptId = '${sessionScope.loginUser.deptId}' ;
			var userId = '${sessionScope.loginUser.userId}' ;
			var submitData = {
				classifyId : $("#classifyId").val(),
				classifyName : classifyName ,
				parentId : $("#parentId").val() ,
				classifyDescr : $("#classifyDescr").val(),
				createTime : $("#createTime").val(),
				modifyTime : $("#modifyTime").val(),
				delTag : $("#delTag").val(),
				categoryId : $("#categoryId").val(),
				deptId : $("#deptId").val(),
				userId : userId, // $("#userId").val()
			};
			// alert(JSON.stringify(submitData));
			// 请求服务器执行添加操作
			$.ajax({
				url : '${pageContext.request.contextPath }/portal/report/manager/updateReportClassify',
				type : 'POST',
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(submitData),
				success : function(data) {
					// alert(data.result_code);
					if (data.result_code == "1") {
						alert('操作反馈^_^:报表分类信息修改成功.');
						// 添加完成后重新跳转到报表分类管理页面(刷新页面信息)
						var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/report/classify/classify_list";
						window.location.href = url;
					}
				},
				error : function(data) {
					// alert(data.result_code);
					console.log(data);
					alert('服务器反馈^_^:服务器无响应，请联系管理员!');
				}
			});
		});
 	</script>

</body>
</html>
