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

===============================================

`2020/06/08` 更新：

新增 `全局异常处理`，主要新增的有：

- `com.spring.boot.demo.mybtisplus.exception.CustomException`: 自定义异常；
- `com.spring.boot.demo.mybtisplus.exception.handler.GlobalExceptionHandler`: 全局异常处理；
- `com.spring.boot.demo.mybtisplus.enums.ResultCode`: 统一状态码；
- `com.spring.boot.demo.mybtisplus.controller.MpUserController`: 具体使用。

新增 `参数校验` 功能：

- 新增依赖：
  ```xml
  <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-validator</artifactId>
      <version>${hibernate.validator.version}</version>
  </dependency>
  ```
- 全局异常处理新增方法：
    ```java
    /**
     * 方法处参数校验异常处理
     *
     * @param cve {@link ConstraintViolationException}
     * @return {@link Result}
     */
    @ExceptionHandler
    public Result constraintViolationExceptionHandler(ConstraintViolationException cve) {
        log.warn("[constraintViolationExceptionHandler]", cve);
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : cve.getConstraintViolations()) {
            if (message.length() > 0) {
                message.append("; ");
            }
            message.append(constraintViolation.getMessage());
        }
        return Result.error(ResultCodeEnum.INVALID_REQUEST_PARAM_ERROR.getCode(),
                StrUtil.format("{}: {}",
                        ResultCodeEnum.INVALID_REQUEST_PARAM_ERROR.getMsg(),
                        message.toString()));
    }
    
    /**
     * bean对象处参数校验异常处理
     *
     * @param me {@link MethodArgumentNotValidException}
     * @return {@link Result}
     */
    @ExceptionHandler
    public Result bindExceptionHandler(MethodArgumentNotValidException me) {
        log.warn("[bindExceptionHandler]", me);
        StringBuilder message = new StringBuilder();
        for (ObjectError objectError : me.getBindingResult().getAllErrors()) {
            if (message.length() > 0) {
                message.append("; ");
            }
            message.append(objectError.getDefaultMessage());
        }
        return Result.error(ResultCodeEnum.INVALID_REQUEST_PARAM_ERROR.getCode(),
                StrUtil.format("{}: {}",
                        ResultCodeEnum.INVALID_REQUEST_PARAM_ERROR.getMsg(),
                        message.toString()));
    }
    ```

- 参数添加校验规则：
    ```java
    @Api(tags = "用户相关接口")
    @RestController
    @RequestMapping("/user")
    @Validated
    public class MpUserController {
    
        @Autowired
        private IMpUserService userService;
        @Autowired
        private UserConvert userConvert;
    
        /**
         * 根据用户编号查询用户信息
         *
         * @param userId
         * @return
         */
        @ApiOperation(value = "根据用户编号查询用户信息")
        @GetMapping("/{userId}")
        public Result getUserById(@PathVariable("userId") @Min(value = 1, message = "用户编号不能小于1") Long userId) {
            MpUser mpUser = userService.lambdaQuery()
                    .eq(MpUser::getId, userId)
                    .one();
            if (mpUser == null) {
                throw new CustomException(ResultCodeEnum.USER_NOT_EXIST);
            }
            return Result.success(mpUser);
        }
    
        /**
         * 注册
         *
         * @param userRegisterDTO
         * @return
         */
        @ApiOperation(value = "用户注册")
        @PostMapping("/register")
        public Result register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
            MpUser mpUser = userService.lambdaQuery()
                    .eq(MpUser::getAccount, userRegisterDTO.getAccount())
                    .one();
            if (mpUser != null) {
                throw new CustomException(ResultCodeEnum.REGISTERED);
            }
            userRegisterDTO.setPassword(MD5Util.md5(userRegisterDTO.getAccount(), userRegisterDTO.getPassword()));
            boolean save = userService.save(userConvert.convert(userRegisterDTO));
            if (!save) {
                throw new CustomException(ResultCodeEnum.SYSTEM_ERROR);
            }
            return Result.success();
        }
    
        /**
         * 登录
         *
         * @param userLoginDTO
         * @return
         */
        @ApiOperation(value = "用户登录")
        @PostMapping("/login")
        public Result login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
            MpUser mpUser = userService.lambdaQuery()
                    .eq(MpUser::getAccount, userLoginDTO.getAccount())
                    .one();
            if (mpUser == null) {
                throw new CustomException(ResultCodeEnum.ACCOUNT_ERROR);
            }
            String md5 = MD5Util.md5(userLoginDTO.getAccount(), userLoginDTO.getPassword());
            if (!md5.equals(mpUser.getPassword())) {
                throw new CustomException(ResultCodeEnum.PASSWORD_ERROR);
            }
            return Result.success(userConvert.convert(mpUser));
        }
    
    }
    ```
    ```java
    @ApiModel(value = "用户登录接口参数实体")
    @Data
    @Accessors(chain = true)
    public class UserLoginDTO {
        /**
         * 登录账号
         */
        @ApiModelProperty(value = "登录账号")
        @NotEmpty(message = "登录账号不能为空")
        private String account;
        /**
         * 登录密码
         */
        @ApiModelProperty(value = "登录密码")
        @NotEmpty(message = "登录密码不能为空")
        private String password;
    }
    ```
    ```java
    @ApiModel(value = "用户注册接口参数实体")
    @Data
    @Accessors(chain = true)
    public class UserRegisterDTO {
        /**
         * 账号
         */
        @ApiModelProperty(value = "账号")
        @NotEmpty(message = "账号不能为空")
        @Size(min = 6, max = 20, message = "账号长度必须是6~20个字符")
        private String account;
        /**
         * 登录密码
         */
        @ApiModelProperty(value = "密码")
        @NotEmpty(message = "密码不能为空")
        @Size(min = 6, max = 12, message = "密码长度必须是6~12个字符")
        private String password;
    }
    ```