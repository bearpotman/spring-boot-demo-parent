package com.spring.boot.demo.swagger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@ApiModel(value = "用户注册接口参数实体")
@Data
@Accessors(chain = true)
public class UserDTO {
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String account;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;
}
