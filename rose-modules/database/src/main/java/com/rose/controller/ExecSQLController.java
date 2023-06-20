package com.rose.controller;

import com.rose.config.SystemConfig;
import com.rose.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rose
 * @create 2022/11/19
 */
@Controller
@RequestMapping("/database")
@Api(tags = "数据库执行脚本" , description = "ExecSQLController | 数据库Exec模块")
public class ExecSQLController {

    @Autowired
    SystemConfig systemConfig;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/execSql")
    @ApiOperation("执行指定的sql【改进：将可执行sql脚本作为参数】")
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
