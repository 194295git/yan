/**
 * handle404.js
 */
import store from '../store'
import axios from './axios'
import { REFRESH_TOKEN } from '../store/mutation-types'

let lock = false // 锁
const originRequest = [] // 缓冲

/**
 * 处理401——刷新token并处理之前的请求，目的在于实现用户无感知刷新
 * @param config 之前的请求的配置
 * @returns {Promise<unknown>}
 */
export default function (config) {
  if (!lock) {
    lock = true
    store.dispatch(REFRESH_TOKEN).then(newToken => {
      // 使用新的token替换旧的token，并构造新的请求
      const requests = originRequest.map(callback => callback(newToken))
      // 重新发送请求
      return axios.all(requests)
    }).finally(() => {
      // 重置
      lock = false
      originRequest.splice(0)
    })
  }
  // 关键代码，返回Promise替换当前的请求
  return new Promise(resolve => {
    // 收集旧的请求，以便刷新后构造新的请求，同时由于Promise链式调用的效果，
    // axios(config)的结果就是最终的请求结果
    originRequest.push(newToken => {
      config.headers.authorization = newToken
      resolve(axios(config))
    })
  })
}
