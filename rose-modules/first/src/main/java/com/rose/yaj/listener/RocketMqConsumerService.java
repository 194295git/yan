package com.rose.yaj.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rose.common.base.WebsocketMessage;
import com.rose.common.constant.NettyConstants;
import com.rose.common.constant.RedisPrefix;
import com.rose.common.mqutil.MqMessage;
import com.rose.common.mqutil.SendRequest;
import com.rose.common.netty.Commond;
import com.rose.common.utils.UUIDUtils;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.feign.NettyMqFeign;
import com.rose.yaj.service.YanUserChatService;
import io.github.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer;
import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 处理MQ中的推送消息，对客户端发起推送
 */
@Service
@Slf4j
public class RocketMqConsumerService extends AbstractRocketMqConsumer<RocketMqTopic, RocketMqContent> {

    @Autowired
    YanUserChatService yanUserChatService;

    @Autowired
    NettyMqFeign nettyMqFeign;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 消费rocketmq的消息，通过channel管道来完成消息的推送
     * 使用返回值 ConsumeStatus的情况下可以确定是不是    ConsumeStatus.CONSUME_SUCCESS;
     * ConsumeStatus作为consumeMsg方法的返回值类型可以提供更好的信息反馈和异常处理，同时可读性也更好。
     * @param content
     * @param msg
     * @return
     */
    @Override
    public boolean consumeMsg(RocketMqContent content, MessageExt msg) {
        String mqmsg = new String(msg.getBody());
        log.info("RocketMqConsumerService=====消费消息:"+mqmsg);
        //消息内容

        MqMessage message1 = JSON.parseObject(mqmsg, MqMessage.class);
        try {


            ChatDto chatDto = new ChatDto();
            chatDto.setContent(message1.getInfoContent());
            chatDto.setToOpenid(message1.getToId());
            chatDto.setGroup(message1.getState());

            SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
            Boolean member = opsForSet.isMember(RedisPrefix.LEAF_PERFIX, message1.getMsgid());
            if(member){
                Long remove = opsForSet.remove(RedisPrefix.LEAF_PERFIX, message1.getMsgid());//删除元素

                //使用lua 表达式 防止同时多个请求进来；
//            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//            Long res = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(key), orderToken);
                if (message1.getState() !=null){
                    if(message1.getType().equals("onLine")){
                        yanUserChatService.saveChat(message1.getFromId(),chatDto,1);
                    }else {
                        yanUserChatService.saveChat(message1.getFromId(),chatDto,0);
                    }
                }
            }
            //在线离是需要ack一下的； 构造一下请求，然后开始发送
            // 前端目前不是特别篇需要处理离线消息的ack 是百里离线消息需要再发一次
            SendRequest send = new SendRequest();
            JSONObject data = new JSONObject();
            data.put("type", Commond.SINGLE_MESSAGE_ACK_FIRST);
            data.put("status", 200);
            JSONObject params = new JSONObject();
            params.put("message", message1.getInfoContent());
//            params.put("fileType", fileType);
            List to = new ArrayList<String>();
            to.add(message1.getFromId());
            send.setTo(to);
            send.setMsg(data);
            send.setUniqueMsgid(message1.getMsgid());
            send.setSendToAll(false);
            nettyMqFeign.send(send);

            return true;
        }catch (Exception e){
            //失败的话需要把redis的这个消息还回去.
            SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
            Long add = opsForSet.add(RedisPrefix.LEAF_PERFIX,  message1.getMsgid());//往集合添加元素
            log.error("consumeMsg 消费mq消息失败.",e);
        }
        return false;
    }

    //构造推送消息体
    private WebsocketMessage getMessage(String channelId, SendRequest request, MessageExt msg) {
        WebsocketMessage websocketMsg = new WebsocketMessage(
                request.getRequestId(),
                "savedb",
                UUIDUtils.getUUID(),
                WebsocketMessage.MsgType.BUSSINESS.code,
                new String[]{channelId},
                request.getMsg(),
                request.getFrom(),
                Integer.parseInt(msg.getUserProperty(NettyConstants.Trigger))
                );
        return websocketMsg;
    }


    /**
     * 订阅该服务实例名的topic
     * @return
     */
    @Override
    public Map<String, Set<String>> subscribeTopicTags() {
        Map<String, Set<String>> map = new HashMap<>();
//        String instanceId =   nettyutil.getInstance();

        map.put(NettyConstants.ROCKETMQ_TPOIC_SAVECHAT, null);
        return map;
    }

    /**
     * 消费组为服务实例名
     * @return
     */
    @Override
    public String getConsumerGroup() {
        return "consumeNetty";
//        String instanceId =   nettyutil.getInstance();
//        return RocketMQConfig.getWebsocketGroup(instanceId);
    }

}