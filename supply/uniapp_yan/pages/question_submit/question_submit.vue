<template>
	<view>

		
		<u-form class="box" :model="form" ref="uForm">
			<u-form-item label-position="top" label="输入问题">
				<u-input 
				placeholder-style="color: #606266;"
				v-model="form.queTitle" />
			</u-form-item>
			<u-form-item label-position="top" label="输入问题描述">
				<u-input
				 placeholder-style="color: #606266;"
				 v-model="form.queContent" />
			</u-form-item>
			<u-form-item label-position="top" label="上传图片">
				<u-upload ref="uUpload" :action="action" :file-list="files"></u-upload>
			</u-form-item>
			<u-form-item label-position="top" label="标签">
				<u-checkbox-group >
					<u-checkbox  v-model="item.checked" v-for="item in listTags" :key="item.tagId" :name="item.queTag">
						{{ item.queTag }}
					</u-checkbox>
				</u-checkbox-group>
			</u-form-item>

		</u-form>
		<button class="box" type="default" @click="addQue()">发表问题</button>
		<!-- <text @click="test">test</text> -->

	</view>
</template>

<script>
	import http from '../../common/http.js';
	import app from '../../common/app.js';
	export default {
		data() {
			return {
				files: [],
				action: app.globalData.action,
				form: {
					queTitle: '',
					queContent: '',
					queTagIds: [],
					feedSourceImg: '',
				},
				
				listTags: [],
			}
		},
		onLoad() {
			this.getAllTags()
		},
		methods: {
			addQue(){
				const {listTags,form,files} = this
				//处理文件数据
				this.files = this.$refs.uUpload.lists.filter(val => {
					return val.progress == 100;
				})
				this.form.feedSourceImg = this.files[0].response.content
				//处理tags数据
				listTags.map((e)=>{
					if(e.checked === true){
						this.form.queTagIds.push(e.tagId)
					}
						
				})
				console.log(form)
				var that = this;
				http.request({
					method: 'POST',
					data: that.form,
					url: 'yaj/yan-question/questionSubmit',
					success: function(res) {
						uni.switchTab({
							url:'../like/like'
						})
						console.log("成功")
				
				
					}
				})
				
			},
			
			
			test() {
				console.log(this.listTags)
			},
			getAllTags() {
				var that = this
				http.request({
					url: 'yaj/yan-question/listTags',
					success: function(res) {
						that.listTags = res.data.content
						console.log(that.listTags)


					}
				})
			}

		}
	}
</script>

<style>
	
	page {
		background-color: rgb(221, 175, 248);
	}


	.question-wrp {
		width: 80%;
		margin: 25rpx auto;
		border-radius: 12px;
		box-shadow: 0 1px 2px #bebebe;
		background: white;
		/* border: 1px solid black; */
	}

	.question-item {
		font-size: 38rpx;
		width: 710rpx;
		padding: 40rpx 20rpx 10rpx;
		/* background: #fff; */
	}

	.question-input {
		/* width: 710rpx; */
		padding: 20rpx 30rpx;
		height: 200rpx;
		/* background: #fff; */
	}

	.submit {
		background: white;
		width: 80%;
		margin: 40rpx auto 4rpx;

	}

	.question {
		position: relative;
		width: 650rpx;
		padding: 40rpx 50rpx 30rpx;
		/* background: #298DE5; */
		background: #F57A82;
		color: #fff;
		font-size: 38rpx;
		line-height: 48rpx;
	}

	textarea {
		width: 700rpx;
		height: 500rpx;
		margin-left: 10rpx;
		margin-right: 10rpx;
		margin-top: 10rpx;
	}
</style>
