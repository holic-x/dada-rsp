package com.dada.mapper;

import com.dada.pojo.AuthorityInfo;
import com.dada.pojo.AuthorityInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorityInfoMapper {
    int countByExample(AuthorityInfoExample example);

    int deleteByExample(AuthorityInfoExample example);

    int deleteByPrimaryKey(String authorityId);

    int insert(AuthorityInfo record);

    int insertSelective(AuthorityInfo record);

    List<AuthorityInfo> selectByExampleWithBLOBs(AuthorityInfoExample example);

    List<AuthorityInfo> selectByExample(AuthorityInfoExample example);

    AuthorityInfo selectByPrimaryKey(String authorityId);

    int updateByExampleSelective(@Param("record") AuthorityInfo record, @Param("example") AuthorityInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") AuthorityInfo record, @Param("example") AuthorityInfoExample example);

    int updateByExample(@Param("record") AuthorityInfo record, @Param("example") AuthorityInfoExample example);

    int updateByPrimaryKeySelective(AuthorityInfo record);

    int updateByPrimaryKeyWithBLOBs(AuthorityInfo record);

    int updateByPrimaryKey(AuthorityInfo record);
}