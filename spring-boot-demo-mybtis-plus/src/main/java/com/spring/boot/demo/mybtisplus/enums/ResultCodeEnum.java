package com.spring.boot.demo.mybtisplus.enums;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
public enum ResultCodeEnum {
    /*------------------------common code---------------------*/
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),

    /*------------------------user module code(1001~1020)---------------------*/
    REGISTERED(1001, "已注册"),
    ACCOUNT_ERROR(1002, "账号错误"),
    PASSWORD_ERROR(1003, "密码错误"),;

    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
