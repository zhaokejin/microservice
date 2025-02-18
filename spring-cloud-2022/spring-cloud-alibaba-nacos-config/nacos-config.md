


其中，dataId 的完整格式为：${prefix}-${spring.profile.active}.${file-extension} 。

prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
spring.profile.active 即为当前环境对应的 profile，详情可以参考 Spring Boot文档。 **注意：当 spring.profile.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 ${prefix}.${file-extension} **
file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。


启动日志
2021-08-30 21:48:33.191  WARN 10336 --- [           main] c.a.c.n.c.NacosPropertySourceBuilder     : Ignore the empty nacos configuration and get it based on dataId[spring-cloud-alibaba-nacos-config] & group[DEFAULT_GROUP]
2021-08-30 21:48:33.196  WARN 10336 --- [           main] c.a.c.n.c.NacosPropertySourceBuilder     : Ignore the empty nacos configuration and get it based on dataId[spring-cloud-alibaba-nacos-config.properties] & group[DEFAULT_GROUP]
2021-08-30 21:48:33.258  INFO 10336 --- [           main] b.c.PropertySourceBootstrapConfiguration : Located property source: [BootstrapPropertySource {name='bootstrapProperties-spring-cloud-alibaba-nacos-config-dev.properties,DEFAULT_GROUP'}, BootstrapPropertySource {name='bootstrapProperties-spring-cloud-alibaba-nacos-config.properties,DEFAULT_GROUP'}, BootstrapPropertySource {name='bootstrapProperties-spring-cloud-alibaba-nacos-config,DEFAULT_GROUP'}]

访问
http://localhost:9003/profile


刷新监听日志打印
2021-08-30 21:50:51.385  INFO 10336 --- [.168.11.50_8848] o.s.c.e.event.RefreshEventListener       : Refresh keys changed: [profile]