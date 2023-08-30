### 准备工作
1、在 [QQ互联](https://connect.qq.com/index.html) 申请成为开发者，并创建应用，得到APP ID 和 APP Key。

2、了解QQ登录时的 [网站应用接入流程](http://wiki.connect.qq.com/%E7%BD%91%E7%AB%99%E5%BA%94%E7%94%A8%E6%8E%A5%E5%85%A5%E6%B5%81%E7%A8%8B)。（必须看完看懂）

为了方便各位测试，直接把我自己申请的贡献出来：

参数|值
---| :---:
APP ID|101386962
APP Key|2a0f820407df400b84a854d054be8b6a
回调地址|http://www.cicoding.cn/login/qq

> 提醒：因为回调地址不是 http://localhost ，所以在启动我提供的demo时，需要在host文件中添加一行：
127.0.0.1 www.cicoding.cn

