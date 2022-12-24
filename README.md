# yanzhandaodi_back

#### 介绍
- 一个开源的springcloud项目。体验和尝试springcloud的各种功能。
- 目的：将工作中使用到的技术脱离业务剥离出来，方便复用、借鉴。让使用者可以轻松调用
- 包括但不限于分布式权限，分布式事务，devops，数据库管理等。将功能细分为模块。包括文件模块、运维模块、数据库
模块、netty模块、用户中心模块、还有其他业务模块。所有的分业务模块设计到的功能可以直接
使用。后续开发sdk，提供更加简介的调用方式。业务部分主要为研战到底后端 主要为研战到底
APP提供服务。
业务主要功能点有。 
1. 开发智能择校模块 
2. 构建信息平台（学习圈）整合资料、信息(提出问题，发表问题，评论点赞等功能)  
3. 搭建研究生和在考生服务生沟通的平台(基于netty聊天功能)。

研战到底APP
1. 基于uniapp和uView   vue3版本的使用的vant

#### 服务说明
1. gateway服务     提供统一网关
2. file服务        提供文件上传下载
3. first服务       提供app基础功能
4. netty服务       聊天功能服务
5. database服务    数据库相关服务 
5. renren          后台管理系统
6. UIManager       后台管理的前端
7. UI2             研战到底前端项目
8. v2              vue重构的前端项目
9. common_shiro    分布式权限
10. loginUser      用户中心、统一完成授权
11. devops         相关的运维板块

#### 软件架构
软件架构说明
主要技术点：springboot springcloud nacos  netty mybatis-plus rabbitmq  
前端：vue3 vue2 uniapp uview websocket vant
#### 启动说明
1. 先启动nacos 
2. 启动gateway服务
3. 启动node_modules 里面的first服务，启动netty服务
4. 启动UI前端服务 使用hbuilder启动，或者npm run dev 启动vue项目
5. 也可以简单调试后单独启动node_modules里面的first服务


### 已完成的功能：
1. 一个后台管理系统 一个前台应用
2. 问题，回答，评论，点赞增删改查
4. 考研面临的专业以及专业下的细分学科
5.. 引入springcloud，使用了gateway网关与nacos注册中心
7. 将使用的中间件rabbitmq使用docker部署在虚拟机上
8. (netty)基于netty,websocket 以及rocketmq的聊天功能
9. (first) 关于研战到底模块很多api在这个里面
10. (renren)后台管理模块 将运维，数据库管理等在前端页面展示
11. (common_shiro)权限管理系统改造成一个微服务(jwt+redis实现分布式权限)
12. (file)文件模块导入、导出 oss ,上传图片、Excel操作等
13. (fileTest) 测试nacos负载均衡
13. (devops) 运维模块。包括swagger
14. (loginUser) 用户中心模块。统一完成用户授权
15. (database)展示不同数据源的表结构，生成项目所需要的代码，执行sql文件

### 补充：
1. swagger 接口文档地址 localhost:8999/swagger-ui.html

### todo:
1. 提供导出pdf，并且自定义表格模板( file模块)
2. 提供java执行sql层面的工作 (database模块)
3. 前端学习西拖拽式编程，可以完成相应的建模的等工作 (database以及flowable)
4. 导入工作流模块(flowable)
5. 写文档 (类似于爱组搭，写清楚每个模块的作用)
6. jmeter测试集成(devops)
7. 使用docker-compose(devops)

### 技术方面：
需要解决的技术方面问题：

1. 分布式情况下的session共享问题；
2. 分布式情况下的事务问题 计划使用seata，学习中；
3. 分布式情况下shrio该如何使用才能让各个系统之间共享权限；
4. netty集群的话也是需要考虑这样类似的问题

### 与本项目相关技术的博文
1. 分布式权限 shiro + jwt + redis   https://blog.csdn.net/qq_21561833/article/details/127605241
2. sprinboot单体项目升级成springcloud项目 https://blog.csdn.net/qq_21561833/article/details/127348148
3. 给为服务添加运维模块 统一管理（第四期） https://blog.csdn.net/qq_21561833/article/details/127821543
      
         

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


