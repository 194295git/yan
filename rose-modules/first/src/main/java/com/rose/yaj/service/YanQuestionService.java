package com.rose.yaj.service;

import com.rose.yaj.dto.QuestionAndAnswerList;
import com.rose.yaj.dto.QuestionFormSubmit;
import com.rose.yaj.entity.YanDataDiscovery;
import com.rose.yaj.entity.YanQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rosejava
 * @since 2020-10-04
 */
public interface YanQuestionService extends IService<YanQuestion> {

    List<YanDataDiscovery> getIndexData();

    void saveQue(String openid, QuestionFormSubmit form);

    List<YanQuestion> getMyQue(String openid);

    QuestionAndAnswerList getQuestionAnswer(Integer id);
}
