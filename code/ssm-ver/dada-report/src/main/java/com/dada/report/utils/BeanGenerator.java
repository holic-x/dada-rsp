package com.dada.report.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.dada.common.utils.CommonDataGenerator;
import com.dada.report.bean.Employee;
import com.dada.report.bean.Staff;

// 对象数据生成器
public class BeanGenerator {
	
	// 随机生成员工信息
	public static Staff randomStaff() {
		Staff staff = new Staff();
		staff.setNum(CommonDataGenerator.random10num());
		staff.setName(CommonDataGenerator.randomChineseName());
		staff.setSalary(CommonDataGenerator.randomSalary());
		staff.setHireDate(CommonDataGenerator.randomDay());
		staff.setEmail(CommonDataGenerator.randomEmail(6, 18));
		staff.setPhone(CommonDataGenerator.randomPhone());
		staff.setAddress(CommonDataGenerator.randomRoad());
		return staff;
	}
	
	// 批量生成员工数据
	public static List<Staff> randomStaffList(){
        List<Staff> staffList = new ArrayList<Staff>();
        for(int i=0;i<10000;i++){
    		staffList.add(randomStaff());
        }
        return staffList;
    }
	
	public static Employee randomEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeId(CommonDataGenerator.random10num());
		employee.setEmployeeName(CommonDataGenerator.randomChineseName());
		String deptName = CommonDataGenerator.randomDept();
		employee.setDeptName(deptName);
		employee.setJobName(CommonDataGenerator.randomJob(deptName));
		employee.setSex(CommonDataGenerator.randomGender());
		employee.setBirthday(CommonDataGenerator.randomDay());
		employee.setMarried(CommonDataGenerator.randomMarried());
		employee.setSalary(CommonDataGenerator.randomSalary());
		employee.setDegree(CommonDataGenerator.randomDegree());
		employee.setEmail(CommonDataGenerator.randomEmail(6, 18));
		employee.setAddress(CommonDataGenerator.randomRoad());
		return employee;
	}
	
	// 批量生成员工数据
	public static List<Employee> randomEmployeeList(){
        List<Employee> employeeList = new ArrayList<Employee>();
        for(int i=0;i<10000;i++){
    		employeeList.add(randomEmployee());
        }
        return employeeList;
    }
	
	// 批量生成测试数据
	public static List<Map<String,Object>> randomData(){
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for(int i=0;i<10000;i++){
            Map<String,Object> m=new HashMap<String,Object> ();
            m.put("num", i);
            m.put("name", CommonDataGenerator.randomChineseName());
            m.put("descr",RandomStringUtils.random(10,true,false));
            m.put("salray",RandomUtils.nextInt(10000)+i );
            list.add(m);
        }
        return list;
    }
	 
}
