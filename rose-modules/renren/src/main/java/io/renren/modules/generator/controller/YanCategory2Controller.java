package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.YanCategoryEntity;
import io.renren.modules.generator.service.YanCategoryService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-05-23 12:48:39
 */
@RestController
@RequestMapping("generator/yancategory")
public class YanCategory2Controller {
    @Autowired
    private YanCategoryService yanCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:yancategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:yancategory:info")
    public R info(@PathVariable("id") Integer id){
		YanCategoryEntity yanCategory = yanCategoryService.getById(id);

        return R.ok().put("yanCategory", yanCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:yancategory:save")
    public R save(@RequestBody YanCategoryEntity yanCategory){
		yanCategoryService.save(yanCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:yancategory:update")
    public R update(@RequestBody YanCategoryEntity yanCategory){
		yanCategoryService.updateById(yanCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:yancategory:delete")
    public R delete(@RequestBody Integer[] ids){
		yanCategoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
