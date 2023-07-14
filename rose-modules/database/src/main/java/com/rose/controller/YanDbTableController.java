package com.rose.controller;

import com.rose.common.base.R;
import com.rose.common.utils.PageUtils;
import com.rose.config.DynamicDataSource;
import com.rose.entity.YanDbTableEntity;
import com.rose.service.YanDbTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author rose
 * @create 2022/11/21
 */
@RestController
@RequestMapping("generator/yandbtable")
public class YanDbTableController {
    @Autowired
    private YanDbTableService yanDbTableService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        DynamicDataSource.name.set("w");
        PageUtils page = yanDbTableService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        DynamicDataSource.name.set("w");
        YanDbTableEntity yanDbTable = yanDbTableService.getById(id);

        return R.ok().put("yanDbTable", yanDbTable);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YanDbTableEntity yanDbTable){
        yanDbTableService.save(yanDbTable);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody YanDbTableEntity yanDbTable){
        DynamicDataSource.name.set("w");
        yanDbTableService.updateById(yanDbTable);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        DynamicDataSource.name.set("w");
        yanDbTableService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
