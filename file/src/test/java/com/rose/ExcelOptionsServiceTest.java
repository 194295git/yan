package com.rose;

import com.alibaba.excel.EasyExcel;


import com.rose.dao.YanMajor1;
import com.rose.handler.DemoDataListener;
import com.rose.handler.UserExcelListener;
import org.junit.Test;


public class ExcelOptionsServiceTest   extends DemoApplicationTests{

//    @Autowired
//    private ExcelOptionsService excelOptionsService;

    /**
     * 测试读取excel
     **/
//    @Test
//    public void testReadExcel() {
//        // 这里的excel文件可以 为xls或xlsx结尾
//        File file = new File("C:\\Users\\samsung\\Desktop\\python转txt为excel\\uuju.xlsx");
//        List<List<String>> result = new ArrayList<>();
//        try {
//            result = StringExcelListener.writeWithoutHead(new FileInputStream(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Assert.assertNotNull(result);
//        System.out.println("读取结果：" + result);
//    }
    @Test
    public void readExcel(){

//        System.out.println(TestFileUtil.getPath());
//        File file = new File("C:\\Users\\samsung\\Desktop\\python转txt为excel\\uuju.xlsx");
//        String fileName = "C:\\Users\\samsung\\Desktop\\python转txt为excel"+ File.separator + "uuju.xlsx";
        String fileName = "C:\\Users\\samsung\\Desktop\\python转txt为excel\\uuju.xlsx";
//        EasyExcel.read(fileName, YanMajor1.class, new UserExcelListener()).sheet().doRead();
        EasyExcel.read(fileName, YanMajor1.class, new UserExcelListener()).sheet().doRead();

    }
    @Test
    public void read() throws Exception{
//        String localFilePath = "C:\\Users\\samsung\\Desktop\\python转txt为excel\\uuju.xlsx";
//        InputStream fileStream = new FileInputStream(localFilePath);//文件流
//        List<Object> dataList = EasyExcelFactory.read(fileStream,new Sheet(1,1, YanMajor1.class));//读取文件流
//        System.out.println(dataList);//读取结果
    }
    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        String fileName = "C:\\Users\\samsung\\Desktop\\python转txt为excel\\uuju.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, YanMajor1.class, new DemoDataListener()).sheet().doRead();


    }

}