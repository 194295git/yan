import  {apiFirstPost} from '../utils/axios'
export function getQuestionData(id) {
  return apiFirstPost('/yaj/yan-question/getQuestionAnswer?id=' + id);
}