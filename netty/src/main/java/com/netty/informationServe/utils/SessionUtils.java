package com.netty.informationServe.utils;

import com.netty.common.domain.User;
import com.rose.common.base.WebsocketMessage;
import com.rose.common.constant.RedisPrefix;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所有的会话在这个里面进行管理
 * 这个里面添加退出的功能
 */
@Slf4j
@Component
public class SessionUtils {


	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * userID 映射 连接channel
	 */

	public static Map<String, Channel> userOpenidChannelMap = new ConcurrentHashMap<>();

	/**
	 *
	 */
	public static Map<String, Map<String, Object>> cacheMsgidAndNumber = new ConcurrentHashMap<>();

	/**
	 * 每次调用这个的msgid每次进行加1，然后调用三次,重复三次之后则客户端不再进行重试，改为拉取消息.
	 * @param channel
	 * @param msgid
	 */
	public static void bindMsgidAndNumber(String channel, WebsocketMessage msgid){
		//判断存不存在number键，不存在则为1，存在则加1
		if (cacheMsgidAndNumber.containsKey(channel)){
			Map<String, Object> map = cacheMsgidAndNumber.get(channel);
			map.put("number",((Integer)map.get("number"))+1);
		}else{
			//如果存在的话则使用number1
			final Map  temp = new HashMap<>();
			temp.put("number",1);
			temp.put("msgid",msgid);
			cacheMsgidAndNumber.put(channel,temp);
		}

	}
	
	/**
	 * groupId ---> channelgroup 群聊ID和群聊ChannelGroup映射
	 */
	private static Map<Integer, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();
	
	public static void bindChannel(User user, Channel channel) {
		userOpenidChannelMap.put(user.getOpenid(), channel);
		log.info("openid为【"+user.getOpenid() + "】的用户已经登陆...");
		log.info("当前channelMap为"+userOpenidChannelMap.toString());
		channel.attr(Attributes.SESSION).set(user);

	}
	public static void clearChannel(User user, Channel channel) {
		userOpenidChannelMap.remove(user.getOpenid());
		log.info("用户【"+user.getOpenid() + "】已经退出...");
		//先暂且这样子做一个退出
		//检测登录会测试这个属性，但是现在不会了
		channel.attr(Attributes.SESSION).remove();
//		channel.attr(Attributes.SESSION).set(user);
	}
	
//	public static void unbind(Channel channel) {
//		if (hasLogin(channel)) {
//			userIdChannelMap.remove(getUser(channel).getOpenid());
//			channel.attr(Attributes.SESSION).set(null);
//		}
//	}
	
	public static boolean hasLogin(Channel channel) {
		return channel.hasAttr(Attributes.SESSION);
	}
	
	public static User getUser(Channel channel) {
		return channel.attr(Attributes.SESSION).get();
	}

	/**
	 * 通过openid来进行channel的发送
	 * @param openid
	 * @return
	 */
	public static Channel getChannel(String openid) {
		return userOpenidChannelMap.get(openid);
	}

	public  Boolean isOnline(String openid) {
		String host = redisTemplate.opsForHash().get(RedisPrefix.PREFIX_CLIENT + openid,"host")+"";

		return !host.equals("null");
	}
	/**
	 * 绑定群聊Id  群聊channelGroup
	 * @param groupId
	 * @param channelGroup
	 */
	public static void bindChannelGroup(Integer groupId, ChannelGroup channelGroup) {
		groupIdChannelGroupMap.put(groupId, channelGroup);
		log.info("当前channelGroup"+channelGroup.toString());
	}

	public static ChannelGroup getChannelGroup(Integer groupId) {
		return groupIdChannelGroupMap.get(groupId);
	}

	public static Map<String, Channel>  getAllOnlineChannel() {
		return userOpenidChannelMap;
	}
}
