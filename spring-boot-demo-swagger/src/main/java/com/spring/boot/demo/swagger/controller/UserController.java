package com.spring.boot.demo.swagger.controller;

import com.spring.boot.demo.swagger.common.Result;
import com.spring.boot.demo.swagger.dto.UserDTO;
import com.spring.boot.demo.swagger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@Api(tags = "用户操作相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param userDTO
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO.getAccount(), userDTO.getPassword());
    }

    /**
     * 登录
     *
     * @param userDTO
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO.getAccount(), userDTO.getPassword());
    }
}
