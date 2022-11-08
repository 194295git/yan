<template>
  <div>
    <div class="title">运维管理主界面</div>
    <div>
      <div class="list">启动sentinel</div>
      <div class="list-auto">
        <div>Redis</div>
        <div class="d-flex">
          <div class="list-half"  @click="startRedis()">启动redis</div>
          <div class="list-half" @click="stopRedis()">停止redis</div>
        </div>
     
      </div>
      
      <div class="list" @click="startRbbit()">启动rabbitmq</div>
      <div class="list-auto">
        <div>微服务占用端口管理</div>
        <div>GatewayApplication  88</div>
        <div>FileApplication 9000</div>
        <div>RenrenApplication 8080</div>
        <div>DevApplication </div>

      </div>
      <div class="list">
        <div>git 合并分支</div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {

    }
  },
  methods: {
    startRedis () {
      this.$http({
        url: this.$http.adornDevUrl(`/dev/containerStart`),
        method: 'get',
        params: this.$http.adornParams({'type': 'redis'})
      }).then(({data}) => {
        console.log(data)
      })
    },
    startRbbit () {
      this.$http({
        url: this.$http.adornDevUrl(`/dev/containerStart`),
        method: 'get',
        params: this.$http.adornParams({'type': 'rabbitmq'})
      }).then(({data}) => {
        console.log(data)
      })
    },
    stopRedis () {
      this.$http({
        url: this.$http.adornDevUrl(`/dev/containerStop`),
        method: 'get',
        params: this.$http.adornParams({'type': 'redis'})
      }).then(({data}) => {
        console.log(data)
      })
    }

  }
}
</script>

<style>
.title {
  padding: 10px 2px;
  font-size: 20px;
}
.nacos{
  width: 80vw;
  height: 80vh;
}
.list{
  line-height:65px;
  width: 600px;
  height: 65px;
  border: 3px solid aqua;
  margin-bottom: 3px;
  padding-left: 15px;
}
.list-half{
  line-height:35px;
  width: 300px;
  height: 35px;
  border: 3px solid blueviolet;
  margin-bottom: 3px;
  padding-left: 15px;
}
.list-auto{
  line-height:25px;
  width: 600px;
 
  border: 3px solid aqua;
  margin-bottom: 3px;
  padding-left: 15px;
}

.d-flex{
  display: flex;
}

.flex-wrap{
  flex-wrap: wrap;
}

.flex-nowrap{
  flex-wrap: nowrap;
}

.flex-column{
  flex-direction: column;
}

.flex-column-reverse{
  flex-direction: column-reverse;
}

.flex-row{
  flex-direction: row;
}

.flex-row-reverse{
  flex-direction: row-reverse;
}

.justify-center{
  justify-content: center;
}

.justify-end{
  justify-content: flex-end;
}
.justify-space-between{
  justify-content: space-between;
}

.align-center{
  align-items: center;
}

.align-top{
  align-items: start;
}

.align-left{
  align-items: start;
}

.flex-1{
  display: flex;
  flex: 1;
}


</style>
