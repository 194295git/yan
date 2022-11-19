<template>
  <div>
    <div class="title">数据库管理</div>
    <div>
      <el-button size="small" type="primary" @click="loadData()" >展示数据(yan_login)</el-button>
       <el-button size="small" type="primary" @click="loadData2()" >展示数据(yan)</el-button>
        <el-button size="small" type="primary" @click="gencode()" >生成代码</el-button>
        <el-button size="small" type="primary" @click="downloadSql()" >下载sql脚本</el-button>
    </div>

    <div>
      <el-table :data="dataList" border 
        @selection-change="selectionChangeHandle"
        style="width: 100%;">
        <el-table-column
          type="selection"
          header-align="center"
          align="center"
          width="50"
        >
        </el-table-column>
        <el-table-column
          prop="tableName"
          header-align="center"
          align="center"
          label="表名"
        >
        </el-table-column>
        <el-table-column
          prop="engine"
          header-align="center"
          align="center"
          label="Engine"
        >
        </el-table-column>
        <el-table-column
          prop="tableComment"
          header-align="center"
          align="center"
          label="表备注"
        >
        </el-table-column>
        <el-table-column
          prop="createTime"
          header-align="center"
          align="center"
          label="创建时间"
        >
        </el-table-column>
      
      
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListSelections: []

    }
  },
  methods: {
        // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
      console.log(72, this.dataListSelections)
    },
    downloadSql () {

    },
    loadData () {
      var that = this
      this.$http({
        url: this.$http.adornDataUrl(`/sys/generator/list`),
        method: 'get',
        params: this.$http.adornParams({ limit: '15', page: '1' })
      }).then(({ data }) => {
        that.dataList = data.page.list
        console.log(that.dataList)
      })
    },
    // 生成压缩包的前端代码
    gencode () {
      let table = ''
      this.dataListSelections.forEach(element => {
        table = table + element.tableName + ','
      })
      console.log(90, table)
      location.href = 'http://localhost:88/api/database/sys/generator/code?tables=' + table
    },
    loadData2 () {
      var that = this
      this.$http({
        url: this.$http.adornDataUrl(`/sys/generator/list2`),
        method: 'get',
        params: this.$http.adornParams({ limit: '15', page: '1' })
      }).then(({ data }) => {
        that.dataList = data.page.list
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
</style>
