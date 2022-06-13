package io.renren.modules.wechat.message;

/**
 * 消息基类（普通用户 -> 公众帐号）
 * @author wuming
 *
 */
public class BaseMessage {
	// 开发者微信号  
    private String ToUserName;  
    // 发送方帐号（一个OpenID）  
    private String FromUserName;  
    // 消息创建时间 （整型）  
    private long CreateTime;  
    // 消息类型（text/image/location/link）  
    private String MsgType;  
    // 消息id，64位整型  
    private long MsgId;
    /**
     * 位0x0001被标志时，星标刚收到的消息
     */
    private int FuncFlag;
    
    public String getToUserName() {
        return ToUserName;
    }
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
    public String getFromUserName() {
        return FromUserName;
    }
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
    public long getCreateTime() {
        return CreateTime;
    }
    public void setCreateTime(long l) {
        CreateTime = l;
    }
    public String getMsgType() {
        return MsgType;
    }
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
   
    public long getMsgId() {
        return MsgId;
    }
    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}
