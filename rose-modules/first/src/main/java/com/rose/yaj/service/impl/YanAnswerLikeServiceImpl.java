package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.entity.YanAnswerComment;
import com.rose.yaj.entity.YanAnswerLike;
import com.rose.yaj.mapper.YanAnswerCommentMapper;
import com.rose.yaj.mapper.YanAnswerLikeMapper;
import com.rose.yaj.service.YanAnswerCommentService;
import com.rose.yaj.service.YanAnswerLikeService;
import com.rose.yaj.service.YanAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YanAnswerLikeServiceImpl   extends ServiceImpl<YanAnswerLikeMapper, YanAnswerLike> implements YanAnswerLikeService {

    @Autowired
    YanAnswerService yanAnswerService;

    @Override
    public void changeAnswerLikeStatus(String openid, String id) {
        YanAnswerLike like = this.getOne(new QueryWrapper<YanAnswerLike>().eq("like_user_id", openid));

        YanAnswerLike yanAnswerLike = new YanAnswerLike();
        yanAnswerLike.setLikeUserId(openid);
        yanAnswerLike.setLikeAnswerId(id);
        if (like  != null) {
            // 0 代表false 1 代表true
            if (like.getStatus() == 1){
                yanAnswerLike.setStatus(0);
                yanAnswerService.changeLikeStatus(1,id);
            }else {
                yanAnswerLike.setStatus(1);
                yanAnswerService.changeLikeStatus(0,id);
            }
            this.update(yanAnswerLike,new QueryWrapper<YanAnswerLike>().eq("like_user_id",openid));
        }else {
            yanAnswerLike.setStatus(1);
            this.save(yanAnswerLike);
            yanAnswerService.changeLikeStatus(1,id);

        }


    }
}
