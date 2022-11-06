<template>
  <div>
    <div class="title">运维管理主界面</div>
    <div>
      <div class="list">启动sentinel</div>
      <div class="list">启动redis</div>
      <div class="list">启动rabbitmq</div>
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
.list-auto{
  line-height:25px;
  width: 600px;
 
  border: 3px solid aqua;
  margin-bottom: 3px;
  padding-left: 15px;
}

</style>
