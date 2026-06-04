## 1. Spring Boot Admin 是什么

Spring Boot Admin 是由 codecentric 组织开发的开源项目，使用 Spring Boot Admin 可以管理和监控你的 Spring Boot 项目。

它分为客户端和服务端两部分，客户端添加到你的 Spring Boot 应用增加暴漏相关信息的 HTTP 接口，然后注册到 Spring Boot Admin 服务端，这一步骤可以直接向服务端注册，也可以通过 Eureka 或者 Consul 进行注册。

而 Spring Boot Admin Server 通过 Vue.js 程序监控信息进行可视化呈现。并且支持多种事件通知操作。



## 2. Spring Boot Admin 服务端

Spring Boot Admin 服务端是基于 Spring Boot 项目的，如何创建一个 Spring Boot 项目这里不提，你可以参考之前文章或者从 https://start.spring.io/ 直接获得一个 Spring Boot 项目。



### 2.1. 添加依赖（服务端）

只需要添加 web 依赖和 Spring-boot-admin-starter-server 依赖。

```xml
       <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-server</artifactId>
            <version>2.2.2</version>
        </dependency>
 
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--安全认证框架-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```



### 2.2. 配置 application.yml

```
server:
  port: 8000
 
####服务监控server端
spring:
  application:
    name: wireless-admin-server
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
  security:
    user:
      name: admin
      password: admin
 
 
```



### 2.3启动类：AdminServerMain

```
package com.gpdi.wireless;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
 
/**
 * @Author Lxq
 * @Date 2020/5/7 17:45
 * @Version 1.0
 */
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class AdminServerMain {
    public static void main(String[] args) {
        SpringApplication.run(AdminServerMain.class, args);
    }
}
```



### 2.4配置类 ：SecuritySecureConfig （直接cp官方文档）

```
package com.gpdi.wireless.config; 
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
 
/**
 * @Author Lxq
 * @Date 2020/5/7 22:15
 * @Version 1.0
 *
 */
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter { 
    private final String adminContextPath; 
    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");
 
        http.authorizeRequests()
                //授予对所有静态资产和登录页面的公共访问权限
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                //必须对每个其他请求进行身份验证
                .anyRequest().authenticated()
                .and()
                //配置登录和注销
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                //启用HTTP-Basic支持。这是Spring Boot Admin Client注册所必需的
                .httpBasic().and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(
                        //	禁用CRSF保护Spring引导管理客户端用来注册的端点。
                        adminContextPath + "/instances",
                        // 禁用执行器端点的CRSF保护
                        adminContextPath + "/actuator/**"
                );
    } 
}
```

![img](https://img.jbzj.com/file_images/article/202112/202112200849135.jpg)



## 3. Spring Boot Admin 客户端

创建 Spring Boot 项目依旧不提，这里只需要添加 Spring Boot Admin 客户端需要的依赖，在项目启动时就会增加相关的获取信息的 API 接口。然后在 Spring Boot 配置文件中配置 Spring Boot Admin 服务端，就可以进行监控了。

### 3.1 客户端依赖

```
<      !--服务监控客户端-->
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-client</artifactId>
            <version>2.2.2</version>
        </dependency>
        <!--alibaba-nacos-discovery 注册中心-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```



### 3.2 客户端配置

客户端配置主要为了让客户端可以成功向服务端注册，所以需要配置客户端所在应用相关信息以及 Spring Boot Admin Server 服务端的 url。

```
server:
  port: 8761
 
spring:
  application:
    name: wireless-code-generatr
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
 
#### 暴露端点
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: always
 
logging:
  file:
    name: boot.log
  pattern:
####日志高亮
    file: '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${PID}){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wEx'
 
```

配置中的 include: "*" 公开了所有的端口，对于生产环境，应该自信的选择要公开的接口。



### 3.3. 客户端运行

启动客户端会暴漏相关的运行状态接口，并且自动向配置的服务端发送注册信息。



## 4. Spring Boot Admin 功能

![img](https://img.jbzj.com/file_images/article/202112/202112200849136.png)

点击监控页面上的在线的应用实例，可以跳转到应用实例详细的监控管理页面，也就是 Vue.js 实现的 web 展示。

Spring Boot Admin Server 可以监控的功能很多，使用起来没有难度，

**下面描述下可以监测的部分内容：**

- 应用运行状态，如时间、垃圾回收次数，线程数量，内存使用走势。
- 应用性能监测，通过选择 JVM 或者 Tomcat 参数，查看当前数值。
- 应用环境监测，查看系统环境变量，应用配置参数，自动配置参数。
- 应用 bean 管理，查看 Spring Bean ，并且可以查看是否单例。
- 应用计划任务，查看应用的计划任务列表。
- 应用日志管理，动态更改日志级别，查看日志。
- 应用 JVM 管理，查看当前线程运行情况，dump 内存堆栈信息。
- 应用映射管理，查看应用接口调用方法、返回类型、处理类等信息。

上面提到的日志管理，可以动态的更改日志级别，以及查看日志。默认配置下是只可以动态更改日志级别的，如果要在线查看日志，就需要手动配置日志路径了。

客户端上可以像下面这样配置日志路径以及日志高亮。

```
# 配置文件：application.yml
logging:
  file:
    name: boot.log
  pattern:
#     日志高亮
    file: '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${PID}){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wEx'
```

下面是在 Spring Boot Admin 监测页面上查看的客户端应用日志。

![img](https://img.jbzj.com/file_images/article/202112/202112200849137.png)

### 报警邮件

在我们服务宕机或上线时可以自动触发邮件发送,需要提前开启邮件的imtp和smtp功能,请自行了解

**pom.xml**

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>





```

**配置账号**

```csharp
  boot:
    admin:
      context-path: "control"
      notify:
        mail:
          to: 00000000@qq.com # 发送给
          from: 1111111@qq.com #发送者
  mail:
    default-encoding: UTF-8
    host:   #邮件服务器
    username:    ##用户名
    password:  #密码
    properties:
      mail:
        debug: false
        smtp:
          port: 465
          auth: true
          ssl:
            enable: true
            socket-factory: sf
```

手动停止一个服务看下效果,成功发送报警邮件

###  客户端注册

**pom.xml**

```xml
       <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-client</artifactId>
            <version>2.3.0</version>
        </dependency>
        
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```

**启动类**

client端相对简单,因为nacos自动帮我们整合了与admin的关联工作,只需要注册进nacos,并且与服务端保持在同一命名空间和分组下即可

**bootstrap.yml**

```cpp
  cloud:
    nacos:
      discovery:
        server-addr: 192.168.10.37:18848
        namespace: 77613cbe-9303-4d79-983e-8e5aef69cc45
        group: PM_DEV
        metadata:
          management:

management:
  health:
    redis:
      enabled: false
    sentinel:
      enabled: false
    ldap:
      enabled: false
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always

logging:
  file:
  
```

一切就绪就可以在控制台看到我们的服务了

参考资料：

[官方文档](https://codecentric.github.io/spring-boot-admin/current/#_what_is_spring_boot_admin)

[SpringCloud Alibaba Nacos 整合SpringBoot Admin实战](https://www.jb51.net/article/232348.htm)

