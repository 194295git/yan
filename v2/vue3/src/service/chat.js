import axios from '../utils/axios'


export function queryEyeUser() {
  return axios.get('/yaj/yan-user/eyeUser');
}

export function getChatContent(params) {
  return axios.get('/yaj/yan-user/getChatContent?toOpenid='+params);
}


export function getAllGroup() {
  return axios.get('/yaj/yan-group/getAllGroup');
}

export function getGroupOpenid(params) {
  return axios.get('/yaj/yan-group/getGroupOpenid?id='+params);
}
export function getGroupContent(params) {
  return axios.get('/yaj/yan-group/getGroupContent?toGroup='+params);
}
export function getAvatarUrlByOpenid(params) {
  return axios.get('/yaj/yan-user/getAvatarUrlByOpenid?openid='+params);
}