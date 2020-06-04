package com.spring.boot.demo.jpa.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@Data
@Accessors(chain = true)
public class UserDTO {
    /**
     * 登录账号
     */
    private String account;
    /**
     * 登录密码
     */
    private String password;
}
