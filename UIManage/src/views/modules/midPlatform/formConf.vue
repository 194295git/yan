<template>
  <div>
    <div class="title">通过输入excel填写配置自动生成表单的功能</div>
    <div>
      <el-upload
        action=""
        class="upload-demo"
        :http-request="upload"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        multiple
        :limit="3"
        :on-exceed="handleExceed"
        :file-list="fileList"
      >
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">
          请上传生成表单的excel，大小不要超过500kb
        </div>
      </el-upload>
    </div>
    <div>
      <div class="title" @click="formCreate">生成相应的前端表单</div>
      <div v-if="show == 1">
        <!-- <div v-if=""></div> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      fileList: [
        {
          name: 'food.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }
      ],
      show: 0,
      type: ''
    }
  },
  methods: {
    upload (param) {
      const formData = new FormData()
      formData.append('file', param.file)
      this.$http({
        url: this.$http.adornUrl(`/platform/upload/uploadfile`),
        method: 'post',
        data: formData
      }).then(({ data }) => {
        if (data && data.code === 200) {
          console.log(this.fileList)
          this.fileList.push({
            name: param.file.name,
            url: data.data.tempPath
          })
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.visible = false
            }
          })
        } else {
          this.$message.error(data.msg)
        }
      })
    },
    async formCreate () {
      console.log('生成表单')
      const res = await this.$http({
        url: this.$http.adornUrl(`/platform/upload/testPromise`),
        method: 'get'
      })
      console.log(res)
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePreview (file) {
      console.log(file)
    },
    handleExceed (files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      )
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    }
  }
}
</script>

<style>
.title {
  padding: 10px 2px;
  font-size: 20px;
}
</style>
