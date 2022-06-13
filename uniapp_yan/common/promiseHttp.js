import app from './app.js';

const baseurl = app.globalData.url


const token = ()=>{
	const token2 = uni.getStorageSync('token');
	if (token2 === undefined || token2 === '' || token2 === null) {
		return ''
	}else{
		return token2;
	}
}
export const myRequest = (options) => {
	return new Promise((reslove, reject) => {
		const header = ()=>{
			let head = options.header || {}
			if(token()){
				head.token = token()
			}
			return head
		}	
		uni.request({
			url: baseurl + options.url,
			method: options.method || 'GET',
			data: options.data || {},
			header: header(),
			// header: options.header || {},
			success: (res) => {
				if (res.data.statusCode !== 200) {
					return uni.showToast({
						title: "数据获取失败"
					})
				}
				reslove(res)
			},
			fail: (err) => {
				uni.showToast({
					title: "请求接口失败"
				})
				reject(err)
			}
		})
	})
}
