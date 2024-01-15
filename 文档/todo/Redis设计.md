# Redis键管理策略文档


### 1.权限模块shrio
- 类型 String
- 功能 存储用户权限
- 过期时间  -1
- 例子 yan_shiro_56C02DF0516B4B079ABFCEC08169E577 
```json
yan_shiro_56C02DF0516B4B079ABFCEC08169E577

[
"yan:common:zero"
]
```

### 2.分布式websocket模块
- 类型 Hash
- 功能 存储集群的服务地址
- 过期时间  -1
- 例子 yan_websocket_server
```json
yan_websocket_server

key: 192.168.56.1:8084 value:8888
```

- 类型 Set
- 功能 存储一个服务器下面有什么客户端，校验已经存在的服务器代码，
以及发送给所有人的消息
- 过期时间  -1
- 例子 yan_serverclients_192.168.56.1
```json
yan_serverclients_192.168.56.1

1879878-NKCNO-NKNK
```
- 类型 Hash
- 功能 存储
- 过期时间 6000
- 例子 yan_serverclients_192.168.56.1
```json
yan_serverclients_192.168.56.1

id  | key       | value 
1   | channelId | 1879878-NKCNO-NKNK
```

### 3.分布式id
- 类型 Set
- 功能 做幂等
- 过期时间  -1
- 例子 yan_leaf 
```json
yan_leaf
16751  16752
```

### 4.业务模块
- 类型 String
- 功能 为了首页内容需要的缓存，提升访问速度
- 过期时间  -1
- 例子 yan_first_indexContentJson 
```json
yan_first_indexContentJson

[
{
answerCtntShort:"测试写一条回答"
answerId:"1421788993934102529"
feedSourceImg:"https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/26/0ea48cb529e54573ae791ffbde3fcd9fu=2095913610,1514262792&fm=26&gp=0.jpg"
question:"气象铁塔的辐射大吗？"
questionId:3
}
]
```


## 2. Redis简介
- 什么是Redis？
- Redis的特点和优势。
- Redis在分布式系统中的常见用途。

## 3. 键命名规范
- 命名约定（如`<service>:<type>:<identifier>`）。
- 避免命名冲突的策略。
- 维护键命名一致性的重要性。

## 4. 键类型和数据结构
- 字符串（String）
- 列表（List）
- 集合（Set）
- 散列（Hash）
- 有序集合（Sorted Set）
- 每种类型的使用场景和示例。

## 5. 键的过期策略
- 如何设置键的TTL。
- 过期键对性能的影响。
- 自动和手动清理策略。

## 6. 键的分区策略
- 分区的必要性。
- 分区技术（如一致性哈希）。
- 分区对性能的影响。

## 7. 键的存储和访问模式
- 键的存储结构。
- 客户端访问模式。
- 读写操作的性能考虑。

## 8. 安全性考虑
- 访问控制（ACL）。
- 传输中的加密（SSL/TLS）。
- 持久化数据的加密。

