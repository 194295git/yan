# yanzhandaodi_back

#### 介绍
- YAN_IM，一个开源的分布式聊天服务。基于Netty构建的高性能websocket服务器。支持群聊单聊,表情包以及发送图片。
支持消息可靠，消息防重，消息有序。同时基础架构有分布式权限，分布式唯一id，分布式websockt，
分布式事务等常见的分布式技术。
- 分布式webscoket服务重点在netty模块。正在逐步完善聊天所需要的全部功能。比如语音消息，音视频等。对于消息收
发模型以及群聊消息优化也在进行中。

- 目的：将IM使用到的东西抽象出来，方便复用、借鉴。让使用者可以轻松调用
- 模块：包括文件模块、netty模块、用户中心模块、还有其他业务模块。
- 后续：会开发sdk，文档，APIStroe等提供更加简介的调用方式。
- 文档：从图片，架构，技术细节以及视频等方面帮助阅读此项目

###
#### 服务说明
1. gateway服务     提供统一网关
2. file服务        提供文件上传下载
3. first服务       提供app基础功能
4. netty服务       聊天功能服务，消息推送框架
5. vue3              vue重构的前端项目
6. common_shiro    分布式权限
7. loginUser      用户中心、统一完成授权

### netty 模块实现功能重点说明
通过集群来支持高并发，通过六层报文来实现高可靠。通过幂等来解决消息重复
1. 支持单聊和群聊 ，支持发送表情包
2. 聊天内容ack机制，然后未送达重试机制(重试三次，三次之后为失败)
3. 设计websocket报文，保证消息的可靠性
4. 支持分布式，支持gateway负载均衡，将netty服务端注册进入nacos
5. 支持websocket授权功能（已经做了相关的截取参数以及改造前端vue）(待改造成异步)
6. 接口引用redis设计成幂等 ,lua表达式完成消息幂等
7. 会话管理(redis结合本地Map) 
8. 支持心跳功能，支持客户端断线重连
9. 设置消息加密(待完善)



#### 软件架构
软件架构说明
主要技术点：Spring Boot Spring Cloud Nacos  Netty mybatis-plus RocketMQ Docker
前端：Vue3    Websocket Vant
#### 启动说明
1. 先启动nacos 
2. 启动gateway服务
3. 启动node_modules 里面的first服务，启动netty服务
4. 启动Vue3项目 npm run serve 



### 模块功能：
1. (netty)基于netty,websocket 以及rocketmq的聊天功能
2. (first) 关于研战到底模块很多api在这个里面，使用了shardingsphere读写分离
3. (common_shiro)权限管理系统改造成一个微服务(jwt+redis实现分布式权限)
4. (file)文件模块导入、导出 oss ,上传图片、Excel操作等
5. (loginUser) 用户中心模块。统一完成用户授权



### 补充：
1. swagger 接口文档地址 localhost:8999/swagger-ui.html


### 短期TODO
1. 完善websocket返回格式，统一报文格式
2. 完善心跳机制
3. 完善websocket会话 
4. refrshtoken机制提供前端支持
5. 提供redis集群和mq集群
6. MDC追踪日志，使用es分析日志。可以使用es
7. 完成聊天消息的分表工作；
8. 处理redis和mysql的一致性
9. 完善群聊遇到的其他常见功能
10. jmeter测试
目前来说websocket功能上面可以跑通，但是易用性上面还有很大进步空间，
可以多完善，统一报文风格。


### 与本项目相关技术的博文
已经涵盖基础的技术选型，以及架构选择，消息可靠，消息防重当方面写了对应文章。业务方面：单聊、群聊、表情包等
1. [sprinboot单体项目升级成springcloud项目【第一期】](https://blog.csdn.net/qq_21561833/article/details/127348148)
2. [前端项目技术选型以及页面展示【第二期】](https://blog.csdn.net/qq_21561833/article/details/131676184)
3.  loginUser [分布式权限 shiro + jwt + redis 【第三期】](https://blog.csdn.net/qq_21561833/article/details/127605241)
4.  devops  [给为服务添加运维模块 统一管理【第四期】](https://blog.csdn.net/qq_21561833/article/details/127821543)
5.  database [微服务数据库模块【第五期】](https://blog.csdn.net/qq_21561833/article/details/131315983)  
6.  netty  [netty与mq在项目中的使用【第六期】](https://blog.csdn.net/qq_21561833/article/details/131317748)   
7. [分布式websocket即时通信(IM)系统构建指南【第七期】](https://blog.csdn.net/qq_21561833/article/details/135658862)
8. [分布式websocket即时通信(IM)系统保证消息可靠性【第八期】](https://blog.csdn.net/qq_21561833/article/details/135681086)
9. [分布式websocket IM聊天系统相关问题问答【第九期】](https://blog.csdn.net/qq_21561833/article/details/135734395)
10. [什么？websocket也有权限！这个应该怎么做？【第十期】](https://blog.csdn.net/qq_21561833/article/details/135758402)
11. [分布式ID是什么，以美团Leaf为例改造融入自己项目【第十一期】](https://blog.csdn.net/qq_21561833/article/details/135852852)
12. [IM聊天系统为什么需要做消息幂等？如何使用Redis以及Lua脚本做消息幂等【第12期】](https://blog.csdn.net/qq_21561833/article/details/136104296)
13. [微信发送一条消息经历哪些过程。企业微信以及钉钉的IM架构对比【第13期】](https://blog.csdn.net/qq_21561833/article/details/136102083)
14. [微信群为什么上限是500人，IM设计系统中的群聊的设计难点【第14期】](https://blog.csdn.net/qq_21561833/article/details/136264878)
15. [【分布式websocket】RocketMQ发送消息保证消息最终一致性需要做哪些处理？【第15期】](https://blog.csdn.net/qq_21561833/article/details/136389095)

16. [【分布式websocket】群聊中的各种难点以及解决推拉结合【第16期】](https://blog.csdn.net/qq_21561833/article/details/136445874)

17. [【分布式webscoket】未读消息如何设计？解决缓存与数据库数据一致性！推送未读消息流程【第17期】](https://blog.csdn.net/qq_21561833/article/details/136447741)

18. [IM系统客户端消息存储在手机电脑浏览器分别存储在什么地方?对消息加密策略？如何保证服务端消息和客户端消息一致性【第18期】](https://blog.csdn.net/qq_21561833/article/details/136535333)
         

如果有什么问题留言联系我。qq:1942951600

### 演示图第二版
<table>
    <tr>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/2024/03/18/734dbb00990d4dab868d8e9f96e98383用户界面.png"/></td>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/2024/03/18/08b870cb78cb431f94e96e76d5db293a聊天框详细内容.png"/></td>
    </tr>
    <tr>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/2024/03/18/8d45fa5387024645b2ffbe307a9d16ea详情页.png"/></td>
        <td><img src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/2024/03/18/dd6a2b5f2adb4d508811eb67de555404me.png"/></td>
    </tr>
</table>

### 演示图第一版

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



