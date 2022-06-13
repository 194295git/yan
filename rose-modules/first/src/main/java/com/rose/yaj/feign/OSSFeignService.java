package com.rose.yaj.feign;

import com.rose.yaj.common.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rose
 * @create 2022/2/24
 */
@FeignClient("yan-file")
public interface OSSFeignService {

    @PostMapping(value="/file/oss/fileoss",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponse uploadOssFile(@RequestPart("file")MultipartFile file);

}
