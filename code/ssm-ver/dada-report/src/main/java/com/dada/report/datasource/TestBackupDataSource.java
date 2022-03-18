package com.dada.report.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bstek.ureport.definition.datasource.BuildinDatasource;

public class TestBackupDataSource implements BuildinDatasource {
	private static final String NAME = "平台测试内置数据源(备份)";
	private Logger log = LoggerFactory.getLogger(TestDataSource.class);
	
	// @Autowired(byType:配置多个相同数据源连接采用该注解会报错)
	@Resource(name="backupDataSource")
	private DataSource dataSource;

	/**
	 * 获取数据源连接
	 */
	@Override
	public Connection getConnection() {
		// 自定义创建数据源连接(借助JDBCUtils获取数据源)
		// return JDBCUtils.getConnection();
		try {
			// 通过spring配置的dataSource对象获取数据库连接
			return dataSource.getConnection();
		} catch (SQLException e) {
			log.error("Ureport 数据源 获取连接失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取数据源名称
	 */
	@Override
	public String name() {
		return NAME;
	}
}
