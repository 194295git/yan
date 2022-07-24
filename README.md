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
前端：vue3 vue2 uniapp uview websocket
#### 启动说明
1. 先启动nacos 
2. 启动gateway服务
3. 启动node_modules 里面的first服务，启动netty服务
4. 启动UI前端服务 使用hbuilder启动，或者npm run dev 启动vue项目
5. 也可以简单调试后单独启动node_modules里面的first服务

#### 服务说明
1. gateway服务 提供统一网关
2. file服务    提供文件上传下载
3. first服务   提供app基础功能
4. UIManager   后台管理的前端
5. renren      基于renren-fast的研战到底后台
5. UI2         研战到底前端项目
### 已完成的功能：
1. 用户登录注册token
2. 提出问题，回答，在回答下面评论，点赞
3. oss功能，上传到阿里云，并通过网关代理
4. 考研面临的专业以及专业下的细分学科
5. 基于netty,websocket 以及rocketmq的聊天功能
6. 将python开发的智能择校模块集成进去
7. 异常处理系统
8. swagger 接口文档地址 localhost:8999/swagger-ui.html
9. 引入springcloud，使用了gateway网关与nacos注册中心
10. 将使用的中间件rabbitmq使用docker部署在虚拟机上

### 今日发现待完善：
1.聊天记录的清除功能没有做
2.关于聊天的小红点这块有问题
3.先抽出一个文件的微服务吧，让上传图片先跑起来，再增加上传excel，上传word等
4.想做的事情实在是太多了

### 后续计划：
1. 使用docker部署。利用nginx转发请求
2. 完善用户聊天功能，解决bug
3. 增加买卖商品的功能，在该场景下使用rabbitmq
4. 引入refreshToken机制，让鉴权做的更加放心
5. 引入自定义权限处理；对于不同的人开启不同的功能
6. 将文件上传抽取出一个微服务的功能，不仅仅限于上传图片。添加Word，PDF等支持。
7. 添加后台管理系统；添加后台的管理模块；可以管理用户上传的内容，管理学科分类，管理热榜等功能。并且对需要做数据可视化的地方做可视化
8. 完善前端的页面逻辑，后续可能考虑使用vue3重构
9. 完善Excel处理的模块

      
         

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


