package com.dada.mapper;

import com.dada.pojo.Department;
import com.dada.pojo.DepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    int countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(String deptId);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExampleWithBLOBs(DepartmentExample example);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(String deptId);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExampleWithBLOBs(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKeyWithBLOBs(Department record);

    int updateByPrimaryKey(Department record);
}