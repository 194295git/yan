package io.renren.modules.wechat.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * 普通按钮
 *
 * @author zhoumin
 * @create 2018-07-12 9:56
 */
@Setter
@Getter
public class CommonButton extends BasicButton {
    private String type;

    private String key;
}
