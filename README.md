# Microservice
【技术项目】SpringCloud 最新版本笔记、Alibaba 体系代码、SpringBoot 整合 Demo，持续更新，欢迎交流！

个人博客：https://www.cicoding.cn

---

## Spring Boot / Spring Cloud / Spring Cloud Alibaba 版本对应关系

> 更新时间：2026-06-04

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

| Spring Cloud Alibaba | Spring Cloud | Spring Boot | Spring Framework | JDK | 推荐度 |
|----------------------|-------------|-------------|-----------------|-----|--------|
| **2025.1.0.0** | 2025.1.1 | **4.0.6** | 7.0.x | 17+ | 最新（Boot 4.x 线） |
| **2025.0.0.0** | 2025.0.2 | **3.5.14** | 6.3.x | 17+ | 推荐（Boot 3.5 线） |
| 2023.0.3.4 | 2023.0.4 | 3.2.x / 3.3.x | 6.1.x | 17+ | 稳定（维护期） |
| 2021.0.6.2 | 2021.0.9 | 2.6.x / 2.7.x | 5.3.x | 8+ | 旧版长期维护 |

### 版本号命名规则

- **Spring Cloud** 从 2023.x 起使用日历版本号：`YYYY.MINOR.MICRO`
  - `MINOR=0` → 对应 Boot 3.x 线（如 2025.0.x）
  - `MINOR=1` → 对应 Boot 4.x 线（如 2025.1.x）
- **Spring Cloud Alibaba** 版本号在前者基础上追加第 4 位数字

### 关键变更说明

- **SCA 2025.1.0.0**：移除 bootstrap 配置，改用 `spring.config.import=nacos:...`
- **Boot 4.0**：底层升级 Spring Framework 7.0、Spring Security 7.0、Tomcat 11、Hibernate 7.2
- **2025.0.x 和 2025.1.x** 是两条并行维护线，分别对应 Boot 3.5.x 和 Boot 4.0.x

---

## 当前项目模块

| 版本 | 值 |
|------|-----|
| Spring Boot | 4.0.6 |
| Spring Cloud | 2025.1.1 |
| Spring Cloud Alibaba | 2025.1.0.0 |
| JDK | 17+ |

子模块：
- **spring-cloud-alibaba-provider**：服务提供者（用户 CRUD + MyBatis + MySQL + Nacos 注册）
- **spring-cloud-alibaba-provider-file**：服务提供者（文件上传 + 用户 CRUD + MyBatis + MySQL + Nacos 注册）
- **spring-cloud-alibaba-consumer-feign**：服务消费者（Feign + LoadBalancer + Nacos 注册）
- **spring-cloud-alibaba-consumer-feign-file**：服务消费者（Feign 文件上传 + LoadBalancer + Nacos 注册）
- **spring-cloud-alibaba-consumer-ribbon**：服务消费者（RestTemplate + LoadBalancer + Nacos 注册）
- **spring-cloud-alibaba-gateway**：API 网关（Spring Cloud Gateway + Nacos 注册 + 跨域配置）
- **spring-cloud-alibaba-nacos-config**：Nacos 配置中心示例（动态配置刷新）
- **spring-cloud-alibaba-multiple-config**：多配置拉取示例（同时拉取多个 Nacos 配置文件）
- **spring-cloud-alibaba-consul-provider**：服务提供者（Consul 注册中心 + MyBatis + MySQL）
- **spring-boot-admin-server**：Spring Boot Admin 监控服务端（SBA 4.0.4 + Boot 4.0.6）
- **springboot-dubbo-demo**：Dubbo + Nacos RPC 调用示例（common-api / service-provider / service-consumer）

其他目录：
- **docs/**：项目文档（开发日志、问题修复记录）
- **skywalking/**：SkyWalking 链路追踪集成配置（Docker Compose + Java Agent）

