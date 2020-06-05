package com.spring.boot.demo.jpa.service.impl;

import com.spring.boot.demo.jpa.common.Result;
import com.spring.boot.demo.jpa.convert.UserConvert;
import com.spring.boot.demo.jpa.entities.User;
import com.spring.boot.demo.jpa.enums.ResultCodeEnum;
import com.spring.boot.demo.jpa.repository.UserRepository;
import com.spring.boot.demo.jpa.service.UserService;
import com.spring.boot.demo.jpa.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConvert userConvert;

    /**
     * 注册
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public Result register(String account, String password) {
        User byAccount = userRepository.findByAccount(account);
        if (byAccount != null) {
            return Result.error(ResultCodeEnum.REGISTERED);
        }
        User user = new User()
                .setAccount(account)
                .setPassword(getMd5(account, password));
        userRepository.save(user);
        return Result.success();
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public Result login(String account, String password) {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            return Result.error(ResultCodeEnum.ACCOUNT_ERROR);
        }
        String md5 = getMd5(account, password);
        if (!md5.equals(user.getPassword())) {
            return Result.error(ResultCodeEnum.PASSWORD_ERROR);
        }
//        UserVO userVO = new UserVO()
//                .setUserId(user.getId())
//                .setAccount(user.getAccount())
//                .setRegisterTime(user.getCreateTime());
        return Result.success(userConvert.convert(user));
    }

    /**
     * md5加密
     *
     * @param account
     * @param password
     * @return
     */
    public String getMd5(String account, String password) {
        String salt = "^#)$^&$$!~@+(,.';-`";
        byte[] bytes = new StringBuilder(account)
                .append(password)
                .append(salt)
                .toString()
                .getBytes();
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
