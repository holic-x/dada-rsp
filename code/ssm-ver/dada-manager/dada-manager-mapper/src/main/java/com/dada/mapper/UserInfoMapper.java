package com.dada.mapper;

import com.dada.pojo.UserInfo;
import com.dada.pojo.UserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExampleWithBLOBs(UserInfoExample example);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}