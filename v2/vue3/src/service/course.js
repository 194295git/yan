import axios from '../utils/axios'
export function getCategory() {
  return axios.get('/yaj/yan-product/getCategory');
}