package com.dada.mapper;

import com.dada.pojo.RoleAuthorityInfo;
import com.dada.pojo.RoleAuthorityInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleAuthorityInfoMapper {
    int countByExample(RoleAuthorityInfoExample example);

    int deleteByExample(RoleAuthorityInfoExample example);

    int deleteByPrimaryKey(String roleAuthorityId);

    int insert(RoleAuthorityInfo record);

    int insertSelective(RoleAuthorityInfo record);

    List<RoleAuthorityInfo> selectByExample(RoleAuthorityInfoExample example);

    RoleAuthorityInfo selectByPrimaryKey(String roleAuthorityId);

    int updateByExampleSelective(@Param("record") RoleAuthorityInfo record, @Param("example") RoleAuthorityInfoExample example);

    int updateByExample(@Param("record") RoleAuthorityInfo record, @Param("example") RoleAuthorityInfoExample example);

    int updateByPrimaryKeySelective(RoleAuthorityInfo record);

    int updateByPrimaryKey(RoleAuthorityInfo record);
}