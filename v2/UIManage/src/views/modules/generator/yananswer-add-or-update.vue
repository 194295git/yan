<template>
  <el-dialog
    :title="!dataForm.answerId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="queId">
      <el-input v-model="dataForm.queId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="openid">
      <el-input v-model="dataForm.openid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="answerName">
      <el-input v-model="dataForm.answerName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="answerDes">
      <el-input v-model="dataForm.answerDes" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="answerContent">
      <el-input v-model="dataForm.answerContent" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="answerLike">
      <el-input v-model="dataForm.answerLike" placeholder=""></el-input>
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
          answerId: 0,
          queId: '',
          openid: '',
          answerName: '',
          answerDes: '',
          answerContent: '',
          answerLike: ''
        },
        dataRule: {
          queId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          openid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          answerName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          answerDes: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          answerContent: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          answerLike: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.answerId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.answerId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/yananswer/info/${this.dataForm.answerId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.queId = data.yanAnswer.queId
                this.dataForm.openid = data.yanAnswer.openid
                this.dataForm.answerName = data.yanAnswer.answerName
                this.dataForm.answerDes = data.yanAnswer.answerDes
                this.dataForm.answerContent = data.yanAnswer.answerContent
                this.dataForm.answerLike = data.yanAnswer.answerLike
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
              url: this.$http.adornUrl(`/generator/yananswer/${!this.dataForm.answerId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'answerId': this.dataForm.answerId || undefined,
                'queId': this.dataForm.queId,
                'openid': this.dataForm.openid,
                'answerName': this.dataForm.answerName,
                'answerDes': this.dataForm.answerDes,
                'answerContent': this.dataForm.answerContent,
                'answerLike': this.dataForm.answerLike
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
