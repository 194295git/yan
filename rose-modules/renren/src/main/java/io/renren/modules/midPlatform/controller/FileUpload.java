package io.renren.modules.midPlatform.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.utils.R;
import io.renren.modules.midPlatform.common.BaseResponse;
import io.renren.modules.midPlatform.entiry.FormExcelModel;
import io.renren.modules.midPlatform.listener.ModelExcelListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

/**
 * @author rose
 * @create 2022/6/1
 */
@RestController
@RequestMapping("platform/upload")
public class FileUpload {
    @PostMapping("/uploadfile")
    public BaseResponse<JSONObject> uploadfile(MultipartFile file) {
        JSONObject json = new JSONObject();
        // 获取项目下的路劲，新建temp文件夹
        String serverPath = System.getProperty("user.dir") + "/temp/";

        new File(serverPath).mkdirs();
        try {
            //生成不会重复的id
            String uuid = UUID.randomUUID()	.toString();
            // 获取文件后缀
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //获取年月日文件路径
            GregorianCalendar g=new GregorianCalendar();
            String y_m_d=g.get(GregorianCalendar.YEAR)+"_"+(g.get(GregorianCalendar.MONTH)+1)+"_"+g.get(GregorianCalendar.DAY_OF_MONTH);
            //年月日文件路径合到项目路径的temp路径下，检查有没有当天文件夹，没有就新建文件夹
            File filemkdir2=new File(serverPath+"/"+y_m_d);
            if  (!filemkdir2 .exists() && !filemkdir2 .isDirectory()){
                filemkdir2.mkdir();
            }
            String path1 = serverPath+"/" +y_m_d+ "/"  + uuid  + suffixName;
            System.out.println(path1);
            File newFile1 = new File(path1);
            // 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(newFile1);
            json.put("tempPath", "/" +y_m_d+ "/"+uuid + suffixName);
            return setResultSuccess(json);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new BaseResponse<JSONObject>(300, "失败");
    }

    private BaseResponse<JSONObject> setResultSuccess(JSONObject json) {
        return new BaseResponse<JSONObject>(200,"成功",json);
    }

    //根据上传的文件分析并且生成数据
    @PostMapping("/analyExcelAndCreateFrom")
    public R analyExcelAndCreateFrom(@RequestParam String path)  {

        List<FormExcelModel> list = new ArrayList<>();
        try {
            //1.将上传的地址从服务器上面下载下来
            String serverPath = System.getProperty("user.dir") + "/temp";
            InputStream inputStream = new FileInputStream(serverPath+path);// 文件的存放路径
            //2.将文件转入流 将流使用easyexcel来解决读取内容
            list = EasyExcel.read(inputStream,FormExcelModel.class,new ModelExcelListener()).sheet().doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.返回excel 中的内容
        return R.ok().put("data",list);

    }
    @GetMapping("/testPromise")
    public R testPromise()  {
        return R.ok().put("data","12346");
    }


}
