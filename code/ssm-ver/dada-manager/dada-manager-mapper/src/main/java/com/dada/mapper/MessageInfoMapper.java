package com.dada.mapper;

import com.dada.pojo.MessageInfo;
import com.dada.pojo.MessageInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageInfoMapper {
    int countByExample(MessageInfoExample example);

    int deleteByExample(MessageInfoExample example);

    int deleteByPrimaryKey(String messageId);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    List<MessageInfo> selectByExampleWithBLOBs(MessageInfoExample example);

    List<MessageInfo> selectByExample(MessageInfoExample example);

    MessageInfo selectByPrimaryKey(String messageId);

    int updateByExampleSelective(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    int updateByExample(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKeyWithBLOBs(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);
}