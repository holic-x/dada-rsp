package com.dada.report.provider;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.dada.common.constant.ConstantUtils;
import com.dada.common.utils.IDUtils;
import com.dada.pojo.UreportFile;
import com.dada.pojo.UserInfo;
import com.dada.report.custom.mapper.UreportFileManagerMapper;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.filter.CustomHolder;
import com.dada.report.pojo.UreportFileManager;

public class CustomProvider implements ReportProvider{
	private static final String NAME = "机构报表存储器";
	
    // 特定前缀，ureport底层会调用 getPrefix 方法来获取报表操作的Provier类
	private String prefix = "dada-mysql-custom:";
	
	// 是否禁用
	private boolean disabled; 

	@Autowired
	// private UreportFileCustomMapper ureportFileCustomMapper;
	private UreportFileManagerMapper ureportFileManagerMapper;
	
	private UserInfo initUserInfo() {
	    UserInfo loginUser = CustomHolder.getCurrentUser();
	    if(loginUser==null) {
	        UserInfo userInfo = new UserInfo();
            userInfo.setCategoryId("1");
            userInfo.setDeptId("1");
            return userInfo;
	    }
	    return loginUser;
	}

	private UreportFileSearchParam initSearchParam(String fileName) {
	    UserInfo loginUser = CustomHolder.getCurrentUser();
        if(loginUser==null) {
            loginUser = new UserInfo();
            loginUser.setCategoryId("1");
            loginUser.setDeptId("1");
        }
        // 设置UreportFileSearchParam参数对象
        UreportFileSearchParam searchParam = new UreportFileSearchParam();
        searchParam.setTableName("ureport_file");
        searchParam.setCategoryId(loginUser.getCategoryId());
        searchParam.setDeptId(loginUser.getDeptId());
        searchParam.setFileName(fileName);
        return searchParam;
	}
	
	private UreportFileSearchParam initUpdateSearchParam(UreportFileManager ureportFileManager) {
        // 设置UreportFileSearchParam参数对象,用于数据添加、修改
        UreportFileSearchParam searchParam = new UreportFileSearchParam();
        searchParam.setTableName("ureport_file");
        searchParam.setUreportFileManager(ureportFileManager);
        return searchParam;
    }
	
	@Override
	public InputStream loadReport(String file) {
	   	// 根据报表名称、机构id获取报表信息
		// UreportFile ureportFile = ureportFileCustomMapper.queryUreportFileByName(getCorrectName(file), categoryId);
		// 处理乱码问题(针对页面跳转,却无法解决report内部的乱码解决)
		/*
		String fileName = null;
		try {
            fileName = new String(getCorrectName(file).getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		UreportFile ureportFile = ureportFileCustomMapper.queryUreportFileByName(fileName, categoryId);
		*/
	    // categoryId、deptId、fileName唯一标识报表信息
	    // 获取当前登录用户信息,调用方法封装成UreportFileSearchParam对象
		UreportFileSearchParam searchParam = initSearchParam(getCorrectName(file));
		UreportFileManager ureportFileManager = ureportFileManagerMapper.queryUreportFileByName(searchParam);
		byte[] content = ureportFileManager.getFileContent();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
		return inputStream;
	}
 
	@Override
	public void deleteReport(String file) {
	    // categoryId、deptId、fileName唯一标识报表信息
	    // 获取当前登录用户信息,调用方法封装成UreportFileSearchParam对象
        UreportFileSearchParam searchParam = initSearchParam(getCorrectName(file));
	    ureportFileManagerMapper.deleteReportFileByName(searchParam);
	}
 
	@Override
	public List<ReportFile> getReportFiles() {
	    // categoryId、deptId标识报表存储器
	    // 获取当前登录用户信息,调用方法封装成UreportFileSearchParam对象
        UreportFileSearchParam searchParam = initSearchParam(null);
		List<UreportFileManager> list = ureportFileManagerMapper.queryReportFileList(searchParam);
		List<ReportFile> reportList = new ArrayList<>();
		for (UreportFileManager ureportFileManager : list) {
			reportList.add(new ReportFile(ureportFileManager.getFileName(), ureportFileManager.getModifyTime()));
		}
		return reportList;
	}
 
	@Override
	public void saveReport(String file, String content) {
	    String fileName = getCorrectName(file);
		UreportFileSearchParam searchParam = initSearchParam(fileName);
		UreportFileManager ureportFileManager = ureportFileManagerMapper.queryUreportFileByName(searchParam);
		Date currentDate = new Date();
		// 获取当前登录用户信息
		UserInfo loginUser = initUserInfo();
		if(ureportFileManager == null){
		    ureportFileManager = new UreportFileManager();
			// 设置报表默认属性
		    ureportFileManager.setFileId(IDUtils.genRandomUUId());
		    ureportFileManager.setCategoryId(loginUser.getCategoryId());
		    ureportFileManager.setDeptId(loginUser.getDeptId());
		    ureportFileManager.setCreateTime(currentDate);
		    ureportFileManager.setModifyTime(currentDate);
		    ureportFileManager.setFileDescr(ConstantUtils.REPORT_FILE_DEFAULT_DESCR);
		    ureportFileManager.setPublicState(ConstantUtils.REPORT_FILE_PUBLIC_STATUS_PRIVATE);
		    ureportFileManager.setVisibleState(ConstantUtils.REPORT_FILE_VISABLE_STATUS_FALSE);
			// 设置报表名称和报表内容
		    ureportFileManager.setFileName(fileName);
		    ureportFileManager.setFileContent(content.getBytes());
		    ureportFileManagerMapper.insertReportFile(initUpdateSearchParam(ureportFileManager));
		}else{
		    // 修改报表信息
		    ureportFileManager.setFileContent(content.getBytes());
		    ureportFileManager.setModifyTime(currentDate);
		    ureportFileManagerMapper.updateReportFile(initUpdateSearchParam(ureportFileManager));
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
 