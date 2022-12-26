//package com.rose.file.util;
//
///**
// * @author rose
// * @create 2022/12/24
// */
//
//import com.google.common.collect.Lists;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.exception.ExceptionUtils;
//import pdf.kit.component.PDFHeaderFooter;
//import pdf.kit.component.PDFKit;
//import pdf.kit.component.chart.ScatterPlotChart;
//import pdf.kit.component.chart.model.XYLine;
//import pdf.kit.component.chart.impl.DefaultLineChart;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * 360报告
// *
// */
//@Slf4j
//public class ReportKit360 {
//
//    public static List<XYLine> getTemperatureLineList() {
//        List<XYLine> list= Lists.newArrayList();
//        for(int i=1;i<=7;i++){
//            XYLine line=new XYLine();
//            float random=Math.round(Math.random()*10);
//            line.setXValue("星期"+i);
//            line.setYValue(20+random);
//            line.setGroupName("下周");
//            list.add(line);
//        }
//        for(int i=1;i<=7;i++){
//            XYLine line=new XYLine();
//            float random=Math.round(Math.random()*10);
//            line.setXValue("星期"+i);
//            line.setYValue(20+random);
//            line.setGroupName("这周");
//            list.add(line);
//        }
//        return list;
//    }
//
//    public  String createPDF(String templatePath,Object data, String fileName){
//        //pdf保存路径
//        try {
//            //设置自定义PDF页眉页脚工具类
//            PDFHeaderFooter headerFooter=new PDFHeaderFooter();
//            PDFKit kit=new PDFKit();
//            kit.setHeaderFooterBuilder(headerFooter);
//            //设置输出路径
//            kit.setSaveFilePath("./hello.pdf");
//
//            String saveFilePath=kit.exportToFile(fileName,data);
//            return  saveFilePath;
//        } catch (Exception e) {
//            log.error("PDF生成失败{}", ExceptionUtils.getFullStackTrace(e));
//            return null;
//        }
//
//    }
//
//    public static void main(String[] args) {
//
//        ReportKit360 kit=new ReportKit360();
//        TemplateBO templateBO=new TemplateBO();
//        templateBO.setTemplateName("考前-真题+考前");
//        templateBO.setFreeMarkerUrl("A 正确");
//        templateBO.setITEXTUrl("我们去了猴山，那里有很多的猴子，都是红屁股，有的在玩荡秋千，有的在避雨，有的在自得其乐地挠痒痒............真可爱。还有一只最威武的猴王，它站在最高的山头上，一动也不动地站着，它在看着这一群调皮的小猴子们，不让它们受到大猴子们的欺负 在动物园里，我还看到了很多别的动物。比如说活泼可爱的小熊猫、大熊猫;漂亮大方的丹顶鹤、孔雀;威风凛凛的狮子、老虎;光滑无比的海狮、海豹;还有让人害怕的鳄鱼;庞然大物般的大象;懒惰的黑熊;凶狠的狼和金钱豹;有着美丽花纹的长颈鹿、梅花鹿、斑马等等............这些动物我都很喜欢哦!0");
//        templateBO.setJFreeChartUrl("B 错误");
//        List<String> scores=new ArrayList<String>();
//        scores.add("90");
//        scores.add("95");
//        scores.add("98");
//        templateBO.setScores(scores);
////        List<XYLine> lineList=getTemperatureLineList();
////        DefaultLineChart lineChart=new DefaultLineChart();
////        lineChart.setHeight(500);
////        lineChart.setWidth(300);
////        String picUrl=lineChart.draw(lineList,0);
////        templateBO.setPicUrl(picUrl);
////
////        //散点图
////        String scatterUrl=ScatterPlotChart.draw(ScatterPlotChartTest.getData(),1,"他评得分(%)","自评得分(%)");
////        templateBO.setScatterUrl(scatterUrl);
//        String templatePath="E:\\code\\pdf-kit\\src\\test\\resources\\templates";
//        String path= kit.createPDF(templatePath,templateBO,"hello.pdf");
//        System.out.println(path);
//
//
//
//    }
//
//
//}