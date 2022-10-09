<template>
	<view>
		<view v-if="current == 0" class="box2 mt-2 ml-2 mb-2">
			<u-cell-group>
				<u-cell-item title="消息(97)" arrow-direction="down">
					<u-icon slot="icon" size="32" name="search"></u-icon>
				</u-cell-item>

			</u-cell-group>
			<u-cell-group class="mt-2">
				<u-search class="mt-2" placeholder="搜索好友" v-model="keyword"></u-search>
			</u-cell-group>

		</view>
		<view v-if="current == 0" class="box2 mb-2">
			<text @click="queryLiveUser">展示所有的用户</text>
			<scroll-view scroll-y="true">
				<view></view>
			</scroll-view>
			<view class="d-flex mb-2 notify-item" @click="changeToUser(index)" v-for="(user,index) in userlist" :key="index">
				<view class="">
					<u-avatar :src="user.avatarUrl"></u-avatar>

				</view>
				<view class="ml-2" style="width: 70vw;">
					{{user.email}}
					<view style="display: flex;justify-content: space-between;" class="d-felx justify-space-between">

						<view class="">
							<text>消息内容</text>
						</view>
						<view v-if="user.noRead !== 0" class="red">
							{{user.noRead}}
						</view>
					</view>
				</view>

			</view>
		</view>
		<view v-if="current == 1" class="box2 mt-2">
			<view class="box2">
				<u-icon name="arrow-left-double" @click="changeCur()"></u-icon>
				<text class="mt-2 ml-2" @click="test">发送人</text>
			</view>
			<scroll-view class="scroll" scroll-y='true' bindscrolltolower="lower">
				<view class="content" v-for="(item,index) in recesiveAllMsg" :key="index">

					<view class="d-felx justify-start " v-if="item.type=== 'self'">
						<view style="display: flex;">
							<u-avatar :size="mini" :src="userInfo.avatarUrl"></u-avatar>

							<view class="font-18 content1">
								<text>{{item.content}}</text>
							</view>
						</view>



					</view>
					<view style="display: flex; justify-content: flex-end;" v-if="item.type=== 'receive'">

						<view class="font-18 content2">
							<text>{{item.content}}</text>

						</view>
						<view class="">
							<u-avatar :size="mini" :src="toUser.avatarUrl"></u-avatar>
						</view>

					</view>


				</view>

			</scroll-view>
			<view class="box2 d-flex align-centers send-msg mt-2">

				<u-input placeholder-style="color: white;" v-model="content" class="box-border-grey" />
				<view class="mt-1">
					<button :size="mini" type="default" @click="sendMsg2()">发送</button>
				</view>


			</view>

		</view>




	</view>
</template>

<script>
	import app from '../../common/app.js'
	import http from '../../common/http.js'
	export default {
		data() {
			return {
				keyword: '搜索好友',
				email: '',
				userlist: [],

				socketTask: null,
				// 确保websocket是打开状态
				is_open_socket: false,
				toUser: {},
				userInfo: {},
				content: '',
				recesiveAllMsg: [],
				current: 0,
				mini: 'mini',
				//关于重连的需要的
				timeout: 40000, // 30s
				timeoutObj: null

			}
		},
		onLoad() {
			// 进入这个页面的时候创建websocket连接【整个页面随时使用】
			this.connectSocketInit();
			//先暂时的关闭心跳
			// this.start();
		},
		// 关闭websocket【必须在实例销毁之前关闭,否则会是underfined错误】
		beforeDestroy() {
			this.closeSocket();
		},

		created() {
			// this.getUserList();
			this.queryEyeUser()
			this.userInfo = this.$store.state.userInfo

		},
		methods: {
			changeCur() {
				console.log(this.recesiveAllMsg)
				this.current = 0
			},
			changeToUser(index) {
				this.email = this.userlist[index].email


				this.toUser = this.userlist[index]
				this.current = 1
				const openid = this.toUser.openid

				var that = this
				http.request({
					url: 'yaj/yan-user/getChatContent',
					data: {
						'toOpenid': openid
					},
					success: function(res) {
						console.log("getChatContent")
						// console.log(res.data.content)
						that.recesiveAllMsg = res.data.content


					}
				})
				this.queryEyeUser()
				// uni.navigateTo({
				// 	url:'../chat/chat'
				// })
			},
			queryEyeUser() {
				var that = this;
				http.request({
					url: 'yaj/yan-user/eyeUser',
					success: function(res) {
						that.userlist = res.data.content
						console.log("userlist")
						console.log(that.userlist)


					}
				})
			},
			queryLiveUser() {
				var that = this;
				http.request({
					url: 'yaj/yan-user/liveUser',
					success: function(res) {
						// that.userlist = res.data.content
					}
				})
			},

			changeLiveStatus(status) {
				http.request({
					url: 'yaj/yan-user/live',
					data: {
						'alive': status
					},
					success: function(res) {

						console.log("改变状态成功")

					}
				})
			},
			//还得专门写个接口让存储数据 要不然达不到和以前一样的效果
			sendMsg2(){
				var that = this;
				const {
					content,
					toUser
				} = this
				let data = {
					// 1代表着私聊的意思
				    type: 1,
				    params: {
						toMessageId: toUser.openid,
				        // toMessageId: toUser.chatId,
				        message: content,
				        fileType: 0,
					},
				};
				that.socketTask.send({
					data: JSON.stringify(data),
					success: res => {
						this.recesiveAllMsg.push({
							'type': 'self',
							'content': content
						})
						//以后不用专门存储数据了
						// this.saveMsg();
						this.content = ''
						
					},
					fail: err => {
						console.log('发送消息失败....');
					}
				});
				
			},
			// 保存消息
			saveMsg() {
				const {
					email,
					content,
					toUser
				} = this
				http.request({
					method: 'POST',
					data: {
						'content': content,
						'toOpenid': toUser.openid,
						'email': email
					},
					url: 'saveChatContent',
					success: function(res) {
						console.log("保存消息成功")
					}
				})
			
			},
			// 发送消息
			sendMsg() {
				const {
					email,
					content,
					toUser
				} = this
				http.request({
					method: 'POST',
					data: {
						'content': content,
						'toOpenid': toUser.openid,
						'email': email
					},
					url: 'push',
					success: function(res) {
						console.log("发送成功")
					}
				})
				this.recesiveAllMsg.push({
					'type': 'self',
					'content': content
				})
				this.content = ''
				console.log(this.recesiveAllMsg)

			},
			connectSocketInit() {

				const that = this;
				const token = uni.getStorageSync('token');
				const email = uni.getStorageSync('email');
				const userInfo = uni.getStorageSync('userInfo');
				// const chatId = uni.getStorageSync('chatId');
				console.log("userInfo",userInfo)
				console.log("{'type':7,'params':{'openid':'"+userInfo.openid+"','userName':'"+email+"'}}")
				// 创建一个this.socketTask对象【发送、接收、关闭socket都由这个对象操作】
				this.socketTask = uni.connectSocket({
					// 【非常重要】必须确保你的服务器是成功的,如果是手机测试千万别使用ws://127.0.0.1:9099【特别容易犯的错误】
					url: app.globalData.nettyws + 'websocket',
					success(data) {
						console.log("websocket连接成功");
					},
				});

				// 消息的发送和接收必须在正常连接打开中,才能发送或接收【否则会失败】
				this.socketTask.onOpen((res) => {
					console.log("WebSocket连接正常打开中...！");
					this.is_open_socket = true;
					// 注：只有连接正常打开中 ，才能正常成功发送消息
				
					this.socketTask.send({

						data: "{'type':7,'params':{'openid':'"+userInfo.openid+"','userName':'"+email+"'}}",
						async success() {
							that.changeLiveStatus(1);
							console.log("消息发送成功");
						},
					});
					// 注：只有连接正常打开中 ，才能正常收到消息
					this.socketTask.onMessage((res) => {
						// 第一种情况，首次连接消息
						if(res.data === '连接成功'){
							return;
						}	
						//第二种情况 收到服务器的回信
						var aaa = JSON.parse(res.data)
						console.log("aaa",aaa)
						console.log(aaa.type)
						//是发消息的情况 直接结束
						if(aaa.type === 0){
							return;
						}
						//收消息的情况，把消息推送上去
						if(aaa.type === 2){
							this.recesiveAllMsg.push({
								'type': 'receive',
								'content': aaa.params.message
							})
							that.recesiveAllMsg.push()	
						}
					
						
						
					});
				})
				// 这里仅是事件监听【如果socket关闭了会执行】
				this.socketTask.onClose(() => {
					console.log("已经被关闭了")
					that.changeLiveStatus(0);
				})
			},
			// 关闭websocket【离开这个页面的时候执行关闭】
			closeSocket() {
				this.socketTask.close({
					success(res) {
						this.is_open_socket = false;
						console.log("关闭成功", res)
					},
					fail(err) {
						console.log("关闭失败", err)
					}
				})
			},
			//下面写关于心跳的方法
			// 启动心跳 start
			start() {
				var that = this;
				this.timeoutObj = setInterval(function() {
					that.socketTask.send({
						data: "{'type': 'ping'}",
						success: res => {
							console.log('连接中....');
						},
						fail: err => {
							console.log('连接失败重新连接....');
							that.connectSocketInit();
						}
					});
				}, this.timeout);
			}




		}
	}
</script>

<style>
	page {
		background-color: rgb(221, 175, 248);
	}

	.scroll {
		height: 70vh;
		padding-bottom: 45rpx;
		border: 1px solid white;

	}

	.content {
		margin-top: 15rpx;
		margin-bottom: 15rpx;
	}

	.content1 {
		padding: 4rpx 12rpx;
		border-radius: 10rpx;
		background-color: white;
		margin-left: 8rpx;
		max-width: 73vw;
	}

	.content2 {
		padding: 4rpx 12rpx;
		margin-right: 8rpx;
		border-radius: 10rpx;
		max-width: 73vw;
		background-color: #FFFF88;
	}

	.send-msg {
		position: fixed;


	}

	.notify-item {

		background: #ffffff;
		padding: 25rpx 20rpx 25rpx 8rpx;
		border-bottom: solid 1px #ebebeb;
		border-radius: 18rpx;


	}

	.red {
		color: white;
		background-color: red;
		border-radius: 50%;
		width: 5vw;
		height: 5vw;
	}
</style>
