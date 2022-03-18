package com.dada.report.datasource;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bstek.ureport.definition.datasource.BuildinDatasource;
import com.dada.common.constant.ConstantUtils;
import com.dada.config.DataSourceConfig;
import com.dada.pojo.UserInfo;
import com.dada.report.filter.CustomHolder;
import com.dada.report.service.DataSourceService;
import com.dada.report.utils.JDBCUtils;


@Controller
@RequestMapping("/dataSource")
// 机构自定义数据源信息（从机构用户的数据源配置中获取）
public class CustomDataSource implements BuildinDatasource {
	private static final String NAME = "机构首选数据源";
	private Logger log = LoggerFactory.getLogger(CustomDataSource.class);
	
	//@Autowired
	//private DataSource dataSource;
	
	@Autowired
    private DataSourceService dataSourceService;
	
	/**
	 * 获取数据源连接
	 */
	@Override
	public Connection getConnection() {
		// 借助ThreadLocal获取当前用户登录信息,从而获取相应用户配置的数据源信息
	    // 考虑数据的维护,针对数据源所属是公用的,归属于机构
	    // UserInfo loginUser = (UserInfo)UserSession.getAttribute("loginUser");
	    UserInfo loginUser = CustomHolder.getCurrentUser();
	    String username = null;
        String password = null;
        String driver = null;
        String url = null;
	    if(loginUser!=null) {
	        // 根据当前登录用户获取相应所属机构的数据源配置
	        String categoryId = loginUser.getCategoryId();
	        DataSourceConfig dataSourceConfig = dataSourceService.getCategoryDataSource(categoryId,ConstantUtils.DATA_SOURCE_PREFER_STATE_FIRST);
	        if(dataSourceConfig!=null) {
	            username = dataSourceConfig.getUsername();
	            password = dataSourceConfig.getPassword();
	            driver = dataSourceConfig.getDriver();
	            url = dataSourceConfig.getUrl();
	        }
	    }
	    
		// 模拟从数据库中获取数据,获取相应的数据库连接
		// String username = "root";
		// String password = "root";
		// String driver = "com.mysql.jdbc.Driver";
		// String url = "jdbc:mysql://localhost:3306/dada-report";
		// 自定义创建数据源连接(借助JDBCUtils获取数据源)
		return JDBCUtils.getConnection(username, password, driver, url);
	}

	/**
	 * 获取数据源名称
	 */
	@Override
	public String name() {
		return NAME;
	}
}
