<template>
	<view>
		<view id='container'>
			<view class='province_list'>

				<view class="question-wrp">
					<view class="title font-18">请描述你对学校的期望</view>
						<textarea class="placeholder" name="queContent" placeholder="   对目标院校进行描述补充" v-model="desc" />
					</view>
		
		  <view>
		    <view 
				class="submit box2"
				@click="getMajor"
			>
			提交信息</view>
		
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
				desc: '',
				majorArr: [],
				major: [
				      { name: '法学', id:'1' },
					  { name: '会计学', id:'2' },
					  { name: '金融学', id:'3' },
					  { name: '工商管理', id:'4' },
					  { name: '商务英语', id:'5' },
					  { name: '心理学', id:'6' },
					  { name: '经济学', id:'7' },
					  { name: '管理科学与工程', id:'8' },
					  { name: '新闻', id:'9' },
					  { name: '传播', id:'10' },
					  { name: '广告', id:'11' },
					  { name: '计算机应用技术', id:'12' },
					  { name: '临床医学', id:'13' },
					  { name: '机械设计制造及其自动化', id:'14' }
				    ],
				options: []
				
			}
		},
		methods: {
			getMajor:function(){
				var that = this;
				console.log(that.desc)
				uni.request({
				  url: app.globalData.url+'getMajor?text='+this.desc,
				  // url: app.globalData.url+'getMajor?text='+'我喜欢计算机，不喜欢法学',
				  success: function (res) { 
					console.log(res.data.success)
						
					if(res.data.success == 'true'){
						
						var majorArr =res.data.content
						console.log(majorArr)
							
						that.majorArr = majorArr
						console.log(that.majorArr)
						
						
						// todo  需要把 optionid个传递过去，这边需要进行一下过滤；拼接'1,2,3,'
						for (var i = 0; i < that.majorArr.length; i++) {
							for (var j = 0; j < that.major.length; j++) {
									
								// console.log(that.majorArr[i])
								// console.log(that.major[j])
								if(that.majorArr[i] == that.major[j].name){
									that.options.push(that.major[j].id) 
									break;
								}
								
								
							}
								
						}
						console.log("=======that.options========")
						
						console.log(that.options)
						var major_ids = '';
						
						major_ids = that.options.join(",")
						console.log(major_ids)
						uni.navigateTo({
							url: '../major_related_question/major_related_question?major_ids='+major_ids
						})
					}else{
						uni.navigateTo({
							url: '../major_related_question/major_related_question?major_ids=1,2,3,7,8'
						})
								
						
						
						
						
						
						
					}
								 
				    
								  
				  }
				})
						
			}
			
		}
	}
</script>

<style>
	#container {
	  width: 750rpx;
	  margin-bottom: 300rpx;
	}
	
	.title{
	  margin: 4rpx 70rpx ;
	  color: grey;
	}
	.question-wrp{
	  width: 96%;
	  margin: 25rpx auto;
	  border-radius: 12px;
	  box-shadow: 0 1px 2px #bebebe;
	  background: white;
	  /* border: 1px solid black; */
	}
	.placeholder{
	  text-indent:30rpx ;
	  width: 550rpx;
	}
		
	.submit{

	  background-color: #9932CC;
	  color: white;
	  margin: 40rpx auto 4rpx;
	  text-align: center;
	  padding: 10rpx;
		
	}
	.list_row {
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	  margin-top: 30rpx;
	  padding-bottom: 50rpx;
	  border-bottom: 1px solid #6b6a6a;
	}
	
	.list_col {
	  width: 220rpx;
	  text-align: center;
	  color: #6b6a6a;
	  font-size: 40rpx;
	  position: relative;
	}
	
	.province_list {
	  width: 660rpx;
	  display: flex;
	  flex-direction: column;
	  margin: 0 auto;
	}
	
	.row_border {
	  border-width: 0px !important;
	}
	
	.list_border {
	  border-right: 1px solid #6b6a6a;
	}
	
	.list_col text{
	  display: block;
	  position: relative;
	  z-index: 3
	}
	
	
	.list_shock{
	  animation: list_shock .4s forwards;
	}

</style>
