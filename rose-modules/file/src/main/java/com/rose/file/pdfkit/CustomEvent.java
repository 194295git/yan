package com.rose.file.pdfkit;

/**
 * @author rose
 * @create 2022/12/24
 */


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

/**
 * @author 小六六
 * @version 1.0
 * @date 2020/4/29 11:14
 */
public class CustomEvent extends PdfPageEventHelper {

    private String waterMark;

    public CustomEvent(String waterMark) {
        this.waterMark = waterMark;
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        try {
            // 加入水印
            PdfContentByte waterMar = writer.getDirectContentUnder();
            // 开始设置水印
            waterMar.beginText();
            // 设置水印透明度
            PdfGState gs = new PdfGState();
            // 设置填充字体不透明度为0.2f
            gs.setFillOpacity(0.1f);
            // 设置水印字体参数及大小
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.EMBEDDED);
            waterMar.setFontAndSize(baseFont,60);
            // 设置透明度
            waterMar.setGState(gs);
            // 设置水印对齐方式 水印内容 X坐标 Y坐标 旋转角度
            waterMar.showTextAligned(Element.ALIGN_CENTER, waterMark , 300, 500, 45);
            //结束设置
            waterMar.endText();
            waterMar.stroke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

