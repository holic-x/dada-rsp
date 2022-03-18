package com.dada.mapper;

import com.dada.pojo.RoleInfo;
import com.dada.pojo.RoleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleInfoMapper {
    int countByExample(RoleInfoExample example);

    int deleteByExample(RoleInfoExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    List<RoleInfo> selectByExampleWithBLOBs(RoleInfoExample example);

    List<RoleInfo> selectByExample(RoleInfoExample example);

    RoleInfo selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") RoleInfo record, @Param("example") RoleInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") RoleInfo record, @Param("example") RoleInfoExample example);

    int updateByExample(@Param("record") RoleInfo record, @Param("example") RoleInfoExample example);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKeyWithBLOBs(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}