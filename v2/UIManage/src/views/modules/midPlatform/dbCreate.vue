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

    <div style="width: 600px">
      <vue-table-dynamic
        :params="params"
        @cell-change="onCellChange"
        ref="table"
      >
      </vue-table-dynamic>
    </div>

  </div>
</template>
<script>

import VueTableDynamic from 'vue-table-dynamic'
export default {
  data () {
    return {
      params: {
        data: [
          ['Index', 'Data1', 'Data2', 'Data3'],
          [1, 'b3ba90', '7c95f7', '9a3853'],
          [2, 'ec0b78', 'ba045d', 'ecf03c'],
          [3, '63788d', 'a8c325', 'aab418']
        ],
        header: 'row',
        edit: {
          row: [1],
          column: [1],
          cell: [[-1, -1]]
        }
      },
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
    VueTableDynamic
  },
  methods: {
    onCellChange (rowIndex, columnIndex, data) {
      console.log('onCellChange: ', rowIndex, columnIndex, data)
      console.log('table data: ', this.$refs.table.getData())
    },
    onSubmit () {
      console.log('submit!')
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {

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
