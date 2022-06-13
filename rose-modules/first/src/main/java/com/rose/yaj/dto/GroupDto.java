package com.rose.yaj.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author rose
 * @create 2022/6/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GroupDto {

    @Data
    public static class GroupMember{
        private String groupId;
        //计划以逗号分割开然后后端去取
        //这个地方存储的是openid
        private List<String> memberIds;
        /**
         * {
         *     "groupId": 1,
         *     "memberIds":[
         *         "1",
         *         "2"
         *     ]
         * }
         */

    }
}
