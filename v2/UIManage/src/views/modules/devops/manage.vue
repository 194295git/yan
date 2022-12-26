<template>
  <div>
    <div class="title">运维管理主界面</div>
    <div>
      <div class="list">
        <el-button size="small" type="primary">启动sentinel</el-button>
      </div>
      <div class="list-auto">
        <div>Redis</div>
        <div class="d-flex">
          <el-button v-if="redisStatus == 0" size="small" type="primary"
            >redis启动了</el-button
          >

          <el-button v-if="redisStatus == 1" size="small" type="primary"
            >redis未启动</el-button
          >

          <el-button size="small" type="primary" @click="startRedis()"
            >启动redis</el-button
          >
          <el-button size="small" type="primary" @click="stopRedis()"
            >停止redis</el-button
          >
        </div>
      </div>

      <div class="list">
          <el-button v-if="rabbitmqStatus == 0" size="small" type="primary"
            >rabbitmq启动了</el-button
          >

          <el-button v-if="rabbitmqStatus == 1" size="small" type="primary"
            >rabbitmq未启动</el-button
          >
        <el-button size="small" type="primary" @click="startRbbit()"
          >启动rabbitmq</el-button
        >
        <el-button size="small" type="primary" @click="stopRbbit()"
            >停止rabbitmq</el-button
          >
      </div>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>微服务占用端口管理</span>
          <el-button style="float: right; padding: 3px 0" type="text"
            >操作按钮</el-button
          >
        </div>
        <div
          v-for="(item, index) in microService"
          :key="index"
          class="text item"
        >
          {{ item }}
        </div>
      </el-card>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>docker容器管理</span>
          <el-button style="float: right; padding: 3px 10px" type="text"
          @click="getAllContainer()"
            >获取所有容器</el-button
          >
          
             <el-button style="float: right; padding: 3px 10px" type="text"
          @click="folder('container')"
            >折叠</el-button
          >
        </div>
        <div
          v-for="(item, index) in container"
          :key="index"
          class="text item"
        >
          {{ item.Names }}
        </div>
      </el-card>

      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>docker镜像管理</span>
          <el-button style="float: right; padding: 3px 10px" type="text"
          @click="getAllImages()"
            >获取所有镜像</el-button
          >
            <el-button style="float: right; padding: 3px 10px" type="text"
          @click="folder('image')"
            >折叠</el-button
          >
        </div>
        <div
          v-for="(item, index) in image"
          :key="index"
          class="text item"
        >
          {{ item.RepoTags }}
        </div>
      </el-card>

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
      microService: [
        'GatewayApplication 88',
        'FileApplication 9000',
        'RenrenApplication 8080',
        'DevApplication'
      ],
      redisStatus: 1,
      rabbitmqStatus: 1,
      container: [],
      image: []
    }
  },
  methods: {
    startRedis () {
      var that = this
      this.$http({
        url: this.$http.adornDevUrl(`/dev/containerStart`),
        method: 'get',
        params: this.$http.adornParams({ type: 'redis' })
      }).then(({ data }) => {
        console.log(data)
        that.redisStatus = 0
      })
    },
    startRbbit () {
      var that = this
      this.$http({
        url: this.$http.adornDevUrl(`/dev/containerStart`),
        method: 'get',
        params: this.$http.adornParams({ type: 'rabbitmq' })
      }).then(({ data }) => {
        console.log(data)
        that.rabbitmqStatus = 0
      })
    },
    stopRedis () {
      var that = this
      this.$http({
        url: this.$http.adornDevUrl(`/dev/containerStop`),
        method: 'get',
        params: this.$http.adornParams({ type: 'redis' })
      }).then(({ data }) => {
        console.log(data)
        that.redisStatus = 1
      })
    },
    stopRbbit () {
      var that = this
      this.$http({
        url: this.$http.adornDevUrl(`/dev/containerStop`),
        method: 'get',
        params: this.$http.adornParams({ type: 'rabbitmq' })
      }).then(({ data }) => {
        console.log(data)
        that.rabbitmqStatus = 0
      })
    },
    getAllContainer () {
      var that = this
      this.$http({
        url: this.$http.adornDevUrl(`/dev/containerList`),
        method: 'get'

      }).then(({ data }) => {
        console.log(data.content)
        that.container = data.content
      })
    },
    getAllImages () {
      var that = this
      this.$http({
        url: this.$http.adornDevUrl(`/dev/imageList`),
        method: 'get'

      }).then(({ data }) => {
        console.log(data.content)
        that.image = data.content
      })
    },
    folder (param) {
      console.log(param)
      if (param === 'container') {
        this.container = []
      }
      if (param === 'image') {
        this.image = []
      }
    }
  }
}
</script>

<style>
.title {
  padding: 10px 2px;
  font-size: 20px;
}
.nacos {
  width: 80vw;
  height: 80vh;
}
.list {
  line-height: 65px;
  width: 600px;
  height: 65px;
  border: 3px solid aqua;
  margin-bottom: 3px;
  padding-left: 15px;
}
.list-half {
  line-height: 35px;
  width: 300px;
  height: 35px;
  border: 3px solid blueviolet;
  margin-bottom: 3px;
  padding-left: 15px;
}
.list-auto {
  line-height: 25px;
  width: 600px;

  border: 3px solid aqua;
  margin-bottom: 3px;
  padding-left: 15px;
}

.d-flex {
  display: flex;
}

.flex-wrap {
  flex-wrap: wrap;
}

.flex-nowrap {
  flex-wrap: nowrap;
}

.flex-column {
  flex-direction: column;
}

.flex-column-reverse {
  flex-direction: column-reverse;
}

.flex-row {
  flex-direction: row;
}

.flex-row-reverse {
  flex-direction: row-reverse;
}

.justify-center {
  justify-content: center;
}

.justify-end {
  justify-content: flex-end;
}
.justify-space-between {
  justify-content: space-between;
}

.align-center {
  align-items: center;
}

.align-top {
  align-items: start;
}

.align-left {
  align-items: start;
}

.flex-1 {
  display: flex;
  flex: 1;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}
</style>
