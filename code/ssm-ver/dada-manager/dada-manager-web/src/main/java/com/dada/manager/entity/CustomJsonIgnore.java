package com.dada.manager.entity;

public class CustomJsonIgnore {
    
    public static String[] getZTreeNodeAttributes() {
        // 设置默认忽略属性
        String[] ignoreAttributes = {
                // "nodeId", // 节点id
                // "nodePid", // 父节点id
                // "name",// 节点名称
                "icon",// 节点图标
                "iconClose", // 节点折叠时的图标
                "iconOpen", // 节点展开时的图标
                // "open", // 节点默认展开状态:true展开、false折叠
                "isParent", // 是否为父节点:true展开、false折叠
                "title", // 节点提示信息
                "url", // url
                // "menuUrl" // 自定义点击事件跳转url
        };
        return ignoreAttributes;
    }

}
