### 1.升级所需要的前置准备
1.把现有的服务拆分成多个模块
2.技术选型（采用Spring Cloud Alibaba）
3.依照模块对应的进行升级

我将按照 引入依赖 加配置 改代码三部曲来完成模块的升级。
拆分服务暂时拆分成，登录权限模块。文件管理模块。聊天netty模块。还有主要业务模块。
### 2.公共依赖
#### 2.1依赖
common.xml
通用依赖里面引入了 服务注册于服务发现。采用nacos。每一个模块都需要服务注册与发现。熔断器。

```xml

    <properties>
        <commons.lang.version>2.6</commons.lang.version>
        <mysql.version>8.0.17</mysql.version>
        <javax.servlet.version>2.5</javax.servlet.version>
        <jjwt.version>0.9.0</jjwt.version>
    </properties>

    <dependencies>
        <!--引入熔断器-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
        </dependency>
        

        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>${commons.lang.version}</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>${javax.servlet.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- 服务注册与发现 -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
            <version>2.2.0.RELEASE</version>
        </dependency>
        <!--        配置中心来做配置管理-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
            <version>2.2.0.RELEASE</version>
        </dependency>





        <!-- 链路追踪  -->
        <!--        <dependency>-->
        <!--            <groupId>org.springframework.cloud</groupId>-->
        <!--            <artifactId>spring-cloud-starter-sleuth</artifactId>-->
        <!--        </dependency>-->
    </dependencies>

```
#### 2.2 配置

关于这块的配置。
bootstrap.properties
配置了相关的端口名称，重要的是配置了nacos的地址

```xml
spring.cloud.nacos.discovery.server-addr=192.168.56.20:8848
spring.cloud.nacos.config.enabled=true

spring.cloud.nacos.config.file-extension=yaml
spring.application.name=yan-file
server.port=9000
```

### 3.网关gateway
##### 3.1依赖
```xml

    <properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Hoxton.SR4</spring-cloud.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>

        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.rose.yan</groupId>
            <artifactId>common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
            <version>2.1.0.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>



    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

```
引入的依赖网关；
##### 3.2 配置

```xml
spring:
  application:
    name: gateway
  cloud:
    nacos:
      discovery:
        server-addr: 192.168.56.20:8848
    sentinel:
      transport:
        dashboard: localhost:8333
    gateway:
      routes:
        - id: yan_first
          uri: lb://yan-first
          predicates:
            - Path=/api/first/**
          filters:
            - RewritePath=/api/first/(?<segment>.*), /$\{segment}
        - id: yan_loginUser
          uri: lb://yan-loginUser
          predicates:
            - Path=/api/loginUser/**
          filters:
            - RewritePath=/api/loginUser/(?<segment>.*), /$\{segment}

```
这个里面主要配置是使用了路由映射。id是自己器的，uri：负载均衡到在nacos中注册的服务。predicates里面放上前端请求到某服务需要加上的路径。filters重写请求进服务的路径。
##### 3.3代码

```java
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
```
加上@EnableDiscoveryClient 允许注册中心发现的注解。然后加上跨域请求注解

```java
@Configuration
public class CorsConfiguration {

	@Bean
	public CorsWebFilter corsWebFilter(){
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 配置跨越允许任意请求头
		corsConfiguration.addAllowedHeader("*");
		// 允许任意方法
		corsConfiguration.addAllowedMethod("*");
		// 允许任意请求来源
		corsConfiguration.addAllowedOrigin("*");
		// 允许携带cookie
		corsConfiguration.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsWebFilter(source);
	}
}

```
### 4.openfeign
##### 4.1引入依赖
服务调用方
```xml

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```
##### 4.2配置 无
##### 4.3代码
启动类上面加的注解
EnableFeignClients；以接口的形式远程调用服务。
```java
@EnableRabbit
@EnableFeignClients(basePackages = "com.rose.yaj.feign")
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.rose.yaj.mapper")
@ComponentScan(basePackages = {"com.rose"})
public class DemoApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}


```java
@FeignClient("yan-file")
public interface OSSFeignService {

    @PostMapping(value="/file/oss/fileoss",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponse uploadOssFile(@RequestPart("file")MultipartFile file);

}
```
实际使用过程中FeignClient标记上对应的微服务。；
然后里面将文件服务里面涉及到的接口签名复制出来；在其他文件里面就可以使用了。
##### 文件微服务里面涉及到的代码书写
```java
@RestController
@RequestMapping("/file/oss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像(图片)的方法.
     * @param file
     * @return
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponse uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        System.out.println("经过了oss办法");
        String url = ossService.uploadFileAvatar(file);
        if(url==null){
            return GenericResponse.response(ServiceError.OSS_ERROR);
        }else{
            return GenericResponse.response(ServiceError.NORMAL, url);
        }

    }

}
```
### 5.流量监控
##### 5.1 依赖

```xml
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
```

##### 5.2 配置

```xml
spring:
  application:
    name: gateway
  cloud:
    nacos:
      discovery:
        server-addr: 192.168.56.20:8848
    sentinel:
      transport:
        dashboard: localhost:8333
```
sentinel这样子就搞定配置了；
然后默认是懒加载的，你第一次请求的时候。然后就控制台就加载进去了。
需要去下载个jar包。使用如下的启动命令启动。
java -Dserver.port=8333 -jar sentinel.jar
打开控制台默认账号密码都是 sentinel 登录后就可以进行简单的监控了。
### 6.负载均衡
将文件部分的代码拷贝一份。然后启动。nacos里面自带负载均衡功能的。可以看到。

### 7.前端部分的改进
更改了axios文件夹。然后
```js
export function apiFirstPost(url,params){
  return axios.post('/api/first'+url, params);
}
export function apiFirstGet(url,params){
  return axios.get('/api/first'+url, params);
}
export function apiLoginUserPost(url,params){
  return axios.post('/api/loginUser'+url, params);
}
export default {axios,apiFirstPost,apiFirstGet,apiLoginUserPost}
```

然后引用的时候分开引用就ok了


```js
 import  {apiFirstPost,apiFirstGet,apiLoginUserPost} from '../utils/axios'


 export function login(params) {
   return apiLoginUserPost('/sys/login', params);
 }
 
 export function register(params) {
   return apiFirstPost('/registByWeb', params);
 }
 
 export function getUserInfo() {
   return apiFirstGet('/yaj/yan-user/getUserInfo');
 }
 export function getUserInfoMe() {
   return apiFirstGet('/yaj/yan-user/getUserInfoMe');
 }
```

