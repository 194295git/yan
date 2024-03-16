
// import { getCart } from '../service/cart'
import {
  getChatContentAll,
} from "@/service/chat";
export default {
  async pullOffline(ctx) {
    // 获取当前store中的privateMsgMaxId
    const privateMsgMaxId = ctx.state.privateMsgMaxId+"";
    console.log("privateMsgMaxId",ctx.state.privateMsgMaxId)
    const res = await getChatContentAll(privateMsgMaxId);
    const contentAll = res.content
    for (var i = 0; i < contentAll.length; i++) {
   
      ctx.commit("initInsertMessage", contentAll[i]); 
    }
   
  }
}
