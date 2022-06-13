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

import io.renren.modules.generator.entity.YanMajorMeEntity;
import io.renren.modules.generator.service.YanMajorMeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:28:50
 */
@RestController
@RequestMapping("generator/yanmajorme")
public class YanMajorMeController {
    @Autowired
    private YanMajorMeService yanMajorMeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yanmajorme:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanMajorMeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:yanmajorme:info")
    public R info(@PathVariable("id") Integer id){
		YanMajorMeEntity yanMajorMe = yanMajorMeService.getById(id);

        return R.ok().put("yanMajorMe", yanMajorMe);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yanmajorme:save")
    public R save(@RequestBody YanMajorMeEntity yanMajorMe){
		yanMajorMeService.save(yanMajorMe);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:yanmajorme:update")
    public R update(@RequestBody YanMajorMeEntity yanMajorMe){
		yanMajorMeService.updateById(yanMajorMe);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yanmajorme:delete")
    public R delete(@RequestBody Integer[] ids){
		yanMajorMeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
