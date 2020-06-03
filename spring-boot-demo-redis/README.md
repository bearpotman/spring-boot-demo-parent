# sping boot整合redis
主要实现的功能：
- spring boot中使用redis
- redis 序列化

`pom.xml` 主要依赖：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

`application-dev.yml` 主要配置：
```yaml
spring:
  redis:
    host: 127.0.0.1  # redis 主机   (默认 localhost)
    port: 6379       # redis 端口   (默认 6379)
    database: 0      # redis 数据库 (默认 0)
    password:
    timeout: 3000
    jedis:
      pool:
        max-active: 8
        max-idle: 8
        max-wait: -1ms
```

`RedisConfig.java` 主要配置：
```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 使用 String 序列化方式，序列化key
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 使用 JSON 序列化方式（库是 Jackson ），序列化value
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
```

`Test` 测试程序：
```java
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
```