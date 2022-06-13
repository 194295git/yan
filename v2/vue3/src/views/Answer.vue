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
    <s-header :name="'回答'"></s-header>
    <div class="container">
      <div class="xian"></div>
      <div class="question box" @click="bindQueTap">
        <div class="question-title">{{ que }}</div>

        <div class="que-follow">
          <div class="watch">
            <image src="../../static/images/eye.png"></image>
            <div>300</div>
          </div>
          <div class="comment">
            <image src="../../static/images/comment2.png"></image>
            <div>27</div>
          </div>

          <div class="follow">
            <div>十 关注</div>
          </div>
        </div>
      </div>
				<!-- <div>
					{{answer}}
				</div> -->
      <div  class="scroll">
        <div>
          <div class="answerer-wrp box">
            <div class="bg-half">
              <div class="answerer d-flex">
                <div class="avatar flex-item">
                  <image :src="answer.avatarUrl"></image>
                </div>
                <div class="answerer-info">
                  <div class="answerer-name">{{ answer.answerName }}</div>
                  <div class="answerer-des">{{ answer.answerDes }}</div>
                </div>
              </div>
              <div class="answer-content">
                <image
                  src="../../static/images/1444983318907-_DSC1826.jpg"
                  style="height: 200rpx;"
                />
                <div class="answerer-cont">{{ answer.answerContent }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="box">
        <div v-for="(item, index) in commentList" :key="index">
          <div class="box2 d-flex commentBox">
            <van-image
              width="35px"
              height="35px"
              fit="cover"
              :src="item.avatarUrl"
            />

            <div class="box2 ml-4">
              <div class="box">
                {{ item.comment }}
              </div>
              <div class="ml-2" v-if="item.level2.length != 0">
                <div
                  class="d-flex"
                  v-for="(item2, index2) in item.level2"
                  :key="index2"
                >
                  <van-image
                    width="35px"
                    height="35px"
                    fit="cover"
                    :src="item2.avatarUrl"
                  />

                  <div class="ml-3">
                    {{ item2.comment }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div style="width: 88vw;margin: 0 auto;" class="">
          <!-- 可以使用 CellGroup 作为容器 -->
          <van-cell-group inset>
            <van-field
              v-model="value"
              label="评论"
              placeholder="欢迎留下美丽的评论"
            />
          </van-cell-group>
        </div>
      </div>

      <div class="answer-footer flex-wrp">
        <div class="operation-wrp flex-item">
          <div class="operation flex-wrp flex-tab">
            <div class="operation-btn d-flex" @click="like()">
              <image src="../../static/images/heart2.png"></image>
              <div class="font-white">{{ answer.answerLike }}</div>
            </div>
            <div class="operation-btn d-flex">
              <image src="../../static/images/star2.png"></image>
              <div>收藏</div>
            </div>
            <div class="operation-btn d-flex" @click="commment()">
              <image src="../../static/images/comment.png"></image>
            </div>
          </div>
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
import { getAnswer, getComment } from "@/service/answer";
import { useRouter, useRoute } from "vue-router";
import { useStore } from "vuex";

export default {
  components: {
    navBar,
    sHeader,

  },
  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const state = reactive({
      userInfo: {},
      answer: {},
      option_id: "",
      html: "",
      que: "",

      show: true,
      commentList: [],
      value: "",
      type: "div",
      border: true,
    });

    onMounted(async () => {
      const { id } = route.query;
      state.option_id = id;
      const que = store.state.que;
      state.que = que;

      await getAnswerData();
			console.log(state.answer)
      await getCommentData();
    });
    const getAnswerData = async () => {
      const data = await getAnswer(state.option_id);
			console.log(data.content)
      state.answer = data.content;
    };
    const getCommentData = async () => {
      const data2 = await getComment(state.option_id);
      state.answer = data2.content;
      state.commentList = data2.content;
    };

    const goBack = () => {
      router.go(-1);
    };

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} });
    };

    return {
      ...toRefs(state),
      goBack,
      goTo,
    };
  },
};
</script>

<style lang="less" scoped>
@import "../common/style/mixin";
@import "../common/style/vuetify";
.container {
  font-size: 14rpx;
  /* height: 1200rpx; */
  background: rgb(221, 175, 248);
  color: #000;
  /* border: 1px solid #000; */
}

.xian {
  width: 105px;
  height: 1.5px;

  border: 1px solid grey;
  margin: 9px auto 2px;
}

// rich-div {
//   width: 670rpx;
//   display: block;
//   font-size: 28rpx;
//   color: 353535;
//   letter-spacing: 1rpx;
// }

.question {
  padding: 20rpx 50rpx;
  background: white;

  font-size: 19px;

  border-radius: 12px;
  margin: 7px auto;
}

.answerer-wrp {
  /* min-height: 400px; */
  border-radius: 12px;
  margin: 2px auto;
  padding-bottom: 5px;
}

.answerer-wrp .bg-half {
  background: white;
  border-radius: 12px;
}

.answerer {
  position: relative;
  margin: 0 auto;
  width: 300px;
  height: 60px;
  padding: 15px;
  border-radius: 3px;
}

.answerer .avatar {
  flex: 1;
  width: 45px;
  height: 45px;
}

.answerer .avatar image {
  display: inline-block;
  width: 45px;
  height: 45px;
  border-radius: 45px;
}

.answerer .answerer-info {
  flex: 5;
  div-align: left;
  padding: 10rpx 20rpx;
  line-height: 38rpx;
}

.answerer .answerer-info div {
  display: block;
}

.answerer .answerer-info .answerer-name {
  font-size: 32rpx;
}

.answerer .answerer-info .answerer-des {
  font-size: 11px;
  color: #808080;
  line-height: 14px;
}

.answerer-cont {
  width: 95%;
  margin: 1.5px auto;
}

.question .follow {
  text-align: right;
  border-radius: 10%;
  padding: 5rpx 8rpx;
  font-size: 22rpx;
  background: rgb(162, 114, 206);
}

.que-follow {
  /* border: 1px solid #000; */
  padding: 2rpx 10rpx;
  display: flex;
  justify-content: space-between;
}

.que-follow image {
  width: 50rpx;
  height: 50rpx;
  margin: 0 10rpx 0 0;
  vertical-align: middle;
}

.answerer .follow div {
  display: inline-block;
  padding: 15rpx 20rpx;
  color: #40bcd0;
  border: solid 2px #40bcd0;
  border-radius: 3px;
}

.answer-content {
  padding: 30rpx 40rpx;
  margin: 4rpx auto;
}

.answer-content div {
  font-size: 32rpx;
  color: #454545;
  line-height: 44rpx;
}

.answer-content image {
  width: 100%;
  margin: 20rpx 0;
}

.answer-footer {
  position: fixed;
  bottom: 80px;
  height: 35px;
  width: 300px;
  padding: 5px 20px 2px;
  /* border: 1px solid black; */
}

.answer-footer .good {
  flex: 1;
  /*display: inline-block;*/
  height: 20px;
  border: solid 1px #d1d1d1;
  border-radius: 3px;
  padding: 5px 4px;
  margin: 5px 0;
}

.answer-footer .good .good-bad {
  display: inline-block;
}

.answer-footer .good image {
  display: inline-block;
  width: 19px;
  height: 19px;
  vertical-align: middle;
}

.answer-footer .good .good-num {
  display: inline-block;
  padding: 5px 2px;
  /*font-size: 24rpx;*/
}

.answer-footer .operation-wrp {
  flex: 5;
}

.answer-footer .operation {
  justify-content: space-between;
  /* padding: 0 0 0 60rpx; */
  /* border: 1px solid black; */
}

.answer-footer .operation-btn {
  flex: 1;
  div-align: center;
  /* border: 1px solid #000; */
  background: rgb(197, 154, 238);
  border-radius: 9px;
  margin: 0px 5px;
}

.answer-footer .operation image {
  display: block;
  margin: 0 auto;
  width: 50rpx;
  height: 50rpx;
}

.answer-footer .operation-btn div {
  display: block;
  font-size: 7px;
  color: white;
}

.scroll {
  height: 35vh;
}

.commentBox {
  width: 88vw;
  border: 1px solid white;
  border-radius: 2px;
  padding: 4px 2px;
}
.user-box {
  background: rgb(222, 183, 241);
  height: 100%;
}
</style>
