<template>
  <div class="cart-box">
    <s-header :name="'企聊聊'" :noback="true"></s-header>

    <div>
      <div v-if="current == 0" class="box2 mb-2">
        <form action="/">
          <van-search
            v-model="value"
            show-action
            placeholder="请输入搜索关键词"
            @search="onSearch"
            @cancel="onCancel"
          />
        </form>
        <text @click="queryLiveUser">展示所有的用户</text>

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
        <!-- 如下是显示群聊的部分 -->
        <text>展示所有的群聊</text>
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
import { Toast } from "vant";
import navBar from "@/components/NavBar";
import sHeader from "@/components/SimpleHeader";
import {
  queryEyeUser,
  getAllGroup,
  getGroupOpenid,
} from "@/service/chat";
import SocketService from "@/common/js/websocket";
import { getUserInfoMe } from "@/service/user";
import { useStore } from "vuex";
import { getLocal } from "@/common/js/utils";
export default {
  components: {
    navBar,
    sHeader,
  },
  setup() {
    const value = ref("");
    const onSearch = (val) => Toast(val);
    const onCancel = () => Toast("取消");

    const store = useStore();
    const router = useRouter();
    const state = reactive({
      userlist: [],
      userInfo: {},
      current: 0,
      //复制来的
      keyword: "搜索好友",
      email: "",
      toUser: {},
      toGroup: {},
      content: "",
      //关于重连的需要的
      timeout: 40000, // 30s
      groups: [],
    });

    //建立连接
    const initConnect = () => {
      console.log("initConnect",store.state.userInfo);
      SocketService.Instance.connect(localStorage.getItem("token"),store.state.userInfo.openid);
      state.socketServe = SocketService.Instance;
      state.socketServe.registerCallBack("callback1", state.socketServe);
    };

    onMounted(() => {
      getToken();
      init();
    });

    const getToken = async () => {
      const token = getLocal("token");
      if (token) {
        // 
        await getUserInfo ();  
        state.userInfo = store.state.userInfo;
        initConnect();
        return;
      } else {
        router.push({ path: "/login" });
      }
    };
    const getUserInfo = async () => {
      const res = await getUserInfoMe();

      store.commit("setUserInfo", res.content);
    };
    const init = async () => {
          //建立连接

      // Toast.loading({ message: "加载中...", forbidClick: true });
      const data = await queryEyeUser();
      console.log(data);
      state.userlist = data.content;

      const res = await getAllGroup();
      state.groups = res.content;
      console.log(res);
      // state.list = data;
      
      //向后端发送注册的消息
      // sendRegisterData();
      // Toast.clear();

      //初始化map
      store.commit("initChat");
      
      //根据store里面的id拉取消息，写到action里面
      // 调用 pullOffline action
      await store.dispatch('pullOffline');

    };

    const goBack = () => {
      router.go(-1);
    };

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} });
    };

    /**
     * @description: 跳转到聊天详情页
     * @param {*} index
     * @return {*}
     */
    const changeToUser = async (index) => {
      state.email = state.userlist[index].email;
      state.toUser = state.userlist[index];
      state.current = 1;
      store.commit("setToUser", state.toUser);
      const openid = state.toUser.openid;
      router.push({
        path: "/chatdetail",
        query: {
          email: state.email,
          current: state.current,
          openid: openid,
        },
      });
    };  
    // //监听一下聊天页面的情况.
    // const recesiveAllMsg =computed(() => store.state.chat);


    const changeToGroup = async (index) => {
      state.toGroup = state.groups[index];
      state.current = 2;
      //切换的时候把群id存一份.
      store.commit("setGroupId",state.toGroup.groupId);
      const res = await getGroupOpenid(state.toGroup.groupId);
      //向后端注册群聊
      
      let data = {
        type: 3,
        params: {
          userIdList: res.content.join(","),
          groupId: state.toGroup.groupId,
        },
      };
      store.commit("setRegisterGroup", data);
       router.push({
        path: "/chatdetail",
        query: {
          current: state.current,
          groupId: state.toGroup.groupId,
        },
      });
     
    };
    const changeCur = () => {
      state.current = 0;
    };
    const changeCurDiy = (param) => {
      state.current = param;
    };
    
    

    return {
      value,
      onSearch,
      onCancel,
      ...toRefs(state),
      goBack,
      goTo,
      changeToUser,
      changeToGroup,
      changeCur,
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
