# 开发日志

> 项目：Spring Cloud Alibaba 2025 微服务项目

---

## 初始状态

迁移开始时已有以下模块：

| 模块 | 端口 | 状态 |
|------|------|------|
| nacos-server | 8848 | ✅ 嵌入式 Nacos |
| spring-cloud-alibaba-provider | 9000 | ✅ 用户 CRUD |
| spring-cloud-alibaba-consumer-feign | 8082 | ✅ Feign 消费 |
| spring-cloud-alibaba-gateway | 7000 | ✅ Gateway 网关 |

父 POM 版本栈：Boot 4.0.6 / Cloud 2025.1.1 / SCA 2025.1.0.0 / JDK 17

---

## 新增模块

### 第 5 步：spring-cloud-alibaba-nacos-config（端口 9003）

- **功能**：Nacos 配置中心示例，演示 `@RefreshScope` + `@Value` 动态刷新配置
- **关键实现**：
  - 使用 `spring.config.import: optional:nacos:${spring.application.name}.properties` 替代旧版 `bootstrap.properties`
  - `ConfigClientController` 用 `@RefreshScope` 注解实现热更新
  - 依赖 `spring-cloud-starter-alibaba-nacos-config` + `spring-cloud-starter-alibaba-nacos-discovery`
- **包路径**：`cn.cicoding.nacos.config`

### 第 6 步：spring-cloud-alibaba-multiple-config（端口 9004）

- **功能**：多配置拉取示例，从 Nacos 同时读取多个配置文件
- **关键实现**：
  - `spring.config.import` 导入 `cicoding-provider.properties` 和 `cicoding-consumer.properties`
  - `ConfigMultipleController` 读取 `profile.provider` 和 `profile.consumer` 两个配置项
  - 支持 namespace 隔离（`3d3be08a-3670-401d-80d0-ac7ddc48115d`）
- **包路径**：`cn.cicoding.multiple.config`

### 第 7 步：spring-boot-admin-server（端口 8000）

- **功能**：Spring Boot Admin 监控服务端，可视化监控所有注册到 Nacos 的微服务
- **版本**：SBA 4.0.4（兼容 Boot 4.0.6）
- **关键实现**：
  - `@EnableAdminServer` + `@EnableDiscoveryClient` 注解
  - `SecuritySecureConfig`：基于 `SecurityFilterChain` Bean（Spring Security 6+ API）
  - 放行 `/actuator/**` 避免自身健康检查被拦截
  - 安全认证：admin/admin
- **包路径**：`cn.cicoding.admin`
- **版本调研**：详见 [issue-fixes.md](issue-fixes.md#1-sba-版本兼容性问题)

### 第 8 步：spring-cloud-alibaba-provider-file（端口 9001）

- **功能**：文件上传服务提供者，在用户 CRUD 基础上增加文件上传接口
- **关键实现**：
  - 复用 provider 的 MyBatis + MySQL 架构
  - `UserController.upload()`：接收 `MultipartFile`，保存到 `file.upload-dir` 配置的目录
  - 上传路径通过 `@Value("${file.upload-dir:D:/}")` 可配置
  - `spring.servlet.multipart` 配置限制：max 10MB
- **包路径**：`cn.cicoding.provider.file`

### 第 9 步：spring-cloud-alibaba-consumer-feign-file（端口 8083）

- **功能**：通过 Feign 调用文件上传接口
- **关键实现**：
  - `feign-form-spring` 3.8.0 支持 `MultipartFile` 跨服务传输
  - `MultipartSupportConfig`：注册 `SpringFormEncoder`
  - `UserFeignClient`：`@RequestPart("file")` + `consumes = MULTIPART_FORM_DATA_VALUE`
- **包路径**：`cn.cicoding.consumer.file`

### 第 10 步：springboot-dubbo-demo（端口 9005/8084）

- **功能**：Spring Boot + Dubbo + Nacos RPC 调用示例
- **版本**：Dubbo 3.3.0
- **子模块结构**：

  | 子模块 | 端口 | 说明 |
  |--------|------|------|
  | common-api | — | 公共 API：`SayService` 接口 |
  | service-provider | 9005 | Dubbo `@DubboService` 暴露 RPC |
  | service-consumer | 8084 | `@DubboReference` 注入远程服务 |

- **关键实现**：
  - `dubbo.registry.address: nacos://127.0.0.1:8848`
  - `dubbo.protocol: dubbo:20880`
  - 同时保留 `spring.cloud.nacos.discovery` 用于 Spring Cloud 服务发现
- **包路径**：`cn.cicoding.dubbo.api/provider/consumer`

### 第 11 步：Docker + SkyWalking 运维集成

- **Docker**：在 `spring-cloud-alibaba-provider` 根目录添加 `Dockerfile`（多阶段构建，eclipse-temurin:17-jre）
- **SkyWalking**：添加 `logback-spring.xml`，日志格式包含 `%X{traceId}`，运行时通过 `-javaagent` 启动参数集成

---

## 未迁移模块

| 原模块 | 原因 |
|--------|------|
| Nacos Discovery + Config 老 API | `@NacosValue`/`@NacosInjected` 已废弃，替代方案为步骤 5/6 |
| Ribbon 消费者 | Ribbon 已进入维护模式，Spring Cloud LoadBalancer 已替代 |
| Consul 服务提供者 | 优先级最低，非核心技术栈 |

---

## 依赖变更记录

| 旧版 | 新版 |
|------|------|
| `mysql:mysql-connector-java` | `com.mysql:mysql-connector-j` |
| `javax.servlet:javax.servlet-api` | `jakarta.servlet:jakarta.servlet-api` |
| `spring-cloud-starter-netflix-ribbon` | `spring-cloud-starter-loadbalancer` |
| `spring-cloud-starter-bootstrap` | 移除（使用 `spring.config.import`） |
| `WebSecurityConfigurerAdapter` | `SecurityFilterChain` Bean |
| `.antMatchers()` | `.requestMatchers()` |
| `bootstrap.yml/bootstrap.properties` | `application.yml` + `spring.config.import` |
| `@NacosValue`/`@NacosInjected` | `@Value` + `@RefreshScope` |
| `MediaType.APPLICATION_JSON_UTF8_VALUE` | `MediaType.APPLICATION_JSON_VALUE` |

---

## 编码规范

- Model 类统一使用 Lombok `@Data`
- 所有子模块继承父 POM（除 admin-server 版本调研期短暂独立）
- 配置文件统一使用 `.yml` 格式
- 包命名模式：`cn.cicoding.<模块简名>`
- 启动类命名：`SpringCloudAlibaba<模块名>Application`
