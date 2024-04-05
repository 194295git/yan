<template>
  <div>
    <van-uploader
      :after-read="uploadImage"
      accept="image/*"
      capture="camera"
      multiple
      :before-read="beforeRead"
      :max-count="1"
      :max-size="10 * 1024 * 1024"
    />
  </div>
</template>

<script>

import {
uploadPost
} from "@/service/file";
export default {

  methods: {
    beforeRead(){
 // 检测浏览器是否支持getUserMedia API，这是访问摄像头的基础
      const isGetUserMediaSupported = !!(navigator.mediaDevices && navigator.mediaDevices.getUserMedia);
      console.log("是否支持getUserMedi",isGetUserMediaSupported)
      if (!isGetUserMediaSupported) {
        // 如果不支持，则提示用户并返回false阻止文件上传
        this.$message.error('您的浏览器不支持调用摄像头，请升级或更换至支持WebRTC的现代浏览器。');
        return false;
      }

      // 如果支持，则继续执行后续的上传逻辑（如验证文件类型、大小等）
      // ...其他文件验证逻辑...

      // 返回true以允许文件上传
      return true;
    },
    async uploadImage(file) {
      // 创建FormData对象
      const formData = new FormData();
      formData.append('file', file.file);

      // 发送请求到你的上传接口
      // 这里使用fetch作为示例，你可以根据实际情况使用axios等HTTP客户端
   
      console.log("onSendRecord",formData)
      const rs =  await uploadPost(formData);
      console.log(rs)
      if(rs.success){
         this.$emit("send", '3',rs.content);
        console.log("这块需要调用父类的方法 发送消息，然后前端消息体那块也是需要加上audio消息类型的")
      }
    },
  },
};
</script>