```js
axios.interceptors.response.use(res => {

  // console.log(res.data)
  if (typeof res.data !== 'object') {
    Toast.fail('服务端异常！')
    return Promise.reject(res)
  }
  if ( res.data.statusCode != 200 ) {
    console.log("进入判断200")
    if (res.data.message) Toast.fail(res.data.message)
    if ( res.data.statusCode == 416) {
      router.push({ path: '/login' })
    }
    if ( res.data.statusCode == -202) {
      router.push({ path: '/login' })
    }
    if (res.data.data && window.location.hash == '#/login') {
      setLocal('token', res.data.data)
      axios.defaults.headers['token'] = res.data.data
    }
    return Promise.reject(res.data)
  }

  return res.data
})
返回结果要是200 并且如果没有token跳转向登录页面
```