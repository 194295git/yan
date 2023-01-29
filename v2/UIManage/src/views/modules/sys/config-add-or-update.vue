<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="key" prop="paramKey">
      <el-input v-model="dataForm.paramKey" placeholder="key"></el-input>
    </el-form-item>
    <el-form-item label="value" prop="paramValue">
      <el-input v-model="dataForm.paramValue" placeholder="value"></el-input>
    </el-form-item>
    <el-form-item label="状态   0：隐藏   1：显示" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态   0：隐藏   1：显示"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="父id" prop="pid">
      <el-input v-model="dataForm.pid" placeholder="父id"></el-input>
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
          paramKey: '',
          paramValue: '',
          status: '',
          remark: '',
          pid: ''
        },
        dataRule: {
          paramKey: [
            { required: true, message: 'key不能为空', trigger: 'blur' }
          ],
          paramValue: [
            { required: true, message: 'value不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态   0：隐藏   1：显示不能为空', trigger: 'blur' }
          ],
          remark: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ],
          pid: [
            { required: true, message: '父id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUserUrl(`/sys/config/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              console.log(data, 74)
              if (data && data.code === 0) {
                this.dataForm.paramKey = data.config.paramKey
                this.dataForm.paramValue = data.config.paramValue
                this.dataForm.status = data.config.status
                this.dataForm.remark = data.config.remark
                this.dataForm.pid = data.config.pid
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
              url: this.$http.adornUserUrl(`/sys/config/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'paramKey': this.dataForm.paramKey,
                'paramValue': this.dataForm.paramValue,
                'status': this.dataForm.status,
                'remark': this.dataForm.remark,
                'pid': this.dataForm.pid
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
