package com.spring.boot.demo.mybtisplus.config;

import com.spring.boot.demo.mybtisplus.interceptor.HttpRequestInterceptor;
import com.spring.boot.demo.mybtisplus.util.BeanFactoryUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * 拦截器配置
 * </p>
 *
 * @author wangJun
 * @version V1.0.0
 * @since 2020/06/15
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 所有请求拦截器
        registry.addInterceptor(BeanFactoryUtil.getBean(HttpRequestInterceptor.class))
                .addPathPatterns("/**");
    }
}
