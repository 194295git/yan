<template>
  <div>
    <div class="font-title">线上数据库管理系统</div>
    <el-form ref="form" :model="form" label-width="80px">
      
      <el-form-item>
        <el-button type="primary" @click="addOrUpdateHandle()">新建查询</el-button>
         <el-button type="primary" @click="downloadSql()">下载sql</el-button>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>

    <div>
      <el-table
        :data="dataList"
        border
        @selection-change="selectionChangeHandle"

      >
        
        <el-table-column
          v-for="(item, index) in  dataColumn" :key="index"
          :prop="item.columnName"
          header-align="center"
          align="center"
          :label="item.columnComment"
          >
        </el-table-column>  
       
      </el-table>
    </div>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update
    style="magrin-left:250px"
      v-if="addOrUpdateVisible"
      ref="addOrUpdate"
      @refreshDataList="getDataList"
    ></add-or-update>
  </div>
</template>
<script>
import AddOrUpdate from './dbCreateUpdate.vue'
export default {
  data () {
    return {
      dataColumn: [],
      tableName: '',
      dataSource: '',
      allColumn: [],
      form: {
        dbname: '',
        tbname: '',
        tbcomment: ''

      },
      dataList: [],
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  created () {
    this.init()
    this.getColumnList()
    this.getDataList()
  },
  methods: {
    init () {
      this.tableName = this.$route.query.tableName
      this.dataSource = this.$route.query.dataSource
    },

    onSubmit () {
      console.log('submit!')
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    getColumnList () {
      var that = this
      this.$http({
        url: this.$http.adornDataUrl(`/sys/generator/showColumn`),
        method: 'get',

        params: this.$http.adornParams({ tables: that.tableName, dataSource: that.dataSource })
      }).then(({ data }) => {
        that.dataColumn = data.page
        console.log(data)
      })
    },
    getDataList () {
      var that = this
      this.$http({
        url: this.$http.adornDataUrl(`/sys/generator/listTableData`),
        method: 'get',

        params: this.$http.adornParams({ limit: '40', page: '1', tableName: that.tableName, dataSource: that.dataSource })
      }).then(({ data }) => {
        that.dataList = data.page
        console.log(108, 'getDataList', data)
      })
    },
    selectionChangeHandle () {

    },
    downloadSql () {

    }
  }
}
</script>
<style scoped>
.font-title{
  font-size: 20px;
  margin:15px;
}
</style>
