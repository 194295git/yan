<template>
	<view>
		<view class="container more">
			<view class="top">
				<view class="top_font mt-4">{{userInfo.college}}</view>
				<view class="top_font2 ">{{userInfo.major}}</view>
				
			</view>

			<view v-if="islogin === 1" class="user flex-wrp">
				<view class="avatar flex-item">
					<image class="userinfo-avatar" :src="userInfo.avatarUrl" background-size="cover"></image>
				</view>
				<view class="user-info flex-item align-center">
					<text class="userinfo-nickname">{{userInfo.userName}}</text>
					<text class="edit" @click="edit">查看或编辑个人主页</text>
				</view>
			</view>
			<view v-if="islogin === 0" class="d-felx align-center">
				<u-avatar></u-avatar>
				<view >
					<text class="edit" @click="edit">点击登录</text>
				</view>
			</view>
			<view v-if="cur === 0" class="my">
				<view @click="jump(0)" class="my-item flex-wrp">
					<view class="myitem-icon flex-item">
						<image src="/static/images/eye.png"></image>
					</view>
					<view class="myitem-name flex-item">
						<text>我的课程</text>
					</view>
				</view>
				<view @click="changeCur(1)" class="my-item flex-wrp">
					<view class="myitem-icon flex-item">
						<image src="/static/images/star.png"></image>
					</view>
					<view class="myitem-name flex-item">
						<text>我的提问</text>
					</view>
				</view>
				<view @click="jump(1)" class="my-item flex-wrp">
					<view class="myitem-icon flex-item">
						<image src="/static/images/draft.png"></image>
					</view>
					<view class="myitem-name flex-item">
						<text>我的关注</text>
					</view>
				</view>
				<view class="my-item flex-wrp">
					<view class="myitem-icon flex-item">
						<image src="/static/images/recent.png"></image>
					</view>
					<view class="myitem-name flex-item">
						<text>我的点赞</text>
					</view>
				</view>
				<view class="my-item flex-wrp">
					<view class="myitem-icon flex-item">
						<image src="/static/images/book.png"></image>
					</view>
					<view class="myitem-name flex-item">
						<text>我的评论</text>
					</view>
				</view>

				<view class="my-item flex-wrp" @click="logout">
					<view class="myitem-icon flex-item">
						<image src="/static/images/zhi.png"></image>
					</view>
					<view class="myitem-name flex-item" bindtap="tap">
						<text>退出登录</text>
					</view>
				</view>
			</view>
			<view v-if="cur !== 0" @click="changeCur(0)" class="box pl-3">
				退回
				
			</view>
			<view v-if="cur === 1" class="box">
				<view v-for="(question,index) in myQue" :key="index">
					<view @click="jumpQue(question.questionId)" class="question-wrp">
						<view class="question-item">
							<view class="que-title">
								{{question.queTitle}}
							</view>
							<view class="que-content">
								{{question.queContent}}
							</view>
							<view class="que-follow">
								<view class="left">
									<view class="watch">
										<image src="../../static/images/eye.png"></image>
										<text>{{question.queView}}</text>
									</view>
									<view class="comment">
										<image src="../../static/images/comment2.png"></image>
										<text>27</text>
									</view>
								</view>
								<view class="right">
									关注
								</view>
							</view>
						</view>
					
					</view>
				</view>
			</view>
		</view>


	</view>
</template>

<script>
	import http from '../../common/http.js';
	export default {
		data() {
			return {

				cur: 0,
				islogin: 1,
				userInfo: {},
				hasUserInfo: false,
				myQue: [],
				userid: '',
				token: ""

			}
		},
		onLoad() {
			console.log("me")
			// console.log(this.$store.state.userInfo)
			const userInfo = uni.getStorageSync('userInfo');
			this.userInfo = userInfo
			this.islogin = 1
		},
		methods: {
			jumpQue(questionId){
				uni.navigateTo({
					url: '../question/question?id=' + questionId,
				})
			},
			changeCur(cur) {
				
				var that = this
				if(cur=== 1){
					http.request({
						url: 'yaj/yan-question/getMyQue',
						success: function(res) {
							that.myQue = res.data.content
							console.log(that.myQue)
					
					
						}
					})
				}
				this.cur = cur

			},
			jump(to) {
				if (to === 0) {
					uni.navigateTo({
						url: '../course/course'
					})
				}
				if (to === 1) {
					uni.switchTab({
						url: '../admissions_line/admissions_line'
					})
				}

			},
			edit() {
				uni.navigateTo({
					url: '../edit_user/edit_user',
				})
			},
			logout() {
				this.$store.commit("clearToken")
				this.$store.commit("clearUserInfo")
		
				this.islogin = 0
			},

		}
	}
</script>

<style>
	.top {
		height: 30vh;
		width: 100%;
		background: #9932CC;
		border-radius: 24rpx;
		margin-top: -18rpx;
		padding-top: 40rpx;
		/* font-size: 40rpx; */

	}

	.top_font {
		width: 80vw;
		font-size: 90rpx;
		margin: -2rpx auto;
		text-align: center;
		color: white;	
	}
	.top_font2 {
		width: 80vw;
		font-size: 80rpx;
		margin: -2rpx auto;
		text-align: center;
		color: white;	
	}

	.user {
		width: 650rpx;
		background: rgb(191, 88, 243);
		padding: 30rpx 25rpx 30rpx 0;
		margin: -100rpx auto 14rpx;

		border-radius: 25rpx;
	}

	.user .userinfo-avatar {
		flex: 1;
		display: inline-block;
		width: 100rpx;
		height: 100rpx;
		border-radius: 80rpx;
	}

	.user .user-info {
		flex: 5;
		text-align: left;
	}

	.user .user-info text {
		display: block;
	}

	.user .user-info .userinfo-nickname {
		padding: 8rpx 0 10rpx 0;
		font-size: 30rpx;
		color: white;
		background: rgb(191, 88, 243);
	}
	.edit {
		font-size: 30rpx;
		color: #bababa;
		line-height: 34rpx;
	}

	.user .user-info .edit {
		font-size: 30rpx;
		color: #bababa;
		line-height: 34rpx;
	}

	.my {
		margin: 15rpx 0 0 0;
		border-top: solid 1px #ebebeb;
		border-bottom: solid 2px #ebebeb;

	}

	.my .my-item {
		width: 660rpx;
		background: #ffffff;
		padding: 0
	}

	.my .my-item .myitem-icon {
		flex: 1;
	}

	.my .my-item .myitem-icon image {
		width: 45rpx;
		height: 45rpx;
		padding: 37.5rpx 0;
	}

	.image {
		width: 45rpx;
		height: 45rpx;
		padding: 37.5rpx 0;
	}

	.my .my-item .myitem-name {
		flex: 5;
		text-align: left;
		font-size: 34rpx;
		color: #6d6d6d;
		line-height: 60rpx;
		padding: 30rpx 0;
		border-bottom: solid 1px #ebebeb;
	}

	.my .my-item:last-child .myitem-name {
		border-bottom: none;
	}
	
	/* question方面的css */
	.question-wrp {
		width: 95%;
		margin: 25rpx auto;
		border-radius: 5px;
		box-shadow: 0 1px 2px #bebebe;
		background: white;
		/* border: 1px solid black; */
	}
	.question-item .que-title {
		padding: 20rpx 20rpx;
		font-size: 30rpx;
		font-weight: bold;
	}
	
	.question-item .que-content {
		padding: 0 20rpx;
		font-size: 24rpx;
	}
	
	.question-item .que-follow {
		margin: 20rpx;
		height: 60rpx;
	}
	
	.question-item .que-follow view {
		display: inline-block;
		margin: 0 40rpx 0 0;
	}
	
	.question-item .que-follow image {
		width: 50rpx;
		height: 50rpx;
		margin: 0 10rpx 0 0;
		vertical-align: middle;
	}
	
	.question-item .que-follow .left {
		float: left;
		padding: 10rpx 0;
	}
	
	.question-item .que-follow .left text {
		color: #AFAFAF;
		height: 50rpx;
		vertical-align: middle;
		padding: 18rpx 0;
		font-size: 24rpx;
	}
	
	.question-item .que-follow .right {
		float: right;
		padding: 10rpx 55rpx;
		color: #ffffff;
		background: #9932CC;
		font-size: 26rpx;
		margin: 0;
	}
	.back{
		
	}
</style>
