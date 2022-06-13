package io.renren.modules.wechat.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhoumin
 * @create 2018-07-11 10:10
 */
@Setter
@Getter
public class NewsMessage extends BaseMessage{
    /**
     * 图文消息个数，限制为10条以内
     */
    private Integer ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<Article> Articles;

}
