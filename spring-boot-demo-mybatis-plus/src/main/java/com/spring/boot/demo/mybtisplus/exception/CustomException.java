package com.spring.boot.demo.mybtisplus.exception;

import com.spring.boot.demo.mybtisplus.enums.ResultCode;
import lombok.Data;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/08
 */
@Data
public final class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }
}
