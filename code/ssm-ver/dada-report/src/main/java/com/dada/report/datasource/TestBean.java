package com.dada.report.datasource;

import java.util.List;
import java.util.Map;

import com.dada.report.bean.Staff;
import com.dada.report.utils.BeanGenerator;

public class TestBean {
	
	
    public List<Map<String,Object>> loadReportData(String dsName,String datasetName,Map<String,Object> parameters){
        return BeanGenerator.randomData();
    }
    
    // Staff.java类(简单测试类实现)
    public List<Staff> buildReport(String dsName,String datasetName,Map<String,Object> parameters){
        return BeanGenerator.randomStaffList();
    }
    
    // Staff.java类(简单测试类实现)
    public List<Staff> buildStaffReport(String dsName,String datasetName,Map<String,Object> parameters){
        return BeanGenerator.randomStaffList();
    }
    
    // Order.java类
    
    

}
