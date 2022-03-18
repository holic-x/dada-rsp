/* function loadjscssfile(filename, filetype) {
         if (filetype == "js") { //判定文件类型
             var fileref = document.createElement('script')//创建标签
             fileref.setAttribute("type", "text/javascript")//定义属性type的值为text/javascript
             fileref.setAttribute("src", filename)//文件的地址
         }
         else if (filetype == "css") { //判定文件类型
             var fileref = document.createElement("link")
             fileref.setAttribute("rel", "stylesheet")
             fileref.setAttribute("type", "text/css")
             fileref.setAttribute("href", filename)
         }
         if (typeof fileref != "undefined")
             document.getElementsByTagName("head")[0].appendChild(fileref)
     } */

	 /*
	 * 动态菜单封装的方式:
	 1.考虑通过后台传入map类型参数,jsp页面接收通过c:foreach标签进行封装
	 2.考虑通过json异步请求参数,通过js封装数据
	 */
	 // $(document).ready(function(){
		 
	 $(function() {
		// 根据当前用户权限获取菜单信息(为避免数据样式渲染失败,设置ajax请求为同步,即待ajax请求完成方渲染页面)
	 	/* 
	 	$.ajaxSettings.async = false;
		$.get("${pageContext.request.contextPath}/portal/common/listMenu",
			function(result) {
				// 处理数据
			} 
		*/
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
					// 封装格式
					/*
					<li>
						<a href="#">
							<i class="fa fa-wrench fa-fw"></i>平台案例
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li>
								<a href="${pageContext.request.contextPath }/manager/page/other?url=">示例1</a>
							</li>
						</ul> 
					</li>
					*/
					/* for (var i = 0; i < menuList.length; i++) {
						// flag为0标识菜单可用 ,为1标识菜单隐藏
						var flag = 0 ;
						// 拼接字符串数据
						// 判断根据父子菜单有不同的显示效果(此处默认均有父子菜单关系)
						var str = '<li> <a href="#"><i class="'+menuList[i].authorityIcon+'"></i>'
							+ menuList[i].authorityName + '<span class="fa arrow"></span></a>';
						// 判断当前父节点的子节点信息是否为空(如果为空则默认不添加当前节点信息)
						var leafMenuList = menuList[i].leafMenuList;
						if((leafMenuList != null) && (leafMenuList.length != 0)){
							// 遍历子节点数据信息继续拼接数据
							for(var j = 0; j<leafMenuList.length; j++){
								str += '<ul class="nav nav-second-level"><li><a href="'
									+leafMenuList[j].authorityUrl+'">'+leafMenuList[j].authorityName
									+'</a></li></ul>';
							}
						}else{
							flag = 1;
						}
						// 最后拼接数据
						str += '</li>';
						// 打印封装是数据测试
						// console.log(str);
						alert(str);
						// 如果标识为true,添加菜单信息
						 if(flag==0){
							$("#navMenu").append(str);
						}  
						// $("#side-menu").append(str);
					} */
					
					
					// 存在问题,拼接出来的html字符串片段样式失效,点击无响应
					// 其次,href设置如果直接设置为'localhost:8080/...',点击无反应
					// 设置格式为https://localhost:8080/portal/system/roleInfo
					// 或者是借助${pageContext.request.contextPath}完成url拼接
					// 方式2：
					var htmlStr = '';
					for (var i = 0; i < menuList.length; i++) {
						// 判断父节点是否存在子节点信息(如果存在,显示数据,反之隐藏当前父节点)
						var leafMenuList = menuList[i].leafMenuList;
						if((leafMenuList != null) && (leafMenuList.length != 0)){
							// 封装父子节点属性
							// 判断根据父子菜单有不同的显示效果(此处默认均有父子菜单关系)
							var str = '<li> <a href="#"><i class="'+menuList[i].authorityIcon+'"></i>'
								+ menuList[i].authorityName + '<span class="fa arrow"></span></a>';
							// 遍历子节点数据信息继续拼接数据
							for(var j = 0; j<leafMenuList.length; j++){
								str += '<ul class="nav nav-second-level"><li><a href="'
									+leafMenuList[j].authorityUrl+'">'+leafMenuList[j].authorityName
									+'</a></li></ul>';
							}
							// 最后拼接数据
							str += '</li>';
							// 打印封装数据进行测试
							// console.log(str);
							alert(str);
							// 拼接总字符串
							htmlStr += str;
						}else{
							// 跳出当次循环
							break;
						}
					}
					// 指定位置插入html片段(after追加文本)
					// $("#side-menu").html(htmlStr);
					 $("#side-menu").append(htmlStr);
					// $.parser.parse('#side-menu'); 
					// $.parser.parse();
				} else {
					alert('服务器访问错误!');
				}
			}
		});
	});