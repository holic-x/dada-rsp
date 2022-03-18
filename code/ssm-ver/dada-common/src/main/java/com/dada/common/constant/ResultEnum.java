package com.dada.common.constant;

/**
 * 失败结果定义
 * Created by Administrator on 2018/7/4.
 */
public enum ResultEnum {
    CLOSE("1","864", "关机","0", "1"),
    STOP("2","872", "停机","0", "1"),
    DEADNUM("3","871", "空号","0", "0"),
    CALLING("4","865,862", "通话中","0", "1"),
    REJECTION("5","866,605,480,408,867,486,868", "久叫不答、拒接","0", "1"),
    ADDZERO("6","873","加拨零","0","0"),
    SUCCESS("7","200", "接听","1", "1"),
    OTHER("8","other", "其他","0", "0");

    public static final String  VALIDCODE = ",864,872,871,865,862,866,605,873,200,480,408,867,486,868,";

    /**
     * 类型id
     */
    private final String id;
    /**
     * 对应ipcc的resultCode编码
     */
    private final String code;
    /**
     * 显示文本内容
     */
    private final String value;
    /**
     * 类型：1成功，2.失败
     */
    private final String type;
    /**
     * 是否允许设置:0、不允许 1、允许
     */
    private final String allowSetting;


    /**
     * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
     */
    private ResultEnum(String id , String code, String value, String type,String allowSetting) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.type = type;
        this.allowSetting = allowSetting;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public String getAllowSetting() {
        return allowSetting;
    }

    public String getId() {
        return id;
    }

    public static boolean hasCode(String code){
        return VALIDCODE.indexOf(code)> -1 ;
    }
}
