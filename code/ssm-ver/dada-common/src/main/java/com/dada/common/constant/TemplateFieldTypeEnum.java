package com.dada.common.constant;

/**
 * Created by Administrator on 2018/7/4.
 */
public enum TemplateFieldTypeEnum {
    VARCHAR("VARCHAR", "字符串"),
    NUMBER("NUMBER", "数字"),
    DATE("DATE", "日期");
    private final String code;
    private final String value;

    /**
     * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
     */
    private TemplateFieldTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
