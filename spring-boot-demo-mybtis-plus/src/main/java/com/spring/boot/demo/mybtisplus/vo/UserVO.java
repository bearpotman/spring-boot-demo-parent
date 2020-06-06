package com.spring.boot.demo.mybtisplus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@ApiModel(value = "用户登录接口返回实体对象")
@Data
@Accessors(chain = true)
public class UserVO {
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Long userId;
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String account;
    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime registerTime;
}
