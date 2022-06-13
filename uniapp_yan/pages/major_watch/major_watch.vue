<template>
	<view>
		<view class='container'>
		  <view class='watch_list'>
		  
		    <view class='list_major' 
			v-for=" (item,index) in major "
			:key='index'
			:data-index='index'
			:id="major[index].class"
			@click="major_son_watch"
			>
		        <view class='curtain'></view>
		        <view class='major_name'>{{item.name}}</view>
		        <view class='major_code'>code: {{item.code}}</view>
		    </view>
		  </view>
		</view>
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				major: [
				      { name: '哲学', code: '01',id:'1' ,class:'major1'},
				      { name: '经济学', code: '02', id: '2',class:'major2'},
				      { name: '法学', code: '03', id: '3',class:'major3'},
				      { name: '教育学', code: '04', id: '4',class:'major4'},
				      { name: '文学', code: '05', id: '5',class:'major5'},
				      { name: '历史学', code: '06', id: '6',class:'major6'},
				      { name: '理学', code: '07', id: '7',class:'major7'},
				      { name: '工学', code: '08', id: '8',class:'major8'},
				      { name: '农学', code: '09', id: '9',class:'major9'},
				      { name: '医学', code: '10', id: '10',class:'major10'},
				      { name: '军事学', code: '11', id: '11',class:'major11'},
				      { name: '管理学', code: '12', id: '12' ,class:'major12'},
				      { name: '艺术学', code: '13', id: '13' ,class:'major13'},
				    ]
				
			}
		},
		methods: {
			
			 major_son_watch:function(e){
			    var index = e.currentTarget.dataset.index;
				console.log(index)
			      uni.navigateTo({
			        url: '../major_watch_son/major_watch_son?code=' + this.major[index].id,
			      })
			  }
			
		}
	}
</script>

<style>
	.container{
	  width: 750rpx;
	  display: flex;
	  justify-content: center;
	  margin-bottom: 200rpx;
	}
	
	.watch_list{
	  display: flex;
	  width: 630rpx;
	  flex-wrap: wrap;
	  justify-content: space-between
	}
	
	.list_major{
	  width: 300rpx;
	  height: 185rpx;
	  background: black;
	  margin-top: 20rpx;
	  border-radius: 20rpx;
	  color: #fff;
	  display: flex;
	  justify-content: center;
	  flex-direction: column;
	  position: relative;
	  overflow: hidden
	}
	.curtain{
	  position: absolute;
	  width: 100%;
	  height: 100%;
	}
	
	.major_name,.major_code{
	  text-align: center;
	  position: relative;
	  z-index: 3;
	  font-weight: 800;
	}
	
	#major1{
	
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 30%;
	}
	
	.major_code{
	  font-size: 40rpx;
	  font-weight: 800;
	}
	
	#major2{
	
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 100%;
	}
	
	#major2 .curtain{
	   background: linear-gradient(120deg, rgba(245, 122, 178, 0.3), #f4cda5);
	   z-index: 1;
	}
	
	#major2 .major_name{
	  font-weight: 800;
	  font-size: 40rpx;
	}
	
	#major3{
	
	  background-repeat: no-repeat;
	  background-size: 100%;
	  background-position: 50% 30%;
	}
	
	#major4 .curtain{
	   background: linear-gradient(120deg, rgba(85, 196, 184, 0.3), #f4cda5);
	   z-index: 1;
	}
	
	#major4{
	
	  background-repeat: no-repeat;
	  background-size: 150%;
	  background-position: 50% 80%;
	}
	
	#major5{
	
	  background-repeat: no-repeat;
	  background-size: 100%;
	  background-position: 50% 80%;
	}
	
	#major5 .curtain{
	   background: linear-gradient(120deg,#f4cda5, rgba(85, 196, 184, 0.3));
	   z-index: 1;
	}
	#major5 .major_name{
	  font-weight: 800;
	  font-size: 40rpx;
	}
	
	#major6{
	  
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 0%;
	}
	
	#major7{
	
	  background-repeat: no-repeat;
	  background-size: 150%;
	  background-position: 50% 10%;
	}
	
	#major8{
	 
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 10%;
	}
	
	#major9{
	
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 10%;
	}
	
	#major10{
	 
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 10%;
	}
	
	#major10 .curtain{
	   background: linear-gradient(120deg,#f4cda5, rgba(85, 196, 184, 0.3));
	   z-index: 1;
	}
	
	#major11{
	
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 10%;
	}
	
	#major11 .curtain{
	   background: linear-gradient(120deg,#F8D17E, rgba(237, 82, 118, 0.3));
	   z-index: 1;
	}
	
	#major12{
	
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 10%;
	}
	
	#major12 .curtain, #major1 .curtain,#major3 .curtain,#major6 .curtain,#major8 .curtain,#major7 .curtain,#major9 .curtain{
	   background: linear-gradient(120deg,rgba(60, 152, 158, 0.3),rgba(237, 82, 118, 0.3));
	   z-index: 1;
	}
	
	#major13{
	  /*background-image: url(http://www.mtdg.club/wwwroot/ftp/裕祺/images/gaokao/major12.jpg);*/
	  background-repeat: no-repeat;
	  background-size: 120%;
	  background-position: 50% 10%;
	}

</style>
