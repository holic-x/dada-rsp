package com.dada.manager.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dada.pojo.UreportDataSourceBackup;

// 平台公共数据源配置文件存储器
public interface UreportDataSourceBackupCustomMapper {

	/**
	 *  根据数据源配置文件名称检查数据源配置文件是否存在
	 * @param fileName 数据源配置文件名称
	 * @return
	 */
	public int checkExistByName(@Param(value="fileName")String fileName);
	
	/**
	 *  根据数据源配置文件名称查询数据源配置文件
	 * @param fileName 数据源配置文件名称
	 * @return
	 */
	public UreportDataSourceBackup queryUreportDataSourceBackupByName(@Param(value="fileName")String fileName);
	
	/**
	 * 查询全部数据源配置文件(所述机构对应所有数据源配置文件)
	 * @return 
	 */
	public List<UreportDataSourceBackup> queryReportBackupList();
	
	/**
	 * 根据数据源配置文件名称删除数据源配置文件
	 * @param name
	 * @param categoryId 机构id
	 * @return
	 */
	public int deleteReportBackupByName(@Param(value="fileName")String name);
	
	
	/**
	 *  保存数据源配置文件
	 */
	public int insertReportBackup(UreportDataSourceBackup ureportDatasourceBackup);
	
	/**
	 *  更新数据源配置文件
	 * @param ureportDatasourceBackup
	 * @return
	 */
	public int updateReportBackup(UreportDataSourceBackup ureportDatasourceBackup);
}
