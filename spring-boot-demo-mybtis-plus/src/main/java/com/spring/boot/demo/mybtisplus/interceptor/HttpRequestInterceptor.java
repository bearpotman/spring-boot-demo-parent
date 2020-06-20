package com.spring.boot.demo.mybtisplus.interceptor;

import cn.hutool.core.lang.Validator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * <p>
 * 全局请求拦截器
 * </p>
 *
 * @author wangJun
 * @version V1.0.0
 * @since 2020/06/20
 */
@Component
public class HttpRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // locale 处理
        String locale = request.getHeader("locale");
        if (Validator.isNotEmpty(locale)) {
            LocaleContextHolder.setLocale(Locale.forLanguageTag(locale));
        } else {
            LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        }
        return true;
    }
}
