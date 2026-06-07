# 微服务中间件扩展开发路线图

> 项目：Spring Cloud Alibaba 2025 微服务示例
> 创建日期：2025-06-07
> 状态：第六阶段已完成，第七阶段规划中

---

## 路线图总览

当前项目已完成 **第一阶段（基础设施）到第五阶段（进阶集成）** 共 15 篇教程，覆盖了注册中心、配置中心、网关、服务调用、监控和链路追踪等基础能力。

本路线图规划 **第六至八阶段**，逐步补全熔断限流、缓存、消息队列、分布式事务、统一认证、可观测性、分布式调度等生产级中间件能力。

```
已完成 ✅
├── 第一阶段：环境与基础设施（01-03）  ← Nacos + MySQL + Maven 骨架
├── 第二阶段：核心微服务开发（04-07）  ← Provider + Feign + RestTemplate + Gateway
├── 第三阶段：配置与高级特性（08-10）  ← Nacos Config + 多配置 + 文件上传
├── 第四阶段：运维与监控（11-12）      ← SBA + Consul
├── 第五阶段：进阶集成（13-15）        ← Dubbo + SkyWalking + Docker Compose
└── 第六阶段：生产必备中间件（16-18）  ← Sentinel + Redis + RocketMQ      ✅ 已完成

规划中 📋
├── 第七阶段：架构进阶能力（19-21）    ← Seata + OAuth2 + OpenTelemetry 🥈 高优先级
└── 第八阶段：大厂级完善（22-27）      ← 调度 + 指标 + 日志 + 网格 + 混沌  🥉 中优先级
```

---

## 第六阶段：生产必备中间件 🥇

> **目标**：补全熔断限流、缓存、消息队列三大生产环境必需品
> **预计工期**：3 周

### 16. Sentinel 熔断限流

| 项目 | 内容 |
|------|------|
| **背景** | 当前项目没有熔断限流保护，一个服务故障可能拖垮全链路 |
| **目标** | 在 Gateway + Consumer 两层接入 Sentinel，实现流量控制和熔断降级 |
| **技术选型** | Alibaba Sentinel 1.8.x + Sentinel Dashboard |
| **模块** | 新建或扩展现有 Gateway/Consumer 模块 |
| **核心功能** | QPS 限流、线程数限流、慢调用熔断、异常比例熔断、热点参数限流 |
| **持久化** | Sentinel 规则推送到 Nacos 持久化 |
| **Dashboard** | Docker Compose 部署 Sentinel Dashboard，可视化流控规则 |
| **学习要点** | 流控模式（直接/关联/链路）、流控效果（快速失败/Warm Up/排队等待）、降级策略（RT/异常比例/异常数）、`@SentinelResource` 注解、Fallback 与 BlockHandler 的区别 |

**开发任务**：
- [x] Docker Compose 添加 Sentinel Dashboard
- [x] Gateway 模块集成 Sentinel 网关限流
- [x] Consumer-Feign 模块集成 Sentinel 资源保护
- [x] Provider 模块添加 Sentinel 热点参数限流
- [x] Sentinel 规则 Nacos 持久化
- [x] 编写教程文档 `16.Sentinel熔断限流.md`

---

### 17. Redis 分布式缓存

| 项目 | 内容 |
|------|------|
| **背景** | 每次请求都直接查 MySQL，无缓存层，高并发下数据库压力大 |
| **目标** | 集成 Redis 缓存用户查询接口，理解缓存策略 |
| **技术选型** | Redis 7.x + Spring Cache + Redisson |
| **模块** | 扩展现有 Provider 模块 |
| **核心功能** | `@Cacheable`/`@CacheEvict`/`@CachePut` 注解式缓存、缓存穿透（布隆过滤器/空值缓存）、缓存击穿（互斥锁）、缓存雪崩（随机过期时间）、Redisson 分布式锁 |
| **部署** | Docker Compose 添加 Redis + RedisInsight（可视化管理） |
| **学习要点** | Spring Cache 抽象、Redis 五种数据结构使用场景、缓存更新策略（Cache Aside/Read Through/Write Through）、Redisson 看门狗机制、Redis Cluster 分片原理 |

**开发任务**：
- [x] Docker Compose 添加 Redis + RedisInsight
- [x] Provider 模块引入 `spring-boot-starter-data-redis` + `spring-boot-starter-cache`
- [x] 用户查询接口接入 `@Cacheable` 缓存
- [x] 用户修改/删除接口接入 `@CacheEvict` 失效
- [x] Redisson 分布式锁实现缓存击穿保护
- [x] 编写教程文档 `17.Redis分布式缓存.md`

---

### 18. RocketMQ 消息队列

| 项目 | 内容 |
|------|------|
| **背景** | 服务间同步调用耦合度高，无法削峰填谷和异步解耦 |
| **目标** | 集成 RocketMQ，实现异步通知、订单削峰等典型场景 |
| **技术选型** | Apache RocketMQ 5.x + Spring Cloud Stream RocketMQ Binder |
| **模块** | 新建 `spring-cloud-alibaba-rocketmq` 模块 |
| **核心功能** | 普通消息（同步/异步/单向）、顺序消息、延迟消息、事务消息、消息重试与死信队列、Spring Cloud Stream 函数式编程模型 |
| **部署** | Docker Compose 添加 RocketMQ NameServer + Broker + Dashboard |
| **学习要点** | 消息模型（Topic/Tag/Group）、消费模式（集群/广播）、消息过滤、消费重试机制、事务消息原理（半消息 + 回查）、Spring Cloud Stream Binder 抽象 |

**开发任务**：
- [x] Docker Compose 添加 RocketMQ + Dashboard
- [x] 新建 `spring-cloud-alibaba-rocketmq` 模块
- [x] 普通消息：用户注册异步发欢迎通知
- [x] 顺序消息：订单状态流转
- [x] 延迟消息：订单超时取消
- [x] 改用 `rocketmq-spring-boot-starter:2.3.3`（SCA 2025 BOM 已移除原 starter）
- [x] 编写教程文档 `18.RocketMQ消息队列.md`

---

## 第七阶段：架构进阶能力 🥈

> **目标**：分布式事务、统一认证、可观测标准三大进阶能力
> **预计工期**：3 周

### 19. Seata 分布式事务

| 项目 | 内容 |
|------|------|
| **背景** | Dubbo + Feign 跨服务调用必然涉及数据一致性问题 |
| **目标** | 集成 Seata，演示 AT/TCC 两种模式的分布式事务方案 |
| **技术选型** | Seata 2.x + Nacos（注册+配置） |
| **模块** | 新建 `seata-order` + `seata-storage` + `seata-account` 示例模块 |
| **核心功能** | AT 模式（无侵入自动回滚）、TCC 模式（Try-Confirm-Cancel 手动补偿）、Seata Dashboard 全局事务监控 |
| **部署** | Docker Compose 添加 Seata Server |
| **学习要点** | AT 模式原理（全局锁 + undo_log 回滚）、TCC 空回滚/悬挂/幂等处理、Saga 模式适用场景、Seata 与 XA 的区别 |

**开发任务**：
- [ ] Docker Compose 添加 Seata Server
- [ ] 新建 seata-order / seata-storage / seata-account 业务模块
- [ ] AT 模式：下单扣库存扣余额（无侵入）
- [ ] TCC 模式：手动补偿接口实现
- [ ] Seata Dashboard 事务监控
- [ ] 编写教程文档 `19.Seata分布式事务.md`

---

### 20. Spring Security + OAuth2 统一认证

| 项目 | 内容 |
|------|------|
| **背景** | 当前所有服务接口无鉴权保护，Gateway 缺乏统一认证入口 |
| **目标** | 基于 Spring Security 7.0 实现 OAuth2 授权服务器 + Gateway 统一认证 |
| **技术选型** | Spring Security 7.0 + Spring Authorization Server 1.x + JWT |
| **模块** | 新建 `auth-server` + 扩展现有 Gateway |
| **核心功能** | 授权码模式、密码模式、客户端模式、JWT 令牌签发与校验、Gateway 全局过滤器 Token 校验、微服务间 Token 传递（Feign RequestInterceptor）、RBAC 权限模型 |
| **学习要点** | OAuth2 四种授权模式及适用场景、JWT 结构（Header/Payload/Signature）、Resource Server 配置、Gateway Security 与微服务 Security 的分工、Spring Security 7.x Filter Chain 新 API |

**开发任务**：
- [ ] 新建 `auth-server` 授权服务器模块
- [ ] OAuth2 客户端模式（服务间调用）
- [ ] OAuth2 密码模式（用户登录）
- [ ] Gateway 全局过滤器 Token 校验
- [ ] Feign RequestInterceptor 自动传递 Token
- [ ] RBAC 接口权限注解
- [ ] 编写教程文档 `20.SpringSecurity-OAuth2统一认证.md`

---

### 21. OpenTelemetry 可观测标准

| 项目 | 内容 |
|------|------|
| **背景** | 已有 SkyWalking 做 APM，但 OpenTelemetry 是 CNCF 制定的行业标准协议 |
| **目标** | 集成 OpenTelemetry Java Agent，实现 Traces + Metrics + Logs 三支柱统一 |
| **技术选型** | OpenTelemetry Java Agent + OTLP Collector + Jaeger/Zipkin（可选后端） |
| **模块** | 扩展现有 Provider + Consumer 模块 |
| **核心功能** | OTLP 协议导出、自定义 Span 手动埋点、Micrometer Bridge 统一指标、Logback MDC TraceId 关联 |
| **部署** | Docker Compose 添加 OTLP Collector + Jaeger |
| **学习要点** | OpenTelemetry 核心概念（Span/Trace/Meter/LogRecord）、OTLP 协议、Sampling 采样策略、Propagator 上下文传播、与 SkyWalking 的对比与共存策略 |

**开发任务**：
- [ ] Docker Compose 添加 OTLP Collector + Jaeger
- [ ] Java Agent 自动埋点（替代/补充 SkyWalking Agent）
- [ ] 业务代码手动 Span 埋点
- [ ] Micrometer → OTLP Metrics 导出
- [ ] Logback 关联 TraceId
- [ ] 编写教程文档 `21.OpenTelemetry可观测标准.md`

---

## 第八阶段：大厂级完善 🥉

> **目标**：分布式调度、Prometheus 指标监控、ELK 日志、服务网格、混沌工程等大厂级能力
> **预计工期**：3 周

### 22. XXL-Job 分布式调度

| 项目 | 内容 |
|------|------|
| **目标** | 用 XXL-Job 替代 `@Scheduled`，实现分布式环境下的定时任务管理 |
| **技术选型** | XXL-Job 2.x |
| **模块** | 新建 `spring-cloud-alibaba-job` 模块 |
| **核心功能** | 分片广播、失败重试、任务依赖、动态 Cron、GLUE 模式 |
| **部署** | Docker Compose 添加 XXL-Job Admin |
| **学习要点** | 路由策略（第一个/最后一个/轮询/随机/分片广播）、阻塞处理策略、GLUE 模式原理 |

**开发任务**：
- [ ] Docker Compose 添加 XXL-Job Admin
- [ ] 新建 job 模块集成 XXL-Job Executor
- [ ] 分片广播：用户批量处理任务
- [ ] 失败重试 + 告警通知
- [ ] 编写教程文档 `22.XXL-Job分布式调度.md`

---

### 23. Prometheus + Grafana 指标监控

| 项目 | 内容 |
|------|------|
| **目标** | Spring Boot Admin 仅能看实时单机状态，补充 Prometheus 聚合指标 + Grafana 可视化 |
| **技术选型** | Prometheus + Grafana + Micrometer |
| **核心功能** | JVM 指标采集、自定义业务指标、PromQL 查询、Grafana Dashboard、AlertManager 告警 |
| **部署** | Docker Compose 添加 Prometheus + Grafana |
| **学习要点** | Micrometer MeterRegistry、PromQL 语法、Grafana Dashboard 设计、Recording Rules 与 Alerting Rules |

**开发任务**：
- [ ] Docker Compose 添加 Prometheus + Grafana
- [ ] 所有模块暴露 `/actuator/prometheus`
- [ ] Prometheus 静态服务发现 + Nacos SD
- [ ] Grafana Dashboard 模板导入
- [ ] AlertManager 告警规则配置
- [ ] 编写教程文档 `23.Prometheus-Grafana指标监控.md`

---

### 24. ELK 日志中心

| 项目 | 内容 |
|------|------|
| **目标** | 微服务日志分散在多个容器，使用 ELK 集中收集、检索、分析 |
| **技术选型** | Elasticsearch + Logstash/Kafka + Kibana（或轻量方案 Loki） |
| **核心功能** | JSON 结构化日志、Filebeat 采集、Kibana 检索 Dashboard |
| **部署** | Docker Compose 添加 Elasticsearch + Kibana + Filebeat |
| **学习要点** | 日志结构化（JSON）、Filebeat 配置、Kibana Index Pattern、日志采样 |

**开发任务**：
- [ ] Docker Compose 添加 ES + Kibana + Filebeat
- [ ] Logback JSON 格式输出
- [ ] Filebeat 采集容器日志
- [ ] Kibana 日志检索 + Dashboard
- [ ] 编写教程文档 `24.ELK日志中心.md`

---

### 25. 服务网格入门（Cilium / Istio Ambient）

| 项目 | 内容 |
|------|------|
| **目标** | 了解 2025 年服务网格趋势：sidecar-less 模式、eBPF 内核级流量管理 |
| **技术选型** | Cilium（eBPF）+ Hubble（可观测）+ Istio Ambient Mesh（可选） |
| **核心功能** | 基于 eBPF 的 L3/L4/L7 网络策略、Hubble 流量可视化、mTLS 自动加密 |
| **学习要点** | Sidecar vs Sidecar-less 架构对比、eBPF 基础原理、Cilium NetworkPolicy |

**开发任务**：
- [ ] Kubernetes 环境搭建（minikube/k3d）
- [ ] Cilium CNI 安装 + Hubble UI
- [ ] 迁移部分服务到 K8s + Cilium
- [ ] 流量策略 + 可观测性验证
- [ ] 编写教程文档 `25.服务网格入门.md`

---

### 26. 下一代 API 网关探索（Higress / APISIX）

| 项目 | 内容 |
|------|------|
| **目标** | Spring Cloud Gateway 是基础方案，探索基于 Envoy + Wasm 插件的下一代网关 |
| **技术选型** | Higress（阿里开源，基于 Envoy + Istio Wasm）+ APISIX（备选） |
| **核心功能** | AI 网关能力（LLM 路由/限流/缓存）、Wasm 插件开发、配置热更新 |
| **学习要点** | Envoy 架构（Listener/Filter/Route/Cluster）、Wasm 插件机制、与 SCG 的对比 |

**开发任务**：
- [ ] Docker Compose 添加 Higress
- [ ] 替代 SCG 或平行运行
- [ ] Wasm 自定义插件示例
- [ ] AI 网关能力演示
- [ ] 编写教程文档 `26.下一代API网关探索.md`

---

### 27. ChaosBlade 混沌工程

| 项目 | 内容 |
|------|------|
| **目标** | 主动注入故障验证系统韧性，确保 Sentinel 熔断、Seata 回滚等机制真正生效 |
| **技术选型** | ChaosBlade（阿里开源） |
| **核心功能** | CPU 满载、内存溢出、网络延迟/丢包、进程 Kill、Pod Kill（K8s 场景） |
| **学习要点** | 混沌工程原则（稳态假设/最小爆炸半径/自动终止）、故障演练流程设计 |

**开发任务**：
- [ ] Docker Compose 添加 ChaosBlade
- [ ] 演练 1：Provider CPU 满载 → 验证 Sentinel 熔断
- [ ] 演练 2：MySQL 网络延迟 → 验证超时与重试
- [ ] 演练 3：Nacos 不可用 → 验证本地缓存兜底
- [ ] 演练 4：服务 Kill → 验证 Failover
- [ ] 编写教程文档 `27.ChaosBlade混沌工程.md`

---

## 模块规划清单

```
spring-cloud-alibaba-2025/
├── (现有模块)...
│
├── 第六阶段
│   ├── spring-cloud-alibaba-rocketmq/       # 18. RocketMQ 消息队列
│   │
├── 第七阶段
│   ├── seata-order/                          # 19. Seata 分布式事务 - 订单服务
│   ├── seata-storage/                        # 19. Seata 分布式事务 - 库存服务
│   ├── seata-account/                        # 19. Seata 分布式事务 - 账户服务
│   ├── auth-server/                          # 20. OAuth2 授权服务器
│   │
├── 第八阶段
│   └── spring-cloud-alibaba-job/             # 22. XXL-Job 分布式调度
│
├── docker-compose.yml                        # 扩展：Sentinel / Redis / RocketMQ
│                                              #       Seata / OTLP / Jaeger
│                                              #       XXL-Job / Prometheus / Grafana
│                                              #       ES / Kibana / ChaosBlade
│
└── docs/blog/
    ├── 16.Sentinel熔断限流.md
    ├── 17.Redis分布式缓存.md
    ├── 18.RocketMQ消息队列.md
    ├── 19.Seata分布式事务.md
    ├── 20.SpringSecurity-OAuth2统一认证.md
    ├── 21.OpenTelemetry可观测标准.md
    ├── 22.XXL-Job分布式调度.md
    ├── 23.Prometheus-Grafana指标监控.md
    ├── 24.ELK日志中心.md
    ├── 25.服务网格入门.md
    ├── 26.下一代API网关探索.md
    └── 27.ChaosBlade混沌工程.md
```

---

## 执行记录

| 序号 | 教程 | 状态 | 开始日期 | 完成日期 | 备注 |
|------|------|------|---------|---------|------|
| 16 | Sentinel 熔断限流 | ✅ 已完成 | 2026-06-07 | 2026-06-07 | Gateway + Consumer + Provider 三层接入 |
| 17 | Redis 分布式缓存 | ✅ 已完成 | 2026-06-07 | 2026-06-07 | Spring Cache + Redisson 分布式锁 |
| 18 | RocketMQ 消息队列 | ✅ 已完成 | 2026-06-07 | 2026-06-07 | rocketmq-spring-boot-starter 2.3.3（SCA 2025 BOM 无此项）|
| 19 | Seata 分布式事务 | ⬜ 待开始 | — | — | |
| 20 | OAuth2 统一认证 | ⬜ 待开始 | — | — | |
| 21 | OpenTelemetry 可观测 | ⬜ 待开始 | — | — | |
| 22 | XXL-Job 分布式调度 | ⬜ 待开始 | — | — | |
| 23 | Prometheus + Grafana | ⬜ 待开始 | — | — | |
| 24 | ELK 日志中心 | ⬜ 待开始 | — | — | |
| 25 | 服务网格入门 | ⬜ 待开始 | — | — | |
| 26 | 下一代 API 网关 | ⬜ 待开始 | — | — | |
| 27 | ChaosBlade 混沌工程 | ⬜ 待开始 | — | — | |

> 状态说明：⬜ 待开始 | 🔄 进行中 | ✅ 已完成 | ⏸️ 暂缓
