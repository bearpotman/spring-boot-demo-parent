package com.spring.boot.demo.mybtisplus.enums;

import com.spring.boot.demo.mybtisplus.util.BeanFactoryUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description 统一状态码
 * <p>
 * 参考艿艿的博客 http://www.iocoder.cn/Spring-Boot/WebFlux/?self
 * <p>
 * 一共 10 位，分成四段
 * <p>
 * 第一段，1 位，类型
 * 1 - 业务级别异常
 * 2 - 系统级别异常
 * 第二段，3 位，系统类型
 * 001 - 用户系统
 * 002 - 商品系统
 * 003 - 订单系统
 * 004 - 支付系统
 * 005 - 优惠劵系统
 * ... - ...
 * 第三段，3 位，模块
 * 不限制规则。
 * 一般建议，每个系统里面，可能有多个模块，可以再去做分段。以用户系统为例子：
 * 001 - OAuth2 模块
 * 002 - User 模块
 * 003 - MobileCode 模块
 * 第四段，3 位，错误码
 * 不限制规则。
 * 一般建议，每个模块自增。
 * @Date 2020/06/04
 */
public enum ResultCodeEnum {
    /*------------------------ 系统级别 ---------------------*/
    SUCCESS(0),
    SYSTEM_ERROR(2001001000),
    INVALID_REQUEST_PARAM_ERROR(2001001001),

    /*------------------------ 用户模块 ---------------------*/
    REGISTERED(1001002000),
    USER_NOT_EXIST(1001002001),
    PASSWORD_ERROR(1001002002),;

    private Integer code;

    ResultCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static ResultCodeEnum fetch(Integer code) {
        for (ResultCodeEnum resultCode : values()) {
            if (resultCode.code.equals(code)) {
                return resultCode;
            }
        }
        return null;
    }

    public String getMsg() {
        MessageSource messageSource = BeanFactoryUtil.getBean(MessageSource.class);
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource
                .getMessage(fetch(code).name(), null, locale);
        return message;
    }
}
