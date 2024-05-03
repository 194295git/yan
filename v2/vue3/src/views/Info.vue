

<template>
  <div class="cart-box">
    <s-header :name="'企聊聊'" :noback="true"></s-header>
    <div>
      <div v-if="current == 0" class="box2 mb-2">
        <!-- todo 做树形结构框 -->
        <van-tree-select

          v-model:main-active-index="activeIndex"
          :items="items"
        >
          <template #content>
            <div
              class="d-flex mb-1 notify-item"
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
          </template>
        </van-tree-select>
        <text @click="queryLiveUser">公司组织架构</text>

        <!-- 如下是显示群聊的部分 -->
        <text>分组</text>
        <div
          class="d-flex mb-2 notify-item"
          @click="changeToGroup(index)"
          v-for="(group, index) in groups"
          :key="index"
        >
          <div class="">
            <van-image
              width="35px"
              height="35px"
              fit="cover"
              :src="group.image"
            />
          </div>
          <div class="ml-2" style="width: 70vw;">
            <div>
              {{ group.groupName }}
            </div>
            <div>
              {{ group.description }}
            </div>
          </div>
        </div>
      </div>
    </div>
  




    <!-- <div @click="sendData()">发送数据</div> -->
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import { reactive, onMounted, toRefs, ref } from "vue";
import { useRouter } from "vue-router";
// import { Toast } from "vant";
import navBar from "@/components/NavBar";
import sHeader from "@/components/SimpleHeader";
import {
  queryEyeUser,
  getChatContent,
  getAllGroup,
  getGroupMemberDetail,
} from "@/service/chat";
import { useStore } from "vuex";
import { getLocal } from "@/common/js/utils";
export default {
  components: {
    navBar,
    sHeader,
  },
  setup() {
    // const activeId = ref([1, 2]);
    const activeIndex = ref(0);
    const items = [
      {
        text: "财务",
      },
      {
        text: "后勤",
      },
      {
        text: "开发",
      },
    ];
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      memberBaseDetail: [],
      userlist: [],
      // socketServe: SocketService.Instance,
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
      toGroup: {},
      content: "",
      mini: "mini",
      //关于重连的需要的
      timeout: 40000, // 30s
      timeoutObj: null,
      groups: [],
    });

    onMounted(() => {
      getToken();
      init();
    
    });
  
    const getToken = async () => {
      const token = getLocal("token");
       console.log(token==undefined ?"token为空":"token不为空");
      if (token) {
        state.userInfo = store.state.userInfo;

        return;
      } else {
        router.push({ path: "/login" });
      }
    };

    const init = async () => {
      // Toast.loading({ message: "加载中...", forbidClick: true });
      const data = await queryEyeUser();
      console.log(data);
      state.userlist = data.content;

      const res = await getAllGroup();
      state.groups = res.content;
      console.log(res);
      // state.list = data;
      // state.result = data.map((item) => item.cartItemId);

      //向后端发送注册的消息
      // Toast.clear();
    };

  
    

    const goBack = () => {
      router.go(-1);
    };

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} });
    };

    const changeToUser = async (index) => {
      state.email = state.userlist[index].email;
      state.toUser = state.userlist[index];
      // state.current = 1;
      const openid = state.toUser.openid;
      const data = await getChatContent(openid);
      state.recesiveAllMsg = data.content;
      goTo('/other')
    };
  
    const changeCur = () => {
      state.current = 0;
    };
    const changeCurDiy = (param) => {
      state.current = param;
    };
    const showAllMember = async () => {
      state.current = 3;
      const memberDeatil = await getGroupMemberDetail(state.toGroup.groupId);
      console.log(429, memberDeatil.content);
      state.memberBaseDetail = memberDeatil.content;
    };
  
    return {
      items,
      // activeId,
      activeIndex,
      ...toRefs(state),
      goBack,
      goTo,
      changeToUser,
      changeCur,
      showAllMember,
      changeCurDiy,
    };
  },
};
</script>

<style lang="less">
@import "../common/style/mixin";
@import "../common/style/vuetify";
.content {
  margin-top: 7px;
  margin-bottom: 7px;
}

.content1 {
  padding: 2px 6px;
  border-radius: 5px;
  background-color: white;
  margin-left: 4px;
  max-width: 73vw;
}

.content2 {
  padding: 2px 6px;
  margin-right: 4px;
  border-radius: 5px;
  max-width: 73vw;
  background-color: #ffff88;
}

.send-msg {
  position: fixed;
}

.notify-item {
  background: #ffffff;
  padding: 5px 8px;
  border-bottom: solid 1px #ebebeb;
  border-radius: 9px;
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
  height: 76vh;
  padding: 0 10px;
  background: #fff;
  overflow-y: scroll;
  touch-action: pan-y;
  margin-bottom: 10px;
  margin-top: 2px;
}
.box-border-grey {
  padding: 2px;
  border: 2px;
  width: 300px;
}
.boxS {
  width: 94%;
  margin: 0 auto;
  border-color: black;
}
.send {
  background-color: aqua;
}
</style>
