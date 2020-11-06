package com.xiaocai.web.demo.entity.emp;

import javax.validation.constraints.Pattern;

public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = 6654960072154305288L;

	private Integer empId;

	//@Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})",
	//		message="用户是2-5位中文或者6-16位英文和数字组合")
    private String empName;

    private String sex;

	//@Pattern(regexp="^([a-zA-Z0-9_-\\.]+)@([\\da-zA-Z0-9_-\\.]+)\\.([a-zA-Z0-9_-\\.]{2,6}))$",
	//		message="用户是2-5位中文或者6-16位英文和数字组合")
    private String email;

    private int did;
    
    private Department department ;
    
    public Employee(){
    	
    }
    
    
    public Employee(Integer empId, String empName, String sex, String email,int did) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.sex = sex;
		this.email = email;
		this.did = did;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


	public int getDid() {
		return did;
	}


	public void setDid(int did) {
		this.did = did;
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", sex=" + sex + ", email=" + email + ", did="
				+ did + ", department=" + (department!=null?department.getDeptName():"") + "]";
	}
    
}