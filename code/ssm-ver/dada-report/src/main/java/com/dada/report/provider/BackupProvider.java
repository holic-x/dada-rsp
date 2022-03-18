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
import com.dada.manager.custom.mapper.UreportBackupCustomMapper;
import com.dada.pojo.UreportBackup;

// 平台备用报表文件存储器
public class BackupProvider implements ReportProvider{
	private static final String NAME = "哒哒公共报表存储器";
	
    // 特定前缀，ureport底层会调用 getPrefix 方法来获取报表操作的Provier类
	private String prefix = "dada-public:";
	
	// 是否禁用
	private boolean disabled; 

	@Autowired
	private UreportBackupCustomMapper ureportBackupCustomMapper;
	
	@Override
	public InputStream loadReport(String file) {
		// 根据报表名称获取报表信息(dada公共仓库)
		UreportBackup ureportBackup = ureportBackupCustomMapper.queryUreportBackupByName(getCorrectName(file));  
	    byte[] content = ureportBackup.getFileContent();  
		ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
		return inputStream;
	}
 
	@Override
	public void deleteReport(String file) {
		ureportBackupCustomMapper.deleteReportBackupByName(getCorrectName(file));
	}
 
	@Override
	public List<ReportFile> getReportFiles() {  
		List<UreportBackup> list = ureportBackupCustomMapper.queryReportBackupList();
		List<ReportFile> reportList = new ArrayList<>();
		for (UreportBackup ureportBackup : list) {
			reportList.add(new ReportFile(ureportBackup.getFileName(), ureportBackup.getModifyTime()));
		}
		return reportList;
	}
 
	@Override
	public void saveReport(String file, String content) {
		file = getCorrectName(file);
		UreportBackup ureportBackup = ureportBackupCustomMapper.queryUreportBackupByName(file);
		Date currentDate = new Date();
		if(ureportBackup == null){
			ureportBackup = new UreportBackup();
			// 设置默认属性
			ureportBackup.setFileId(IDUtils.genRandomUUId());
			ureportBackup.setFileDescr(ConstantUtils.REPORT_FILE_DEFAULT_DESCR);
			ureportBackup.setCreateTime(currentDate);
			ureportBackup.setModifyTime(currentDate);
			ureportBackup.setPublicState(ConstantUtils.REPORT_FILE_PUBLIC_STATUS_PRIVATE);
			ureportBackup.setVisibleState(ConstantUtils.REPORT_FILE_VISABLE_STATUS_FALSE);
			// 设置报表名称、报表内容
			ureportBackup.setFileName(file);
			ureportBackup.setFileContent(content.getBytes());
			ureportBackupCustomMapper.insertReportBackup(ureportBackup);
		}else{
			ureportBackup.setFileContent(content.getBytes());
   			ureportBackup.setModifyTime(currentDate);
   			ureportBackupCustomMapper.updateReportBackup(ureportBackup);
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
		return name; 
	}

}
 