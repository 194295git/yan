import  {apiFirstGet} from '../utils/axios'


export function getMajorData(major_ids) {
  return apiFirstGet('/yaj/yan-rem/getQuestionAndType?major_id=' + major_ids);
}
export function getMajorMe() {
  return apiFirstGet('/yaj/yan-rem/getMajorMe' );
}

