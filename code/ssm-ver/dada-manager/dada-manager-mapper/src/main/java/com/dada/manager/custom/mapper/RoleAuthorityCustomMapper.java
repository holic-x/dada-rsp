package com.dada.manager.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dada.common.utils.Page;
import com.dada.common.utils.PageData;
import com.dada.vo.RoleAuthorityVO;

public interface RoleAuthorityCustomMapper {

    List<PageData> selectRoleAuthorityByPage(Page page);
    
    List<RoleAuthorityVO> selectRoleAuthorityByRoleId(@Param(value="roleId")String roleId);
    
}
