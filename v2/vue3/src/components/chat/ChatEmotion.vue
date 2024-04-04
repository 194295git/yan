<template>
  <div  class="emoji-panel">
    <van-grid column-num="5">
      <van-grid-item
        v-for="(emoji, index) in emojis"
        :key="index"
        @click="addEmoji(emoji)"
      >
        <van-image
          v-if="emoji.src"
          width="35px"
          height="35px"
          fit="cover"
          :src="emoji.src"
        />
        <div v-if="!emoji.src">{{ emoji.name }}</div>
        <!-- <img :src="emoji.src" :alt="emoji.name" class="emoji-img" /> -->
      </van-grid-item>
    </van-grid>
  </div>
</template>
<script>
export default {
  props: {
    // 声明接收名为 'emojis' 的 prop，类型为数组
    emojis: {
      type: Array,
      required: true,
    },
  },
  methods: {
    // 子组件内的 addEmoji 方法
    addEmoji(emoji) {
      // ... 这里可能有一些逻辑判断或数据准备
      // 调用父组件方法的间接方式：触发自定义事件
      this.$emit("add-emoji", emoji);
    },
  },
};
</script>
