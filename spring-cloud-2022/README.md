# 版本说明

- Spring Boot 3.0.2.RELEASE
- Spring Cloud 2022.0.0
- Spring Cloud Alibaba 2022.0.0.0
- Nacos 2.2.1
- Spring Cloud Consul

## Nacos2.2.1下载
https://github.com/alibaba/nacos/releases/download/2.2.1/nacos-server-2.2.1.zip
https://github.com/alibaba/nacos/releases/download/2.2.3/nacos-server-2.2.3.zip


http://localhost:7000/spring-cloud-alibaba-provider/user
http://localhost:8082/fegin/user/1
http://localhost:8081/ribbon/user/1
http://localhost:7000/spring-cloud-alibaba-consumer-feign/fegin/user/1


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

**扩展Ribbon支持Nacos权重的三种方式** ： [扩展Ribbon支持Nacos权重的三种方式](https://www.itmuch.com/spring-cloud-alibaba/ribbon-nacos-weight/)
**扩展Ribbon支持Nacos集群配置** ： [扩展Ribbon支持Nacos集群配置](https://www.itmuch.com/spring-cloud-alibaba/ribbon-nacos-weight-cluster/)
**Sentinel 规则参数总结** ： [Sentinel 规则参数总结](https://www.itmuch.com/spring-cloud-alibaba/sentinel-configuration-rule/)
**Alibaba Sentinel规则持久化-推模式-手把手教程【基于Nacos】** ： [Sentinel规则持久化-推模式-手把手教程【基于Nacos】](https://www.itmuch.com/spring-cloud-alibaba/sentinel-rules-persistence-push-mode-using-nacos/)
**Alibaba Sentinel 规则持久化-拉模式-手把手教程【基于文件】** ： [Sentinel 规则持久化-拉模式-手把手教程【基于文件】](https://www.itmuch.com/spring-cloud-alibaba/sentinel-rules-persistence-pull-mode/)
**Spring Cloud Stream实现消息过滤消费** ： [Stream实现消息过滤消费](https://www.itmuch.com/spring-cloud-alibaba/spring-cloud-stream-rocketmq-filter-consume/)
**Alibaba Sentinel 配置项总结** : [Sentinel配置项总结](https://www.itmuch.com/spring-cloud-alibaba/sentinel-config-properties/)

### Gateway报503的原因（nocas可以正常注册）
1.springcloud与spring-cloud-alibaba依赖，版本不一致；
2.由于springcloud2020弃用了Ribbon，因此Alibaba在2021版本nacos中删除了Ribbon的jar包，因此无法通过lb路由到指定微服务，出现了503情况。加入这个依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```
3.最蠢的：你要转发的路由对应的模块得启动！
参考：https://blog.csdn.net/m0_68177111/article/details/129474531


SpringBoot Admin

Zipkin

将链路数据存储在Mysql数据库中

将链路数据存在在Elasticsearch中

Elasticsearch
