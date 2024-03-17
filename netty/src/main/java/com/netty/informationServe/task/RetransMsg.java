//package com.netty.informationServe.task;/**
// * @author rose
// * @create 2024/2/11
// */
//
///**
// * @author rose<br>
// */
//
//import com.netty.informationServe.message.MessageSendService;
//import com.netty.informationServe.utils.SessionUtils;
//import com.rose.common.base.WebsocketMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * 重发消息
// */
//@Component
//@EnableScheduling   // 2.开启定时任务
//@Slf4j
//public class RetransMsg {
//
//    /**
//     * 推送消息的服务
//     */
//    @Autowired
//    private MessageSendService messageSendService;
//
//    @Scheduled(fixedRate=1000)
//    private void execute() {
//        /**
//         * 先获取一下map。消息发送小于三次的全部重发。
//         *
//         *  遍历一下cache 然后发送消息，TODO 还需要完善一下ackpush的删除map操作
//         */
//        Map<String, Map<String, Object>>  cache =  SessionUtils.cacheMsgidAndNumber;
//        //遍历这些消息并且重新发送
//        for (String key : cache.keySet()) {
//            //key
//            Map<String, Object> stringStringMap = cache.get(key);
//            Integer  number =  (Integer)stringStringMap.get("number");
//            if(number<3){
//                WebsocketMessage wsmsg = (WebsocketMessage)stringStringMap.get("msgid");
//                messageSendService.sendMessage(key,wsmsg);
//            }
//
//        }
////
//    }
//}
