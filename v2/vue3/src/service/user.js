
 import  {apiFirstGet,apiLoginUserPost} from '../utils/axios'


 export function login(params) {
   return apiLoginUserPost('/sys/login', params);
 }
 
 export function register(params) {
   return apiLoginUserPost('/sys/registByWeb', params);
 }
 
 export function getUserInfo() {
   return apiFirstGet('/yaj/yan-user/getUserInfo');
 }

 export function getLeaf(params) {
  return apiLoginUserPost('/api/segment/get/leaf-segment-test',params);
}

 export function getUserInfoMe() {
   return apiFirstGet('/yaj/yan-user/getUserInfoMe');
 }