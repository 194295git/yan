import  {apiFirstGet} from '../utils/axios'


export function queryEyeUser() {
  return apiFirstGet('/yaj/yan-user/eyeUser');
}

export function getChatContent(params) {
  return apiFirstGet('/yaj/yan-user/getChatContent?toOpenid='+params);
}


export function getAllGroup() {
  return apiFirstGet('/yaj/yan-group/getAllGroup');
}

export function getGroupOpenid(params) {
  return apiFirstGet('/yaj/yan-group/getGroupOpenid?id='+params);
}
export function getGroupContent(params) {
  return apiFirstGet('/yaj/yan-group/getGroupContent?toGroup='+params);
}
export function getAvatarUrlByOpenid(params) {
  return apiFirstGet('/yaj/yan-user/getAvatarUrlByOpenid?openid='+params);
}
export function getGroupMemberDetail(params) {
  return apiFirstGet('/yaj/yan-group/getGroupMemberDetail?id='+params);
}

