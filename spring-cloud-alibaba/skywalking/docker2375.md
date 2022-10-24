# 开启2375
在/usr/lib/systemd/system/docker.service，配置远程访问。

主要是在[Service]这个部分，加上下面两个参数
```
# vim /usr/lib/systemd/system/docker.service
[Service]
ExecStart=
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H fd:// --containerd=/run/containerd/containerd.sock
```

## 重启
```
systemctl daemon-reload
systemctl restart docker
```

查看docker进程：
```
[root@localhost ~]# ps -ef|grep docker
root       2263      1  0 22:29 ?        00:00:00 /usr/bin/dockerd -H tcp://0.0.0.0:2375 -H fd:// --containerd=/run/containerd/containerd.sock
root       2420   2200  0 22:34 pts/1    00:00:00 grep --color=auto docker
```
Docker守护进程打开一个HTTP Socket,这样才能实现远程通信

## 简单使用
-H为连接目标主机docker服务
  
查看docker版本
```
[root@localhost ~]# docker -H tcp://192.168.11.50:2375 version
Client: Docker Engine - Community
 Version:           19.03.12
 API version:       1.40
 Go version:        go1.13.10
 Git commit:        48a66213fe
 Built:             Mon Jun 22 15:46:54 2020
 OS/Arch:           linux/amd64
 Experimental:      false

Server: Docker Engine - Community
 Engine:
  Version:          19.03.12
  API version:      1.40 (minimum version 1.12)
  Go version:       go1.13.10
  Git commit:       48a66213fe
  Built:            Mon Jun 22 15:45:28 2020
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          1.2.13
  GitCommit:        7ad184331fa3e55e52b890ea95e65ba581ae3429
 runc:
  Version:          1.0.0-rc10
  GitCommit:        dc9208a3303feef5b3839f4323d9beb36df0a9dd
 docker-init:
  Version:          0.18.0
  GitCommit:        fec3683
```
查看镜像包：
```
[root@localhost ~]# docker -H tcp://192.168.11.50:2375 images
REPOSITORY                          TAG                 IMAGE ID            CREATED             SIZE
mysql                               8.0                 0d64f46acfd1        12 months ago       544MB
apolloconfig/apollo-portal          1.7.0               16a7840a6f9c        13 months ago       184MB
apolloconfig/apollo-adminservice    1.7.0               5bb5cd0fa47f        13 months ago       211MB
apolloconfig/apollo-configservice   1.7.0               822b3597a040        13 months ago       218MB
```