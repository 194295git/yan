<template>
  <div>
    <div class="container">
      <!-- 顶部 -->
      <div id="top">
        <div class="top">
          <div class="d-flex align-center">
            <div class="title font-18" style="margin:0 auto ">企聊聊</div>
            <div v-show="showLogin">
              <router-link class="title" to="./login"
                >登录</router-link
              >
              <!-- <div class="title" style="margin-right:4px">登录</div> -->
            </div>
          </div>
          <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>1</van-swipe-item>
            <van-swipe-item>2</van-swipe-item>
            <van-swipe-item>3</van-swipe-item>
            <van-swipe-item>4</van-swipe-item>
          </van-swipe>
          <!-- <van-grid :gutter="10" :row-num="1">
            <van-grid-item v-for="i in 4" :key="i" icon="photo" text="标题" />
          </van-grid> -->
          <!-- <div class="top_left">
            <div class="top_left_1">
             {{ now_year }}/{{ now_month }}/{{ now_date }}
              <div>我的待办</div>
               <div>   我的已办</div>
            </div>
          <div class="top_left_font">倒计时</div>
          </div>
          -->
        </div>

        <div class="index_navigation">
          <div class="navigation_son navigation_son_1" @click="open_01">
            <div class="navigation_son_word">商旅系统</div>
          </div>
          <div
            class="navigation_son navigation_son_2 "
            @click="open_02"
            bindtransitionend="navigation_shadow_close"
          >
            <div class="navigation_son_word navigation_son_word_2">HR系统</div>
          </div>
          <div class="navigation_son navigation_son_2" @click="open_03">
            <div class="navigation_son_word navigation_son_word_2">
              报销系统
            </div>
          </div>
        </div>
        <!-- <div class="input_around" bindtap="test">
          <input type="div" class="input" placeholder="目标专业院校" />
        </div> -->
      </div>
      <!-- mid 三大模块入口 -->

      <!-- 底部 -->

      <div class="scroll-bottom">
        <div class="bottom">
          <div class="word d-flex justify-space-between">
            <div class="pic_word1">资讯</div>
            <div class="pic_word2">更多</div>
          </div>
          <div class="search-content">
            <list-scroll :scroll-data="feed">
              <div class="swiper-container">
                <div
                  class="box2 notify-item"
                  v-for="(item, index) in feed"
                  :key="index"
                  @click="getIndex(index)"
                >
                  <div class="d-flex">
                    <div>
                      <van-image
                        width="35px"
                        height="35px"
                        fit="cover"
                        :src="item.feedSourceImg"
                      />
                    </div>
                    <div>
                      <div @click="bindQueTap">
                        <div>{{ item.question }}</div>
                      </div>
                      <div class="">
                        <div @click="bindItemTap">
                          <div class="">{{ item.answerCtntShort }}</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </list-scroll>
          </div>
        </div>
      </div>

      <nav-bar />
    </div>
  </div>
</template>

<script>
import { reactive, onMounted, toRefs, nextTick } from "vue";
import { useRouter } from "vue-router";
import listScroll from "@/components/ListScroll";
// import swiper from '@/components/Swiper'
import navBar from "@/components/NavBar";
import { getData } from "@/service/home";
import { getUserInfoMe } from "@/service/user";
import { Toast } from "vant";
import { useStore } from "vuex";
import { getLocal } from "@/common/js/utils";
export default {
  name: "home",
  components: {
    listScroll,
    // swiper,
    navBar,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      showLogin: true,
      timestamp: 0,
      to: "",
      index: "",
      index_data: "", //高考时间
      now_year: "", //当前年
      now_month: "", //当前月
      now_date: "", //当前日
      now_day: "一", //当前星期
      day_Differ: "342", //相差日
      i: 1,
      feed: [],
      url: "https://www.yanzhan.xyz/",
      swiperList: [], // 轮播图列表
      isLogin: false, // 是否已登录
      headerScroll: false, // 滚动透明判断
      hots: [],
      newGoodses: [],
      recommends: [],
      loading: true,
    });

    onMounted(async () => {
      Toast.loading({
        message: "加载中...",
        forbidClick: true,
      });

      const data2 = await getData(1);

      state.loading = false;
      state.feed = data2.content;
      // 创建时间
      let date = new Date();
      state.now_year = date.getFullYear();
      state.now_month = date.getMonth() + 1;
      state.now_date = date.getDate();
      //更新scroll

      Toast.clear();
      const token = getLocal("token");
      if (token) {
        await getUserInfo();
        state.showLogin = false;
      } else {
        console.log("首页 没有token");
      }
      //设置用户信息
    });

    const getUserInfo = async () => {
      const res = await getUserInfoMe();
      // console.log("==========getUserInfo")
      // console.log(res.content)
      store.commit("setUserInfo", res.content);
      // console.log(store.state.userInfo)
    };

    nextTick(() => {
      window.addEventListener("scroll", () => {
        let scrollTop =
          window.pageYOffset ||
          document.documentElement.scrollTop ||
          document.body.scrollTop;
        scrollTop > 100
          ? (state.headerScroll = true)
          : (state.headerScroll = false);
      });
    });

    const goToDetail = (item) => {
      router.push({ path: `/product/${item.goodsId}` });
    };

    const tips = () => {
      Toast("敬请期待");
    };
    const getIndex = (index) => {
      console.log(index);
      store.commit("setQueQuestion", state.feed[index].question);
      console.log(store.state.que);
      var a = store.state.to;
      console.log("======a=====");
      console.log(a);
      if (a == "que") {
        router.push({
          path: "/question",
          query: { id: state.feed[index].questionId },
        });
      } else {
        const answerId = state.feed[index].answerId;
        router.push({ path: "/answer", query: { id: answerId } });
      }
    };
    const bindQueTap = () => {
      store.commit("setToAsQue");

      console.log(store.state.to);
    };
    const bindItemTap = () => {
      store.commit("setToAsItem");
    };
    const open_01 = () => {
      router.push({ path: "/majorWatch" });
    };
    const open_02 = () => {
      router.push({ path: "/recommend" });
    };
    const open_03 = () => {
      router.push({ path: "/course" });
    };

    return {
      ...toRefs(state),
      goToDetail,
      tips,
      getIndex,
      bindQueTap,
      bindItemTap,
      open_01,
      open_02,
      open_03,
    };
  },
};
</script>

<style lang="less" scoped>
@import "../common/style/mixin";
@import "../common/style/vuetify";
.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: 150px;
  text-align: center;
  background-color: #39a9ed;
}

#top {
  // border: 1px solid #2469e9;
  // background: #a8e5f9;
  border-radius: 2px;
  // margin-top: -22.5px;
  height: 300px;
}

// .top {
//   display: block;
//   margin-top: 30px;
// }

.top_left {
  float: left;
  margin-left: 20px;
  padding-left: 20px;
  padding-top: 5px;
  /* width: 300rpx; */
  margin-top: 10px;
}

.top_right {
  float: right;
  margin-right: 8vw;
  font-size: 80px;
  color: white;
}

.top_left_1 {
  color: white;
  font-size: 22.5px;
}

.top_left_font {
  font-size: 37.5px;
  color: white;
}

.input {
  margin: 0 auto;
  width: 325px;
  height: 45px;
  padding-left: 12px;
  color: gray;
  background: white;
  border: 1px solid #524f50;
  border-radius: 11px;
}

.input_around {
  display: flex;
  align-items: center;
  position: relative;
  margin: 17px auto 90rem;
  width: 325px;
  height: 70px;
  border-radius: 13px;
  background-repeat: no-repeat;
  background-size: cover;
}

.bottom {
  margin-top: 22px;
  width: 100%;
  border-radius: 10px;
  margin-left: 4px;
}
.word {
  border-radius: 2.5px;
  height: 25px;
  width: 365px;
}

.pic_word1 {
  font-size: 17px;
  color: gray;
  z-index: 1;
  margin-left: 25px;
  padding-bottom: 5px;
}

.pic_word2 {
  font-size: 15px;
  color: gray;
  width: 40px;
  z-index: 1;
  margin-right: 2px;
}

.index_navigation {
  margin: 12px auto;
  width: 350px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-around;
}

.index_navigation_slide {
  margin-top: 425px;
}

.navigation_son {
  height: 90px;
  width: 90px;
  display: inline-block;
  text-align: center;
  transition: 0.2s;
  position: relative;
  border-radius: 14px;

  box-shadow: 0rpx 0rpx #fff;
}

.navigation_son_1 {
  background: #39a9ed !important;
}

.navigation_son_2 {
  background: white !important;
}

.navigation_son_word {
  display: block;
  color: #fff;
  font-size: 16px;
  padding-top: 2px;
  padding-bottom: 2px;
}

.navigation_son_word_2 {
  color: #39a9ed !important;
}

.navigation_son .fa {
  font-size: 40px;
  color: #fff;
  margin-top: 50rpx;
}
.frist {
  border: dashed 1px;
  padding: 40px;
  height: 40px;
}
.notify-item {
  background: #ffffff;
  padding: 12px 5px 12px 5px;
  border-bottom: solid 1px #ebebeb;
  border-radius: 9px;
  margin: 2px auto;
}
.search-content {
  border-radius: 5px;
  width: 90%;
  height: 40vh;
  margin: 0 auto;
  background: #fff;
  overflow-y: scroll;
  touch-action: pan-y;
}
.container {
  height: 100%;
  overflow-x: hidden;
  overflow-y: hidden;
}
.title {
  color: rgb(24, 12, 12);
}
</style>
