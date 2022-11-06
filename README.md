# yanzhandaodi_back

#### 介绍
研战到底后端 主要为研战到底APP提供服务。
主要功能点有 
1. 开发智能择校模块 
2. 构建信息平台（学习圈）整合资料、信息(提出问题，发表问题，评论点赞等功能)  
3. 搭建研究生和在考生服务生沟通的平台(基于netty聊天功能)。

研战到底APP
1. 基于uniapp和uView   vue3版本的使用的vant

研战到底后台
1. 基于开源项目renren-fast

#### 软件架构
软件架构说明
主要技术点：springboot springcloud nacos  netty mybatis-plus rabbitmq  
前端：vue3 vue2 uniapp uview websocket vant
#### 启动说明
1. 先启动 nacos  rabbitmq
2. 启动gateway服务
3. 启动node_modules 里面的first服务，启动netty服务 loginUser 这样就ok了
4. 启动  v2/vue3文件夹下面的vue3 项目 npm install  npm run serve

#### 服务说明
1. gateway服务 提供统一网关
2. file服务    提供文件上传下载
3. first服务   提供app基础功能
4. netty服务   聊天功能服务
5. renren      基于renren-fast的研战到底后台
6. loginUser  登录模块，用户中心
7. UIManager   后台管理的前端
8. v2/vue3     vue3重构的前端项目(目前主要写的)


### 已完成的功能：
1. 用户登录注册token
2. 提出问题，回答，在回答下面评论，点赞
3. oss功能，上传到阿里云，并通过网关代理
4. 考研面临的专业以及专业下的细分学科
5. 基于netty,websocket 以及rabbitmq的聊天功能
6. 将python开发的智能择校模块集成进去
7. 异常处理系统
8. swagger 接口文档地址 localhost:8999/swagger-ui.html
9. 引入springcloud，使用了gateway网关与nacos注册中心
10. 将使用的中间件rabbitmq使用docker部署在虚拟机上


### 后续计划：
1. 使用docker-compose
2. 完善用户聊天功能，解决bug
3. 增加买卖商品的功能，在该场景下使用rabbitmq
4. 引入refreshToken机制，让鉴权做的更加放心
5. 引入自定义权限处理；对于不同的人开启不同的功能
6. 后台管理系统；添加后台的管理模块；可以管理用户上传的内容，管理学科分类，管理热榜等功能、陆续完善页面。
7.登录专门使用一个服务，专门使用一个数据库存储 后续陆续完善 单点登录、oauth2  将后台的登录也集成进去；
8. 后续后台管理项目使用vue3 ts来做
### netty后续计划
1. 搭建一套消息推送机制
2. 完善netty的心跳服务机制
3. 可能使用netty来完成rpc框架
4. 考虑netty分布式情况 让这个小的聊天是项目可以抗住更多并发
5. 引入 zookeeper ，后续可能项目使用自己搭建的rpc框架。

### 技术方面：
需要解决的技术方面问题：

1. 分布式情况下的session共享问题；
2. 分布式情况下的事务问题 计划使用seata，学习中；
3. 分布式情况下shrio该如何使用才能让各个系统之间共享权限；
4. netty集群的话也是需要考虑这样类似的问题


      
         

如果有什么问题留言联系我。qq:1942951600

### 演示图

<table>
    <tr>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/typora/image-20220224131939476.png"/></td>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/typora/image-20220224132011836.png"/></td>
    </tr>
    <tr>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/typora/image-20220224132024789.png"/></td>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/typora/image-20220224132037230.png"/></td>
    </tr>
    <tr>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/typora/image-20220224132109613.png"/></td>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/typora/image-20220224132150045.png"/></td>
    </tr>
	<tr>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/typora/image-20220224133145288.png"/></td>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/typora/image-20220224133216636.png"/></td>
    </tr>	 
</table>


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


