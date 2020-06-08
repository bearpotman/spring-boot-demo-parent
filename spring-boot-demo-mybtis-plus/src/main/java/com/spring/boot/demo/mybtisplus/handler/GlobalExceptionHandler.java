package com.spring.boot.demo.mybtisplus.handler;

import com.spring.boot.demo.mybtisplus.common.Result;
import com.spring.boot.demo.mybtisplus.enums.ResultCodeEnum;
import com.spring.boot.demo.mybtisplus.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description 全局异常处理
 * @Date 2020/06/08
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result customExceptionHandler(CustomException ce) {
        log.error("自定义异常", ce);
        return Result.error(ce.getResultCodeEnum());
    }

    @ExceptionHandler
    public Result exceptionHandler(Exception e) {
        log.error("系统异常", e);
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }
}
