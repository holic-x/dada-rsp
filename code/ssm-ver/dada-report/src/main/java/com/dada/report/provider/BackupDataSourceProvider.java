package com.dada.report.provider;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.dada.common.constant.ConstantUtils;
import com.dada.common.utils.IDUtils;
import com.dada.manager.custom.mapper.UreportDataSourceBackupCustomMapper;
import com.dada.pojo.UreportDataSourceBackup;

// 平台备用报表文件存储器
public class BackupDataSourceProvider implements ReportProvider{
	private static final String NAME = "哒哒公共数据源配置文件存储器";
	
    // 特定前缀，ureport底层会调用 getPrefix 方法来获取报表操作的Provier类
	private String prefix = "dada-public-datasource:";

	// 是否禁用
	private boolean disabled; 

	@Autowired
	private UreportDataSourceBackupCustomMapper ureportDataSourceBackupCustomMapper;
	
	@Override
	public InputStream loadReport(String file) {
		// 根据报表名称获取报表信息(dada公共仓库)
		UreportDataSourceBackup ureportDataSourceBackup = ureportDataSourceBackupCustomMapper.queryUreportDataSourceBackupByName(getCorrectName(file));  
	    byte[] content = ureportDataSourceBackup.getFileContent();  
		ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
		return inputStream;
	}
 
	@Override
	public void deleteReport(String file) {
		ureportDataSourceBackupCustomMapper.deleteReportBackupByName(getCorrectName(file));
	}
 
	@Override
	public List<ReportFile> getReportFiles() {  
		List<UreportDataSourceBackup> list = ureportDataSourceBackupCustomMapper.queryReportBackupList();
		List<ReportFile> reportList = new ArrayList<>();
		for (UreportDataSourceBackup ureportDataSourceBackup : list) {
			reportList.add(new ReportFile(ureportDataSourceBackup.getFileName(), ureportDataSourceBackup.getModifyTime()));
		}
		return reportList;
	}
 
	@Override
	public void saveReport(String file, String content) {
		file = getCorrectName(file);
		UreportDataSourceBackup ureportDataSourceBackup = ureportDataSourceBackupCustomMapper.queryUreportDataSourceBackupByName(file);
		Date currentDate = new Date();
		if(ureportDataSourceBackup == null){
			ureportDataSourceBackup = new UreportDataSourceBackup();
			// 设置默认属性
			ureportDataSourceBackup.setFileId(IDUtils.genRandomUUId());
			ureportDataSourceBackup.setFileDescr(ConstantUtils.DATA_SOURCE_FILE_DEFAULT_DESCR);
			ureportDataSourceBackup.setCreateTime(currentDate);
			ureportDataSourceBackup.setModifyTime(currentDate);
			ureportDataSourceBackup.setPublicState(ConstantUtils.DATA_SOURCE_FILE_PUBLIC_STATUS_PRIVATE);
			ureportDataSourceBackup.setVisibleState(ConstantUtils.DATA_SOURCE_FILE_VISABLE_STATUS_FALSE);
			// 设置报表名称、报表内容
			ureportDataSourceBackup.setFileName(file);
			ureportDataSourceBackup.setFileContent(content.getBytes());
			ureportDataSourceBackupCustomMapper.insertReportBackup(ureportDataSourceBackup);
		}else{
			ureportDataSourceBackup.setFileContent(content.getBytes());
   			ureportDataSourceBackup.setModifyTime(currentDate);
   			ureportDataSourceBackupCustomMapper.updateReportBackup(ureportDataSourceBackup);
		}
	}
 
	@Override
	public String getName() {
		return NAME;
	}
 
	@Override
	public boolean disabled() {
		return disabled;
	}
 
	@Override
	public String getPrefix() {
		return prefix;
	}
 
	/**
	 * 获取没有前缀的文件名
	 * @param name
	 * @return
	 */
	private String getCorrectName(String name){
		if(name.startsWith(prefix)){
			name = name.substring(prefix.length(), name.length());
		}
		// 解决传入的file名称中文乱码问题
        try {
            name = new String(name.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		return name; 
	}

}
 