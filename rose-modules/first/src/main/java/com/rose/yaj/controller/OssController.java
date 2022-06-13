package com.rose.yaj.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;
    //上传头像的方法
    @PostMapping
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