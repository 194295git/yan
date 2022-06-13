package com.rose.yaj.mapper;

import com.rose.yaj.dto.QuestionFormSubmit;
import com.rose.yaj.entity.YanQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rosejava
 * @since 2020-10-04
 */
public interface YanQuestionMapper extends BaseMapper<YanQuestion> {
    List<Map<String,Object>> testMap();

    List<QuestionFormSubmit.Yan> testMap2();


}
