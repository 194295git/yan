<template>
	<view>
		<view>
			<view id="top">
				<view class='top'>
					<view class="top_left">
						<view class="top_left_1">
							{{now_year}}/{{now_month}}/{{now_date}}</view>
						<view class='top_left_font'>倒计时</view>
					</view>
					<view class="top_right">
						<u-count-down 
						font-size="160"
						color="white"
						bg-color="#9932CC"
						separator-color="#9932CC"
						:timestamp="timestamp" 
						:show-days="true" 
						:show-hours="false"
						:show-minutes="false"
						:show-seconds="false"
						></u-count-down>
					</view>
				</view>
				<view class='input_around' bindtap="test">
					<input type="text" class="input" placeholder="目标专业院校" />
				</view>

			</view>


			<!-- mid 三大模块入口 -->
			<view class="index_navigation">
				<view class="navigation_son navigation_son_1" @click='open_01'>
					<!-- <i class="fa fa-fort-awesome fa-lg"></i> -->
					<text class='navigation_son_word'>院校专业</text>
				</view>
				<view class="navigation_son navigation_son_2 " @click='open_02' bindtransitionend="navigation_shadow_close">
					<!-- <i class="fa fa-book fa-lg navigation_son_word_2"></i> -->
					<text class='navigation_son_word navigation_son_word_2'>智能推荐</text>
				</view>
				<view class="navigation_son navigation_son_2" @click='open_03'>
					<!-- 看来这个样式 里不能使用这种方法 -->
					<!-- <i class="fa fa-server fa-lg navigation_son_word_2"></i> -->
					<text class='navigation_son_word navigation_son_word_2'>我的课程</text>
				</view>

			</view>


			<view class="scroll-bottom">
				<scroll-view class="scroll" scroll-y='true' bindscrolltolower="lower">

					<view class='bottom'>
						<view class='word d-flex justify-space-between'>
							<view class='pic_word1'>研友讨论热榜</view>
							<view class='pic_word2'>更多</view>
						</view>
						<!-- wx:for='{{feed}}' wx:for-index='idx' data-idx='{{idx}}'  -->
						<view class="box2 notify-item" v-for=" (item,index) in feed " :key='index' @click='getIndex' :data-index='index'>
							<view class='d-flex'>
								<view class=''>
									<u-avatar :src="item.feedSourceImg"></u-avatar>

								</view>
								<view class=''>
									<view class='' @click='bindQueTap'>
										<text>{{item.question}}</text>
									</view>
									<view class=''>
										<view @click='bindItemTap'>
											<text class=''>{{item.answerCtntShort}}</text>
										</view>

									</view>
								</view>
							</view>
						</view>
					</view>
				</scroll-view>
				

			</view>


		</view>
	</view>
</template>

<script>
	import app from '../../common/app.js';
	import http from '../../common/http.js';
	export default {
		data() {
			return {
				timestamp: 0,
				to: '',
				index: '',
				index_data: '', //高考时间
				now_year: '', //当前年
				now_month: '', //当前月
				now_date: '', //当前日
				now_day: '一', //当前星期
				day_Differ: '342', //相差日
				i: 1,
				feed: [],
				url: "https://www.yanzhan.xyz/"
			}
		},
		onLoad(options) {
			this.time_set();
			console.log("是否执行onload函数")
			this.getData(1);
			this.getUserInfo();
			this.computeTime()
			// this.getIndex();

		},
		methods: {
			getUserInfo() {
				var that = this;
				http.request({
					url: 'yaj/yan-user/getUserInfoMe',
					success: function(res) {
						that.userInfo = res.data.content
						that.$store.commit("setUserInfo", res.data.content)
						console.log(that.userInfo)

					}
				})
			},

			open_01: function() {
				uni.navigateTo({
					url: '../major_watch/major_watch',
				})

			},
			open_02: function() {
				uni.navigateTo({
					url: '../province_search/province_search',
				})

			},
			open_03: function() {
				uni.navigateTo({
					url: '../course/course',
				})

			},


			//这个方法跳转的方法就先不整了
			getIndex: function(e) {

				var _index = e.currentTarget.dataset;
				console.log("========_index")
				console.log(_index)

				// //获取当前的这个参数值 是有卖

				var that = this;


				this.$store.commit("setQueQuestion", that.feed[_index.index].question)
				//这个东西commit都可以带着参数传值，并且可以取到，好了，现在其实没有什么大的 开发障碍了
				console.log(this.$store.state.que)


				// //看看这样的方法是否可行
				var a = that.$store.state.to
				// var a = wx.getStorageSync('to')
				console.log("======a=====")
				console.log(a)
				console.log(typeof a)

				if (a == 'que') {
					uni.navigateTo({
						url: '../question/question?id=' + this.feed[_index.index].questionId,
					})

				} else {
					var answerId = this.feed[_index.index].answerId

					uni.navigateTo({
						url: '../answer/answer?id=' + answerId,
					})
				}
			},

			bindQueTap: function(e) {
				// var that = this;

				this.$store.commit("setToAsQue")

				console.log(this.$store.state.to)
				//这块的代码行数是一样的，设置执行哪个方法
				// uni.setStorageSync('to', 'que')

			},
			bindItemTap: function(e) {
				this.$store.commit("setToAsItem")

				// var that = this;
				// uni.setStorageSync('to', 'item')
			},

			lower() {
				var page = parseInt(this.data.i) + parseInt(1)

				this.addData(page);

			},
			test() {
				console.log("test")
			},
			addData(page) {
				var that = this;
				uni.request({
					url: app.globalData.url + 'yaj/yan-data-discovery/getDataDiscoveryPage?pageNo=' + page,
					success: function(res) {

						var add_arr = that.feed.concat(res.data.content.records)

						that.feed = add_arr

					}
				})


			},


			getData(page) {
				console.log("是否走到函数内部")
				var that = this;
				http.request({
					url: 'yaj/yan-question/getDataDiscoveryPage?pageNo=' + page,
					success: function(res) {
						that.feed = res.data.content
						console.log(that.feed)
						// that.setData({
						//   feed: res.data.content.records
						// })
					}
				})

			},
			computeTime(){
				this.timestamp = (1671859090000 - (new Date()).getTime()) /1000
				console.log(this.timestamp)
			},


			time_set: function(e) {
				// var b = wx.getStorageSync('nianfen')
				let that = this;
				let date = new Date();
				var year = date.getFullYear()
				var month = date.getMonth() + 1
				var dates = date.getDate()
				var day = date.getDay()
				that.now_year = year
				that.now_month = month
				that.now_date = dates
				// 微信小程序在取值的时候多了一个data ，赋值的时候多了setData
			},

		}
	}
</script>

<style>
	/* pages/index/index.wxss */
	@import "../lib/style/animation.wxss";

	.scroll-bottom {
		height: 600rpx;
	}

	.scroll {
		height: 60vh;
		padding-bottom: 45rpx;

	}

	#top {
		border: 1px solid #524f50;
		background: #9932CC;
		border-radius: 60rpx;
		margin-top: -45rpx;
	}

	.top {
		display: block;
		margin-top: 60rpx;
	}

	.top_left {
		float: left;
		margin-left: 40rpx;
		padding-left: 40rpx;
		padding-top: 10rpx;
		/* width: 300rpx; */
		margin-top: 20rpx;
	}

	.top_right {
		float: right;
		margin-right: 8vw;
		font-size: 160rpx;
		color: white;
	}

	.top_left_1 {
		color: white;
		font-size: 45rpx;
	}

	.top_left_font {
		font-size: 75rpx;
		color: white;

	}

	.input {
		margin: 0 auto;
		width: 650rpx;
		height: 90rpx;
		padding-left: 25rpx;
		color: gray;
		background: white;
		border: 1px solid #524f50;
		border-radius: 22rpx;
	}


	.input_around {
		display: flex;
		align-items: center;
		position: relative;
		margin: 34rpx auto 90rpx;
		width: 650rpx;
		height: 140rpx;
		border-radius: 26rpx;
		background-repeat: no-repeat;
		background-size: cover;

	}

	.bottom {

		margin-top: 44rpx;
		width: 100%;
		border-radius: 20rpx;
		margin-left: 8rpx;

	}
	.word {
		border-radius: 5rpx;
		height: 50rpx;
		width: 730rpx;
	}

	.pic_word1 {
		font-size: 35rpx;
		color: gray;
		z-index: 1;
		margin-left: 50rpx;
		padding-bottom: 10rpx
	}

	.pic_word2 {
		font-size: 30rpx;
		color: gray;
		width: 80rpx;
		z-index: 1;
		margin-right: 4rpx;
	}

	.index_navigation {

		margin: -85rpx auto 3rpx;
		width: 700rpx;
		display: flex;
		flex-wrap: wrap;
		flex-direction: row;
		justify-content: space-around;
	}

	.index_navigation_slide {
		margin-top: 850rpx;
	}

	.navigation_son {
		height: 180rpx;
		width: 180rpx;
		display: inline-block;
		text-align: center;
		transition: 0.2s;
		position: relative;
		border-radius: 28rpx;

		box-shadow: 0rpx 0rpx #fff;
	}

	.navigation_son_1 {
		background: rgb(155, 17, 247) !important;
	}

	.navigation_son_2 {
		background: white !important;

	}


	.navigation_son_word {
		display: block;
		color: #fff;
		font-size: 32rpx;
		padding-top: 5rpx;
		padding-bottom: 5rpx;
	}

	.navigation_son_word_2 {
		color: #9932CC !important;
	}

	.navigation_son .fa {
		font-size: 80rpx;
		color: #fff;
		margin-top: 50rpx;
	}
	.frist {
		border: dashed 2rpx;
		padding: 82rpx;
		height: 85rpx
	}
	.notify-item{
	  background: #ffffff;
	  padding: 25rpx 10rpx 25rpx 10rpx;
	  border-bottom: solid 1px #ebebeb;
	  /* border: 1px solid black; */
	  border-radius: 18rpx;
	  margin: 5rpx auto;
	
	}
</style>
