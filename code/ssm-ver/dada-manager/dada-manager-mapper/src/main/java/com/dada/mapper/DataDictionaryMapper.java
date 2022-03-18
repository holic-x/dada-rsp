package com.dada.mapper;

import com.dada.pojo.DataDictionary;
import com.dada.pojo.DataDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDictionaryMapper {
    int countByExample(DataDictionaryExample example);

    int deleteByExample(DataDictionaryExample example);

    int deleteByPrimaryKey(String dataId);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    List<DataDictionary> selectByExampleWithBLOBs(DataDictionaryExample example);

    List<DataDictionary> selectByExample(DataDictionaryExample example);

    DataDictionary selectByPrimaryKey(String dataId);

    int updateByExampleSelective(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByExampleWithBLOBs(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByExample(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKeyWithBLOBs(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
}