package com.springboot.domain;

/**
 * @classDesc: 自定义返回值模型
 * @author: Vipin Zheng
 * @createDate: 2018-05-13 11:52:33
 * @version: v1.0
 */
public class CustomType {
    private Integer code;
    private String message;

    public CustomType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
