package com.netty.informationServe.utils;

import com.netty.common.domain.User;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
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
		System.out.println(user.getOpenid() + "已经登陆...");
		System.out.println(userOpenidChannelMap);
		channel.attr(Attributes.SESSION).set(user);
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
