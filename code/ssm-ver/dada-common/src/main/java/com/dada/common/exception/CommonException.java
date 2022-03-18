package com.dada.common.exception;

/**
 * 自定义异常类
 */
public class CommonException extends BusinessException {
    private static final long serialVersionUID = -3731534625112887691L;
    /**
     * 错误Code
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    public CommonException(String msg) {
        super(msg);
        this.errorMsg = msg;
    }

    public CommonException(Exception e) {
        super(e);
        this.errorMsg = e.getMessage();
    }

    public CommonException(String code, String msg) {
        super(msg);
        this.errorMsg = msg;
        this.errorCode = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
