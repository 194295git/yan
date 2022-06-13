package io.renren.modules.wechat.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhoumin
 * @create 2018-07-11 15:23
 */
@Setter
@Getter
public class ViewButton extends BasicButton {
    private String type;
    private String name;
    private String url;

}
