package com.rose;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 模块主要功能
 * 1.生成后端需要的增删改查
 * 2.多数据源
 * 3.分库分表啥的在这个里面操作
 * 4.区分不同类型的数据库(oracle,mysql)
 * 5.读写分离
 * 6.ShardingSphere5
 *
 */

@EnableAsync
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@MapperScan("com.rose.dao")
public class AsyncMQApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncMQApplication.class, args);
	}
}
