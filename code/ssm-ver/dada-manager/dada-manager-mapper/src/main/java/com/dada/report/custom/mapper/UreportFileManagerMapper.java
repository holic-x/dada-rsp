package com.dada.report.custom.mapper;

import java.util.List;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.pojo.UreportFileManager;


/**
 * <p>项目名称:dada-manager-mapper</p>
 * <p>包名称:com.dada.report.custom.mapper.UreportFileManagerMapper</p>
 * <p>文件名称:UreportFileManagerMapper.java</p>
 * <p>功能描述: Ureport报表存储器(通用管理mapper)</p>
 * <p>其他说明: 根据传入不同的表名操作对应的数据
 *      机构相关存储器：ureport_file、ureport_data_source_file、
 *      平台公共存储器：ureport_backup、ureport_data_source_backup
 * </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年4月6日上午11:21:39 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public interface UreportFileManagerMapper {
	
	/**
	 * checkExistByName:检查对应筛选条件下报表存储器是否存在,tableName为必选项
	 * @author thanos
	 * @param searchParam 筛选参数
	 * @return  <br/>
	 * @since JDK 1.8
	 */
	public int checkExistByName(UreportFileSearchParam searchParam);
	
	/**
	 * queryUreportFileByName:根据报表名称查询报表,tableName为必选项
	 * @author thanos
	 * @param searchParam 筛选参数
	 * @return  <br/>
	 * @since JDK 1.8
	 */
	public UreportFileManager queryUreportFileByName(UreportFileSearchParam searchParam);
	
	/**
	 * queryReportFileList:对应筛选条件下查询全部报表(对应存储器所有报表信息)
	 * @author thanos
	 * @param searchParam 筛选参数
	 * @return  <br/>
	 * @since JDK 1.8
	 */
	public List<UreportFileManager> queryReportFileList(UreportFileSearchParam searchParam);
	
	/**
	 * deleteReportFileByName:根据报表名称删除报表(指定相应筛选条件-报表存储器)
	 * @author thanos
	 * @param searchParam 筛选参数
	 * @return  <br/>
	 * @since JDK 1.8
	 */
	public int deleteReportFileByName(UreportFileSearchParam searchParam);
	
	/**
	 * insertReportFile:保存报表至指定报表存储器(tableName、ureportFileManager必须指定)
	 * @author thanos
	 * @param searchParam 筛选参数
	 * @return  <br/>
	 * @since JDK 1.8
	 */
	public int insertReportFile(UreportFileSearchParam searchParam);
	
	/**
	 * updateReportFile:更新指定报表存储器中的报表信息(tableName、ureportFileManager必须指定)
	 * @author thanos
	 * @param searchParam
	 * @return  <br/>
	 * @since JDK 1.8
	 */
	public int updateReportFile(UreportFileSearchParam searchParam);
	
	/**
	 * listUreportFileByPage:根据筛选条件分页列出报表信息
	 * @param page
	 * @return  List<PageData>
	 */
    public List<PageData> listUreportFileByPage(Page page);
    
    /**
     * deleteUreportFile:根据fileId删除指定存储器对应的文件信息(需指定tableName、fileId)
     * @author thanos
     * @param fileId
     * @return  <br/>
     * @since JDK 1.8
     * (指定fileId可等价于categoryId、deptId、fileName联查)
     */
    public int deleteUreportFileById(UreportFileSearchParam searchParam);
    
    /**
     * getUreportFile:根据fileId获取指定存储器对应的文件信息(需指定tableName、fileId)
     * @author thanos
     * @param fileId
     * @return  <br/>
     * @since JDK 1.8
     * (指定fileId可等价于categoryId、deptId、fileName联查)
     */
    public UreportFileManager getUreportFileById(UreportFileSearchParam searchParam);

    /**
     * listReportFileByNoClassify:根据指定筛选条件查找尚未当前机构指定部门尚未进行分类的报表信息
     * @author thanos
     * @param searchParam
     * @return  <br/>
     * @since JDK 1.8
     */
    public List<UreportFileManager> listReportFileByNoClassify(UreportFileSearchParam searchParam);
}
