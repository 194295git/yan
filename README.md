# yanzhandaodi_back

#### 介绍
- 一个开源的springcloud项目。体验和尝试springcloud的各种功能。
- 内容：抽象出cloud中使用到的常用技术。目标做一个低代码的组件平台。将流程，数据库操作，
建模、docker运维、文件系统等抽象出来方便的调用。会定期提升代码质量。还有移动端uniapp的支持。
- 目的：将工作中使用到的技术脱离业务剥离出来，方便复用、借鉴。让使用者可以轻松调用
- 模块：包括文件模块、运维模块、数据库模块、netty模块、用户中心模块、还有其他业务模块。
- 后续：会开发sdk，文档，APIStroe等提供更加简介的调用方式。然后考虑孵化一个产品。


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
1. 一个后台管理系统 一个前台应用
2. 问题，回答，评论，点赞增删改查
4. 考研面临的专业以及专业下的细分学科
5. 引入springcloud，使用了gateway网关与nacos注册中心
6. 将使用的中间件rabbitmq使用docker部署在虚拟机上
7. 给common模块添加了日志，完善了日志模块，清理了一些不需要的依赖

### 补充：
1. swagger 接口文档地址 localhost:8999/swagger-ui.html

### todo:
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
想做的事情好多呀，想完善数据库模块建表，想完善文件系统模块。想完善分布式的技术。想抽象前端组件。
想给自己整一个文档。其实也不用急的拓展。能把目前项目中用到的技术难点整理成文档就已经很不容易啦。
### 技术方面：
需要解决的技术方面问题：

1. 分布式情况下的session共享问题；
2. 分布式情况下的事务问题 计划使用seata，学习中；
3. 分布式情况下shrio该如何使用才能让各个系统之间共享权限；
4. netty集群的话也是需要考虑这样类似的问题

### 与本项目相关技术的博文
1.  loginUser [分布式权限 shiro + jwt + redis ](https://blog.csdn.net/qq_21561833/article/details/127605241)
2. [sprinboot单体项目升级成springcloud项目](https://blog.csdn.net/qq_21561833/article/details/127348148) 
3.  devops  [给为服务添加运维模块 统一管理（第四期](https://blog.csdn.net/qq_21561833/article/details/127821543)
4.  database [微服务数据库模块（第五期](https://blog.csdn.net/qq_21561833/article/details/131315983)  
5.  netty  [netty与mq在项目中的使用(第六期)]()   
6.  common [common里面添加的常用依赖以及用途]()
7. 项目质量提升计划(1.写文档 2.加注释3.取出不需要的import 4.去除不需要的maven )
         

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


