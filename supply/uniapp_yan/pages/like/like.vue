<template>
	<view>

		<view class='top-tab flex-wrp flex-tab'>

			<view class='toptab flex-item active' v-for=" (item,index) in navTab " :key="index" @click="switchTab" :data-index='index'>
				{{item}}
			</view>
		</view>
		<scroll-view class='container' scroll-y='true'>

			<view v-show='currentNavtab == 1 ? "" : true'>
				<swiper class='activity' :indicator-dots='indicatorDots' :interval='interval'>
					<block v-for="i in imgUrls">
						<swiper-item>
							<image :src='i' class='slide-image' width='355' height='155'></image>
						</swiper-item>
					</block>
				</swiper>
				<view class="index_greet">
					<view class="front">讨论</view>
					<view id="hover" class="left left_auto" @click="question_submit">发起回答</view>
					<view class="right left_auto" @click="course">寻找课程</view>
					<view class="notify-item flex-wrp">
						<view class="avatar flex-item1">
							<image src="/static/images/icon1.jpeg"></image>
						</view>
						<view class="notify-content flex-item1">
							<text class="notify-source">Rebecca 回答了问题</text>
							<text class="notify-title">#考研择校#我三本学生可以考上贵校的会计专硕吗？</text>
						</view>
					</view>

					<view class="notify-item flex-wrp">
						<view class="avatar flex-item1">
							<image src="/static/images/icon1.jpeg"></image>
						</view>
						<view class="notify-content flex-item1">
							<text class="notify-source">Rebecca 回答了问题</text>
							<text class="notify-title">#考研择校#我三本学生可以考上贵校的会计专硕吗？</text>
						</view>
					</view>
				</view>
				<view class="index_greet">
					<view class="front">资讯</view>
					<view class="notify-item flex-wrp">
						<view class="avatar flex-item1">
							<image src="/static/images/icon1.jpeg"></image>
						</view>
						<view class="notify-content flex-item1">
							<text class="notify-source">Rebecca 回答了问题</text>
							<text class="notify-title">#考研择校#我三本学生可以考上贵校的会计专硕吗？</text>
						</view>
					</view>
				
					<view class="notify-item flex-wrp">
						<view class="avatar flex-item1">
							<image src="/static/images/icon1.jpeg"></image>
						</view>
						<view class="notify-content flex-item1">
							<text class="notify-source">Rebecca 回答了问题</text>
							<text class="notify-title">#考研择校#我三本学生可以考上贵校的会计专硕吗？</text>
						</view>
					</view>
				</view>

			</view>

			<view class='ctnt2 ' v-show='currentNavtab == 0 ? "" : true'>
				<view class="unread">
					<text>3 条未读</text>
					<image src="../../static/images/allread.png"></image>
				</view>
				<view class="notify-item flex-wrp">
					<view class="avatar flex-item1">
						<image src="../../static/images/icon1.jpeg"></image>
					</view>
					<view class="notify-content flex-item1">
						<text class="notify-source">Rebecca 回答了问题</text>
						<text class="notify-title">#考研择校#我三本学生可以考上贵校的会计专硕吗？</text>
					</view>
				</view>
				<view class="notify-item flex-wrp">
					<view class="avatar flex-item1">
						<image src="../../static/images/icon1.jpeg"></image>
					</view>
					<view class="notify-content flex-item1">
						<text class="notify-source">Rebecca 回答了问题</text>
						<text class="notify-title">有哪些考研需要避开的坑？</text>
					</view>
				</view>
				<view class="notify-item flex-wrp">
					<view class="avatar flex-item1">
						<image src="../../static/images/icon1.jpeg"></image>
					</view>
					<view class="notify-content flex-item1">
						<text class="notify-source">Rebecca 回答了问题</text>
						<text class="notify-title">考研冲刺状态如何调整心态</text>
					</view>
				</view>
				<view class="notify-item flex-wrp">
					<view class="avatar flex-item1">
						<image src="../../static/images/icon1.jpeg"></image>
					</view>
					<view class="notify-content flex-item1">
						<text class="notify-source">Rebecca 回答了问题</text>
						<text class="notify-title">大学到底该考研还是该就业？</text>
					</view>
				</view>
				<view class="notify-item flex-wrp">
					<view class="avatar flex-item1">
						<image src="../../static/images/icon1.jpeg"></image>
					</view>
					<view class="notify-content flex-item1">
						<text class="notify-source">Rebecca 回答了问题</text>
						<text class="notify-title">考研买个平板会方便些吗？</text>
					</view>
				</view>
				<view class="notify-item flex-wrp">
					<view class="avatar flex-item1">
						<image src="../../static/images/icon1.jpeg"></image>
					</view>
					<view class="notify-content flex-item1">
						<text class="notify-source">Rebecca 回答了问题</text>
						<text class="notify-title">考研失败什么感觉？</text>
					</view>
				</view>
				<view class="notify-item flex-wrp">
					<view class="avatar flex-item1">
						<image src="../../static/images/icon1.jpeg"></image>
					</view>
					<view class="notify-content flex-item1">
						<text class="notify-source">Rebecca 回答了问题</text>
						<text class="notify-title">C#如何在不覆盖原有文件的情况下直接修改某一部分的内容？</text>
					</view>
				</view>


			</view>

		</scroll-view>




	</view>
</template>

<script>
	export default {
		data() {
			return {
				navTab: ["圈层", "收藏"],
				currentNavtab: '0',
				imgUrls: [
					'/static/images/24213.jpg',
					'/static/images/24280.jpg',
					'/static/images/1444983318907-_DSC1826.jpg'
				],
				indicatorDots: false,
				autoplay: true,
				interval: 5000,
				duration: 1000,
				feed: [],
				index: '',
				feed_length: 0

			}
		},
		methods: {
			switchTab(e) {
				console.log(e.currentTarget.dataset)
				this.currentNavtab = e.currentTarget.dataset.index

			},
			question_submit(e) {
				uni.navigateTo({
					url: '../question_submit/question_submit'
				})
				console.log("跳转")

			},
			course: function() {
				uni.navigateTo({
					url: '../course/course'
				})
			}

		}
	}
</script>

<style>
	/* miniprogram/pages/discovery2/discovery2.wxss */
	.container {
		height: 1500rpx;
		background: rgb(225, 189, 243)
	}

	.top-tab {
		background: rgb(225, 189, 243);
		color: white;
		font-size: 28rpx;
		/* line-height: 100rpx; */
		margin: 0 0 8rpx 0;
		z-index: 9999;
	}

	.flex-item {
		flex-grow: 1;
		text-align: center;
		border-radius: 20rpx;
		background: rgb(225, 189, 243);

		/* border: 1px solid black; */
		width: 180rpx;
		margin: 8rpx;
	}

	.flex-item1 {
		flex-grow: 1;
		text-align: center;
	}

	.toptab.active {
		/* color: #ffffff;
	  border-bottom: solid 2px #ffffff; */

		background: rgb(163, 84, 202);
	}

	.activity {
		width: 750rpx;
		height: 375rpx;
	}

	.activity image {
		width: 750rpx;
		height: 375rpx;
	}

	.add {
		padding: 10px 30px;
		background: #c9c5c9;
		border-radius: 3px;
	}

	/* ============notify========== */
	.unread {
		/* color: #9A9C9B; */
		height: 10rpx;
		padding: 10rpx 40rpx;
		text-align: left;
		width: 670rpx;
	}

	.unread text {
		display: inline-block;
		line-height: 60rpx;
		font-size: 22rpx;
	}

	.unread image {
		display: inline-block;
		width: 50rpx;
		height: 50rpx;
		vertical-align: middle;
		float: right;
	}

	.notify-item {
		width: 640rpx;
		background: #ffffff;
		padding: 25rpx 70rpx 25rpx 0;
		border-bottom: solid 1px #ebebeb;
		/* border: 1px solid black; */
		border-radius: 18rpx;
		margin: 5rpx auto;

	}

	.notify-item image {
		width: 80rpx;
		height: 80rpx;
		border-radius: 80rpx;
	}

	.notify-item .avatar {
		flex: 1;
	}

	.notify-item .notify-content {
		flex: 4;
		text-align: left;
	}

	.notify-item .notify-content text {
		display: block;
	}

	.notify-item .notify-content .notify-source {
		color: #818A8F;
		font-size: 28rpx;
		padding: 0 0 8rpx 0;
	}

	.notify-item .notify-content .notify-title {
		color: #AFAFAF;
		font-size: 30rpx;
		line-height: 34rpx;
	}

	.placehold {
		padding: 20rpx 0;
		font-size: 28rpx
	}

	.index_greet {
		margin-left: 20rpx;
		margin-top: 20rpx;
		width: 710rpx;
		/* height: 250rpx; */
		border-radius: 38rpx;
		background-color: #fff;
		/* border: 1px solid #f4cda5; */
		/* box-shadow: 0rpx 20rpx 50rpx #c9c5c9; */
	}

	.index_greet .front {
		height: 60rpx;
		margin: 6rpx auto;
		padding: 6rpx auto;
		/* border: 1px solid #818A8F; */
		font-size: 38rpx;
		text-align: center;
	}

	.left {
		float: left;
		font-size: 34rpx;
		width: 300rpx;
		margin-left: 10rpx;
		/* border: 1px solid #818A8F; */


	}

	.left_auto {
		text-align: center;
		border: 1px solid #fff888;
		background-color: #fffddc;
		border-radius: 10rpx;

	}

	/* 	#hover:hover{
		background: rgb(174, 134, 219);
		padding: 6rpx auto!important;
	} */
	.left.left_auto:hover {
		background: rgb(174, 134, 219);
		padding: 6rpx auto !important;
	}

	.right.left_auto:hover {
		background: rgb(174, 134, 219);
		padding: 6rpx auto !important;
	}

	.submit {
		background: rgb(174, 134, 219);
		padding: 6rpx auto !important;
	}

	.right {
		float: right;
		font-size: 34rpx;
		width: 300rpx;
		/* border: 1px solid #818A8F; */
		margin-right: 10rpx;
	}

	/* =============== */
	.bottom {
		display: block;
		margin-top: 44rpx;
		width: 100%;
		height: 160px;
		border-radius: 20rpx;
		margin-left: 8rpx;
		/* background: red; */

	}

	.bottom_1 {
		height: 195rpx;



	}

	.feed-item {
		width: 630rpx;
		padding: 20rpx 20rpx;
		height: 152rpx;
		margin: 20rpx auto;
		display: block;
		background: #ffffff;
		border-radius: 25rpx;


	}

	.feed-source {
		float: left;
		width: 80rpx;
		left: 0;
		padding: auto 0px;
		height: 80rpx;
		/* border: 1px solid #524f50 */
	}

	.feed-content {
		width: 520rpx;
		height: 50rpx;
		float: right;
		padding: 10rpx 0 0 0;
		font-size: 28rpx;
		font-weight: 600px;
		line-height: 40rpx;
		color: gray
	}

	.feed-content_subject {
		color: black;
	}
</style>
