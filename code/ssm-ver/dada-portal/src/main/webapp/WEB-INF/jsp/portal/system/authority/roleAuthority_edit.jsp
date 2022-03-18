<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
				<h1 class="page-header">&nbsp;角色权限变更</h1>
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
							<li><a href="#">权限管理</a></li>
							<li>角色权限信息</li>
						</ul>
					</div>

					<div class="panel-body">
						<form id="submitForm" class="form-horizontal" action="#">
							<h5 class="page-header alert-info" style="padding: 10px; margin: 0px; margin-bottom: 5px;">菜单信息</h5>
							<!-- 封装隐藏属性 -->
							<input type="hidden" id="categoryId" name="categoryId" value="${loginUser.categoryId }" />
							<input type="hidden" id="roleId" name="roleId" value="${roleId }" />
							
							<table class="table">
								<tr>
									<th>父菜单</th>
									<th>子菜单</th>
								</tr>
				
								<c:forEach var="menuVO" items="${menuVOList }">
									<tr>
										<td>${menuVO.authorityName }</td>
										<td>
											<c:forEach var="leafMenu" items="${menuVO.leafMenuList }">
												<c:if test="${menuVO.leafMenuList!=null && fn:length(menuVO.leafMenuList)!=0 }">
													<label class="checkbox-inline"> 
														<input type="checkbox" 
															<c:forEach var="j" begin="0" end="${fn:length(roleAuthorityList)}" step="1">  
																		${roleAuthorityList[j].authorityId==leafMenu.authorityId?'checked':'' }
															</c:forEach>
														id="authorityIds" name="authorityIds" value="${leafMenu.authorityId }">${leafMenu.authorityName }
													</label>
												</c:if>
											</c:forEach>
										</td>
									</tr>
								</c:forEach>
							</table>

							<div class="row">
								<div class="col-sm-3 col-sm-offset-4">
									<!-- <input type="submit" class="btn btn-success" value="保存" />  -->
									<input id="submitButton" type="submit" class="btn btn-success" value="保存" /> 
									<!-- <input type="reset" class="btn btn-grey" value="取消" /> --> 
									<a class="btn btn-warning" href="${pageContext.request.contextPath }/portal/page/other?url=portal/system/authority/roleAuthority_list">返回上一级</a>
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
    // 如果是submit则直接验证，如果是button类型则调用验证
    $("#submitButton").click(function() {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
      	// 提交表单角色,禁用提交按钮,防止表单重复提交
     	$('#submitButton').attr("disabled", true);
    	// 遍历封装checkbox选中属性
    	var obj = document.getElementsByName("authorityIds");
    	// 封装数据
    	var check_obj = [];
    	for(var k in obj){
    		if(obj[k].checked){
    			check_obj.push(obj[k].value);
    		}
    	}
    	// alert(check_obj);
    	
     	// 1.封装页面提交
         var submitData = {
       		"roleId" : $("#roleId").val(),
       		"categoryId" : $("#categoryId").val(),
     		// "authorityIds" : $("#authorityIds").val()
     		"authorityIds" : check_obj
       	};

   		// 2.请求服务器传送json数据
     	// url : contentPath + '/admin/system/roleInfo/addRole',
    	$.ajax({
       		url :'${pageContext.request.contextPath}/portal/system/updateRoleAuthority',
          	type : 'POST',
          	contentType : 'application/json; charset=UTF-8',
         	async : false,
         	dataType : 'json',
          	data : JSON.stringify(submitData),
          	success : function(responseData) {
       			console.log(responseData);
          		var result_code = responseData.result_code;
             	if (result_code == "1") {
               		// 返回状态码为1，角色权限访问成功
                 	alert('操作反馈^_^:角色权限修改成功,正在返回页面!');
                  	// 如果角色修改成功跳转到list页面
                  	var url = "${pageContext.request.contextPath }/portal/page/other?url=portal/system/authority/roleAuthority_list";
                 	window.location.href = url;
              	} else {
               		alert('服务器反馈^_^:服务器访问错误!');
            	}
          	}
      	});
 	});
</script>
</body>

</html>
