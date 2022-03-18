package com.dada.report.bean;
/**
 * @author hahabibu
 * 系统名称: 
 * 模块名称: 
 * 类 名 称: Product
 * 软件版权: 淘淘商城
 * 功能说明：
 * 系统版本：
 * 开发时间: create in 2019年3月12日 下午12:46:55
 * 开发说明: 
 */
// 商品明细(详细内容)
public class Product {
	
	private String prod_id; // 商品id
	private String flow_id; // 商品流水编号
	private String prod_name; // 商品名称
	private double prod_price; // 商品售价
	private String prod_unit; // 商品单位描述
	private String prod_origin;// 商品来源(产地)
	private String prod_date;// 商品生产日期
	private String prod_descr;// 商品描述
	private String vendor_name;// 商品供应商名称
	
	public Product() {
		super();
	}
	
	public Product(String prod_id, String flow_id, String prod_name, double prod_price, String prod_unit,
			String prod_origin, String prod_date, String prod_descr, String vendor_name) {
		super();
		this.prod_id = prod_id;
		this.flow_id = flow_id;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_unit = prod_unit;
		this.prod_origin = prod_origin;
		this.prod_date = prod_date;
		this.prod_descr = prod_descr;
		this.vendor_name = vendor_name;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getFlow_id() {
		return flow_id;
	}
	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public double getProd_price() {
		return prod_price;
	}
	public void setProd_price(double prod_price) {
		this.prod_price = prod_price;
	}
	public String getProd_unit() {
		return prod_unit;
	}
	public void setProd_unit(String prod_unit) {
		this.prod_unit = prod_unit;
	}
	public String getProd_origin() {
		return prod_origin;
	}
	public void setProd_origin(String prod_origin) {
		this.prod_origin = prod_origin;
	}
	public String getProd_date() {
		return prod_date;
	}
	public void setProd_date(String prod_date) {
		this.prod_date = prod_date;
	}
	public String getProd_descr() {
		return prod_descr;
	}
	public void setProd_descr(String prod_descr) {
		this.prod_descr = prod_descr;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	@Override
	public String toString() {
		return "Product [prod_id=" + prod_id + ", flow_id=" + flow_id + ", prod_name=" + prod_name + ", prod_price="
				+ prod_price + ", prod_unit=" + prod_unit + ", prod_origin=" + prod_origin + ", prod_date=" + prod_date
				+ ", prod_descr=" + prod_descr + ", vendor_name=" + vendor_name + "]";
	}
	
	public String toInsertString() {
		return "insert into product values('" + prod_id + "', '" + flow_id + "', '" + prod_name + "', '"
				+ prod_price + "','" + prod_unit + "', '" + prod_origin + "', '" + prod_date
				+ "', '" + prod_descr + "', '" + vendor_name + "');";
	}
	
}
