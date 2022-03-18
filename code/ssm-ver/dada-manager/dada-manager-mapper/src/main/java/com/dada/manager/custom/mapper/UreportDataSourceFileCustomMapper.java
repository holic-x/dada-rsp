package com.dada.manager.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dada.pojo.UreportDataSourceFile;


// 机构用户数据源配置文件存储器
public interface UreportDataSourceFileCustomMapper {
	
	/**
	 *  根据报表名称检查数据源配置文件是否存在(匹配当前登录用户所属机构id)
	 * @param fileName 数据源配置文件名称
	 * @param categoryId 机构id
	 * @return
	 */
	public int checkExistByName(@Param(value="fileName")String fileName,@Param(value="categoryId")String categoryId);
	
	/**
	 *  根据数据源配置文件名称查询数据源配置文件
	 * @param fileName 数据源配置文件名称
	 * @param categoryId 机构id
	 * @return
	 */
	public UreportDataSourceFile queryUreportDataSourceFileByName(@Param(value="fileName")String fileName,@Param(value="categoryId")String categoryId);
	
	/**
	 * 查询全部数据源配置文件(所述机构对应所有数据源配置文件)
	 * @param categoryId 机构id
	 * @return 
	 */
	public List<UreportDataSourceFile> queryReportFileList(String categoryId);
	
	/**
	 * 根据数据源配置文件名称删除数据源配置文件
	 * @param name
	 * @param categoryId 机构id
	 * @return
	 */
	public int deleteReportFileByName(@Param(value="fileName")String name,@Param(value="categoryId")String categoryId);
	
	/**
	 *  保存数据源配置文件
	 */
	public int insertReportFile(UreportDataSourceFile ureportDataSourceFile);
	
	/**
	 *  更新数据源配置文件
	 * @param ureportDataSourceFile
	 * @return
	 */
	public int updateReportFile(UreportDataSourceFile ureportDataSourceFile);

}
