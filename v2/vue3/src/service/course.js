import  {apiFirstGet} from '../utils/axios'
export function getCategory() {
  return apiFirstGet('/yaj/yan-product/getCategory');
}