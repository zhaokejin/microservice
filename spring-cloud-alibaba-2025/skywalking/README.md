# SkyWalking 链路追踪集成指南

## 快速启动

### 1. 启动 SkyWalking 服务（Docker Compose）

```bash
cd spring-cloud-alibaba-2025
docker compose up -d
```

这将同时启动 Nacos + SkyWalking OAP + SkyWalking UI：

| 服务 | 端口 | 地址 |
|------|------|------|
| SkyWalking OAP (gRPC) | 11800 | Agent 上报数据 |
| SkyWalking OAP (HTTP) | 12800 | UI 查询接口 |
| SkyWalking UI | 8080 | http://localhost:8080 |

### 2. 下载 Java Agent

```bash
# Windows
skywalking\download-agent.bat

# Linux / macOS / Git Bash
bash skywalking/download-agent.sh
```

下载后 `skywalking/agent/` 目录下会包含 `skywalking-agent.jar` 和配置文件。

### 3. 启动微服务（挂载 Agent）

#### 方式 A：命令行启动

```bash
java -javaagent:skywalking/agent/skywalking-agent.jar \
     -Dskywalking.agent.service_name=spring-cloud-alibaba-provider \
     -jar spring-cloud-alibaba-provider/target/spring-cloud-alibaba-provider-1.0.0.jar
```

#### 方式 B：IntelliJ IDEA

在 Run Configuration 的 **VM options** 中添加：

```
-javaagent:skywalking/agent/skywalking-agent.jar
-Dskywalking.agent.service_name=spring-cloud-alibaba-provider
```

![IDEA VM Options](https://resources.jetbrains.com/help/img/idea/2024.3/run-debug-configuration.png)

各模块推荐的服务名：

| 模块 | service_name |
|------|-------------|
| spring-cloud-alibaba-provider | `spring-cloud-alibaba-provider` |
| spring-cloud-alibaba-provider-file | `spring-cloud-alibaba-provider-file` |
| spring-cloud-alibaba-consumer-feign | `spring-cloud-alibaba-consumer-feign` |
| spring-cloud-alibaba-consumer-feign-file | `spring-cloud-alibaba-consumer-feign-file` |
| spring-cloud-alibaba-gateway | `spring-cloud-alibaba-gateway` |
| spring-cloud-alibaba-nacos-config | `spring-cloud-alibaba-nacos-config` |
| spring-cloud-alibaba-multiple-config | `spring-cloud-alibaba-multiple-config` |
| spring-boot-admin-server | `spring-boot-admin-server` |
| springboot-dubbo-demo (provider) | `dubbo-service-provider` |
| springboot-dubbo-demo (consumer) | `dubbo-service-consumer` |

#### 方式 C：Maven 插件

```bash
mvn -pl spring-cloud-alibaba-provider spring-boot:run \
    -Dspring-boot.run.jvmArguments="-javaagent:skywalking/agent/skywalking-agent.jar -Dskywalking.agent.service_name=spring-cloud-alibaba-provider"
```

---

## 关键 JVM 参数

| 参数 | 默认值 | 说明 |
|------|--------|------|
| `-Dskywalking.agent.service_name` | `spring-cloud-alibaba-2025` | 服务名（在 UI 中显示） |
| `-Dskywalking.agent.collector.backend_service` | `127.0.0.1:11800` | OAP gRPC 地址 |
| `-Dskywalking.logging.level` | `INFO` | Agent 日志级别 |

完整配置项参考：`skywalking/agent/config/agent.config`

---

## 日志集成

日志框架已配置为自动注入 `traceId`（SkyWalking Agent 自动向 SLF4J MDC 写入）：

```xml
<!-- logback-spring.xml -->
<property name="LOG_PATTERN"
          value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] [%X{traceId}] %-5level %logger{36} - %msg%n"/>
```

日志输出示例：

```
2026-06-06 10:30:00.123 [http-nio-9000-exec-1] [b8e5d5f6c3a14e2f9a7b1c3d5e7f9a0b.1.16543210981230001] INFO  c.c.controller.UserController - 查询用户
```

其中的 `[traceId]` 与 SkyWalking UI 中的链路 ID 一致，可直接跳转查看完整调用链。

---

## Docker 部署（带 Agent）

每个模块的 Dockerfile 保持简洁（只打 jar + 启动入口），不硬编码 SkyWalking Agent。

容器化部署时，通过 **volume 挂载** 注入 Agent jar，通过 **环境变量** 控制是否启用：

```yaml
# docker-compose 示例（在服务定义中添加）
services:
  provider:
    build:
      context: .
      dockerfile: spring-cloud-alibaba-provider/Dockerfile
    ports:
      - "9000:9000"
    volumes:
      # 把宿主机的 agent 目录挂进去
      - ./skywalking/agent:/opt/skywalking/agent:ro
    environment:
      - SW_AGENT_ENABLE=true
      - SW_AGENT_NAME=spring-cloud-alibaba-provider
      - SW_AGENT_COLLECTOR_BACKEND_SERVICES=skywalking-oap:11800
      - JAVA_TOOL_OPTIONS=-javaagent:/opt/skywalking/agent/skywalking-agent.jar
```

或者构建镜像时用 `docker build --build-arg` 传入 Agent 路径（不推荐，镜像会变大）。

---

## UI 功能

访问 http://localhost:8080 ：

- **仪表盘**：服务拓扑图、QPS、延迟、成功率
- **追踪**：完整的请求调用链（跨服务追踪）
- **服务**：每个服务的性能指标
- **实例**：每个实例的 JVM 内存、GC、线程
- **拓扑**：服务间调用关系拓扑图
- **日志**：关联 traceId 的日志查看

---

## 常见问题

**Q: 启动后 UI 中看不到服务？**

1. 确认 SkyWalking OAP 和 UI 已启动：`docker ps | grep skywalking`
2. 确认微服务启动时挂载了 `-javaagent`
3. 检查 OAP 日志：`docker logs skywalking-oap`
4. 确认 11800 端口可达：`telnet 127.0.0.1 11800`
5. 首次启动后等待 30 秒（数据采集有延迟）

**Q: 内存不足？**

可以在 `docker-compose.yml` 中调整 OAP 内存：
```yaml
skywalking-oap:
  environment:
    - JAVA_OPTS=-Xms256m -Xmx256m  # 降低内存
```

**Q: Agent 日志太多？**

设置 `-Dskywalking.logging.level=WARN`
