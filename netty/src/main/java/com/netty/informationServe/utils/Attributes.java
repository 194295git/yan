package com.netty.informationServe.utils;

import com.netty.common.domain.User;
import io.netty.util.AttributeKey;

/**
 * 设置channel的属性来判断有没有登录   ---》 channel.attr()
 * @author holiday
 * 2020-10-22
 */
public interface Attributes {
    AttributeKey<User> SESSION = AttributeKey.newInstance("session");
}
