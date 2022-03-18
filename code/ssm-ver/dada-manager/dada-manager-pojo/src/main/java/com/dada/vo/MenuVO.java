package com.dada.vo;

import java.io.Serializable;
import java.util.List;

import com.dada.pojo.AuthorityInfo;

/**
 * <p>项目名称:dada-manager-pojo</p>
 * <p>包名称:com.dada.vo.MenuVO</p>
 * <p>文件名称:MenuVO.java</p>
 * <p>功能描述: 菜单显示</p>
 * <p>其他说明: 封装要显示的页面信息   </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年2月26日下午10:08:18 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
// public class MenuVO implements Serializable{ 
public class MenuVO  implements Serializable{ 
     
     private String authorityId;
     
     private String authorityName;
     
     private String authorityIcon;
     
     private String authorityUrl;
     
     private String visableStatus;
     
     private List<MenuVO> leafMenuList;
     
     public MenuVO() {
         
     }
     
     public MenuVO(String authorityId,String authorityName,String authorityIcon,String authorityUrl,String visableStatus) {
         this.authorityId = authorityId;
         this.authorityName = authorityName;
         this.authorityIcon = authorityIcon;
         this.authorityUrl = authorityUrl;
         this.visableStatus = visableStatus;
     }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityIcon() {
        return authorityIcon;
    }

    public void setAuthorityIcon(String authorityIcon) {
        this.authorityIcon = authorityIcon;
    }

    public String getAuthorityUrl() {
        return authorityUrl;
    }

    public void setAuthorityUrl(String authorityUrl) {
        this.authorityUrl = authorityUrl;
    }

    public String getVisableStatus() {
        return visableStatus;
    }

    public void setVisableStatus(String visableStatus) {
        this.visableStatus = visableStatus;
    }

    public List<MenuVO> getLeafMenuList() {
        return leafMenuList;
    }

    public void setLeafMenuList(List<MenuVO> leafMenuList) {
        this.leafMenuList = leafMenuList;
    }

    @Override
    public String toString() {
        return "MenuVO [authorityId=" + authorityId + ", authorityName=" + authorityName + ", authorityIcon="
                + authorityIcon + ", authorityUrl=" + authorityUrl + ", visableStatus=" + visableStatus
                + ", leafMenuList=" + leafMenuList + "]";
    }
 
 }
