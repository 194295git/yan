package com.rose.file.controller.oss;

/**
 * @author rose
 * @create 2021/8/26
 */

import com.rose.common.base.GenericResponse;
import com.rose.common.base.ServiceError;
import com.rose.file.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file/oss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像(图片)的方法.
     * @param fileve
     * @return
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponse uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        log.info("经过了oss办法");
        String url = ossService.uploadFileAvatar(file);
        if(url==null){
            return GenericResponse.response(ServiceError.OSS_ERROR);
        }else{
            return GenericResponse.response(ServiceError.NORMAL, url);
        }

    }

}