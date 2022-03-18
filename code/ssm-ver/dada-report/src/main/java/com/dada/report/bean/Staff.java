package com.dada.report.bean;

public class Staff {
	
	private String num ;
	
	private String name ;
	
	private Double salary ;

	private String hireDate ;
	
	private String email ;
	
	private String phone ;
	
	private String address ;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Staff [num=" + num + ", name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + ", email="
				+ email + ", phone=" + phone + ", address=" + address + "]";
	}
	
	public String toInsertString() {
		return "insert into employee(emp_num,emp_name,emp_salary,emp_hiredate,emp_email,emp_phone,emp_address) "
				+ "values('" + num + "', '" + name + "', '" + salary + "', '" + hireDate + "', '"
				+ email + "', '" + phone + "', '" + address + "');";
	}

}
