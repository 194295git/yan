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
    <s-header :name="'问题测试'"></s-header>
    <div v-if="cur === 0">
      <div class="box2 font-bold-16 font-light-purple">
        请回答相关的问题
      </div>

      <div class="box2" v-for="(item, index) in question" :key="index">
        <div class="question">{{ index + 1 + " " + item }}</div>

        <div v-show="type[index] == 0">
          <!-- @click="chan1(index)" -->
          <van-radio-group v-model="checked" @change="chan($event, index)">
            <van-radio
              :checked="select == activeRadio"
              class="radio"
              :value="select"
              >{{ select }}</van-radio
            >
          </van-radio-group>
        </div>
        <div v-show="type[index] == 1">
          
          <!-- <radio-group@change="chan($event,index)">
						<label v-for="select in radioGroup2" :key="select">
							<radio class="radio" :value="select" :checked="select==activeRadio" />{{select}}
						</label>
					</radio-group> -->
        </div>
      </div>

      <div class="submit box2" @click="compute">计算得分</div>
    </div>
    <div v-if="cur === 1">
      <div v-for="(item, index) in result" :key="index">
        {{ result[item + 1].name }}
      </div>
    </div>

    <nav-bar></nav-bar>
  </div>
</template>

<script>
import { reactive, onMounted, toRefs } from "vue";
import navBar from "@/components/NavBar";
import sHeader from "@/components/SimpleHeader";
import { getMajorData, getMajorMe } from "@/service/majorQuestion";
import { useRouter, useRoute } from "vue-router";
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
      activeRadio: [],
      radioGroup: ["是", "否"],
      radioGroup2: ["优秀", "良好", "一般"],
      option_id: "",
      question_and_type: {
        question: "",
        type: "",
      },
      question: "",
      type: "",
      counts: [],
      counts_sorted: [],
      result: [],
      yanMajorMe: [],
      cur: 0,
    });

    onMounted(async () => {
      const { major_ids } = route.query;
      state.option_id = major_ids;
      console.log(major_ids);
      getData();
      getYanMajorMe();
    });

    const goBack = () => {
      router.go(-1);
    };

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} });
    };
    const getData = async () => {
      const data = await getMajorData(state.option_id);
      const res1 = data.content;
      state.question = res1.question;
      state.type = res1.type;
    };
    const getYanMajorMe = async () => {
      const data2 = await getMajorMe();
      state.yanMajorMe = data2.content;
    };

    return {
      ...toRefs(state),
      goBack,
      goTo,
      getData,
      getYanMajorMe,
    };
  },
};
</script>

<style lang="less" scoped>
@import "../common/style/mixin";
@import "../common/style/vuetify";
.radio {
		transform: scale(0.7);
		display: inline-block;
		border-radius: 2px;
		margin-right: 5px;
		position: relative;

	}

	.question {
		background: #ffffff;
		padding: 25rpx 70rpx 25rpx 0;
		border-bottom: solid 1px #ebebeb;
		/* border: 1px solid black; */
		border-radius: 18rpx;
		margin: 5rpx auto;
		text-indent: 8rpx;
	}

	.submit {

		background-color: #9932CC;
		margin: 40rpx auto 4rpx;
		text-align: center;
		padding: 20rpx 10rpx;

	}
</style>
