package com.rose.yaj.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;



@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AnswerDto  implements Serializable {


    @Data
    public static class AnswerSubmit{

        private Integer queId;

        private String answerContent;
    }





}
