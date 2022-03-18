<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 

<title>公有的分页组件</title>
</head>
<body>
	<div class="container">
		<nav aria-label="Page navigation">
 			当前是第[${page.pagenum }]页
			<ul class="pagination pagination-md">
				<!-- 判断当前是否为第1页，如果是则上一页不显示 -->
				<c:if test="${page.pagenum>1 }">
					<li>		
						<a href="${page.url}?pagenum=${page.pagenum-1}">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:if>
				<!-- 根据传递的参数显示相应位置的页数 -->
				<c:forEach var="pagenum" begin="${page.startpage }" end="${page.endpage }">
					<li>
			 			<a href="${page.url}?pagenum=${pagenum}">${pagenum}</a>
			 				</li>
				 </c:forEach>	
				<!-- 判断当前页数是否为最后一页，如果是则最后一页不显示 -->
				<c:if test="${page.pagenum<page.totalpage }">
					<li>
						<a href="${page.url}?pagenum=${page.pagenum+1}">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
				&nbsp;
				<li>
					<input type="text" id="pagenum" style="width: 40px;height:35px;"/>
					<input type="button" value="跳转" class="btn btn-md btn-success" onclick="goWhich(document.getElementById('pagenum'))">
				</li>
			</ul>
		</nav>
		当前共有[${page.totalpage }]页,共[${page.totalrecord }]记录.
		<script type="text/javascript">
			// 定义方法验证输入的时候为有效数字，如果是则跳转到指定的位置
			function goWhich(input) {
				var pagenum=input.value; 
				if(pagenum==null || pagenum==""){
					alert("请输入页码!!");
					return;
				}
				if(!pagenum.match("\\d+")){
					alert("请输入数字!!");
					input.value="";
					return;
				}	 
				if(pagenum<1 || pagenum> (${page.totalpage })){
					alert("无效的页码！！");
					input.value="";
					return;
				}
				window.location.href="${page.url}?pagenum="+pagenum;
			}
		</script>
	</div>
</body>
</html>