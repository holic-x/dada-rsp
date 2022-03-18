package com.dada.report.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dada.common.utils.CommonDataGenerator;
import com.dada.report.bean.OrderDetail;
import com.dada.report.bean.OrderMaster;
import com.dada.report.bean.Product;

// 对象数据生成器
public class BeanDataGenerator {

	// 定义product列表
	// private static List<Product> products = new ArrayList<>(100);
	private static Product[] products = {
			new Product("c9458bd95b144853a8cf9f3db7fdf03f", "0653985620", "清扬洗发水", 35.50, "瓶/支", "北京朝阳", "2019/01/01", "去屑用清扬，更自信", "美乐家日用品（中国）有限公司"),
			new Product("d8478ab4e9654af2b32da719087cdea2", "3567643447", "爱马仕箱包", 299.90, "个", "上海浦东", "2018/01/01", "爱在每时每分每秒，爱马仕,生活充满传奇!装的下,世界就是你的!","美乐家日用品（中国）有限公司"),
			new Product("e1cf8247178e400fbf68270aa695866f", "8061215435", "纳爱斯香皂", 9.90, "个", "上海浦东", "2018/01/01", "啦啦啦", "美乐家日用品（中国）有限公司"),
			new Product("65e13ada22ea47729353a487dd299945", "0382900318", "可比克薯片", 6.90, "包", "哈尔滨", "2018/07/01", "薯我鲜,薯我辣,薯我脆,薯片可比克,片片都欢乐,我的选择可比克!",""),
			new Product("364108d902844c9384a539b5f67c7dc8", "2790390676", "老北京鸡爪", 1.90, "个", "福建福州", "2019/01/01", "美味无穷,辣爪天下,吃辣敢挑战吗,来只爪!", "浙江青莲食品股份公司"),
			new Product("55ff19e3df5049a78dd24804114209cb", "3390617614", "康师傅红烧牛肉面", 4.50, "个", "四川", "2018/01/01", "康师傅牛肉面,好吃看得见！","浙江青莲食品股份公司"),
			new Product("8298c8452a1248688e5cbfa058289bf8", "6670834570", "合味道（咖喱味）1*12规格", 59.90, "箱", "香港", "2018/01/01", "方便您的合味道,合您味道的方便面！", "浙江青莲食品股份公司"),
			new Product("75115f84d9da423f92d990f1d4a4379b", "0950903648", "鸡蛋雪糕", 3.90, "个/只", "山东", "2019/03/01", "总是在不经意的时候,给你带来一份最细致体贴的关怀！", "浙江青莲食品股份公司"),
			new Product("46e0ab7574fe42ecbc7838cff0e356ab", "5721574158", "唐僧肉（辣条）", 4.90, "包", "北京", "2018/01/01", "吃上一口唐僧肉,战胜上下五千年妖魔鬼怪！", "浙江青莲食品股份公司"),
			new Product("0527a136d3754fa4aeba3ef8bac38dff", "3525253773", "优悦矿泉水", 3.90, "瓶", "山东", "2018/01/01", "地下深水,喝的就是滋味", "华润怡宝饮料（中国）有限公司"),
			new Product("97efab7b824c4bde921a22aa27e495f2", "9781592342", "康师傅冰红茶", 3.90, "瓶", "长春", "2018/01/01", "冰力十足,无可替代! 传递品牌畅快,冰酷,乐趣的个性！", "华润怡宝饮料（中国）有限公司"),
			new Product("42f074b594b1464ba2c4f717328965ce", "9319884700", "晨光签字笔", 3.90, "只", "上海", "2013/01/20", "Forever Thinking,Forever Writting", "美乐家日用品（中国）有限公司"),
			new Product("0f5e6469bb654097b5c1f871ab084809", "5937237218", "快客钢笔", 69.90, "只", "北京", "2018/01/30", "钢笔小世界,书写大乾坤", "美乐家日用品（中国）有限公司"),
			new Product("fab01f7aa2274eb9994d69887608234d", "7967407523", "作业本", 3.90, "本", "山东", "2018/06/01", "这是你曾经使用过的最舒适的笔记本", "美乐家日用品（中国）有限公司"),
			new Product("1208de2d0c4d42d7b10c01c4f4da761f", "4725904945", "娃哈哈儿童裝", 399.90, "套", "北京", "2019/01/01", "快乐童年我最棒！", "浙江盛邦服饰有限公司"),
			new Product("2e22e515d0b346789082e6a1c9ebeec3", "4231099780", "snapback棒球帽", 39.90, "个", "广东深圳", "2018/01/01", "爱尚伊帽,时尚snapback棒球帽为您打造", "浙江盛邦服饰有限公司"),
			new Product("e6ab4a0d2b1447abbae44e24e06d9b6b", "5343381114", "盐焗鸡爪", 23.50, "包", "广东梅州", "2018/02/01", "盐焗鸡,好味道,少校老青都爱吃,吃后都说顶呱呱,顶呱呱,还能开胃呢!", "广东振成食品有限公司"),
			new Product("0bddf95f1d7d40849f251d7b8f169756", "8712472047", "香辣鸡爪", 24.50, "包", "广东梅州", "2018/09/01", "美食不可抵挡", "广东振成食品有限公司"),
			new Product("154767bd90f745d5816399bd2d941b14", "2693386899", "盐焗鸡翅", 26.50, "包", "广东梅州", "2019/01/20", "盐焗鸡,好味道,少校老青都爱吃,吃后都说顶呱呱,顶呱呱,还能开胃呢!", "广东振成食品有限公司"),
			new Product("376a14f67a924841acacf43f499d22ba", "0275773767", "ACER笔记本电脑", 4999.90, "台", "香港", "2019/01/01", "我信我选acer", "美乐家日用品（中国）有限公司")
	};
	
	// 定义订单处理状态
	private static String[] orderStatus = {"预订","完成","退货"};
	
	

	/**
	 * 获取商品信息(此处随机)
	 */
	public static List<Product> randomProduct() {
		List<Product> productList = new ArrayList<>();
		for(Product product : products) {
			productList.add(product);
		}
		return productList;
	}
	
	
	/**
	 * 获取start-end的随机整数
	 */
	public static int getNum(int start, int end) {
		// int b=(int)(Math.random()*10);//生成[0,9]之间的随机整数。
		// int temp=m+(int)(Math.random()*(n+1-m)); //生成从m到n的随机整数[m,n]
		// return (int) (Math.random() * (end - start + 1) + start);
		return start+(int)(Math.random()*(end+1-start));
	}
	
	
	/**
	 * 随机生成订单主表和相应的订单明细
	 */
	public static Map<String, Object> randomOrder(){
		Map<String, Object> order = new HashMap<>();
		// 随机生成订单主表id和相应的订单编号
		String masterId = CommonDataGenerator.randomId();
		String orderNum = CommonDataGenerator.random10num();
		// 随机获取相应的顾客名称、处理时间
		String customerName = CommonDataGenerator.randomChineseName();
		String handleTime = CommonDataGenerator.randomDay();
		// 随机获取订单状态
		String handleStatus = orderStatus[getNum(0, orderStatus.length-1)];
		// 生成订单主表信息
		OrderMaster orderMaster = new OrderMaster(masterId, orderNum, customerName, handleTime, handleStatus);
		// 生成相应的订单明细信息
		int orderDetailNum = getNum(1, 10);
		List<OrderDetail> orderDetailList = new ArrayList<>(orderDetailNum);
		// 考虑商品信息较少,极有可能获取到重复的商品信息,此处做去重处理
		List<String> record = new ArrayList<>(orderDetailNum);
		for(int i=0;i<orderDetailNum;i++) {
			// 随机获取商品信息
			Product product = products[getNum(0, products.length-1)];
			// 根据商品id判断当前记录列表中是否已添加了该商品的记录,如果已经添加则作去重处理(跳过当次循环)
			if(record.contains(product.getProd_id())) {
				break;
			}
			String master_id = masterId;
			String prod_id = product.getProd_id();
			int quantity = getNum(1, 10);
			double unit_price = product.getProd_price();
			DecimalFormat df=new DecimalFormat("#.00");
			double amount = Double.valueOf(df.format(unit_price * quantity));
			OrderDetail orderDetail = new OrderDetail(master_id, prod_id, quantity, unit_price, amount);
			orderDetailList.add(orderDetail);
			// 记录已添加的商品信息
			record.add(prod_id);
		}
		 order.put("master", orderMaster);
		 order.put("detail", orderDetailList);
		return order;
	}
	
	public static void testRandomProduct() {
		System.out.println("-- 产品数据导入");
		for(Product product : products) {
			System.out.println(product.toInsertString());
		}
		System.out.println();
	}

	public static void testRandomOrder() {
		for(int i=0;i<10;i++) {
			System.out.println("-- 订单主表、从表信息插入"+(i+1));
			Map<String, Object> order = randomOrder();
			OrderMaster orderMaster = (OrderMaster) order.get("master");
			System.out.println(orderMaster.toInsertString());
			List<OrderDetail> orderDetailList = (List<OrderDetail>) order.get("detail");
			for(OrderDetail orderDetail : orderDetailList) {
				System.out.println(orderDetail.toInsertString());
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		testRandomProduct();
		testRandomOrder();
//		for(int i=0;i<1000;i++) {
//			System.out.println("第"+i+"个："+getNum(0, 10));
//		}
//		for(int i=0;i<1000;i++) {
//			System.out.println("第"+i+"个："+getNum(1, 10));
//		}
	}

}


/*
 * static { // 初始化商品的基本信息 // Product p = new Product(prod_id, flow_id,
 * prod_name, prod_price, prod_unit, prod_origin, prod_date, prod_descr,
 * vendor_name) Product product1 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "清扬洗发水", 35.50, "瓶/支", "北京朝阳",
 * "2018/01/01", "去屑用清扬，更自信", "美乐家日用品（中国）有限公司");
 * 
 * Product product2 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "爱马仕箱包", 299.90, "个", "上海浦东",
 * "2018/01/01", "爱在每时每分每秒，爱马仕,生活充满传奇!装的下,世界就是你的!","美乐家日用品（中国）有限公司");
 * 
 * Product product3 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "纳爱斯香皂", 9.90, "个", "上海浦东", "2018/01/01",
 * "去屑用清扬，更自信", "美乐家日用品（中国）有限公司");
 * 
 * Product product4 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "可比克薯片", 6.90, "包", "哈尔滨", "2018/07/01",
 * "薯我鲜,薯我辣,薯我脆,薯片可比克,片片都欢乐,我的选择可比克!","");
 * 
 * Product product5 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "老北京鸡爪", 1.90, "个", "福建福州", "2019/01/01",
 * "美味无穷,辣爪天下,吃辣敢挑战吗,来只爪!", "浙江青莲食品股份公司");
 * 
 * Product product6 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "康师傅红烧牛肉面", 4.50, "个", "四川", "2018/01/01",
 * "康师傅牛肉面,好吃看得见！","浙江青莲食品股份公司");
 * 
 * Product product7 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "合味道（咖喱味）1*12规格", 59.90, "箱", "香港",
 * "2018/01/01", "方便您的合味道,合您味道的方便面！", "浙江青莲食品股份公司");
 * 
 * Product product8 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "鸡蛋雪糕", 3.90, "个/只", "山东", "2018/01/01",
 * "总是在不经意的时候,给你带来一份最细致体贴的关怀！", "浙江青莲食品股份公司");
 * 
 * Product product9 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "唐僧肉（辣条）", 4.90, "包", "北京", "2018/01/01",
 * "吃上一口唐僧肉,战胜上下五千年妖魔鬼怪！", "浙江青莲食品股份公司");
 * 
 * Product product10 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "优悦矿泉水", 3.90, "瓶", "山东", "2018/01/01",
 * "地下深水,喝的就是滋味", "华润怡宝饮料（中国）有限公司");
 * 
 * Product product11 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "康师傅冰红茶", 3.90, "瓶", "长春", "2018/01/01",
 * "冰力十足,无可替代! 传递品牌畅快,冰酷,乐趣的个性！", "华润怡宝饮料（中国）有限公司");
 * 
 * Product product12 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "晨光签字笔", 3.90, "只", "上海", "2018/01/01",
 * "Forever Thinking,Forever Writting", "美乐家日用品（中国）有限公司");
 * 
 * Product product13 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "快客钢笔", 69.90, "只", "北京", "2018/01/01",
 * "钢笔小世界,书写大乾坤", "美乐家日用品（中国）有限公司");
 * 
 * Product product14 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "作业本", 3.90, "本", "山东", "2018/01/01",
 * "这是你曾经使用过的最舒适的笔记本", "美乐家日用品（中国）有限公司");
 * 
 * Product product15 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "娃哈哈儿童裝", 399.90, "套", "北京", "2018/01/01",
 * "快乐童年我最棒！", "浙江盛邦服饰有限公司");
 * 
 * Product product16 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "snapback棒球帽", 39.90, "个", "广东深圳",
 * "2018/01/01", "爱尚伊帽,时尚snapback棒球帽为您打造", "浙江盛邦服饰有限公司");
 * 
 * Product product17 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "盐焗鸡爪", 23.50, "包", "广东梅州", "2019/01/01",
 * "盐焗鸡,好味道,少校老青都爱吃,吃后都说顶呱呱,顶呱呱,还能开胃呢!", "广东振成食品有限公司");
 * 
 * 
 * Product product18 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "香辣鸡爪", 24.50, "包", "广东梅州", "2019/01/01",
 * "美食不可抵挡", "广东振成食品有限公司");
 * 
 * 
 * Product product19 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "盐焗鸡翅", 26.50, "包", "广东梅州", "2019/01/01",
 * "盐焗鸡,好味道,少校老青都爱吃,吃后都说顶呱呱,顶呱呱,还能开胃呢!", "广东振成食品有限公司");
 * 
 * 
 * Product product20 = new Product(CommonDataGenerator.randomId(),
 * CommonDataGenerator.random10num(), "ACER笔记本电脑", 4999.90, "台", "香港",
 * "2019/01/01", "我信我选acer", "美乐家日用品（中国）有限公司");
 * 
 * }
 */
