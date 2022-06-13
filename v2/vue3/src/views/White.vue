<!--
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2020 陈尼克 all rights reserved.
 * 版权所有，侵权必究！
 *
-->

<template>
  <div class="user-box">
    <s-header :name="'问题'"></s-header>
    
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import { reactive, onMounted, toRefs } from 'vue'
import navBar from '@/components/NavBar'
import sHeader from '@/components/SimpleHeader'
import { getUserInfo } from '@/service/user'
import { useRouter } from 'vue-router'
export default {
  components: {
    navBar,
    sHeader
  },
  setup() {
    const router = useRouter()
    const state = reactive({
      user: {},
      loading: true
    })

    onMounted(async () => {
      const  data  = await getUserInfo()
      state.user = data.content
      state.loading = false
    })

    const goBack = () => {
      router.go(-1)
    }

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} })
    }

    return {
      ...toRefs(state),
      goBack,
      goTo
    }
  }
}
</script>

<style lang="less" scoped>
  @import '../common/style/mixin';
  @import "../common/style/vuetify";

</style>
