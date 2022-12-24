<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="80px"
    >
      <el-form-item label="名" prop="fieldName">
        <el-input v-model="dataForm.fieldName" placeholder="字段名"></el-input>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="dataForm.type" placeholder="请选择类型">
          <el-option label="bigint" value="shanghai"></el-option>
          <el-option label="binary" value="beijing"></el-option>
          <el-option label="bit" value="beijing"></el-option>
          <el-option label="blob" value="beijing"></el-option>
          <el-option label="char" value="beijing"></el-option>
          <el-option label="date" value="beijing"></el-option>
          <el-option label="timestamp" value="beijing"></el-option>
          <el-option label="tinyblob" value="beijing"></el-option>
          <el-option label="tinytext" value="beijing"></el-option>
          <el-option label="varbinary" value="beijing"></el-option>
          <el-option label="varchar" value="beijing"></el-option>
          <el-option label="year" value="beijing"></el-option>
        </el-select>
        <!-- <el-input v-model="dataForm.image" placeholder="类型"></el-input> -->
      </el-form-item>
      <el-form-item label="长度" prop="length">
        <el-input v-model="dataForm.length" placeholder="长度"></el-input>
      </el-form-item>
      <el-form-item label="isNull" prop="isNull">
        <el-switch v-model="dataForm.isNull"></el-switch>
      </el-form-item>
      <el-form-item label="键" prop="key">
        <el-switch v-model="dataForm.key"></el-switch>
      </el-form-item>
       <el-form-item label="注释" prop="fieldComment">
        <el-input v-model="dataForm.fieldComment" placeholder="注释"></el-input>
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
        fieldname: '',
        type: '',
        length: '',
        isNull: '',
        key: '',
        fieldComment: ''

      },
      dataRule: {
        categoryId: [
          { required: true, message: '对应的category不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '商品名称不能为空', trigger: 'blur' }
        ],
        image: [
          { required: true, message: '商品图片不能为空', trigger: 'blur' }
        ],
        price: [{ required: true, message: '价格不能为空', trigger: 'blur' }],
        stock: [
          { required: true, message: '库存数量不能为空', trigger: 'blur' }
        ],
        status: [
          {
            required: true,
            message: '商品状态 1 在售 0 下架不能为空',
            trigger: 'blur'
          }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        updateTime: [
          { required: true, message: '更新时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init (id) {
      this.dataForm.id = id || 0
      this.visible = true
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/generator/yanproduct/${!this.dataForm.id ? 'save' : 'update'}`
            ),
            method: 'post',
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              categoryId: this.dataForm.categoryId,
              name: this.dataForm.name,
              image: this.dataForm.image,
              price: this.dataForm.price,
              stock: this.dataForm.stock,
              status: this.dataForm.status,
              createTime: this.dataForm.createTime,
              updateTime: this.dataForm.updateTime
            })
          }).then(({ data }) => {
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
