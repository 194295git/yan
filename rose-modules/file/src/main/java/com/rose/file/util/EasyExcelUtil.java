package com.rose.file.util;

import com.rose.file.model.UserExcelModel;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author rose
 * @create 2022/9/4
 */
public class EasyExcelUtil {

    public static <T> void exportExcel(HttpServletResponse response, String[] columnNames, String fileName, List<T> dataList,
                                   String[] columnAttrs) throws IOException {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        XSSFCellStyle titleStyle = getTitleStyle(workbook);
        //设置标题
        setTitle(columnNames, sheet, titleStyle);
        //设置内容
        setContent(dataList, columnAttrs, sheet);
        //将流导出
        exportStream(response, fileName, workbook);
        return;
    }

    private static <T> void setContent(List<T> dataList, String[] columnAttrs, Sheet sheet) {
        //创建数据行并写入值
        for (int j = 0; j < dataList.size(); j++) {
            T t = dataList.get(j);
            int lastRowNum = sheet.getLastRowNum();
            Row dataRow = sheet.createRow(lastRowNum + 1);
            try {
                for (int i = 0; i < columnAttrs.length; i++) {
                    dataRow.createCell(i).setCellValue(t.getClass().getMethod(columnAttrs[i]).invoke(t).toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
    }

    private static void exportStream(HttpServletResponse response, String fileName, XSSFWorkbook workbook) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName+".xls", "utf-8"));
        response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private static void setTitle(String[] columnNames, Sheet sheet, XSSFCellStyle titleStyle) {
        Row titleRow = sheet.createRow(0);

        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(titleStyle);
        }
    }

    /**
     * 获取标题样式.
     * @param workbook 工作表
     * @return
     */
    private static XSSFCellStyle getTitleStyle(XSSFWorkbook workbook) {
        Font titleFont = workbook.createFont();
        titleFont.setFontName("simsun");
        titleFont.setBold(true);
        titleFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }
}
