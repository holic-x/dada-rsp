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
import com.dada.manager.custom.mapper.UreportDataSourceFileCustomMapper;
import com.dada.pojo.UreportDataSourceFile;
import com.dada.pojo.UserInfo;

public class CustomDataSourceProvider implements ReportProvider{
	private static final String NAME = "机构数据源配置文件存储器";
	
    // 特定前缀，ureport底层会调用 getPrefix 方法来获取报表操作的Provier类
	private String prefix = "dada-mysql-custom-datasource:";
	
	// 是否禁用
	private boolean disabled; 

	private static UserInfo userInfo ;
	
	static {
		// userInfo = UserSession.getUser();
		// 定死数据先
		userInfo = new UserInfo();
		userInfo.setCategoryId("1");
	}
	
	@Autowired
	private UreportDataSourceFileCustomMapper ureportDataSourceFileCustomMapper;
	
	@Override
	public InputStream loadReport(String file) {
		String categoryId = userInfo.getCategoryId();
		// 根据报表名称、机构id获取报表信息
		UreportDataSourceFile ureportDataSourceFile = ureportDataSourceFileCustomMapper.queryUreportDataSourceFileByName(getCorrectName(file), categoryId);
		byte[] content = ureportDataSourceFile.getFileContent();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
		return inputStream;
	}
 
	@Override
	public void deleteReport(String file) {
		ureportDataSourceFileCustomMapper.deleteReportFileByName(getCorrectName(file),userInfo.getCategoryId());
	}
 
	@Override
	public List<ReportFile> getReportFiles() {
		List<UreportDataSourceFile> list = ureportDataSourceFileCustomMapper.queryReportFileList(userInfo.getCategoryId());
		List<ReportFile> reportList = new ArrayList<>();
		for (UreportDataSourceFile ureportDataSourceFile : list) {
			reportList.add(new ReportFile(ureportDataSourceFile.getFileName(), ureportDataSourceFile.getModifyTime()));
		}
		return reportList;
	}
 
	@Override
	public void saveReport(String file, String content) {
		file = getCorrectName(file);
		UreportDataSourceFile ureportDataSourceFile = ureportDataSourceFileCustomMapper.queryUreportDataSourceFileByName(file,userInfo.getCategoryId());
		Date currentDate = new Date();
		if(ureportDataSourceFile == null){
			ureportDataSourceFile = new UreportDataSourceFile();
			// 设置报表默认属性
			ureportDataSourceFile.setFileId(IDUtils.genRandomUUId());
			ureportDataSourceFile.setCategoryId("1");
			ureportDataSourceFile.setCreateTime(currentDate);
			ureportDataSourceFile.setModifyTime(currentDate);
			ureportDataSourceFile.setFileDescr(ConstantUtils.DATA_SOURCE_FILE_DEFAULT_DESCR);
			ureportDataSourceFile.setPublicState(ConstantUtils.DATA_SOURCE_FILE_PUBLIC_STATUS_PRIVATE);
			ureportDataSourceFile.setVisibleState(ConstantUtils.DATA_SOURCE_FILE_VISABLE_STATUS_FALSE);
			// 设置报表名称和报表内容
			ureportDataSourceFile.setFileName(file);
			ureportDataSourceFile.setFileContent(content.getBytes());
			ureportDataSourceFileCustomMapper.insertReportFile(ureportDataSourceFile);
		}else{
			ureportDataSourceFile.setFileContent(content.getBytes());
   			ureportDataSourceFile.setModifyTime(currentDate);
   			ureportDataSourceFileCustomMapper.updateReportFile(ureportDataSourceFile);
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
 