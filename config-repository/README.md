**在git上配置config远程仓库**

config-server访问git仓库时需添加如下配置：
`spring.cloud.config.label=master`  
`spring.cloud.config.server.git.uri=https://github.com/Sicimike/take-away.git`  
`spring.cloud.config.server.git.search-paths=config-repository`  
`spring.cloud.config.server.git.username=Sicimike`  
`spring.cloud.config.server.git.password=`  

通过http协议访问：
`http://ip:port/{name}-{profiles}.yml`
`http://ip:port/{label}/{name}-{profiles}.yml`
* name 服务名
* profiles 环境
* label 分支(branch)，缺省时是master分支