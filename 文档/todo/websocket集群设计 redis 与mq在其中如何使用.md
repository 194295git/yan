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

订阅该服务实例名的topic

#### 还需要一整套ack机制来保证消息确认；
客户端再收到消息后还需要重新向发送端发送消息；收到了才能算是发送成功；
然后以唯一消息id为唯一键拉取做消息幂等；



