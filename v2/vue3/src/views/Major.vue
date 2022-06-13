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
    <s-header :name="'专业'"></s-header>
    <div class="container">
      <div class="watch_list">
        <div
          class="list_major"
          v-for="(item, index) in major"
          :key="index"
          :id="major[index].class"
          @click="major_son_watch(index)"
        >
          <div class="curtain"></div>
          <div class="major_name">{{ item.name }}</div>
          <div class="major_code">code: {{ item.code }}</div>
        </div>
      </div>
    </div>

    <nav-bar></nav-bar>
  </div>
</template>

<script>
import { reactive, onMounted, toRefs } from "vue";
import navBar from "@/components/NavBar";
import sHeader from "@/components/SimpleHeader";
import { getUserInfo } from "@/service/user";
import { useRouter } from "vue-router";
export default {
  components: {
    navBar,
    sHeader,
  },
  setup() {
    const router = useRouter();
    const state = reactive({
      user: {},
      loading: true,
      major: [
        { name: '哲学', code: '01',id:'1' ,class:'major1'},
        { name: '经济学', code: '02', id: '2',class:'major2'},
        { name: '法学', code: '03', id: '3',class:'major3'},
        { name: '教育学', code: '04', id: '4',class:'major4'},
        { name: '文学', code: '05', id: '5',class:'major5'},
        { name: '历史学', code: '06', id: '6',class:'major6'},
        { name: '理学', code: '07', id: '7',class:'major7'},
        { name: '工学', code: '08', id: '8',class:'major8'},
        { name: '农学', code: '09', id: '9',class:'major9'},
        { name: '医学', code: '10', id: '10',class:'major10'},
        { name: '军事学', code: '11', id: '11',class:'major11'},
        { name: '管理学', code: '12', id: '12' ,class:'major12'},
        { name: '艺术学', code: '13', id: '13' ,class:'major13'},
      ]
  
    });

    onMounted(async () => {
      const data = await getUserInfo();
      state.user = data.content;
      state.loading = false;
    });

    const goBack = () => {
      router.go(-1);
    };

    const goTo = (r, query) => {
      router.push({ path: r, query: query || {} });
    };
    const major_son_watch = (index)=>{
      router.push({ path: '/majorWatchSon', query: {  code: state.major[index].id}})
    }

    return {
      ...toRefs(state),
      goBack,
      major_son_watch,
      goTo,
    };
  },
};
</script>

<style lang="less" scoped>
@import "../common/style/mixin";
@import "../common/style/vuetify";
.container{
  width:375px;
  display: flex;
  justify-content: center;
  margin-bottom: 100px;
}

.watch_list{
  display: flex;
  width: 315px;
  flex-wrap: wrap;
  justify-content: space-between
}

.list_major{
  width: 150px;
  height: 90px;
  background: black;
  margin-top: 10px;
  border-radius: 10px;
  color: #fff;
  display: flex;
  justify-content: center;
  flex-direction: column;
  position: relative;
  overflow: hidden
}
.curtain{
  position: absolute;
  width: 100%;
  height: 100%;
}

.major_name,.major_code{
  text-align: center;
  position: relative;
  z-index: 3;
  font-weight: 800;
}

#major1{

  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 30%;
}

.major_code{
  font-size: 18px;
  font-weight: 800;
}

#major2{

  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 100%;
}

#major2 .curtain{
   background: linear-gradient(120deg, rgba(245, 122, 178, 0.3), #f4cda5);
   z-index: 1;
}

#major2 .major_name{
  font-weight: 800;
  font-size: 20px;
}

#major3{

  background-repeat: no-repeat;
  background-size: 100%;
  background-position: 50% 30%;
}

#major4 .curtain{
   background: linear-gradient(120deg, rgba(85, 196, 184, 0.3), #f4cda5);
   z-index: 1;
}

#major4{

  background-repeat: no-repeat;
  background-size: 150%;
  background-position: 50% 80%;
}

#major5{

  background-repeat: no-repeat;
  background-size: 100%;
  background-position: 50% 80%;
}

#major5 .curtain{
   background: linear-gradient(120deg,#f4cda5, rgba(85, 196, 184, 0.3));
   z-index: 1;
}
#major5 .major_name{
  font-weight: 800;
  font-size: 20px;
}

#major6{
  
  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 0%;
}

#major7{

  background-repeat: no-repeat;
  background-size: 150%;
  background-position: 50% 10%;
}

#major8{
 
  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 10%;
}

#major9{

  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 10%;
}

#major10{
 
  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 10%;
}

#major10 .curtain{
   background: linear-gradient(120deg,#f4cda5, rgba(85, 196, 184, 0.3));
   z-index: 1;
}

#major11{

  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 10%;
}

#major11 .curtain{
   background: linear-gradient(120deg,#F8D17E, rgba(237, 82, 118, 0.3));
   z-index: 1;
}

#major12{

  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 10%;
}

#major12 .curtain, #major1 .curtain,#major3 .curtain,#major6 .curtain,#major8 .curtain,#major7 .curtain,#major9 .curtain{
   background: linear-gradient(120deg,rgba(60, 152, 158, 0.3),rgba(237, 82, 118, 0.3));
   z-index: 1;
}

#major13{
  /*background-image: url(http://www.mtdg.club/wwwroot/ftp/裕祺/images/gaokao/major12.jpg);*/
  background-repeat: no-repeat;
  background-size: 120%;
  background-position: 50% 10%;
}
</style>
