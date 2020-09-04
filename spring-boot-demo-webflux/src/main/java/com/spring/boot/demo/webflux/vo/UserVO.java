package com.spring.boot.demo.webflux.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
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
