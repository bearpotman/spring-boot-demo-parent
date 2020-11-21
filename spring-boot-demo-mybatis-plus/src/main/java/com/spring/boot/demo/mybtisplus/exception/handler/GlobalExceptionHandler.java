package com.spring.boot.demo.mybtisplus.exception.handler;

import cn.hutool.core.util.StrUtil;
import com.spring.boot.demo.mybtisplus.common.Result;
import com.spring.boot.demo.mybtisplus.enums.ResultCode;
import com.spring.boot.demo.mybtisplus.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description 全局异常处理
 * @Date 2020/06/08
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     *
     * @param ce {@link CustomException}
     * @return {@link Result}
     */
    @ExceptionHandler
    public Result customExceptionHandler(CustomException ce) {
        log.warn("【customExceptionHandler】 - {}", ce.getMessage());
        return Result.error(ce.getResultCode());
    }

    /**
     * 未知异常处理
     *
     * @param e {@link Throwable}
     * @return {@link Result}
     */
    @ExceptionHandler
    public Result exceptionHandler(Throwable e) {
        log.warn("[exceptionHandler] - {}", e);
        return Result.error(ResultCode.SYSTEM_ERROR);
    }

    /**
     * 方法处参数校验异常处理
     *
     * @param cve {@link ConstraintViolationException}
     * @return {@link Result}
     */
    @ExceptionHandler
    public Result constraintViolationExceptionHandler(ConstraintViolationException cve) {
        log.warn("【constraintViolationExceptionHandler】 - {}", cve.getMessage());
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : cve.getConstraintViolations()) {
            if (message.length() > 0) {
                message.append("; ");
            }
            message.append(constraintViolation.getMessage());
        }
        return Result.error(ResultCode.INVALID_REQUEST_PARAM_ERROR.getCode(),
                StrUtil.format("{}: {}",
                        ResultCode.INVALID_REQUEST_PARAM_ERROR.getMsg(),
                        message.toString()));
    }

    /**
     * bean对象处参数校验异常处理
     *
     * @param me {@link MethodArgumentNotValidException}
     * @return {@link Result}
     */
    @ExceptionHandler
    public Result bindExceptionHandler(MethodArgumentNotValidException me) {
        log.warn("【bindExceptionHandler】 - {}", me.getMessage());
        StringBuilder message = new StringBuilder();
        for (ObjectError objectError : me.getBindingResult().getAllErrors()) {
            if (message.length() > 0) {
                message.append("; ");
            }
            message.append(objectError.getDefaultMessage());
        }
        return Result.error(ResultCode.INVALID_REQUEST_PARAM_ERROR.getCode(),
                StrUtil.format("{}: {}",
                        ResultCode.INVALID_REQUEST_PARAM_ERROR.getMsg(),
                        message.toString()));
    }
}
