package com.spring.boot.demo.mybtisplus.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description Swagger 配置
 * @Date 2020/06/05
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2) // 文档类型，使用 Swagger2
                .apiInfo(this.apiInfo()) // 设置 API 信息
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.boot.demo.mybtisplus.controller"))
                .paths(PathSelectors.any())
                // 构建出 Docket 对象
                .build()
                .globalOperationParameters(globParams())
                .securityContexts(Lists.newArrayList(securityContext()));
    }

    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-boot-demo-mybtisplus")
                .description("spring-boot-demo-mybtisplus 接口文档")
                .version("1.0.0") // 版本号
                .contact(new Contact("wangJun", "https://github.com/bearpotman/spring-boot-demo-parent", "wangjun9406@163.com"))
                .build();
    }

    private List<Parameter> globParams() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name("locale")
                .description("语言，目前支持中文：zh-CN：英文：en-US，默认中文")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false)
                .build();
        //header中的ticket参数非必填，传空也可以
        //根据每个方法名也知道当前方法在设置什么参
        List<Parameter> pars = new ArrayList<>();
        pars.add(ticketPar.build());
        return pars;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }
}
