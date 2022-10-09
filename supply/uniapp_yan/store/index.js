import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
	//全局的属性变量，

	state: {
		num: 0,
		price: 10,
		name: '苹果',
		to: '',
		que: '',
		isLogin: false,
		userInfo: {},
		token: '',
		email: '',
		isLogin: false,
		chatId:0
	},

	// 同步方法
	mutations: {
		add(state) {
			state.num++;
			console.log(state.num)
		},
		setToAsQue(state) {
			state.to = 'que'

		},
		setToAsItem(state) {
			state.to = 'item'
		},
		setQueQuestion(state, res) {

			state.que = res
		},
		setLogin(state) {
			state.isLogin = true
		},
		setLogout(state) {
			state.isLogin = false
		},
		setUserInfo(state, res) {
			state.userInfo = res || null;
			uni.setStorageSync('userInfo', res)
		},
		setSignToken(state, res) {
			state.token = res;
			state.islogin = true
			uni.setStorageSync('token', res)
			// sessionStorage.setItem('token', token);
		},
		setEmail(state, res) {
			uni.setStorageSync('email', res)
		},
		setChatId(state, res) {
			uni.setStorageSync('chatId', res)
		},
		clearUserInfo(state) {
			state.userInfo = {};
			state.islogin = false
			uni.removeStorage({
				key: 'userInfo',
				success() {
					console.log('删除成功')
				}
			})
		},
		clearToken(state, res) {
			uni.removeStorage({
				key: 'token',
				success() {
					console.log('删除成功')
				}
			})
			state.token = ''
			state.islogin = false
		}


	},
	//vuex里面的属性计算 也就是会监听属性
	getters: {
		const (state) {
			//这个函数的执行依赖可变的变量
			return state.num * state.price

		}
	},
	//测试异步方法
	actions: {
		testActions(context) {
			//执行一些异步的操作，比如通用的ajax
			setTimeout(function() {

			}, 2000)

		}
	}
})
