# 问题修复记录

> 项目：Spring Cloud Alibaba 2025 微服务迁移
> 记录时间：2026-06-06

---

## 1. SBA 版本兼容性问题

### 现象

编译时找不到 `de.codecentric:spring-boot-admin-starter-server:3.5.1`，不存在此版本。

### 排查过程

**第一次尝试**：调研发现 SBA 最新版本为 3.4.1，无 3.5.1 或 4.x 版本。SBA 3.4.1 仅兼容 Boot 3.4.x。

解决方向：将 admin-server 改为独立项目，使用 Boot 3.4.5 + Cloud 2024.0.x + SCA 2023.0.3.4。

```xml
<!-- 独立项目 pom.xml 版本栈 -->
<spring-boot.version>3.4.5</spring-boot.version>
<spring-cloud-alibaba.version>2023.0.3.4</spring-cloud-alibaba.version>
<spring-boot-admin.version>3.4.1</spring-boot-admin.version>
```

**结果**：编译通过，启动报错 —— SCA 2023.0.3.4 不允许 Boot 3.4.5（仅支持 [3.2.x, 3.3.x]）。

```
Spring Boot [3.4.5] is not compatible with this Spring Cloud release train
Change Spring Boot version to one of the following versions [3.2.x, 3.3.x]
```

**第二次尝试**：重新调研 Maven Central，发现 **SBA 4.0.4 已发布**（最新版 `4.0.4`），完全兼容 Boot 4.x！

### 最终方案

使用 SBA 4.0.4，回归父 POM 统一编译：

```xml
<!-- pom.xml -->
<parent>
    <groupId>cn.cicoding</groupId>
    <artifactId>spring-cloud-alibaba-2025</artifactId>
    <version>1.0.0</version>
</parent>

<properties>
    <spring-boot-admin.version>4.0.4</spring-boot-admin.version>
</properties>
```

### 结论

| 事项 | 值 |
|------|-----|
| 根因 | 第一次调研未搜到 SBA 4.x 版本，采用了不兼容的版本栈 |
| 修复 | 升级到 SBA 4.0.4，继承父 POM 统一编译 |
| 验证 | `mvn compile` 全量通过 ✅ |

### 影响范围

- `spring-boot-admin-server/pom.xml`：改为继承父 POM，版本 4.0.4
- `pom.xml`：重新添加 `spring-boot-admin-server` 到 modules
- `README.md`：移除"独立项目"警告，更新版本号
- `DEVELOPMENT-GUIDE.md`：更新版本说明

---

## 2. SBA Admin Server 自身显示 DOWN

### 现象

`spring-boot-admin-server` 启动后，在 SBA 控制台中自身实例状态显示为 DOWN。

### 根因

`SecuritySecureConfig` 使用 `anyRequest().authenticated()` 保护了所有端点，包括 `/actuator/health`。SBA 通过 Nacos 发现自身后，尝试访问 `/actuator/health` 获取健康状态，请求被 Security 拦截返回 401 → SBA 判定实例为 DOWN。

```java
// 问题代码
http.authorizeHttpRequests(auth -> auth
    .requestMatchers("/assets/**").permitAll()
    .requestMatchers("/login", "/logout").permitAll()
    .anyRequest().authenticated()  // ← /actuator/health 被拦截
);
```

### 修复

放行 actuator 端点，并关闭其 CSRF 校验：

```java
// 修复后
http
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/assets/**").permitAll()
        .requestMatchers("/login", "/logout").permitAll()
        .requestMatchers("/actuator/**").permitAll()  // ← 放行
        .anyRequest().authenticated()
    )
    .formLogin(Customizer.withDefaults())
    .httpBasic(Customizer.withDefaults())
    .csrf(csrf -> csrf.ignoringRequestMatchers("/actuator/**"));  // ← 关 CSRF
```

### 结论

| 事项 | 值 |
|------|-----|
| 根因 | Security 拦截了 SBA 自身的 actuator 健康检查请求 |
| 修复 | `.requestMatchers("/actuator/**").permitAll()` |
| 文件 | `SecuritySecureConfig.java:18-24` |

### 影响范围

- `SecuritySecureConfig.java`：新增 2 处修改

---

## 3. Nacos 配置模块启动报错 `${profile}` 占位符无法解析

### 现象

启动 `spring-cloud-alibaba-nacos-config` 时报错：

```
Could not resolve placeholder 'profile' in value "${profile}"
```

`spring-cloud-alibaba-multiple-config` 同样问题：`${profile.provider}`、`${profile.consumer}` 无法解析。

### 根因

`@Value("${profile}")` 没有提供默认值。虽然 `application.yml` 中使用了 `optional:nacos:...` 声明配置导入可选，但 `@Value` 注解本身在 Bean 初始化时要求占位符必须可解析 —— Nacos 上没有对应配置项时直接抛 `PlaceholderResolutionException`。

`optional:` 前缀只影响 `spring.config.import` 阶段（找不到配置文件不报错），不影响 `@Value` 注入阶段（占位符不可解析仍报错）。

### 修复

给所有 `@Value` 注解添加默认值：

```java
// 修复前
@Value("${profile}")
private String profile;

@Value("${profile.provider}")
private String cicodingProvider;

@Value("${profile.consumer}")
private String cicodingConsumer;

// 修复后
@Value("${profile:dev}")
private String profile;

@Value("${profile.provider:default-provider}")
private String cicodingProvider;

@Value("${profile.consumer:default-consumer}")
private String cicodingConsumer;
```

### 结论

| 事项 | 值 |
|------|-----|
| 根因 | `@Value` 占位符无默认值，Nacos 无对应配置时 Bean 初始化失败 |
| 修复 | 添加 `:defaultValue` 默认值语法 |
| 文件 | `ConfigClientController.java`、`ConfigMultipleController.java` |

---

## 4. Nacos 配置模块登录失败 "User nacos not found"

### 现象

启动 `spring-cloud-alibaba-nacos-config` 或 `spring-cloud-alibaba-multiple-config` 时持续报错：

```
login failed: {"code":500,"message":"caused: User nacos not found;"}
```

### 根因

`docker-compose.yml` 中 Nacos 配置了 `NACOS_AUTH_ENABLE=false`（认证关闭），但 `application.yml` 中仍然配置了 `username: nacos` 和 `password: nacos`。

Nacos Config Client 3.x 在检测到有 username 配置时会主动发起认证请求，但认证关闭的 Nacos 服务端没有 `nacos` 用户，导致登录失败。Discovery Client 对 auth 的处理方式不同，所以已有模块（provider 等）不受影响。

### 修复

移除两个配置模块中 Nacos `config` 和 `discovery` 部分的 `username`/`password`：

```yaml
# 修复前
spring:
  cloud:
    nacos:
      config:
        server-addr: 127.0.0.1:8848
        username: nacos       # ← 删除
        password: nacos        # ← 删除
      discovery:
        server-addr: 127.0.0.1:8848
        username: nacos        # ← 删除
        password: nacos         # ← 删除

# 修复后
spring:
  cloud:
    nacos:
      config:
        server-addr: 127.0.0.1:8848
      discovery:
        server-addr: 127.0.0.1:8848
```

### 结论

| 事项 | 值 |
|------|-----|
| 根因 | Nacos 认证已关闭，但配置了无效的 username/password |
| 修复 | 移除 `username` 和 `password` 配置项 |
| 文件 | `spring-cloud-alibaba-nacos-config/application.yml`、`spring-cloud-alibaba-multiple-config/application.yml` |

---

## 5. Nacos 配置修改后 @RefreshScope 不刷新

### 现象

访问 `/profile` 返回默认值（如 `moren`），Nacos 上的配置（`profile=dev111`）完全未被加载。

### 根因

**SCA 2025 的 `spring.config.import:nacos:` 不会自动追加 `file-extension`**。dataId 必须写完整的文件名（含扩展名）。

启动日志可确认：

```
[Nacos Config] config[dataId=spring-cloud-alibaba-nacos-config, group=DEFAULT_GROUP] is empty
```

Nacos 中实际存储的完整 dataId 是 `spring-cloud-alibaba-nacos-config.properties`，但 import 只写了 `spring-cloud-alibaba-nacos-config`（不含扩展名），导致查到了不同的 dataId，配置为空。

```yaml
# ❌ 错误 —— dataId 不带扩展名，实际查到的是 spring-cloud-alibaba-nacos-config
spring:
  config:
    import:
      - nacos:spring-cloud-alibaba-nacos-config
  cloud:
    nacos:
      config:
        file-extension: properties  # ← 此配置对 spring.config.import 不生效！

# ✅ 正确 —— dataId 必须写完整名称
spring:
  config:
    import:
      - nacos:spring-cloud-alibaba-nacos-config.properties
```

**附加发现**：刷新参数应用 `refreshEnabled` 而非 `refresh`，格式为 `?refreshEnabled=true`。

### 修复

- `nacos-config`：`nacos:spring-cloud-alibaba-nacos-config.properties?refreshEnabled=true`
- `multiple-config`：`optional:nacos:cicoding-provider.properties?refreshEnabled=true` 等（已带扩展名，无需改）

### 结论

| 事项 | 值 |
|------|-----|
| 根因 | `spring.config.import:nacos:` 不会自动拼接 `file-extension`，dataId 不完整 |
| 修复 | dataId 写完整名称（含 `.properties` 扩展名） |
| 文件 | `nacos-config/application.yml` |

---

## 常见问题 FAQ

### Q: 为什么 admin-server 不需要 spring-boot-admin-starter-client？

A: SBA 4.x 的服务端内置了自注册能力。当 admin-server 注册到 Nacos 后，SBA 自动发现 Nacos 中的所有服务实例（包括自己），通过 HTTP 访问各实例的 `/actuator/health` 获取状态。只要 actuator 端点可访问（不被 Security 拦截），状态就会显示 UP。

### Q: 其他服务（provider 等）在 SBA 中也是 DOWN 怎么办？

A: 检查以下两点：
1. 该服务暴露了 actuator 端点：`management.endpoints.web.exposure.include: '*'`
2. 该服务没有 Security 拦截 actuator 端点（本项目 provider/consumer 等未引入 Security，默认无此问题）

### Q: SBA 控制台访问不了？

A: 检查启动日志中 `spring-boot-admin-server` 是否注册到 Nacos 成功（日志关键词：`nacos registry`）。确认 `spring.cloud.nacos.discovery.server-addr` 配置正确。
