package com.spring.boot.demo.mybtisplus.common;

import com.spring.boot.demo.mybtisplus.enums.ResultCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code = ResultCodeEnum.SUCCESS.getCode();
    /**
     * 描述信息
     */
    private String msg = ResultCodeEnum.SUCCESS.getMsg();
    /**
     * 响应数据
     */
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<T>().setData(data);
    }

    public static Result success(Integer code, String msg) {
        return new Result().setCode(code).setMsg(msg);
    }

    public static Result success() {
        return new Result();
    }

    public static Result error(ResultCodeEnum resultEnum) {
        Result result = new Result()
                .setCode(resultEnum.getCode())
                .setMsg(resultEnum.getMsg());
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result()
                .setCode(code)
                .setMsg(msg);
        return result;
    }
}
