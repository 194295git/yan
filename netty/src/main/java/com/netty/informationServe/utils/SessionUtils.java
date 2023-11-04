package com.netty.informationServe.utils;

import com.netty.common.domain.User;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这个里面添加退出的功能
 */
@Slf4j
public class SessionUtils {
	/**
	 * userID 映射 连接channel
	 */

	public static Map<String, Channel> userOpenidChannelMap = new ConcurrentHashMap<>();
	
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
	

	public static Channel getChannel(String openid) {
		return userOpenidChannelMap.get(openid);
	}
	/**
	 * 绑定群聊Id  群聊channelGroup
	 * @param groupId
	 * @param channelGroup
	 */
	public static void bindChannelGroup(Integer groupId, ChannelGroup channelGroup) {
		groupIdChannelGroupMap.put(groupId, channelGroup);
		System.out.println(channelGroup);
	}

	public static ChannelGroup getChannelGroup(Integer groupId) {
		return groupIdChannelGroupMap.get(groupId);
	}

	public static Map<String, Channel>  getAllOnlineChannel() {
		return userOpenidChannelMap;
	}
}
