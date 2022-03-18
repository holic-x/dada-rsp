<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

<title>首页</title>
<%@include file="/public/index.jspf" %>
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> 
					<small> 
						<i class="icon-leaf"></i>哒哒报表管理平台
					</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<!-- 导航栏顶部-右侧菜单栏 -->
			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<!-- 消息记录(下拉框) -->
					<li class="green">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
							<i class="icon-envelope icon-animated-vertical"></i> 
							<span class="badge badge-success">5</span>
						</a>
						<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header">
								<i class="icon-envelope-alt"></i>5条未读消息
							</li>
						</ul>
					</li>
					<!-- 用户个人资料(设置)下拉框 -->
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
							<img class="nav-user-photo" src="${pageContext.request.contextPath }/resource/assets/avatars/user.jpg" alt="头像" /> 
							<span class="user-info">
								<small>用户名称</small>
							</span>
							<i class="icon-caret-down"></i>
						</a>
						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li>
								<a href="#" target="mainframe">
									<i class="icon-user"></i>个人资料
								</a>
							</li>
							<!-- 分隔线 -->
							<li class="divider"></li>
							<!-- 登录退出 -->
							<li>
								<a href="login.html">
									<i class="icon-off"></i>登录退出
								</a>
							</li>
						</ul>
					</li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> 
				<span class="menu-text"></span>
			</a>
			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>
				<!-- 左侧导航栏对应按钮(分别对应不同的功能信息) -->
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>
					<!-- 菜单隐藏后缩小的按钮信息 -->
					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> 
						<span class="btn btn-info"></span>
						<span class="btn btn-warning"></span> 
						<span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->

				<!-- 导航栏 -->
				<ul class="nav nav-list">
					<!-- 控制台 -->
					<li class="active">
						<a href="${pageContext.request.contextPath }/view/bootm.jsp" target="mainframe"> 
							<i class="icon-dashboard"></i> 
							<span class="menu-text">控制台</span>
						</a>
					</li>
					
					<!-- 封装菜单信息 -->
					<!-- 完整的菜单信息参考 -->
					<li>
						<!-- 父级菜单数据封装 -->
						<a href="javascript:void(0)" target="mainframe" class="dropdown-toggle"> 
							<i class="icon-desktop"></i> 
							<span class="menu-text">平台案例</span> 
							<b class="arrow icon-angle-down"></b>
						</a>
						<!-- 对应子级菜单数据封装 -->
						<ul class="submenu">
							<li>
								<a href="${pageContext.request.contextPath }/system/.." target="mainframe">
									<i class="icon-double-angle-right"></i>案例1
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/system/.." target="mainframe">
									<i class="icon-double-angle-right"></i>案例2
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/system/.." target="mainframe">
									<i class="icon-double-angle-right"></i>案例3
								</a>
							</li>
						</ul>
					</li>
					
					<!-- 
						为解决数据局部刷新与页面刷新(js、css)渲染的冲突,此处获取用户菜单信息不是通过ajax进行异步请求,
						即当页面js、css数据加载完成时,ajax请求却还未完成,此处通过ajax相应拼接或封装的html片段无法进行渲染,
						如果是EasyUI或其他的框架则可以调用相应的方法进行强制渲染,此处针对bootstrap框架还没有了解到有效的解决
						方案,可以考虑通过dom操纵节点,或是由后台分装数据转发到页面再进行处理
						
						为了避免用户每请求一次页面就封装一次菜单信息,导致页面数据响应缓慢,此处不沿用后台管理框架,参考以往框架,在点击
						相应模块页面的时候,在当前页面中提供空白区域供子页面展示,除非用户手动刷新页面,否则不会请求菜单刷新
						
						借助后台通过model或request请求封装参数到前台页面中获取,再通过c:foreach标签遍历封装菜单信息
						(间接实现用户的权限管理)--此处暂不支持权限覆盖到按钮,仅仅针对某个模块进行权限验证、设置
					 -->
					<!-- 请求后台数据,封装数据信息(格式参考如下所示) -->
					<%-- 
					<li>
						<!-- 父级菜单数据封装 -->
						<a href="#" target="mainframe" class="dropdown-toggle"> 
							<i class="icon-desktop"></i> 
							<span class="menu-text">menuName</span> 
							<b class="arrow icon-angle-down"></b>
						</a>
						<!-- 对应子级菜单数据封装 -->
						<ul class="submenu">
							<!-- 嵌套c:foreach封装子级菜单 -->
							<li>
								<a href="${pageContext.request.contextPath }/system/.." target="mainframe">
									<i class="icon-double-angle-right"></i>案例1
								</a>
							</li>
						</ul>
					</li>
					--%>
					<c:forEach var="parentMenu" items="${menuList }">
						<!-- 判断父级菜单下是否有子菜单,如果存在子菜单方封装数据,否则默认隐藏数据 -->					
						<c:if test="${parentMenu.leafMenuList!=null && parentMenu.leafMenuList.length!=0 }">
							<li>
								<!-- 父级菜单数据封装 -->
								<a href="#" target="mainframe" class="dropdown-toggle"> 
									<i class="${parentMenu.authorityUrl }"></i> 
									<span class="menu-text">${parentMenu.authorityName }</span> 
									<b class="arrow icon-angle-down"></b>
								</a>
								<!-- 对应子级菜单数据封装 -->
								<ul class="submenu">
									<!-- 嵌套c:foreach遍历子节点信息 -->
									<c:forEach var="leafMenu" items="${parentMenu.leafMenuList }">
										<li>
											<a href="${leafMenu.authorityUrl }" target="mainframe">
												<i class="icon-double-angle-right"></i>${leafMenu.authorityName }
											</a>   
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>
						 
				</ul>
				<!-- /.nav-list -->

				<!-- 隐藏导航栏 -->
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>

			<!-- 页面整体布局 -->
			<div class="main-content" id="mains">
				<iframe id="mainframe" name="mainframe" 
					src="${pageContext.request.contextPath }/view/bootm.jsp"
					style="width: 100%; border: 0px;"> 
				</iframe>
			</div>

			<script type="text/javascript">
				var height = jQuery(window).height() - 50;
				jQuery("#mainframe").attr("height", "" + height + "px;");
			</script>

			<div class="ace-settings-container" id="ace-settings-container">
				<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
					<i class="icon-cog bigger-150"></i>
				</div>

				<div class="ace-settings-box" id="ace-settings-box">
					<!-- 页面样式选择 -->
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; 选择皮肤</span>
					</div>
					
					<!-- 页面基本设置 -->
					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" /> 
						<label class="lbl" for="ace-settings-navbar">固定导航条</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" /> 
						<label class="lbl" for="ace-settings-sidebar">固定滑动条</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
						<label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
						<label class="lbl" for="ace-settings-rtl">切换到左侧</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
						<label class="lbl" for="ace-settings-add-container">切换窄屏
							<b></b>
						</label>
					</div>
				</div>
			</div>
			<!-- /#ace-settings-container -->
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<"+"script>");
	</script>
	
	<!-- page specific plugin scripts -->
	<!--
		[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]
	-->

	<!-- ace scripts -->
	<!-- inline scripts related to this page -->

	<script type="text/javascript">
		jQuery(function($) {
			$('.easy-pie-chart.percentage')
					.each(
							function() {
								var $box = $(this).closest('.infobox');
								var barColor = $(this).data('color')
										|| (!$box.hasClass('infobox-dark') ? $box
												.css('color')
												: 'rgba(255,255,255,0.95)');
								var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
										: '#E2E2E2';
								var size = parseInt($(this).data('size')) || 50;
								$(this)
										.easyPieChart(
												{
													barColor : barColor,
													trackColor : trackColor,
													scaleColor : false,
													lineCap : 'butt',
													lineWidth : parseInt(size / 10),
													animate : /msie\s*(8|7|6)/
															.test(navigator.userAgent
																	.toLowerCase()) ? false
															: 1000,
													size : size
												});
							})

			$('.sparkline').each(
					function() {
						var $box = $(this).closest('.infobox');
						var barColor = !$box.hasClass('infobox-dark') ? $box
								.css('color') : '#FFF';
						$(this).sparkline('html', {
							tagValuesAttribute : 'data-values',
							type : 'bar',
							barColor : barColor,
							chartRangeMin : $(this).data('min') || 0
						});
					});

			var placeholder = $('#piechart-placeholder').css({
				'width' : '90%',
				'min-height' : '150px'
			});
			var data = [ {
				label : "social networks",
				data : 38.7,
				color : "#68BC31"
			}, {
				label : "search engines",
				data : 24.5,
				color : "#2091CF"
			}, {
				label : "ad campaigns",
				data : 8.2,
				color : "#AF4E96"
			}, {
				label : "direct traffic",
				data : 18.6,
				color : "#DA5430"
			}, {
				label : "other",
				data : 10,
				color : "#FEE074"
			} ]
			function drawPieChart(placeholder, data, position) {
				$.plot(placeholder, data, {
					series : {
						pie : {
							show : true,
							tilt : 0.8,
							highlight : {
								opacity : 0.25
							},
							stroke : {
								color : '#fff',
								width : 2
							},
							startAngle : 2
						}
					},
					legend : {
						show : true,
						position : position || "ne",
						labelBoxBorderColor : null,
						margin : [ -30, 15 ]
					},
					grid : {
						hoverable : true,
						clickable : true
					}
				})
			}
			drawPieChart(placeholder, data);

			/**
			we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			so that's not needed actually.
			 */
			placeholder.data('chart', data);
			placeholder.data('draw', drawPieChart);

			var $tooltip = $(
					"<div class='tooltip top in'><div class='tooltip-inner'></div></div>")
					.hide().appendTo('body');
			var previousPoint = null;

			placeholder.on('plothover', function(event, pos, item) {
				if (item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : "
								+ item.series['percent'] + '%';
						$tooltip.show().children(0).text(tip);
					}
					$tooltip.css({
						top : pos.pageY + 10,
						left : pos.pageX + 10
					});
				} else {
					$tooltip.hide();
					previousPoint = null;
				}

			});

			var d1 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.5) {
				d1.push([ i, Math.sin(i) ]);
			}

			var d2 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.5) {
				d2.push([ i, Math.cos(i) ]);
			}

			var d3 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.2) {
				d3.push([ i, Math.tan(i) ]);
			}

			var sales_charts = $('#sales-charts').css({
				'width' : '100%',
				'height' : '220px'
			});
			$.plot("#sales-charts", [ {
				label : "Domains",
				data : d1
			}, {
				label : "Hosting",
				data : d2
			}, {
				label : "Services",
				data : d3
			} ], {
				hoverable : true,
				shadowSize : 0,
				series : {
					lines : {
						show : true
					},
					points : {
						show : true
					}
				},
				xaxis : {
					tickLength : 0
				},
				yaxis : {
					ticks : 10,
					min : -2,
					max : 2,
					tickDecimals : 3
				},
				grid : {
					backgroundColor : {
						colors : [ "#fff", "#fff" ]
					},
					borderWidth : 1,
					borderColor : '#555'
				}
			});

			$('#recent-box [data-rel="tooltip"]').tooltip({
				placement : tooltip_placement
			});
			function tooltip_placement(context, source) {
				var $source = $(source);
				var $parent = $source.closest('.tab-content')
				var off1 = $parent.offset();
				var w1 = $parent.width();

				var off2 = $source.offset();
				var w2 = $source.width();

				if (parseInt(off2.left) < parseInt(off1.left)
						+ parseInt(w1 / 2))
					return 'right';
				return 'left';
			}

			$('.dialogs,.comments').slimScroll({
				height : '300px'
			});

			//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
			//so disable dragging when clicking on label
			var agent = navigator.userAgent.toLowerCase();
			if ("ontouchstart" in document && /applewebkit/.test(agent)
					&& /android/.test(agent))
				$('#tasks').on('touchstart', function(e) {
					var li = $(e.target).closest('#tasks li');
					if (li.length == 0)
						return;
					var label = li.find('label.inline').get(0);
					if (label == e.target || $.contains(label, e.target))
						e.stopImmediatePropagation();
				});

			$('#tasks').sortable({
				opacity : 0.8,
				revert : true,
				forceHelperSize : true,
				placeholder : 'draggable-placeholder',
				forcePlaceholderSize : true,
				tolerance : 'pointer',
				stop : function(event, ui) {//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
					$(ui.item).css('z-index', 'auto');
				}
			});
			$('#tasks').disableSelection();
			$('#tasks input:checkbox').removeAttr('checked').on('click',
					function() {
						if (this.checked)
							$(this).closest('li').addClass('selected');
						else
							$(this).closest('li').removeClass('selected');
					});

		})
	</script>
</body>
</html>

