package com.rose.yaj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.common.constant.RedisPrefix;
import com.rose.yaj.dto.QuestionAndAnswerList;
import com.rose.yaj.dto.QuestionFormSubmit;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanDataDiscovery;
import com.rose.yaj.entity.YanQuestion;
import com.rose.yaj.entity.YanQuestionTags;
import com.rose.yaj.mapper.YanQuestionMapper;
import com.rose.yaj.service.*;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rosejava
 * @since 2020-10-04
 */
@Service
public class YanQuestionServiceImpl extends ServiceImpl<YanQuestionMapper, YanQuestion> implements YanQuestionService {
    @Autowired
    YanAnswerService yanAnswerService;
    @Autowired
    YanQuestionTagService yanQuestionTagService;
    @Autowired
    YanQuestionTagsService yanQuestionTagsService;

    @Autowired
    YanUserService yanUserService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public List<YanDataDiscovery> getIndexData() {
        /**
         * 加入缓存逻辑
         */
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        if( StringUtils.isEmpty(ops.get(RedisPrefix.INDEX_CONTET))){
            //缓存中没有数据 应该从数据库查询出来
            List<YanDataDiscovery> indexDataFromDB = getIndexDataFromDB();
            ops.set(RedisPrefix.INDEX_CONTET, JSON.toJSONString(indexDataFromDB),20 ,TimeUnit.MINUTES);
            return indexDataFromDB;
        }
        String indexContentJson = ops.get(RedisPrefix.INDEX_CONTET);
        //TypeReference这个构造器是受保护的，还需要使用匿名内部类的形式来搞定
        List<YanDataDiscovery> jsonObject = JSON.parseObject(indexContentJson, new TypeReference<List<YanDataDiscovery>>(){});
        return jsonObject;
    }

    /**
     * 从数据库查询并且封装整个分类数据
     * @return
     */
    public List<YanDataDiscovery> getIndexDataFromDB() {
        /**
         * select question_id, que_content  ,que_title from yan_question order by que_collect desc
         */
        List<YanQuestion> yanQuestions = this.list(new QueryWrapper<YanQuestion>().orderByDesc("que_collect"));
        List<YanDataDiscovery> list = yanQuestions.stream()
                .filter(
                        (data)->{
                            Integer questionId = data.getQuestionId();
                            YanAnswer yanAnswer = yanAnswerService.getOne(new QueryWrapper<YanAnswer>().eq("que_id", questionId));
                            if (yanAnswer != null){
                                return true;
                            }else {
                                return false;
                            }
                        }
                )
                .map(
                (item) -> {
                    Integer questionId = item.getQuestionId();
                    YanDataDiscovery dataDiscovery = new YanDataDiscovery();
                    // 问题id
                    dataDiscovery.setQuestionId(questionId);
                    // 问题内容
                    dataDiscovery.setQuestion(item.getQueTitle());
                    if (item.getOpenid() != null){
                        dataDiscovery.setFeedSourceImg(yanUserService.getAvatarUrlByOpenid(item.getOpenid()));
                    }



                    YanAnswer yanAnswer = yanAnswerService.getOne(new QueryWrapper<YanAnswer>().eq("que_id", questionId));

                    if (yanAnswer !=null){
                        // 回答id
                        dataDiscovery.setAnswerId(yanAnswer.getAnswerId());
                        // 回答的精简内容
                        String answerCtnt = yanAnswer.getAnswerContent();
                        answerCtnt = Jsoup.parse(answerCtnt).text();
                        if (answerCtnt.length() > 25) {
                            String substring = answerCtnt.substring(0, 40).concat("...");
                            dataDiscovery.setAnswerCtntShort(substring);
                        } else {
                            dataDiscovery.setAnswerCtntShort(answerCtnt);
                        }
                    }



                    return dataDiscovery;

                }
        ).collect(Collectors.toList());

        return list;
    }
    // todo 自己的这个项目里面没有事务这环节

    /**
     * 改变两个表
     * @param openid
     * @param form
     */
    @Override
    public void saveQue(String openid, QuestionFormSubmit form) {

        YanQuestion yanQuestion = new YanQuestion();
        yanQuestion.setOpenid(openid);

        BeanUtils.copyProperties(form,yanQuestion);
        this.save(yanQuestion);
        Integer questionId = yanQuestion.getQuestionId();

        for (Integer integer : form.getQueTagIds()) {
            YanQuestionTags yanQuestionTags = new YanQuestionTags();
            yanQuestionTags.setQueId(questionId);
            yanQuestionTags.setTagId(integer);
            yanQuestionTagsService.save(yanQuestionTags);
        }


    }

    @Override
    public List<YanQuestion> getMyQue(String openid) {
        List<YanQuestion> list = this.list(new QueryWrapper<YanQuestion>().eq("openid",openid));
        return list;
    }

    @Override
    public QuestionAndAnswerList getQuestionAnswer(Integer id) {

        QuestionAndAnswerList queAnswerList = new QuestionAndAnswerList();

        YanQuestion byId = this.getById(id);

        BeanUtils.copyProperties(byId,queAnswerList);
        System.out.println(byId);
        /**
         *       这一条sql等于目前的我写的这些语句，涉及到三个表之间的连接
         *      SELECT * FROM yan_question q,(
         *          SELECT a.answer_name ,a.answer_content,a.answer_like ,avatar_url FROM yan_answer a
         *          LEFT JOIN yan_user AS u ON u.openid = a.openid
         *          WHERE que_id =1
         *      ) t
         *      WHERE q.question_id =1
         */

        QueryWrapper<YanAnswer> answerWrapper = new QueryWrapper<>();
        answerWrapper.eq("que_id",id);
        List<YanAnswer> list = yanAnswerService.list(answerWrapper); //list是数据库当中当查询出来的

        if (list==null || list.size() == 0){
            return queAnswerList;
        }else {
            List<YanAnswer> collect = list.stream().map(
                    (item) -> {
                        String openid = item.getOpenid();
                        String avatarUrl = yanUserService.getAvatarUrlByOpenid(openid);
                        item.setAvatarUrl(avatarUrl);
                        return item;
                    }
            ).collect(Collectors.toList());
            queAnswerList.setAnswerList(collect);
            return queAnswerList;
        }

    }
    //这个总归是一个联表查询了吧，我就不信了；
    //这种一个里面映射多个的是需要collection 或者assiocatin吧，这就难过了；



//    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<YanQuestion> page = this.page(
//                new Query<YanQuestion>().getPage(params),
//                new QueryWrapper<YanQuestion>()
//        );
//
//        return new PageUtils(page);
//    }
}
