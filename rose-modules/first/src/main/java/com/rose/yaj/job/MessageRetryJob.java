package com.rose.yaj.job;/**
 * @author rose
 * @create 2024/4/23
 */

import com.alibaba.fastjson.JSONObject;
import com.rose.common.mqutil.SendRequest;
import com.rose.common.netty.Commond;
import com.rose.yaj.listener.AsyncSend;
import com.rose.yaj.mapper.YanUserChatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <br>
 */
@Slf4j
@Component
@EnableScheduling
public class MessageRetryJob {

    @Autowired
    AsyncSend asyncSend;

    @Autowired
    YanUserChatMapper chatMapper;
    // 定时任务方法
//    @Scheduled(cron = "0 0/5 * * * ?")  // 每5分钟执行一次

    /**
     * 2000秒走一趟吧；
     */
//    @Scheduled(fixedRate=2000)
//    public void checkAndRetryFailedMessages() {
//        // 查询本地消息表中状态为PENDING或FAILED的消息
////        List<LocalMessage> failedMessages = localMessageRepository.findByStatusIn(Arrays.asList("PENDING", "FAILED"));
////
////        for (LocalMessage message : failedMessages) {
////            // ...处理消息重试逻辑，如重新发送、更新状态等...
////        }
//        List<Map> rs = chatMapper.selectSendingAndFailing();
//        log.info("【定时任务】查询未发送条数"+rs.size());
//        //还没开发推送多条消息的接口，先暂时用一下把
//        List<SendRequest> msg = new ArrayList<SendRequest>();
//        for(Map item :rs){
//            SendRequest senddata = buildSendRequest(item);
//            msg.add(senddata);
//        }
//        //批量的发送消息.
//        asyncSend.pushMessageList(msg);
//    }


    private SendRequest buildSendRequest(Map message1) {
        SendRequest send = new SendRequest();
        JSONObject data = new JSONObject();
        data.put("type", Commond.SINGLE_MESSAGE_OTHER);
        data.put("status", 200);

        JSONObject params = new JSONObject();
        params.put("message", message1.get("content") );
        params.put("msgId", message1.get("msgid"));
        params.put("openid",message1.get("fromId"));
        params.put("ttype",message1.get("ttTpye"));
        data.put("params",params);
        List to = new ArrayList<String>();
        to.add(message1.get("toId"));
        send.setTo(to);
        send.setMsg(data);
        send.setUniqueMsgid( message1.get("msgid")+"");
        send.setSendToAll(false);
        return send;
    }
}
