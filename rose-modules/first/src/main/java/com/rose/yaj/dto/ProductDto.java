package com.rose.yaj.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rose
 * @create 2022/6/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductDto implements Serializable {

    private Integer categoryId;

    private Integer categoryLevel;

    private String categoryName;

    private List<SecondLevelCategory> secondLevelCategoryVOS = new ArrayList<>();

    @Data
    public static class SecondLevelCategory{
        private Integer categoryId;

        private Integer categoryLevel;

        private String categoryName;
        private Integer parentId;
    }



}
