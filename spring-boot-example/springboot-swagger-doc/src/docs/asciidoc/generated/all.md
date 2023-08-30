# Cicoding API


<a name="overview"></a>
## Overview
Cicoding API


### Version information
*Version* : 1.0.0


### URI scheme
*Host* : localhost:8080  
*BasePath* : /


### Tags

* 公告相关接口 : 公告相关接口




<a name="paths"></a>
## Resources

<a name="25e062b8715916f35f52ca526ab006c6"></a>
### 公告相关接口
公告相关接口


<a name="findnewestusingget"></a>
#### 查询最新的一条公告
```
GET /notices/newest
```


##### Description
用于：公告


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[公告](#fa86f1a57d6f758a93cb33b59c015654)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`




<a name="definitions"></a>
## Definitions

<a name="fa86f1a57d6f758a93cb33b59c015654"></a>
### 公告

|Name|Description|Schema|
|---|---|---|
|**content**  <br>*optional*|公告内容|string|
|**id**  <br>*optional*|id|integer (int32)|





