package com.dada.portal.constant;
/**
 * @author hahabibu
 * 系统名称: 
 * 模块名称: 
 * 类 名 称: DataCheck
 * 软件版权: 淘淘商城
 * 功能说明：
 * 系统版本：
 * 开发时间: create in 2019年2月22日 下午2:53:44
 * 开发说明: 
 */
public enum DataCheck {
	
	// 用户校验字段
    USER_EMAIL("email"),
    USER_LOGIN_ACCOUNT("loginAccount"),
    CATEGORY_CODE("categoryCode");
	
    private final String dataField;

    /**
     * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
     */
    private DataCheck(String dataField) {
        this.dataField = dataField;
    }

	public String getDataField() {
		return dataField;
	}

}
