package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.dto.AnswerDto;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.mapper.YanAnswerMapper;
import com.rose.yaj.service.YanAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rosejava
 * @since 2020-10-04
 */
@Service
public class YanAnswerServiceImpl extends ServiceImpl<YanAnswerMapper, YanAnswer> implements YanAnswerService {


    @Override
    public void changeLikeStatus(Integer i, String answerId) {
        YanAnswer answer = this.getById(answerId);
        Integer like = answer.getAnswerLike();
        if (like == null){
            like = 0;
        }
        YanAnswer answerLike = new YanAnswer();
        if (i == 1){
            answerLike.setAnswerLike(like+1);

        }else if (i == 0){
            answerLike.setAnswerLike(like-1);
        }else {
            throw new RuntimeException("传递参数有误");
        }
        this.update(answerLike,new QueryWrapper<YanAnswer>().eq("answer_id",answerId));
        /**
         *  SELECT SUM(( SELECT answer_like FROM yan.`yan_answer` WHERE answer_id = 1 ) +1) AS t
         *  这行可以将+1操作处理，然后
         */

    }

    @Override
    public void writeAnswer(String openid, AnswerDto.AnswerSubmit answerSubmit) {
        YanAnswer yanAnswer = new YanAnswer();
        yanAnswer.setOpenid(openid);
        BeanUtils.copyProperties(answerSubmit,yanAnswer);
        this.save(yanAnswer);
    }
}
