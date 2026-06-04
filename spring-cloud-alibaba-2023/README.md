# Spring Cloud Alibaba

| Spring Cloud Alibaba Version | Spring Cloud Version  | Spring Boot Version | **Nacos Version** | **Sentinel Version** | RocketMQ Version | **Seata Version** |
|------------------------------| --------------------- |---------------------| ----------------- | -------------------- | ---------------- | ----------------- |
| 2023.0.1.2                   | Spring Cloud 2023.0.3 | 3.3.5               | 2.3.2             | 1.8.6                | 5.1.4            | 2.0.0             |

## Nacos下载V2.3.2

https://nacos.io/download/release-history/

**spring-cloud-alibaba-provider** ： 服务注册（生产者）  9000



<spring-cloud.version>2023.0.3</spring-cloud.version>
<spring-cloud-alibaba.version>2023.0.1.2</spring-cloud-alibaba.version>
<spring-boot-admin.version>3.3.5</spring-boot-admin.version>



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