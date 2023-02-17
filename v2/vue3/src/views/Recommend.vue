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
    <s-header :name="'智能推荐'"></s-header>
    <div id="container">
      <div class="province_list">
        <div class="question-wrp">
          <div class="title font-18">请描述你对学校的期望</div>
          <!-- 可以使用 CellGroup 作为容器 -->
          <van-cell-group inset>
            <van-field
              class="placeholder"
              v-model="desc"
              label="请输入"
              placeholder="对目标院校进行描述"
            />
          </van-cell-group>

        </div>

        <div>
          <div class="submit box2" @click="getMajor">提交信息</div>
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
import { getUserInfo } from "@/service/user";
import { useRouter } from "vue-router";
export default {
  components: {
    navBar,
    sHeader,
  },
  setup() {
    const router = useRouter();
    const state = reactive({
      user: {},
      loading: true,
      desc: "",
      majorArr: [],
      major: [
        { name: "法学", id: "1" },
        { name: "会计学", id: "2" },
        { name: "金融学", id: "3" },
        { name: "工商管理", id: "4" },
        { name: "商务英语", id: "5" },
        { name: "心理学", id: "6" },
        { name: "经济学", id: "7" },
        { name: "管理科学与工程", id: "8" },
        { name: "新闻", id: "9" },
        { name: "传播", id: "10" },
        { name: "广告", id: "11" },
        { name: "计算机应用技术", id: "12" },
        { name: "临床医学", id: "13" },
        { name: "机械设计制造及其自动化", id: "14" },
      ],
      options: [],
    });

    onMounted(async () => {
      const data = await getUserInfo();
      state.user = data.content;
      state.loading = false;
    });

    const goBack = () => {
      router.go(-1);
    };

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} });
    };

    const  getMajor = ()=>{
      router.push({ path: '/majorQuestion', query: {  major_ids: '1,2,3,7,8'}})
    }

    return {
      ...toRefs(state),
      goBack,
      goTo,
      getMajor,
    };
  },
};
</script>

<style lang="less" scoped>
@import "../common/style/mixin";
@import "../common/style/vuetify";
#container {
  width: 375px;
  margin-bottom: 150px;
}

.title {
  margin: 2px 35px;
  color: grey;
}
.question-wrp {
  width: 96%;
  margin: 12px auto;
  border-radius: 12px;
  box-shadow: 0 1px 2px #bebebe;
  background: white;
  /* border: 1px solid black; */
}
.placeholder {
  text-indent: 15px;
  width: 275px;
  height: 50vh;
}

.submit {
  background-color: #9932cc;
  color: white;
  margin: 20px auto 2px;
  text-align: center;
  padding: 5px;
}
.list_row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 15px;
  padding-bottom: 25px;
  border-bottom: 1px solid #6b6a6a;
}

.list_col {
  width: 110px;
  text-align: center;
  color: #6b6a6a;
  font-size: 20px;
  position: relative;
}

.province_list {
  width: 330px;
  display: flex;
  border-radius: 10px;
  flex-direction: column;
  margin: 0 auto;
}

.row_border {
  border-width: 0px !important;
}

.list_border {
  border-right: 1px solid #6b6a6a;
}

.list_col text {
  display: block;
  position: relative;
  z-index: 3;
}

.list_shock {
  animation: list_shock 0.4s forwards;
}
</style>
