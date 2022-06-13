package com.rose.yaj.dto;

import com.rose.yaj.entity.YanAnswer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionAndAnswerList  implements Serializable {
    private Integer questionId;

    private String queTitle;

    private String queContent;

    private String queTag;

    private Integer queView;

    private List<YanAnswer> answerList = new ArrayList<>();

}
