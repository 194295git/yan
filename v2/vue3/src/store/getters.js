export default {

  findChatIdx: (state) => (chat) => {
    for (let idx in state.chats) {
  
      if ( state.chats[idx].targetId !=undefined&&
        state.chats[idx].targetId === chat.targetId) {
        chat = state.chats[idx];
        return idx
      }
    }
  },
  findChat: (state) => (msgInfo) => {
    // 获取对方id或群id
    let type = msgInfo.group =='1' ?  'PRIVATE': 'GROUP' ;
    let targetId = null;
    console.log("findChat state.groupId",state.groupId,"==",type)
    if(type == 'GROUP' ){
      targetId = state.groupId
    }else{
      //单聊情况
      targetId = state.toUser.openid
    }
    //对方的id存储在state 里面
  
    let chat = null;
    for (let idx in state.chats) {
      if (state.chats[idx].type == type &&
        state.chats[idx].targetId == targetId) {
        chat = state.chats[idx];
        console.log("成功找到chat",chat)
        break;
      }
    }
    return chat;
  },
  findChatByOpenid: (state) => (targetId) => {
    // 获取对方id或群id
  
    let chat = null;
    for (let idx in state.chats) {
      if (
        state.chats[idx].targetId === targetId) {
        chat = state.chats[idx];
        break;
      }
    }
    return chat;
  },


}
