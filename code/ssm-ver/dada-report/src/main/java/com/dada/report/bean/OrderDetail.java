package com.dada.report.bean;
/**
 * @author hahabibu
 * 系统名称: 
 * 模块名称: 
 * 类 名 称: OrderDetail
 * 软件版权: 淘淘商城
 * 功能说明：
 * 系统版本：
 * 开发时间: create in 2019年3月12日 下午1:05:34
 * 开发说明: 
 */

// 订单明细
public class OrderDetail {

	private String master_id; // 明细对应主表id
	private String prod_id; // 商品id
	private int quantity; // 处理数量
	private double unit_price; // 商品单价
	private double amount; // 当前明细总额
	
	public OrderDetail() {
		super();
	}
	public OrderDetail(String master_id, String prod_id, int quantity, double unit_price, double amount) {
		super();
		this.master_id = master_id;
		this.prod_id = prod_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.amount = amount;
	}
	public String getMaster_id() {
		return master_id;
	}
	public void setMaster_id(String master_id) {
		this.master_id = master_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "OrderDetail [master_id=" + master_id + ", prod_id=" + prod_id + ", quantity=" + quantity
				+ ", unit_price=" + unit_price + ", amount=" + amount + "]";
	}
	public String toInsertString() {
		return "insert into order_detail values('" + master_id + "', '" + prod_id + "', '" + quantity
				+ "', '" + unit_price + "', '" + amount + "');";
	}
	
}
