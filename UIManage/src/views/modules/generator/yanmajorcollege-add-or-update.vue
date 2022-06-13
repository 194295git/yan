<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="majorId">
      <el-input v-model="dataForm.majorId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="grade">
      <el-input v-model="dataForm.grade" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="collegeName">
      <el-input v-model="dataForm.collegeName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="element1">
      <el-input v-model="dataForm.element1" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="element2">
      <el-input v-model="dataForm.element2" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="element3">
      <el-input v-model="dataForm.element3" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="element4">
      <el-input v-model="dataForm.element4" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="location">
      <el-input v-model="dataForm.location" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="element5">
      <el-input v-model="dataForm.element5" placeholder=""></el-input>
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
          majorId: '',
          grade: '',
          collegeName: '',
          element1: '',
          element2: '',
          element3: '',
          element4: '',
          location: '',
          element5: ''
        },
        dataRule: {
          majorId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          grade: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          collegeName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          element1: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          element2: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          element3: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          element4: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          location: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          element5: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/generator/yanmajorcollege/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.majorId = data.yanMajorCollege.majorId
                this.dataForm.grade = data.yanMajorCollege.grade
                this.dataForm.collegeName = data.yanMajorCollege.collegeName
                this.dataForm.element1 = data.yanMajorCollege.element1
                this.dataForm.element2 = data.yanMajorCollege.element2
                this.dataForm.element3 = data.yanMajorCollege.element3
                this.dataForm.element4 = data.yanMajorCollege.element4
                this.dataForm.location = data.yanMajorCollege.location
                this.dataForm.element5 = data.yanMajorCollege.element5
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
              url: this.$http.adornUrl(`/generator/yanmajorcollege/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'majorId': this.dataForm.majorId,
                'grade': this.dataForm.grade,
                'collegeName': this.dataForm.collegeName,
                'element1': this.dataForm.element1,
                'element2': this.dataForm.element2,
                'element3': this.dataForm.element3,
                'element4': this.dataForm.element4,
                'location': this.dataForm.location,
                'element5': this.dataForm.element5
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
