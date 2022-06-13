package io.renren.modules.wechat.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoumin
 * @create 2018-07-11 15:33
 */
public interface MessageService {

    /**
     * 接收到微信消息的时候的请求方式
     * @param request
     * @return
     */
    String newMessageRequest(HttpServletRequest request);


    /**
     * 通过openid 和关键字实现发送消息到对应的模板
     * @param openId
     * @param keyword
     * @return
     */
    Boolean sendTemplate(String openId, String keyword);
}
