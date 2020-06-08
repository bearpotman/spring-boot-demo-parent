package com.spring.boot.demo.mybtisplus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@ApiModel(value = "用户登录接口参数实体")
@Data
@Accessors(chain = true)
public class UserLoginDTO {
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    @NotEmpty(message = "登录账号不能为空")
    private String account;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    @NotEmpty(message = "登录密码不能为空")
    private String password;
}
