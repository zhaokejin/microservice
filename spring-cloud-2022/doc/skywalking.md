**skywalking直接部署**

本文探讨如何快速安装单机的Skywalking。


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
http://skywalking.apache.org/downloads/

# 解压

# 启动
安装&启动
安装Skywalking比较简单，解压，然后根据操作系统的不同，执行对应命令即可。

## Linux或macOS
执行：
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