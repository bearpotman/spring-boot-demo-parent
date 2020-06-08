package com.spring.boot.demo.mybtisplus.aspect;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/08
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.spring.boot.demo.mybtisplus.controller..*.*(..))")
    private void controllerAspect() {
    }


    @ResponseBody
    @Around("controllerAspect()")
    public Object doRecord(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String uuid = IdUtil.fastSimpleUUID();
        log.info("请求编号【{}】请求地址【{}】", uuid, request.getRequestURL().toString());
        log.info("请求编号【{}】请求方式【{}】", uuid, request.getMethod());
        log.info("请求编号【{}】请求类方法【{}】", uuid, joinPoint.getSignature());

        Object[] args = joinPoint.getArgs();
        Stream<Object> stream = Arrays.stream(args);
        //过滤一些参数，不然上传和下载文件时异常
        List<Object> logArgs = stream
                .filter(arg -> (
                        !(arg instanceof HttpServletRequest) &&
                                !(arg instanceof HttpServletResponse)) &&
                        !(arg instanceof MultipartFile))
                .collect(Collectors.toList());
        log.info("请求编号【{}】请求类方法参数【{}】", uuid, JSONUtil.toJsonStr(logArgs));

        Object result = joinPoint.proceed(args);

        log.info("请求编号【{}】Response内容【{}】", uuid, JSONUtil.toJsonStr(result));
        return result;
    }
}
