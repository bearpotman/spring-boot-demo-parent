package com.spring.boot.demo.mybtisplus.aspect;

import cn.hutool.core.lang.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.util.Locale;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description 获取请求头中的 locale 并放入 RequestContextHolder 中
 * @Date 2020/06/11
 */
@Slf4j
@RestControllerAdvice
public class LocaleHolderAspect extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body,
                                HttpInputMessage inputMessage,
                                MethodParameter parameter,
                                Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        String locale = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("locale");
        if (Validator.isNotEmpty(locale)) {
            LocaleContextHolder.setLocale(Locale.forLanguageTag(locale));
        } else {
            LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        }
        return body;
    }
}
