package com.dada.sso.utils;

import java.io.Serializable;
import java.util.Map;


public class ResultManage implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 接口返回状态1为成功，0为失败
     */
    private int result_code;

    /**
     * 接口失败时，返回信息
     */
    private String result_message;

    /**
     * 接口返回的业务数据
     */
    private Map data;

    public int getResult_code() {
        return result_code;
    }

    /**
     * 接口返回状态1为成功，0为失败
     */
    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public Map getData() {
        return data;
    }

    /**
     * 接口返回的业务数据
     */
    public void setData(Map data) {
        this.data = data;
    }

}
