package com.rose.yaj.service;

import com.rose.yaj.dto.AnswerDto;
import com.rose.yaj.entity.YanAnswer;
import com.baomidou.mybatisplus.extension.service.IService;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rosejava
 * @since 2020-10-04
 */
public interface YanAnswerService extends IService<YanAnswer> {
    //传入1 增加一个like数量 0 减少一个
    void changeLikeStatus(Integer i, String answerId);

    void writeAnswer(String openid, AnswerDto.AnswerSubmit answerSubmit);
}
