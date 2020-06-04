package com.spring.boot.demo.jpa.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@Data
@Accessors(chain = true)
public class UserVO {
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 登录账号
     */
    private String account;
    /**
     * 注册时间
     */
    private LocalDateTime registerTime;
}
