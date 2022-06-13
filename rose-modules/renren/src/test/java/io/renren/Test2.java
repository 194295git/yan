package io.renren;

/**
 * @author rose
 * @create 2021/8/7
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.exception.RRException;
import io.renren.common.utils.FileUtils;
import io.renren.common.utils.TimeUtils;
import io.renren.modules.app.utils.JwtUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2 {
    @Autowired
    private JwtUtils jwtUtils;


    final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE };

    // Requires positive x
    static int stringSize(int x) {
        for (int i=0; ; i++)
            if (x <= sizeTable[i])
                return i+1;
    }
    @Test
    public void test3(){


        int BJT = 1555;
        int UTC = BJT+2400 - 900;

        if (UTC > 2359) {
            UTC = UTC - 2400;
        }

        System.out.println(UTC);
    }
    @Test
    public void test0(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHH");
        String string = formatter.format(date);
        String mm = string.substring(0,2);
        String dd =string.substring(2,4);
        String hh = string.substring(4);
        if(new Integer(hh) - 9>0){
            System.out.println(dd+ (new Integer(hh) - 9));
            System.out.println(mm+"/"+dd+"/"+ (new Integer(hh) - 9));
        }else {
            System.out.println(new Integer(dd) -1);
            System.out.println(new Integer(hh) - 9 +24);
            System.out.println(mm +"/"+(new Integer(dd) -1)+"/" +(new Integer(hh) - 9 +24));
        }


    }

    @Test
    public void test() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("_yyyyMMdd_HHmm");
        String string = formatter.format(date);
        String substring = string.substring(10,12);
        Integer i = new Integer(substring) - 9;
        if (i >0){
            if (stringSize(i) == 1){
                System.out.println("h08"+string.substring(0,10)+"0"+i.toString());
            }else {
                System.out.println("h08"+string.substring(0,10)+i.toString());
            }

        }else {
            System.out.println("h08"+string.substring(0,10)+i.toString());
        }


    }
    @Test
    public void test5(){
        System.out.println(false != true);

    }


    @Test
    public void test6(){
        String fileName = "H08_20210807_0500_L2WLF010_FLDK.06001_06001.csv";
        String basePath = FileUtils.getRootPath();

        String savePath = "UploadFiles/"+ "Temporary/" + TimeUtils.formatDate2String(new Date(),"yyyyMM/");
        File fileDir = new File(basePath + savePath);
        System.out.println(fileDir);
        System.out.println(fileDir.exists());
        if (!fileDir.exists()) fileDir.mkdirs();
    }

    @Test
    public void test7(){
        try {
            Thread.sleep(500);
            int rs = 5/0;
        } catch (Exception e) {
            if (e instanceof ArithmeticException){
                throw new RRException("爆出错误");
            }

        }

    }

    @Test
    public void test8(){

        long thisTime = System.currentTimeMillis();
        //下面就是15 分钟了 感觉15分钟这个地方就挺好用的；就先这样吧
//        Date date = new Date(thisTime - 32400000L+900000L);

        //这个大概对应每个时间的  那么不到25的就可以正常 获取到，如果超过25的
        Date date = new Date(thisTime - 32400000L+900000L);

        System.out.println(TimeUtils.formatDate2String(date, "yyyyMMddHHmm").substring(0,11)+"0");

        //如果要减10 的话 就应该又是十分钟这样
        Date date2 = new Date(thisTime - 32400000L+900000L-1000000L);
        System.out.println(TimeUtils.formatDate2String(date2, "yyyyMMddHHmm").substring(0,11)+"0");
    }

}
