<template>
  <div>
    <div class="title">nacos</div>
    <div>
      <iframe class="nacos" 
      src="http://192.168.56.20:8848/nacos/#/configurationManagement?dataId=&group=&appName=&namespace=&pageSize=&pageNo="></iframe>
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
</style>
