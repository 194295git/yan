<template>
	<view>
		<u-form class="box" :model="form" ref="uForm">
			<u-form-item label="姓名">
				<u-input v-model="form.username" />
			</u-form-item>
			<u-form-item label="密码">
				<u-input v-model="form.password" />
			</u-form-item>
			<u-form-item label="头像">
				<u-upload ref="uUpload" :action="action" :auto-upload="false"></u-upload>
				<u-button @click="submit">提交</u-button>


			</u-form-item>
			<u-form-item label="专业">
				<u-input v-model="form.major" />

			</u-form-item>
			<u-form-item label="大学">
				<u-input v-model="form.college" />

			</u-form-item>
		</u-form>
		<button type="default" @click="addUser()">总提交</button>

	</view>
</template>

<script>
	import http from '../../common/http.js';
	import app from '../../common/app.js';
	export default {
		data() {
			return {
				files: [],
				form: {
					name: '',
					password: '',
					avatarUrl: '',
					major: '',
					college: ''
				},
				userInfo: {},
				action: app.globalData.action,

			}
		},
		created() {
			this.getUserInfo()
		},
		methods: {
			getUserInfo() {
				var that = this;
				http.request({
					url: 'yaj/yan-user/getUserInfo',
					success: function(res) {
						that.userInfo = res.data.content
						that.$store.commit("setUserInfo",res.data.content)
						console.log(that.userInfo)

					}
				})
			},
			
			submit() {
				this.$refs.uUpload.upload();
				
			},
			addUser(){
				this.files = this.$refs.uUpload.lists.filter(val => {
					return val.progress == 100;
				})
				this.form.avatarUrl = this.files[0].response.content
				const { form } = this
				http.request({
					method: 'POST',
					data: form,
					url: 'yaj/yan-user/addInfo',
					success: function(res) {
						console.log("成功")
					}
				})
				
			},

		}
	}
</script>

<style>

</style>
