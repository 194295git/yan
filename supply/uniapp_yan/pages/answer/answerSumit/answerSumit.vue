<template>
	<view class="courseIntroduce box">
		<view class="navBar">
			<view class="title">写回答</view>
			<view class="edit" v-if="isEdit" @tap="toEdit">编辑</view>
		</view>
		<view class="editBox" v-if="showEdit">
			<editor id="editor" class="ql-container" placeholder="请开始编辑" :read-only="!isEditing" @input="getEditText"></editor>
			<view class="addBtn" @tap="toEdit" v-if="!showEdit">立即编辑</view>
			<view class="addBtn" @tap="saveEditText" v-if="showEdit && !isEdit">保存</view>
		</view>
	</view>
</template>

<script>
	import http from '../../../common/http.js';
	import app from '../../../common/app.js';
	export default {
		data() {
			return {
				option_id: '',
				// 展示编辑按钮
				showEdit: false,
				// 展示编辑文字
				isEdit: true,
				// 课程介绍
				answerContent: '',
				// 是否可编辑
				isEditing: false,
				introFlag: '' //判断介绍是否为空

			}
		},
		onLoad: function(option) {
			console.log(option.id);
			this.option_id = option.id
			console.log(this.option_id)

		},
		methods: {
			toEdit() {
				this.showEdit = true;
				this.isEdit = false;
				this.isEditing = true;
			},
			// 获取编辑的文本
			getEditText(e) {
				console.log(e.detail.html);
				this.introFlag = e.detail.text;
				this.answerContent = e.detail.html;
			},
			//保存需要的文本
			saveEditText(){
				var that = this;
				http.request({
					url: 'yaj/yan-answer/writeAnswer',
					header: {
						'content-type': 'application/json'
					},
					method: 'POST',
					data: {
						'queId': that.option_id,
						'answerContent': that.answerContent
					},
					success: function(res) {
						console.log("保存成功")
						uni.navigateTo({
							url:'../../question/question?id='+that.option_id
						})
						// uni.navigateBack({
							
						// })
						
					}
				})
			}


		}
	}
</script>

<style scoped>
	.navBar {
		margin-top: 30rpx;
		padding: 0 30rpx;
		height: 32rpx;
		display: flex;
		align-items: center;
		justify-content: space-between;
	}
	.title {
		font-size: 32rpx;
		font-weight: bold;
	}
	.courseIntroduce {
		min-height: 242rpx;
		background: rgba(255, 255, 255, 1);
		border-radius: 16rpx;
		overflow: hidden;
	}
	
	.more {
		font-size: 24rpx;
	}

	.addBtn {
		margin: 60rpx auto 30rpx;
		width: 240rpx;
		min-height: 60rpx;
		line-height: 60rpx;
		text-align: center;
		background: rgba(21, 150, 240, 1);
		border-radius: 30rpx;
		font-size: 30rpx;
		color: rgba(255, 255, 255, 1);
	}

	.editBox {
		margin-top: 50rpx;
		margin-left: 30rpx;
		margin-right: 30rpx
	}
</style>
