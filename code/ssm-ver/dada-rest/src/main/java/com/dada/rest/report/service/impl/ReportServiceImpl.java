package com.dada.rest.report.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dada.common.constant.ConstantUtils;
import com.dada.common.exception.CommonException;
import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.dto.ReportClassifyDTO;
import com.dada.manager.custom.mapper.ReportCustomMapper;
import com.dada.mapper.ReportClassifyLinkMapper;
import com.dada.mapper.ReportClassifyMapper;
import com.dada.pojo.ReportClassify;
import com.dada.pojo.ReportClassifyExample;
import com.dada.pojo.ReportClassifyExample.Criteria;
import com.dada.pojo.ReportClassifyLink;
import com.dada.rest.report.service.ReportService;
@Service("reportServiceImpl")
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportClassifyMapper classifyMapper;
    
    @Autowired
    private ReportClassifyLinkMapper classifyLinkMapper;
    
    @Autowired
    private ReportCustomMapper reportCustomMapper;
    
    @Override
    public String addReportClassify(ReportClassify classify) {
        int i = classifyMapper.insert(classify);
        if(i>0) {
            return classify.getClassifyId();
        }
        return null;
    }

    @Override
    public boolean deleteReportClassifyById(ReportClassifyDTO classifyDTO) {
        String classifyId = classifyDTO.getClassifyId();
        if(StringUtils.isEmpty(classifyId)) {
            throw new CommonException("dada-rest:传入的classifyId不能为空");
        }
        int i = classifyMapper.deleteByPrimaryKey(classifyId);
        return i>0;
    }

    @Override
    public boolean BatchDeleteReportClassify(ReportClassifyDTO classifyDTO) {
        List<String> classifyIds = classifyDTO.getClassifyIds();
        if(CollectionUtils.isEmpty(classifyIds)) {
            throw new CommonException("dada-rest:至少传入一条数据");
        }
        for(String classifyId : classifyIds) {
            int i = classifyMapper.deleteByPrimaryKey(classifyId);
            if(i<0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateReportClassify(ReportClassify classify) {
        int i = classifyMapper.updateByPrimaryKeyWithBLOBs(classify);
        return i>0;
    }

    @Override
    public ReportClassify getReportClassify(String classifyId) {
        return classifyMapper.selectByPrimaryKey(classifyId);
    }

    @Override
    public List<ReportClassify> listReportClassify(ReportClassifyDTO classifyDTO) {
        ReportClassifyExample classifyExample = new ReportClassifyExample();
        Criteria criteria = classifyExample.createCriteria();
        // 封装筛选条件:根据指定categoryId、deprId查找数据
        if (classifyDTO != null) {
            if (!StringUtils.isEmpty(classifyDTO.getCategoryId())) {
                criteria.andCategoryIdEqualTo(classifyDTO.getCategoryId());
            }
            if (!StringUtils.isEmpty(classifyDTO.getDeptId())) {
                criteria.andDeptIdEqualTo(classifyDTO.getDeptId());
            }
        }
        criteria.andDelTagEqualTo(ConstantUtils.DEL_TAG_SAVE);
        return classifyMapper.selectByExampleWithBLOBs(classifyExample);
    }

    @Override
    public List<PageData> listReportClassifyByPage(Page page) {
        return reportCustomMapper.selectReportClassifyByPage(page);
    }

    @Override
    public boolean setReportClassifyLink(ReportClassifyDTO classifyDTO) {
        /*
         * 根据传入的数据封装报表分类关联关系
         * 根据categoryId、deptId、classifyId重置对应的数据信息
         * 先删除,后重新添加
         */
        /*
         * 方式1:先清空后添加
        String categoryId = classifyDTO.getCategoryId();
        String deptId = classifyDTO.getDeptId();
        String classifyId = classifyDTO.getClassifyId();
        if(StringUtils.isEmpty(categoryId)) {
            throw new CommonException("dada-rest:传入categoryId不能为空");
        }
        if(StringUtils.isEmpty(deptId)) {
            throw new CommonException("dada-rest:传入deptId不能为空");
        }
        if(StringUtils.isEmpty(classifyId)) {
            throw new CommonException("dada-rest:传入classifyId不能为空");
        }
        // 调用方法删除指定分类的报表关联管理
        reportCustomMapper.deleteClassifyLink(classifyDTO);
        // 重新遍历数据添加报表分类关联关系
        List<ReportClassifyLink> linkList = classifyDTO.getLinkList();
        if(CollectionUtils.isEmpty(linkList)) {
            return true;
        }
        for(ReportClassifyLink link : linkList) {
            int i = classifyLinkMapper.insert(link);
            if(i<0) {
                return false;
            }
        }*/
        // 方式2:根据用户指定的operatorType操作类型确定要进行操作的方式
        String operatorType = classifyDTO.getOperatorType();
        if(ConstantUtils.LINK_OPERATOR_TYPR_SAVE.equals(operatorType)) {
            // 标识添加报表分类关联操作
            // 重新遍历数据添加报表分类关联关系
            List<ReportClassifyLink> linkList = classifyDTO.getLinkList();
            if(CollectionUtils.isEmpty(linkList)) {
                return true;
            }
            for(ReportClassifyLink link : linkList) {
                int i = classifyLinkMapper.insert(link);
                if(i<0) {
                    return false;
                }
            }
        }else if(ConstantUtils.LINK_OPERATOR_TYPR_DELETE.equals(operatorType)) {
            // 标识删除报表分类关联操作
            // 依次删除指定的报表关联关系(直接根据id(传入linkIds)指定即可)
            List<String> linkIds = classifyDTO.getLinkIds();
            if(CollectionUtils.isEmpty(linkIds)) {
                return true;
            }
            for(String linkId : linkIds) {
                if(StringUtils.isEmpty(linkId)) {
                    throw new CommonException("dada-rest:指定操作的linkId不能为空");
                }
                int i = classifyLinkMapper.deleteByPrimaryKey(linkId);
                if(i<0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<PageData> listLinkList(Page page) {
        // 根据categoryId、deptId、classifyId查找对应的数据信息（报表分类关联数据）
        return reportCustomMapper.listLinkList(page);
    }

    @Override
    public boolean setLinkHideStatus(ReportClassifyDTO classifyDTO) {
        // 根据指定的linkIds、hideStatus修改对应报表分类下的报表关联关系(修改报表隐藏状态-hideStatus)
        List<String> linkIds = classifyDTO.getLinkIds();
        if(CollectionUtils.isEmpty(linkIds)) {
            return true;
        }
        String hideStatus = classifyDTO.getHideStatus();
        if(StringUtils.isEmpty(hideStatus)) {
            // 设置默认状态
            hideStatus = ConstantUtils.DEFAULT_DATA;
        }
        for(String linkId : linkIds) {
            // 根据linkId查找数据 ,修改状态
            ReportClassifyLink reportClassifyLink = classifyLinkMapper.selectByPrimaryKey(linkId);
            if(reportClassifyLink==null) {
                // throw new CommonException("dada-portal:当前要操作的数据不存在");
                return false;
            }
            // 修改状态信息
            reportClassifyLink.setHideStatus(hideStatus);
            int i =classifyLinkMapper.updateByPrimaryKey(reportClassifyLink);
            if(i<0) {
                return false;
            }
        }
        return false;
    }

}
