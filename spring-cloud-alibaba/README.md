# Spring Cloud Alibaba
Spring Boot 2.3.2.RELEASE
Spring Cloud Hoxton.SR9
Spring Cloud Alibaba 2.2.6.RELEASE
Nacos 1.4.2

## Nacos1.4.2下载
https://github.com/alibaba/nacos/releases/download/1.4.2/nacos-server-1.4.2.zip

**spring-cloud-alibaba-provider** ： 服务注册（生产者）  9000

**spring-cloud-alibaba-consumer-ribbon** ： 服务注册（消费者Ribbon方式）  8081

**spring-cloud-alibaba-consumer-feign** ： 服务注册（消费者Feign方式）  8082

**spring-cloud-alibaba-provider-file** ： 文件上传服务注册（生产者）  8080

**spring-cloud-alibaba-consumer-feign-file** ： 服务注册（消费者Feign方式传输文件）  8083

**spring-cloud-alibaba-provider_skywalking_log** ： 服务注册集成skywalking栗子  9002

**spring-cloud-alibaba-provider_dockerfile_deploy** ： 服务提供者idea部署dockerfile到docker中  9001

**spring-boot-alibaba-nacos-config-discovery** ： [Spring Boot如何使用 Nacos 来实现分布式环境下的配置管理和服务发现。](https://nacos.io/zh-cn/docs/quick-start-spring-boot.html)  9005

**spring-boot-admin-server** ： [springboot admin server]() 8000






**docker开启2375端口** [skywalking直接部署](skywalking/docker2375.md)

**1. docker idea部署项目** [idea部署项目](https://www.yuque.com/zhaokejin/ck21tv/gf6frg)

**2. dockerfile项目打包部署** [dockerfile项目打包部署](spring-cloud-alibaba-provider_skywalking_log/Dockerfile)

**skywalking部署链路跟踪**

- **skywalking直接部署** [skywalking直接部署](skywalking/skywalking.md)
- **skywalking源码编译部署** [skywalking源码编译部署](skywalking/skywalking-code.md)
- **skywalking持久化mysql、es部署** [skywalking持久化mysql](apache-skywalking-apm-bin/README.md)
- **skywalking集成项目使用** [skywalking集成项目使用](spring-cloud-alibaba-provider_skywalking_log/skywalking集成.md)
- **dockerfile中skywalking集成项目使用打包**
- **springboot集成skywalking日志跟踪** [springboot集成skywalking日志跟踪](spring-cloud-alibaba-provider_skywalking_log/skywalking日志收集集成.md)
- **skywalking演示示例**

**spring-cloud-alibaba-nacos-config** ： [使用nacos config实现配置中心  9003](spring-cloud-alibaba-nacos-config/nacos-config.md)
**spring-cloud-alibaba-multiple-configuration-pull** ： [使用nacos config实现多个配置拉取  9004](spring-cloud-alibaba-multiple-configuration-pull/readme.md)
**spring-cloud-alibaba-provider-config** ： 使用nacos config实现动态配置更新  9005
**spring-cloud-alibaba-provider-config** ： 使用nacos config实现动态配置更新  9006
**nacos-cluster** ： nacos集群搭建
**Sentinel入门** ： Sentinel入门搭建
**扩展Ribbon支持Nacos权重的三种方式** ： [扩展Ribbon支持Nacos权重的三种方式](https://www.itmuch.com/spring-cloud-alibaba/ribbon-nacos-weight/)
**扩展Ribbon支持Nacos集群配置** ： [扩展Ribbon支持Nacos集群配置](https://www.itmuch.com/spring-cloud-alibaba/ribbon-nacos-weight-cluster/)
**Sentinel 规则参数总结** ： [Sentinel 规则参数总结](https://www.itmuch.com/spring-cloud-alibaba/sentinel-configuration-rule/)
**Alibaba Sentinel规则持久化-推模式-手把手教程【基于Nacos】** ： [Sentinel规则持久化-推模式-手把手教程【基于Nacos】](https://www.itmuch.com/spring-cloud-alibaba/sentinel-rules-persistence-push-mode-using-nacos/)
**Alibaba Sentinel 规则持久化-拉模式-手把手教程【基于文件】** ： [Sentinel 规则持久化-拉模式-手把手教程【基于文件】](https://www.itmuch.com/spring-cloud-alibaba/sentinel-rules-persistence-pull-mode/)
**Spring Cloud Stream实现消息过滤消费** ： [Stream实现消息过滤消费](https://www.itmuch.com/spring-cloud-alibaba/spring-cloud-stream-rocketmq-filter-consume/)
**Alibaba Sentinel 配置项总结** : [Sentinel配置项总结](https://www.itmuch.com/spring-cloud-alibaba/sentinel-config-properties/)

 

SpringBoot Admin

Zipkin

将链路数据存储在Mysql数据库中

将链路数据存在在Elasticsearch中

Elasticsearch