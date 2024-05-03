

<template>
  <div class="nav-bar van-hairline--top">
    <ul class="nav-list">
      <router-link  class="nav-list-item active" to="chat">
        <i class="nbicon nblvsefenkaicankaoxianban-1"></i>
        <span>消息</span>
      </router-link>
      <router-link  class="nav-list-item" to="info">
        <i class="nbicon nbfenlei"></i>
        <span>通讯录</span>
      </router-link>
      <router-link  class="nav-list-item" to="home">
         <i class="nbicon nbfenlei"></i>
        <!-- <van-icon  name="shopping-cart-o" :badge="!count ? '' : count" /> -->
        <span>资讯</span>
      </router-link>
      <router-link  class="nav-list-item" to="me">
        <i class="nbicon nblvsefenkaicankaoxianban-"></i>
        <span>我的</span>
      </router-link>
    </ul>
  </div>
</template>

<script>
import { onMounted, computed } from 'vue'
// import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
// import { getLocal } from '@/common/js/utils'
export default {
  setup() {
    // const route = useRoute()
    const store = useStore()
    onMounted(() => {
      // const token = getLocal('token')
      // const path = route.path
      // if (token && !['/home', '/category'].includes(path)) {
      //   store.dispatch('updateCart')
      // }
    })
    const count = computed(() => {
      return store.state.cartCount
    })

    return {
      count
    }
  }
}
</script>

<style lang="less" scoped >
    @import '../common/style/mixin';
    .nav-bar{
      position: fixed;
      left: 0;
      bottom: 0;
      width: 100%;
      padding: 5px 0;
      z-index: 1000;
      background: #F0F4F3;
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
          span{
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
