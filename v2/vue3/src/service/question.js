import axios from '../utils/axios'


export function getQuestionData(id) {
  return axios.get('/yaj/yan-question/getQuestionAnswer?id=' + id);
}