package com.spring.boot.demo.mybtisplus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@ApiModel(value = "用户注册接口参数实体")
@Data
@Accessors(chain = true)
public class UserRegisterDTO {
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "{account.notEmpty}")
    @Size(min = 6, max = 20, message = "{account.size}")
    private String account;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "{password.notEmpty}")
    @Size(min = 6, max = 12, message = "{password.size}")
    private String password;
}
