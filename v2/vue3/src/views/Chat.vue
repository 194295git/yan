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
  <div class="cart-box">
    <s-header :name="'研战到底'" :noback="true"></s-header>
    <div>
      <div v-if="current == 0" class="box2 mb-2">
        <text @click="queryLiveUser">展示所有的用户</text>

        <div
          class="d-flex mb-2 notify-item"
          @click="changeToUser(index)"
          v-for="(user, index) in userlist"
          :key="index"
        >
          <div class="">
            <van-image
              width="35px"
              height="35px"
              fit="cover"
              :src="user.avatarUrl"
            />
          </div>
          <div class="ml-2" style="width: 70vw;">
            {{ user.email }}
            <div
              style="display: flex;justify-content: space-between;"
              class="d-felx justify-space-between"
            >
              <div class="">
                <text>消息内容</text>
              </div>
              <div v-if="user.noRead !== 0" class="red">
                {{ user.noRead }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <div v-if="current == 1" class="box2 mt-2">
        <div class="box2">
          <van-icon name="chat-o" @click="changeCur()" />

          <text class="mt-2 ml-2" @click="test">发送人</text>
        </div>
        <div class="scroll">
          <list-scroll :scroll-data="recesiveAllMsg">
            <div class="swiper-container">
              <div
                class="content"
                v-for="(item, index) in recesiveAllMsg"
                :key="index"
              >
                <div class="d-felx justify-start " v-if="item.type === 'self'">
                  <div style="display: flex;">
                    <van-image
                      width="35px"
                      height="35px"
                      fit="cover"
                      :src="userInfo.avatarUrl"
                    />
                    <div class="font-18 content1">
                      <text>{{ item.content }}</text>
                    </div>
                  </div>
                </div>
                <div
                  style="display: flex; justify-content: flex-end;"
                  v-if="item.type === 'receive'"
                >
                  <div class="font-18 content2">
                    <text>{{ item.content }}</text>
                  </div>
                  <div class="">
                    <van-image
                      width="35px"
                      height="35px"
                      fit="cover"
                      :src="toUser.avatarUrl"
                    />
                  </div>
                </div>
              </div>
            </div>
          </list-scroll>
        </div>
        <div class="box2 d-flex align-centers send-msg mt-2">
          <!-- 可以使用 CellGroup 作为容器 -->
          <div style="height:20px">
            <van-cell-group inset>
              <van-field
                v-model="content"
                placeholder="请输入文本内容"
                class="box-border-grey"
              />
            </van-cell-group>
          </div>
          <div class="mt-1">
            <button :size="mini" type="default" @click="sendMsg2()">
              发送
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- <div @click="sendData()">发送数据</div> -->
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import listScroll from "@/components/ListScroll";
import { reactive, onMounted, toRefs } from "vue";
import { useRouter } from "vue-router";
import { Toast } from "vant";
import navBar from "@/components/NavBar";
import sHeader from "@/components/SimpleHeader";
import { queryEyeUser, getChatContent } from "@/service/chat";
import SocketService from "@/common/js/websocket";

export default {
  components: {
    navBar,
    sHeader,
    listScroll,
  },
  setup() {
    const router = useRouter();
    const state = reactive({
      userlist: [],
      socketServe: SocketService.Instance,
      recesiveAllMsg: [],
      userInfo: {},
      checked: false,
      list: [],
      all: false,
      result: [],
      checkAll: true,
      current: 0,
      //复制来的
      keyword: "搜索好友",
      email: "",
      socketTask: null,
      // 确保websocket是打开状态
      is_open_socket: false,
      toUser: {},
      content: "",
      mini: "mini",
      //关于重连的需要的
      timeout: 40000, // 30s
      timeoutObj: null,
    });

    onMounted(() => {
      init();
    });

    const init = async () => {
      Toast.loading({ message: "加载中...", forbidClick: true });
      const data = await queryEyeUser();
      console.log(data);
      state.userlist = data.content;
      // state.list = data;
      // state.result = data.map((item) => item.cartItemId);
      Toast.clear();
    };

    SocketService.Instance.connect();
    state.socketServe = SocketService.Instance;
    state.socketServe.registerCallBack("callback1", state.socketServe);
    const sendData = () => {
      state.socketServe.send("发送数据");
      console.log("发送数据");
    };

    const goBack = () => {
      router.go(-1);
    };

    const goTo = () => {
      router.push({ path: "/home" });
    };

    const changeToUser = async (index) => {
      state.email = state.userlist[index].email;
      state.toUser = state.userlist[index];
      state.current = 1;
      const openid = state.toUser.openid;
      const data = await getChatContent(openid);
      state.recesiveAllMsg = data.content;
    };
    const changeCur = () => {
      state.current = 0;
    };
    const sendMsg2 = () => {
      const { content, toUser } = state;
      let data = {
        // 1代表着私聊的意思
        type: 1,
        params: {
          toMessageId: toUser.openid,
          message: content,
          fileType: 0,
        },
      };
      console.log(data);
    };

    return {
      ...toRefs(state),
      goBack,
      goTo,
      sendData,
      changeToUser,
      changeCur,
      sendMsg2,
    };
  },
};
</script>

<style lang="less">
@import "../common/style/mixin";
@import "../common/style/vuetify";
.content {
  margin-top: 15rpx;
  margin-bottom: 15rpx;
}

.content1 {
  padding: 4rpx 12rpx;
  border-radius: 10rpx;
  background-color: white;
  margin-left: 8rpx;
  max-width: 73vw;
}

.content2 {
  padding: 4rpx 12rpx;
  margin-right: 8rpx;
  border-radius: 10rpx;
  max-width: 73vw;
  background-color: #ffff88;
}

.send-msg {
  position: fixed;
}

.notify-item {
  background: #ffffff;
  padding: 25rpx 20rpx 25rpx 8rpx;
  border-bottom: solid 1px #ebebeb;
  border-radius: 18rpx;
}

.red {
  color: white;
  background-color: red;
  border-radius: 50%;
  width: 5vw;
  height: 5vw;
}
.scroll {
  // width: 72%;
  height: 72vh;
  padding: 0 10px;
  background: #fff;
  overflow-y: scroll;
  touch-action: pan-y;
}
</style>
