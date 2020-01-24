package com.zjf.exception;

/**
 * @author zjf
 * @create 2020/1/22-13:51
 */

public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }


    @Override
    public String getMessage() {
        return message;
    }


    public Integer getCode() {
        return code;
    }
}
