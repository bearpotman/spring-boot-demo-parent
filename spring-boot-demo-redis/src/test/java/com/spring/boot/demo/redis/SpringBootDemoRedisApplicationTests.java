package com.spring.boot.demo.redis;

import com.spring.boot.demo.redis.entities.User;
import com.spring.boot.demo.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringBootDemoRedisApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSetString() {
        try {
            redisUtil.set("user:10001:name", "wangJun");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetUser() {
        try {
            User user = new User()
                    .setId(10001L)
                    .setName("wangJun")
                    .setAge(18);
            redisUtil.set(String.format("user:%d", user.getId()), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUser() {
        try {
            User  user = (User) redisUtil.get(String.format("user:%d", 10001));
            log.info("user:{}", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
