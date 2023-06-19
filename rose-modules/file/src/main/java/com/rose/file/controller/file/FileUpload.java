package com.rose.file.controller.file;


import com.rose.file.util.FileUtils;
import com.rose.file.util.R;
import com.rose.file.util.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;


/**
 * 文件  文件上传功能 上传到服务器的某个位置
 */
@Api(tags = "文件上传相关接口" , description = "FileUpload | 文件上传模块")
@RestController
public class FileUpload {
    private static String[] FILE_SQL = { ".SQL", ".SQL" };
    private static String[] FILE_WORD = { ".DOC", ".DOCX" };
    private static String[] FILE_APK = { ".APK" };
    private static String[] FILE_EXCEL = { ".XLS", ".XLSX" };
    private static String[] FILE_PICTURE = { ".JPG", ".JPEG", ".PNG", ".GIF" };
    private static String[] FILE_ATTACHMENT = { ".JPG", ".JPEG", ".PNG", ".GIF", ".MP4", ".3GP", ".ZIP", ".RAR", ".TXT",
            ".DOC", ".DOCX", ".XLS", ".XLSX", ".PPT", ".PPTX" };


    /**
     * 上传文件接口.
     * @param resourceFiles 前端传递过来的文件
     * @param path   指定的路径
     * @param timeStamp  是否有时间戳
     * @param type  文件类型
     * @return
     */
    @ApiOperation("上传文件接口")
    @PostMapping("/FileUpload")
    public R fileUpload( @RequestParam("files") MultipartFile[] resourceFiles, String path, Boolean timeStamp, byte type) {
        //解析失败会异常跳出

        List<Map<String, Object>> listResult = new ArrayList<>();
        Map<String, Object> itemResult;
        for (MultipartFile itemFile : resourceFiles) {
            //空文件
            //if(itemFile.isEmpty()) continue;
            itemResult = new HashMap<>();
            //原始文件名
            String fileName = itemFile.getOriginalFilename().trim();
            //文件标题
            itemResult.put("fileTitle", fileName);
            //文件后缀变大写
            String suffix = fileName.substring(fileName.lastIndexOf(".")).toUpperCase();
            //文件校验
            for (String fileKey : getCheckFile(type)) {
                //验证成功
                if (suffix.equals(fileKey)) {
                    // 定义文件存储路径，并创建相关文件夹
                    String basePath = FileUtils.getRootPath();
                    System.out.println("basepath"+basePath);
                    String savePath = "/UploadFiles/"+ (!"".equals(path) ? path + "/" : "Temporary/" + TimeUtils.formatDate2String(new Date(),"yyyyMM/"));
                    File fileDir = new File(basePath + savePath);
                    if (!fileDir.exists()) fileDir.mkdirs();
                    // 需要重新命名文件名
                    if (timeStamp) {
                        // 生成统一格式文件名 格式：年月日时分秒毫秒
                        fileName = TimeUtils.formatDate2String(new Date(), "yyyyMMddHHmmssSSS") + suffix.toLowerCase();
                    }
                    // 存放文件对象
                    File file = new File(fileDir.getAbsolutePath(), fileName);
                    try {
                        // 文件上传
                        itemFile.transferTo(file);
                        itemResult.put("filePath", savePath + fileName);
                        listResult.add(itemResult);
                    } catch (Exception e) {
                    }	
                }
            }
        }
        return R.ok().put("data", listResult);
    }
     /**
     * 根据类别获取文件校验后缀数组  1-文档 2-apk 3-表格 4-图片 5-附件
     * @param type 文件类别
     * @return 校验后缀数组
     */ 
    private static String[] getCheckFile(int type) {
        String[] checkFile;
        switch (type) {
            case 1:
                checkFile = FILE_WORD;
                break;
            case 2:
                checkFile = FILE_APK;
                break;
            case 3:
                checkFile = FILE_EXCEL;
                break;
            case 4:
                checkFile = FILE_PICTURE;
                break;
            case 5:
                checkFile = FILE_ATTACHMENT;
                break;
            case 6:
                checkFile = FILE_SQL;
                break;

            default:
                checkFile = new String[0];
        }
        return checkFile;
    }        
}
