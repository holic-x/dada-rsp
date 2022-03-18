package com.dada.report.bean;

import java.util.Date;

/**
 * @author hahabibu
 * 系统名称: 
 * 模块名称: 
 * 类 名 称: Employee
 * 软件版权: 淘淘商城
 * 功能说明：
 * 系统版本：
 * 开发时间: create in 2019年3月8日 下午2:15:48
 * 开发说明: 
 */
public class Employee {
	
	private String employeeId;
	
	private String employeeName;
	
	private String deptName;
	
	private String jobName;
	
	private String sex;
	
	private String birthday;
	
	private String married;
	
	private double salary;
	
	private String degree;
	
	private String email;
	
	private String address;

	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getMarried() {
		return married;
	}


	public void setMarried(String married) {
		this.married = married;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", deptName=" + deptName
				+ ", jobName=" + jobName + ", sex=" + sex + ", birthday=" + birthday + ", married=" + married
				+ ", salary=" + salary + ", degree=" + degree + ", email=" + email + ", address=" + address + "]";
	}


	public String toInsertString() {
		return "insert into employee values('" + employeeId + "', '" + employeeName + "', '" + deptName + "', '" + jobName + "', '"
				+ sex + "', '" + birthday + "', '" + married + "', '" + salary + "', '" + degree
				+ "', '" + email + "', '" + address + "');";
	}
	
}
