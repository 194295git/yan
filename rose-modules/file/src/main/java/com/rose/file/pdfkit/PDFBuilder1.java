package com.rose.file.pdfkit;

/**
 * @author rose
 * @create 2022/12/24
 */


/**
 * @author 小六六
 * @version 1.0
 * @date 2020/4/29 10:35
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

public class PDFBuilder1 extends PdfPageEventHelper {

    private Phrase leftHeader;
    private Phrase rigntHeader;

    public static final int marginX = 90;
    public static final int marginY = 70;

    private static BaseFont baseFont;
    // 生成下划线空白占位符
    private static String Blank;
    // 页眉字体
    private static Font font;
    // 下划线字体
    private static Phrase blankPhrase;

    public PDFBuilder1() {
        //this.leftHeader = new Phrase(header[0], PDFBuilder.font);
        //this.rigntHeader = new Phrase(header[1], PDFBuilder.font);
    }

    static {
        try {
            // 中文字体依赖itext得itext-asian包
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 210; i++) {
                sb.append("\u00a0");
            }
            Blank = sb.toString();
            font = new Font(PDFBuilder1.baseFont, 16, Font.UNDEFINED);
            blankPhrase = new Phrase(PDFBuilder1.Blank, new Font(PDFBuilder1.baseFont, Font.DEFAULTSIZE, Font.UNDERLINE));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param writer
     * @param document
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        int yMargin = -20;
        float top = document.top(yMargin);
        float bottom=document.bottom(-10);
        // 第一页不生成页眉页脚
        if (document.getPageNumber() == 1) {
            return;
        }
        //生成下划线，使用空格占位
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, PDFBuilder1.blankPhrase,
                document.left(-1), top, 0);
//        //生成左侧页眉
//        ColumnText.showTextAligned(writer.getDirectContent(),
//                Element.ALIGN_LEFT, leftHeader,
//                document.left(), top, 0);
//        //生成右侧页眉
//        ColumnText.showTextAligned(writer.getDirectContent(),
//                Element.ALIGN_RIGHT, rigntHeader,
//                document.right(), top, 0);
        ColumnText.showTextAligned(
                writer.getDirectContent(),
                Element.ALIGN_LEFT,
                new Phrase("我是页眉", font),
                document.left(),
                document.top() + 20, 0);

        //生成下划线，使用空格占位
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, PDFBuilder1.blankPhrase,
                document.left(-1), bottom, 0);

        //生成页脚页数
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("我是页尾", PDFBuilder1.font), document.left(), document.bottom(-30), 0);

    }

}


