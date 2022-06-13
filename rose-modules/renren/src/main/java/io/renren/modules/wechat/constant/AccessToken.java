package io.renren.modules.wechat.constant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 凭证
 * @author zhoumin
 * @create 2018-07-11 15:22
 */
@ToString
@Setter
@Getter
public class AccessToken {
	/**
	 *  获取到的凭证
	 */
	private String accessToken;
	
	/**
	 *  凭证有效时间，单位：秒
	 */
	private int expiresIn;
}
