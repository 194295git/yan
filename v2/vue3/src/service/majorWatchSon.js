import  {apiFirstGet} from '../utils/axios'


export function searchMajor(id) {
  return apiFirstGet('/yaj/yan-major/getMajor?id=' + id);
}
