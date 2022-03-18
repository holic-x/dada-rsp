// 初始化的时候加载数据
function loadData(){
	loadProvince();
}

/*地址数据加载*/
	
		function loadProvince(){
			// 加载省份
			var saveProvince = ["浙江省","广东省","山东省","江苏省","福建省","甘肃省"];
			// 获取指定的表单中的下拉列表
			var province = document.forms["my-form"].province;
			// 依次加载数据
			for(var i=0;i<saveProvince.length;i++){
				// 创建新的节点并设置相关的属性，挂载节点到指定的位置
				var op = document.createElement("option");
				op.value = saveProvince[i];
				op.textContent = saveProvince[i];
				province.appendChild(op);
			}
		}
	/*将每个省份对应的城市用二维数组进行联系*/
		var saveCity = [
			["杭州市","温州市","宁波市","嘉兴市"],
			["广州市","深圳市","惠州市","潮州市"],
			["济南市","青岛市","潍坊市","济宁市"],
			["南京市","苏州市","南通市","常州市"],
			["厦门市","福州市","龙岩市","福安市"],
			["兰州市","敦煌市","定西市","白银市"]
		];
		/*当选择了相应的省份相应修改城市选择*/
		function getCity(){
			// 获取指定的表单
			var form = document.forms["my-form"];
			// 1.获取当前用户选择的省份的索引号(注意索引号要与数组下标一一对应)
			var selectProvinceIndex = form.province.selectedIndex-1;
			var selectProvinceCity = saveCity[selectProvinceIndex];
			// 2.获取城市信息，对城市进行刷新（先清空数据，后添加数据）
			var city = form.city;
			// 清空数据，保留第一行
			city.length = 1;
			// 3.根据索引查找的城市信息依次添加数据
			for(var i=0;i<selectProvinceCity.length;i++){
				var newOption = new Option(selectProvinceCity[i],selectProvinceCity[i]);
				city[city.length] = newOption;
			}
		}
		
		
		// 根据提交的数据进行字符串的拼接
		/**
		 * 定义表单提交的时候触犯的方法
		 * 处理数据：年月日拼接、preference数据转化为字符串
		 * 利用dom的相关知识点插入两个节点，设置相应的属性，供
		 * 相应的servlet进行访问
		 */
		function doSubmit(){
			makeAddr();
		}
		
		function makeAddr(){
			 var province =document.getElementById("province").value;
			 var city =document.getElementById("city").value;
			 var addr =province+"-"+city ;
			 // 创建节点
			 var input =document.createElement("input");//<input/>
			 input.type="hidden";
			 input.name="address";
			 input.value=addr; //<input type="hidden" value="......"/>
			 alert(addr);
			 // 插入节点
			 document.getElementById("my-form").appendChild(input);
		}
		
		
