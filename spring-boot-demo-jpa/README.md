# spring boot中使用JPA
`pom.xml` 主要依赖：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

`application-dev.yml` 主要配置：
```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/spring_boot_demo_jpa?characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      # update: 没有表就新建，有表则更新
      ddl-auto: update
    # 控制台显示sql语句（正式环境还是不要用）
    show-sql: true
```

`User.java` 实体层：
```java
@Data
@Accessors(chain = true)
@Entity
@Table(name = "jpa_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 20)
    private Long id;

    @Column(name = "account", columnDefinition = "varchar(11) COMMENT '账号'")
    private String account;

    @Column(name = "password", columnDefinition = "varchar(32) COMMENT '密码'")
    private String password;

    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;
}
```

`UserRepository.java` 数据访问层：
```java
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据账号查询用户
     *
     * @param account 用户登录账号
     * @return
     */
    User findByAccount(String account);
}
```

`service` 层与 `controller` 层都是一些简单的测试代码，就不一一列举了。

需要注意的地方：
- 首先需要在 `MySQL` 中创建数据库，比如创建数据库 `spring_boot_demo_jpa`;
- 修改 `application-dev.yml` 中的配置信息，主要指 `url`、`username` 和 `password`;
- 启动项目，查看上述创建的数据库中是否存在表 `jpa_user` 或可直接查看控制台日志看是否有输出创建表的语句;
- 最后可根据 `UserController.java` 中的接口进行简单测试。
