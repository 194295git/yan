# yanzhandaodi_back

#### 介绍
- 一个开源的springcloud项目。体验和尝试springcloud的各种功能。
- 内容：抽象出cloud中使用到的常用技术。目标做一个低代码的组件平台。将流程，数据库操作，
建模、docker运维、文件系统等抽象出来方便的调用。会定期提升代码质量。还有移动端uniapp的支持。
目前重点在做netty模块。通过websocket构建消息推送系统以及IM相关功能。先完成简单的音视频推送，
后续考虑添加视频推流
- 目的：将工作中使用到的技术脱离业务剥离出来，方便复用、借鉴。让使用者可以轻松调用
- 模块：包括文件模块、运维模块、数据库模块、netty模块、用户中心模块、还有其他业务模块。
- 后续：会开发sdk，文档，APIStroe等提供更加简介的调用方式。
- 文档：从图片，架构，技术细节以及视频等方面帮助阅读此项目

###
#### 服务说明
1. gateway服务     提供统一网关
2. file服务        提供文件上传下载
3. first服务       提供app基础功能
4. netty服务       聊天功能服务，消息推送框架
5. database服务    数据库相关服务 
5. renren          后台管理系统
6. UIManager       后台管理的前端
8. vue3              vue重构的前端项目
9. common_shiro    分布式权限
10. loginUser      用户中心、统一完成授权
11. devops         相关的运维板块


### netty 模块实现功能重点说明
通过集群来支持高并发，通过六层报文来实现高可靠。通过幂等来解决消息重复
1. 支持单聊和群聊 ，支持发送表情包
2. 聊天内容ack机制，然后未送达重试机制(重试三次，三次之后为失败)
3. 设计websocket报文，保证消息的可靠性
4. 支持分布式，支持gateway负载均衡，将netty服务端注册进入nacos
5. 支持websocket授权功能（已经做了相关的截取参数以及改造前端vue）
6. 接口引用redis设计成幂等 (todo)需要做一下lua表达式
7. 会话管理(redis结合本地Map) 待完善
8. 支持心跳功能(待完善)
9. 设置消息加密(待完善)



#### 软件架构
软件架构说明
主要技术点：springboot springcloud nacos  netty mybatis-plus rabbitmq docker
前端：vue3 vue2 uniapp uview websocket vant
#### 启动说明
1. 先启动nacos 
2. 启动gateway服务
3. 启动node_modules 里面的first服务，启动netty服务
4. 启动UI前端服务 使用hbuilder启动，或者npm run dev 启动vue项目
5. 也可以简单调试后单独启动node_modules里面的first服务


### 模块功能：
1. (netty)基于netty,websocket 以及rocketmq的聊天功能
2. (first) 关于研战到底模块很多api在这个里面，使用了shardingsphere读写分离
3. (renren)后台管理模块 将运维，数据库管理等在前端页面展示
4. (common_shiro)权限管理系统改造成一个微服务(jwt+redis实现分布式权限)
5. (file)文件模块导入、导出 oss ,上传图片、Excel操作等
6. (fileTest) 测试nacos负载均衡
7. (devops) 运维模块。
8. (loginUser) 用户中心模块。统一完成用户授权
9. (database)展示不同数据源的表结构，生成项目所需要的代码，执行sql文件
#### 完成的功能
1. 一个后台管理系统(运维模块，数据库模块等模块) 一个前台应用(vue3，主要包含IM模块，文件模块，相关增删改查模块)
2. 基于netty,websocket 以及rocketmq的聊天功能
3. 异常处理系统
4. swagger 接口文档地址 localhost:8999/swagger-ui.html
5. 引入springcloud，使用了gateway网关与nacos注册中心
6. 将使用的中间件RabbitMQ，RocketMQ使用docker部署在虚拟机上
7. 分布式权限功能已完成

### 补充：
1. swagger 接口文档地址 localhost:8999/swagger-ui.html


### 短期TODO
1. 完善websocket返回格式
2. 完善心跳机制
3. 完善websocket会话 
4. refrshtoken机制提供前端支持
5. 提供redis集群和mq集群
6. MDC追踪日志，使用es分析日志。可以使用es
7. 完成聊天消息的分表工作；
8. 处理redis和mysql的一致性
目前来说websocket功能上面可以跑通，但是易用性上面还有很大进步空间，
可以多完善，统一风格。


### 长期TODO:
1. 提供导出pdf，并且自定义表格模板( file模块)
2. 提供java执行sql层面的工作 (database模块)
3. 前端学习拖拽式编程，可以完成相应的建模的等工作 (database以及flowable)
4. 导入工作流模块(flowable)
5. 写文档 (类似于爱组搭，写清楚每个模块的作用)
6. jmeter测试集成(devops)
7. 使用docker-compose(devops)


---8想要通过这个来孵化别的项目
8.1 在文件模块 里面放上生成vue的基础文件  就是vue2 或者vue3 的基础框架
8.2 完成一些基础表的建设。可以方便
8.3 完善知识等模块
8.4 流程相关还需要好长时间

### 技术方面：
需要解决的技术方面问题：

1. 分布式情况下的session共享问题；(已解决)
2. 分布式情况下的事务问题 计划使用seata，
3. 分布式情况下shrio该如何使用才能让各个系统之间共享权限；(已解决)


### 与本项目相关技术的博文
1. [sprinboot单体项目升级成springcloud项目【第一期】](https://blog.csdn.net/qq_21561833/article/details/127348148)
2. [前端项目技术选型以及页面展示【第二期】](https://blog.csdn.net/qq_21561833/article/details/131676184)
3.  loginUser [分布式权限 shiro + jwt + redis 【第三期】](https://blog.csdn.net/qq_21561833/article/details/127605241)
4.  devops  [给为服务添加运维模块 统一管理【第四期】](https://blog.csdn.net/qq_21561833/article/details/127821543)
5.  database [微服务数据库模块【第五期】](https://blog.csdn.net/qq_21561833/article/details/131315983)  
6.  netty  [netty与mq在项目中的使用【第六期】](https://blog.csdn.net/qq_21561833/article/details/131317748)   
7.  common [common里面添加的常用依赖以及用途]()
8. 项目质量提升计划(1.写文档 2.加注释3.取出不需要的import 4.去除不需要的maven )
         

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

#### 参考过的开源项目
1. 人人fast开源项目  后台管理系统前端借鉴
2. new-bee 前台 vue3借鉴


