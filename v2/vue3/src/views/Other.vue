
<template>
  <div class="user-box">
    <s-header :name="'我的'"></s-header>
    <van-skeleton title :avatar="true" :row="3" :loading="loading">
       <van-contact-card
        type="edit"
        name="张三"
        tel="13000000000"
        :editable="false"
      />
      <!-- <div class="user-info">
        <div class="info">
          <van-image
            width="45px"
            height="45px"
            fit="cover"
            :src="user.avatarUrl"
          />
          <div class="user-desc">
            <span>昵称：{{ user.username }}</span>
            <span>登录名：{{ user.email }}</span>
          </div>
        </div>
      </div> -->
    </van-skeleton>
    <ul class="user-list">
     
    </ul>
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import { reactive, onMounted, toRefs } from "vue";
import navBar from "@/components/NavBar";
import sHeader from "@/components/SimpleHeader";
import { getUserInfo, getUserInfoMe } from "@/service/user";
import { useRouter } from "vue-router";
import { setLocal } from "@/common/js/utils";
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
    });

    onMounted(async () => {
      const data = await getUserInfo();
      const data2 = await getUserInfoMe();
      console.log(data2);
      state.user = data.content;
      state.loading = false;
    });

    const goBack = () => {
      router.go(-1);
    };

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} });
    };
    const logout = () => {
      setLocal("token", "");
      router.push({ path: "./login" });
    };

    return {
      ...toRefs(state),
      goBack,
      goTo,
      logout,
    };
  },
};
</script>

<style lang="less" scoped>
@import "../common/style/mixin";
.user-box {
  .user-header {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10000;
    .fj();
    .wh(100%, 44px);
    line-height: 44px;
    padding: 0 10px;
    .boxSizing();
    color: #252525;
    background: #fff;
    border-bottom: 1px solid #dcdcdc;
    .user-name {
      font-size: 14px;
    }
  }
  .user-info {
    width: 94%;
    margin: 10px;
    height: 115px;
    background: linear-gradient(90deg, @primary, #51c7c7);
    box-shadow: 0 2px 5px #269090;
    border-radius: 6px;
    .info {
      position: relative;
      display: flex;
      width: 100%;
      height: 100%;
      padding: 25px 20px;
      .boxSizing();
      img {
        .wh(60px, 60px);
        border-radius: 50%;
        margin-top: 4px;
      }
      .user-desc {
        display: flex;
        flex-direction: column;
        margin-left: 10px;
        line-height: 20px;
        font-size: 14px;
        color: #fff;
        span {
          color: #fff;
          font-size: 14px;
          padding: 2px 0;
        }
      }
      .account-setting {
        position: absolute;
        top: 10px;
        right: 20px;
        font-size: 13px;
        color: #fff;
        .van-icon-setting-o {
          font-size: 16px;
          vertical-align: -3px;
          margin-right: 4px;
        }
      }
    }
  }
  .user-list {
    padding: 0 20px;
    margin-top: 20px;
    li {
      height: 40px;
      line-height: 40px;
      display: flex;
      justify-content: space-between;
      font-size: 14px;
      .van-icon-arrow {
        margin-top: 13px;
      }
    }
  }
}
</style>
