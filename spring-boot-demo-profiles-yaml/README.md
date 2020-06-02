# 基于yaml配置文件的环境区分
为确保项目能正常启动运行，项目依赖添加 `spring-boot-starter-web`.

遵守 `spring boot` 一贯的风格 - "约定大于配置"，只要相应的配置文件以约定的格式命名即可实现此功能。
约定的格式即 `application-{profile}.yml`。

各个配置文件的职责：
- application.yml

  此配置文件一般用来存放一些公用的配置信息。
- application-dev.yml

  存放 `dev` 即开发环境需要用到的一些配置信息。
- application-prod.yml

  存放 `prod` 即正式环境需要用到的一些配置信息。
- application-test.yml

  存放 `test` 即测试环境需要用到的一些配置信息。
  
正如本demo所演示一样，切换环境只需要在 `application.yml` 配置文件中将 `spring.profiles.active` 设置为约定的值即可。

比如在测试环境时，只需要将 `spring.profiles.active` 设置为 `test` 即可，spring boot 会自动找到 `application-test.yml` 配置文件并加载配置。其他类似。

对应[官方文档](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/reference/html/spring-boot-features.html#boot-features-external-config-profile-specific-properties)