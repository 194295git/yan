package io.renren.modules.generator.controller;

import com.rose.common.base.R;
import com.rose.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanCollegeEntity;
import io.renren.modules.generator.service.YanCollegeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:19:18
 */
@RestController
@RequestMapping("generator/yancollege")
public class YanCollegeController {
    @Autowired
    private YanCollegeService yanCollegeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yancollege:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanCollegeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{index}")
    @RequiresPermissions("generator:yancollege:info")
    public R info(@PathVariable("index") Integer index){
		YanCollegeEntity yanCollege = yanCollegeService.getById(index);

        return R.ok().put("yanCollege", yanCollege);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yancollege:save")
    public R save(@RequestBody YanCollegeEntity yanCollege){
		yanCollegeService.save(yanCollege);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:yancollege:update")
    public R update(@RequestBody YanCollegeEntity yanCollege){
		yanCollegeService.updateById(yanCollege);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yancollege:delete")
    public R delete(@RequestBody Integer[] indexs){
		yanCollegeService.removeByIds(Arrays.asList(indexs));

        return R.ok();
    }

}
