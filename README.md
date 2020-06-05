# 基于spring boot的一些demo

- spring-boot-demo-profiles-yaml

  基于 `yaml` 配置文件的环境切换demo。
  
  其实平时开发也在用，但不是这样在 `application.yml` 中来回切换环境，而是结合 `maven` 来使用，也就是下一个案例的使用方式。

- spring-boot-demo-profiles-maven

  基于 `yaml` 配置文件以及 `maven` 的环境切换demo。
  
  推荐使用，因为一般都是通过 `maven` 的一些命令来进行编译打包部署的，比如常用的命令 `maven clean package -P test`。
  
- spring-boot-demo-redis

  `spring boot` 整合 `redis` demo。
  
  同时实现了序列化功能，比如对象中某些属性为 `null` 时不进行序列化操作。
  
- spring-boot-demo-jpa

  `spring boot` 整合 `JPA` demo。
  
  同时提供 `Jackson` 序列化与反序列化配置。
  
  其实相比较而言（比如跟 `mybatis` 比较），JPA 使用起来更方便，它默认提供的常用方法基本上已经够平时开发使用了，对于其他的一些特殊需求，即使你使用 `mybatis` ，你也是需要单独写 `sql` 语句来完成的，其实个人感觉 `JPA` 更好用一些，但是国内可能使用 `mybatis` 的更多一些。
  
- spring-boot-demo-swagger
  
  `spring boot` 整合 `swagger` demo。
  
  在之前 `spring-boot-demo-jpa` 案例的基础之上添加了对 `swagger` 的支持，接口更新后悔更新到 `swagger` 文档中，方便后端与前端小姐姐对接。