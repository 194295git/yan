<template>
  <div class="cart-box">
    <s-header :name="'企聊聊'" :noback="true"></s-header>

    <div>
      <div v-if="current == 1" class="box2 mt-2">
        <div class="box2">
          <van-icon name="chat-o" @click="changeCur()" />

          <text class="mt-2 ml-2" @click="changeCur()">返回</text>
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
        <div
          class="boxS d-flex justify-space-between align-centers send-msg mt-4"
        >
          <!-- 可以使用 CellGroup 作为容器 -->
          <div style="height:10px">
            <van-cell-group inset>
              <van-field
                v-model="content"
                placeholder="请输入文本内容"
                class="box-border-grey "
              />
            </van-cell-group>
          </div>
          <div class="mt-1 send">
            <button
              style="background-color: aqua;"
              :size="mini"
              type="default"
              @click="sendMsg2()"
            >
              发送
            </button>
          </div>
        </div>
      </div>
    </div>

    <div>
      <div v-if="current == 2" class="box2 mt-2">
        <div class="box2">
          <van-icon name="chat-o" @click="changeCur()" />

          <text class="mt-2 ml-2" @click="changeCur()">返回</text>
          <text class="mt-2 ml-2" @click="showAllMember()">查看成员</text>
        </div>
        <div class="scroll">
          <list-scroll :scroll-data="recesiveAllMsg"  :scrollToEndFlag="scrollToEndFlag">
            <div class="swiper-container">
              <div
                class="content"
                v-for="(item, index) in recesiveAllMsg"
                :key="index"
              >
                <!-- self -->
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
                <!-- receive这边的消息 -->
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
                      :src="item.avatarUrl"
                    />
                  </div>
                </div>
              </div>
            </div>
          </list-scroll>
        </div>
        <div
          class="boxS d-flex justify-space-between align-centers send-msg mt-4"
        >
          <!-- 可以使用 CellGroup 作为容器 -->
          <div style="height:10px">
            <van-cell-group inset>
              <van-field
                v-model="content"
                placeholder="请输入文本内容"
                class="box-border-grey "
              />
            </van-cell-group>
          </div>
          <div class="mt-1 send">
            <button
              style="background-color: aqua;"
              :size="mini"
              type="default"
              @click="sendMsg2()"
            >
              发送
            </button>
          </div>
        </div>
      </div>
    </div>
    <div>
      <div v-if="current == 3" class="box2 mt-2">
        <div @click="changeCurDiy(2)">返回</div>
        <div
          class="d-flex mb-1 notify-item"
          v-for="(userBase, index) in memberBaseDetail"
          :key="index"
        >
          <div class="">
            <van-image
              width="35px"
              height="35px"
              fit="cover"
              :src="userBase.avatarUrl"
            />
          </div>
          <div class="ml-2" style="width: 10vw;">
            {{ userBase.email }}
          </div>
          <div class="ml-2" style="width: 70vw;">
            {{ userBase.username }}
          </div>
        </div>
      </div>
    </div>

    <!-- <div @click="sendData()">发送数据</div> -->
    <nav-bar-chat></nav-bar-chat>
  </div>
</template>

<script>
import listScroll from "@/components/ListScroll";
import { reactive, onMounted, toRefs, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { Toast } from "vant";
import navBarChat from "@/components/NavBarChat";
import sHeader from "@/components/SimpleHeader";
import {
  queryEyeUser,
  getChatContent,
  getAllGroup,
  getGroupContent,
  getAvatarUrlByOpenid,
  getGroupMemberDetail,
} from "@/service/chat";
import { getLeaf } from "@/service/user";
import SocketService from "@/common/js/websocket";
import Queue from "@/common/js/queue";
import * as imconstant from "@/common/js/imconstant";
import { useStore } from "vuex";
import { getLocal, retry } from "@/common/js/utils";
export default {
  components: {
    sHeader,
    listScroll,
    navBarChat,
  },
  setup() {
    const value = ref("");
    const onSearch = (val) => Toast(val);
    const onCancel = () => Toast("取消");

    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const state = reactive({
      memberBaseDetail: [],
      userlist: [],
      queue: new Queue(),
      socketServe: SocketService.Instance,
      recesiveAllMsg: [],
      userInfo: {},
      current: route.query.current,
      //复制来的
      keyword: "搜索好友",
      email: route.query.email,
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
      openid: route.query.openid,
      groupId: route.query.groupId,
      //用于消息重试时候的消息
      tempSendMsg: {},
    });

    //重试的函数，判断消息队列里面有没有消息，有消息的话需要重新发送一下
    const fetchDataFn = function(msgid) {
      //检测队列的msgid 和发送消息的msgid 一不一样。不一样的话需要重新发送消息；
      console.log("【IM日志 重试机制】type为 -1时候收到的mgsid", msgid);
      const temp = state.queue.peek();
      console.log(
        "【IM日志 重试机制】消息队列里面的peek()",
        state.queue.peek()
      );
      //  state.socketServe.send(state.tempSendMsg);
      // console.log("【IM日志 重试机制】消息队列里面的size", state.queue.size());
      if (temp != undefined) {
        if (temp.params.msgid == msgid) {
          //还没有收到消息代表需要重发消息。
          state.tempSendMsg.parmas.isretry = true;
          state.socketServe.send(state.tempSendMsg);
          throw new Error("消息队列还存在消息");
        }
      }
      //抛出异常后进行重试
      return "ok";
    };

    onMounted(() => {
      getToken();
      init();
      //收到消息后更新前端数据
      state.socketServe.ws.onmessage = async (msg) => {
        console.log("【IM日志】从服务端获取到的原始数据", msg.data);
        const res =
          JSON.parse(msg.data).msg == undefined
            ? JSON.parse(msg.data)
            : JSON.parse(msg.data).msg;
        console.log(res);
        if (res.type === 0) {
          return;
        }
        // msg:A 收消息的情况，把消息推送上去
        if (res.type === imconstant.SINGLE_MESSAGE_RESPONSE) {
          //发送消息成功的时候将自己这条消息维护进如队列。
          console.log("【IM日志】 msg:A 维护消息进入队列");
          // Queue 推送队列消息
          //如果用户没有登录，则是不需要进行队列信息维护的
          console.log("【IM日志】res.params.online", res.params.online, res.params.isretry);
          if (res.params.online == true && res.params.isretry == false) {
            state.queue.offer(state.tempSendMsg);
            // 用户没有登录的时候使用 这个机制 登录了就不使用了 使用了timer机制
            //使用timer机制 检测队列里面是否存在ack，如果存在，则超时重发以及限制次数
            const result = await retry(fetchDataFn, 3, 1000, res.params.msgid);
            //三次之后消息还没有发送成功 提示消息发送失败
            if (result == false) {
              Toast("消息发送失败，请重新发送");
            }
          } else {
            console.log("【IM日志】 接受消息者没有登录");
          }
        }
        //收消息的情况，把消息推送上去
        if (res.type === imconstant.SINGLE_MESSAGE_OTHER) {
          state.recesiveAllMsg.push({
            type: "receive",
            content: res.params.message,
          });
          singleAck(JSON.parse(msg.data));
        }

        if (res.type === imconstant.SINGLE_MESSAGE_ACK_OTHERCLIENT) {
          //代表客户端a成功的把消息发送了出去
          state.queue.poll();
          console.log(
            "【IM日志】ack:N 成功收到了ack消息  队列中的消息已经被删除"
          );
          console.log("【IM日志 】ack:N消息队列里面的peek", state.queue.peek());
        }
        //群聊收消息
        if (res.type === 10) {
          const avatar = getAvatarUrl(res.params.fromUser.openid);
          avatar.then((result) => {
            console.log(result);
            state.recesiveAllMsg.push({
              type: "receive",
              content: res.params.message,
              avatarUrl: result,
            });
          });
          // console.log(avatar)
          //  这里写个接口去获取头像res.params.fromUser.openid
        }
      };
    });
    const getAvatarUrl = async (id) => {
      const res = await getAvatarUrlByOpenid(id);
      return res.content;
    };
    const getToken = async () => {
      const token = getLocal("token");
      // console.log(token == undefined ? "token为空" : "token不为空");
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

      state.userlist = data.content;

      state.toUser = store.state.toUser;
      console.log("【vuex】state.toUser", state.toUser);
      const res = await getAllGroup();
      state.groups = res.content;
      //先注册自己到channel 里面
      sendRegisterData();
      if (state.current == 1) {
        //单聊情况
        //向后端发送注册的消息
        const msgdata = await getChatContent(state.openid);

        state.recesiveAllMsg = msgdata.content;
      }
      if (state.current == 2) {
        state.socketServe.send(store.state.registerGroup);
        console.log("发送群聊注册消息");

        //展现出所有的聊天内容
        const group = await getGroupContent(state.groupId);
        state.recesiveAllMsg = [];
        state.recesiveAllMsg = group.content;
      }

      // Toast.clear();
    };

    //建立连接

    SocketService.Instance.connect(localStorage.getItem("token"));
    state.socketServe = SocketService.Instance;
    state.socketServe.registerCallBack("callback1", state.socketServe);

    //发送注册的数据
    const sendRegisterData = () => {
      var data = {
        type: imconstant.REGISTER,
        params: {
          openid: store.state.userInfo.openid,
          userName: store.state.userInfo.email,
          loginStatus: "1",
        },
      };
      state.socketServe.send(data);
      console.log("【IM日志】 发送注册数据", data);
    };

    //发送单聊ack
    const singleAck = async (receive) => {
      console.log("receive", receive);

      var data = {
        type: 15,
        params: {
          from: "client",
          msgid: receive.messageId,
          fromUser: receive.msg.params.fromUser.openid,
          toUser: receive.msg.params.toUser.openid,
        },
      };
      console.log(data);
      state.socketServe.send(data);
      console.log("【IM日志】回应单聊ack");
    };

    const goBack = () => {
      router.go(-1);
    };

    const goTo = () => {
      router.push({ path: "/home" });
    };

    const changeCur = () => {
      router.go(-1);
    };
    const changeCurDiy = (param) => {
      state.current = param;
    };
    const showAllMember = async () => {
      state.current = 3;
      const memberDeatil = await getGroupMemberDetail(state.groupId);
      console.log(429, memberDeatil.content);
      state.memberBaseDetail = memberDeatil.content;
    };
    const sendMsg2 = async () => {
      const { content, toUser } = state;
      const no = await getLeaf();
      let data = {
        // 1代表着私聊的意思
        type: 1,
        params: {
          msgid: no.content,
          toMessageId: toUser.openid,
          message: content,
          fileType: 0,
          isretry:false,
        },
      };
      if (state.current == 2) {
        data = {
          type: 9,
          params: {
            toMessageId: state.groupId,
            message: content,
            fileType: 0,
          },
        };
      }
      console.log(data);
      state.tempSendMsg = data;
      state.socketServe.send(data);
      state.recesiveAllMsg.push({
        type: "self",
        content: content,
      });
      state.content = "";
    };

    return {
      value,
      onSearch,
      onCancel,
      ...toRefs(state),
      goBack,
      goTo,
      sendRegisterData,
      changeCur,
      showAllMember,
      sendMsg2,
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
