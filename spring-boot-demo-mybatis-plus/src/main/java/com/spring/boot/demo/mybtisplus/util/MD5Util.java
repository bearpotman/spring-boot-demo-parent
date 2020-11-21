package com.spring.boot.demo.mybtisplus.util;

import org.springframework.util.DigestUtils;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/06
 */
public class MD5Util {

    private MD5Util() {
    }

    /**
     * md5加密
     *
     * @param account
     * @param password
     * @return
     */
    public static String md5(String account, String password) {
        String salt = "^#)$^&$$!~@+(,.';-`";
        byte[] bytes = new StringBuilder(account)
                .append(password)
                .append(salt)
                .toString()
                .getBytes();
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
