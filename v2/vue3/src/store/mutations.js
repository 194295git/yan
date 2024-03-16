// import {
// 	MESSAGE_TYPE,
// 	MESSAGE_STATUS
// } from "../common/js/enums"

export default {
  addCart(state, payload) {
    state.cartCount = payload.count;
  },
  setToAsQue(state) {
    state.to = "que";
  },
  setToAsItem(state) {
    state.to = "item";
  },
  setQueQuestion(state, res) {
    state.que = res;
  },
  setUserInfo(state, res) {
    state.userInfo = res || null;
  },
  setToUser(state, res) {
    state.toUser = res || null;
  },
  setGroupId(state, res) {
    state.groupId = res || null;
  },
  
  setRegisterGroup(state, res) {
    state.registerGroup = res || null;
  },
  //初始化一下state 里面的chat数据，从localstorgae里面去出来，然后放到state里面去
  initChat(state) {
    console.log("initChat", state.userInfo.openid);
    let userId = state.userInfo.openid;
    let key = "chats-" + userId;
    let item = localStorage.getItem(key);
    if (item) {
      let chatsData = JSON.parse(item);
      state.chats = chatsData.chats || [];
      state.privateMsgMaxId = chatsData.privateMsgMaxId || 0;
      state.groupMsgMaxId = chatsData.groupMsgMaxId || 0;
    } else {
      //第一次进入则设置一个空的 ；
      this.commit("saveToStorage");
    }
  },
  /**
   * 打开会话，会话不存在则创建会话.
   * @param {*} state
   * @param {*} chatInfo
   */
  openChat(state, chatInfo) {
    let chat = null;
    for (let idx in state.chats) {
      if (
        state.chats[idx].type == chatInfo.type &&
        state.chats[idx].targetId === chatInfo.targetId
      ) {
        chat = state.chats[idx];
        // 放置头部
        this.commit("moveTop", idx);
        break;
      }
    }
    // 创建会话
    if (chat == null) {
      chat = {
        targetId: chatInfo.targetId,
        type: chatInfo.type,
        showName: chatInfo.showName,
        headImage: chatInfo.headImage,
        lastContent: "",
        lastSendTime: new Date().getTime(),
        unreadCount: 0,
        messages: [],
        atMe: false,
        atAll: false,
      };
      state.chats.unshift(chat);
    }
  },
  createChat(state, chatInfo) {
    let chat = null;
    chatInfo.group
    let type = chatInfo.group=='1' ? "PRIVATE":"GROUP" ;
    chat = {
      targetId: chatInfo.targetId,
      type: type,
      showName: chatInfo.showName,
      headImage: chatInfo.headImage,
      lastContent: "",
      lastSendTime: new Date().getTime(),
      unreadCount: 0,
      messages: [],
      atMe: false,
      atAll: false,
    };
    state.chats.unshift(chat);
  },
  
  moveTop(state, idx) {
    // 加载中不移动，很耗性能
    if (state.loadingPrivateMsg || state.loadingGroupMsg) {
      return;
    }
    if (idx > 0) {
      let chat = state.chats[idx];
      state.chats.splice(idx, 1);
      state.chats.unshift(chat);
      this.commit("saveToStorage");
    }
  },
  /**
   *  state.chats 将更新后的存储.
   * @param {*} state
   */
  saveToStorage(state) {
    let userId = state.userInfo.openid;
    let key = "chats-" + userId;
    let chatsData = {
      privateMsgMaxId: state.privateMsgMaxId,
      groupMsgMaxId: state.groupMsgMaxId,
      chats: state.chats,
    };
    localStorage.setItem(key, JSON.stringify(chatsData));
  },

  /**
   * 插入消息.
   * @param {*} state
   * @param {*} msgInfo 当前消息
   */
  insertMessage(state, msgInfo) {
      state.privateMsgMaxId = msgInfo.msgId;
      state.groupMsgMaxId = msgInfo.msgId;
    // 如果是已存在消息，则覆盖旧的消息数据
    let chat = this.getters.findChat(msgInfo);
    if (chat == null) {
      this.commit("createChat", msgInfo);
      chat = this.getters.findChat(msgInfo);
    }
    if(chat == null){
      console.log("没有找到chat",chat);
      return;
    }
    chat.messages.push(msgInfo);
    this.commit("saveToStorage");
  },

  /**
   * 插入消息.
   * @param {*} state
   * @param {*} msgInfo 当前消息
   */
  initInsertMessage(state, msgInfo) {
    // 如果是已存在消息，则覆盖旧的消息数据
    let chat = this.getters.findChatByOpenid(msgInfo.targetId);
    if (chat == null) {
      this.commit("createChat", msgInfo);
      chat = this.getters.findChatByOpenid(msgInfo.targetId);
    }
    //更新最大消息数量.
    if (msgInfo.msgId && +msgInfo.msgId > +state.privateMsgMaxId) {
      console.log("更新")
      state.privateMsgMaxId = msgInfo.msgId;
    }

   
    chat.messages.push(msgInfo);
    console.log("插入消息msgInfo  state.groupMsgMaxId ", msgInfo,state.groupMsgMaxId );
    this.commit("saveToStorage");
  },
};
