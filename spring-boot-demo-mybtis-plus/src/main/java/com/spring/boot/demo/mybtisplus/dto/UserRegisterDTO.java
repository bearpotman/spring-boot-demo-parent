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
    @NotEmpty(message = "账号不能为空")
    @Size(min = 6, max = 20, message = "账号长度必须是6~20个字符")
    private String account;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 12, message = "密码长度必须是6~12个字符")
    private String password;
}
