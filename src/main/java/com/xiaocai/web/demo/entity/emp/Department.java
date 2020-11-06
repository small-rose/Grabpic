package com.xiaocai.web.demo.entity.emp;

public class Department  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4969741886389350881L;

	private Integer deptId;

    private String deptName;

    public Department() {
		// TODO Auto-generated constructor stub
	}
    public Department(String deptName) {
		super();
		this.deptName = deptName;
	}
    
	public Department(Integer deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}


	public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }


	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
	}
    
    
}