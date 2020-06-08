package com.spring.boot.demo.mybtisplus.controller;


import com.spring.boot.demo.mybtisplus.common.Result;
import com.spring.boot.demo.mybtisplus.convert.UserConvert;
import com.spring.boot.demo.mybtisplus.dto.UserLoginDTO;
import com.spring.boot.demo.mybtisplus.dto.UserRegisterDTO;
import com.spring.boot.demo.mybtisplus.entity.MpUser;
import com.spring.boot.demo.mybtisplus.enums.ResultCodeEnum;
import com.spring.boot.demo.mybtisplus.exception.CustomException;
import com.spring.boot.demo.mybtisplus.service.IMpUserService;
import com.spring.boot.demo.mybtisplus.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangJun
 * @since 2020-06-06
 */
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
@Validated
public class MpUserController {

    @Autowired
    private IMpUserService userService;
    @Autowired
    private UserConvert userConvert;

    /**
     * 根据用户编号查询用户信息
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据用户编号查询用户信息")
    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable("userId") @Min(value = 1, message = "用户编号不能小于1") Long userId) {
        MpUser mpUser = userService.lambdaQuery()
                .eq(MpUser::getId, userId)
                .one();
        if (mpUser == null) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST);
        }
        return Result.success(mpUser);
    }

    /**
     * 注册
     *
     * @param userRegisterDTO
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        MpUser mpUser = userService.lambdaQuery()
                .eq(MpUser::getAccount, userRegisterDTO.getAccount())
                .one();
        if (mpUser != null) {
            throw new CustomException(ResultCodeEnum.REGISTERED);
        }
        userRegisterDTO.setPassword(MD5Util.md5(userRegisterDTO.getAccount(), userRegisterDTO.getPassword()));
        boolean save = userService.save(userConvert.convert(userRegisterDTO));
        if (!save) {
            throw new CustomException(ResultCodeEnum.SYSTEM_ERROR);
        }
        return Result.success();
    }

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        MpUser mpUser = userService.lambdaQuery()
                .eq(MpUser::getAccount, userLoginDTO.getAccount())
                .one();
        if (mpUser == null) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST);
        }
        String md5 = MD5Util.md5(userLoginDTO.getAccount(), userLoginDTO.getPassword());
        if (!md5.equals(mpUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PASSWORD_ERROR);
        }
        return Result.success(userConvert.convert(mpUser));
    }

}
