<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>

	<%@include file="/public/index.jspf" %>
	
</head>
<!-- 首页显示主页面 -->
<body>
	
	<div style="padding: 0px; margin: 0px;">
		<ul class="breadcrumb" style="padding: 0px; padding-left: 20px;">
			<li><a href="#">哒哒报表平台</a></li>
			<li>主页面</li>
		</ul>
	</div>

	<div class="row " style="padding: 1px; margin: 0px;">
	
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-road"></span>&nbsp;图1
				</div>
				<div class="panel-body">
					<!-- 图表数据 -->
					<img src="${pageContext.request.contextPath }/static/img/report_exam_1.png" width="100%" />
				</div>
			</div>
		</div>
		
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-signal"></span>&nbsp;报表案例-机构招录审核统计
				</div>
				<table class="table table-bordered">
					<tr>
						<th colspan="4">机构招录审核统计</th>
					</tr>
					
					<tr>
						<th>招录机关</th>
						<th>招考人数</th>
						<th>合格人数</th>
						<th>竞争比</th>
					</tr>

					<tr>
						<td>国家税务总局山东省税务局</td>
						<td>180</td>
						<td>23050</td>
						<td>128.06</td>
					</tr>
					
					<tr>
						<td>水利部黄河水利委员会</td>
						<td>55</td>
						<td>6508</td>
						<td>118.33</td>
					</tr>
					
					<tr>
						<td>济南铁路公安局</td>
						<td>49</td>
						<td>5811</td>
						<td>118.59</td>
					</tr>
					
					<tr>
						<td>国家税务总局青岛市税务局</td>
						<td>70</td>
						<td>5305</td>
						<td>75.79</td>
					</tr>
					
					<tr>
						<td>国家统计局山东调查总队</td>
						<td>60</td>
						<td>2320</td>
						<td>38.67</td>
					</tr>
					
					<tr>
						<td>山东气象局</td>
						<td>31</td>
						<td>1371</td>
						<td>44.23</td>
					</tr>
					
					<tr>
						<td>中国银行保险监督管理委员会山东监管局</td>
						<td>41</td>
						<td>1293</td>
						<td>31.54</td>
					</tr>
					
					<tr>
						<td>青岛海关</td>
						<td>16</td>
						<td>944</td>
						<td>59.00</td>
					</tr>
					
					<tr>
						<td>山东海事局</td>
						<td>60</td>
						<td>719</td>
						<td>11.98</td>
					</tr>
					
					<tr>
						<td>水利部海河水利委员会</td>
						<td>12</td>
						<td>649</td>
						<td>54.08</td>
					</tr>
				</table>
			</div>
		</div>
		
		<!-- 
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>招生线索来源图
				</div>
				<div class="panel-body">
					<img src="${pageContext.request.contextPath }/static/img/02.png" width="100%" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>招生线索来源图
				</div>
				<div class="panel-body">
					<img src="${pageContext.request.contextPath }/static/img/02.png" width="100%" />
				</div>
			</div>
		</div>
		 -->
		 <!-- 
		<div class="col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>&nbsp;今日联系
				</div>
				<table class="table table-bordered">
					<tr>
						<th>联系人</th>
						<th>电话号码</th>

					</tr>
					<tr>
						<td>张三</td>
						<td>1522355214</td>
					</tr>
				</table>
			</div>
		</div>
		 -->
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-flag"></span>&nbsp;今日联系
				</div>
				<table class="table table-bordered">
					<tr>
						<th>联系人</th>
						<th>电话号码</th>

					</tr>
					<tr>
						<td>张三</td>
						<td>1522355214</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>&nbsp;公告
				</div>
				<div class="panel-body">哒哒报表服务平台-公告</div>
			</div>
		</div>
	</div>

</body>
</html>
