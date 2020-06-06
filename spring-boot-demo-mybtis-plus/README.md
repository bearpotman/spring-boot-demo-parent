# spring boot整合mybatis-plus
本案例是在之前 `spring-boot-demo-swagger` 的基础之上，将数据访问层 `JPA` 替换为 `mybatis-plus`，同时提供代码生成器。

`pom.xml` 新增依赖:
```xml
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>${freemarker.version}</version>
</dependency>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>${mybatis.plus.version}</version>
</dependency>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>${mybatis.plus.version}</version>
</dependency>
```

`mp_user.sql` 表结构:
```mysql
CREATE TABLE `mp_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';
```

执行 `CodeGenerator.java#main(String[] args)`。

`SpringBootDemoMybtisPlusApplication.java` 启动类:
```java
@SpringBootApplication
@MapperScan(value = {"com.spring.boot.demo.mybtisplus.mapper"})
@EnableSwagger2
public class SpringBootDemoMybtisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoMybtisPlusApplication.class, args);
	}

}
```

最后启动项目，访问接口测试。