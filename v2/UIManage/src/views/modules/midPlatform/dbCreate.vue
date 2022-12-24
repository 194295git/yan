<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="库名">
        <el-input v-model="form.dbname"></el-input>
      </el-form-item>
      <el-form-item label="表名">
        <el-input v-model="form.tbname"></el-input>
      </el-form-item>
      <el-form-item label="表注释">
        <el-input v-model="form.tbcomment"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
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
        style="width: 100%;"
      >
        <el-table-column
          type="selection"
          header-align="center"
          align="center"
          width="50"
        >
        </el-table-column>
        <el-table-column
          prop="fieldName"
          header-align="center"
          align="center"
          label="名"
        >
        </el-table-column>
        <el-table-column
          prop="type"
          header-align="center"
          align="center"
          label="类型"
        >
        </el-table-column>
        <el-table-column
          prop="length"
          header-align="center"
          align="center"
          label="长度"
        >
        </el-table-column>
        <el-table-column
          prop="decimalPoint"
          header-align="center"
          align="center"
          label="小数点"
        >
        </el-table-column>
        <el-table-column
          prop="isNull"
          header-align="center"
          align="center"
          label="是否NUll"
        >
        </el-table-column>
        <el-table-column
          prop="key"
          header-align="center"
          align="center"
          label="键"
        >
        </el-table-column>
        <el-table-column
          prop="fieldComment"
          header-align="center"
          align="center"
          label="注释"
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
  methods: {
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
    getDataList () {

    },
    selectionChangeHandle () {

    },
    downloadSql () {

    }
  }
}
</script>
