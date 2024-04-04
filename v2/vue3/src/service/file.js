import  {apiFileUploadPost} from '../utils/axios'

export function uploadPost(params) {
  console.log("apiFileUploadPost",params)
  return apiFileUploadPost('/file/oss/fileoss',params);
}
