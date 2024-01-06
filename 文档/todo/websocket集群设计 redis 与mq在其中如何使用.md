### redis 设计
yan_websocket_server:192.168.56.1:8084(记录所有的服务端)
yan_serverclients_192.168.56.1:  (一个客户端底下有多少用户)
yan_client_1879878-NKCNO-NKNK (分布式用户具体的信息)

### mq主题发送：
可以使用rocketmqTmeplate提升效率
rocketmq consumer started,
nameserver:192.168.56.20:9876, group:group192-168-56-1-8084


### websock集群设计:
websocket互相转发消息通过mq来完成消息的转发

```json
msg:R
//发送消息
{
	"type": 1,
	"params": {
		"toMessageId": "56C02DF0516B4B079ABFCEC08169E577",
		"message": "20240102",
		"fileType": 0
	}
}
msg:A
//收到客户端消息
{
	"type": 0,
	"params": {
		"date": "Tue Jan 02 10:28:12 CST 2024",
		"message": "20240102"
	},
	"status": 200
}

```
```json
msg:N
//收到消息回应
{
	"activeTime": 1704162492912,
	"from": "system",
	"messageId": "c41c45611af44a36a79a93d5193aa116",
	"msg": {
		"type": 2,
		"params": {
			"toUser": {
				"openid": "56C02DF0516B4B079ABFCEC08169E577",
				"loginStatus": "1",
				"userName": "123"
			},
			"fromUser": {
				"openid": "1879878-NKCNO-NKNK",
				"loginStatus": "1",
				"userName": "rose"
			},
			"message": "20240102",
			"fileType": "0"
		},
		"status": 200
	},
	"msgType": 1,
	"requestId": "fec21e30-9260-486f-b78e-04aa3b3d7b5f",
	"sessionId": "192.168.56.1:8084_56C02DF0516B4B079ABFCEC08169E577_20240102102737",
	"to": ["56C02DF0516B4B079ABFCEC08169E577"],
	"trigger": 1
} 
```
```json
//发送ack消息
{
	"type": 15,
	"params": {
		"from": "client",
		"msgid": "16516",
		"fromUser": "56C02DF0516B4B079ABFCEC08169E577",
		"toUser": "1879878-NKCNO-NKNK"
	}
}
//收到消息
{
	"type": 16,
	"params": {
		"date": "Tue Jan 02 21:59:05 CST 2024",
		"message": "16516"
	},
	"status": 200
}

```
```json
{
	"activeTime": 1704203945910,
	"from": "system",
	"messageId": "17adfc694782429ea973076d83fe8c8a",
	"msg": {
		"type": 17,
		"status": 200
	},
	"msgType": 1,
	"requestId": "eee728b0-0182-46c7-9d97-39e711f20368",
	"sessionId": "192.168.56.1:8084_56C02DF0516B4B079ABFCEC08169E577_20240102215858",
	"to": ["56C02DF0516B4B079ABFCEC08169E577"],
	"trigger": 1
}
```
用户在线是6条报文
用户不在线是几条报文呢；
离线消息也是需要ack 的。如果没有ack 将不知道是否保存成功了没有；

在first里面需要构造一下这样的消息来完成相关的消息推送机制.
将mq封装成http请求；
需要使用到openfeign这样的技术 或者先使用restTemplateld 完成http消息的发送.

```java
    @ApiOperation(value="消息推送接口", notes="根据用户标识进行推送，返回不存在的用户")
    @RequestMapping(value="/message/send",method = RequestMethod.POST)
    public GenericResponse send(@RequestBody @Valid SendRequest request){
        GenericResponse result = null;
        Set notExist = messageService.execute(request, Commond.HTTP_REQUEST);

        if(notExist!= null && notExist.size()>0){
            //存在找不到的客户端
            result =GenericResponse.response(ServiceError.NOT_EXIST_CLIENT,notExist);
//                    new GenericResponse(,notExist);
        }else{
            //客户端都存在
            result = GenericResponse.response(ServiceError.NORMAL);
        }
        return result;
    }
```



type为 15  16 17三条消息完成消息的闭环
然后消息队里那块还是需要处理一下；

消息重发机制需要再次研究一下
还有mq的ack机制也是需要研究的；


分发消息还需要ack一下；
ACK的话使用mq换一个主题。 netty-ack 后续建立一个常量类来记录所有使用到的mq主题
订阅该服务实例名的topic

#### 还需要一整套ack机制来保证消息确认；
客户端再收到消息后还需要重新向发送端发送消息；收到了才能算是发送成功；
然后以唯一消息id为唯一键拉取做消息幂等；
消息重发做了消息重试了；
现在还需要做消息幂等

还需要区分一下重发消息的类型 ，这个重发的消息不需要再次重发了


后续mq的消息确认机制也需要完善一下； 包括看要不要存库记录
 使用本地消息表的方式来完成

### http三次握手和四次挥手


### websocket 鉴权 
先研究一下shiro自己之前是怎么鉴定权限的，然后迁移过来。迁移到这个netty的应用里面来；
感觉有token 随便找个地方存储一下都行；
感觉自己当时写的shiro也有点东西。竟然可以分离的这么好，不需要什么配置，现在竟然不会了。


两种处理思路 一种是在还是http请求的时候完成一下认证和授权
第二种思路，成为ws请求的时候进行授权；


是的 我的token放在url中，
 我在这里面通过fullhttprequest
 获取了url然后做个字符串处理就获取到了token，
 然后调用单点登录的接口完成token校验
 
 放到http那里处理的话就不需要每次都参数带上token去校验了；看起来也不难做
 
 还需要一些openfeign的知识 添加上请求头header 然后去请求。测试接口通过结果.
 自己写一个feign的过滤器 添加上token就得了；
 使用一下threadlocal来存储一下token完事了；
 
 ### java 实现一下幂等操作；
####  哪些语句是幂等的
 select语句是天然幂等的
 update语句 修改成固定值是天然幂等的 如 update table set age=18 where id=1 , 变量值修改不是幂等的 ,如 update table set age=age+1 where id=1
 delect语句是天然幂等的
 insert不是幂等的
#### 幂等性出现的场景
表单重复提交
变量值修改多次
#### 幂等性解决方案
token机制 (适用于insert , update语句)
乐观锁 (适用于update语句)
1.数据库唯一索引 (适用于insert语句)

2.   token 机制  要么使用redis  就是订单的思路  插入思路；携带唯一msgid ， redis 中有的话就删除；
3. 防重表  区分业务表

使用一下redis发放token机制吧；这个看起来也简单一些； 而且和插入数据稍微不一样一点；
//使用redis 来保障消息幂等的话  还需要使用分布式锁， 
不能出现已经删除了redis但是事务回滚了的情况

但是如果是唯一主键的话就不能使用分库分表了，就会有数据库上面的局限；
### redis lua 表达式
保证幂等需要的知识还是有点多的；

二）诸如fegin这种远程调用的失败重试问题
这个问题是比如fegin这种远程接口调用失败，自动重试引起的，特点是这两次的远程调用请求完全相同。
直接说解决方案: 可以生命一个唯一请求id,放在请求的Header中，被调用方接收到请求以后，首先将请求的唯一id存在reids,可以利用setNx指令进行防重，也可以利用redis的set。
看到网上有说将fegin的自动重试功能禁止的，还有说创建一个拦截器，只要是完全一样的请求就拦截的，个人觉得不太靠谱

三）消息重试
直接上结论吧：

将业务设计成幂等的，比如在接收到消息之前，先去数据库查询下数据状态，状态对的话就不处理了
根据消息中的消息id,去redis中查找是否已经存在。
### redis 和MySQL的的数据一致性； 

可以通过引入消息队列来完成一致性；
后续的这个redis 的方法还是要在考虑考虑要不要换一下；
因为目前来看
###  不同服务之间MySQL之间保持一致

### redis mq 之间保证一致性


 
 ### mq的ack确认机制
 等这个做完就做相关的mq的校验去重；
 然后做成
 
 ### nacos redis 集群相关保证成功策略
 
 ### 心跳机制以及相关的断线重连机制
 
 


