<template>
  <el-dialog
    :title="!dataForm.openid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="头像地址" prop="avatarUrl">
      <el-input v-model="dataForm.avatarUrl" placeholder="头像地址"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="会员等级id" prop="levelId">
      <el-input v-model="dataForm.levelId" placeholder="会员等级id"></el-input>
    </el-form-item>
    <el-form-item label="是否登录" prop="isAlive">
      <el-input v-model="dataForm.isAlive" placeholder="是否登录"></el-input>
    </el-form-item>
    <el-form-item label="积分" prop="integration">
      <el-input v-model="dataForm.integration" placeholder="积分"></el-input>
    </el-form-item>
    <el-form-item label="成长值" prop="growth">
      <el-input v-model="dataForm.growth" placeholder="成长值"></el-input>
    </el-form-item>
    <el-form-item label="我心怡的大学" prop="college">
      <el-input v-model="dataForm.college" placeholder="我心怡的大学"></el-input>
    </el-form-item>
    <el-form-item label="我的专业" prop="major">
      <el-input v-model="dataForm.major" placeholder="我的专业"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="最后一次更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="最后一次更新时间"></el-input>
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
          openid: 0,
          username: '',
          password: '',
          avatarUrl: '',
          email: '',
          levelId: '',
          isAlive: '',
          integration: '',
          growth: '',
          college: '',
          major: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          avatarUrl: [
            { required: true, message: '头像地址不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          levelId: [
            { required: true, message: '会员等级id不能为空', trigger: 'blur' }
          ],
          isAlive: [
            { required: true, message: '是否登录不能为空', trigger: 'blur' }
          ],
          integration: [
            { required: true, message: '积分不能为空', trigger: 'blur' }
          ],
          growth: [
            { required: true, message: '成长值不能为空', trigger: 'blur' }
          ],
          college: [
            { required: true, message: '我心怡的大学不能为空', trigger: 'blur' }
          ],
          major: [
            { required: true, message: '我的专业不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '最后一次更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.openid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.openid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/yanuser/info/${this.dataForm.openid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.yanUser.username
                this.dataForm.password = data.yanUser.password
                this.dataForm.avatarUrl = data.yanUser.avatarUrl
                this.dataForm.email = data.yanUser.email
                this.dataForm.levelId = data.yanUser.levelId
                this.dataForm.isAlive = data.yanUser.isAlive
                this.dataForm.integration = data.yanUser.integration
                this.dataForm.growth = data.yanUser.growth
                this.dataForm.college = data.yanUser.college
                this.dataForm.major = data.yanUser.major
                this.dataForm.createTime = data.yanUser.createTime
                this.dataForm.updateTime = data.yanUser.updateTime
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
              url: this.$http.adornUrl(`/generator/yanuser/${!this.dataForm.openid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'openid': this.dataForm.openid || undefined,
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'avatarUrl': this.dataForm.avatarUrl,
                'email': this.dataForm.email,
                'levelId': this.dataForm.levelId,
                'isAlive': this.dataForm.isAlive,
                'integration': this.dataForm.integration,
                'growth': this.dataForm.growth,
                'college': this.dataForm.college,
                'major': this.dataForm.major,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
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
