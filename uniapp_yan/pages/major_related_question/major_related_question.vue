<template>
	<view>
		<view v-if="cur === 0">
			<view class="box2 font-bold-16 font-light-purple">
				请回答相关的问题
			</view>


			<view class="box2" v-for=" (item,index) in question " :key='index' :data-index='index'>

				<view class="question">{{(index+1)+" "+item}}</view>

				<view v-show="type[index] == 0">

					<!-- @click="chan1(index)" -->
					<radio-group @change="chan($event,index)">
						<label v-for="select in radioGroup" :key="select">

							<radio class="radio" :value="select" :checked="select==activeRadio" />{{select}}
						</label>
					</radio-group>
				</view>
				<view v-show="type[index] == 1">
					<radio-group@change="chan($event,index)">
						<label v-for="select in radioGroup2" :key="select">
							<radio class="radio" :value="select" :checked="select==activeRadio" />{{select}}
						</label>
						</radio-group>


				</view>


			</view>

			<view class="submit box2" @click="compute">计算得分</view>
		</view>

		<view v-if="cur === 1" v-for="item in result">
			{{result[item+1].name}}
		</view>




	</view>
</template>

<script>
	import app from '../../common/app.js';
	import http from '../../common/http.js';
	export default {
		data() {
			return {
				activeRadio: [],
				radioGroup: ['是', '否'],
				radioGroup2: ['优秀', '良好', '一般'],
				option_id: '',
				question_and_type: {
					"question": '',
					"type": ''
				},
				question: '',
				type: '',
				counts: [],
				counts_sorted: [],
				result: [],
				yanMajorMe: [],
				cur: 0,

			}
		},
		onLoad: function(option) {
			console.log("====option=======")
			console.log(option)

			this.option_id = option.major_ids
			console.log("======option_id=======")
			console.log(this.option_id)
			// this.getData(option.id)
			this.getData();

			this.question_and_type.question = this.question
			this.question_and_type.type = this.type

			this.getYanMajorMe();

		},


		methods: {
			getYanMajorMe() {
				var that = this;
				http.request({
					url: 'yaj/yan-rem/getMajorMe',
					success: function(res) {
						that.yanMajorMe = res.data.content
						console.log(that.yanMajorMe)

					}
				})
			},
			


			compute: function() {
				
				//第一步  先取出 major_ids 
				console.log(this.option_id)
				var ids = this.option_id.split(",")
				console.log(ids)
				var arr = [];

				//第二步  划分每个id的管的question数
				for (var i = 0; i < ids.length; i++) {
					var key = ids[i]
					var _arr = {}
					_arr[ids[i]] = (i + 1) * 5
					arr.push(_arr)


				}


				//第三步  分别对每个id 进行计分
				for (var j = 0; j < arr.length; j++) {
					var element = arr[j]

					var count = 0;
					for (var k = 0; k < this.activeRadio.length; k++) {
						var element2 = this.activeRadio[k]


						var index = Object.keys(element2)[0]
						// console.log("============element2的键================="+index)
						// console.log("====element2====")
						// console.log(element2)
						// console.log("============element的值================="+Object.values(element)[0])
						if (index < Object.values(element)[0] && index >= (Object.values(element)[0] - 5)) {

							if (Object.values(element2)[0] == "是") {
								count = count + 1
							}
							if (Object.values(element2)[0] == "优秀") {
								count = count + 1
							}
							if (Object.values(element2)[0] == "良好") {
								count = count + 0.5
							}


						}
					}

					console.log(count)
					var key = Object.keys(element)[0]
					var object = {};
					object[key] = count

					this.counts.push(object)
					this.counts_sorted.push(count)
				}
				console.log(this.counts)


				//第四步  比较每个id里面的得分，取出前三名；

				//先关联一下得分与count
				var sorted = this.counts_sorted.sort()
				for (var w = 0; w < this.counts.length; w++) {
					var element3 = this.counts[w]
					console.log("Object.values(element3)[0]=>" + Object.values(element3)[0])

					//判断里面的值 取出前三的key
					if (Object.values(element3)[0] == sorted[4]) {
						console.log("sorted[-1]=>" + sorted[4])

						if (this.result.indexOf(Object.keys(element3)[0]) == "-1") {
							this.result.push(Object.keys(element3)[0])
						}

					}
					if (Object.values(element3)[0] == sorted[3]) {
						console.log("sorted[-2]=>" + sorted[3])
						if (this.result.indexOf(Object.keys(element3)[0]) == "-1") {
							this.result.push(Object.keys(element3)[0])
						}
					}
					if (Object.values(element3)[0] == sorted[2]) {
						console.log("sorted[-3]=>" + sorted[2])
						if (this.result.indexOf(Object.keys(element3)[0]) == "-1") {
							this.result.push(Object.keys(element3)[0])
						}
					}


				}
				console.log(this.result)
				
				//当拿到结果后开始跳转页面
				this.cur = 1



			},

			chan(e, index) {

				console.log("====chan=====")
				console.log(e.detail)
				console.log(index)

				var all = {};
				all[index] = e.detail.value

				if (this.activeRadio.length == '0') {

					this.activeRadio.push(all)

				} else {
					console.log('走的是else路线')
					var length = this.activeRadio.length
					var flag = true
					for (var i = 0; i < length; i++) {
						var element = this.activeRadio[i]
						// console.log(element)
						// console.log("观察for循环内部")
						// console.log(index)
						// console.log(Object.keys(element)[0])

						if (index == Object.keys(element)[0]) {
							console.log("是否走到了if")
							element[index] = all[index]
							flag = false
						}

					}

					while (flag) {
						this.activeRadio.push(all)
						flag = !flag
					}



				}


				console.log(this.activeRadio)

			},




			getData: function() {
				var that = this;
				//String major_id = "1,2,3,";  这样子的就行
				uni.request({
					url: app.globalData.url + 'yaj/yan-rem/getQuestionAndType?major_id=' + that.option_id,
					// url: app.globalData.url +'yaj/yan-rem/getQuestionAndType?major_id=',
					success: function(res) {
						var res1 = res.data.content
						that.question = res1.question
						that.type = res1.type


						console.log(res1)


					}
				})
			}



		}
	}
</script>

<style>
	.radio {
		transform: scale(0.7);
		display: inline-block;
		border-radius: 2px;
		margin-right: 5px;
		position: relative;

	}

	.question {
		background: #ffffff;
		padding: 25rpx 70rpx 25rpx 0;
		border-bottom: solid 1px #ebebeb;
		/* border: 1px solid black; */
		border-radius: 18rpx;
		margin: 5rpx auto;
		text-indent: 8rpx;
	}

	.submit {

		background-color: #9932CC;
		margin: 40rpx auto 4rpx;
		text-align: center;
		padding: 20rpx 10rpx;

	}
</style>
