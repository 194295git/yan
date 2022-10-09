<template>
	<view>
		<!--pages/major_watch_son/major_watch_son.wxml-->
		<view class='container'>
		  <view class='son_title'>
		    <view class='title_name'>专业名</view>
		    <view class='title_code'>
		      <i class="fa fa-star-o fa-lg"></i>
		      <text>专业代码</text>
		    </view>
		  </view>
		  <view class='son_list'>
		    <view class='major_son' 
				v-for="(item,index) in major" 
				data-key='index' 
				@click='major_watch'>
		      <view class='son_name'>{{item.name}}</view>
		      <view class='son_code'>
		        <text>{{item.code}}</text>
		      </view>
		      <i class="fa fa-chevron-right fa-lg"></i>
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
				 code:'',
				 if2st:true,
				 major: []
			}
		},
		
		onLoad: function (options) {
		    let that = this;
		    if (options.code) {
		      that.code = options.code
		    
		      that.search();
		    }
		},
		methods: {
			major_watch: function (e) {
			    var index = e.currentTarget.dataset.key;
			    uni.navigateTo({
			      url: '../major_watch_3rdson/major_watch_3rdson?major_id=' + this.major[index].code,
			    })
			},
			
			//向后台请求数据
			search: function (e) {
			    let that = this;   //后面存储数据的时候要用到   
			    uni.request({
			      url: app.globalData.url+'yaj/yan-major/getMajor?id=' + that.code,
			      success: function (res) {
					  that.major = res.data.content
					  }
			   
			    })
			}

		}
		
	}
	
	
</script>

<style>
	
	.container{
	  width: 750rpx;
	}
	
	.son_title{
	  width: 750rpx;
	  display: flex;
	  align-items: center;
	  justify-content: space-around;
	  margin-top: 30rpx;
	}
	
	.title_name{
	  color: #3C989E;
	  padding-left: 10rpx;
	  display: flex;
	  align-items: center;
	  border-left: 3px solid #3C989E;
	  font-weight: 800
	}
	
	
	.title_code{
	  color: #88CBCF;
	  font-weight: 800;
	}
	
	.title_code i{
	  margin-right: 20rpx;
	}
	
	.major_son{
	  display: flex;
	  width: 710rpx;
	  padding: 30rpx 20rpx;
	  background: #F4EFEF;
	  margin-top: 20rpx;
	  position: relative;
	  align-items: center;
	}
	
	.son_name{
	  color: #909290;
	  margin-left: 50rpx;
	  font-size: 35rpx;
	  font-weight: 800
	}
	
	.fa-chevron-right{
	  position: absolute;
	  right: 30rpx;
	  color: #55C4B8
	}
	
	.son_code{
	  position: absolute;
	  right: 150rpx;
	  color: #525151;
	  font-size: 30rpx;
	}

</style>
