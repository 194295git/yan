import app from './app.js';

const baseurl = app.globalData.url
// 请求封装
function request(obj) {
	let url = baseurl + obj.url;
	let data = obj.data || {};
	let success = obj.success;

	let method = obj.method || 'GET';
	const token = uni.getStorageSync('token');
	if (token === undefined || token === '' || token === null) {
		console.log("方法内")
		uni.redirectTo({
			url: '/pages/login/login'
		})
	}

	if (token !== undefined && token !== '' && token !== null) {
		uni.request({
			url: url,
			data: data,
			header: {
				'token': token,
			},
			method: method,
			success: function(res) {
				// 判断token是否过期
				// if (res.data.status == 601) {
				console.log(res.data.statusCode)
				if (res.data.statusCode !== 200 && res.data.statusCode !== -202) {
					console.log("是否走到了!== 200这个方法")
					uni.showModal({
						title: '提示',
						content: res.data.msg,
						showCancel: false,
						success: function(res) {
							if (res.confirm) {
								uni.redirectTo({
									url: '../Login/register'
								})
							}
						}
					})


					return
				}
				if(res.data.statusCode === -202){
					//在这个方法里面处理token过期,当token过期后，请重新登录
					console.log("是否走到了这个方法")
					uni.redirectTo({
						url: '/pages/login/login'
					})
					return
					
				}
				//请求成功后返回数据
				success(res)



			},
			fail: function(res) {},
			complete: function(res) {
				console.log("请求成功")
			},
		})

	}
	// if(token !== null)

}
export default {
	request: request,
};
