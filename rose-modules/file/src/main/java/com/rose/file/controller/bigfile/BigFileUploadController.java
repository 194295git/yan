package com.rose.file.controller.bigfile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author rose
 * @create 2022/12/24
 */
@Controller
public class BigFileUploadController {
    private final static String utf8 = "utf-8";
    @RequestMapping("/up")
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(utf8);
        //长传时候会有多个分片，需要记录当前为那个分片
        Integer schunk = null;
        //总分片数
        Integer schunks = null;
        //名字
        String name = null;
        //文件目录
        String path = "D:\\file";
        BufferedOutputStream os = null;
        try {
            //设置缓冲区大小  先读到内存里在从内存写
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024);
            factory.setRepository(new File(path));
            //解析
            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置单个大小与最大大小
            upload.setFileSizeMax(5l*1024l*1024l*1024l);
            upload.setSizeMax(10l*1024l*1024l*1024l);
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items){
                if (item.isFormField()){
                    //获取分片数赋值给遍量
                    if ("chunk".equals(item.getFieldName())){
                        schunk = Integer.parseInt(item.getString(utf8));
                    }
                    if ("chunks".equals(item.getFieldName())){
                        schunks = Integer.parseInt(item.getString(utf8));
                    }
                    if ("name".equals(item.getFieldName())){
                        name = item.getString(utf8);
                    }
                }
            }
            //取出文件基本信息后
            for (FileItem item : items){
                if (!item.isFormField()){
                    //有分片需要临时目录
                    String temFileName = name;
                    if (name != null){
                        if (schunk != null){
                            temFileName = schunk+"_"+name;
                        }
                        //判断文件是否存在
                        File temfile = new File(path, temFileName);
                        //断点续传  判断文件是否存在，若存在则不传
                        if (!temfile.exists()){
                            item.write(temfile);
                        }
                    }
                }
            }
            //文件合并  当前分片为最后一个就合并
            if (schunk != null && schunk.intValue()== schunks.intValue()-1){
                File tempFile = new File(path, name);
                os = new BufferedOutputStream(new FileOutputStream(tempFile));
                //根据之前命名规则找到所有分片
                for (int i = 0; i < schunks; i++) {
                    File file = new File(path, i + "_" + name);
                    //并发情况 需要判断所有  因为可能最后一个分片传完，之前有的还没传完
                    while (!file.exists()){
                        //不存在休眠100毫秒后在从新判断
                        Thread.sleep(100);
                    }
                    //分片存在  读入数组中
                    byte[] bytes = FileUtils.readFileToByteArray(file);
                    os.write(bytes);
                    os.flush();
                    file.delete();
                }
                os.flush();
            }
            response.getWriter().write("上传成功");
        }finally {
            try {
                if (os != null){
                    os.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}