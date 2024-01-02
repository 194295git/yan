package com.rose.common.netty;

import io.netty.util.AttributeKey;

public interface AttrConstants {

	public final static AttributeKey<String> channelId = AttributeKey.valueOf("channelId");

	public final static AttributeKey<String> activeTime = AttributeKey.valueOf("activeTime");

	public final static AttributeKey<String> sessionId = AttributeKey.valueOf("sessionId");

}
