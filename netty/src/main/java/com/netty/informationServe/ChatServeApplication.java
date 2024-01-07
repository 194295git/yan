package com.netty.informationServe;

import com.netty.informationServe.serve.WebSocketServe;
import com.netty.informationServe.utils.NetUtils;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**

 *@描述 CommandLineRunner、ApplicationRunner 接口是在容器启动成功后的最后一步回调（类似开机自启动）。
 *
 * 1.先添加个退出功能，要不然确实不退出不太好，占用着资源

 */
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ChatServeApplication implements CommandLineRunner {

    private static String yamlPath = "application.yml";
    private static String yamlKey = "hostip";

    @Value("${hostip}")
    private static String hostip;
    @Autowired
    WebSocketServe serve;

    private static void init(){
        //根据网卡动态设置ip（为了将netty的ip:port维护进redis）
        initIp();
    }
    private static void initIp() {
        //优先获取jvm参数中指定的ip
        hostip = System.getProperty(yamlKey);
        if(StringUtils.isNotEmpty(hostip)){
            log.info("jvm启动参中指定ip为[{}]",hostip);
            return ;
        }else{
            String localIP = NetUtils.getLocalHost();
            System.setProperty(yamlKey,localIP);
            log.info("自动获取ip为[{}]",localIP);
        }
        log.info("若此ip不是与网关gateway通信的内网ip，请尝试通过启动参数指定[{}]","java -jar -Dhostip=x.x.x.x");
    }

    public static void main(String[] args) {
        init();
        SpringApplication.run(ChatServeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        MqMessage message = new MqMessage();
//        message.setFromId(1);
//        message.setInfoContent("465");
//        rocketMQTemplate.convertAndSend("NettyInfo",message);
//        System.out.println("消息已经发送拉...");
        ChannelFuture future = serve.run();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> serve.destroy()));
        future.channel().closeFuture().syncUninterruptibly();

    }
}
