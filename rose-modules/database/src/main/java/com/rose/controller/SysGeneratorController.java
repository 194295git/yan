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
import java.util.List;
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
		selectDynamicDatasource(params);

		PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));

		return R.ok().put("page", pageUtil);
	}

	private void selectDynamicDatasource(@RequestParam Map<String, Object> params) {
		String dataSource = (String) params.get("dataSource");
		//这个也是需要动态的设置一下的
		if(dataSource.equals("0")){
			DynamicDataSource.name.set("r");
		}
		if(dataSource.equals("1")){
			DynamicDataSource.name.set("w");
		}
	}


	/**
	 * 查询列表所有数据.
	 * @param params  {"tableName":""}
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listTableData")
	public R listTableData(@RequestParam Map<String, Object> params){
		selectDynamicDatasource(params);
		List pageUtil = sysGeneratorService.queryTableList(new Query(params));

		return R.ok().put("page", pageUtil);
	}
	/**
	 * 展示所有列.
	 * 原来之前都错在没有加ResponseBody了，失误了，报错一直404给我弄反方向了
	 */
	@ResponseBody
	@RequestMapping("/showColumn")
	public R showColumn(@RequestParam Map<String, Object> params) throws IOException{
		String dataSource = (String) params.get("dataSource");
		String tables = (String) params.get("tables");

		//这个也是需要动态的设置一下的
		if(dataSource.equals("0")){
			DynamicDataSource.name.set("r");
		}
		if(dataSource.equals("1")){
			DynamicDataSource.name.set("w");
		}
		//查询出所有的列名
		List<Map<String, String>> maps = sysGeneratorService.queryColumns(tables);
		//查询出所有的数据
		return R.ok().put("page", maps);
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
