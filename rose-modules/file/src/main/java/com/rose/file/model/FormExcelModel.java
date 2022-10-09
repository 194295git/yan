package com.rose.file.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author rose
 * @create 2022/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormExcelModel  extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "问题", index = 0)
    private String question;

    //选项中间以、隔开 比如[国有企业、医疗企业、三资企业、其他]
    @ExcelProperty(value = "选项", index = 1)
    private String option;

    // 0-单选 1 -多选  2-开放性问题 3-上传佐证资料
    @ExcelProperty(value = "类别", index = 2)
    private Integer type;

}
