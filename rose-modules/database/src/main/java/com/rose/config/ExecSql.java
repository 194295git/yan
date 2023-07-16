package com.rose.config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.rose.utils.SqlScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author rose
 * @create 2022/11/19
 */
@Configuration
public class ExecSql {


    @Autowired
    SystemConfig systemConfig;

    @Bean
    SqlScriptRunner SqlScriptRunner(){

        return new SqlScriptRunner();
    }

    @Bean
    ActiveRecordPlugin activeRecordPlugin(){
        C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://127.0.0.1:3306/yan_login?characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai",
                "root", "1942951600", "com.mysql.cj.jdbc.Driver", 1, 1, 1, 20, 1);
        c3p0Plugin.setDriverClass("com.mysql.cj.jdbc.Driver");
        c3p0Plugin.start();
        return new ActiveRecordPlugin(c3p0Plugin);
    }
}
