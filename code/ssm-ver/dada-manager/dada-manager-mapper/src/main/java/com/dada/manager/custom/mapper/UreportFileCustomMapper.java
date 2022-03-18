package com.dada.manager.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.pojo.UreportFile;

/**
 * @author hahabibu
 * 系统名称: 
 * 模块名称: 
 * 类 名 称: UreportStorageCustomMapper
 * 软件版权: 淘淘商城
 * 功能说明：
 * 系统版本：
 * 开发时间: create in 2019年3月6日 下午2:34:26
 * 开发说明: 
 */

// 机构用户报表存储器
public interface UreportFileCustomMapper {
	
	/**
	 *  根据报表名称检查报表是否存在(匹配当前登录用户所属机构id)
	 * @param fileName 报表名称
	 * @param categoryId 机构id
	 * @return
	 */
	public int checkExistByName(@Param(value="fileName")String fileName,@Param(value="categoryId")String categoryId);
	
	/**
	 *  根据报表名称查询报表
	 * @param fileName 报表名称
	 * @param categoryId 机构id
	 * @return
	 */
	public UreportFile queryUreportFileByName(@Param(value="fileName")String fileName,@Param(value="categoryId")String categoryId);
	
	/**
	 * 查询全部报表(所述机构对应所有报表)
	 * @param categoryId 机构id
	 * @return 
	 */
	public List<UreportFile> queryReportFileList(String categoryId);
	
	/**
	 * 根据报表名称删除报表
	 * @param name
	 * @param categoryId 机构id
	 * @return
	 */
	public int deleteReportFileByName(@Param(value="fileName")String name,@Param(value="categoryId")String categoryId);
	
	/**
	 *  保存报表
	 */
	public int insertReportFile(UreportFile ureportFile);
	
	/**
	 *  更新报表
	 * @param ureportFile
	 * @return
	 */
	public int updateReportFile(UreportFile ureportFile);
	
	/**
	 * listUreportFileByPage:根据筛选条件分页列出报表信息
	 * @param page
	 * @return  List<PageData>
	 */
    public List<PageData> listUreportFileByPage(Page page);

}
