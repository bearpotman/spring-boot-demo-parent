package com.spring.boot.demo.jpa.service;

import com.spring.boot.demo.jpa.common.Result;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
public interface UserService {

    /**
     * 注册
     *
     * @param account
     * @param password
     * @return
     */
    Result register(String account, String password);

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    Result login(String account, String password);

}
