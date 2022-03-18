package com.dada.portal.entity;
/**
 * <p>项目名称:dada-manager-web</p>
 * <p>包名称:com.dada.manager.controller.ZTreeNode</p>
 * <p>文件名称:ZTreeNode.java</p>
 * <p>功能描述:ZTree节点实体</p>
 * <p>其他说明:    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月24日上午10:44:14 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */

import java.util.Arrays;

/**
 * 
  treeNode: {
      name, //节点显示的文本
      checked, //节点是否勾选，ztree配置启用复选框时有效
      open, //节点是否展开
      icon, //节点的图标
      iconOpen, //节点展开式的图标
      iconClose, //节点折叠时的图标
      id,  //节点的标识属性，对应的是启用简单数据格式时idKey对应的属性名，并不一定是id,如果setting中定义的idKey:"zId",那么此处就是zId
      pId, //节点parentId属性，命名规则同id
      children, //得到该节点所有孩子节点，直接下级，若要得到所有下属层级节点，需要自己写递归得到
      isParent, //判断该节点是否是父节点，一般应用中通常需要判断只有叶子节点才能进行相关操作，或者删除时判断下面是有子节点时经常用到。
      getPath() //得到该节点的路径，即所有父节点，包括自己，此方法返回的是一个数组，通常用于创建类似面包屑导航的东西A-->B-->C 
  }
 */
public class ZTreeNode {
    
    // 节点id
    private String nodeId ;
    
    // 父节点id
    private String nodePid ;
    
    // 节点名称
    private String name ;

    // 节点图标
    private String icon;

    // 节点折叠时的图标
    private String iconClose ;

    // 节点展开时的图标
    private String iconOpen ;

    // 节点默认展开状态:true展开、false折叠
    private boolean open ;

    // 是否为父节点:true展开、false折叠
    private boolean isParent;
    
    // 节点提示信息
    private String title;
    
    // url(默认url,需要配置启动才相应生效)
    private String url;
    
    // 自定义菜单url
    private String menuUrl;
    
    // 创建转化成json数据时需忽略的属性
    private String[] ignoreAttributes;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodePid() {
        return nodePid;
    }

    public void setNodePid(String nodePid) {
        this.nodePid = nodePid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String[] getIgnoreAttributes() {
        return ignoreAttributes;
    }
    
    public void setIgnoreAttributes(String[] ignoreAttributes) {
        this.ignoreAttributes = ignoreAttributes;
    }

    @Override
    public String toString() {
        return "ZTreeNode [nodeId=" + nodeId + ", nodePid=" + nodePid + ", name=" + name + ", icon=" + icon
                + ", iconClose=" + iconClose + ", iconOpen=" + iconOpen + ", open=" + open + ", isParent=" + isParent
                + ", title=" + title + ", url=" + url + ", menuUrl=" + menuUrl + ", ignoreAttributes="
                + Arrays.toString(ignoreAttributes) + "]";
    }
 
}
