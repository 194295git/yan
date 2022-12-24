<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="字段类型" prop="fieldtype">
      <el-input v-model="dataForm.fieldtype" placeholder="字段类型"></el-input>
    </el-form-item>
    <el-form-item label="字段名" prop="fieldname">
      <el-input v-model="dataForm.fieldname" placeholder="字段名"></el-input>
    </el-form-item>
    <el-form-item label="长度" prop="length">
      <el-input v-model="dataForm.length" placeholder="长度"></el-input>
    </el-form-item>
    <el-form-item label="是否null
是否null" prop="isnull">
      <el-input v-model="dataForm.isnull" placeholder="是否null
是否null"></el-input>
    </el-form-item>
    <el-form-item label="小数点" prop="decimalpoint">
      <el-input v-model="dataForm.decimalpoint" placeholder="小数点"></el-input>
    </el-form-item>
    <el-form-item label="键" prop="fieldkey">
      <el-input v-model="dataForm.fieldkey" placeholder="键"></el-input>
    </el-form-item>
    <el-form-item label="注释" prop="fieldcomment">
      <el-input v-model="dataForm.fieldcomment" placeholder="注释"></el-input>
    </el-form-item>

    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          fieldtype: '',
          fieldname: '',
          length: '',
          isnull: '',
          decimalpoint: '',
          fieldkey: '',
          fieldcomment: ''

        },
        dataRule: {
          fieldtype: [
            { required: true, message: '字段类型不能为空', trigger: 'blur' }
          ],
          fieldname: [
            { required: true, message: '字段名不能为空', trigger: 'blur' }
          ],
          length: [
            { required: true, message: '长度不能为空', trigger: 'blur' }
          ],
          isnull: [
            { required: true, message: '是否null是否null不能为空', trigger: 'blur' }
          ],
          decimalpoint: [
            { required: true, message: '小数点不能为空', trigger: 'blur' }
          ],
          fieldkey: [
            { required: true, message: '键不能为空', trigger: 'blur' }
          ],
          fieldcomment: [
            { required: true, message: '注释不能为空', trigger: 'blur' }
          ]
  
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornDataUrl(`/generator/yandbtable/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.fieldtype = data.yanDbTable.fieldtype
                this.dataForm.fieldname = data.yanDbTable.fieldname
                this.dataForm.length = data.yanDbTable.length
                this.dataForm.isnull = data.yanDbTable.isnull
                this.dataForm.decimalpoint = data.yanDbTable.decimalpoint
                this.dataForm.fieldkey = data.yanDbTable.fieldkey
                this.dataForm.fieldcomment = data.yanDbTable.fieldcomment
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornDataUrl(`/generator/yandbtable/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'fieldtype': this.dataForm.fieldtype,
                'fieldname': this.dataForm.fieldname,
                'length': this.dataForm.length,
                'isnull': this.dataForm.isnull,
                'decimalpoint': this.dataForm.decimalpoint,
                'fieldkey': this.dataForm.fieldkey,
                'fieldcomment': this.dataForm.fieldcomment
  
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
