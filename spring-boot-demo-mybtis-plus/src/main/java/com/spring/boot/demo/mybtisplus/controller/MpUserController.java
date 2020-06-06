package com.spring.boot.demo.mybtisplus.controller;


import com.spring.boot.demo.mybtisplus.common.Result;
import com.spring.boot.demo.mybtisplus.convert.UserConvert;
import com.spring.boot.demo.mybtisplus.dto.UserDTO;
import com.spring.boot.demo.mybtisplus.entity.MpUser;
import com.spring.boot.demo.mybtisplus.enums.ResultCodeEnum;
import com.spring.boot.demo.mybtisplus.service.IMpUserService;
import com.spring.boot.demo.mybtisplus.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class MpUserController {

    @Autowired
    private IMpUserService userService;
    @Autowired
    private UserConvert userConvert;

    /**
     * 注册
     *
     * @param userDTO
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        MpUser mpUser = userService.lambdaQuery()
                .eq(MpUser::getAccount, userDTO.getAccount())
                .one();
        if (mpUser == null) {
            userDTO.setPassword(MD5Util.md5(userDTO.getAccount(), userDTO.getPassword()));
            boolean save = userService.save(userConvert.convert(userDTO));
            if (save) {
                return Result.success();
            }
        }
        return Result.error(ResultCodeEnum.REGISTERED);
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
        MpUser mpUser = userService.lambdaQuery()
                .eq(MpUser::getAccount, userDTO.getAccount())
                .one();
        if (mpUser == null) {
            return Result.error(ResultCodeEnum.ACCOUNT_ERROR);
        }
        String md5 = MD5Util.md5(userDTO.getAccount(), userDTO.getPassword());
        if (!md5.equals(mpUser.getPassword())) {
            return Result.error(ResultCodeEnum.PASSWORD_ERROR);
        }
        return Result.success(userConvert.convert(mpUser));
    }

}
