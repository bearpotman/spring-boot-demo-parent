# spring boot整合swagger
本案例是在之前 `spring-boot-demo-jpa` 案例的基础之上添加了对 `swagger` 的支持。

`pom.xml` 新增依赖: 
```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring</artifactId>
    <version>${swagger.bootstrap.ui.version}</version>
</dependency>

<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-ui</artifactId>
    <version>${swagger.bootstrap.ui.version}</version>
</dependency>
```

`SwaggerConfig.java` swagger 配置: 
```java
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2) // 文档类型，使用 Swagger2
                .apiInfo(this.apiInfo()) // 设置 API 信息
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.boot.demo.swagger.controller"))
                .paths(PathSelectors.any())
                // 构建出 Docket 对象
                .build();
    }

    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-boot-demo-swagger")
                .description("spring-boot-demo-swagger 接口文档")
                .version("1.0.0") // 版本号
                .build();
    }
}
```

`SpringBootDemoSwaggerApplication.java` 启动类:
```java
@EnableSwagger2
@SpringBootApplication
public class SpringBootDemoSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoSwaggerApplication.class, args);
	}

}
```

晚上上述配置，启动项目，访问 `http://localhost:8080/doc.html` 即可查看接口文档。