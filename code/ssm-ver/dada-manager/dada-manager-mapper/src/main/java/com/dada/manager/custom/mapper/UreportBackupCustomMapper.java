package com.dada.manager.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dada.pojo.UreportBackup;

// 平台公共报表存储器
public interface UreportBackupCustomMapper {

	/**
	 *  根据报表名称检查报表是否存在
	 * @param fileName 报表名称
	 * @return
	 */
	public int checkExistByName(@Param(value="fileName")String fileName);
	
	/**
	 *  根据报表名称查询报表
	 * @param fileName 报表名称
	 * @return
	 */
	public UreportBackup queryUreportBackupByName(@Param(value="fileName")String fileName);
	
	/**
	 * 查询全部报表(所述机构对应所有报表)
	 * @return 
	 */
	public List<UreportBackup> queryReportBackupList();
	
	/**
	 * 根据报表名称删除报表
	 * @param name
	 * @param categoryId 机构id
	 * @return
	 */
	public int deleteReportBackupByName(@Param(value="fileName")String name);
	
	
	/**
	 *  保存报表
	 */
	public int insertReportBackup(UreportBackup ureportBackup);
	
	/**
	 *  更新报表
	 * @param ureportBackup
	 * @return
	 */
	public int updateReportBackup(UreportBackup ureportBackup);
}
