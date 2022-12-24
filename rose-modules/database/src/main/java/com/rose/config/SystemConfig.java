package com.rose.config;

/**
 * @author rose
 * @create 2022/11/19
 */

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.rose.utils.SqlScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class SystemConfig
{
    @Autowired
    SqlScriptRunner runner;


    @Autowired
    ActiveRecordPlugin arp;
    /**
     * 使用mysql数据库
     * 使用者不需关注数据库配置的初始化过程，本demo是借助jfinal框架初始化的数据库配置，
     * 使用者完全可以用其他方式代替
     */
    public  void initMysqlDruid()
    {
        arp.setDialect(new MysqlDialect());
        arp.setDevMode(true);
        arp.setShowSql(true);
        arp.setContainerFactory(new CaseInsensitiveContainerFactory());
        arp.start();
    }

    /**
     * 表结构初始化
     */
    public  void initTableDesign()
    {
        Connection conn = null;
        try
        {
            conn = Db.use().getConfig().getConnection();
            URL systemResource = ClassLoader.getSystemResource("");
//            E:\lang\project\git\micro_service_yan\yan\rose-modules\database\target\classes\sql
            File file = new File("E:\\lang\\project\\git\\micro_service_yan\\yan\\rose-modules\\database\\target\\classes\\sql");
            File[] files = file.listFiles();
            if (files.length == 0)
            {
                return;
            }
            List<File> fileList = Arrays.asList(file.listFiles());
            fileList.sort(Comparator.comparing(File::getName));
            for (File requestFile : fileList)
            {
                // 如下代码是读者需要关注的
//                SqlScriptRunner runner = new SqlScriptRunner(conn);
                runner.setConnection(conn);
                runner.setEscapeProcessing(false);
                runner.setSendFullScript(false);
                runner.setDelimiter(";;");
                runner.runScript(new InputStreamReader(new FileInputStream(requestFile), "UTF-8"));
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException("initTableDesign error!");
        }
        finally
        {
            Db.use().getConfig().close(conn);
        }
    }
}