import axios from '../utils/axios'


export function queryEyeUser() {
  return axios.get('/yaj/yan-user/eyeUser');
}

export function getChatContent(params) {
  return axios.get('/yaj/yan-user/getChatContent?toOpenid='+params);
}