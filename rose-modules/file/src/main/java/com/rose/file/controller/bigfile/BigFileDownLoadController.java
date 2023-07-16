package com.rose.file.controller.bigfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author rose
 * @create 2022/12/24
 *
 *  前端调用操作.
 *  <body>
 * <div id="upload-container">
 *     <span>上传</span>
 * </div>
 * <div id="upload-list"></div>
 * <button id="picker">点击上传</button>
 * </body>
 * <script>
 *     $('#upload-container').click(function (event){
 *         $("#picker").find('input').click();
 *     });
 *     var uploader = WebUploader.create({
 *         auto: true,
 *         swf : 'Uploader.swf', //swf文件路径
 *         server: 'http://localhost:8080/upload',
 *         dnd: '#upload-container',
 *         pick: '#picker',  //内部根据当前运行创建
 *         multiple: true,     //选择多个
 *         chunked: true,      //开启分片
 *         threads: 20,        //并发数
 *         method: 'POST',
 *         fileSizeLimit: 1024*1024*1024*10, //单个文件大小限制
 *         fileSingleSizeLimit: 1024*1024*1024,  //总文件大小
 *         fileVal: 'upload'
 *     });
 *     uploader.on("beforeFileQueued",function (file){
 *         console.log(file); //获取文件后缀
 *     });
 *     uploader.on('fileQueued',function (file){
 *         //选中文件要做的事
 *         console.log(file.ext);
 *         console.log(file.size);
 *         console.log(file.name);
 *         var html = '<div class="upload-item"><span>文件名：'+file.name+'</span><span data-file_id="'+file.id+'"class="btn-delete">删除</span><span data-file_id="'+file.id+'"class="btn-retry">重试</span><div class="percentage '+file.id+'" style="width: 0%;"></div></div>'
 *         $('#upload-list').append(html);
 *         uploader.md5File(file)  //给文件定义唯一的md5值，当再次上传相同文件时，就不用传了  大文件秒传实际上是没传，直接拷贝之前文件地址
 *         //显示进度
 *         .progress(function (percentage){
 *             console.log('Percentage:',percentage);
 *         })
 *         //完成
 *         .then(function (val){
 *             console.log('md5 result',val);
 *         });
 *     });
 *
 */
@Controller
public class BigFileDownLoadController {
    private final static String utf8 = "utf-8";
    @RequestMapping("/down")
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(utf8);
        //定义文件路径
        File file = new File("D:\\File\\a.mp4");
        InputStream is = null;
        OutputStream os = null;
        try {
            //分片下载
            long fSize = file.length();//获取长度
            response.setContentType("application/x-download");
            String fileName = URLEncoder.encode(file.getName(),utf8);
            response.addHeader("Content-Disposition","attachment;filename="+fileName);
            //根据前端传来的Range  判断支不支持分片下载
            response.setHeader("Accept-Range","bytes");
            //获取文件大小
            response.setHeader("fSize",String.valueOf(fSize));
            response.setHeader("fName",fileName);
            //定义断点
            long pos = 0,last = fSize-1,sum = 0;
            //判断前端需不需要分片下载
            if (null != request.getHeader("Range")){
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String numRange = request.getHeader("Range").replaceAll("bytes=","");
                String[] strRange = numRange.split("-");
                if (strRange.length == 2){
                    pos = Long.parseLong(strRange[0].trim());
                    last = Long.parseLong(strRange[1].trim());
                    //若结束字节超出文件大小 取文件大小
                    if (last>fSize-1){
                        last = fSize-1;
                    }
                }else {
                    //若只给一个长度  开始位置一直到结束
                    pos = Long.parseLong(numRange.replaceAll("-","").trim());
                }
            }
            long rangeLenght = last-pos+1;
            String contentRange = new StringBuffer("bytes").append(pos).append("-").append(last).append("/").append(fSize).toString();
            response.setHeader("Content-Range",contentRange);
            response.setHeader("Content-Lenght",String.valueOf(rangeLenght));
            os = new BufferedOutputStream(response.getOutputStream());
            is = new BufferedInputStream(new FileInputStream(file));
            is.skip(pos);//跳过已读的文件
            byte[] buffer = new byte[1024];
            int lenght = 0;
            //相等证明读完
            while (sum < rangeLenght){
                lenght = is.read(buffer,0, (rangeLenght-sum)<=buffer.length? (int) (rangeLenght - sum) :buffer.length);
                sum = sum+lenght;
                os.write(buffer,0,lenght);

            }
            System.out.println("下载完成");
        }finally {
            if (is!= null){
                is.close();
            }
            if (os!=null){
                os.close();
            }
        }
    }
}

