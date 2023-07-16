import  {apiFirstGet} from '../utils/axios'
export function getQuestionData(id) {
  return apiFirstGet('/yaj/yan-question/getQuestionAnswer?id=' + id);
}