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

import io.renren.modules.generator.entity.YanProductEntity;
import io.renren.modules.generator.service.YanProductService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-05-23 13:13:58
 */
@RestController
@RequestMapping("generator/yanproduct")
public class YanProductController {
    @Autowired
    private YanProductService yanProductService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:yanproduct:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanProductService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:yanproduct:info")
    public R info(@PathVariable("id") Integer id){
		YanProductEntity yanProduct = yanProductService.getById(id);

        return R.ok().put("yanProduct", yanProduct);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yanproduct:save")
    public R save(@RequestBody YanProductEntity yanProduct){
		yanProductService.save(yanProduct);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:yanproduct:update")
    public R update(@RequestBody YanProductEntity yanProduct){
		yanProductService.updateById(yanProduct);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yanproduct:delete")
    public R delete(@RequestBody Integer[] ids){
		yanProductService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
