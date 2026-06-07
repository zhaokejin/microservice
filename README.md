# Microservice
【技术项目】SpringCloud 最新版本笔记、Alibaba 体系代码、SpringBoot 整合 Demo，持续更新，欢迎交流！

个人博客：https://www.cicoding.cn

---

## Spring Boot / Spring Cloud / Spring Cloud Alibaba 版本对应关系

> 更新时间：2026-06-07

### 最新稳定版本

| 项目 | 最新版本 | 发布日期 | 备注 |
|------|---------|---------|------|
| Spring Boot | **4.0.6** | 2026-04-23 | Spring Framework 7.0.7, JDK 17+ |
| Spring Boot 3.x | **3.5.14** | 2026-04-23 | Spring Framework 6.3.x, JDK 17+ |
| Spring Cloud | **2025.1.1** | 2026-01-29 | 对应 Boot 4.0.x |
| Spring Cloud | **2025.0.2** | 2026-05-05 | 对应 Boot 3.5.x |
| Spring Cloud Alibaba | **2025.1.0.0** | 2026-02-06 | 对应 Cloud 2025.1.x + Boot 4.0.x |
| Spring Cloud Alibaba | **2025.0.0.0** | 2025-10-17 | 对应 Cloud 2025.0.x + Boot 3.5.x |

### 推荐版本组合

> **2026-06 选型建议**：新项目首选 **SCA 2025.1.0.0 + Boot 4.0.6**（最新特性线）；存量项目可继续 **SCA 2025.0.0.0 + Boot 3.5.14**（维护期更久、生态最成熟）。

| Spring Cloud Alibaba | Spring Cloud | Spring Boot | Spring Framework | JDK | 推荐度 | Sentinel 对应 | RocketMQ 对应 |
|----------------------|-------------|-------------|-----------------|-----|--------|--------------|----------------|
| **2025.1.0.0** | 2025.1.1 | **4.0.6** | 7.0.x | 17+ | ⭐⭐⭐ 最新（Boot 4.x 线） | **1.8.8+** | **5.3.0+** |
| **2025.0.0.0** | 2025.0.2 | **3.5.14** | 6.3.x | 17+ | ⭐⭐⭐ 推荐（Boot 3.5 线，生态成熟） | **1.8.6** | **5.1.4+** |
| 2023.0.3.4 | 2023.0.4 | 3.2.x / 3.3.x | 6.1.x | 17+ | ⭐⭐ 稳定（Bug 维护期） | 1.8.6 | 5.1.4 |
| 2021.0.6.2 | 2021.0.9 | 2.6.x / 2.7.x | 5.3.x | 8+ | ⭐ 旧版长期维护（仅维护期必要升级） | 1.8.0+ | 4.9.x |

### 配套中间件推荐版本（与主版本对齐）

> 第六阶段新增中间件需要与上面的主版本组合**配套使用**，避免依赖冲突。

#### 组合 A：SCA 2025.1.0.0 + Boot 4.0.6（最新线）

| 中间件 | 推荐版本 | 对应 Starter / 镜像 | 配套关系 |
|--------|---------|-------------------|---------|
| Nacos Client | 3.1.x | `spring-cloud-starter-alibaba-nacos-discovery` | Boot 4.x 适配 |
| Nacos Server | 2.5.0+ | Docker `nacos/nacos-server:v2.5.0` | 需 gRPC 9848 |
| **Sentinel Client** | **1.8.8+** | `spring-cloud-starter-alibaba-sentinel` | **↕ 配套 Sentinel Dashboard 1.8.8** |
| **Sentinel Dashboard** | **1.8.8** | Docker `bladex/sentinel-dashboard:1.8.8` | **↕ 配套 Client 1.8.8+** |
| Redis | 7.2+ | `spring-boot-starter-data-redis` | Lettuce 6.x |
| Redisson | 3.40.2+ | `redisson-spring-boot-starter:3.40.2` | Spring Data Redis 3.4+ |
| **RocketMQ Broker** | **5.3.0+** | `spring-cloud-starter-alibaba-rocketmq` | **↕ 配套 RocketMQ Dashboard + Spring Cloud Stream 4.x** |
| **RocketMQ Dashboard** | **latest** | Docker `apacherocketmq/rocketmq-dashboard` | **↕ 配套 Broker 5.3.0+** |

#### 组合 B：SCA 2025.0.0.0 + Boot 3.5.14（推荐线，存量项目首选）

| 中间件 | 推荐版本 | 对应 Starter / 镜像 | 配套关系 |
|--------|---------|-------------------|---------|
| Nacos Client | 2.5.x | `spring-cloud-starter-alibaba-nacos-discovery` | Boot 3.5 适配 |
| Nacos Server | 2.4.6 LTS | Docker `nacos/nacos-server:v2.4.6` | 2.4.6 LTS |
| **Sentinel Client** | **1.8.6** | `spring-cloud-starter-alibaba-sentinel` | **↕ 配套 Sentinel Dashboard 1.8.6** |
| **Sentinel Dashboard** | **1.8.6** | Docker `bladex/sentinel-dashboard:1.8.6` | **↕ 配套 Client 1.8.6** |
| Redis | 7.2+ | `spring-boot-starter-data-redis` | Lettuce 6.3.x |
| Redisson | 3.32.x | `redisson-spring-boot-starter:3.32.0` | Spring Data Redis 3.3+ |
| **RocketMQ Broker** | **5.1.4+** | `spring-cloud-starter-alibaba-rocketmq` | **↕ 配套 Dashboard + Spring Cloud Stream 3.x** |
| **RocketMQ Dashboard** | **stable** | Docker `apacherocketmq/rocketmq-dashboard` | **↕ 配套 Broker 5.1.4+** |

#### 组合 C：SCA 2023.0.3.4 + Boot 3.2/3.3（Bug 维护期）

| 中间件 | 推荐版本 | 对应 Starter / 镜像 | 配套关系 |
|--------|---------|-------------------|---------|
| Nacos Server | 2.3.2 LTS | Docker `nacos/nacos-server:v2.3.2` | 2.3.2 LTS |
| **Sentinel Client** | **1.8.6** | `spring-cloud-starter-alibaba-sentinel` | **↕ 配套 Sentinel Dashboard 1.8.6** |
| **Sentinel Dashboard** | **1.8.6** | Docker `bladex/sentinel-dashboard:1.8.6` | **↕ 配套 Client 1.8.6** |
| Redis | 7.0+ | `spring-boot-starter-data-redis` | Lettuce 6.2 |
| Redisson | 3.27.x | `redisson-spring-boot-starter:3.27.2` | Spring Data Redis 3.2 |
| **RocketMQ Broker** | **5.1.4** | `spring-cloud-starter-alibaba-rocketmq` | **↕ 配套 Dashboard + Stream Binder** |
| **RocketMQ Dashboard** | **stable** | Docker `apacherocketmq/rocketmq-dashboard` | **↕ 配套 Broker 5.1.4** |

### 组件兼容性速查

```
SCA 2025.1.x  ──>  Nacos 3.1.x  ──>  Sentinel 1.8.8+  ──>  RocketMQ 5.3.0+
   │                  │                  │                    │
   │                  │                  │                    ├─ Spring Cloud Stream 4.x
   │                  │                  └─ Sentinel Dashboard 1.8.8
   │                  └─ Nacos Server 2.5.0+（需 gRPC 9848）
   └─ Spring Cloud 2025.1.1 + Spring Boot 4.0.6

SCA 2025.0.x  ──>  Nacos 2.5.x  ──>  Sentinel 1.8.6   ──>  RocketMQ 5.1.4+
   │                  │                  │                    │
   │                  │                  │                    └─ Spring Cloud Stream 3.x
   │                  │                  └─ Sentinel Dashboard 1.8.6
   │                  └─ Nacos Server 2.4.6 LTS
   └─ Spring Cloud 2025.0.2 + Spring Boot 3.5.14
```

### 版本号命名规则

- **Spring Cloud** 从 2023.x 起使用日历版本号：`YYYY.MINOR.MICRO`
  - `MINOR=0` → 对应 Boot 3.x 线（如 2025.0.x）
  - `MINOR=1` → 对应 Boot 4.x 线（如 2025.1.x）
- **Spring Cloud Alibaba** 版本号在前者基础上追加第 4 位数字

### 关键变更说明

- **SCA 2025.1.0.0**：移除 bootstrap 配置，改用 `spring.config.import=nacos:...`
- **Boot 4.0**：底层升级 Spring Framework 7.0、Spring Security 7.0、Tomcat 11、Hibernate 7.2
- **2025.0.x 和 2025.1.x** 是两条并行维护线，分别对应 Boot 3.5.x 和 Boot 4.0.x
- **SCA 2025.x**：内置 RocketMQ 5.x，不再单独维护 4.x 兼容层

---

## 当前项目模块

| 版本 | 值 |
|------|-----|
| Spring Boot | 4.0.6 |
| Spring Cloud | 2025.1.1 |
| Spring Cloud Alibaba | 2025.1.0.0 |
| JDK | 17+ |

### 第一阶段～第五阶段（已完成 ✅）

| 模块 | 端口 | 说明 |
|------|------|------|
| **spring-cloud-alibaba-provider** | 9000 | 服务提供者（用户 CRUD + MyBatis + MySQL + Nacos 注册） |
| **spring-cloud-alibaba-provider-file** | 9001 | 服务提供者（文件上传 + 用户 CRUD + MyBatis + MySQL + Nacos 注册） |
| **spring-cloud-alibaba-consumer-feign** | 9010 | 服务消费者（Feign + LoadBalancer + Nacos 注册 + Sentinel 熔断） |
| **spring-cloud-alibaba-consumer-feign-file** | 9011 | 服务消费者（Feign 文件上传 + LoadBalancer + Nacos 注册） |
| **spring-cloud-alibaba-consumer-ribbon** | 9020 | 服务消费者（RestTemplate + LoadBalancer + Nacos 注册） |
| **spring-cloud-alibaba-gateway** | 9999 | API 网关（Spring Cloud Gateway + Nacos 注册 + Sentinel 网关限流 + 跨域） |
| **spring-cloud-alibaba-nacos-config** | 9030 | Nacos 配置中心示例（动态配置刷新） |
| **spring-cloud-alibaba-multiple-config** | 9031 | 多配置拉取示例（同时拉取多个 Nacos 配置文件） |
| **spring-cloud-alibaba-consul-provider** | 9040 | 服务提供者（Consul 注册中心 + MyBatis + MySQL） |
| **spring-boot-admin-server** | 8888 | Spring Boot Admin 监控服务端（SBA 4.0.4 + Boot 4.0.6） |
| **springboot-dubbo-demo** | — | Dubbo + Nacos RPC 调用示例（common-api / service-provider / service-consumer） |

### 第六阶段：生产必备中间件（✅ 实施中）

| 模块 | 端口 | 说明 |
|------|------|------|
| **spring-cloud-alibaba-rocketmq** | 9100 | RocketMQ 消息队列示例（普通/顺序/延迟消息） |

集成到现有模块的能力：
- **Provider**：`@SentinelResource` 资源保护 + `@Cacheable/@CacheEvict` Spring Cache + Redisson 分布式锁
- **Gateway**：Sentinel 网关限流 + 自定义 BlockResponse
- **Consumer-Feign**：`@FeignClient` + `FallbackFactory` 熔断降级

### 其他目录

- **docs/**：项目文档（开发日志、问题修复记录、开发路线图）
- **docs/blog/**：完整 27 篇教程 Markdown 源文件
- **skywalking/**：SkyWalking 链路追踪集成配置（Docker Compose + Java Agent）
- **docker/**：Docker Compose 挂载的中间件配置（RocketMQ broker.conf 等）

---

## Docker Compose 中间件清单

通过 `docker-compose up -d` 一键启动所有基础环境：

| 服务 | 端口 | 用途 | 阶段 |
|------|------|------|------|
| **nacos** | 8848 / 9848 | 注册中心 + 配置中心 | 1-5 |
| **mysql** | 3306 | 数据库 | 1-5 |
| **consul** | 8500 | 注册中心（替代方案） | 4 |
| **skywalking-oap / ui** | 11800 / 8080 | 链路追踪 | 5 |
| **sentinel** | 8858 | 限流熔断 Dashboard | **6** |
| **redis + redisinsight** | 6379 / 5540 | 分布式缓存 + 可视化 | **6** |
| **rocketmq-nameserver** | 9876 | 消息队列 NameServer | **6** |
| **rocketmq-broker** | 10909 / 10911 | 消息队列 Broker | **6** |
| **rocketmq-dashboard** | 8180 | 消息队列可视化 | **6** |

---

## 教程目录

### 第一阶段：环境与基础设施
- [01.环境准备与工具安装](docs/blog/01.环境准备与工具安装.md)
- [02.Docker基础设施搭建](docs/blog/02.Docker基础设施搭建.md)
- [03.Maven多模块父工程创建](docs/blog/03.Maven多模块父工程创建.md)

### 第二阶段：核心微服务开发
- [04.第一个服务提供者-用户CRUD](docs/blog/04.第一个服务提供者-用户CRUD.md)
- [05.服务消费者-Feign声明式调用](docs/blog/05.服务消费者-Feign声明式调用.md)
- [06.服务消费者-RestTemplate负载均衡调用](docs/blog/06.服务消费者-RestTemplate负载均衡调用.md)
- [07.API网关-SpringCloudGateway](docs/blog/07.API网关-SpringCloudGateway.md)

### 第三阶段：配置与高级特性
- [08.Nacos配置中心-动态配置刷新](docs/blog/08.Nacos配置中心-动态配置刷新.md)
- [09.Nacos多配置管理与环境隔离](docs/blog/09.Nacos多配置管理与环境隔离.md)
- [10.微服务文件上传-Feign跨服务传输](docs/blog/10.微服务文件上传-Feign跨服务传输.md)

### 第四阶段：运维与监控
- [11.SpringBootAdmin监控服务](docs/blog/11.SpringBootAdmin监控服务.md)
- [12.Consul注册中心替代方案](docs/blog/12.Consul注册中心替代方案.md)

### 第五阶段：进阶集成
- [13.Dubbo-RPC远程调用集成](docs/blog/13.Dubbo-RPC远程调用集成.md)
- [14.SkyWalking链路追踪集成](docs/blog/14.SkyWalking链路追踪集成.md)
- [15.DockerCompose一键部署](docs/blog/15.DockerCompose一键部署.md)

### 第六阶段：生产必备中间件 ⭐ NEW
- [16.Sentinel熔断限流](docs/blog/16.Sentinel熔断限流.md) ✅
- [17.Redis分布式缓存](docs/blog/17.Redis分布式缓存.md) ✅
- [18.RocketMQ消息队列](docs/blog/18.RocketMQ消息队列.md) ✅

### 后续阶段（规划中）
- 第七阶段：Seata 分布式事务、Spring Security OAuth2 统一认证、OpenTelemetry 可观测标准
- 第八阶段：XXL-Job 分布式调度、Prometheus + Grafana 指标监控、ELK 日志中心、Cilium 服务网格、Higress 下一代网关、ChaosBlade 混沌工程

> 完整路线图见 [docs/development-roadmap.md](docs/development-roadmap.md)

---

## 快速开始

```bash
# 1. 启动所有基础中间件
docker-compose up -d

# 2. 启动服务（按依赖顺序）
mvn spring-boot:run -pl spring-cloud-alibaba-provider
mvn spring-boot:run -pl spring-cloud-alibaba-consumer-feign
mvn spring-boot:run -pl spring-cloud-alibaba-gateway

# 3. 通过网关测试
curl http://localhost:9999/user/list
```

## 技术交流

- 个人博客：https://www.cicoding.cn
- GitHub：详见项目仓库
