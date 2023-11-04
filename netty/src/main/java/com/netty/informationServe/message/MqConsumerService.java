//package com.netty.informationServe.message;
//
//import com.alibaba.fastjson.JSON;
//import com.huangliang.api.config.RocketMQConfig;
//import com.huangliang.api.constants.Constants;
//import com.huangliang.api.entity.WebsocketMessage;
//import com.huangliang.api.entity.request.SendRequest;
//import com.huangliang.api.util.UUIDUtils;
//import com.huangliang.cloudpushwebsocket.constants.AttrConstants;
//import com.netty.informationServe.service.ChannelService;
//import io.github.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer;
//import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
//import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTopic;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 处理MQ中的推送消息，对客户端发起推送
// */
//@Service
//@Slf4j
//public class MqConsumerService extends AbstractRocketMqConsumer<RocketMqTopic, RocketMqContent> {
//
//    @Value("${eureka.instance.instance-id}")
//    private String instanceId;
//
//    @Autowired
//    private ChannelService channelService;
//
//    @Autowired
//    private MessageSendService messageSendService;
//
//    @Override
//    public boolean consumeMsg(RocketMqContent content, MessageExt msg) {
//        try {
//            String MqMessage = new String(msg.getBody());
//            log.info("=====消费消息:"+MqMessage);
//            //消息内容
//            SendRequest request = JSON.parseObject(MqMessage,SendRequest.class);
//            if(request.getSendToAll()){
//                //遍历该服务上的所有客户端进行推送
//                for(String channelId : channelService.getAll().keySet()){
//                    messageSendService.sendMessage(channelId,getMessage(channelId,request,msg));
//                }
//                return true;
//            }
//            //根据请求标识进行推送
//            for(String channelId : request.getTo()){
//                messageSendService.sendMessage(channelId,getMessage(channelId,request,msg));
//            }
//            return true;
//        }catch (Exception e){
//            log.error("推送失败.",e);
//        }
//        return false;
//    }
//
//    //构造推送消息体
//    private WebsocketMessage getMessage(String channelId,SendRequest request,MessageExt msg) {
//        WebsocketMessage websocketMsg = new WebsocketMessage(
//                request.getRequestId(),
//                channelService.get(channelId).attr(AttrConstants.sessionId).get(),
//                UUIDUtils.getUUID(),
//                WebsocketMessage.MsgType.BUSSINESS.code,
//                new String[]{channelId},
//                request.getMsg(),
//                request.getFrom(),
//                Integer.parseInt(msg.getUserProperty(Constants.Trigger))
//                );
//        return websocketMsg;
//    }
//
//
//    /**
//     * 订阅该服务实例名的topic
//     * @return
//     */
//    @Override
//    public Map<String, Set<String>> subscribeTopicTags() {
//        Map<String, Set<String>> map = new HashMap<>();
//        map.put(RocketMQConfig.getWebsocketTopic(instanceId), null);
//        return map;
//    }
//
//    /**
//     * 消费组为服务实例名
//     * @return
//     */
//    @Override
//    public String getConsumerGroup() {
//        return RocketMQConfig.getWebsocketGroup(instanceId);
//    }
//
//}