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

import io.renren.modules.generator.entity.YanUserEntity;
import io.renren.modules.generator.service.YanUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:19:18
 */
@RestController
@RequestMapping("generator/yanuser")
public class YanUserController {
    @Autowired
    private YanUserService yanUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yanuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{openid}")
    @RequiresPermissions("generator:yanuser:info")
    public R info(@PathVariable("openid") String openid){
		YanUserEntity yanUser = yanUserService.getById(openid);

        return R.ok().put("yanUser", yanUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yanuser:save")
    public R save(@RequestBody YanUserEntity yanUser){
		yanUserService.save(yanUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:yanuser:update")
    public R update(@RequestBody YanUserEntity yanUser){
		yanUserService.updateById(yanUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yanuser:delete")
    public R delete(@RequestBody String[] openids){
		yanUserService.removeByIds(Arrays.asList(openids));

        return R.ok();
    }

}
