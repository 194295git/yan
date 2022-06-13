<template>
	<view>
		<view class='container'>
			<view class="xian"></view>
			<view class='question box' @click='bindQueTap'>
				<text class='question-title'>{{que}}</text>

				<view class="que-follow">

					<view class="watch">
						<image src="../../static/images/eye.png"></image>
						<text>300</text>
					</view>
					<view class="comment">
						<image src="../../static/images/comment2.png"></image>
						<text>27</text>
					</view>

					<view class='follow'>
						<text>十 关注</text>
					</view>
				</view>
			</view>
			<scroll-view class="scroll" scroll-y="true">
				<view>
					<view class='answerer-wrp box'>
						<view class='bg-half'>
							<view class='answerer d-flex'>
								<view class='avatar flex-item'>
									<image :src='answer.avatarUrl'></image>
								</view>
								<view class='answerer-info'>
									<text class='answerer-name'>{{answer.answerName}}</text>
									<text class='answerer-des'>{{answer.answerDes}}</text>
								</view>
							</view>
							<view class='answer-content'>
								<image src="../../static/images/1444983318907-_DSC1826.jpg" style="height: 200rpx;" />
								<rich-text :nodes="answer.answerContent" class='answerer-cont'></rich-text>
							</view>

						</view>
					</view>
					<view class="box">
						<view v-for="(item,index) in commentList" :key="index">
							<view class="box2 d-flex commentBox">
								<u-avatar size="40" :src="item.avatarUrl"></u-avatar>
								<view class="box2 ml-4">
									<view class="box">
										{{item.comment}}
									</view>
									<view class="ml-2" v-if="item.level2.length != 0">
										<view class="d-flex" v-for="(item2,index2) in item.level2" :key="index2">
											<u-avatar size="40" :src="item2.avatarUrl"></u-avatar>
											<view class="ml-3">
												{{item2.comment}}
											</view>

										</view>

									</view>


								</view>
							</view>
						</view>

						<view style="width: 88vw;margin: 0 auto;" class="">
							<u-input 
							placeholder="欢迎留下美丽的评论"
							placeholder-style="color:grey" v-model="value" :type="type" :border="border" />

						</view>
					</view>
				</view>
			</scroll-view>



			<view class='answer-footer flex-wrp'>
				<view class="operation-wrp flex-item">
					<view class="operation flex-wrp flex-tab">
						<view class="operation-btn d-flex"  @click="like()">
							<image src="../../static/images/heart2.png"></image>
							<text class="font-white">{{answer.answerLike}}</text>
						</view>
						<view class="operation-btn d-flex">
							<image src="../../static/images/star2.png"></image>
							<text>收藏</text>
						</view>
						<view class="operation-btn d-flex" @click="commment()">
							<image src="../../static/images/comment.png"></image>
							<!-- <u-mask :show="show" @click="show = false"></u-mask> -->

						</view>
					</view>
				</view>
			</view>

			<!-- 从这个地方开始文章的评论环节 -->
			<!-- <u-card :title="title" :sub-title="subTitle" :thumb="thumb">
				<view class="" slot="body">
					<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0">
						<view class="u-body-item-title u-line-2">瓶身描绘的牡丹一如你初妆，冉冉檀香透过窗心事我了然，宣纸上走笔至此搁一半</view>
						<image src="https://img11.360buyimg.com/n7/jfs/t1/94448/29/2734/524808/5dd4cc16E990dfb6b/59c256f85a8c3757.jpg"
						 mode="aspectFill"></image>
					</view>
					<view class="u-body-item u-flex u-row-between u-p-b-0">
						<view class="u-body-item-title u-line-2">釉色渲染仕女图韵味被私藏，而你嫣然的一笑如含苞待放</view>
						<image src="https://img12.360buyimg.com/n7/jfs/t1/102191/19/9072/330688/5e0af7cfE17698872/c91c00d713bf729a.jpg"
						 mode="aspectFill"></image>
					</view>
				</view>
				<view class="" slot="foot">
					<u-icon name="chat-fill" size="34" color="" label="30评论"></u-icon>
				</view>
			</u-card> -->


		</view>


	</view>
</template>

<script>
	import app from '../../common/app.js';
	export default {
		data() {
			return {
				userInfo: {},
				answer: {},
				option_id: {},
				html: '',
				que: '',
				
				show: true,
				commentList: [],
				value: '',
				type: 'text',
				border: true

			}
		},
		onLoad: function(option) {

			// var que  = wx.getStorageSync("que")

			var que = this.$store.state.que
			this.que = que;
			console.log(option.id);
			this.option_id = option.id

			this.getData();
			this.getComment();
		},
		methods: {
			async commment(){
				var that = this;
				const res = await this.$myRequest({
					url: 'yaj/yan-answer-comment/addComment/' + that.option_id,
					method: 'POST',
					data:{
						comment: that.value,
						isTop:1
					}
				})
				await that.getComment();
				that.value = ''
				
				console.log("commment")
			},
			async like(){
				var that = this;
				const res = await this.$myRequest({
					url: 'yaj/yan-answer-like/changeAnswerLikeStatus/' + that.option_id,
					method: 'POST',
					
				})
				await that.getData();
				
				console.log("like")
			},
			//事件处理函数
			bindQueTap() {
				wx.navigateTo({
					url: '../question/question'
				})
			},
			async getComment() {
				var that = this;
				const res = await this.$myRequest({
					url: 'yaj/yan-answer-comment/getComment/' + that.option_id,
				})
				that.commentList = res.data.content
				console.log(that.commentList)
			},

			getData() {
				var that = this;

				uni.request({
					url: app.globalData.url + 'yaj/yan-answer/getAnswer?id=' + that.option_id,
					success: function(res) {
						that.answer = res.data.content

					}
				})



			}

		}
	}
</script>

<style lang="scss">
	page {
		background-color: rgb(221, 175, 248);
	}

	.container {

		font-size: 14rpx;
		/* height: 1200rpx; */
		background: rgb(221, 175, 248);
		color: #000;
		/* border: 1px solid #000; */
	}

	.xian {
		width: 210rpx;
		height: 3rpx;

		border: 1px solid grey;
		margin: 18rpx auto 4rpx;

	}

	rich-text {
		width: 670rpx;
		display: block;
		font-size: 28rpx;
		color: 353535;
		letter-spacing: 1rpx;
	}

	.question {
		padding: 20rpx 50rpx;
		background: white;

		font-size: 38rpx;

		border-radius: 25rpx;
		margin: 15rpx auto;
	}

	.answerer-wrp {

		/* min-height: 400px; */
		border-radius: 25rpx;
		margin: 5rpx auto;
		padding-bottom: 10rpx;
	}

	.answerer-wrp .bg-half {

		background: white;
		border-radius: 25rpx;

	}

	.answerer {
		position: relative;
		margin: 0 auto;
		width: 600rpx;
		height: 90rpx;
		padding: 30rpx;
		border-radius: 3px;
	}

	.answerer .avatar {
		flex: 1;
		width: 90rpx;
		height: 90rpx;
	}

	.answerer .avatar image {
		display: inline-block;
		width: 90rpx;
		height: 90rpx;
		border-radius: 90rpx;
	}

	.answerer .answerer-info {
		flex: 5;
		text-align: left;
		padding: 10rpx 20rpx;
		line-height: 38rpx;
	}

	.answerer .answerer-info text {
		display: block;
	}

	.answerer .answerer-info .answerer-name {
		font-size: 32rpx;
	}

	.answerer .answerer-info .answerer-des {
		font-size: 22rpx;
		color: #808080;
		line-height: 28rpx;

	}

	.answerer-cont {
		width: 95%;
		margin: 3rpx auto;

	}

	.question .follow {
		text-align: right;
		border-radius: 10%;
		padding: 5rpx 8rpx;
		font-size: 22rpx;
		background: rgb(162, 114, 206)
	}

	.que-follow {
		/* border: 1px solid #000; */
		padding: 2rpx 10rpx;
		display: flex;
		justify-content: space-between;
	}

	.que-follow image {
		width: 50rpx;
		height: 50rpx;
		margin: 0 10rpx 0 0;
		vertical-align: middle;
	}

	.answerer .follow text {
		display: inline-block;
		padding: 15rpx 20rpx;
		color: #40bcd0;
		border: solid 2px #40bcd0;
		border-radius: 6rpx;
	}

	.answer-content {
		padding: 30rpx 40rpx;
		margin: 4rpx auto;
	}

	.answer-content text {
		font-size: 32rpx;
		color: #454545;
		line-height: 44rpx;
	}

	.answer-content image {
		width: 100%;
		margin: 20rpx 0;
	}

	.answer-footer {
		position: fixed;
		bottom: 0;
		height: 70rpx;
		width: 600rpx;
		padding: 10rpx 40rpx 5rpx;
		/* border: 1px solid black; */
	}

	.answer-footer .good {
		flex: 1;
		/*display: inline-block;*/
		height: 40rpx;
		border: solid 1px #d1d1d1;
		border-radius: 3px;
		padding: 10rpx 8rpx;
		margin: 10rpx 0;
	}

	.answer-footer .good .good-bad {
		display: inline-block;
	}

	.answer-footer .good image {
		display: inline-block;
		width: 38rpx;
		height: 38rpx;
		vertical-align: middle;
	}

	.answer-footer .good .good-num {
		display: inline-block;
		padding: 10rpx 4rpx;
		/*font-size: 24rpx;*/
	}

	.answer-footer .operation-wrp {
		flex: 5;
	}

	.answer-footer .operation {
		justify-content: space-between;
		/* padding: 0 0 0 60rpx; */
		/* border: 1px solid black; */
	}

	.answer-footer .operation-btn {
		flex: 1;
		text-align: center;
		/* border: 1px solid #000; */
		background: rgb(197, 154, 238);
		border-radius: 18rpx;
		margin: 0px 13rpx;
	}

	.answer-footer .operation image {
		display: block;
		margin: 0 auto;
		width: 50rpx;
		height: 50rpx;

	}

	.answer-footer .operation-btn text {
		display: block;
		font-size: 14rpx;
		color: #white;
	}

	.u-card-wrap {
		background-color: $u-bg-color;
		padding: 1px;
	}

	.u-body-item {
		font-size: 32rpx;
		color: #333;
		padding: 20rpx 10rpx;
	}

	.u-body-item image {
		width: 120rpx;
		flex: 0 0 120rpx;
		height: 120rpx;
		border-radius: 8rpx;
		margin-left: 12rpx;
	}

	.scroll {
		height: 65vh;
	}

	.commentBox {
		width: 88vw;
		border: 1rpx solid white;
		border-radius: 5rpx;
		padding: 8rpx 4rpx;
	}
</style>
