package com.spring.boot.demo.mybtisplus.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author wangJun
 * @version V1.0.1
 * @Description
 * @date 2020/06/11
 */
@Component
public class BeanFactoryUtil implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext application) throws BeansException {
        applicationContext = application;
    }

    /**
     * 获取应用程序当前运行环境
     */
    public static Environment currentEnvironment() {
        return applicationContext.getEnvironment();
    }

    /**
     * 获取应用程序当前激活的配置
     */
    public static String[] currentProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }

    /**
     * 检查当前环境是否是开发环境
     */
    public static boolean isDev() {
        return Arrays.asList(currentProfiles()).contains("dev");
    }

    public static boolean isProd() {
        return Arrays.asList(currentProfiles()).contains("prod");
    }

    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);

    }

    /**
     * 通过class获取Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
