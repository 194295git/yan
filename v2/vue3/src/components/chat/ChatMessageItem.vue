<template>
  <div class="d-felx justify-start " v-if="item.type === 'self'">
    <div style="display: flex;">
      <van-image
        width="35px"
        height="35px"
        fit="cover"
        :src="userInfo.avatarUrl"
      />
      <div
        class="font-18 content1"
        @mousedown.prevent="startPress"
        @mouseup="stopPress"
        @mouseleave="stopPress"
      >
        <div ref="contentArea" v-html="decodeCodeToEmoji(item.content)"></div>
      </div>
      <div
        class="context-menu"
        v-if="isContextMenuVisible"
        @click.self="hideContextMenu"
      >
        <!-- 这里放置你的菜单项，例如：引用、收藏、制作表情包等 -->
        <button @click="quoteMessage">引用消息</button>
        <button @click="collectMessage">收藏</button>
        <button @click="makeSticker">制作表情包</button>
      </div>
    </div>
  </div>
  <div
    style="display: flex; justify-content: flex-end;"
    v-if="item.type === 'receive'"
  >
    <div class="font-18 content2">
      <div v-html="decodeCodeToEmoji(item.content)"></div>
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
</template>

<script>
import { reactive, toRefs } from "vue";
export default {
  props: {
    // 声明接收名为 'emojis' 的 prop，类型为数组
    item: {
      type: Object,
      required: true,
    },
    userInfo: {
      type: Object,
      required: true,
    },
     toUser: {
      type: Object,
      required: true,
    },
    emojis: {
      type: Array,
      required: true,
    },
  },

  setup(props) {
    
    const state = reactive({
      //长按事件
      pressTimer: null,
      isContextMenuVisible: false,
      longPressDuration: 1000, // 设定长按时间阈值（毫秒）
      currentLongPressedItem: null,
    });
    const startPress = function(event) {
      console.log("startPress");
      clearTimeout(state.pressTimer);
      state.pressTimer = setTimeout(() => {
        showContextMenu(event, this.item);
      }, state.longPressDuration);
    };
    const stopPress = function() {
      clearTimeout(this.pressTimer);
      hideContextMenu();
    };
    const showContextMenu = function(event, item) {
      state.isContextMenuVisible = true;
      state.currentLongPressedItem = item;
      // 设置context menu的位置
      const rect = this.$refs.contentArea.getBoundingClientRect();
      this.contextMenuTop = event.clientY - rect.top + window.pageYOffset;
      this.contextMenuLeft = event.clientX - rect.left + window.pageXOffset;
    };
    const hideContextMenu = function() {
      state.isContextMenuVisible = false;
    };
 // 将特定编码转换为<img>标签用于显示
    const decodeCodeToEmoji = function(message) {
      const avatarRegex = /\[emoji:avatar(\d+)?\]/g;
      let match;
      const avatars = props.emojis;
      while ((match = avatarRegex.exec(message)) !== null) {
        // const avatarIndex = match[1]; // 获取头像编号 // 获取头像自定义标识符（"avatar" 或 "avatar2"）
        const avatar = avatars.find((a) => a.encode === match[0]); // 查找对应的头像信息
        if (avatar) {
          message = message.replace(
            match[0],
            `<img src="${avatar.src}"   style="width: 25px; height: 25px; border-radius: 50%;" alt="${avatar.name}" />`
          );
        }
      }
      return message;
    };
    return {
      ...toRefs(state),
decodeCodeToEmoji,
      stopPress,
      startPress,
      hideContextMenu,
      showContextMenu,
    };
  },
};
</script>

<style></style>
