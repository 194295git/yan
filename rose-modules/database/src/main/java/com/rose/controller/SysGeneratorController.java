/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.controller;


import com.rose.config.DynamicDataSource;
import com.rose.service.SysGeneratorService;
import com.rose.utils.PageUtils;
import com.rose.utils.Query;
import com.rose.utils.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		DynamicDataSource.name.set("r");
		PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list2")
	public R list2(@RequestParam Map<String, Object> params){
		DynamicDataSource.name.set("w");
		PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(String tables,String dataSource, HttpServletResponse response) throws IOException{
		//这个也是需要动态的设置一下的
		if(dataSource.equals("0")){
			DynamicDataSource.name.set("r");
		}
		if(dataSource.equals("1")){
			DynamicDataSource.name.set("w");
		}
		byte[] data = sysGeneratorService.generatorCode(tables.split(","));
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());
	}
}
