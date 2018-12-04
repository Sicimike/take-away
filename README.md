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

### 技术栈
* spring-boot-2.0.2.RELEASE
* spring-cloud-Finchley.RELEASE
	* Eureka
	* OpenFeign
	* Config
	* Bus
	* Stream
* Docker-18.05.0-ce
* RabbitMQ:3.7.3
* Redis:4.0.8

## Spring Cloud组件
### Spring Cloud Eureka
基于Netflix Eureka做了二次封装
包括两个部分：
 * Eureka Server 注册中心，[localhost:8761, localhost:8762]
 * Eureka Client 注册服务，**product**(localhost:8080)是商品服务，**order**(localhost:8081)是订单服务  
两个server相互注册，client同时在两个server上注册即可实现高可用

### Spring Cloud OpenFeign

### Spring Cloud Config
配置中心，会从远端git拉取配置文件到本地git仓库
* Config Server 配置文件服务，[localhost:9761, localhost:9762]
config无需任何配置，在eureka server上注册、启动多个实例即可实现高可用  

### Spring Cloud Bus
消息总线结合RabbitMQ、Webhooks实现配置自动刷新
1. 把config server服务localhost:9761映射到外网`http://sicimike.ngrok.xiaomiqiu.cn`(可利用内外穿透工具)
2. 添加pom.xml文件添加两个依赖: `spring-cloud-starter-bus-amqp`和`spring-cloud-config-monitor`
3. 配置文件中添加`management.endpoints.web.exposure.include=*`
4. 登录github，进入`https://github.com/{username}/{project-name}`，找到Settings->Webhooks添加`http://sicimike.ngrok.xiaomiqiu.cn/monitor`