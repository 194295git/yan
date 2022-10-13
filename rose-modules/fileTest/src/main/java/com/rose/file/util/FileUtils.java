package com.rose.file.util;


import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 文件工具类
 */
public class FileUtils {

    /**
     * 目录根路径
     */
    private static String BASE_PATH;
    public static void init(String path) {
        BASE_PATH = path;
    }
    
    /**
     * 获取根目录根路径 （根目录为/static/层）
     * 
     * @return 返回目录目录 失败时抛出异常 
     */
    public static String getRootPath() {
        try {
            return ResourceUtils.getURL("classpath:").getPath() + "static/";
        } catch (FileNotFoundException e) {
            throw new SRException("获取根目录失败",e);
        }
        // return BASE_PATH;
    }

    /**
     * 删除路径下所有文件
     * 
     * @param filePath 文件路径  例：文件UploadFiles/xxx/xxx/xxx.jsp  或文件夹UploadFiles/xxx/xxx
     */
    public static void delFile(String filePath) {
        delFile(new File(getRootPath(), filePath));
    }

    /**
     * 删除所有文件
     * 
     * @param file 文件对象
     */
    public static void delFile(File file) {
        if (file != null && file.exists()) {
            if(file.isDirectory()){
                for (File childFile : file.listFiles()) {
                    //递归删除所有子目录
                    delFile(childFile);
                }
            }
            file.delete();
        }
    }

}
