<template>
	<view>
		<u-tabs :list="list" :is-scroll="false" :current="current" @change="change"></u-tabs>
		
		<view v-if="current == 0" class="box">
			推荐课程
		</view>
		<view v-if="current == 1" class="box">
			我的课程
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [{
					name: '推荐课程'
				}, {
					name: '我的课程'
				}],
				current: 0,
				
				
			}
		},
		methods: {
			change(index) {
				this.current = index;
			},
			
		}
	}
</script>

<style>
	page{
	 
	}

	.notify-item{
	  width: 640rpx;
	  background: #ffffff;
	  padding: 25rpx 70rpx 25rpx 0;
	  border-bottom: solid 1px #ebebeb;
	  /* border: 1px solid black; */
	  border-radius: 18rpx;
	  margin: 15rpx auto;
	
	}
	.notify-item  image{
	  width: 80rpx;
	  height: 80rpx;
	  border-radius: 80rpx;
	}
	.notify-item .avatar{
	  flex: 1;
	}
	.notify-item .notify-content{
	  flex: 4;
	  text-align: left;
	}
	.notify-item .notify-content text{
	  display: block;
	}
	.notify-item .notify-content .notify-source{
	  color: #818A8F;
	  font-size: 28rpx;
	  padding: 0 0 8rpx 0;
	}
	.notify-item .notify-content .notify-title{
	  color: #AFAFAF;
	  font-size: 30rpx;
	  line-height: 34rpx;
	}
	
	.inc_change{
	  width: 750rpx;
	  background: #9932CC;
	  display: flex;
	  align-items: center;
	  height: 110rpx;
	  color: #fff;
	  position: fixed;
	  top: 0px;
	  z-index: 5;
	}
	
	.change_college,.change_gongsi{
	  width: 375rpx;
	  display: flex;
	  align-items: center;
	  justify-content: center;
	}
	
	.change_college i,.change_gongsi i{
	  font-size: 62.5rpx;
	  margin: 0 20rpx;
	}
	
	.change_block{
	  position: absolute;
	  width: 200rpx;
	  height: 10rpx;
	  background-color: #FAD68A;
	  bottom: 5rpx;
	  left: 87.5rpx;
	  transition: .4s;
	}
	
	.change_block_right{
	  left: 462.5rpx!important;
	}
	
	.inc_content{
	  width: 750rpx;
	  position: relative;
	}
	
	.inc_college{
	  width: 750rpx;
	  position: absolute;
	  left: 0rpx;
	  top: 110rpx;
	  margin-bottom: 100rpx;
	}
	
	.inc_college_hidden{
	   animation: inc_college_hidden .4s forwards;
	}
	
	.inc_college_back{
	  animation: inc_college_back .4s forwards;
	}
	
	.inc_company{
	  width: 750rpx;
	  position: absolute;
	  height: 700rpx;
	  background-color: #fff;
	  /* right: -750rpx; */
	  margin-bottom: 100rpx;
	  top: 110rpx;
	}
	
	.inc_company_hidden{
	  animation: inc_company_hidden .4s forwards;
	}
	
	.inc_company_back{
	  animation: inc_company_back .4s forwards;
	}
	
	.college_search{
	  width: 730rpx;
	  margin-left: 10rpx;
	  display: flex;
	  align-items: center;
	  margin-top: 5rpx;
	  border: 1px solid #FAD68A;
	  box-shadow: 0rpx 10rpx 50rpx #BCA3A3;
	}
	
	.search_button{
	  width: 100rpx;
	  height: 70rpx;
	  display: flex;
	  justify-content: center;
	  align-items: center
	}
	
	
	.college_search i{
	  font-size: 50rpx;
	  color: #696666;
	}
	
	.search_input{
	  width: 600rpx;
	  padding-left: 30rpx;
	  height: 50rpx;
	  padding-top: 10rpx;
	  padding-bottom: 10rpx;
	  font-size: 30rpx;
	  letter-spacing: 1px;
	}
	
	.college_inc{
	  display: flex;
	  width: 750rpx;
	  flex-direction: column;
	  margin-top: 15rpx;
	}
	
	.inc_son{
	  position: relative;
	  width: 750rpx;
	  height: 208rpx;
	  border-bottom: 2px solid #BBBBBB;
	}
	
	movable-area{
	  position: absolute;
	}
	
	
	.zone_s{
	  width: 100rpx;
	}
	
	.son_out{
	  z-index: 3;
	  width: 750rpx;
	  height: 208rpx;
	  background: #fff;
	  display: flex;
	  flex-wrap: wrap;
	  align-items: center;
	}
	
	.son_out i{
	  display: block;
	  color: #FFA960;
	  font-size: 100rpx;
	  margin-left: 45rpx;
	  transform:rotate(0deg);
	  transition: .3s;
	}
	
	.out_logo image{
	  height: 166rpx;
	  width: 166rpx;
	  display: blcok;
	}
	
	.out_logo{
	  position: relative;
	  height: 166rpx;
	  width: 166rpx;
	  border-radius: 50%;
	  overflow: hidden;
	  background: #fff;
	  margin-left: 20rpx;
	}
	
	.out_type{
	  padding: 16rpx 24rpx;
	  background-color: #3C989E;
	  color: #fff;
	  position: absolute;
	  bottom: 10rpx;
	  right: 20rpx;
	}
	
	.out_name{
	  margin-left: 30rpx;
	  height: 120rpx;
	}
	
	.name_chinese{
	  width: 400rpx;
	  font-size: 40rpx;
	  margin-bottom: 30rpx;
	  display: block;
	  color: #525050;
	}
	
	.name_english{
	  width: 400rpx;
	  font-size: 25rpx;
	  display: block;
	  color: #857E7E;
	  font-style: oblique;
	  letter-spacing: 1px;
	}
	
	.son_in{
	  position: relative;
	  z-index: 1;
	  width: 750rpx;
	  height: 208rpx;
	
	  background-repeat: no-repeat;
	  background-size: cover;
	  background-position: 60% 60%;
	}
	
	.in_out{
	  background: linear-gradient(90deg, #6b87ad,#7bb2a4);
	  opacity: .8;
	  width: 750rpx;
	  height: 208rpx;
	  position: absolute;
	  z-index: 1;
	}
	
	.in_information{
	  position: relative;
	  width: 670rpx;
	  z-index: 2;
	  height: 208rpx;
	  display: flex;
	  flex-wrap: wrap;
	  margin-left: 80rpx;
	}
	
	.information{
	  display: flex;
	  align-items: flex-start;
	  flex-shrink: 0;
	  margin-top: 20rpx;
	  margin-left: 30rpx;
	}
	
	.front{
	  display: inline-block;
	  color: #fff;
	  font-size: 30rpx;
	  height: 40rpx;
	  padding-left: 5rpx;
	  border-left: 5px solid #FAD68A;
	}
	
	.behind{
	  max-width: 450rpx;
	  margin-left: 20rpx;
	  display: inline-block;
	  color: #fff;
	  font-size: 30rpx;
	}
	
	.chevron_ani{
	  animation: chevron 3s forwards;
	}
	
	.chevron_roate{
	  transform:rotate(180deg)!important;
	}
	
	
	
	.son_introduce{
	  text-indent:2em;
	  width: 710rpx;
	  min-height:208rpx;
	  background-color: #F3F3F3;
	  font-size: 30rpx;
	  line-height: 45rpx;
	  letter-spacing: 1px;
	  padding: 20rpx;
	  color: #676363
	}
	
	.information_logo image{
	  width: 160rpx;
	  height: 160rpx;
	}
	
	.information_logo{
	  width: 160rpx;
	  height: 160rpx;
	  overflow: hidden;
	  border-radius: 50%;
	  margin-left: 30rpx;
	}
	
	.son_information{
	  width: 750rpx;
	  height: 208rpx;
	  background: #fff;
	  display: flex;
	  align-items: center;
	  position: relative
	}
	
	.information_name{
	  margin-left: 30rpx;
	  display: flex;
	  flex-direction: column;
	}
	
	.name_name{
	  color: #525050;
	  margin-bottom: 20rpx;
	  font-size: 40rpx;
	}
	
	.name_adress{
	  color: #857E7E;
	  font-size: 25rpx;
	}
	
	.son_information i{
	  display: block;
	  color: #FFA960;
	  font-size: 100rpx;
	  position: absolute;
	  right: 10rpx;
	}
	
	.company_son{
	  border-bottom: 2px dashed  #F57A82;
	  margin-bottom: 30rpx;
	}
	
	.curtain{
	  position: fixed;
	  height: 100%;
	  width: 100%;
	  z-index: 15;
	  top: 0px;
	  left: 0rpx;
	
	}

</style>
