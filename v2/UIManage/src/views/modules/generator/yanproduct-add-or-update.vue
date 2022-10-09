<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="对应的category" prop="categoryId">
      <el-input v-model="dataForm.categoryId" placeholder="对应的category"></el-input>
    </el-form-item>
    <el-form-item label="商品名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="商品名称"></el-input>
    </el-form-item>
    <el-form-item label="商品图片" prop="image">
      <el-input v-model="dataForm.image" placeholder="商品图片"></el-input>
    </el-form-item>
    <el-form-item label="价格" prop="price">
      <el-input v-model="dataForm.price" placeholder="价格"></el-input>
    </el-form-item>
    <el-form-item label="库存数量" prop="stock">
      <el-input v-model="dataForm.stock" placeholder="库存数量"></el-input>
    </el-form-item>
    <el-form-item label="商品状态 1 在售 0 下架" prop="status">
      <el-input v-model="dataForm.status" placeholder="商品状态 1 在售 0 下架"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
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
          categoryId: '',
          name: '',
          image: '',
          price: '',
          stock: '',
          status: '',
          createTime: '',
          updateTime: ''
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
          price: [
            { required: true, message: '价格不能为空', trigger: 'blur' }
          ],
          stock: [
            { required: true, message: '库存数量不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '商品状态 1 在售 0 下架不能为空', trigger: 'blur' }
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
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/generator/yanproduct/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.categoryId = data.yanProduct.categoryId
                this.dataForm.name = data.yanProduct.name
                this.dataForm.image = data.yanProduct.image
                this.dataForm.price = data.yanProduct.price
                this.dataForm.stock = data.yanProduct.stock
                this.dataForm.status = data.yanProduct.status
                this.dataForm.createTime = data.yanProduct.createTime
                this.dataForm.updateTime = data.yanProduct.updateTime
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
              url: this.$http.adornUrl(`/generator/yanproduct/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'categoryId': this.dataForm.categoryId,
                'name': this.dataForm.name,
                'image': this.dataForm.image,
                'price': this.dataForm.price,
                'stock': this.dataForm.stock,
                'status': this.dataForm.status,
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
