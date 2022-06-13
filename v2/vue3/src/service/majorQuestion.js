import axios from '../utils/axios'


export function getMajorData(major_ids) {
  return axios.get('/yaj/yan-rem/getQuestionAndType?major_id=' + major_ids);
}
export function getMajorMe() {
  return axios.get('/yaj/yan-rem/getMajorMe' );
}

