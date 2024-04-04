/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2020 陈尼克 all rights reserved.
 * 版权所有，侵权必究！
 */
import axios from 'axios'
import { Toast } from 'vant'
import { setLocal } from '@/common/js/utils'
import router from '../router'

axios.defaults.baseURL = process.env.NODE_ENV == 'development' ? '//127.0.0.1:88' : 'http://192.168.53.110:88'
// axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers['token'] = localStorage.getItem('token') || ''
axios.defaults.headers.post['Content-Type'] = 'application/json'

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
export function apiFirstPost(url,params){
  return axios.post('/api/first'+url, params);
}
export function apiFirstGet(url,params){
  return axios.get('/api/first'+url, params);
}
export function apiLoginUserPost(url,params){
  return axios.post('/api/loginUser'+url, params);
}
export function apiLoginUserGet(url,params){
  return axios.get('/api/loginUser'+url, params);
}
export function apiFileUploadPost(url,params){
  const config = {
    headers: {
      'Content-Type': 'multipart/form-data', // 通常不需要手动设置，由浏览器自动处理
    },
  };

  return axios.post('/api/file'+url, params,config);
}
export default {axios,apiFirstPost,apiFirstGet,apiLoginUserPost,apiLoginUserGet,apiFileUploadPost}

