### 1.pom文件引入
路径 rose-modules/first
        <dependency>
            <groupId>org.apache.shardingsphere</groupId>
            <artifactId>shardingsphere-jdbc-core-spring-boot-starter</artifactId>
            <version>5.1.1</version>
        </dependency>

### 2.添加配置
这个是读写分离的配置；

```
  shardingsphere:
    props:
      sql-show: true
    mode:
      type: Memory
    datasource:    # 配置数据源
      names: yan,slave1,slave2    # 分库的逻辑库名,可配置多个
      yan:    # 逻辑库名
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/yan?characterEncoding=utf-8&useSSL=false&useUnicode=true&serverTimezone=UTC
        username: root
        password: 1942951600
      slave1:    # 逻辑库名
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/yan_slave?characterEncoding=utf-8&useSSL=false&useUnicode=true&serverTimezone=UTC
        username: root
        password: 1942951600
      slave2:    # 逻辑库名
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/yan_slave?characterEncoding=utf-8&useSSL=false&useUnicode=true&serverTimezone=UTC
        username: root
        password: 1942951600
    master-slave-rules:
      myds:
        master-data-source-name: yan
        slave-data-source-names: slave1,slave2
    rules:
      readwrite-splitting:
#      replica-query:
        data-sources:
          # myds 定义的是上层的数据源
          myds:
            type: Static
            props:
              write-data-source-name: yan
#              read-data-source-names: slave1,slave2
              read-data-source-names: yan
#            primary-data-source-name: yan
#            replica-data-source-names: slave1,slave2
            load-balancer-name: alg_round
        load-balancers:
          alg_round:
            type: ROUND_ROBIN



```
### 分库分表
#### 1.1分库策略
分表常见的策略是有水平分表和垂直分表； 水平分表比如按照单位分，按照年份分 垂直分表就是

分库的策略
### 1.2分库分表带来的问题

1.事务问题
2.跨节点join问题
3.跨节点count group by  order by 问题
4.数据迁移 再次扩容的问题
5.主键id问题

分库数量
mysql单库 超过五千万
oracle超过一亿  数据库压力就比较大了
初次分建议4-8个库

#### 1.3配置主从数据库



### 多数据源策略  是指可以使用多个数据源
```
 datasource:
    type: com.alibaba.druid.pool.DruidDat aSource
    master:
      #MySQL配置
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/yan?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
      username: root
      password: 1942951600
    slave:
      #MySQL配置
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/yan_login?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
      username: root
      password: 1942951600
```
 
### 不同数据类型 oralce mysql 