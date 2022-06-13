import axios from '../utils/axios'


export function searchMajor(id) {
  return axios.get('/yaj/yan-major/getMajor?id=' + id);
}
