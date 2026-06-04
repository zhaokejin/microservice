**skywalking源码编译部署**

本文探讨如何快速安装单机的Skywalking源码编译安装。


# 环境需求
JDK版本在JDK 8 - JDK 12之间
注意：如使用 ElasticSearch7 的版本，那么需要 JDK 11+
确保如下端口可用：
11800：和Skywalking通信的gRPC端口
12800：和Skywalking通信的HTTP端口
8080：UI所占用的端口
TIPS

可使用如下命令查询端口是否被占用。

# Linux/macOS
netstat -an|grep 8080

# 对于使用windows的同学，则可以使用
netstat -ano|findstr 8080
如果没有结果，就说明8080端口没有被占用。其他端口也是一样，以此类推。


# 下载
git clone https://github.com/apache/skywalking.git

# 进入
cd skywalking/

git submodule init //初始化子模块
git submodule update //更新子模块

# Run
./mvnw clean package -DskipTests

## Linux或macOS
All packages are in `/dist` (.tar.gz for Linux and .zip for Windows).
在dist文件夹里面有压缩包.tar.gz 是Linux的  .zip是windows的

打开文件夹dist/apache-skywalking-apm-8.5.0\apache-skywalking-apm-bin，目录结构如下：

agent -> 探针相关
bin -> collector和webapp的启动脚本等
config -> collector的相关配置文件
logs -> collector和webapp等的相关日志(执行脚本之后才会生成, 看第4步初始化那里)
webapp -> skywalking展示UI的相关配置


# 启动
## linux
```
cd apache-skywalking-apm-bin/bin

sh startup.sh
```

## Windows
执行：
```
cd apache-skywalking-apm-bin/bin
startup.bat
```

# 首页

访问 http://localhost:8080 ，即可看到类似如下的界面


修改默认的8080端口，如果不冲突不修改端口号

配置文件apache-skywalking-apm-bin\webapp\webapp.yml
```
server:
  port: 8787
 
collector:
  path: /graphql
  ribbon:
    ReadTimeout: 10000
    # Point to all backend's restHost:restPort, split by ,
    listOfServers: 127.0.0.1:12800
```

