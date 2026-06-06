# Spring Cloud Alibaba 2025 微服务示例

> 基于 **Spring Boot 4.0.6 / Spring Cloud 2025.1.1 / Spring Cloud Alibaba 2025.1.0.0 / JDK 17** 的微服务全家桶示例项目。

---

## 技术栈

| 组件 | 版本 |
|------|------|
| Spring Boot | 4.0.6 |
| Spring Cloud | 2025.1.1 |
| Spring Cloud Alibaba | 2025.1.0.0 |
| Nacos | 2.5.0（Docker Compose） |
| Dubbo | 3.3.0 |
| Spring Boot Admin | 4.0.4 |
| MyBatis | 4.0.1 |
| MySQL Connector | mysql-connector-j |
| Feign Form | 3.8.0 |
| JDK | 17 |

---

## 模块清单

### 基础设施

Nacos 注册配置中心通过 `docker-compose.yml` 启动，不作为单独的 Maven 模块管理。

```bash
docker compose up -d nacos
```

**控制台**：http://localhost:8848/nacos （认证已关闭，直接点击登录即可）

| 服务 | 端口 | 说明 |
|------|------|------|
| Nacos | 8848/9848/9849 | 注册中心 + 配置中心 |

### 服务提供者

| 模块 | 端口 | 名称 | 描述 |
|------|------|------|------|
| spring-cloud-alibaba-provider | 9000 | 用户服务提供者 | 用户 CRUD + MyBatis + MySQL，注册到 Nacos |
| spring-cloud-alibaba-provider-file | 9001 | 文件上传服务提供者 | 在用户 CRUD 基础上增加文件上传接口 |

### 服务消费者

| 模块 | 端口 | 名称 | 描述 |
|------|------|------|------|
| spring-cloud-alibaba-consumer-feign | 8082 | Feign 服务消费者 | 通过 Feign + LoadBalancer 调用 Provider |
| spring-cloud-alibaba-consumer-feign-file | 8083 | 文件上传消费者 | 通过 Feign 调用文件上传接口 |

### 网关

| 模块 | 端口 | 名称 | 描述 |
|------|------|------|------|
| spring-cloud-alibaba-gateway | 7000 | API 网关 | Spring Cloud Gateway + CORS + 路由转发 |

### 配置中心

| 模块 | 端口 | 名称 | 描述 |
|------|------|------|------|
| spring-cloud-alibaba-nacos-config | 9003 | Nacos 配置中心示例 | REST API 拉取 + 定时轮询刷新配置 |
| spring-cloud-alibaba-multiple-config | 9004 | 多配置拉取示例 | REST API + 多配置文件 + 命名空间 |

### 监控

| 模块 | 端口 | 名称 | 描述 |
|------|------|------|------|
| spring-boot-admin-server | 8000 | Spring Boot Admin 监控 | SBA 4.0.4，可视化监控所有注册到 Nacos 的微服务 |

### RPC 调用

| 模块 | 端口 | 名称 | 描述 |
|------|------|------|------|
| springboot-dubbo-demo | 9005/8084 | Dubbo RPC 示例 | Dubbo + Nacos 的 RPC 调用演示（含 API/Provider/Consumer） |

---

## 模块详细说明

### Nacos（Docker Compose）

Nacos 注册配置中心，作为整个微服务体系的服务发现和配置管理基础设施。通过 `docker-compose.yml` 以 standalone 模式启动，无需额外下载 Nacos 安装包。

```bash
docker compose up -d nacos
```

**控制台**：http://localhost:8848/nacos （认证已关闭，无需输入用户名密码）

**暴露端口**：8848（HTTP 控制台）、9848（gRPC 客户端通信）、9849（gRPC 服务端通信）

---

### spring-cloud-alibaba-provider

用户管理服务提供者（Producer）。提供完整的 RESTful CRUD 接口，使用 MyBatis 操作 MySQL 数据库，注册到 Nacos 供其他服务发现和调用。

**API 端点**：
- `POST /user` —— 添加用户
- `DELETE /user` —— 删除用户
- `PUT /user` —— 更新用户
- `GET /user` —— 查询所有用户
- `GET /user/{id}` —— 按 ID 查询用户

---

### spring-cloud-alibaba-provider-file

文件上传服务提供者。在用户 CRUD 功能基础上，增加了文件上传接口，支持通过 `MultipartFile` 接收文件并保存到本地磁盘。上传路径可通过 `file.upload-dir` 配置项自定义。

**API 端点**：
- 继承 Provider 的全部 CRUD 接口
- `POST /user/upload` —— 上传文件

---

### spring-cloud-alibaba-consumer-feign

基于 Feign 的服务消费者（Consumer）。通过 Feign 声明式 HTTP 客户端 + Spring Cloud LoadBalancer 负载均衡调用 Provider 服务，演示微服务间的服务发现与远程调用。

**API 端点**：
- `GET /feign/user/{id}` —— 通过 Feign 调用 Provider 查询用户

---

### spring-cloud-alibaba-consumer-feign-file

文件上传消费端。通过 Feign 调用 Provider-File 的文件上传接口，使用 `feign-form-spring` 支持 `MultipartFile` 跨服务传输。

**API 端点**：
- `POST /feign/upload` —— 通过 Feign 转发文件上传请求

---

### spring-cloud-alibaba-gateway

基于 Spring Cloud Gateway 的 API 网关。提供统一入口、路由转发和跨域配置。使用 Nacos 服务发现自动路由到下游服务。

**路由规则**：
- `/api/user/**` → `spring-cloud-alibaba-provider`（转发时自动去除 `/api` 前缀）

---

### spring-cloud-alibaba-nacos-config

Nacos 配置中心使用示例。展示 SCA 2025 中 Nacos 配置的标准使用方式，并实现动态刷新。

**背景**：SCA 2025.1.0.0 内置的 Nacos Client 3.x 通过 gRPC 与 Nacos 2.5.x Server 通信时，存在协议兼容问题（`getConfig()` 返回空内容）。因此本模块通过 `NacosRestConfigInitializer` 在启动时使用 REST API 拉取配置，并由 `NacosRestConfigRefresher` 定时轮询（默认 5s）实现动态刷新。

**核心功能**：
- `spring.config.import: optional:nacos:...?refresh=true` 满足 SCA starter 强制要求
- `NacosRestConfigInitializer` 通过 v1 REST API 拉取配置并注入 `Environment`
- `NacosRestConfigRefresher` 每 5 秒轮询 Nacos 检测变更，发布 `RefreshEvent` 触发 `@RefreshScope` bean 重建
- 轮询间隔可通过 `spring.cloud.nacos.config.refresh-interval-ms` 配置

**API 端点**：
- `GET /profile` —— 通过 `@Value` 读取配置值
- `GET /profile/v2` —— 通过 `@ConfigurationProperties` 读取配置值

**Nacos 配置**（需在 Nacos 控制台创建）：
- Data ID：`spring-cloud-alibaba-nacos-config.properties`
- 配置内容：`profile=dev`

---

### spring-cloud-alibaba-multiple-config

多配置拉取示例。演示如何从 Nacos 同时读取多个配置文件，适用于多团队或多环境共享配置的场景。

**背景**：与 nacos-config 模块相同，使用 REST API + 定时轮询的方案绕过 SCA 2025 gRPC 兼容问题。额外演示了命名空间（namespace）的使用。

**核心功能**：
- `spring.config.import` 同时导入 `cicoding-provider.properties` 和 `cicoding-consumer.properties`
- 通过命名空间 `3d3be08a-3670-401d-80d0-ac7ddc48115d` 隔离配置
- `NacosRestConfigInitializer` 通过 v1 REST API（带 `tenant=` 参数）分别拉取
- `NacosRestConfigRefresher` 同时轮询两个配置，统一发布一次 `RefreshEvent`
- 通过 `@Value` 分别注入 `profile.provider` 和 `profile.consumer`

**API 端点**：
- `GET /profile` —— 通过 `@Value` 读取两个配置值
- `GET /profile/v2` —— 通过 `@ConfigurationProperties` 读取

**Nacos 配置**（需在指定命名空间下创建）：
- Data ID：`cicoding-provider.properties`，内容：`profile.provider=provider-team-config`
- Data ID：`cicoding-consumer.properties`，内容：`profile.consumer=consumer-team-config`

---

### spring-boot-admin-server

Spring Boot Admin 监控服务端。自动发现并监控所有注册到 Nacos 的微服务实例，提供运行状态、JVM 指标、日志级别管理、环境变量查看等可视化管理功能。使用 **SBA 4.0.4**，完全兼容 Boot 4.0.6。

**控制台**：http://localhost:8000 （用户名/密码：admin/admin）

**功能**：
- 服务实例上下线监控
- JVM 内存、线程、GC 指标
- 日志级别动态调整
- Spring Beans 查看
- HTTP 请求追踪

---

### springboot-dubbo-demo

Spring Boot + Dubbo + Nacos 的 RPC 调用示例，演示高性能 RPC 通信方案。

**子模块**：

| 子模块 | 端口 | 说明 |
|--------|------|------|
| common-api | — | 公共接口定义（`SayService`） |
| service-provider | 9005 | Dubbo 服务提供者，实现 `SayService`，暴露 Dubbo 协议（20880） |
| service-consumer | 8084 | Dubbo 服务消费者，通过 `@DubboReference` 注入远程服务 |

**API 端点**：
- `GET /demo/say/sayHello?name=xxx` —— Dubbo RPC 调用示例

---

## 依赖关系

```
Nacos（Docker Compose 基础设施）
    │
    ├── spring-cloud-alibaba-provider（用户服务）
    │       │
    │       ├── spring-cloud-alibaba-consumer-feign（Feign 调用）
    │       └── spring-cloud-alibaba-gateway（网关路由）
    │
    ├── spring-cloud-alibaba-provider-file（文件上传服务）
    │       │
    │       └── spring-cloud-alibaba-consumer-feign-file（Feign 文件上传）
    │
    ├── spring-cloud-alibaba-nacos-config（配置中心示例）
    ├── spring-cloud-alibaba-multiple-config（多配置示例）
    ├── spring-boot-admin-server（监控中心）
    └── springboot-dubbo-demo（Dubbo RPC）
```

---

## 启动顺序

1. **Nacos** —— 注册配置中心，最先启动
   ```bash
   docker compose up -d nacos
   ```
2. **spring-cloud-alibaba-provider** —— 核心用户服务
3. **spring-cloud-alibaba-consumer-feign** —— 验证服务间调用
4. **spring-cloud-alibaba-gateway** —— 验证网关路由
5. **spring-cloud-alibaba-nacos-config** —— 配置中心示例
6. **spring-cloud-alibaba-multiple-config** —— 多配置拉取
7. **spring-boot-admin-server** —— 监控中心
8. **spring-cloud-alibaba-provider-file** —— 文件上传服务
9. **spring-cloud-alibaba-consumer-feign-file** —— 文件上传消费
10. **springboot-dubbo-demo** —— Dubbo RPC

---

## 环境要求

- JDK 17+
- Maven 3.6+
- Docker Desktop（运行 Nacos）
- MySQL 8.0+

## 快速开始

```bash
# 1. 启动 Nacos
cd spring-cloud-alibaba-2025
docker compose up -d nacos

# 2. 启动 MySQL 并创建数据库
CREATE DATABASE mytest;

# 3. 编译项目
mvn clean compile

# 4. 按上述顺序启动各模块
```

---

## 参考文档

- [开发日志](docs/development-log.md) —— 各模块实现细节与依赖变更
- [问题修复记录](docs/issue-fixes.md) —— 版本兼容性问题与故障排查</｜DSML｜parameter>
