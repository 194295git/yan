<template>
	<view>
		<!--miniprogram/pages/question/question.wxml-->

		<view class="container">
			<view class="question-wrp">
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
			<scroll-view class="content">
				<view class="answer-feed" v-for='(item,index) in question.answerList' :key='index' :data-idx='index' @click='getIndex'>
					<view @click="bindItemTap(item.answerId)" class="box feed-item d-flex">
						<view class="feed-source">
							<u-avatar :src="item.avatarUrl"></u-avatar>
						
						</view>
						<view class="feed-content">
							<view class="answer-body">
								<view>
									<rich-text :nodes="item.answerContent"></rich-text>

								</view>
								<view class="answer-actions flex-wrp">
									<view class="like dot flex-item">
										<a>3.9K 赞同 </a>
									</view>
									<view class="comments dot flex-item">
										<a>254 评论 </a>
									</view>
									<view class="time flex-item">
										<a>2 个月前</a>
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
			<view class="bottom_1">

				<view class="invite ">
					<image src="../../static/images/invite.png" style="width:60rpx;height:60rpx;padding:auto 6rpx;margin-left:8rpx;margin-right:8rpx;"></image>
					<text style="margin-bottom:24rpx;">邀请回答</text>
				</view>
				<view class="write">
					<image src="../../static/images/write.png" style="width:60rpx;height:60rpx;padding:auto 6rpx;margin-left:8rpx;margin-right:8rpx;"></image>
					<text @click="writeAnswer">写回答</text>
				</view>

			</view>
		</view>


	</view>
</template>

<script>
	import app from '../../common/app.js';

	export default {
		data() {
			return {
				userInfo: {},
				question: {},
				index: '',
				option_id: ''

			}
		},

		onLoad: function(option) {
			console.log(option.id);
			this.option_id = option.id
			console.log(this.option_id)
			this.getData();
		},

		methods: {

			//事件处理函数
			bindItemTap(id) {
				var that = this;
				// var answer_id = that.question.answerList[that.index].answerId;
				console.log("answer_id")
				// uni.navigateTo({
				// 	url: '../answer/answer?id=' + that.question.answerList[that.index].answerId
				// })
				uni.navigateTo({
					url: '../answer/answer?id=' + id
				})
			},
			writeAnswer: function() {
				var that = this;
				uni.navigateTo({
				  url: '../answer/answerSumit/answerSumit?id='+that.question.questionId
	
				})
				console.log("跳转")


			},
			getIndex: function(e) {
				var _index = e.currentTarget.dataset.idx;
				console.log(" getIndex  index")
				console.log(_index)
				var that = this;
				that.index = _index
				//下面的这一行绝对是可以提取出值来的；
				console.log(that.question.answerList[that.index].answerId)
			},
		
			getData() {
				var that = this;
				uni.request({
					url: app.globalData.url + 'yaj/yan-question/getQuestionAnswer?id=' + that.option_id,
					success: function(res) {
						var res1 = res.data.content.answerList
						console.log(res1)
						for (var i = 0; i < res1.length; i++) {
							res.data.content.answerList[i].answerContent = res1[i].answerContent.substr(0, 75) + "..."

						}
						that.question = res.data.content
						// that.setData({
						//   question: res.data.content
						// })
						console.log(res.data.content)
						// console.log("item",that.data.item)
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
	.container {
		background: rgb(222, 183, 241);
		/* height: 100%; */
	}

	.answer-feed {
		padding: 0;
		font-size: 14rpx;
		color: #000;
	}

	.question-wrp {
		width: 90%;
		border-radius: 25rpx;
		margin: 10rpx 30rpx;
		background: white;
		box-shadow: 0 1px 2px #bebebe;
	}

	.question-item {
		width: 660prx;
		padding: 5rpx 20rpx;
		border-radius: 25rpx;
	}

	.question-item .que-tag {}

	.avatar {
		width: 100rpx;
		height: 100rpx;


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

	.bottom_1 {
		position: fixed;
		bottom: 15rpx;
		width: 620rpx;
		/* height: 195rpx; */
		/* background: rgb(151, 49, 202); */
	}


	.invite {
		border-right: solid 2px #ebebeb;
		float: left;
		width: 300rpx;
		margin-right: 5rpx;
		border-radius: 18rpx;
		background: rgb(151, 49, 202);

	}

	.write {
		border-right: solid 2px #ebebeb;
		float: right;
		width: 300rpx;
		margin-left: 5rpx;
		border-radius: 18rpx;
		background: rgb(151, 49, 202);
	}

	.image {
		width: 50rpx;
		height: 50rpx;
		vertical-align: middle;
		color: white;
		margin: 0 20rpx 0 0;
	}

	.content {
		/* border: 1px solid #000; */
		height: 930rpx;
	}

	.answer-feed .feed-item .feed-content .answer-body {
		padding: 0;
		/*height: 10rpx;*/
		font-size: 24rpx;
		line-height: 28rpx;
		color: #5b5b5b;
	}

	/* =========== */

	.feed-item {
		padding: 15rpx 20rpx;
		margin: 15rpx auto;
		background: #ffffff;
		border-radius: 25rpx;


	}

	.feed-source {
		width: 80rpx;
		padding: auto 0px;
		height: 80rpx;
	}

	.feed-content {
		width: 520rpx;
		padding: 10rpx 0 0 20rpx;
		font-size: 28rpx;
		font-weight: 600px;
		line-height: 40rpx;
		color: gray
	}

	.feed-content_subject {
		color: black;
	}
</style>
