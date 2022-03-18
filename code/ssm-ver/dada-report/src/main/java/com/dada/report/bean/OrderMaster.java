package com.dada.report.bean;
/**
 * @author hahabibu
 * 系统名称: 
 * 模块名称: 
 * 类 名 称: OrderMaster
 * 软件版权: 淘淘商城
 * 功能说明：
 * 系统版本：
 * 开发时间: create in 2019年3月12日 下午12:59:26
 * 开发说明: 
 */
// 订单主表介绍
public class OrderMaster {

	private String master_id; // 订单主表id
	private String order_num; // 订单编号
	private String customer_name; // 对应顾客(顾客名称)
	private String handle_time; // 订单处理时间
	private String handle_status; // 订单处理状态
	
	public OrderMaster() {
		super();
	}
	
	public OrderMaster(String master_id, String order_num, String customer_name, String handle_time,
			String handle_status) {
		super();
		this.master_id = master_id;
		this.order_num = order_num;
		this.customer_name = customer_name;
		this.handle_time = handle_time;
		this.handle_status = handle_status;
	}

	public String getMaster_id() {
		return master_id;
	}
	public void setMaster_id(String master_id) {
		this.master_id = master_id;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getHandle_time() {
		return handle_time;
	}
	public void setHandle_time(String handle_time) {
		this.handle_time = handle_time;
	}
	public String getHandle_status() {
		return handle_status;
	}
	public void setHandle_status(String handle_status) {
		this.handle_status = handle_status;
	}
	@Override
	public String toString() {
		return "OrderMaster [master_id=" + master_id + ", order_num=" + order_num + ", customer_name=" + customer_name
				+ ", handle_time=" + handle_time + ", handle_status=" + handle_status + "]";
	}
	
	public String toInsertString() {
		return "insert into order_master values('" + master_id + "', '" + order_num + "', '" + customer_name
				+ "', '" + handle_time + "', '" + handle_status + "');";
	}
	
	
}
