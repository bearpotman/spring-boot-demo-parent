package com.spring.boot.demo.webflux.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * </p>
 *
 * @author wangJun
 * @version V1.0.0
 * @since 2020/09/04
 */
@Data
@Accessors(chain = true)
public class UserAddDTO {
    /**
     * 编号
     */
    private String id;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 真实姓名
     */
    private String realName;
}
