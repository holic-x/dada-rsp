package com.dada.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.IDUtils;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.DataDictionaryDTO;
import com.dada.manager.custom.mapper.DataDictionaryCustomMapper;
import com.dada.manager.service.DataDictionaryService;
import com.dada.mapper.DataDictionaryMapper;
import com.dada.pojo.DataDictionary;
import com.dada.pojo.DataDictionaryExample;
import com.dada.pojo.DataDictionaryExample.Criteria;

@Service("dataDictionaryServiceImpl")
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Autowired
    private DataDictionaryCustomMapper dataDictionaryCustomMapper;

    @Override
    public String addDataDictionary(DataDictionary data) {
       // validateData(data);
        // 封装默认数据
        String dataId = IDUtils.genRandomUUId();
        data.setDataId(dataId);
        // 封装创建日期、修改日期
        data.setCreateTime(new Date());
        data.setModifyTime(new Date());
        dataDictionaryMapper.insert(data);
        return dataId;
    }

    @Override
    public boolean deleteDataDictionaryById(String dataId) {
        // 删除数据
        // 判断是否为父节点，如果是父节点则删除对应的子节点信息
        DataDictionary data = dataDictionaryMapper.selectByPrimaryKey(dataId);
        if(data!=null) {
            if(ConstantUtils.DATA_ISLEAF_NO.equals(data.getIsleaf())) {
                // 查找对应的子节点信息，依次遍历删除
                List<DataDictionary> leafList = dataDictionaryCustomMapper.listLeafByDataCode(data.getDataCode());
                for(DataDictionary dd : leafList) {
                    int i = dataDictionaryMapper.deleteByPrimaryKey(dd.getDataId());
                    if(i<0) {
                        return false;
                    }
                }
            }
        }
        // 如果是子节点则直接删除即可(最终删除父节点)
        int j = dataDictionaryMapper.deleteByPrimaryKey(dataId);
        return (j > 0);
    }

    @Override
    public boolean BatchDeleteDataDictionary(List<String> dataIds) {
        if (CollectionUtils.isEmpty(dataIds)) {
            throw new CommonException("传入的数据id列表不能为空!");
        } else {
            for (String dataId : dataIds) {
               /* int i = dataDictionaryMapper.deleteByPrimaryKey(dataId);
                if (i < 0) {
                    return false;
                }*/
                boolean flag = deleteDataDictionaryById(dataId);
                if(!flag) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean updateDataDictionary(DataDictionary data) {
        if (StringUtils.isEmpty(data.getDataId())) {
            throw new CommonException("需要传入dataId标识要修改的对象!");
        }
       // validateData(data);
        int i = dataDictionaryMapper.updateByPrimaryKeyWithBLOBs(data);
        return (i > 0);
    }

    @Override
    public DataDictionary getDataDictionary(String dataId) {
        return dataDictionaryMapper.selectByPrimaryKey(dataId);
    }

    @Override
    public List<DataDictionary> listDataDictionary(DataDictionaryDTO dataDTO) {
        // 封装筛选对象
        DataDictionaryExample dataDictionaryExample = new DataDictionaryExample();
        Criteria criteria = dataDictionaryExample.createCriteria();
        if (dataDTO != null) {
            // 根据数据编码查找
            if (!StringUtils.isEmpty(dataDTO.getDataCode())) {
                criteria.andDataCodeEqualTo(dataDTO.getDataCode());
            }
            // 根据数据名称模糊查找
            if (!StringUtils.isEmpty(dataDTO.getDataName())) {
                criteria.andDataNameLike("%" + dataDTO.getDataName() + "%");
            }
            // 根据数据类型查找
            if (!StringUtils.isEmpty(dataDTO.getDataType())) {
                criteria.andDataTypeEqualTo(dataDTO.getDataType());
            }
            // 根据是否为父子节点进行查找
            if (!StringUtils.isEmpty(dataDTO.getIsleaf())) {
                criteria.andIsleafEqualTo(dataDTO.getIsleaf());
            }
            // 根据数据启用状态进行标识
            if (!StringUtils.isEmpty(dataDTO.getDataStatus())) {
                criteria.andDataStatusEqualTo(dataDTO.getDataStatus());
            }
        }
        return dataDictionaryMapper.selectByExampleWithBLOBs(dataDictionaryExample);
    }

    // 验证数据
    private void validateData(DataDictionary data) {
        // dataCode数据编码验证：不能为空且唯一
        if (StringUtils.isEmpty(data.getDataCode())) {
            throw new CommonException("数据编码不能为空!");
        } else {
            // 验证数据编码是否唯一


        }
        // dataName数据名称验证：不能为空
        if (StringUtils.isEmpty(data.getDataName())) {
            throw new CommonException("数据名称不能为空!");
        }
        // isLeaf：验证是否为叶子节点-暂定两级(0.父节点 1.叶子节点)
        if (StringUtils.isEmpty(data.getIsleaf())) {
            throw new CommonException("父、子节点标识不能为空");
        }

        // dataType：数据类型必须选择(与父节点一致,如果是父节点则手动输入且需要保证唯一性)
        /*if (StringUtils.isEmpty(data.getDataType())) {
            // 数据类型不能为空且必须满足指定条件
            throw new CommonException("数据类型不能为空!");
        } else {
            // 如果是父节点需要验证数据类型是否唯一，如果是叶子结点则需要与父节点的类型保持一致亦可
            if (ConstantUtils.DATA_ISLEAF_NO.equals(data.getIsleaf())) {
                DataDictionaryDTO dataVO = new DataDictionaryDTO();
                dataVO.setIsleaf(data.getIsleaf());
                List<DataDictionary> findDataList = listDataDictionary(dataVO);
                if (!CollectionUtils.isEmpty(findDataList)) {
                    throw new CommonException("数据必须类型唯一!");
                }
            }
        }*/
    }

    @Override
    public List<DataDictionary> listLeafByDataCode(DataDictionaryDTO dataDTO) {
        if (StringUtils.isEmpty(dataDTO.getDataCode())) {
            throw new CommonException("传入的数据编码不能为空!");
        }
        return dataDictionaryCustomMapper.listLeafByDataCode(dataDTO.getDataCode());
    }

    @Override
    public boolean validateDataCode(String dataCode, String dataId) {
        if (StringUtils.isEmpty(dataCode)) {
            throw new CommonException("传入验证的dataCode不能为空!");
        }
        // 封装筛选对象
        DataDictionaryExample dataDictionaryExample = new DataDictionaryExample();
        Criteria criteria = dataDictionaryExample.createCriteria();
        criteria.andDataCodeEqualTo(dataCode);
        List<DataDictionary> dataList = dataDictionaryMapper.selectByExampleWithBLOBs(dataDictionaryExample);
        if (CollectionUtils.isEmpty(dataList)) {
            // 查找数据为空说明该数据编码不存在
            return false;
        } else {
            // 区分修改时针对同一个数据需要去重判断，否则验证始终无法通过
            if (!StringUtils.isEmpty(dataId)) {
                // 判断id是否相同，如果相同则不视为重复的情况
                if (dataList.get(0).getDataId().equals(dataId)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<PageData> listDataByPage(Page page) {
        return dataDictionaryCustomMapper.selectDataByPage(page);
    }
}
