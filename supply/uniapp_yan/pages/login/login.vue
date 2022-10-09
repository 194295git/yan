<template>
	<view>
		<u-tabs :list="list" :is-scroll="false" :current="current" @change="change"></u-tabs>
		<u-form class="box" v-if="current == 0" :model="form" ref="uForm">
			<u-form-item label="邮箱">
				<u-input v-model="form.email" />
			</u-form-item>
			<u-form-item label="密码">
				<u-input v-model="form.password" />
			</u-form-item>
			<button type="default" @click="login2()">登录</button>

		</u-form>

		<u-form class="box" v-if="current == 1" :model="form" ref="uForm">

			<u-form-item label="邮箱">
				<u-input v-model="form1.email" />
			</u-form-item>
			<u-form-item label="用户名" :label-width="labelWidth">
				<u-input v-model="form1.username" />
			</u-form-item>
			<u-form-item label="密码">
				<u-input v-model="form1.password" />
			</u-form-item>
			<u-form-item label="验证码" :label-width="labelWidth">
				<u-input v-model="form1.email" />
			</u-form-item>
			<button type="default">注册</button>

		</u-form>

	</view>
</template>

<script>
	import app from '../../common/app.js';
	import http from '../../common/http.js';
	export default {
		data() {
			return {
				form: {
					email: '',
					password: ''
				},
				list: [{
					name: '登录'
				}, {
					name: '注册'
				}],
				form1: {},
				current: 0,
				labelWidth: '60px',

			}
		},
		methods: {
			change(index) {
				this.current = index;
			},
			toIndex() {
				uni.navigateTo({
					url: '../index/index',
				})

			},
			async getUserInfo() {
				var that = this;
				const res =  await this.$myRequest({
					url: 'yaj/yan-user/getUserInfoMe',
				})
				that.userInfo = res.data.content
				that.$store.commit("setUserInfo", res.data.content)
				that.$store.commit("setChatId", res.data.content.chatId)
				console.log("that.userInfo")
				console.log(that.userInfo)
			},
			// 这样子可以保证跳转页面是在获取信息之后
			async login2() {
				const {	form } = this;
				this.$store.commit("setEmail", form.email)
				const res = await this.$myRequest({
					url: 'loginByWeb',
					header: {
						'content-type': 'application/json'
					},
					method: 'POST',
					data: {
						'email': form.email,
						'password': form.password
					}
					
				})
				this.$store.commit("setSignToken", res.data.content)
				const res2 = await this.getUserInfo()
				uni.switchTab({
					url: '../index/index'
				})
				
				
			},
			login() {
				var that = this;
				console.log("进入方法")
				const {
					form
				} = this;
				console.log(form)
				that.$store.commit("setEmail", form.email)
				uni.request({
					url: app.globalData.url + 'loginByWeb',
					header: {
						'content-type': 'application/json'
					},
					method: 'POST',
					data: {
						'email': form.email,
						'password': form.password
					},
					success: function(res) {
						that.$store.commit("setSignToken", res.data.content)
						that.getUserInfo();
						console.log("token")
						console.log(uni.getStorageSync('token'))
					},
					complete: function(res) {
						// 
						console.log("是否进入complete")
						

					}
				})

			}


		}
	}
</script>

<style>


</style>
