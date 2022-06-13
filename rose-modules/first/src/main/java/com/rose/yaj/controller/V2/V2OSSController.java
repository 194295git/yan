package com.rose.yaj.controller.V2;

import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.feign.OSSFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rose
 * @create 2022/2/24
 */
@RestController
@RequestMapping("/V2/eduoss/fileoss")
public class V2OSSController {

    @Autowired
    OSSFeignService ossFeignService;

    @PostMapping
    public GenericResponse uploadOssFile(MultipartFile file) {
        GenericResponse genericResponse = ossFeignService.uploadOssFile(file);
        System.out.println("远程调用成功");
        return genericResponse;
    }
}
