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

后续mq的消息确认机制也需要完善一下； 包括看要不要存库记录
 使用本地消息表的方式来完成

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
 
 
 ### mq的ack确认机制
 等这个做完就做相关的mq的校验去重；
 然后做成
 
 ### nacos redis 集群相关保证成功策略
 
 ### 心跳机制以及相关的断线重连机制
 
 


