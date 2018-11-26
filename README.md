**微服务是一种架构风格，而不是指某些框架**

## 介绍
### 微服务特点
* 一些列微小的服务共同组成
* 跑在自己的进程里
* 每个服务为独立的业务开发
* 独立部署
* 分布式管理  

### 单体架构优点
* 容易测试
* 容易部署

### 单体架构缺点
* 开发效率低，所有开发人员开发同一个项目，代码冲突严重
* 代码维护难
* 部署不灵活，构建时间长
* 稳定性不高
* 扩展性不够

### 微服务必定是分布式的，分布式特点
* 多个自治的处理元素
* 不共享主内存
* 通过网络通信，http RESTful、RPC

## Spring Cloud组件
### Spring Cloud Eureka
基于Netflix Eureka做了二次封装
包括两个部分：
 * Rureka Server 注册中心，[localhost:8761, localhost:8762]
 * Rureka Client 注册服务，product(localhost:8080)是商品服务，order(localhost:8081)是订单服务