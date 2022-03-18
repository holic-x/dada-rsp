<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE>

<%-- <script src="${pageContext.request.contextPath }/resource/custom/common/js/loadMenu.js"></script> --%>
<script type="text/javascript">
 
/*
* 动态菜单封装的方式:
1.考虑通过后台传入map类型参数,jsp页面接收通过c:foreach标签进行封装
2.考虑通过json异步请求参数,通过js封装数据
*/

// $(document).ready(function(){
$(function() {
// 根据当前用户权限获取菜单信息(为避免数据样式渲染失败,设置ajax请求为同步,即待ajax请求完成方渲染页面)
	$.ajax({
		// url : '${pageContext.request.contextPath}/portal/common/listMenu',
		url : 'http://localhost:8082/portal/common/listMenu',
		type : 'GET',
		async : false,
		success : function(result) {	
			console.log(result);
			var result_code = result.result_code;
			if (result_code == "1") {
				// 返回状态码为1，菜单访问成功
				alert('访问菜单!');
				// 封装菜单信息
				var menuList = result.data.dataList;
				// 如果菜单信息为空,显示默认的平台数据(其次提示用户无权限)
				if((menuList==null)||(menuList.length==0)){
					alert('当前用户还没有足够的平台权限,请联系相应管理员进行处理!');
				}
			} else {
				alert('服务器访问错误!');
			}
		}
	});
});
</script>


<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.html">哒哒报表服务平台</a>
	</div>
	<!-- /.navbar-header -->
	<ul class="nav navbar-top-links navbar-right">
		<!-- 消息记录 -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-messages">
				<li><a href="#">
						<div>
							<strong>John Smith</strong> <span class="pull-right text-muted">
								<em>Yesterday</em>
							</span>
						</div>
						<div>Lorem ipsum dolor sit amet, consectetur adipiscing
							elit. Pellentesque eleifend...</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<strong>John Smith</strong> <span class="pull-right text-muted">
								<em>Yesterday</em>
							</span>
						</div>
						<div>Lorem ipsum dolor sit amet, consectetur adipiscing
							elit. Pellentesque eleifend...</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<strong>John Smith</strong> <span class="pull-right text-muted">
								<em>Yesterday</em>
							</span>
						</div>
						<div>Lorem ipsum dolor sit amet, consectetur adipiscing
							elit. Pellentesque eleifend...</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>Read
							All Messages</strong> <i class="fa fa-angle-right"></i>
				</a></li>
			</ul> <!-- /.dropdown-messages --></li>
		<!-- /.dropdown -->

		<!-- 任务进度 -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-tasks">
				<li><a href="#">
						<div>
							<p>
								<strong>Task 1</strong> <span class="pull-right text-muted">40%
									Complete</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%">
									<span class="sr-only">40% Complete (success)</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<p>
								<strong>Task 2</strong> <span class="pull-right text-muted">20%
									Complete</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
									style="width: 20%">
									<span class="sr-only">20% Complete</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<p>
								<strong>Task 3</strong> <span class="pull-right text-muted">60%
									Complete</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-warning"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" style="width: 60%">
									<span class="sr-only">60% Complete (warning)</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<p>
								<strong>Task 4</strong> <span class="pull-right text-muted">80%
									Complete</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
									style="width: 80%">
									<span class="sr-only">80% Complete (danger)</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>See
							All Tasks</strong> <i class="fa fa-angle-right"></i>
				</a></li>
			</ul> <!-- /.dropdown-tasks --></li>
		<!-- /.dropdown -->

		<!-- 弹框提醒 -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-alerts">
				<li><a href="#">
						<div>
							<i class="fa fa-comment fa-fw"></i> New Comment <span
								class="pull-right text-muted small">4 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
								class="pull-right text-muted small">12 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<i class="fa fa-envelope fa-fw"></i> Message Sent <span
								class="pull-right text-muted small">4 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<i class="fa fa-tasks fa-fw"></i> New Task <span
								class="pull-right text-muted small">4 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
								class="pull-right text-muted small">4 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>See
							All Alerts</strong> <i class="fa fa-angle-right"></i>
				</a></li>
			</ul> <!-- /.dropdown-alerts --></li>
		<!-- /.dropdown -->

		<!-- 个人中心 -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#"><i class="fa fa-user fa-fw"></i>用户资料</a></li>
				<li><a href="#"><i class="fa fa-gear fa-fw"></i> 基本设置</a></li>
				<li class="divider"></li>
				<li><a
					href="${pageContext.request.contextPath }/manager/user/logout.action"><i
						class="fa fa-sign-out fa-fw"></i> 登录注销</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>

	<!-- 脚注 -->
	<!-- /.navbar-top-links -->
	<!-- <div class="chs">
		Collect from <a href="http://www.cssmoban.com/">网页模板</a>
	</div> -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<!-- 查找框 -->
				<li class="sidebar-search">
					<div class="input-group custom-search-form">
						<input type="text" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div> <!-- /input-group -->
				</li>

				<!-- 首页展示 -->
				<li>
					<a href="index.html">
						<i class="fa fa-dashboard fa-fw"></i>
						平台首页
					</a>
				</li>
				<!-- 报错：属性值request.getAttribute("menuList")引用”必须在值内使用时进行转义 -->
				<!-- 如果c:foreach中遍历的集合为空,则编译页面不会显示相关的标签 -->
				<%-- <c:forEach var="parentMenu" items="<%=request.getAttribute("menuList") %>"> --%>
				<c:forEach var="parentMenu" items="${menuList }">
				<!-- 是不行的，因为ajax运行完时，页面被加载为html到浏览器中，所以c:foreach中没有值 -->
					<li> 
						<a href="#">
							<i class="fa fa-wrench fa-fw"></i>${parentMenu.authorityName }
							<span class="fa arrow"></span>
						</a>
						<c:if test="${menuList.leafMenuList!=null && menuList.leafMenuList.length!=0}">
							<%-- <c:forEach var="leafMenu" items="${menuList.leafMenuList }">
								<ul class="nav nav-second-level">
									<li>
										<a href="${leafMenu.authorityUrl }">${leafMenu.authorityName }</a>
									</li>
								</ul>
							</c:forEach> --%>
						</c:if>
					</li>
					
					<li> 
						<a href="#">
							<i class="fa fa-wrench fa-fw"></i>
							系统管理
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li>
								<a href="localhost:8082/portal/system/roleInfo">角色管理</a>
							</li>
						</ul>
					</li>
				</c:forEach>
				
				<!-- 从数据库动态获取的菜单 START -->
			 
				 <!-- 
				 <li> 
					<a href="#">
						<i class="fa fa-wrench fa-fw"></i>
						系统管理
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="localhost:8082/portal/system/roleInfo">角色管理</a>
						</li>
					</ul>
				</li>
				  -->
				<!-- 从数据库动态获取的菜单 END -->

				 
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>


