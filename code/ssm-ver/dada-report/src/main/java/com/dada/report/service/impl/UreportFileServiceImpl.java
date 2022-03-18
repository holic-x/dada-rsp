package com.dada.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.manager.custom.mapper.UreportFileCustomMapper;
import com.dada.mapper.UreportFileMapper;
import com.dada.pojo.UreportFile;
import com.dada.report.service.UreportFileService;

@Service("ureportFileServiceImpl")
@Transactional
public class UreportFileServiceImpl implements UreportFileService{
    
    @Autowired
    private UreportFileMapper ureportFileMapper;

    @Autowired
    private UreportFileCustomMapper ureportFileCustomMapper;

    @Override
    public boolean updateUreportFile(UreportFile ureportFile) {
        // int i = ureportFileMapper.updateByPrimaryKey(ureportFile);
        // 需要根据修改大字段、根据条件修改(没传入的参数不作变动)
        int i = ureportFileMapper.updateByPrimaryKeySelective(ureportFile);
        return (i>0);
    }

    @Override
    public boolean deleteUreportFile(String fileId) {
        int i = ureportFileMapper.deleteByPrimaryKey(fileId);
        return (i>0);
    }
    
    @Override
    public UreportFile getUreportFile(String fileId) {
        return ureportFileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public List<PageData> listUreportFileByPage(Page page) {
        return ureportFileCustomMapper.listUreportFileByPage(page);
    }

}
