package com.rose.file.controller.excel;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.rose.file.model.FormExcelModel;
import com.rose.file.model.UserExcelModel;

import com.rose.file.util.EasyExcelUtil;
import com.rose.file.util.ExcelImportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "Excel导出导入相关接口" , description = "ExcelExportImport | 评论模块")
public class ExcelExportImport {


    /**
     * 导出excel.
     * @param response
     * @throws Exception
     */
    @ApiOperation("EasyExcel导出数据")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) throws Exception {
        String []columnNames = {"用户名","年龄","手机号","性别"};
        String fileName = "测试泛型方法";
        //模拟构造数据
        String []columnAttrs = {"getName","getAge","getMobile","getSex"};
        List<UserExcelModel> dataList = new ArrayList<>();
        dataList.add(new UserExcelModel("张三",12,"13867098765","男"));
        dataList.add(new UserExcelModel("张三1",12,"13867098765","男"));
        dataList.add(new UserExcelModel("张三2",12,"13867098765","男"));
        dataList.add(new UserExcelModel("张三3",12,"13867098765","男"));
        EasyExcelUtil.exportExcel(response, columnNames, fileName, dataList,columnAttrs);

        return;
    }

    /**
     * poi读取excel
     * @return
     * @throws Exception
     */
    @ApiOperation("poi读取本地数据")
    @GetMapping("/readExcelByPOI")
    public List<List<Object>> readExcel2() throws Exception {
        String filepath = "C:\\Users\\renyun\\Downloads\\easyexcel (3).xlsx";
        FileInputStream inputStream = new FileInputStream(new File(filepath));
        List<List<Object>> list = ExcelImportUtil.getListByExcel(inputStream, filepath);
        System.out.println(list);

        return list;
    }

    /**
     * poi导入excel
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation("poi读取数据接口")
    @PostMapping("/readExcel3")
    public List<List<Object>> readExcel3(@RequestParam("file") MultipartFile file)  throws Exception{
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = ExcelImportUtil.getListByExcel(inputStream, file.getName());
        return list;
    }


    /**
     * easyExcel的读取excel的方式  读取的实体类为UserExcelModel
     * @param file
     * @return
     */
    @ApiOperation("easyexcel读取数据接口")
    @PostMapping("/readExcel")
    public List<UserExcelModel> readExcel(@RequestParam("file") MultipartFile file){
        List<UserExcelModel> list = new ArrayList<>();
        try {
            list = EasyExcel.read(file.getInputStream(),UserExcelModel.class,new ModelExcelListener()).sheet().doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @PostMapping("/readFromExcel")
    public List<FormExcelModel> readFromExcel(@RequestParam("file") MultipartFile file){
        List<FormExcelModel> list = new ArrayList<>();
        try {
            list = EasyExcel.read(file.getInputStream(),FormExcelModel.class,new ModelExcelListener()).sheet().doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static class ModelExcelListener extends AnalysisEventListener {
        private List<Object> datas = new ArrayList<>();
        /**
         * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
         */
        @Override
        public void invoke(Object data, AnalysisContext context) {
            //数据存储到list，供批量处理，或后续自己业务逻辑处理。
            log.info("读取到数据{}",data);
            datas.add(data);
            //根据业务自行处理，可以写入数据库等等
        }

        //所以的数据解析完了调用
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            log.info(datas.toString());
            log.info("所有数据解析完成");
        }
    }

    /**
     * 下载模板 可以正常使用
     */
    @ApiOperation("下载模板【例子】")
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("/excelTemplate/easyexcel.xlsx");
        InputStream inputStream = classPathResource.getInputStream();

        Workbook workbook = new XSSFWorkbook(inputStream);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("easyexcel.xlsx", "utf-8"));
        response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }



}
