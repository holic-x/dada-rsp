package com.dada.common.constant;

/**
 * @author 卢炳伸
 * 系统名称: 新外呼
 * 模块名称: 模板
 * 类 名 称: JdbcTypeEnum
 * 软件版权: 远传股份有限公司
 * 功能说明：
 * 系统版本：
 * 开发人员: 卢炳伸
 * 开发时间: 2018/11/6 10:37
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 */
public enum JdbcTypeEnum {
    //列举出所有可能的驱动类型
    MYSQL("mysql"),
    ORACLE("oracle");

    /**
     * 驱动名称
     */
    private String type;

    JdbcTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
