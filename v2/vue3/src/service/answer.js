import  {apiFirstGet} from '../utils/axios'


export function getAnswer(id) {
  return apiFirstGet('/yaj/yan-answer/getAnswer?id=' + id);
}
export function getComment(id) {
  return apiFirstGet('/yaj/yan-answer-comment/getComment/' + id);
}