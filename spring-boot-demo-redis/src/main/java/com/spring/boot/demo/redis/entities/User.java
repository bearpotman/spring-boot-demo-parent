package com.spring.boot.demo.redis.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

}
