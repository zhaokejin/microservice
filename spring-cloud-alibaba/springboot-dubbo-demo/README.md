参考：https://blog.neoniou.com/posts/spring-boot-nacos-dubbo/

## 1、前言

今天在spring boot2.x下整合了一下dubbo2.7.8和nacos1.4.0，因为都是最新版，所以nacos和dubbo都有一些东西和网上的教程相比有所变化。

在写最新版的整合时，也会讲一下各个版本相对应的旧版的变化。

参考官方文档：

[dubbo](http://dubbo.apache.org/zh/)

[nacos](https://nacos.io/zh-cn/index.html)

## 2、安装 nacos注册中心

### 2.1 下载

下载：[github release](https://github.com/alibaba/nacos/releases)

我使用的版本是1.4.0，两个压缩包只是对应了不用的操作系统的压缩，内容是一样的，所以任意选一个下载即可

部分文件结构：

```
nacos
├── bin
│   ├── shutdown.sh
│   ├── startup.sh
│   ├── startup.cmd
│   ├── shutdown.cmd
├── conf
│   ├── application.properties
│   ├── application.properties.example
│   ├── cluster.conf.example
│   ├── nacos-logback.xml
│   ├── nacos-mysql.sql
│   └── schema.sql
Copy
```

### 2.2 修改启动文件

因为新版nacos加入了cluster集群模式，而我们我目前只需要standalone单机模式，所以需要修改启动文件，不然每次都添加一个指令很麻烦

linux下修改startup.sh，windows下修改startup.cmd

windows：

```
set MODE="standalone"
Copy
```

linux：

```
export MODE="standalone"
Copy
```

### 2.3 配置数据库

nacos支持内置数据库，不过为了更方便管理，还是建立一个外部数据库比较好

在数据库中创建一个nacos相关的数据库，然后将 **/conf/nacos-mysql.sql** 文件执行即可

### 2.4 修改配置文件

配置文件：**/conf/application.properties**

```
#修改为你的数据的信息
spring.datasource.platform=mysql

db.num=1

db.url.0=jdbc:mysql://database-url:12345/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user=nacos
db.password=nacos
Copy
```

### 2.5 启动nacos

windows下双击startup.cmd即可

linux下：

```
sh startup.sh
#ubuntu
bash startup.sh
Copy
```

根据提示的网址访问即可，默认的用户名和密码都是nacos

[![img](https://img.neoniou.com/blog/2194sda.png)](https://img.neoniou.com/blog/2194sda.png)

## 3、项目整合

我的整合demo: https://github.com/aowubulao/dubbo-demo

项目目录

[![img](https://img.neoniou.com/blog/20201218162624.png)](https://img.neoniou.com/blog/20201218162624.png)

**common-api**：接口提供包，普通mavne项目，用于提供接口类

**service-consumer**：dubbo消费者

**service-provider**：dubbo提供者

### 父类

父类用于提供依赖管理

pom文件：

```
<properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <netty.version>4.1.56.Final</netty.version>
        <spring-boot.version>2.3.5.RELEASE</spring-boot.version>
        <dubbo.version>2.7.8</dubbo.version>
        <nacos-client.version>1.4.0</nacos-client.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-dependencies-bom</artifactId>
                <version>${dubbo.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-spring-boot-starter</artifactId>
                <version>${dubbo.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-registry-nacos</artifactId>
                <version>${dubbo.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba.nacos</groupId>
                <artifactId>nacos-client</artifactId>
                <version>${nacos-client.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo</artifactId>
                <version>${dubbo.version}</version>
                <exclusions>
                    <exclusion>
                        <groupId>org.springframework</groupId>
                        <artifactId>spring</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>javax.servlet</groupId>
                        <artifactId>servlet-api</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>log4j</groupId>
                        <artifactId>log4j</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
        </dependencies>
    </dependencyManagement>
Copy
```

### common-api模块

[![img](https://img.neoniou.com/blog/20201218162945.png)](https://img.neoniou.com/blog/20201218162945.png)

用于提供一个接口

**SayService:**

```
public interface SayService {

    /**
     * 根据名字say hello
     * @param name 名字
     * @return name + hello
     */
    String sayHelloByName(String name);
}
Copy
```

### service-provider

[![img](https://img.neoniou.com/blog/20201218163945.png)](https://img.neoniou.com/blog/20201218163945.png)

SpringBoot项目，提供实例供consumer使用

**pom：**

```
<properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-registry-nacos</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.nacos</groupId>
        <artifactId>nacos-client</artifactId>
    </dependency>
    <dependency>
        <groupId>com.neoniou.demo</groupId>
        <artifactId>common-api</artifactId>
        <version>0.0.1</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
Copy
```

**配置文件application.yaml：**

```
server:
  port: 14511
spring:
  application:
    name: provider-service
  main:
    allow-bean-definition-overriding: true
dubbo:
  application:
    name: provider-service
  registry:
    address: nacos://127.0.0.1:8848
    username: nacos
    password: nacos
  scan:
    base-packages: com.neoniou.provider.service.impl
  protocol:
    name: dubbo
    port: 15511
Copy
```

**ProviderApplication.class：**

```
@EnableDubbo
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
Copy
```

**SayServiceImpl.class：**

关于这个地方，**@DubboService**是新版Dubbo的注解，如果是旧版比如2.7.3，使用的是**org.apache.dubbo.config.annotation.Service**这个注解

```
@DubboService
public class SayServiceImpl implements SayService {

    @Override
    public String sayHelloByName(String name) {
        return name + ",hello!";
    }
}
Copy
```

### service-consumer

[![img](https://img.neoniou.com/blog/20201218163928.png)](https://img.neoniou.com/blog/20201218163928.png)

pom和启动类同provider

**SayController：**

同上，这里的**@DubboReference**是一个新版注解，老版本使用的是**org.apache.dubbo.config.annotation.Reference**

```
@RestController
@RequestMapping("/demo/say")
public class SayController {

    @DubboReference
    private SayService sayService;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello(@RequestParam("name") String name) {
        return ResponseEntity.ok(sayService.sayHelloByName(name));
    }
}
Copy
```

## 4、项目启动

先启动provider，再启动consumer

可以在nacos控制面板中看到：

[![img](https://img.neoniou.com/blog/20201218164228.png)](https://img.neoniou.com/blog/20201218164228.png)

**测试API：**

[![img](https://img.neoniou.com/blog/20201218164353.png)](https://img.neoniou.com/blog/20201218164353.png)

至此，SpringBoot+nacos+dubbo的整合就结束了，关于dubbo的配置，不仅只有yaml配置方法，还有xml、注解、bean等很多方式，可以查阅官方文档进行更适合你自己项目的配置方式。