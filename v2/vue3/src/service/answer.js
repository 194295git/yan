import axios from '../utils/axios'


export function getAnswer(id) {
  return axios.get('/yaj/yan-answer/getAnswer?id=' + id);
}
export function getComment(id) {
  return axios.get('/yaj/yan-answer-comment/getComment/' + id);
}