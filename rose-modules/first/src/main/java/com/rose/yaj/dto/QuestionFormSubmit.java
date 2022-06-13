package com.rose.yaj.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionFormSubmit  implements Serializable {

    //标题
    private String queTitle;
    //附标题
    private String queContent;
    //标签
    private List<Integer> queTagIds;

    // 图片
    private String feedSourceImg;

    @Data
    public static class Yan{
        private String queTitle;
        private String queContent;
        private String avatarUrl;
        private String growth;
    }

}
