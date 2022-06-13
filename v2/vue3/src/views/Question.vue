<!--
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2020 陈尼克 all rights reserved.
 * 版权所有，侵权必究！
 *
-->

<template>
  <div class="user-box">
    <s-header :name="'问题'"></s-header>
    <div class="container">
      <div class="question-wrp">
        <div class="question-item">
          <div class="que-title">
            {{ question.queTitle }}
          </div>
          <div class="que-content">
            {{ question.queContent }}
          </div>
          <div class="que-follow">
            <div class="left">
              <div class="watch">
                <image src="../../static/images/eye.png"></image>
                <text>{{ question.quediv }}</text>
              </div>
              <div class="comment">
                <image src="../../static/images/comment2.png"></image>
                <text>27</text>
              </div>
            </div>
            <div class="right">
              关注
            </div>
          </div>
        </div>
      </div>
      <div>
        <list-scroll :scroll-data="question" class="content">
          <div class="swiper-container">
            <div
              class="answer-feed"
              v-for="(item, index) in question.answerList"
              :key="index"
              :data-idx="index"
              @click="getIndex"
            >
              <div
                @click="bindItemTap(item.answerId)"
                class="box3 feed-item d-flex"
              >
                <div class="feed-source">
                  <van-image
                    width="35px"
                    height="35px"
                    fit="cover"
                    :src="item.avatarUrl"
                  />
                </div>
                <div class="feed-content">
                  <div class="answer-body">
                    <div>
                      <!-- 富文本 -->
                      <div>
                        {{ item.answerContent }}
                      </div>
                      <!-- <rich-text :nodes="item.answerContent"></rich-text> -->
                    </div>
                    <div class="answer-actions flex-wrp">
                      <div class="like dot flex-item">
                        <a>3.9K 赞同 </a>
                      </div>
                      <div class="comments dot flex-item">
                        <a>254 评论 </a>
                      </div>
                      <div class="time flex-item">
                        <a>2 个月前</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </list-scroll>
      </div>
      <div class="bottom_1">
        <div class="invite ">
          <image
            src="../../static/images/invite.png"
            style="width:60rpx;height:60rpx;padding:auto 6rpx;margin-left:8rpx;margin-right:8rpx;"
          ></image>
          <text style="margin-bottom:24rpx;">邀请回答</text>
        </div>
        <div class="write">
          <image
            src="../../static/images/write.png"
            style="width:60rpx;height:60rpx;padding:auto 6rpx;margin-left:8rpx;margin-right:8rpx;"
          ></image>
          <text @click="writeAnswer">写回答</text>
        </div>
      </div>
    </div>

    <nav-bar></nav-bar>
  </div>
</template>

<script>
import { reactive, onMounted, toRefs } from "vue";
import navBar from "@/components/NavBar";
import sHeader from "@/components/SimpleHeader";
import { getQuestionData } from "@/service/question";
import { useRouter, useRoute } from "vue-router";
import listScroll from "@/components/ListScroll";
export default {
  components: {
    navBar,
    sHeader,
    listScroll,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();
    const state = reactive({
      userInfo: {},
      question: {},
      index: "",
      option_id: route.query.id,
      user: {},
      loading: true,
    });

    onMounted(() => {
      const { id } = route.query;

      state.option_id = id;
      getData();
    });

    const goBack = () => {
      router.go(-1);
    };

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} });
    };
    const getData = async () => {
      var questionData = await getQuestionData(state.option_id);
      var res1 = questionData.content.answerList;
      console.log(res1);
      for (var i = 0; i < res1.length; i++) {
        questionData.content.answerList[i].answerContent =
          res1[i].answerContent.substr(0, 75) + "...";
      }
      state.question = questionData.content;

      console.log(questionData.content);
    };

    return {
      ...toRefs(state),
      goBack,
      goTo,
      getData,
    };
  },
};
</script>

<style lang="less" scoped>
@import "../common/style/mixin";
@import "../common/style/vuetify";
.user-box {
  background: rgb(222, 183, 241);
  height: 100%; 
}

.answer-feed {
  padding: 0;
  font-size: 7px;
  color: #000;
}

.question-wrp {
  width: 90%;
  border-radius: 10px;
  margin: 5px auto;
  padding: 5px 1px;
  background: white;
  box-shadow: 0 1px 2px #bebebe;
}

.question-item {
  width: 90%;
  padding: 2px 10px;
  border-radius: 12.5px;
}

.question-item .que-tag {
}

.avatar {
  width: 100rpx;
  height: 100rpx;
}

.question-item .que-title {
  padding: 10px 10px;
  font-size: 15px;
  font-weight: bold;
}

.question-item .que-content {
  padding: 0 10px;
  font-size: 12px;
}

.question-item .que-follow {
  margin: 10px;
  height: 30px;
}

.question-item .que-follow view {
  display: inline-block;
  margin: 0 20px 0 0;
}

.question-item .que-follow image {
  width: 50rpx;
  height: 50rpx;
  margin: 0 10rpx 0 0;
  vertical-align: middle;
}

.question-item .que-follow .left {
  float: left;
  padding: 10rpx 0;
}

.question-item .que-follow .left text {
  color: #afafaf;
  height: 25px;
  vertical-align: middle;
  padding: 9px 0;
  font-size: 12px;
}

.question-item .que-follow .right {
  float: right;
  padding: 5px 26px;
  color: #ffffff;
  background: #9932cc;
  font-size: 13px;
  margin: 0;
}

.bottom_1 {
  position: fixed;
  bottom: 100px;
  width: 310px;
  margin-left: 30px;
  /* height: 195rpx; */
  /* background: rgb(151, 49, 202); */
}

.invite {
  border-right: solid 2px #ebebeb;
  float: left;
  width: 150px;
  margin-right: 2px;
  border-radius: 9px;
  background: rgb(151, 49, 202);
}

.write {
  border-right: solid 2px #ebebeb;
  float: right;
  width: 150px;
  margin-left: 2px;
  border-radius: 9px;
  background: rgb(151, 49, 202);
}

.image {
  width: 50rpx;
  height: 50rpx;
  vertical-align: middle;
  color: white;
  margin: 0 20rpx 0 0;
}

.content {
  /* border: 1px solid #000; */
  height: 460px;
}

.answer-feed .feed-item .feed-content .answer-body {
  padding: 0;
  /*height: 10rpx;*/
  font-size: 24rpx;
  line-height: 28rpx;
  color: #5b5b5b;
}

/* =========== */

.feed-item {
  padding: 7px 10px;
  margin: 7px auto;
  background: #ffffff;
  border-radius: 12.5px;
}

.feed-source {
  width: 80rpx;
  padding: auto 0px;
  height: 80rpx;
}

.feed-content {
  width: 520rpx;
  padding: 10rpx 0 0 20rpx;
  font-size: 28rpx;
  font-weight: 600px;
  line-height: 40rpx;
  color: gray;
}

.feed-content_subject {
  color: black;
}
</style>
