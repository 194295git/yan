<template>
  <el-dialog
    :title="!dataForm.index ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="code1">
      <el-input v-model="dataForm.code1" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="direction">
      <el-input v-model="dataForm.direction" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="name">
      <el-input v-model="dataForm.name" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="nizhao">
      <el-input v-model="dataForm.nizhao" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="school">
      <el-input v-model="dataForm.school" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="shuju">
      <el-input v-model="dataForm.shuju" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tongzhao">
      <el-input v-model="dataForm.tongzhao" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tuimian">
      <el-input v-model="dataForm.tuimian" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="xh">
      <el-input v-model="dataForm.xh" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="yuan">
      <el-input v-model="dataForm.yuan" placeholder=""></el-input>
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
          index: 0,
          code1: '',
          direction: '',
          name: '',
          nizhao: '',
          school: '',
          shuju: '',
          tongzhao: '',
          tuimian: '',
          xh: '',
          yuan: ''
        },
        dataRule: {
          code1: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          direction: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          nizhao: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          school: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          shuju: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tongzhao: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tuimian: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          xh: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          yuan: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.index = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.index) {
            this.$http({
              url: this.$http.adornUrl(`/generator/yancollege/info/${this.dataForm.index}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.code1 = data.yanCollege.code1
                this.dataForm.direction = data.yanCollege.direction
                this.dataForm.name = data.yanCollege.name
                this.dataForm.nizhao = data.yanCollege.nizhao
                this.dataForm.school = data.yanCollege.school
                this.dataForm.shuju = data.yanCollege.shuju
                this.dataForm.tongzhao = data.yanCollege.tongzhao
                this.dataForm.tuimian = data.yanCollege.tuimian
                this.dataForm.xh = data.yanCollege.xh
                this.dataForm.yuan = data.yanCollege.yuan
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
              url: this.$http.adornUrl(`/generator/yancollege/${!this.dataForm.index ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'index': this.dataForm.index || undefined,
                'code1': this.dataForm.code1,
                'direction': this.dataForm.direction,
                'name': this.dataForm.name,
                'nizhao': this.dataForm.nizhao,
                'school': this.dataForm.school,
                'shuju': this.dataForm.shuju,
                'tongzhao': this.dataForm.tongzhao,
                'tuimian': this.dataForm.tuimian,
                'xh': this.dataForm.xh,
                'yuan': this.dataForm.yuan
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
