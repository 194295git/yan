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
    <s-header :name="'专业'"></s-header>
    <div class="container">
      <div class="son_title">
        <div class="title_name">专业名</div>
        <div class="title_code">
          <i class="fa fa-star-o fa-lg"></i>
          <text>专业代码</text>
        </div>
      </div>
      <div class="son_list">
        <div
          class="major_son"
          v-for="(item, index) in major"
          @click="major_watch"
          :key="index"
        >
          <div class="son_name">{{ item.name }}</div>
          <div class="son_code">
            <text>{{ item.code }}</text>
          </div>
          <i class="fa fa-chevron-right fa-lg"></i>
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
import { searchMajor } from "@/service/majorWatchSon";
import { useRouter,useRoute } from "vue-router";
export default {
  components: {
    navBar,
    sHeader,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();
    const state = reactive({
      user: {},
      loading: true,
      code:'',	 
      major: []
    });

    onMounted(async () => {
      const { code } = route.query;

      state.code = code;
      const data = await searchMajor(code);
      state.major = data.content
      
    });

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
.container{
  width: 375px;
}

.son_title{
  width: 375ppx;
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 15px;
}

.title_name{
  color: #3C989E;
  padding-left: 10rpx;
  display: flex;
  align-items: center;
  border-left: 3px solid #3C989E;
  font-weight: 800
}


.title_code{
  color: #88CBCF;
  font-weight: 800;
}

.title_code i{
  margin-right: 10px;
}

.major_son{
  display: flex;
  width: 350px;
  padding: 15px 10px;
  background: #F4EFEF;
  margin-top: 10px;
  position: relative;
  align-items: center;
}

.son_name{
  color: #909290;
  margin-left: 25px;
  font-size: 17px;
  font-weight: 800
}

.fa-chevron-right{
  position: absolute;
  right: 20px;
  color: #55C4B8
}

.son_code{
  position: absolute;
  right: 75px;
  color: #525151;
  font-size: 15px;
}
</style>
