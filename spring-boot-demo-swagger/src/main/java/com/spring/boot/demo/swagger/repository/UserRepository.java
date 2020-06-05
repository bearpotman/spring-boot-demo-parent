package com.spring.boot.demo.swagger.repository;

import com.spring.boot.demo.swagger.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据账号查询用户
     *
     * @param account 用户登录账号
     * @return
     */
    User findByAccount(String account);
}
