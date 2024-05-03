

<template>
  <div class="login">
    <img class="logo" src="https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/26/a10016be5a4348a885bc79bb7bf78ad4logo1.png" alt="">
    <div v-if="type == 'login'" class="login-body login">
      <van-form @submit="onSubmit">
        <van-field
          v-model="username"
          name="username"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
        />
        <van-field
          v-model="password"
          type="password"
          name="password"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
        />

        <div style="margin: 16px;">
          <div class="link-register" @click="toggle('register')">立即注册</div>
          <van-button round block color="#1baeae" native-type="submit">登录</van-button>
        </div>
      </van-form>
    </div>
    <div v-else class="login-body register">
      <van-form @submit="onSubmit">
        <van-field
          v-model="username1"
          name="username1"
          label="昵称"
          placeholder="昵称"
          :rules="[{ required: true, message: '请填写昵称' }]"
        />
        <van-field
          v-model="email1"
          name="email1"
          label="邮箱"
          placeholder="邮箱"
          :rules="[{ required: true, message: '请填写邮箱' }]"
        />
        <van-field
          v-model="password1"
          type="password"
          name="password1"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
        />
         
       
        <div style="margin: 16px;">
          <div class="link-login" @click="toggle('login')">已有登录账号</div>
          <van-button round block color="#1baeae" native-type="submit">注册</van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from 'vue'
import { login, register } from '@/service/user'
import { setLocal } from '@/common/js/utils'
import { Toast } from 'vant'
export default {
  setup() {
    const state = reactive({
      username: '',
      password: '',
      email1: '',
      username1: '',
      password1: '',
      type: 'login',
      verify: ''
    })

    // 切换登录和注册两种模式
    const toggle = (v) => {
      state.type = v
      state.verify = ''
    }
  
    // 提交登录或注册表单
    const onSubmit = async (values) => {
   
      if (state.type == 'login') {
        const  data  = await login({
          "username": values.username,
          "password": values.password
        })
        console.log("token",data)
        setLocal('token', data.content)
        // getUserInfo();
        // 需要刷新页面，否则 axios.js 文件里的 token 不会被重置
        window.location.href = '/'
        
      } else {
        await register({
          // "username": values.username1,
          "password": values.password1,
          "username":values.email1
        })
        Toast.success('注册成功')
        state.type = 'login'
        state.verify = ''
      }
    }
    // const getUserInfo =async ()=>{
    //   const res =await getUserInfoMe()
    //   console.log("==========getUserInfo")
    //   console.log(res.content)
    //   store.$store.commit("setUserInfo", res.content)

    // }



    return {
      ...toRefs(state),
      toggle,
      onSubmit,
  
    }
  }

}
</script>

<style lang="less">
  .login {
    .logo {
      width: 120px;
      height: 120px;
      display: block;
      margin: 80px auto 20px;
    }
    .login-body {
      padding: 0 20px;
    }
    .login {
      .link-register {
        font-size: 14px;
        margin-bottom: 20px;
        color: #1989fa;
        display: inline-block;
      }
    }
    .register {
      .link-login {
        font-size: 14px;
        margin-bottom: 20px;
        color: #1989fa;
        display: inline-block;
      }
    }
    .verify-bar-area {
      margin-top: 24px;
      .verify-left-bar {
        border-color: #1baeae;
      }
      .verify-move-block {
        background-color: #1baeae;
        color: #fff;
      }
    }
    .verify {
      >div {
        width: 100%;
      }
      display: flex;
      justify-content: center;
      .cerify-code-panel {
        margin-top: 16px;
      }
      .verify-code {
        width: 40%!important;
        float: left!important;
      }
      .verify-code-area {
        float: left!important;
        width: 54%!important;
        margin-left: 14px!important;
        .varify-input-code {
          width: 90px;
          height: 38px!important;
          border: 1px solid #e9e9e9;
          padding-left: 10px;
          font-size: 16px;
        }
        .verify-change-area {
          line-height: 44px;
        }
      }
    }
  }
</style>
