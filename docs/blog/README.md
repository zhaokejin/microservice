# Spring Cloud Alibaba 2025 微服务实战教程

> 最新版本栈：Spring Boot 4.0.6 + Spring Cloud 2025.1.1 + Spring Cloud Alibaba 2025.1.0.0 + JDK 17
>
> 作者博客：[https://www.cicoding.cn](https://www.cicoding.cn)

---

## 📚 学习路线图

本教程按照**由浅入深、从基础到进阶**的顺序编排，建议按序号逐一学习：

### 第一阶段：环境与基础设施（01-03）

| 序号 | 教程 | 核心内容 |
|------|------|---------|
| [01](./01.环境准备与工具安装.md) | 环境准备与工具安装 | JDK 17、Maven、Docker Desktop、IDE 配置 |
| [02](./02.Docker基础设施搭建.md) | Docker 基础设施搭建 | Nacos 2.5.0 + MySQL 8.0 一键启动 |
| [03](./03.Maven多模块父工程创建.md) | Maven 多模块父工程创建 | BOM 依赖管理、版本选型、项目骨架 |

### 第二阶段：核心微服务开发（04-07）

| 序号 | 教程 | 核心内容 |
|------|------|---------|
| [04](./04.第一个服务提供者-用户CRUD.md) | 第一个服务提供者 | 用户 CRUD + MyBatis + Nacos 注册 |
| [05](./05.服务消费者-Feign声明式调用.md) | Feign 声明式调用 | `@FeignClient` + LoadBalancer 服务消费 |
| [06](./06.服务消费者-RestTemplate负载均衡调用.md) | RestTemplate 负载均衡 | `@LoadBalanced` + RestTemplate 编程式调用 |
| [07](./07.API网关-SpringCloudGateway.md) | Spring Cloud Gateway | 路由转发、跨域、StripPrefix |

### 第三阶段：配置与高级特性（08-10）

| 序号 | 教程 | 核心内容 |
|------|------|---------|
| [08](./08.Nacos配置中心-动态配置刷新.md) | Nacos 配置中心 | `@RefreshScope`、动态刷新、SCA 2025 变更 |
| [09](./09.Nacos多配置管理与环境隔离.md) | 多配置与 Namespace | 多配置拉取、环境隔离、配置优先级 |
| [10](./10.微服务文件上传-Feign跨服务传输.md) | 文件上传 | feign-form、`@RequestPart` 跨服务传输 |

### 第四阶段：运维与监控（11-12）

| 序号 | 教程 | 核心内容 |
|------|------|---------|
| [11](./11.SpringBootAdmin监控服务.md) | Spring Boot Admin | SBA 4.0.4、服务监控、Security 配置 |
| [12](./12.Consul注册中心替代方案.md) | Consul 注册中心 | Consul 替代 Nacos、零代码切换 |

### 第五阶段：进阶集成（13-15）

| 序号 | 教程 | 核心内容 |
|------|------|---------|
| [13](./13.Dubbo-RPC远程调用集成.md) | Dubbo RPC 集成 | Dubbo 3.3 + Nacos、`@DubboReference` |
| [14](./14.SkyWalking链路追踪集成.md) | SkyWalking 链路追踪 | Java Agent 无侵入、拓扑图、性能监控 |
| [15](./15.DockerCompose一键部署.md) | Docker Compose 部署 | Dockerfile 多阶段构建、集群编排 |

### 第六阶段：生产必备中间件（16-18）🥇

| 序号 | 教程 | 核心内容 |
|------|------|---------|
| 16 | Sentinel 熔断限流 | QPS 限流、熔断降级、热点参数限流、Nacos 规则持久化 |
| 17 | Redis 分布式缓存 | Spring Cache、缓存穿透/击穿/雪崩、Redisson 分布式锁 |
| 18 | RocketMQ 消息队列 | 普通/顺序/延迟/事务消息、Spring Cloud Stream |

### 第七阶段：架构进阶能力（19-21）🥈

| 序号 | 教程 | 核心内容 |
|------|------|---------|
| 19 | Seata 分布式事务 | AT 模式无侵入、TCC 手动补偿、全局事务监控 |
| 20 | Spring Security OAuth2 统一认证 | 授权服务器、JWT 令牌、Gateway 全局鉴权、RBAC |
| 21 | OpenTelemetry 可观测标准 | OTLP 协议、Traces/Metrics/Logs 三支柱、与 SkyWalking 共存 |

### 第八阶段：大厂级完善（22-27）🥉

| 序号 | 教程 | 核心内容 |
|------|------|---------|
| 22 | XXL-Job 分布式调度 | 分片广播、失败重试、动态 Cron、GLUE 模式 |
| 23 | Prometheus + Grafana 指标监控 | Micrometer、PromQL、Grafana Dashboard、AlertManager 告警 |
| 24 | ELK 日志中心 | Filebeat 采集、Elasticsearch 检索、Kibana 可视化 |
| 25 | 服务网格入门 | Cilium eBPF、Hubble 可观测、Istio Ambient Mesh |
| 26 | 下一代 API 网关 | Higress + Envoy + Wasm 插件、AI 网关能力 |
| 27 | ChaosBlade 混沌工程 | 故障注入演练、验证 Sentinel/Seata 韧性 |

---

## 🚀 快速开始

```bash
# 1. 启动基础设施
docker compose up -d nacos mysql

# 2. 初始化数据库（连接 MySQL 执行）
# CREATE TABLE t_user (...)

# 3. 启动服务提供者
mvn spring-boot:run -pl spring-cloud-alibaba-provider

# 4. 测试
curl http://localhost:9000/user/1
```

---

## 🏗️ 项目模块总览

| 模块 | 端口 | 功能 |
|------|------|------|
| spring-cloud-alibaba-provider | 9000 | 用户 CRUD 服务提供者 |
| spring-cloud-alibaba-provider-file | 9001 | 文件上传服务提供者 |
| spring-cloud-alibaba-consumer-feign | 8082 | Feign 服务消费者 |
| spring-cloud-alibaba-consumer-feign-file | 8083 | Feign 文件上传消费者 |
| spring-cloud-alibaba-consumer-ribbon | 8081 | RestTemplate 服务消费者 |
| spring-cloud-alibaba-gateway | 7000 | API 网关 |
| spring-cloud-alibaba-nacos-config | 9003 | Nacos 配置中心示例 |
| spring-cloud-alibaba-multiple-config | 9004 | 多配置拉取示例 |
| spring-cloud-alibaba-consul-provider | 9101 | Consul 注册中心示例 |
| spring-boot-admin-server | 8000 | SBA 监控服务端 |
| springboot-dubbo-demo | 9005/8084 | Dubbo RPC 示例 |

| 基础设施 | 端口 |
|---------|------|
| Nacos | 8848 / 9848 |
| MySQL | 3306 |
| Consul | 8500 |
| SkyWalking OAP | 11800 / 12800 |
| SkyWalking UI | 8080 |

---

## 📝 其他文档

- [开发路线图](../development-roadmap.md) —— 中间件扩展规划与执行记录
- [开发日志](../development-log.md)
- [问题修复记录](../issue-fixes.md)
