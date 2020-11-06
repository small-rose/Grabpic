package com.xiaocai.web.demo.dao.emp;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaocai.web.demo.entity.emp.Department;
import com.xiaocai.web.demo.entity.emp.DepartmentExample;

public interface DepartmentMapper {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);
}