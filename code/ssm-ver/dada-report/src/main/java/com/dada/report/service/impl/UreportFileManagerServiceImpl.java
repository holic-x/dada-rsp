package com.dada.report.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dada.common.exception.CommonException;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.pojo.UreportFile;
import com.dada.report.custom.mapper.UreportFileManagerMapper;
import com.dada.report.dto.UreportFileSearchParam;
import com.dada.report.pojo.UreportFileManager;
import com.dada.report.service.UreportFileManagerService;

@Service("ureportFileManagerServiceImpl")
@Transactional
public class UreportFileManagerServiceImpl implements UreportFileManagerService{
    
    @Autowired
    private UreportFileManagerMapper ureportFileManagerMapper;

    private void validateTableName(UreportFileSearchParam searchParam) {
        if(searchParam!=null) {
            if(StringUtils.isEmpty(searchParam.getTableName())) {
                throw new CommonException("dada-report:必须指定tableName-ureport_file/ureport_data_source_file/ureport_backup/ureport_data_source_backup");
            }
        }
    }
    
    @Override
    public boolean updateUreportFile(UreportFileSearchParam searchParam) {
        // 需要根据修改大字段、根据条件修改(没传入的参数不作变动)
        // 验证传入参数(tableName、ureportFileManager中id必须指定)
        validateTableName(searchParam);
        UreportFileManager ureportFileManager = searchParam.getUreportFileManager();
        if(ureportFileManager==null) {
            throw new CommonException("dada-report:必须指定ureportFileManager对象");
        }else {
            if(StringUtils.isEmpty(ureportFileManager.getFileId())) {
                throw new CommonException("dada-report:必须指定要更新的文件id-fileId");
            }
        }
        int i = ureportFileManagerMapper.updateReportFile(searchParam);
        return (i>0);
    }


    @Override
    public boolean deleteUreportFile(UreportFileSearchParam searchParam) {
        // 验证传入参数(tableName、fileId必须指定)
        validateTableName(searchParam);
        if(StringUtils.isEmpty(searchParam.getFileId())) {
            throw new CommonException("dada-report:必须指定fileId属性");
        }
        int i = ureportFileManagerMapper.deleteUreportFileById(searchParam);
        return (i>0);
    }

    @Override
    public UreportFileManager getUreportFile(UreportFileSearchParam searchParam) {
        return ureportFileManagerMapper.getUreportFileById(searchParam);
    }

    @Override
    public List<PageData> listUreportFileByPage(Page page) {
        PageData pageData = page.getPageData();
        if(pageData==null) {
            throw new CommonException("dada-report:必须指定pageData对象");
        }else {
            if(StringUtils.isEmpty(pageData.getString("tableName"))) {
                throw new CommonException("dada-report:pageData对象中必须指定tableName属性");
            }
        }
        return ureportFileManagerMapper.listUreportFileByPage(page);
    }

    @Override
    public List<UreportFileManager> listReportFileByNoClassify(UreportFileSearchParam searchParam) {
        validateTableName(searchParam);
        return ureportFileManagerMapper.listReportFileByNoClassify(searchParam);
    }

}
