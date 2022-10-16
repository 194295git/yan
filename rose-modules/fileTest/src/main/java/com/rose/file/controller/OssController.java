package com.rose.file.controller;

/**
 * @author rose
 * @create 2021/8/26
 */

import com.rose.common.base.GenericResponse;
import com.rose.common.base.ServiceError;
import com.rose.file.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file/oss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像(图片)的方法.
     * @param file
     * @return
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponse uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        System.out.println("经过了oss办法");
        String url = ossService.uploadFileAvatar(file);
        if(url==null){
            return GenericResponse.response(ServiceError.OSS_ERROR);
        }else{
            return GenericResponse.response(ServiceError.NORMAL, url);
        }

    }

}