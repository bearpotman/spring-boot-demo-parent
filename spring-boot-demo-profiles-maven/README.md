# 基于maven的环境区分
`pom.xml` 配置：
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <!-- 开启过滤替换功能 -->
            <filtering>true</filtering>
            <includes>
                <!-- 项目打包完成的包中只包含当前环境文件 -->
                <include>application.yml</include>
                <include>application-${activeProfile}.yml</include>
            </includes>
        </resource>
    </resources>
</build>

<profiles>
    <profile>
        <id>dev</id>
        <properties>
            <activeProfile>dev</activeProfile>
        </properties>
        <activation>
            <!-- 默认使用dev开发配置 -->
            <activeByDefault>true</activeByDefault>
        </activation>
    </profile>
    <!-- 打包命令：mvn package -P test -->
    <profile>
        <id>test</id>
        <properties>
            <activeProfile>test</activeProfile>
        </properties>
    </profile>
    <!-- 打包命令：mvn package -P prod -->
    <profile>
        <id>prod</id>
        <properties>
            <activeProfile>prod</activeProfile>
        </properties>
    </profile>
</profiles>
```

`application.yml` 配置：
```yaml
spring:
  profiles:
    active: @activeProfile@
```
`activeProfile` 对应于 `pom.xml` 配置文件的那个属性，命名可以自定义。

最后使用 `maven clean package -P test` 命令打包，然后查看 `target` 目录生成的配置文件，发现配置已生效。