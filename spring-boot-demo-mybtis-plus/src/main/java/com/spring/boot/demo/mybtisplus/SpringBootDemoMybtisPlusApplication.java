package com.spring.boot.demo.mybtisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value = {"com.spring.boot.demo.mybtisplus.mapper"})
@EnableSwagger2
public class SpringBootDemoMybtisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoMybtisPlusApplication.class, args);
    }

}
