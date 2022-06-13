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
  <div class="defineBox">
    <div class="top-tab flex-wrp flex-tab">
      <div
        class="toptab flex-item active"
        v-for="(item, index) in navTab"
        :key="index"
        @click="switchTab"
        :data-index="index"
      >
        {{ item }}
      </div>
    </div>
    <div class="container">
      <div v-show="currentNavtab == 1 ? '' : true">
        <div class="index_greet">
          <div class="front">讨论</div>
          <div id="hover" class="left left_auto" @click="question_submit">
            发起回答
          </div>
          <div class="right left_auto" @click="course">寻找课程</div>
          <div class="notify-item flex-wrp">
            <div class="avatar flex-item1">
              <image src="/static/images/icon1.jpeg"></image>
            </div>
            <div class="notify-content flex-item1">
              <text class="notify-source">Rebecca 回答了问题</text>
              <text class="notify-title"
                >#考研择校#我三本学生可以考上贵校的会计专硕吗？</text
              >
            </div>
          </div>

          <div class="notify-item flex-wrp">
            <div class="avatar flex-item1">
              <image src="../static/images/icon1.jpeg"></image>
            </div>
            <div class="notify-content flex-item1">
              <text class="notify-source">Rebecca 回答了问题</text>
              <text class="notify-title"
                >#考研择校#我三本学生可以考上贵校的会计专硕吗？</text
              >
            </div>
          </div>
        </div>
        <div class="index_greet">
          <div class="front">资讯</div>
          <div class="notify-item flex-wrp">
            <div class="avatar flex-item1">
              <image src="/static/images/icon1.jpeg"></image>
            </div>
            <div class="notify-content flex-item1">
              <text class="notify-source">Rebecca 回答了问题</text>
              <text class="notify-title"
                >#考研择校#我三本学生可以考上贵校的会计专硕吗？</text
              >
            </div>
          </div>

          <div class="notify-item flex-wrp">
            <div class="avatar flex-item1">
              <image src="/static/images/icon1.jpeg"></image>
            </div>
            <div class="notify-content flex-item1">
              <text class="notify-source">Rebecca 回答了问题</text>
              <text class="notify-title"
                >#考研择校#我三本学生可以考上贵校的会计专硕吗？</text
              >
            </div>
          </div>
        </div>
      </div>

      <div class="ctnt2 " v-show="currentNavtab == 0 ? '' : true">
        <div class="unread">
          <text>3 条未读</text>
          <image src="../../static/images/allread.png"></image>
        </div>
        <div class="notify-item flex-wrp">
          <div class="avatar flex-item1">
            <image src="../../static/images/icon1.jpeg"></image>
          </div>
          <div class="notify-content flex-item1">
            <text class="notify-source">Rebecca 回答了问题</text>
            <text class="notify-title"
              >#考研择校#我三本学生可以考上贵校的会计专硕吗？</text
            >
          </div>
        </div>
        <div class="notify-item flex-wrp">
          <div class="avatar flex-item1">
            <image src="../../static/images/icon1.jpeg"></image>
          </div>
          <div class="notify-content flex-item1">
            <text class="notify-source">Rebecca 回答了问题</text>
            <text class="notify-title">有哪些考研需要避开的坑？</text>
          </div>
        </div>
        <div class="notify-item flex-wrp">
          <div class="avatar flex-item1">
            <image src="../../static/images/icon1.jpeg"></image>
          </div>
          <div class="notify-content flex-item1">
            <text class="notify-source">Rebecca 回答了问题</text>
            <text class="notify-title">考研冲刺状态如何调整心态</text>
          </div>
        </div>
        <div class="notify-item flex-wrp">
          <div class="avatar flex-item1">
            <image src="../../static/images/icon1.jpeg"></image>
          </div>
          <div class="notify-content flex-item1">
            <text class="notify-source">Rebecca 回答了问题</text>
            <text class="notify-title">大学到底该考研还是该就业？</text>
          </div>
        </div>
        <div class="notify-item flex-wrp">
          <div class="avatar flex-item1">
            <image src="../../static/images/icon1.jpeg"></image>
          </div>
          <div class="notify-content flex-item1">
            <text class="notify-source">Rebecca 回答了问题</text>
            <text class="notify-title">考研买个平板会方便些吗？</text>
          </div>
        </div>
        <div class="notify-item flex-wrp">
          <div class="avatar flex-item1">
            <image src="../../static/images/icon1.jpeg"></image>
          </div>
          <div class="notify-content flex-item1">
            <text class="notify-source">Rebecca 回答了问题</text>
            <text class="notify-title">考研失败什么感觉？</text>
          </div>
        </div>
        <div class="notify-item flex-wrp">
          <div class="avatar flex-item1">
            <image src="../../static/images/icon1.jpeg"></image>
          </div>
          <div class="notify-content flex-item1">
            <text class="notify-source">Rebecca 回答了问题</text>
            <text class="notify-title"
              >C#如何在不覆盖原有文件的情况下直接修改某一部分的内容？</text
            >
          </div>
        </div>
      </div>
      <nav-bar />
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue";

import navBar from "@/components/NavBar";
export default {
  components: {
    
    // swiper,
    navBar,
  },
  setup() {
    const state = reactive({
      navTab: ["圈层", "收藏"],
      currentNavtab: "0",
      imgUrls: [
        "/static/images/24213.jpg",
        "/static/images/24280.jpg",
        "/static/images/1444983318907-_DSC1826.jpg",
      ],
      indicatorDots: false,
      autoplay: true,
      interval: 5000,
      duration: 1000,
      feed: [],
      index: "",
      feed_length: 0,
    });

  
    return {
      ...toRefs(state),
   
    };
  },
};
</script>

<style lang="less">
.defineBox{
  height: 100%;
  background: rgb(225, 189, 243);
}
.container {
  
}

.top-tab {
  background: rgb(225, 189, 243);
  color: white;
  font-size: 14px;
  /* line-height: 100rpx; */
  margin: 0 0 4px 0;
  z-index: 9999;
}

.flex-item {
  flex-grow: 1;
  text-align: center;
  border-radius: 10px;
  background: rgb(225, 189, 243);

  /* border: 1px solid black; */
  width: 90px;
  margin: 4px;
}

.flex-item1 {
  flex-grow: 1;
  text-align: center;
}

.toptab.active {
  background: rgb(163, 84, 202);
}

.activity {
  width: 375px;
  height: 132rpx;
}

.activity image {
  width: 375px;
  height: 132rpx;
}

.add {
  padding: 10px 30px;
  background: #c9c5c9;
  border-radius: 3px;
}

/* ============notify========== */
.unread {
  /* color: #9A9C9B; */
  height: 5px;
  padding: 5px 20px;
  text-align: left;
  width: 335px;
}

.unread text {
  display: inline-block;
  line-height: 30px;
  font-size: 11px;
}

.unread image {
  display: inline-block;
  width: 25px;
  height: 25px;
  vertical-align: middle;
  float: right;
}

.notify-item {
  width: 310px;
  background: #ffffff;
  padding: 12px 35px 12px 0;
  border-bottom: solid 1px #ebebeb;
  /* border: 1px solid black; */
  border-radius: 9px;
  margin: 3px auto;
}

.notify-item image {
  width: 40px;
  height: 40px;
  border-radius: 40px;
}

.notify-item .avatar {
  flex: 1;
}

.notify-item .notify-content {
  flex: 4;
  text-align: left;
}

.notify-item .notify-content text {
  display: block;
}

.notify-item .notify-content .notify-source {
  color: #818a8f;
  font-size: 14px;
  padding: 0 0 8rpx 0;
}

.notify-item .notify-content .notify-title {
  color: #afafaf;
  font-size: 15px;
  line-height: 17px;
}

.placehold {
  padding: 10px 0;
  font-size: 14px;
}

.index_greet {
  margin-left: 10px;
  margin-top: 10px;
  width: 350px;
  /* height: 250rpx; */
  border-radius: 19px;
  background-color: #fff;
  /* border: 1px solid #f4cda5; */
  /* box-shadow: 0rpx 20rpx 50rpx #c9c5c9; */
}

.index_greet .front {
  height: 30px;
  margin: 3px auto;
  padding: 3px auto;
  /* border: 1px solid #818A8F; */
  font-size: 19px;
  text-align: center;
}

.left {
  float: left;
  font-size: 17px;
  width: 150px;
  margin-left: 5px;
  /* border: 1px solid #818A8F; */
}
.flex-wrp{
  display: flex;
}
.flex-tab{
  flex-flow: row nowrap;
  justify-content: space-around;
  align-items: stretch;
}
.flex-item{
  flex-grow: 1;
  text-align: center;
}
</style>
