package com.rose.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author rose
 * @create 2021/8/26
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
