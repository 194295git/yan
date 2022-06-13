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

import io.renren.modules.generator.entity.YanMajorCollegeEntity;
import io.renren.modules.generator.service.YanMajorCollegeService;
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
@RequestMapping("generator/yanmajorcollege")
public class YanMajorCollegeController {
    @Autowired
    private YanMajorCollegeService yanMajorCollegeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yanmajorcollege:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanMajorCollegeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:yanmajorcollege:info")
    public R info(@PathVariable("id") Integer id){
		YanMajorCollegeEntity yanMajorCollege = yanMajorCollegeService.getById(id);

        return R.ok().put("yanMajorCollege", yanMajorCollege);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yanmajorcollege:save")
    public R save(@RequestBody YanMajorCollegeEntity yanMajorCollege){
		yanMajorCollegeService.save(yanMajorCollege);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:yanmajorcollege:update")
    public R update(@RequestBody YanMajorCollegeEntity yanMajorCollege){
		yanMajorCollegeService.updateById(yanMajorCollege);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yanmajorcollege:delete")
    public R delete(@RequestBody Integer[] ids){
		yanMajorCollegeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
