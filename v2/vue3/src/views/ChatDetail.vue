<template>
  <div class="cart-box">
    <s-header :name="'企聊聊'" :noback="true"></s-header>

    <div>
      <div class="box2 mt-2">
        <div v-if="current == 1" class="box2">
          <van-icon name="chat-o" @click="changeCur()" />

          <text class="mt-2 ml-2" @click="changeCur()">返回</text>
        </div>
        <div v-if="current == 2" class="box2">
          <van-icon name="chat-o" @click="changeCur()" />

          <text class="mt-2 ml-2" @click="changeCur()">返回</text>
          <text class="mt-2 ml-2" @click="showAllMember()">查看成员</text>
        </div>
        <div class="scroll">
          <list-scroll :scroll-data="computedChats.messages">
            <div class="swiper-container">
              <div
                class="content"
                v-for="(item, index) in computedChats.messages"
                :key="index"
              >
                <chat-message-item :emojis="emojis" :item="item" />
              </div>
            </div>
          </list-scroll>
        </div>
        <div class="d-flex justify-space-between align-centers">
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
          <div v-if="current == 1" class="mt-1 send">
            <button
              style="background-color: aqua;"
              :size="mini"
              type="default"
              @click="sendMsg2('1')"
            >
              发送
            </button>
          </div>
          <div v-if="current == 2" class="mt-1 send">
            <button
              style="background-color: aqua;"
              :size="mini"
              type="default"
              @click="sendMsgGroup('1')"
            >
              发送
            </button>
          </div>
        </div>
        <!-- 底部表情包 -->
        <div class="nav-bar-chat ">
          <ul class="nav-list">
            <div class="nav-list-item" @click="toggleEmojiPanel">
              <van-icon name="like" />
              <span>表情</span>
            </div>
            <div class="nav-list-item">
              <van-icon name="video" />
              <span @click="toggleCapture">拍摄</span>
            </div>
            <div class="nav-list-item">
              <van-icon name="photo" /><span @click="toggleCapture">图片</span>
            </div>
            <div class="nav-list-item">
              <van-icon name="column" />
              <span @click="toggleCapture">文件</span>
            </div>
            <div class="nav-list-item">
              <van-icon name="audio" /><span @click="toggleVoice">语音</span>
            </div>
          </ul>
          <!-- 表情表以及聊天室 -->
          <chat-Emotion
            style="margin: 10px auto;
            display: flex;
            justify-content: center;"
            :emojis="emojis"
            v-if="showEmojiPanel"
            @add-emoji="addEmoji"
          />
          <!-- 语音 -->
          <chat-Voice
            style="margin: 10px auto;
            display: flex;
            justify-content: center;"
            v-if="showVoice"
            @send="sendVoice"
          />
          <!-- 文件上传 -->
          <chat-upload
            style="margin: 10px auto;
            display: flex;
            justify-content: center;"
            v-if="showCapture"
            @send="sendVoice"
          />
        </div>
      </div>
    </div>
    <!-- 展示群聊成员页面 暂时隐藏吧 -->
    <!-- <div>
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
    </div> -->
  </div>
</template>

<script>
import listScroll from "@/components/ListScroll";

import { reactive, onMounted, toRefs } from "vue";
import { useRouter, useRoute } from "vue-router";
import { Toast } from "vant";
import sHeader from "@/components/SimpleHeader";
import chatEmotion from "@/components/chat/ChatEmotion";
import chatVoice from "@/components/chat/ChatVoice";
import chatUpload from "@/components/chat/ChatUpload";
import ChatMessageItem from "@/components/chat/ChatMessageItem";
import {
  queryEyeUser,
  getAllGroup,
  getAvatarUrlByOpenid,
  getGroupMemberDetail,
} from "@/service/chat";
import { getLeaf } from "@/service/user";
import SocketService from "@/common/js/websocket";
import Queue from "@/common/js/queue";
import * as imconstant from "@/common/js/imconstant";
import { useStore } from "vuex";
import { computed } from "vue";
import { getLocal, retry } from "@/common/js/utils";
import { EMOJIS } from "@/common/js/emoji";
export default {
  //使用如下代码暂时停用一下客户端重试逻辑.

  /* eslint-disable no-unused-vars */
  components: {
    sHeader,
    listScroll,
    chatEmotion,
    chatVoice,
    ChatMessageItem,
    chatUpload,
  },
  setup() {
    const onSearch = (val) => Toast(val);
    const onCancel = () => Toast("取消");

    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const state = reactive({
      //表情包
      showEmojiPanel: false, // 控制表情包面板的显示
      //显示语音通话
      showVoice: false,
      showCapture: false, //显示拍照
      emojis: EMOJIS,
      memberBaseDetail: [],
      userlist: [],
      queue: new Queue(),
      socketServe: SocketService.Instance,
      recesiveAllMsg: [],
      userInfo: {},
      current: route.query.current,
      //复制来的
      email: route.query.email,
      toUser: {},
      toGroup: {},
      content: "",
      mini: "mini",
      groups: [],
      openid: route.query.openid,
      groupId: route.query.groupId,
      //用于消息重试时候的消息
      tempSendMsg: {},
      //暂存需要commit的数据
      commitdata: {},
    });

    //切换表情面板
    const toggleEmojiPanel = function() {
      state.showEmojiPanel = !state.showEmojiPanel; // 切换表情面板的显示状态
    };
    // toggleCapture
    //切换拍摄面板
    const toggleCapture = function() {
      state.showCapture = !state.showCapture; // 切换表情面板的显示状态
    };
    //切换语音面板
    const toggleVoice = function() {
      console.log("toggleVoice");
      state.showVoice = !state.showVoice; // 切换表情面板的显示状态
    };

    //添加表情
    const addEmoji = function(emoji) {
      if (emoji.type == "pic") {
        state.content += emoji.encode;
      } else {
        state.content += emoji.name;
      }
      // 简化处理，实际应用中可能需要特殊处理
      state.showEmojiPanel = false; // 选择表情后关闭面板
    };

    /**
     * 重试的函数，判断消息队列里面有没有消息，有消息的话需要重新发送一下
     */
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
          console.log(" state.tempSendMsg.", state.tempSendMsg);
          // state.tempSendMsg.parmas.isretry = 'true';
          state.socketServe.send(state.tempSendMsg);
          throw new Error("消息队列还存在消息");
        }
      }
      //抛出异常后进行重试
      return "ok";
    };

    // 创建一个计算属性，该属性基于其他响应式状态计算值
    const computedChats = computed(() => {
      let chat = null;
      console.log("computedChats route.query.groupId", route.query.groupId);
      if (state.current == 1) {
        chat = {
          targetId: state.toUser.openid,
        };
      } else {
        chat = {
          // targetId: state.toUser.openid,
          targetId: state.groupId,
        };
      }
      const idx = store.getters.findChatIdx(chat);
      if (idx == null || idx == undefined) {
        return [];
      }
      if (
        store.state.chats[idx] == null ||
        store.state.chats[idx] == undefined
      ) {
        return [];
      }
      console.log("computedChats idx", idx);
      console.log("computedChats 寻找成功啦", store.state.chats[idx]);
      return store.state.chats[idx];
    });
    const solveSINGLE_MESSAGE_RESPONSE = async (res) => {
      //发送消息成功的时候将自己这条消息维护进如队列。
      console.log("【IM日志】 msg:A 维护消息进入队列");
      if (res.params.online == true && res.params.isretry == "false") {
        state.queue.offer(state.tempSendMsg);
        // 用户没有登录的时候使用 这个机制 登录了就不使用了 使用了timer机制
        //使用timer机制 检测队列里面是否存在ack，如果存在，则超时重发以及限制次数
        const result = await retry(fetchDataFn, 3, 1000, res.params.msgid);
        //三次之后消息还没有发送成功 提示消息发送失败
        if (result == false) {
          Toast("消息发送失败，请重新发送");
        }
      } else {
        console.log("【IM日志】 接受消息者没有登录或者是重试消息 ");
      }
    };
    const solveSINGLE_MESSAGE_OTHER = async (res) => {
      console.log("【IM日志】imconstant.SINGLE_MESSAGE_OTHER", res);
      /**
       * 需要去前端对比一下id
       */
      let avatardb = null;
      const avatararray = store.state.avatarMap;
      for (var i = 0; i < avatararray.length; i++) {
        if (avatararray[i].openid == res.params.openid) {
          console.log("avatararray[i].avatarUrl", avatararray[i].avatar_url);
          avatardb = avatararray[i].avatar_url;
        }
      }
      console.log("avatarMap", avatararray);
      //创建前端消息存储.
      const commitdata = {
        type: "receive",
        content: res.params.message,
        msgId: res.params.msgId,
        otherOpenid: res.params.openid,
        ttype: res.params.ttype,
        avatarUrl: avatardb,
        group: "1",
        createTime: new Date().getTime(),
        targetId: res.params.openid,
      };

      store.commit("insertMessage", commitdata);
  
      singleAck(res);
    };

    const solveGroupMessage = async (res) => {
      const avatar = getAvatarUrl(res.params.fromUser.openid);
      avatar.then((result) => {
        console.log(result);
        // state.recesiveAllMsg.push({
        //   type: "receive",
        //   content: res.params.message,
        //   avatarUrl: result,
        // });
        const commitdata = {
          type: "receive",
          content: res.params.message,
          msgId: res.params.msgId,
          otherOpenid: res.params.fromUser.openid,
          avatarUrl: result,
          createTime: new Date().getTime(),
          targetId: route.query.groupId,
        };

        store.commit("insertMessage", commitdata);
      });
    };
    onMounted(() => {
      // decodeCodeToEmoji("[emoji:avatar]321321");
      getToken();
      init();
      //收到消息后更新前端数据
      state.socketServe.ws.onmessage = async (msg) => {
        console.log("【IM日志】从服务端获取到的原始数据", msg.data);
        // const res =
        //   JSON.parse(msg.data).msg == undefined
        //     ? JSON.parse(msg.data)
        //     : JSON.parse(msg.data).msg;
        const res = JSON.parse(msg.data)
         
        console.log("收到服务端原始数据解析数据",res);
        if (res.type === 0 || res.type == "pong") {
          console.log(res.type);
          return;
        }


        //处理前端重试逻辑
        if (res.type === imconstant.SINGLE_MESSAGE_RESPONSE) {
          
            // solveSINGLE_MESSAGE_RESPONSE(res);
         
        }
        //用户b收消息
        if (res.type === imconstant.SINGLE_MESSAGE_OTHER) {
          solveSINGLE_MESSAGE_OTHER(res);
        }
        //当收到消息的时候则删除队列里面的消息，停止重试,插入消息
        if (res.type === imconstant.SINGLE_MESSAGE_ACK_RESPONSE) {
          //代表客户端a成功的把消息发送了出去
          //收到ack的时候才需要把消息给放置到客户端，之前的有逻辑问题.
          state.queue.poll();
          console.log(
            "【IM日志】ack:N 成功收到了ack消息  队列中的消息已经被删除"
          );
          console.log("【IM日志 】ack:N消息队列里面的peek", state.queue.peek());

          //是不能立即insert的，必须发送成功了之后才能insert todo
          store.commit("insertMessage", state.commitdata);
          state.content = "";
        }
        //群聊收消息
        if (res.type === 10) {
          solveGroupMessage(res);
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

    /**
     * 初始化方法
     */
    const init = async () => {
      // Toast.loading({ message: "加载中...", forbidClick: true });
      const data = await queryEyeUser();

      state.userlist = data.content;

      state.toUser = store.state.toUser;
      console.log("【vuex】state.toUser", state.toUser);

      // 这个地方openchat 要判断一下群聊还是单聊
      if (state.current == 1) {
        const openChat = {
          type: "PRIVATE",
          targetId: state.toUser.openid,
        };

        store.commit("openChat", openChat);
        store.commit("saveToStorage");
      }
      if (state.current == 2) {
        const res = await getAllGroup();
        state.groups = res.content;
        const openChat = {
          type: "GROUP",
          targetId: route.query.groupId,
        };

        store.commit("openChat", openChat);
        store.commit("saveToStorage");
      }

    };

   
    //发送单聊ack
    const singleAck = async (res) => {
      console.log("singleAck receive", res);

      var data = {
        type: 15,
        params: {
         
          msgid: res.params.msgId,
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

    /**
     * 构造发送消息的请求体
     */
    const createData = (no, ttype, isretry, isvoice, url) => {
      const { content, toUser } = state;
      let data = {};
      let type = "";
      if (state.current == 1) {
        type = "1";
      }
      if (state.current == 2) {
        type = "9";
      }
      data.type = type;
      let params = {
        msgid: no.content,
        toMessageId: toUser.openid,
        fileType: ttype,
        ttype: ttype,
        isretry: isretry,
      };
      if (isvoice) {
        params.message = url;
      } else {
        params.message = content;
      }
      data.params = params;
      return data;
    };
    /**
     * 发送消息
     */
    const sendMsg2 = async (ttype) => {
      const { content, toUser, userInfo } = state;
      const no = await getLeaf();
      let data = createData(no, ttype, false, false, "");
      let data2 = createData(no, ttype, true, false, "");

      state.tempSendMsg = data2;
      state.socketServe.send(data);
      const commitdata = {
        type: "self",
        ttype: ttype,
        content: content,
        msgId: no.content,
        otherOpenid: toUser.openid,
        avatarUrl: userInfo.avatarUrl,
        group: "1",
        createTime: new Date().getTime(),
        targetId: toUser.openid,
      };
      state.commitdata = commitdata;
    };
    /*
     * 发送语音
     */
    const sendVoice = async (ttype, src) => {
      console.log("是否走进来发送语音");
      const { toUser } = state;
      const no = await getLeaf();
      let data = createData(no, ttype, false, true, src);
      let data2 = createData(no, ttype, true, true, src);
      console.log("发送sendVoice数据", data);
      state.tempSendMsg = data2;
      state.socketServe.send(data);
      const commitdata = {
        type: "self",
        ttype: ttype,
        content: src,
        msgId: no.content,
        otherOpenid: toUser.openid,
        avatarUrl: state.userInfo.avatarUrl,
        group: "1",
        createTime: new Date().getTime(),
        targetId: toUser.openid,
      };
      store.commit("insertMessage", commitdata);
    };
    const sendMsgGroup = async (ttype) => {
      const { content } = state;
      const no = await getLeaf();

      const data = {
        type: 9,
        params: {
          toMessageId: state.groupId,
          message: content,
          fileType: ttype,
          msgid: no.content,
        },
      };
      console.log("sendMsgGroup", sendMsgGroup);
      state.socketServe.send(data);
      const commitdata = {
        type: "self",
        ttype: ttype,
        content: content,
        msgId: no.content,
        otherOpenid: route.query.groupId,
        avatarUrl: state.userInfo.avatarUrl,
        createTime: new Date().getTime(),
        targetId: route.query.groupId,
        group: "0",
      };
      console.log(" sendMsgGroup commitdata", commitdata);
      store.commit("insertMessage", commitdata);
      state.content = "";
    };

    return {
      computedChats,
      onSearch,
      onCancel,
      ...toRefs(state),
      goBack,
      goTo,
      changeCur,
      showAllMember,
      sendMsg2,
      sendVoice,
      changeCurDiy,
      sendMsgGroup,
      toggleEmojiPanel,
      addEmoji,
      toggleCapture,
      toggleVoice,
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
  display: flex;
  flex-direction: column;
  align-items: center;
}

.content2 {
  padding: 2px 6px;
  margin-right: 4px;
  border-radius: 5px;
  max-width: 73vw;
  background-color: #ffff88;
}

.send-msg {
  // position: fixed;
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
@import "../common/style/mixin";
.nav-bar-chat {
  left: 0;
  bottom: 0;
  height: 10px;
  width: 100%;
  padding: 5px 0;
  background: #f0f4f3;
  transform: translateZ(0);
  -webkit-transform: translateZ(0);
  .nav-list {
    width: 100%;
    .fj();
    flex-direction: row;
    padding: 0;
    .nav-list-item {
      display: flex;
      flex: 1;
      flex-direction: column;
      text-align: center;
      color: #666;
      &.router-link-active {
        color: @primary;
      }
      i {
        text-align: center;
        font-size: 22px;
      }
      span {
        font-size: 12px;
      }
      .van-icon-shopping-cart-o {
        margin: 0 auto;
        margin-bottom: 2px;
      }
    }
  }
}
</style>
