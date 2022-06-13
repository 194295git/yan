<template>
  <el-dialog
    :title="!dataForm.questionId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id" prop="openid">
      <el-input v-model="dataForm.openid" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="主标题" prop="queTitle">
      <el-input v-model="dataForm.queTitle" placeholder="主标题"></el-input>
    </el-form-item>
    <el-form-item label="问题副标题" prop="queContent">
      <el-input v-model="dataForm.queContent" placeholder="问题副标题"></el-input>
    </el-form-item>
    <el-form-item label="标签的id" prop="queTagId">
      <el-input v-model="dataForm.queTagId" placeholder="标签的id"></el-input>
    </el-form-item>
    <el-form-item label="问题阅读数量" prop="queView">
      <el-input v-model="dataForm.queView" placeholder="问题阅读数量"></el-input>
    </el-form-item>
    <el-form-item label="问题收藏数量" prop="queCollect">
      <el-input v-model="dataForm.queCollect" placeholder="问题收藏数量"></el-input>
    </el-form-item>
    <el-form-item label="问题的展示图片" prop="feedSourceImg">
      <el-input v-model="dataForm.feedSourceImg" placeholder="问题的展示图片"></el-input>
    </el-form-item>
    <el-form-item label="" prop="queComment">
      <el-input v-model="dataForm.queComment" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="queAnswerListId">
      <el-input v-model="dataForm.queAnswerListId" placeholder=""></el-input>
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
          questionId: 0,
          openid: '',
          queTitle: '',
          queContent: '',
          queTagId: '',
          queView: '',
          queCollect: '',
          feedSourceImg: '',
          queComment: '',
          queAnswerListId: ''
        },
        dataRule: {
          openid: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          queTitle: [
            { required: true, message: '主标题不能为空', trigger: 'blur' }
          ],
          queContent: [
            { required: true, message: '问题副标题不能为空', trigger: 'blur' }
          ],
          queTagId: [
            { required: true, message: '标签的id不能为空', trigger: 'blur' }
          ],
          queView: [
            { required: true, message: '问题阅读数量不能为空', trigger: 'blur' }
          ],
          queCollect: [
            { required: true, message: '问题收藏数量不能为空', trigger: 'blur' }
          ],
          feedSourceImg: [
            { required: true, message: '问题的展示图片不能为空', trigger: 'blur' }
          ],
          queComment: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          queAnswerListId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.questionId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.questionId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/yanquestion/info/${this.dataForm.questionId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.openid = data.yanQuestion.openid
                this.dataForm.queTitle = data.yanQuestion.queTitle
                this.dataForm.queContent = data.yanQuestion.queContent
                this.dataForm.queTagId = data.yanQuestion.queTagId
                this.dataForm.queView = data.yanQuestion.queView
                this.dataForm.queCollect = data.yanQuestion.queCollect
                this.dataForm.feedSourceImg = data.yanQuestion.feedSourceImg
                this.dataForm.queComment = data.yanQuestion.queComment
                this.dataForm.queAnswerListId = data.yanQuestion.queAnswerListId
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
              url: this.$http.adornUrl(`/generator/yanquestion/${!this.dataForm.questionId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'questionId': this.dataForm.questionId || undefined,
                'openid': this.dataForm.openid,
                'queTitle': this.dataForm.queTitle,
                'queContent': this.dataForm.queContent,
                'queTagId': this.dataForm.queTagId,
                'queView': this.dataForm.queView,
                'queCollect': this.dataForm.queCollect,
                'feedSourceImg': this.dataForm.feedSourceImg,
                'queComment': this.dataForm.queComment,
                'queAnswerListId': this.dataForm.queAnswerListId
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
