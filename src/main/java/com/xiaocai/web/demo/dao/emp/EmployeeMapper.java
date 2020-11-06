package com.xiaocai.web.demo.dao.emp;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaocai.web.demo.entity.emp.Employee;
import com.xiaocai.web.demo.entity.emp.EmployeeExample;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);
    
    List<Employee> selectByExampleWithDepart(EmployeeExample example);

    Employee selectByPrimaryKeyWithDepart(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}