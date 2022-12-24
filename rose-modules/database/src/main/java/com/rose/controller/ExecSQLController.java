package com.rose.controller;

import com.jfinal.plugin.activerecord.Db;
import com.rose.config.DynamicDataSource;
import com.rose.config.SystemConfig;
import com.rose.utils.PageUtils;
import com.rose.utils.Query;
import com.rose.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.util.Map;

/**
 * @author rose
 * @create 2022/11/19
 */
@Controller
@RequestMapping("/database")
public class ExecSQLController {

    @Autowired
    SystemConfig systemConfig;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/execSql")
    public R list(){
        try {
            systemConfig.initMysqlDruid();
            systemConfig.initTableDesign();
            return R.ok();
        }catch(Exception e){

            return R.error(e.getMessage());
        }

    }

}
